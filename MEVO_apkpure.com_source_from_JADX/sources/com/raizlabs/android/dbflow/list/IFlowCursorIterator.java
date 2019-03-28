package com.raizlabs.android.dbflow.list;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.Closeable;
import java.io.IOException;

public interface IFlowCursorIterator<TModel> extends Closeable {
    void close() throws IOException;

    @Nullable
    Cursor cursor();

    long getCount();

    @Nullable
    TModel getItem(long j);

    @NonNull
    FlowCursorIterator<TModel> iterator();

    @NonNull
    FlowCursorIterator<TModel> iterator(int i, long j);
}
