package com.raizlabs.android.dbflow.structure.listener;

import android.database.Cursor;
import android.support.annotation.NonNull;

public interface LoadFromCursorListener {
    void onLoadFromCursor(@NonNull Cursor cursor);
}
