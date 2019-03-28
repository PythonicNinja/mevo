package com.annimon.stream;

import com.annimon.stream.function.Function;
import com.annimon.stream.function.ToDoubleFunction;
import com.annimon.stream.function.ToIntFunction;
import com.annimon.stream.function.ToLongFunction;
import java.util.Collections;
import java.util.Comparator;

public final class ComparatorCompat<T> implements Comparator<T> {
    private static final ComparatorCompat<Comparable<Object>> NATURAL_ORDER = new ComparatorCompat(new C03261());
    private static final ComparatorCompat<Comparable<Object>> REVERSE_ORDER = new ComparatorCompat(Collections.reverseOrder());
    private final Comparator<? super T> comparator;

    /* renamed from: com.annimon.stream.ComparatorCompat$1 */
    static class C03261 implements Comparator<Comparable<Object>> {
        C03261() {
        }

        public int compare(Comparable<Object> comparable, Comparable<Object> comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    public static <T extends Comparable<? super T>> ComparatorCompat<T> naturalOrder() {
        return NATURAL_ORDER;
    }

    public static <T extends Comparable<? super T>> ComparatorCompat<T> reverseOrder() {
        return REVERSE_ORDER;
    }

    public static <T> Comparator<T> reversed(Comparator<T> comparator) {
        return Collections.reverseOrder(comparator);
    }

    public static <T> Comparator<T> thenComparing(final Comparator<? super T> comparator, final Comparator<? super T> comparator2) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(comparator2);
        return new Comparator<T>() {
            public int compare(T t, T t2) {
                int compare = comparator.compare(t, t2);
                return compare != 0 ? compare : comparator2.compare(t, t2);
            }
        };
    }

    public static <T, U> ComparatorCompat<T> comparing(final Function<? super T, ? extends U> function, final Comparator<? super U> comparator) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(comparator);
        return new ComparatorCompat(new Comparator<T>() {
            public int compare(T t, T t2) {
                return comparator.compare(function.apply(t), function.apply(t2));
            }
        });
    }

    public static <T, U extends Comparable<? super U>> ComparatorCompat<T> comparing(final Function<? super T, ? extends U> function) {
        Objects.requireNonNull(function);
        return new ComparatorCompat(new Comparator<T>() {
            public int compare(T t, T t2) {
                return ((Comparable) function.apply(t)).compareTo((Comparable) function.apply(t2));
            }
        });
    }

    public static <T> ComparatorCompat<T> comparingInt(final ToIntFunction<? super T> toIntFunction) {
        Objects.requireNonNull(toIntFunction);
        return new ComparatorCompat(new Comparator<T>() {
            public int compare(T t, T t2) {
                return Objects.compareInt(toIntFunction.applyAsInt(t), toIntFunction.applyAsInt(t2));
            }
        });
    }

    public static <T> ComparatorCompat<T> comparingLong(final ToLongFunction<? super T> toLongFunction) {
        Objects.requireNonNull(toLongFunction);
        return new ComparatorCompat(new Comparator<T>() {
            public int compare(T t, T t2) {
                return Objects.compareLong(toLongFunction.applyAsLong(t), toLongFunction.applyAsLong(t2));
            }
        });
    }

    public static <T> ComparatorCompat<T> comparingDouble(final ToDoubleFunction<? super T> toDoubleFunction) {
        Objects.requireNonNull(toDoubleFunction);
        return new ComparatorCompat(new Comparator<T>() {
            public int compare(T t, T t2) {
                return Double.compare(toDoubleFunction.applyAsDouble(t), toDoubleFunction.applyAsDouble(t2));
            }
        });
    }

    public static <T> ComparatorCompat<T> nullsFirst() {
        return nullsComparator(true, null);
    }

    public static <T> ComparatorCompat<T> nullsFirst(Comparator<? super T> comparator) {
        return nullsComparator(true, comparator);
    }

    public static <T> ComparatorCompat<T> nullsLast() {
        return nullsComparator(false, null);
    }

    public static <T> ComparatorCompat<T> nullsLast(Comparator<? super T> comparator) {
        return nullsComparator(false, comparator);
    }

    private static <T> ComparatorCompat<T> nullsComparator(final boolean z, final Comparator<? super T> comparator) {
        return new ComparatorCompat(new Comparator<T>() {
            public int compare(T t, T t2) {
                int i = 1;
                int i2 = 0;
                if (t == null) {
                    if (t2 == null) {
                        i = 0;
                    } else if (z != null) {
                        i = -1;
                    }
                    return i;
                } else if (t2 == null) {
                    if (z == null) {
                        i = -1;
                    }
                    return i;
                } else {
                    if (comparator != null) {
                        i2 = comparator.compare(t, t2);
                    }
                    return i2;
                }
            }
        });
    }

    public static <T> ComparatorCompat<T> chain(Comparator<T> comparator) {
        return new ComparatorCompat(comparator);
    }

    public ComparatorCompat(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public ComparatorCompat<T> reversed() {
        return new ComparatorCompat(Collections.reverseOrder(this.comparator));
    }

    public ComparatorCompat<T> thenComparing(final Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return new ComparatorCompat(new Comparator<T>() {
            public int compare(T t, T t2) {
                int compare = ComparatorCompat.this.comparator.compare(t, t2);
                return compare != 0 ? compare : comparator.compare(t, t2);
            }
        });
    }

    public <U> ComparatorCompat<T> thenComparing(Function<? super T, ? extends U> function, Comparator<? super U> comparator) {
        return thenComparing(comparing(function, comparator));
    }

    public <U extends Comparable<? super U>> ComparatorCompat<T> thenComparing(Function<? super T, ? extends U> function) {
        return thenComparing(comparing(function));
    }

    public ComparatorCompat<T> thenComparingInt(ToIntFunction<? super T> toIntFunction) {
        return thenComparing(comparingInt(toIntFunction));
    }

    public ComparatorCompat<T> thenComparingLong(ToLongFunction<? super T> toLongFunction) {
        return thenComparing(comparingLong(toLongFunction));
    }

    public ComparatorCompat<T> thenComparingDouble(ToDoubleFunction<? super T> toDoubleFunction) {
        return thenComparing(comparingDouble(toDoubleFunction));
    }

    public Comparator<T> comparator() {
        return this.comparator;
    }

    public int compare(T t, T t2) {
        return this.comparator.compare(t, t2);
    }
}
