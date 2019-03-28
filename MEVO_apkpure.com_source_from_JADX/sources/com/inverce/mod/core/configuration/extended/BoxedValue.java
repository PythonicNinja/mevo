package com.inverce.mod.core.configuration.extended;

public class BoxedValue<T> {
    protected T value;

    public BoxedValue(T t) {
        this.value = t;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T t) {
        this.value = t;
    }
}
