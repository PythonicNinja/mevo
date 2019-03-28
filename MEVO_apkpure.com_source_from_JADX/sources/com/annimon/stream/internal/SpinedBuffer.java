package com.annimon.stream.internal;

import com.annimon.stream.function.DoubleConsumer;
import com.annimon.stream.function.IntConsumer;
import com.annimon.stream.function.LongConsumer;
import java.util.Arrays;
import java.util.Iterator;

final class SpinedBuffer {
    private static final int MAX_CHUNK_POWER = 30;
    static final int MIN_CHUNK_POWER = 4;
    static final int MIN_CHUNK_SIZE = 16;
    static final int MIN_SPINE_SIZE = 8;

    static abstract class OfPrimitive<E, T_ARR, T_CONS> implements Iterable<E> {
        T_ARR curChunk;
        int elementIndex;
        final int initialChunkPower;
        long[] priorElementCount;
        T_ARR[] spine;
        int spineIndex;

        protected abstract int arrayLength(T_ARR t_arr);

        public abstract Iterator<E> iterator();

        protected abstract T_ARR newArray(int i);

        protected abstract T_ARR[] newArrayArray(int i);

        OfPrimitive(int i) {
            if (i < 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Illegal Capacity: ");
                stringBuilder.append(i);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
            this.initialChunkPower = Math.max(4, 32 - Integer.numberOfLeadingZeros(i - 1));
            this.curChunk = newArray(1 << this.initialChunkPower);
        }

        OfPrimitive() {
            this.initialChunkPower = 4;
            this.curChunk = newArray(1 << this.initialChunkPower);
        }

        public boolean isEmpty() {
            return this.spineIndex == 0 && this.elementIndex == 0;
        }

        public long count() {
            return this.spineIndex == 0 ? (long) this.elementIndex : this.priorElementCount[this.spineIndex] + ((long) this.elementIndex);
        }

        int chunkSize(int i) {
            if (i != 0) {
                if (i != 1) {
                    i = Math.min((this.initialChunkPower + i) - 1, 30);
                    return 1 << i;
                }
            }
            i = this.initialChunkPower;
            return 1 << i;
        }

        long capacity() {
            if (this.spineIndex == 0) {
                return (long) arrayLength(this.curChunk);
            }
            return this.priorElementCount[this.spineIndex] + ((long) arrayLength(this.spine[this.spineIndex]));
        }

        private void inflateSpine() {
            if (this.spine == null) {
                this.spine = newArrayArray(8);
                this.priorElementCount = new long[8];
                this.spine[0] = this.curChunk;
            }
        }

        final void ensureCapacity(long j) {
            long capacity = capacity();
            if (j > capacity) {
                inflateSpine();
                int i = this.spineIndex + 1;
                while (j > capacity) {
                    int length;
                    if (i >= this.spine.length) {
                        length = this.spine.length * 2;
                        this.spine = Arrays.copyOf(this.spine, length);
                        this.priorElementCount = Arrays.copyOf(this.priorElementCount, length);
                    }
                    length = chunkSize(i);
                    this.spine[i] = newArray(length);
                    int i2 = i - 1;
                    this.priorElementCount[i] = this.priorElementCount[i2] + ((long) arrayLength(this.spine[i2]));
                    i++;
                    capacity += (long) length;
                }
            }
        }

        void increaseCapacity() {
            ensureCapacity(capacity() + 1);
        }

        int chunkFor(long j) {
            int i = 0;
            if (this.spineIndex == 0) {
                if (j < ((long) this.elementIndex)) {
                    return 0;
                }
                throw new IndexOutOfBoundsException(Long.toString(j));
            } else if (j >= count()) {
                throw new IndexOutOfBoundsException(Long.toString(j));
            } else {
                while (i <= this.spineIndex) {
                    if (j < this.priorElementCount[i] + ((long) arrayLength(this.spine[i]))) {
                        return i;
                    }
                    i++;
                }
                throw new IndexOutOfBoundsException(Long.toString(j));
            }
        }

        void copyInto(T_ARR t_arr, int i) {
            long j = (long) i;
            long count = j + count();
            if (count <= ((long) arrayLength(t_arr))) {
                if (count >= j) {
                    if (this.spineIndex == 0) {
                        System.arraycopy(this.curChunk, 0, t_arr, i, this.elementIndex);
                        return;
                    }
                    int i2 = i;
                    for (i = 0; i < this.spineIndex; i++) {
                        System.arraycopy(this.spine[i], 0, t_arr, i2, arrayLength(this.spine[i]));
                        i2 += arrayLength(this.spine[i]);
                    }
                    if (this.elementIndex > 0) {
                        System.arraycopy(this.curChunk, 0, t_arr, i2, this.elementIndex);
                        return;
                    }
                    return;
                }
            }
            throw new IndexOutOfBoundsException("does not fit");
        }

        public T_ARR asPrimitiveArray() {
            long count = count();
            Compat.checkMaxArraySize(count);
            T_ARR newArray = newArray((int) count);
            copyInto(newArray, 0);
            return newArray;
        }

        void preAccept() {
            if (this.elementIndex == arrayLength(this.curChunk)) {
                inflateSpine();
                if (this.spineIndex + 1 >= this.spine.length || this.spine[this.spineIndex + 1] == null) {
                    increaseCapacity();
                }
                this.elementIndex = 0;
                this.spineIndex++;
                this.curChunk = this.spine[this.spineIndex];
            }
        }

        public void clear() {
            if (this.spine != null) {
                this.curChunk = this.spine[0];
                this.spine = null;
                this.priorElementCount = null;
            }
            this.elementIndex = 0;
            this.spineIndex = 0;
        }
    }

