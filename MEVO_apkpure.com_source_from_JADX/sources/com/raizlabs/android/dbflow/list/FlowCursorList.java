package com.raizlabs.android.dbflow.list;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.InstanceAdapter;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.cache.ModelCache;
import com.raizlabs.android.dbflow.structure.cache.ModelLruCache;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FlowCursorList<TModel> implements Iterable<TModel>, IFlowCursorIterator<TModel> {
    public static final int DEFAULT_CACHE_SIZE = 50;
    public static final int MIN_CACHE_SIZE = 20;
    private boolean cacheModels;
    @Nullable
    private FlowCursor cursor;
    private final Set<OnCursorRefreshListener<TModel>> cursorRefreshListenerSet;
    private InstanceAdapter<TModel> instanceAdapter;
    private ModelCache<TModel, ?> modelCache;
    @Nullable
    private ModelQueriable<TModel> modelQueriable;
    private Class<TModel> table;

    public static class Builder<TModel> {
        private boolean cacheModels = true;
        private FlowCursor cursor;
        private ModelCache<TModel, ?> modelCache;
        private final Class<TModel> modelClass;
        private ModelQueriable<TModel> modelQueriable;

        public Builder(@NonNull Class<TModel> cls) {
            this.modelClass = cls;
        }

        public Builder(@NonNull ModelQueriable<TModel> modelQueriable) {
            this.modelClass = modelQueriable.getTable();
            modelQueriable(modelQueriable);
        }

        @NonNull
        public Builder<TModel> cursor(@Nullable Cursor cursor) {
            if (cursor != null) {
                this.cursor = FlowCursor.from(cursor);
            }
            return this;
        }

        @NonNull
        public Builder<TModel> modelQueriable(@Nullable ModelQueriable<TModel> modelQueriable) {
            this.modelQueriable = modelQueriable;
            return this;
        }

        @NonNull
        public Builder<TModel> cacheModels(boolean z) {
            this.cacheModels = z;
            return this;
        }

        @NonNull
        public Builder<TModel> modelCache(@Nullable ModelCache<TModel, ?> modelCache) {
            this.modelCache = modelCache;
            if (modelCache != null) {
                cacheModels(true);
            }
            return this;
        }

        @NonNull
        public FlowCursorList<TModel> build() {
            return new FlowCursorList();
        }
    }

    public interface OnCursorRefreshListener<TModel> {
        void onCursorRefreshed(@NonNull FlowCursorList<TModel> flowCursorList);
    }

    private FlowCursorList(Builder<TModel> builder) {
        this.cursorRefreshListenerSet = new HashSet();
        this.table = builder.modelClass;
        this.modelQueriable = builder.modelQueriable;
        if (builder.modelQueriable == null) {
            this.cursor = builder.cursor;
            if (this.cursor == null) {
                this.modelQueriable = SQLite.select(new IProperty[0]).from(this.table);
                this.cursor = this.modelQueriable.query();
            }
        } else {
            this.cursor = builder.modelQueriable.query();
        }
        this.cacheModels = builder.cacheModels;
        if (this.cacheModels) {
            this.modelCache = builder.modelCache;
            if (this.modelCache == null) {
                this.modelCache = ModelLruCache.newInstance(0);
            }
        }
        this.instanceAdapter = FlowManager.getInstanceAdapter(builder.modelClass);
        setCacheModels(this.cacheModels);
    }

    @NonNull
    InstanceAdapter<TModel> getInstanceAdapter() {
        return this.instanceAdapter;
    }

    @NonNull
    ModelAdapter<TModel> getModelAdapter() {
        return (ModelAdapter) this.instanceAdapter;
    }

    @NonNull
    public FlowCursorIterator<TModel> iterator() {
        return new FlowCursorIterator(this);
    }

    @NonNull
    public FlowCursorIterator<TModel> iterator(int i, long j) {
        return new FlowCursorIterator(this, i, j);
    }

    public void addOnCursorRefreshListener(@NonNull OnCursorRefreshListener<TModel> onCursorRefreshListener) {
        synchronized (this.cursorRefreshListenerSet) {
            this.cursorRefreshListenerSet.add(onCursorRefreshListener);
        }
    }

    public void removeOnCursorRefreshListener(@NonNull OnCursorRefreshListener<TModel> onCursorRefreshListener) {
        synchronized (this.cursorRefreshListenerSet) {
            this.cursorRefreshListenerSet.remove(onCursorRefreshListener);
        }
    }

    void setCacheModels(boolean z) {
        this.cacheModels = z;
        if (!z) {
            clearCache();
        }
    }

    public void clearCache() {
        if (this.cacheModels) {
            this.modelCache.clear();
        }
    }

    public synchronized void refresh() {
        warnEmptyCursor();
        if (this.cursor != null) {
            this.cursor.close();
        }
        if (this.modelQueriable == null) {
            throw new IllegalStateException("Cannot refresh this FlowCursorList. This list was instantiated from a Cursor. Once closed, we cannot reopen it. Construct a new instance and swap with this instance.");
        }
        this.cursor = this.modelQueriable.query();
        if (this.cacheModels) {
            this.modelCache.clear();
            setCacheModels(true);
        }
        synchronized (this.cursorRefreshListenerSet) {
            for (OnCursorRefreshListener onCursorRefreshed : this.cursorRefreshListenerSet) {
                onCursorRefreshed.onCursorRefreshed(this);
            }
        }
    }

    @Nullable
    public ModelQueriable<TModel> modelQueriable() {
        return this.modelQueriable;
    }

    @Nullable
    public TModel getItem(long j) {
        throwIfCursorClosed();
        warnEmptyCursor();
        if (this.cacheModels) {
            TModel tModel = this.modelCache.get(Long.valueOf(j));
            if (tModel != null || this.cursor == null || !this.cursor.moveToPosition((int) j)) {
                return tModel;
            }
            TModel convertToData = this.instanceAdapter.getSingleModelLoader().convertToData(this.cursor, null, false);
            this.modelCache.addModel(Long.valueOf(j), convertToData);
            return convertToData;
        } else if (this.cursor == null || this.cursor.moveToPosition((int) j) == null) {
            return null;
        } else {
            return this.instanceAdapter.getSingleModelLoader().convertToData(this.cursor, null, false);
        }
    }

    @NonNull
    public List<TModel> getAll() {
        throwIfCursorClosed();
        warnEmptyCursor();
        List<TModel> arrayList;
        if (this.cacheModels) {
            arrayList = new ArrayList();
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            return arrayList;
        }
        if (this.cursor == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = FlowManager.getModelAdapter(this.table).getListModelLoader().convertToData(this.cursor, null);
        }
        return arrayList;
    }

    public boolean isEmpty() {
        throwIfCursorClosed();
        warnEmptyCursor();
        return getCount() == 0;
    }

    public long getCount() {
        throwIfCursorClosed();
        warnEmptyCursor();
        return this.cursor != null ? (long) this.cursor.getCount() : 0;
    }

    @NonNull
    public ModelCache<TModel, ?> modelCache() {
        return this.modelCache;
    }

    public boolean cachingEnabled() {
        return this.cacheModels;
    }

    public void close() {
        warnEmptyCursor();
        if (this.cursor != null) {
            this.cursor.close();
        }
        this.cursor = null;
    }

    @Nullable
    public Cursor cursor() {
        throwIfCursorClosed();
        warnEmptyCursor();
        return this.cursor;
    }

    @NonNull
    public Class<TModel> table() {
        return this.table;
    }

    private void throwIfCursorClosed() {
        if (this.cursor != null && this.cursor.isClosed()) {
            throw new IllegalStateException("Cursor has been closed for FlowCursorList");
        }
    }

    private void warnEmptyCursor() {
        if (this.cursor == null) {
            FlowLog.log(Level.W, "Cursor was null for FlowCursorList");
        }
    }

    @NonNull
    public Builder<TModel> newBuilder() {
        return new Builder(this.table).modelQueriable(this.modelQueriable).cursor(this.cursor).cacheModels(this.cacheModels).modelCache(this.modelCache);
    }
}
