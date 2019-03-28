package com.inverce.mod.core.collections;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;

public class WeakArrayList<T> extends AbstractList<T> {
    @NonNull
    protected ArrayList<WeakReference<T>> impl = new ArrayList(1);

    @Nullable
    public T get(int i) {
        return ((WeakReference) this.impl.get(i)).get();
    }

    public int size() {
        return this.impl.size();
    }

    @Nullable
    public T set(int i, T t) {
        WeakReference weakReference = (WeakReference) this.impl.set(i, new WeakReference(t));
        return weakReference != null ? weakReference.get() : null;
    }

    public void add(int i, T t) {
        this.impl.add(i, new WeakReference(t));
    }

    @Nullable
    public T remove(int i) {
        WeakReference weakReference = (WeakReference) this.impl.remove(i);
        return weakReference != null ? weakReference.get() : null;
    }

    public int clearEmptyReferences() {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next() == null) {
                it.remove();
                i++;
            }
        }
        return i;
    }
}
