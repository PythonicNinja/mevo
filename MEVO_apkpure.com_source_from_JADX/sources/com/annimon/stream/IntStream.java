package com.annimon.stream;

import com.annimon.stream.function.Function;
import com.annimon.stream.function.IntBinaryOperator;
import com.annimon.stream.function.IntConsumer;
import com.annimon.stream.function.IntFunction;
import com.annimon.stream.function.IntPredicate;
import com.annimon.stream.function.IntPredicate.Util;
import com.annimon.stream.function.IntSupplier;
import com.annimon.stream.function.IntToDoubleFunction;
import com.annimon.stream.function.IntToLongFunction;
import com.annimon.stream.function.IntUnaryOperator;
import com.annimon.stream.function.ObjIntConsumer;
import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.ToIntFunction;
import com.annimon.stream.internal.Compose;
import com.annimon.stream.internal.Operators;
import com.annimon.stream.internal.Params;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;
import com.annimon.stream.operator.IntArray;
import com.annimon.stream.operator.IntCodePoints;
import com.annimon.stream.operator.IntConcat;
import com.annimon.stream.operator.IntDropWhile;
import com.annimon.stream.operator.IntFilter;
import com.annimon.stream.operator.IntFlatMap;
import com.annimon.stream.operator.IntGenerate;
import com.annimon.stream.operator.IntIterate;
import com.annimon.stream.operator.IntLimit;
import com.annimon.stream.operator.IntMap;
import com.annimon.stream.operator.IntMapToDouble;
import com.annimon.stream.operator.IntMapToLong;
import com.annimon.stream.operator.IntMapToObj;
import com.annimon.stream.operator.IntPeek;
import com.annimon.stream.operator.IntRangeClosed;
import com.annimon.stream.operator.IntSample;
import com.annimon.stream.operator.IntScan;
import com.annimon.stream.operator.IntScanIdentity;
import com.annimon.stream.operator.IntSkip;
import com.annimon.stream.operator.IntSorted;
import com.annimon.stream.operator.IntTakeUntil;
import com.annimon.stream.operator.IntTakeWhile;
import java.io.Closeable;
import java.util.Comparator;
import java.util.NoSuchElementException;

public final class IntStream implements Closeable {
    private static final IntStream EMPTY = new IntStream(new C06541());
    private static final ToIntFunction<Integer> UNBOX_FUNCTION = new C06585();
    private final OfInt iterator;
    private final Params params;

    /* renamed from: com.annimon.stream.IntStream$1 */
    static class C06541 extends OfInt {
        public boolean hasNext() {
            return false;
        }

        public int nextInt() {
            return 0;
        }

        C06541() {
        }
    }

    /* renamed from: com.annimon.stream.IntStream$2 */
    class C06552 implements IntBinaryOperator {
        public int applyAsInt(int i, int i2) {
            return i < i2 ? i : i2;
        }

        C06552() {
        }
    }

    /* renamed from: com.annimon.stream.IntStream$3 */
    class C06563 implements IntBinaryOperator {
        public int applyAsInt(int i, int i2) {
            return i > i2 ? i : i2;
        }

        C06563() {
        }
    }

    /* renamed from: com.annimon.stream.IntStream$4 */
    class C06574 implements IntBinaryOperator {
        public int applyAsInt(int i, int i2) {
            return i2;
        }

        C06574() {
        }
    }

    /* renamed from: com.annimon.stream.IntStream$5 */
    static class C06585 implements ToIntFunction<Integer> {
        C06585() {
        }

        public int applyAsInt(Integer num) {
            return num.intValue();
        }
    }

    public static IntStream empty() {
        return EMPTY;
    }

    public static IntStream of(OfInt ofInt) {
        Objects.requireNonNull(ofInt);
        return new IntStream(ofInt);
    }

    public static IntStream of(int... iArr) {
        Objects.requireNonNull(iArr);
        if (iArr.length == 0) {
            return empty();
        }
        return new IntStream(new IntArray(iArr));
    }

    public static IntStream of(int i) {
        return new IntStream(new IntArray(new int[]{i}));
    }

    public static IntStream ofCodePoints(CharSequence charSequence) {
        return new IntStream(new IntCodePoints(charSequence));
    }

