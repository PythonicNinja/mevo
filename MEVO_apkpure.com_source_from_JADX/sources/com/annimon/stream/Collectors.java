package com.annimon.stream;

import com.annimon.stream.function.BiConsumer;
import com.annimon.stream.function.BinaryOperator;
import com.annimon.stream.function.Consumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Predicate;
import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.ToDoubleFunction;
import com.annimon.stream.function.ToIntFunction;
import com.annimon.stream.function.ToLongFunction;
import com.annimon.stream.function.UnaryOperator.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class Collectors {
    private static final Supplier<double[]> DOUBLE_2ELEMENTS_ARRAY_SUPPLIER = new C06402();
    private static final Supplier<long[]> LONG_2ELEMENTS_ARRAY_SUPPLIER = new C06391();

    private static final class Tuple1<A> {
        /* renamed from: a */
        A f11a;

        Tuple1(A a) {
            this.f11a = a;
        }
    }

    private static final class Tuple2<A> {
        /* renamed from: a */
        A f12a;
        /* renamed from: b */
        A f13b;

        Tuple2(A a, A a2) {
            this.f12a = a;
            this.f13b = a2;
        }
    }

    /* renamed from: com.annimon.stream.Collectors$1 */
    static class C06391 implements Supplier<long[]> {
        C06391() {
        }

        public long[] get() {
            return new long[]{0, 0};
        }
    }

    /* renamed from: com.annimon.stream.Collectors$2 */
    static class C06402 implements Supplier<double[]> {
        C06402() {
        }

        public double[] get() {
            return new double[]{0.0d, 0.0d};
        }
    }

    /* renamed from: com.annimon.stream.Collectors$3 */
    static class C06423 implements BiConsumer<R, T> {
        C06423() {
        }

        public void accept(R r, T t) {
            r.add(t);
        }
    }

    /* renamed from: com.annimon.stream.Collectors$4 */
    static class C06434 implements Supplier<List<T>> {
        C06434() {
        }

        public List<T> get() {
            return new ArrayList();
        }
    }

    /* renamed from: com.annimon.stream.Collectors$5 */
    static class C06445 implements BiConsumer<List<T>, T> {
        C06445() {
        }

        public void accept(List<T> list, T t) {
            list.add(t);
        }
    }

    /* renamed from: com.annimon.stream.Collectors$6 */
    static class C06456 implements Supplier<Set<T>> {
        C06456() {
        }

        public Set<T> get() {
            return new HashSet();
        }
    }

    /* renamed from: com.annimon.stream.Collectors$7 */
    static class C06467 implements BiConsumer<Set<T>, T> {
        C06467() {
        }

        public void accept(Set<T> set, T t) {
            set.add(t);
        }
    }

    /* renamed from: com.annimon.stream.Collectors$9 */
    static class C06489 implements Supplier<StringBuilder> {
        C06489() {
        }

        public StringBuilder get() {
            return new StringBuilder();
        }
    }

    private static final class CollectorsImpl<T, A, R> implements Collector<T, A, R> {
        private final BiConsumer<A, T> accumulator;
        private final Function<A, R> finisher;
        private final Supplier<A> supplier;

        public CollectorsImpl(Supplier<A> supplier, BiConsumer<A, T> biConsumer) {
            this(supplier, biConsumer, null);
        }

        public CollectorsImpl(Supplier<A> supplier, BiConsumer<A, T> biConsumer, Function<A, R> function) {
            this.supplier = supplier;
            this.accumulator = biConsumer;
            this.finisher = function;
        }

        public Supplier<A> supplier() {
            return this.supplier;
        }

        public BiConsumer<A, T> accumulator() {
            return this.accumulator;
        }

        public Function<A, R> finisher() {
            return this.finisher;
        }
    }

    private Collectors() {
    }

    public static <T, R extends Collection<T>> Collector<T, ?, R> toCollection(Supplier<R> supplier) {
        return new CollectorsImpl(supplier, new C06423());
    }

    public static <T> Collector<T, ?, List<T>> toList() {
        return new CollectorsImpl(new C06434(), new C06445());
    }

    public static <T> Collector<T, ?, Set<T>> toSet() {
        return new CollectorsImpl(new C06456(), new C06467());
    }

    public static <T, K> Collector<T, ?, Map<K, T>> toMap(Function<? super T, ? extends K> function) {
        return toMap(function, Util.identity());
    }

    public static <T, K, V> Collector<T, ?, Map<K, V>> toMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        return toMap(function, function2, hashMapSupplier());
    }

    public static <T, K, V, M extends Map<K, V>> Collector<T, ?, M> toMap(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2, Supplier<M> supplier) {
        return new CollectorsImpl(supplier, new BiConsumer<M, T>() {
            public void accept(M m, T t) {
                Object apply = function.apply(t);
                t = function2.apply(t);
                T t2 = m.get(apply);
                if (t2 != null) {
                    t = t2;
                }
                if (t == null) {
                    m.remove(apply);
                } else {
                    m.put(apply, t);
                }
            }
        });
    }

    public static Collector<CharSequence, ?, String> joining() {
        return joining("");
    }

    public static Collector<CharSequence, ?, String> joining(CharSequence charSequence) {
        return joining(charSequence, "", "");
    }

    public static Collector<CharSequence, ?, String> joining(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charSequence2.toString());
        stringBuilder.append(charSequence3.toString());
        return joining(charSequence, charSequence2, charSequence3, stringBuilder.toString());
    }

    public static Collector<CharSequence, ?, String> joining(final CharSequence charSequence, final CharSequence charSequence2, final CharSequence charSequence3, final String str) {
        return new CollectorsImpl(new C06489(), new BiConsumer<StringBuilder, CharSequence>() {
            public void accept(StringBuilder stringBuilder, CharSequence charSequence) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(charSequence);
                } else {
                    stringBuilder.append(charSequence2);
                }
                stringBuilder.append(charSequence);
            }
        }, new Function<StringBuilder, String>() {
            public String apply(StringBuilder stringBuilder) {
                if (stringBuilder.length() == 0) {
                    return str;
                }
                stringBuilder.append(charSequence3);
                return stringBuilder.toString();
            }
        });
    }

    @Deprecated
    public static <T> Collector<T, ?, Double> averaging(final Function<? super T, Double> function) {
        return averagingDouble(new ToDoubleFunction<T>() {
            public double applyAsDouble(T t) {
                return ((Double) function.apply(t)).doubleValue();
            }
        });
    }

    public static <T> Collector<T, ?, Double> averagingInt(final ToIntFunction<? super T> toIntFunction) {
        return averagingHelper(new BiConsumer<long[], T>() {
            public void accept(long[] jArr, T t) {
                jArr[0] = jArr[0] + 1;
                jArr[1] = jArr[1] + ((long) toIntFunction.applyAsInt(t));
            }
        });
    }

    public static <T> Collector<T, ?, Double> averagingLong(final ToLongFunction<? super T> toLongFunction) {
        return averagingHelper(new BiConsumer<long[], T>() {
            public void accept(long[] jArr, T t) {
                jArr[0] = jArr[0] + 1;
                jArr[1] = jArr[1] + toLongFunction.applyAsLong(t);
            }
        });
    }

    private static <T> Collector<T, ?, Double> averagingHelper(BiConsumer<long[], T> biConsumer) {
        return new CollectorsImpl(LONG_2ELEMENTS_ARRAY_SUPPLIER, biConsumer, new Function<long[], Double>() {
            public Double apply(long[] jArr) {
                if (jArr[0] == 0) {
                    return Double.valueOf(0.0d);
                }
                return Double.valueOf(((double) jArr[1]) / ((double) jArr[0]));
            }
        });
    }

    public static <T> Collector<T, ?, Double> averagingDouble(final ToDoubleFunction<? super T> toDoubleFunction) {
        return new CollectorsImpl(DOUBLE_2ELEMENTS_ARRAY_SUPPLIER, new BiConsumer<double[], T>() {
            public void accept(double[] dArr, T t) {
                dArr[0] = dArr[0] + 1.0d;
                dArr[1] = dArr[1] + toDoubleFunction.applyAsDouble(t);
            }
        }, new Function<double[], Double>() {
            public Double apply(double[] dArr) {
                if (dArr[0] == 0.0d) {
                    return Double.valueOf(0.0d);
                }
                return Double.valueOf(dArr[1] / dArr[0]);
            }
        });
    }

    public static <T> Collector<T, ?, Integer> summingInt(final ToIntFunction<? super T> toIntFunction) {
        return new CollectorsImpl(new Supplier<int[]>() {
            public int[] get() {
                return new int[]{0};
            }
        }, new BiConsumer<int[], T>() {
            public void accept(int[] iArr, T t) {
                iArr[0] = iArr[0] + toIntFunction.applyAsInt(t);
            }
        }, new Function<int[], Integer>() {
            public Integer apply(int[] iArr) {
                return Integer.valueOf(iArr[0]);
            }
        });
    }

    public static <T> Collector<T, ?, Long> summingLong(final ToLongFunction<? super T> toLongFunction) {
        return new CollectorsImpl(LONG_2ELEMENTS_ARRAY_SUPPLIER, new BiConsumer<long[], T>() {
            public void accept(long[] jArr, T t) {
                jArr[0] = jArr[0] + toLongFunction.applyAsLong(t);
            }
        }, new Function<long[], Long>() {
            public Long apply(long[] jArr) {
                return Long.valueOf(jArr[0]);
            }
        });
    }

    public static <T> Collector<T, ?, Double> summingDouble(final ToDoubleFunction<? super T> toDoubleFunction) {
        return new CollectorsImpl(DOUBLE_2ELEMENTS_ARRAY_SUPPLIER, new BiConsumer<double[], T>() {
            public void accept(double[] dArr, T t) {
                dArr[0] = dArr[0] + toDoubleFunction.applyAsDouble(t);
            }
        }, new Function<double[], Double>() {
            public Double apply(double[] dArr) {
                return Double.valueOf(dArr[0]);
            }
        });
    }

    public static <T> Collector<T, ?, Long> counting() {
        return summingLong(new ToLongFunction<T>() {
            public long applyAsLong(T t) {
                return 1;
            }
        });
    }

    public static <T> Collector<T, ?, T> reducing(final T t, final BinaryOperator<T> binaryOperator) {
        return new CollectorsImpl(new Supplier<Tuple1<T>>() {
            public Tuple1<T> get() {
                return new Tuple1(t);
            }
        }, new BiConsumer<Tuple1<T>, T>() {
            public void accept(Tuple1<T> tuple1, T t) {
                tuple1.f11a = binaryOperator.apply(tuple1.f11a, t);
            }
        }, new Function<Tuple1<T>, T>() {
            public T apply(Tuple1<T> tuple1) {
                return tuple1.f11a;
            }
        });
    }

    public static <T, R> Collector<T, ?, R> reducing(final R r, final Function<? super T, ? extends R> function, final BinaryOperator<R> binaryOperator) {
        return new CollectorsImpl(new Supplier<Tuple1<R>>() {
            public Tuple1<R> get() {
                return new Tuple1(r);
            }
        }, new BiConsumer<Tuple1<R>, T>() {
            public void accept(Tuple1<R> tuple1, T t) {
                tuple1.f11a = binaryOperator.apply(tuple1.f11a, function.apply(t));
            }
        }, new Function<Tuple1<R>, R>() {
            public R apply(Tuple1<R> tuple1) {
                return tuple1.f11a;
            }
        });
    }

    public static <T, A, R> Collector<T, ?, R> filtering(final Predicate<? super T> predicate, Collector<? super T, A, R> collector) {
        final BiConsumer accumulator = collector.accumulator();
        return new CollectorsImpl(collector.supplier(), new BiConsumer<A, T>() {
            public void accept(A a, T t) {
                if (predicate.test(t)) {
                    accumulator.accept(a, t);
                }
            }
        }, collector.finisher());
    }

    public static <T, U, A, R> Collector<T, ?, R> mapping(final Function<? super T, ? extends U> function, Collector<? super U, A, R> collector) {
        final BiConsumer accumulator = collector.accumulator();
        return new CollectorsImpl(collector.supplier(), new BiConsumer<A, T>() {
            public void accept(A a, T t) {
                accumulator.accept(a, function.apply(t));
            }
        }, collector.finisher());
    }

    public static <T, U, A, R> Collector<T, ?, R> flatMapping(final Function<? super T, ? extends Stream<? extends U>> function, Collector<? super U, A, R> collector) {
        final BiConsumer accumulator = collector.accumulator();
        return new CollectorsImpl(collector.supplier(), new BiConsumer<A, T>() {
            public void accept(final A a, T t) {
                Stream stream = (Stream) function.apply(t);
                if (stream != null) {
                    stream.forEach(new Consumer<U>() {
                        public void accept(U u) {
                            accumulator.accept(a, u);
                        }
                    });
                }
            }
        }, collector.finisher());
    }

    public static <T, A, IR, OR> Collector<T, A, OR> collectingAndThen(Collector<T, A, IR> collector, Function<IR, OR> function) {
        Function finisher = collector.finisher();
        if (finisher == null) {
            finisher = castIdentity();
        }
        return new CollectorsImpl(collector.supplier(), collector.accumulator(), Function.Util.andThen(finisher, function));
    }

    public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> function) {
        return groupingBy(function, toList());
    }

    public static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> function, Collector<? super T, A, D> collector) {
        return groupingBy(function, hashMapSupplier(), collector);
    }

    public static <T, K, D, A, M extends Map<K, D>> Collector<T, ?, M> groupingBy(final Function<? super T, ? extends K> function, Supplier<M> supplier, final Collector<? super T, A, D> collector) {
        final Function finisher = collector.finisher();
        return new CollectorsImpl(supplier, new BiConsumer<Map<K, A>, T>() {
            public void accept(Map<K, A> map, T t) {
                Object requireNonNull = Objects.requireNonNull(function.apply(t), "element cannot be mapped to a null key");
                Object obj = map.get(requireNonNull);
                if (obj == null) {
                    obj = collector.supplier().get();
                    map.put(requireNonNull, obj);
                }
                collector.accumulator().accept(obj, t);
            }
        }, finisher != null ? new Function<Map<K, A>, M>() {
            public M apply(Map<K, A> map) {
                for (Entry entry : map.entrySet()) {
                    entry.setValue(finisher.apply(entry.getValue()));
                }
                return map;
            }
        } : null);
    }

    public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningBy(Predicate<? super T> predicate) {
        return partitioningBy(predicate, toList());
    }

    public static <T, D, A> Collector<T, ?, Map<Boolean, D>> partitioningBy(final Predicate<? super T> predicate, final Collector<? super T, A, D> collector) {
        final BiConsumer accumulator = collector.accumulator();
        return new CollectorsImpl(new Supplier<Tuple2<A>>() {
            public Tuple2<A> get() {
                return new Tuple2(collector.supplier().get(), collector.supplier().get());
            }
        }, new BiConsumer<Tuple2<A>, T>() {
            public void accept(Tuple2<A> tuple2, T t) {
                accumulator.accept(predicate.test(t) ? tuple2.f12a : tuple2.f13b, t);
            }
        }, new Function<Tuple2<A>, Map<Boolean, D>>() {
            public Map<Boolean, D> apply(Tuple2<A> tuple2) {
                Function finisher = collector.finisher();
                if (finisher == null) {
                    finisher = Collectors.castIdentity();
                }
                Map<Boolean, D> hashMap = new HashMap(2);
                hashMap.put(Boolean.TRUE, finisher.apply(tuple2.f12a));
                hashMap.put(Boolean.FALSE, finisher.apply(tuple2.f13b));
                return hashMap;
            }
        });
    }

    private static <K, V> Supplier<Map<K, V>> hashMapSupplier() {
        return new Supplier<Map<K, V>>() {
            public Map<K, V> get() {
                return new HashMap();
            }
        };
    }

    static <A, R> Function<A, R> castIdentity() {
        return new Function<A, R>() {
            public R apply(A a) {
                return a;
            }
        };
    }
}
