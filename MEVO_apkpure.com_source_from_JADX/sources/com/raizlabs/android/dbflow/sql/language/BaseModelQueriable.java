package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.list.FlowCursorList.Builder;
import com.raizlabs.android.dbflow.list.FlowQueryList;
import com.raizlabs.android.dbflow.runtime.NotifyDistributor;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.queriable.AsyncQuery;
import com.raizlabs.android.dbflow.sql.queriable.ListModelLoader;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.sql.queriable.SingleModelLoader;
import com.raizlabs.android.dbflow.structure.InstanceAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.List;

public abstract class BaseModelQueriable<TModel> extends BaseQueriable<TModel> implements ModelQueriable<TModel>, Query {
    private boolean cachingEnabled = true;
    private InstanceAdapter<TModel> retrievalAdapter;

    protected BaseModelQueriable(Class<TModel> cls) {
        super(cls);
    }

    private InstanceAdapter<TModel> getRetrievalAdapter() {
        if (this.retrievalAdapter == null) {
            this.retrievalAdapter = FlowManager.getInstanceAdapter(getTable());
        }
        return this.retrievalAdapter;
    }

    @NonNull
    public CursorResult<TModel> queryResults() {
        return new CursorResult(getRetrievalAdapter().getModelClass(), query());
    }

    @NonNull
    public List<TModel> queryList() {
        String query = getQuery();
        Level level = Level.V;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Executing query: ");
        stringBuilder.append(query);
        FlowLog.log(level, stringBuilder.toString());
        return getListModelLoader().load(query);
    }

    @Nullable
    public TModel querySingle() {
        String query = getQuery();
        Level level = Level.V;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Executing query: ");
        stringBuilder.append(query);
        FlowLog.log(level, stringBuilder.toString());
        return getSingleModelLoader().load(query);
    }

    public TModel querySingle(@NonNull DatabaseWrapper databaseWrapper) {
        String query = getQuery();
        Level level = Level.V;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Executing query: ");
        stringBuilder.append(query);
        FlowLog.log(level, stringBuilder.toString());
        return getSingleModelLoader().load(databaseWrapper, query);
    }

    @NonNull
    public List<TModel> queryList(@NonNull DatabaseWrapper databaseWrapper) {
        String query = getQuery();
        Level level = Level.V;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Executing query: ");
        stringBuilder.append(query);
        FlowLog.log(level, stringBuilder.toString());
        return getListModelLoader().load(databaseWrapper, query);
    }

    @NonNull
    public FlowCursorList<TModel> cursorList() {
        return new Builder(getTable()).cacheModels(this.cachingEnabled).modelQueriable(this).build();
    }

    @NonNull
    public FlowQueryList<TModel> flowQueryList() {
        return new FlowQueryList.Builder(getTable()).cacheModels(this.cachingEnabled).modelQueriable(this).build();
    }

    public long executeUpdateDelete() {
        return executeUpdateDelete(FlowManager.getWritableDatabaseForTable(getTable()));
    }

    public long executeUpdateDelete(@NonNull DatabaseWrapper databaseWrapper) {
        long executeUpdateDelete = databaseWrapper.compileStatement(getQuery()).executeUpdateDelete();
        if (executeUpdateDelete > 0) {
            NotifyDistributor.get().notifyTableChanged(getTable(), getPrimaryAction());
        }
        return executeUpdateDelete;
    }

    @NonNull
    public AsyncQuery<TModel> async() {
        return new AsyncQuery(this);
    }

    @NonNull
    public <QueryClass> List<QueryClass> queryCustomList(@NonNull Class<QueryClass> cls) {
        String query = getQuery();
        Level level = Level.V;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Executing query: ");
        stringBuilder.append(query);
        FlowLog.log(level, stringBuilder.toString());
        cls = FlowManager.getQueryModelAdapter(cls);
        if (this.cachingEnabled) {
            return cls.getListModelLoader().load(query);
        }
        return cls.getNonCacheableListModelLoader().load(query);
    }

    @Nullable
    public <QueryClass> QueryClass queryCustomSingle(@NonNull Class<QueryClass> cls) {
        String query = getQuery();
        Level level = Level.V;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Executing query: ");
        stringBuilder.append(query);
        FlowLog.log(level, stringBuilder.toString());
        cls = FlowManager.getQueryModelAdapter(cls);
        if (this.cachingEnabled) {
            return cls.getSingleModelLoader().load(query);
        }
        return cls.getNonCacheableSingleModelLoader().load(query);
    }

    @NonNull
    public ModelQueriable<TModel> disableCaching() {
        this.cachingEnabled = false;
        return this;
    }

    private ListModelLoader<TModel> getListModelLoader() {
        if (this.cachingEnabled) {
            return getRetrievalAdapter().getListModelLoader();
        }
        return getRetrievalAdapter().getNonCacheableListModelLoader();
    }

    private SingleModelLoader<TModel> getSingleModelLoader() {
        if (this.cachingEnabled) {
            return getRetrievalAdapter().getSingleModelLoader();
        }
        return getRetrievalAdapter().getNonCacheableSingleModelLoader();
    }
}
