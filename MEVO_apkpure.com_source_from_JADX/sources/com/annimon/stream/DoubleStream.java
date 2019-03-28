package com.annimon.stream;

import com.annimon.stream.function.DoubleBinaryOperator;
import com.annimon.stream.function.DoubleConsumer;
import com.annimon.stream.function.DoubleFunction;
import com.annimon.stream.function.DoublePredicate;
import com.annimon.stream.function.DoublePredicate.Util;
import com.annimon.stream.function.DoubleSupplier;
import com.annimon.stream.function.DoubleToIntFunction;
import com.annimon.stream.function.DoubleToLongFunction;
import com.annimon.stream.function.DoubleUnaryOperator;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.ObjDoubleConsumer;
import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.ToDoubleFunction;
import com.annimon.stream.internal.Compose;
import com.annimon.stream.internal.Operators;
import com.annimon.stream.internal.Params;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;
import com.annimon.stream.operator.DoubleArray;
import com.annimon.stream.operator.DoubleConcat;
import com.annimon.stream.operator.DoubleDropWhile;
import com.annimon.stream.operator.DoubleFilter;
import com.annimon.stream.operator.DoubleFlatMap;
import com.annimon.stream.operator.DoubleGenerate;
import com.annimon.stream.operator.DoubleIterate;
import com.annimon.stream.operator.DoubleLimit;
import com.annimon.stream.operator.DoubleMap;
import com.annimon.stream.operator.DoubleMapToInt;
import com.annimon.stream.operator.DoubleMapToLong;
import com.annimon.stream.operator.DoubleMapToObj;
import com.annimon.stream.operator.DoublePeek;
import com.annimon.stream.operator.DoubleSample;
import com.annimon.stream.operator.DoubleScan;
import com.annimon.stream.operator.DoubleScanIdentity;
import com.annimon.stream.operator.DoubleSkip;
import com.annimon.stream.operator.DoubleSorted;
import com.annimon.stream.operator.DoubleTakeUntil;
import com.annimon.stream.operator.DoubleTakeWhile;
import java.io.Closeable;
import java.util.Comparator;
import java.util.NoSuchElementException;

public final class DoubleStream implements Closeable {
    private static final DoubleStream EMPTY = new DoubleStream(new C06491());
    private static final ToDoubleFunction<Double> UNBOX_FUNCTION = new C06535();
    private final OfDouble iterator;
    private final Params params;

    /* renamed from: com.annimon.stream.DoubleStream$1 */
    static class C06491 extends OfDouble {
        public boolean hasNext() {
            return false;
        }

        public double nextDouble() {
            return 0.0d;
        }

        C06491() {
        }
    }

    /* renamed from: com.annimon.stream.DoubleStream$2 */
    class C06502 implements DoubleBinaryOperator {
        C06502() {
        }

        public double applyAsDouble(double d, double d2) {
            return Math.min(d, d2);
        }
    }

    /* renamed from: com.annimon.stream.DoubleStream$3 */
    class C06513 implements DoubleBinaryOperator {
        C06513() {
        }

        public double applyAsDouble(double d, double d2) {
            return Math.max(d, d2);
        }
    }

    /* renamed from: com.annimon.stream.DoubleStream$4 */
    class C06524 implements DoubleBinaryOperator {
        public double applyAsDouble(double d, double d2) {
            return d2;
        }

        C06524() {
        }
    }

    /* renamed from: com.annimon.stream.DoubleStream$5 */
    static class C06535 implements ToDoubleFunction<Double> {
        C06535() {
        }

        public double applyAsDouble(Double d) {
            return d.doubleValue();
        }
    }

    public static DoubleStream empty() {
        return EMPTY;
    }

    public static DoubleStream of(OfDouble ofDouble) {
        Objects.requireNonNull(ofDouble);
        return new DoubleStream(ofDouble);
    }

    public static DoubleStream of(double... dArr) {
        Objects.requireNonNull(dArr);
        if (dArr.length == 0) {
            return empty();
        }
        return new DoubleStream(new DoubleArray(dArr));
    }

    public static DoubleStream of(double d) {
        return new DoubleStream(new DoubleArray(new double[]{d}));
    }

    public static DoubleStream generate(DoubleSupplier doubleSupplier) {
        Objects.requireNonNull(doubleSupplier);
        return new DoubleStream(new DoubleGenerate(doubleSupplier));
    }

