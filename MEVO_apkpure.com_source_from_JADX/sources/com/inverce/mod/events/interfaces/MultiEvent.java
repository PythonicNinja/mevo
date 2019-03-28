package com.inverce.mod.events.interfaces;

public interface MultiEvent<T> {
    void addListener(T t);

    void clear();

    void removeListener(T t);
}