    public static IntStream range(int i, int i2) {
        if (i >= i2) {
            return empty();
        }
        return rangeClosed(i, i2 - 1);
    }

    public static IntStream rangeClosed(int i, int i2) {
        if (i > i2) {
            return empty();
        }
        if (i == i2) {
            return of(i);
        }
        return new IntStream(new IntRangeClosed(i, i2));
    }

    public static IntStream generate(IntSupplier intSupplier) {
        Objects.requireNonNull(intSupplier);
        return new IntStream(new IntGenerate(intSupplier));
    }

    public static IntStream iterate(int i, IntUnaryOperator intUnaryOperator) {
        Objects.requireNonNull(intUnaryOperator);
        return new IntStream(new IntIterate(i, intUnaryOperator));
    }

    public static IntStream iterate(int i, IntPredicate intPredicate, IntUnaryOperator intUnaryOperator) {
        Objects.requireNonNull(intPredicate);
        return iterate(i, intUnaryOperator).takeWhile(intPredicate);
    }

    public static IntStream concat(IntStream intStream, IntStream intStream2) {
        Objects.requireNonNull(intStream);
        Objects.requireNonNull(intStream2);
        return new IntStream(new IntConcat(intStream.iterator, intStream2.iterator)).onClose(Compose.closeables(intStream, intStream2));
    }

    private IntStream(OfInt ofInt) {
        this(null, ofInt);
    }

    IntStream(Params params, OfInt ofInt) {
        this.params = params;
        this.iterator = ofInt;
    }

    public OfInt iterator() {
        return this.iterator;
    }

    public <R> R custom(Function<IntStream, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public Stream<Integer> boxed() {
        return new Stream(this.params, this.iterator);
    }

    public IntStream filter(IntPredicate intPredicate) {
        return new IntStream(this.params, new IntFilter(this.iterator, intPredicate));
    }

    public IntStream filterNot(IntPredicate intPredicate) {
        return filter(Util.negate(intPredicate));
    }

    public IntStream map(IntUnaryOperator intUnaryOperator) {
        return new IntStream(this.params, new IntMap(this.iterator, intUnaryOperator));
    }

    public <R> Stream<R> mapToObj(IntFunction<? extends R> intFunction) {
        return new Stream(this.params, new IntMapToObj(this.iterator, intFunction));
    }

    public LongStream mapToLong(IntToLongFunction intToLongFunction) {
        return new LongStream(this.params, new IntMapToLong(this.iterator, intToLongFunction));
    }

    public DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction) {
        return new DoubleStream(this.params, new IntMapToDouble(this.iterator, intToDoubleFunction));
    }

    public IntStream flatMap(IntFunction<? extends IntStream> intFunction) {
        return new IntStream(this.params, new IntFlatMap(this.iterator, intFunction));
    }

    public IntStream distinct() {
        return boxed().distinct().mapToInt(UNBOX_FUNCTION);
    }

    public IntStream sorted() {
        return new IntStream(this.params, new IntSorted(this.iterator));
    }

    public IntStream sorted(Comparator<Integer> comparator) {
        return boxed().sorted(comparator).mapToInt(UNBOX_FUNCTION);
    }

