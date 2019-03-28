package com.inverce.mod.core.configuration;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChangeValueHandler<T> implements ValueChangedEvent<ValueChanged<T>> {
    @Nullable
    protected List<ValueChanged<T>> list = null;

    @Nullable
    private List<ValueChanged<T>> list() {
        if (this.list == null) {
            this.list = new ArrayList();
        }
        return this.list;
    }

    public void addListener(@NonNull ValueChanged<T> valueChanged) {
        list().add(valueChanged);
    }

    public void removeListener(ValueChanged<T> valueChanged) {
        list().remove(valueChanged);
    }

    public void clear() {
        list().clear();
    }

    public void postNewValue(Value<T> value, T t) {
        Iterator it = new ArrayList(list()).iterator();
        while (it.hasNext()) {
            ((ValueChanged) it.next()).valueChanged(value, t);
        }
    }
}