    static class OfDouble extends OfPrimitive<Double, double[], DoubleConsumer> implements DoubleConsumer {

        /* renamed from: com.annimon.stream.internal.SpinedBuffer$OfDouble$1 */
        class C07291 extends com.annimon.stream.iterator.PrimitiveIterator.OfDouble {
            long index = 0;

            C07291() {
            }

            public double nextDouble() {
                OfDouble ofDouble = OfDouble.this;
                long j = this.index;
                this.index = j + 1;
                return ofDouble.get(j);
            }

            public boolean hasNext() {
                return this.index < OfDouble.this.count();
            }
        }

        OfDouble() {
        }

        OfDouble(int i) {
            super(i);
        }

        protected double[][] newArrayArray(int i) {
            return new double[i][];
        }

        public double[] newArray(int i) {
            return new double[i];
        }

        protected int arrayLength(double[] dArr) {
            return dArr.length;
        }

        public void accept(double d) {
            preAccept();
            double[] dArr = (double[]) this.curChunk;
            int i = this.elementIndex;
            this.elementIndex = i + 1;
            dArr[i] = d;
        }

        public double get(long j) {
            int chunkFor = chunkFor(j);
            if (this.spineIndex == 0 && chunkFor == 0) {
                return ((double[]) this.curChunk)[(int) j];
            }
            return ((double[][]) this.spine)[chunkFor][(int) (j - this.priorElementCount[chunkFor])];
        }

        public com.annimon.stream.iterator.PrimitiveIterator.OfDouble iterator() {
            return new C07291();
        }
    }

    static class OfInt extends OfPrimitive<Integer, int[], IntConsumer> implements IntConsumer {

        /* renamed from: com.annimon.stream.internal.SpinedBuffer$OfInt$1 */
        class C07301 extends com.annimon.stream.iterator.PrimitiveIterator.OfInt {
            long index = 0;

            C07301() {
            }

            public int nextInt() {
                OfInt ofInt = OfInt.this;
                long j = this.index;
                this.index = j + 1;
                return ofInt.get(j);
            }

            public boolean hasNext() {
                return this.index < OfInt.this.count();
            }
        }

        OfInt() {
        }

        OfInt(int i) {
            super(i);
        }

        protected int[][] newArrayArray(int i) {
            return new int[i][];
        }

        public int[] newArray(int i) {
            return new int[i];
        }

        protected int arrayLength(int[] iArr) {
            return iArr.length;
        }

        public void accept(int i) {
            preAccept();
            int[] iArr = (int[]) this.curChunk;
            int i2 = this.elementIndex;
            this.elementIndex = i2 + 1;
            iArr[i2] = i;
        }

        public int get(long j) {
            int chunkFor = chunkFor(j);
            if (this.spineIndex == 0 && chunkFor == 0) {
                return ((int[]) this.curChunk)[(int) j];
            }
            return ((int[][]) this.spine)[chunkFor][(int) (j - this.priorElementCount[chunkFor])];
        }

        public com.annimon.stream.iterator.PrimitiveIterator.OfInt iterator() {
            return new C07301();
        }
    }

    static class OfLong extends OfPrimitive<Long, long[], LongConsumer> implements LongConsumer {

        /* renamed from: com.annimon.stream.internal.SpinedBuffer$OfLong$1 */
        class C07311 extends com.annimon.stream.iterator.PrimitiveIterator.OfLong {
            long index = 0;

            C07311() {
            }

            public long nextLong() {
                OfLong ofLong = OfLong.this;
                long j = this.index;
                this.index = j + 1;
                return ofLong.get(j);
            }

            public boolean hasNext() {
                return this.index < OfLong.this.count();
            }
        }

        OfLong() {
        }

        OfLong(int i) {
            super(i);
        }

        protected long[][] newArrayArray(int i) {
            return new long[i][];
        }

        public long[] newArray(int i) {
            return new long[i];
        }

        protected int arrayLength(long[] jArr) {
            return jArr.length;
        }

        public void accept(long j) {
            preAccept();
            long[] jArr = (long[]) this.curChunk;
            int i = this.elementIndex;
            this.elementIndex = i + 1;
            jArr[i] = j;
        }

        public long get(long j) {
            int chunkFor = chunkFor(j);
            if (this.spineIndex == 0 && chunkFor == 0) {
                return ((long[]) this.curChunk)[(int) j];
            }
            return ((long[][]) this.spine)[chunkFor][(int) (j - this.priorElementCount[chunkFor])];
        }

        public com.annimon.stream.iterator.PrimitiveIterator.OfLong iterator() {
            return new C07311();
        }
    }

    private SpinedBuffer() {
    }
}
