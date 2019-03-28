package com.annimon.stream;

import com.annimon.stream.function.DoubleSupplier;
import com.annimon.stream.function.IntSupplier;
import com.annimon.stream.function.LongSupplier;
import java.util.Random;

public final class RandomCompat {
    private final Random random;

    /* renamed from: com.annimon.stream.RandomCompat$1 */
    class C06641 implements IntSupplier {
        C06641() {
        }

        public int getAsInt() {
            return RandomCompat.this.random.nextInt();
        }
    }

    /* renamed from: com.annimon.stream.RandomCompat$2 */
    class C06652 implements LongSupplier {
        C06652() {
        }

        public long getAsLong() {
            return RandomCompat.this.random.nextLong();
        }
    }

    /* renamed from: com.annimon.stream.RandomCompat$3 */
    class C06663 implements DoubleSupplier {
        C06663() {
        }

        public double getAsDouble() {
            return RandomCompat.this.random.nextDouble();
        }
    }

    public RandomCompat() {
        this.random = new Random();
    }

    public RandomCompat(long j) {
        this.random = new Random(j);
    }

    public RandomCompat(Random random) {
        this.random = random;
    }

    public Random getRandom() {
        return this.random;
    }

    public IntStream ints(long j) {
        if (j < 0) {
            throw new IllegalArgumentException();
        } else if (j == 0) {
            return IntStream.empty();
        } else {
            return ints().limit(j);
        }
    }

    public LongStream longs(long j) {
        if (j < 0) {
            throw new IllegalArgumentException();
        } else if (j == 0) {
            return LongStream.empty();
        } else {
            return longs().limit(j);
        }
    }

    public DoubleStream doubles(long j) {
        if (j < 0) {
            throw new IllegalArgumentException();
        } else if (j == 0) {
            return DoubleStream.empty();
        } else {
            return doubles().limit(j);
        }
    }

    public IntStream ints() {
        return IntStream.generate(new C06641());
    }

    public LongStream longs() {
        return LongStream.generate(new C06652());
    }

    public DoubleStream doubles() {
        return DoubleStream.generate(new C06663());
    }

    public IntStream ints(long j, int i, int i2) {
        if (j < 0) {
            throw new IllegalArgumentException();
        } else if (j == 0) {
            return IntStream.empty();
        } else {
            return ints(i, i2).limit(j);
        }
    }

    public LongStream longs(long j, long j2, long j3) {
        if (j < 0) {
            throw new IllegalArgumentException();
        } else if (j == 0) {
            return LongStream.empty();
        } else {
            return longs(j2, j3).limit(j);
        }
    }

    public DoubleStream doubles(long j, double d, double d2) {
        if (j < 0) {
            throw new IllegalArgumentException();
        } else if (j == 0) {
            return DoubleStream.empty();
        } else {
            return doubles(d, d2).limit(j);
        }
    }

    public IntStream ints(final int i, final int i2) {
        if (i < i2) {
            return IntStream.generate(new IntSupplier() {
                private final int bound = (i2 - i);

                public int getAsInt() {
                    if (this.bound >= 0) {
                        return i + RandomCompat.this.random.nextInt(this.bound);
                    }
                    while (true) {
                        int nextInt = RandomCompat.this.random.nextInt();
                        if (i < nextInt && nextInt < i2) {
                            return nextInt;
                        }
                    }
                }
            });
        }
        throw new IllegalArgumentException();
    }

    public LongStream longs(long j, long j2) {
        if (j >= j2) {
            throw new IllegalArgumentException();
        }
        final long j3 = j2;
        final long j4 = j;
        return LongStream.generate(new LongSupplier() {
            private final long bound = (j3 - j4);
            private final long boundMinus1 = (this.bound - 1);

            public long getAsLong() {
                long nextLong = RandomCompat.this.random.nextLong();
                if ((this.bound & this.boundMinus1) == 0) {
                    return (nextLong & this.boundMinus1) + j4;
                }
                if (this.bound > 0) {
                    nextLong >>>= 1;
                    while (true) {
                        long j = nextLong + this.boundMinus1;
                        nextLong %= this.bound;
                        if (j - nextLong >= 0) {
                            return nextLong + j4;
                        }
                        nextLong = RandomCompat.this.random.nextLong() >>> 1;
                    }
                } else {
                    long j2 = nextLong;
                    while (true) {
                        if (j4 < j2) {
                            if (j2 < j3) {
                                return j2;
                            }
                        }
                        j2 = RandomCompat.this.random.nextLong();
                    }
                }
            }
        });
    }

    public DoubleStream doubles(double d, double d2) {
        if (d >= d2) {
            throw new IllegalArgumentException();
        }
        final double d3 = d2;
        final double d4 = d;
        return DoubleStream.generate(new DoubleSupplier() {
            private final double bound = (d3 - d4);

            public double getAsDouble() {
                double nextDouble = (RandomCompat.this.random.nextDouble() * this.bound) + d4;
                return nextDouble >= d3 ? Double.longBitsToDouble(Double.doubleToLongBits(d3) - 1) : nextDouble;
            }
        });
    }
}
