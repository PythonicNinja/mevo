package com.annimon.stream.iterator;

import java.util.Iterator;

public final class PrimitiveIterator {

    public static abstract class OfDouble implements Iterator<Double> {
        public abstract double nextDouble();

        public Double next() {
            return Double.valueOf(nextDouble());
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    public static abstract class OfInt implements Iterator<Integer> {
        public abstract int nextInt();

        public Integer next() {
            return Integer.valueOf(nextInt());
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    public static abstract class OfLong implements Iterator<Long> {
        public abstract long nextLong();

        public Long next() {
            return Long.valueOf(nextLong());
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    private PrimitiveIterator() {
    }
}
