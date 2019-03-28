package com.raizlabs.android.dbflow.runtime;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface TableNotifierRegister {
    boolean isSubscribed();

    <T> void register(@NonNull Class<T> cls);

    void setListener(@Nullable OnTableChangedListener onTableChangedListener);

    <T> void unregister(@NonNull Class<T> cls);

    void unregisterAll();
}
