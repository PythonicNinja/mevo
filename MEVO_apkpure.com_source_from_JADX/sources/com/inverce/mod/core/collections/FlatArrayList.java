package com.inverce.mod.core.collections;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class FlatArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {
    protected List<E> cache;
    protected FlatArrayListStore<E> container;
    protected boolean flatError;
    protected boolean optimize;

    public static class FlatArrayListStore<T> extends ArrayList<List<? extends T>> {
        @NonNull
        private List<T> singleton(T t) {
            List arrayList = new ArrayList(1);
            arrayList.add(t);
            return arrayList;
        }

        public T getSingle(int i) {
            List list = (List) super.get(i);
            if (list.size() == 1) {
                return list.get(0);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("No singleton object at position: ");
            stringBuilder.append(i);
            throw new NoSuchElementException(stringBuilder.toString());
        }

        public List<? extends T> setSingle(int i, T t) {
            return (List) super.set(i, singleton(t));
        }

        public boolean addSingle(T t) {
            return super.add(singleton(t));
        }

        public void addSingle(int i, T t) {
            super.add(i, singleton(t));
        }

        public boolean removeSingle(@Nullable T t) {
            boolean z = false;
            if (t == null) {
                return false;
            }
            for (int i = 0; i < size(); i++) {
                List list = (List) get(i);
                if (list.size() == 1 && t.equals(list.get(0))) {
                    if (remove(i) != null) {
                        z = true;
                    }
                    return z;
                }
            }
            return false;
        }
    }

    public FlatArrayList() {
        this(false);
    }

    public FlatArrayList(boolean z) {
        this.optimize = false;
        this.flatError = false;
        this.container = new FlatArrayListStore();
        this.cache = new ArrayList(0);
        this.optimize = z;
    }

    public E get(int i) {
        return this.optimize ? this.cache.get(i) : getFlatElement(i);
    }

    public int size() {
        return this.optimize ? this.cache.size() : calculateSize();
    }

    private synchronized E getFlatElement(int i) {
        int size = size();
        if (i >= 0) {
            if (i < size) {
                size = 0;
                int i2 = 0;
                while (size < this.container.size()) {
                    List list = (List) this.container.get(size);
                    int size2 = list.size() + i2;
                    if (i < i2 || i >= size2) {
                        size++;
                        i2 = size2;
                    } else {
                        return list.get(i - i2);
                    }
                }
                if (this.flatError) {
                    this.flatError = false;
                    throw new IllegalStateException("Reached size and not found index.");
                }
                this.flatError = true;
                i = getFlatElement(i);
                this.flatError = false;
                return i;
            }
        }
        throw new IndexOutOfBoundsException(String.format(Locale.getDefault(), "Accessing %d when size is %d", new Object[]{Integer.valueOf(i), Integer.valueOf(size)}));
    }

    private synchronized int calculateSize() {
        int i;
        i = 0;
        Iterator it = this.container.iterator();
        while (it.hasNext()) {
            i += ((List) it.next()).size();
        }
        return i;
    }

    public synchronized void refresh() {
        List arrayList = new ArrayList();
        Iterator it = this.container.iterator();
        while (it.hasNext()) {
            arrayList.addAll((List) it.next());
        }
        this.cache = arrayList;
    }

    public FlatArrayListStore<E> store() {
        return this.container;
    }
}
