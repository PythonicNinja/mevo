package com.annimon.stream;

import com.annimon.stream.function.Function;
import com.annimon.stream.function.IntConsumer;
import com.annimon.stream.function.IntFunction;
import com.annimon.stream.function.IntPredicate;
import com.annimon.stream.function.IntPredicate.Util;
import com.annimon.stream.function.IntSupplier;
import com.annimon.stream.function.IntToDoubleFunction;
import com.annimon.stream.function.IntToLongFunction;
import com.annimon.stream.function.IntUnaryOperator;
import com.annimon.stream.function.Supplier;
import java.util.NoSuchElementException;

public final class OptionalInt {
    private static final OptionalInt EMPTY = new OptionalInt();
    private final boolean isPresent;
    private final int value;

    private OptionalInt() {
        this.isPresent = false;
        this.value = 0;
    }

    public static OptionalInt empty() {
        return EMPTY;
    }

    private OptionalInt(int i) {
        this.isPresent = true;
        this.value = i;
    }

    public static OptionalInt of(int i) {
        return new OptionalInt(i);
    }

    public int getAsInt() {
        if (this.isPresent) {
            return this.value;
        }
        throw new NoSuchElementException("No value present");
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public void ifPresent(IntConsumer intConsumer) {
        if (this.isPresent) {
            intConsumer.accept(this.value);
        }
    }

    public void ifPresentOrElse(IntConsumer intConsumer, Runnable runnable) {
        if (this.isPresent) {
            intConsumer.accept(this.value);
        } else {
            runnable.run();
        }
    }

    public OptionalInt executeIfPresent(IntConsumer intConsumer) {
        ifPresent(intConsumer);
        return this;
    }

    public OptionalInt executeIfAbsent(Runnable runnable) {
        if (!isPresent()) {
            runnable.run();
        }
        return this;
    }

    public <R> R custom(Function<OptionalInt, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public OptionalInt filter(IntPredicate intPredicate) {
        if (!isPresent()) {
            return this;
        }
        return intPredicate.test(this.value) != null ? this : empty();
    }

    public OptionalInt filterNot(IntPredicate intPredicate) {
        return filter(Util.negate(intPredicate));
    }

    public OptionalInt map(IntUnaryOperator intUnaryOperator) {
        if (isPresent()) {
            return of(intUnaryOperator.applyAsInt(this.value));
        }
        return empty();
    }

    public <U> Optional<U> mapToObj(IntFunction<U> intFunction) {
        if (isPresent()) {
            return Optional.ofNullable(intFunction.apply(this.value));
        }
        return Optional.empty();
    }

    public OptionalLong mapToLong(IntToLongFunction intToLongFunction) {
        if (isPresent()) {
            return OptionalLong.of(intToLongFunction.applyAsLong(this.value));
        }
        return OptionalLong.empty();
    }

    public OptionalDouble mapToDouble(IntToDoubleFunction intToDoubleFunction) {
        if (isPresent()) {
            return OptionalDouble.of(intToDoubleFunction.applyAsDouble(this.value));
        }
        return OptionalDouble.empty();
    }

    public IntStream stream() {
        if (isPresent()) {
            return IntStream.of(this.value);
        }
        return IntStream.empty();
    }

    public OptionalInt or(Supplier<OptionalInt> supplier) {
        if (isPresent()) {
            return this;
        }
        Objects.requireNonNull(supplier);
        return (OptionalInt) Objects.requireNonNull(supplier.get());
    }

    public int orElse(int i) {
        return this.isPresent ? this.value : i;
    }

    public int orElseGet(IntSupplier intSupplier) {
        return this.isPresent ? this.value : intSupplier.getAsInt();
    }

    public <X extends Throwable> int orElseThrow(Supplier<X> supplier) throws Throwable {
        if (this.isPresent) {
            return this.value;
        }
        throw ((Throwable) supplier.get());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalInt)) {
            return false;
        }
        OptionalInt optionalInt = (OptionalInt) obj;
        if (this.isPresent && optionalInt.isPresent) {
            if (this.value == optionalInt.value) {
                return z;
            }
        } else if (this.isPresent == optionalInt.isPresent) {
            return z;
        }
        z = false;
        return z;
    }

    public int hashCode() {
        return this.isPresent ? this.value : 0;
    }

    public String toString() {
        if (!this.isPresent) {
            return "OptionalInt.empty";
        }
        return String.format("OptionalInt[%s]", new Object[]{Integer.valueOf(this.value)});
    }
}