    public static DoubleStream iterate(double d, DoubleUnaryOperator doubleUnaryOperator) {
        Objects.requireNonNull(doubleUnaryOperator);
        return new DoubleStream(new DoubleIterate(d, doubleUnaryOperator));
    }

    public static DoubleStream iterate(double d, DoublePredicate doublePredicate, DoubleUnaryOperator doubleUnaryOperator) {
        Objects.requireNonNull(doublePredicate);
        return iterate(d, doubleUnaryOperator).takeWhile(doublePredicate);
    }

    public static DoubleStream concat(DoubleStream doubleStream, DoubleStream doubleStream2) {
        Objects.requireNonNull(doubleStream);
        Objects.requireNonNull(doubleStream2);
        return new DoubleStream(new DoubleConcat(doubleStream.iterator, doubleStream2.iterator)).onClose(Compose.closeables(doubleStream, doubleStream2));
    }

    private DoubleStream(OfDouble ofDouble) {
        this(null, ofDouble);
    }

    DoubleStream(Params params, OfDouble ofDouble) {
        this.params = params;
        this.iterator = ofDouble;
    }

    public OfDouble iterator() {
        return this.iterator;
    }

    public <R> R custom(Function<DoubleStream, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public Stream<Double> boxed() {
        return new Stream(this.params, this.iterator);
    }

    public DoubleStream filter(DoublePredicate doublePredicate) {
        return new DoubleStream(this.params, new DoubleFilter(this.iterator, doublePredicate));
    }

    public DoubleStream filterNot(DoublePredicate doublePredicate) {
        return filter(Util.negate(doublePredicate));
    }

    public DoubleStream map(DoubleUnaryOperator doubleUnaryOperator) {
        return new DoubleStream(this.params, new DoubleMap(this.iterator, doubleUnaryOperator));
    }

    public <R> Stream<R> mapToObj(DoubleFunction<? extends R> doubleFunction) {
        return new Stream(this.params, new DoubleMapToObj(this.iterator, doubleFunction));
    }

    public IntStream mapToInt(DoubleToIntFunction doubleToIntFunction) {
        return new IntStream(this.params, new DoubleMapToInt(this.iterator, doubleToIntFunction));
    }

    public LongStream mapToLong(DoubleToLongFunction doubleToLongFunction) {
        return new LongStream(this.params, new DoubleMapToLong(this.iterator, doubleToLongFunction));
    }

    public DoubleStream flatMap(DoubleFunction<? extends DoubleStream> doubleFunction) {
        return new DoubleStream(this.params, new DoubleFlatMap(this.iterator, doubleFunction));
    }

    public DoubleStream distinct() {
        return boxed().distinct().mapToDouble(UNBOX_FUNCTION);
    }

    public DoubleStream sorted() {
        return new DoubleStream(this.params, new DoubleSorted(this.iterator));
    }

    public DoubleStream sorted(Comparator<Double> comparator) {
        return boxed().sorted(comparator).mapToDouble(UNBOX_FUNCTION);
    }

    public DoubleStream sample(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("stepWidth cannot be zero or negative");
        } else if (i == 1) {
            return this;
        } else {
            return new DoubleStream(this.params, new DoubleSample(this.iterator, i));
        }
    }

    public DoubleStream peek(DoubleConsumer doubleConsumer) {
        return new DoubleStream(this.params, new DoublePeek(this.iterator, doubleConsumer));
    }

    public DoubleStream scan(DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return new DoubleStream(this.params, new DoubleScan(this.iterator, doubleBinaryOperator));
    }

    public DoubleStream scan(double d, DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return new DoubleStream(this.params, new DoubleScanIdentity(this.iterator, d, doubleBinaryOperator));
    }

    public DoubleStream takeWhile(DoublePredicate doublePredicate) {
        return new DoubleStream(this.params, new DoubleTakeWhile(this.iterator, doublePredicate));
    }

    public DoubleStream takeUntil(DoublePredicate doublePredicate) {
        return new DoubleStream(this.params, new DoubleTakeUntil(this.iterator, doublePredicate));
    }

    public DoubleStream dropWhile(DoublePredicate doublePredicate) {
        return new DoubleStream(this.params, new DoubleDropWhile(this.iterator, doublePredicate));
    }

