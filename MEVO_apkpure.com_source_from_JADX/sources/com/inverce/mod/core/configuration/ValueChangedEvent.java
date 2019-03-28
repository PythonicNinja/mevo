package com.inverce.mod.core.configuration;

import android.support.annotation.NonNull;

public interface ValueChangedEvent<T> {
    void addListener(@NonNull T t);

    void clear();

    void removeListener(T t);
}
