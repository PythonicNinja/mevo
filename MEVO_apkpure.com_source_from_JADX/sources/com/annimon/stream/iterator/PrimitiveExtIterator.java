package com.annimon.stream.iterator;

import java.util.NoSuchElementException;

public final class PrimitiveExtIterator {

    public static abstract class OfDouble extends com.annimon.stream.iterator.PrimitiveIterator.OfDouble {
        protected boolean hasNext;
        protected boolean isInit;
        protected double next;

        protected abstract void nextIteration();

        public boolean hasNext() {
            if (!this.isInit) {
                nextIteration();
                this.isInit = true;
            }
            return this.hasNext;
        }

        public double nextDouble() {
            if (!this.isInit) {
                hasNext();
            }
            if (this.hasNext) {
                double d = this.next;
                nextIteration();
                return d;
            }
            throw new NoSuchElementException();
        }
    }

    public static abstract class OfInt extends com.annimon.stream.iterator.PrimitiveIterator.OfInt {
        protected boolean hasNext;
        protected boolean isInit;
        protected int next;

        protected abstract void nextIteration();

        public boolean hasNext() {
            if (!this.isInit) {
                nextIteration();
                this.isInit = true;
            }
            return this.hasNext;
        }

        public int nextInt() {
            if (!this.isInit) {
                hasNext();
            }
            if (this.hasNext) {
                int i = this.next;
                nextIteration();
                return i;
            }
            throw new NoSuchElementException();
        }
    }

    public static abstract class OfLong extends com.annimon.stream.iterator.PrimitiveIterator.OfLong {
        protected boolean hasNext;
        protected boolean isInit;
        protected long next;

        protected abstract void nextIteration();

        public boolean hasNext() {
            if (!this.isInit) {
                nextIteration();
                this.isInit = true;
            }
            return this.hasNext;
        }

        public long nextLong() {
            if (!this.isInit) {
                hasNext();
            }
            if (this.hasNext) {
                long j = this.next;
                nextIteration();
                return j;
            }
            throw new NoSuchElementException();
        }
    }

    private PrimitiveExtIterator() {
    }
}
