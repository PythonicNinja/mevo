package com.annimon.stream;

import com.annimon.stream.function.DoubleConsumer;
import com.annimon.stream.function.DoubleFunction;
import com.annimon.stream.function.DoublePredicate;
import com.annimon.stream.function.DoublePredicate.Util;
import com.annimon.stream.function.DoubleSupplier;
import com.annimon.stream.function.DoubleToIntFunction;
import com.annimon.stream.function.DoubleToLongFunction;
import com.annimon.stream.function.DoubleUnaryOperator;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Supplier;
import java.util.NoSuchElementException;

public final class OptionalDouble {
    private static final OptionalDouble EMPTY = new OptionalDouble();
    private final boolean isPresent;
    private final double value;

    public static OptionalDouble empty() {
        return EMPTY;
    }

    public static OptionalDouble of(double d) {
        return new OptionalDouble(d);
    }

    private OptionalDouble() {
        this.isPresent = false;
        this.value = 0.0d;
    }

    private OptionalDouble(double d) {
        this.isPresent = true;
        this.value = d;
    }

    public double getAsDouble() {
        if (this.isPresent) {
            return this.value;
        }
        throw new NoSuchElementException("No value present");
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public void ifPresent(DoubleConsumer doubleConsumer) {
        if (this.isPresent) {
            doubleConsumer.accept(this.value);
        }
    }

    public void ifPresentOrElse(DoubleConsumer doubleConsumer, Runnable runnable) {
        if (this.isPresent) {
            doubleConsumer.accept(this.value);
        } else {
            runnable.run();
        }
    }

    public OptionalDouble executeIfPresent(DoubleConsumer doubleConsumer) {
        ifPresent(doubleConsumer);
        return this;
    }

    public OptionalDouble executeIfAbsent(Runnable runnable) {
        if (!isPresent()) {
            runnable.run();
        }
        return this;
    }

    public <R> R custom(Function<OptionalDouble, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public OptionalDouble filter(DoublePredicate doublePredicate) {
        if (!isPresent()) {
            return this;
        }
        return doublePredicate.test(this.value) != null ? this : empty();
    }

    public OptionalDouble filterNot(DoublePredicate doublePredicate) {
        return filter(Util.negate(doublePredicate));
    }

    public OptionalDouble map(DoubleUnaryOperator doubleUnaryOperator) {
        if (!isPresent()) {
            return empty();
        }
        Objects.requireNonNull(doubleUnaryOperator);
        return of(doubleUnaryOperator.applyAsDouble(this.value));
    }

    public <U> Optional<U> mapToObj(DoubleFunction<U> doubleFunction) {
        if (!isPresent()) {
            return Optional.empty();
        }
        Objects.requireNonNull(doubleFunction);
        return Optional.ofNullable(doubleFunction.apply(this.value));
    }

    public OptionalInt mapToInt(DoubleToIntFunction doubleToIntFunction) {
        if (!isPresent()) {
            return OptionalInt.empty();
        }
        Objects.requireNonNull(doubleToIntFunction);
        return OptionalInt.of(doubleToIntFunction.applyAsInt(this.value));
    }

    public OptionalLong mapToLong(DoubleToLongFunction doubleToLongFunction) {
        if (!isPresent()) {
            return OptionalLong.empty();
        }
        Objects.requireNonNull(doubleToLongFunction);
        return OptionalLong.of(doubleToLongFunction.applyAsLong(this.value));
    }

    public DoubleStream stream() {
        if (isPresent()) {
            return DoubleStream.of(this.value);
        }
        return DoubleStream.empty();
    }

    public OptionalDouble or(Supplier<OptionalDouble> supplier) {
        if (isPresent()) {
            return this;
        }
        Objects.requireNonNull(supplier);
        return (OptionalDouble) Objects.requireNonNull(supplier.get());
    }

    public double orElse(double d) {
        return this.isPresent ? this.value : d;
    }

    public double orElseGet(DoubleSupplier doubleSupplier) {
        return this.isPresent ? this.value : doubleSupplier.getAsDouble();
    }

    public <X extends Throwable> double orElseThrow(Supplier<X> supplier) throws Throwable {
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
        if (!(obj instanceof OptionalDouble)) {
            return false;
        }
        OptionalDouble optionalDouble = (OptionalDouble) obj;
        if (this.isPresent && optionalDouble.isPresent) {
            if (Double.compare(this.value, optionalDouble.value) == null) {
                return z;
            }
        } else if (this.isPresent == optionalDouble.isPresent) {
            return z;
        }
        z = false;
        return z;
    }

    public int hashCode() {
        return this.isPresent ? Objects.hashCode(Double.valueOf(this.value)) : 0;
    }

    public String toString() {
        if (!this.isPresent) {
            return "OptionalDouble.empty";
        }
        return String.format("OptionalDouble[%s]", new Object[]{Double.valueOf(this.value)});
    }
}
