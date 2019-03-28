package org.simpleframework.xml.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

public class WeakCache<T> implements Cache<T> {
    private SegmentList list;

    private class Segment extends WeakHashMap<Object, T> {
        private Segment() {
        }

        public synchronized void cache(Object obj, T t) {
            put(obj, t);
        }

        public synchronized T fetch(Object obj) {
            return get(obj);
        }

        public synchronized T take(Object obj) {
            return remove(obj);
        }

        public synchronized boolean contains(Object obj) {
            return containsKey(obj);
        }
    }

    private class SegmentList implements Iterable<Segment> {
        private List<Segment> list = new ArrayList();
        private int size;

        public SegmentList(int i) {
            this.size = i;
            create(i);
        }

        public Iterator<Segment> iterator() {
            return this.list.iterator();
        }

        public Segment get(Object obj) {
            obj = segment(obj);
            return obj < this.size ? (Segment) this.list.get(obj) : null;
        }

        private void create(int i) {
            while (true) {
                int i2 = i - 1;
                if (i > 0) {
                    this.list.add(new Segment());
                    i = i2;
                } else {
                    return;
                }
            }
        }

        private int segment(Object obj) {
            return Math.abs(obj.hashCode() % this.size);
        }
    }

    public WeakCache() {
        this(10);
    }

    public WeakCache(int i) {
        this.list = new SegmentList(i);
    }

    public boolean isEmpty() {
        Iterator it = this.list.iterator();
        while (it.hasNext()) {
            if (!((Segment) it.next()).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void cache(Object obj, T t) {
        map(obj).cache(obj, t);
    }

    public T take(Object obj) {
        return map(obj).take(obj);
    }

    public T fetch(Object obj) {
        return map(obj).fetch(obj);
    }

    public boolean contains(Object obj) {
        return map(obj).contains(obj);
    }

    private Segment map(Object obj) {
        return this.list.get(obj);
    }
}
