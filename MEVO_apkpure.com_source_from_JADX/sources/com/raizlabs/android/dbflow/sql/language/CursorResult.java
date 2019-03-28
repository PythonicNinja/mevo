package com.raizlabs.android.dbflow.sql.language;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.list.FlowCursorIterator;
import com.raizlabs.android.dbflow.list.IFlowCursorIterator;
import com.raizlabs.android.dbflow.structure.InstanceAdapter;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.util.ArrayList;
import java.util.List;

public class CursorResult<TModel> implements IFlowCursorIterator<TModel> {
    @Nullable
    private FlowCursor cursor;
    private final InstanceAdapter<TModel> retrievalAdapter;

    CursorResult(Class<TModel> cls, @Nullable Cursor cursor) {
        if (cursor != null) {
            this.cursor = FlowCursor.from(cursor);
        }
        this.retrievalAdapter = FlowManager.getInstanceAdapter(cls);
    }

    public void swapCursor(@Nullable FlowCursor flowCursor) {
        if (!(this.cursor == null || this.cursor.isClosed())) {
            this.cursor.close();
        }
        this.cursor = flowCursor;
    }

    @NonNull
    public List<TModel> toList() {
        return this.cursor != null ? this.retrievalAdapter.getListModelLoader().convertToData(this.cursor, null) : new ArrayList();
    }

    @NonNull
    public List<TModel> toListClose() {
        List<TModel> load = this.cursor != null ? this.retrievalAdapter.getListModelLoader().load(this.cursor) : new ArrayList();
        close();
        return load;
    }

    @NonNull
    public <TCustom> List<TCustom> toCustomList(@NonNull Class<TCustom> cls) {
        return this.cursor != null ? FlowManager.getQueryModelAdapter(cls).getListModelLoader().convertToData(this.cursor, null) : new ArrayList();
    }

    @NonNull
    public <TCustom> List<TCustom> toCustomListClose(@NonNull Class<TCustom> cls) {
        cls = this.cursor != null ? FlowManager.getQueryModelAdapter(cls).getListModelLoader().load(this.cursor) : new ArrayList();
        close();
        return cls;
    }

    @Nullable
    public TModel toModel() {
        return this.cursor != null ? this.retrievalAdapter.getSingleModelLoader().convertToData(this.cursor, null) : null;
    }

    @Nullable
    public TModel toModelClose() {
        TModel load = this.cursor != null ? this.retrievalAdapter.getSingleModelLoader().load(this.cursor) : null;
        close();
        return load;
    }

    @Nullable
    public <TCustom> TCustom toCustomModel(@NonNull Class<TCustom> cls) {
        if (this.cursor != null) {
            return FlowManager.getQueryModelAdapter(cls).getSingleModelLoader().convertToData(this.cursor, null);
        }
        return null;
    }

    @Nullable
    public <TCustom> TCustom toCustomModelClose(@NonNull Class<TCustom> cls) {
        cls = this.cursor != null ? FlowManager.getQueryModelAdapter(cls).getSingleModelLoader().load(this.cursor) : null;
        close();
        return cls;
    }

    @Nullable
    public TModel getItem(long j) {
        if (this.cursor == null || this.cursor.moveToPosition((int) j) == null) {
            return null;
        }
        return this.retrievalAdapter.getSingleModelLoader().convertToData(this.cursor, null, false);
    }

    @NonNull
    public FlowCursorIterator<TModel> iterator() {
        return new FlowCursorIterator(this);
    }

    @NonNull
    public FlowCursorIterator<TModel> iterator(int i, long j) {
        return new FlowCursorIterator(this, i, j);
    }

    public long getCount() {
        return this.cursor == null ? 0 : (long) this.cursor.getCount();
    }

    @Nullable
    public Cursor cursor() {
        return this.cursor;
    }

    public void close() {
        if (this.cursor != null) {
            this.cursor.close();
        }
    }
}
