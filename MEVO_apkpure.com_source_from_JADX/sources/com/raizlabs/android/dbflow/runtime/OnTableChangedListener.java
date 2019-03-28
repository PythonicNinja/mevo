package com.raizlabs.android.dbflow.runtime;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;

public interface OnTableChangedListener {
    void onTableChanged(@Nullable Class<?> cls, @NonNull Action action);
}