    public DoubleStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("maxSize cannot be negative");
        } else if (j == 0) {
            return empty();
        } else {
            return new DoubleStream(this.params, new DoubleLimit(this.iterator, j));
        }
    }

    public DoubleStream skip(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n cannot be negative");
        } else if (j == 0) {
            return this;
        } else {
            return new DoubleStream(this.params, new DoubleSkip(this.iterator, j));
        }
    }

    public void forEach(DoubleConsumer doubleConsumer) {
        while (this.iterator.hasNext()) {
            doubleConsumer.accept(this.iterator.nextDouble());
        }
    }

    public double reduce(double d, DoubleBinaryOperator doubleBinaryOperator) {
        while (this.iterator.hasNext()) {
            d = doubleBinaryOperator.applyAsDouble(d, this.iterator.nextDouble());
        }
        return d;
    }

    public OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator) {
        Object obj = null;
        double d = 0.0d;
        while (this.iterator.hasNext()) {
            double nextDouble = this.iterator.nextDouble();
            if (obj == null) {
                obj = 1;
                d = nextDouble;
            } else {
                d = doubleBinaryOperator.applyAsDouble(d, nextDouble);
            }
        }
        return obj != null ? OptionalDouble.of(d) : OptionalDouble.empty();
    }

    public double[] toArray() {
        return Operators.toDoubleArray(this.iterator);
    }

    public <R> R collect(Supplier<R> supplier, ObjDoubleConsumer<R> objDoubleConsumer) {
        supplier = supplier.get();
        while (this.iterator.hasNext()) {
            objDoubleConsumer.accept(supplier, this.iterator.nextDouble());
        }
        return supplier;
    }

    public double sum() {
        double d = 0.0d;
        while (this.iterator.hasNext()) {
            d += this.iterator.nextDouble();
        }
        return d;
    }

    public OptionalDouble min() {
        return reduce(new C06502());
    }

    public OptionalDouble max() {
        return reduce(new C06513());
    }

    public long count() {
        long j = 0;
        while (this.iterator.hasNext()) {
            this.iterator.nextDouble();
            j++;
        }
        return j;
    }

    public OptionalDouble average() {
        double d = 0.0d;
        long j = 0;
        while (this.iterator.hasNext()) {
            d += this.iterator.nextDouble();
            j++;
        }
        if (j == 0) {
            return OptionalDouble.empty();
        }
        return OptionalDouble.of(d / ((double) j));
    }

    public boolean anyMatch(DoublePredicate doublePredicate) {
        while (this.iterator.hasNext()) {
            if (doublePredicate.test(this.iterator.nextDouble())) {
                return true;
            }
        }
        return null;
    }

    public boolean allMatch(DoublePredicate doublePredicate) {
        while (this.iterator.hasNext()) {
            if (!doublePredicate.test(this.iterator.nextDouble())) {
                return null;
            }
        }
        return true;
    }

    public boolean noneMatch(DoublePredicate doublePredicate) {
        while (this.iterator.hasNext()) {
            if (doublePredicate.test(this.iterator.nextDouble())) {
                return null;
            }
        }
        return true;
    }

    public OptionalDouble findFirst() {
        if (this.iterator.hasNext()) {
            return OptionalDouble.of(this.iterator.nextDouble());
        }
        return OptionalDouble.empty();
    }

    public OptionalDouble findLast() {
        return reduce(new C06524());
    }

    public double single() {
        if (this.iterator.hasNext()) {
            double nextDouble = this.iterator.nextDouble();
            if (!this.iterator.hasNext()) {
                return nextDouble;
            }
            throw new IllegalStateException("DoubleStream contains more than one element");
        }
        throw new NoSuchElementException("DoubleStream contains no element");
    }

    public OptionalDouble findSingle() {
        if (!this.iterator.hasNext()) {
            return OptionalDouble.empty();
        }
        double nextDouble = this.iterator.nextDouble();
        if (!this.iterator.hasNext()) {
            return OptionalDouble.of(nextDouble);
        }
        throw new IllegalStateException("DoubleStream contains more than one element");
    }

    public DoubleStream onClose(Runnable runnable) {
        Params params;
        Objects.requireNonNull(runnable);
        if (this.params == null) {
            params = new Params();
            params.closeHandler = runnable;
        } else {
            params = this.params;
            params.closeHandler = Compose.runnables(params.closeHandler, runnable);
        }
        return new DoubleStream(params, this.iterator);
    }

    public void close() {
        if (this.params != null && this.params.closeHandler != null) {
            this.params.closeHandler.run();
            this.params.closeHandler = null;
        }
    }
}