    public IntStream sample(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("stepWidth cannot be zero or negative");
        } else if (i == 1) {
            return this;
        } else {
            return new IntStream(this.params, new IntSample(this.iterator, i));
        }
    }

    public IntStream peek(IntConsumer intConsumer) {
        return new IntStream(this.params, new IntPeek(this.iterator, intConsumer));
    }

    public IntStream scan(IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return new IntStream(this.params, new IntScan(this.iterator, intBinaryOperator));
    }

    public IntStream scan(int i, IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return new IntStream(this.params, new IntScanIdentity(this.iterator, i, intBinaryOperator));
    }

    public IntStream takeWhile(IntPredicate intPredicate) {
        return new IntStream(this.params, new IntTakeWhile(this.iterator, intPredicate));
    }

    public IntStream takeUntil(IntPredicate intPredicate) {
        return new IntStream(this.params, new IntTakeUntil(this.iterator, intPredicate));
    }

    public IntStream dropWhile(IntPredicate intPredicate) {
        return new IntStream(this.params, new IntDropWhile(this.iterator, intPredicate));
    }

    public IntStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("maxSize cannot be negative");
        } else if (j == 0) {
            return empty();
        } else {
            return new IntStream(this.params, new IntLimit(this.iterator, j));
        }
    }

    public IntStream skip(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n cannot be negative");
        } else if (j == 0) {
            return this;
        } else {
            return new IntStream(this.params, new IntSkip(this.iterator, j));
        }
    }

    public void forEach(IntConsumer intConsumer) {
        while (this.iterator.hasNext()) {
            intConsumer.accept(this.iterator.nextInt());
        }
    }

    public int reduce(int i, IntBinaryOperator intBinaryOperator) {
        while (this.iterator.hasNext()) {
            i = intBinaryOperator.applyAsInt(i, this.iterator.nextInt());
        }
        return i;
    }

    public OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
        Object obj = null;
        int i = 0;
        while (this.iterator.hasNext()) {
            int nextInt = this.iterator.nextInt();
            if (obj == null) {
                obj = 1;
                i = nextInt;
            } else {
                i = intBinaryOperator.applyAsInt(i, nextInt);
            }
        }
        return obj != null ? OptionalInt.of(i) : OptionalInt.empty();
    }

    public int[] toArray() {
        return Operators.toIntArray(this.iterator);
    }

    public <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> objIntConsumer) {
        supplier = supplier.get();
        while (this.iterator.hasNext()) {
            objIntConsumer.accept(supplier, this.iterator.nextInt());
        }
        return supplier;
    }

    public int sum() {
        int i = 0;
        while (this.iterator.hasNext()) {
            i += this.iterator.nextInt();
        }
        return i;
    }

    public OptionalInt min() {
        return reduce(new C06552());
    }

    public OptionalInt max() {
        return reduce(new C06563());
    }

    public long count() {
        long j = 0;
        while (this.iterator.hasNext()) {
            this.iterator.nextInt();
            j++;
        }
        return j;
    }

    public boolean anyMatch(IntPredicate intPredicate) {
        while (this.iterator.hasNext()) {
            if (intPredicate.test(this.iterator.nextInt())) {
                return true;
            }
        }
        return null;
    }

    public boolean allMatch(IntPredicate intPredicate) {
        while (this.iterator.hasNext()) {
            if (!intPredicate.test(this.iterator.nextInt())) {
                return null;
            }
        }
        return true;
    }

    public boolean noneMatch(IntPredicate intPredicate) {
        while (this.iterator.hasNext()) {
            if (intPredicate.test(this.iterator.nextInt())) {
                return null;
            }
        }
        return true;
    }

    public OptionalInt findFirst() {
        if (this.iterator.hasNext()) {
            return OptionalInt.of(this.iterator.nextInt());
        }
        return OptionalInt.empty();
    }

    public OptionalInt findLast() {
        return reduce(new C06574());
    }

    public int single() {
        if (this.iterator.hasNext()) {
            int nextInt = this.iterator.nextInt();
            if (!this.iterator.hasNext()) {
                return nextInt;
            }
            throw new IllegalStateException("IntStream contains more than one element");
        }
        throw new NoSuchElementException("IntStream contains no element");
    }

    public OptionalInt findSingle() {
        if (!this.iterator.hasNext()) {
            return OptionalInt.empty();
        }
        int nextInt = this.iterator.nextInt();
        if (!this.iterator.hasNext()) {
            return OptionalInt.of(nextInt);
        }
        throw new IllegalStateException("IntStream contains more than one element");
    }

    public IntStream onClose(Runnable runnable) {
        Params params;
        Objects.requireNonNull(runnable);
        if (this.params == null) {
            params = new Params();
            params.closeHandler = runnable;
        } else {
            params = this.params;
            params.closeHandler = Compose.runnables(params.closeHandler, runnable);
        }
        return new IntStream(params, this.iterator);
    }

    public void close() {
        if (this.params != null && this.params.closeHandler != null) {
            this.params.closeHandler.run();
            this.params.closeHandler = null;
        }
    }
}
