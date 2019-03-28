package com.annimon.stream;

import com.annimon.stream.function.BooleanConsumer;
import com.annimon.stream.function.BooleanFunction;
import com.annimon.stream.function.BooleanPredicate;
import com.annimon.stream.function.BooleanPredicate.Util;
import com.annimon.stream.function.BooleanSupplier;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Supplier;
import java.util.NoSuchElementException;

public final class OptionalBoolean {
    private static final OptionalBoolean EMPTY = new OptionalBoolean();
    private static final OptionalBoolean FALSE = new OptionalBoolean(false);
    private static final OptionalBoolean TRUE = new OptionalBoolean(true);
    private final boolean isPresent;
    private final boolean value;

    public static OptionalBoolean empty() {
        return EMPTY;
    }

    public static OptionalBoolean of(boolean z) {
        return z ? TRUE : FALSE;
    }

    private OptionalBoolean() {
        this.isPresent = false;
        this.value = false;
    }

    private OptionalBoolean(boolean z) {
        this.isPresent = true;
        this.value = z;
    }

    public boolean getAsBoolean() {
        if (this.isPresent) {
            return this.value;
        }
        throw new NoSuchElementException("No value present");
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public void ifPresent(BooleanConsumer booleanConsumer) {
        if (this.isPresent) {
            booleanConsumer.accept(this.value);
        }
    }

    public void ifPresentOrElse(BooleanConsumer booleanConsumer, Runnable runnable) {
        if (this.isPresent) {
            booleanConsumer.accept(this.value);
        } else {
            runnable.run();
        }
    }

    public OptionalBoolean executeIfPresent(BooleanConsumer booleanConsumer) {
        ifPresent(booleanConsumer);
        return this;
    }

    public OptionalBoolean executeIfAbsent(Runnable runnable) {
        if (!isPresent()) {
            runnable.run();
        }
        return this;
    }

    public <R> R custom(Function<OptionalBoolean, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public OptionalBoolean filter(BooleanPredicate booleanPredicate) {
        if (!isPresent()) {
            return this;
        }
        return booleanPredicate.test(this.value) != null ? this : empty();
    }

    public OptionalBoolean filterNot(BooleanPredicate booleanPredicate) {
        return filter(Util.negate(booleanPredicate));
    }

    public OptionalBoolean map(BooleanPredicate booleanPredicate) {
        if (!isPresent()) {
            return empty();
        }
        Objects.requireNonNull(booleanPredicate);
        return of(booleanPredicate.test(this.value));
    }

    public <U> Optional<U> mapToObj(BooleanFunction<U> booleanFunction) {
        if (!isPresent()) {
            return Optional.empty();
        }
        Objects.requireNonNull(booleanFunction);
        return Optional.ofNullable(booleanFunction.apply(this.value));
    }

    public OptionalBoolean or(Supplier<OptionalBoolean> supplier) {
        if (isPresent()) {
            return this;
        }
        Objects.requireNonNull(supplier);
        return (OptionalBoolean) Objects.requireNonNull(supplier.get());
    }

    public boolean orElse(boolean z) {
        return this.isPresent ? this.value : z;
    }

    public boolean orElseGet(BooleanSupplier booleanSupplier) {
        return this.isPresent ? this.value : booleanSupplier.getAsBoolean();
    }

    public <X extends Throwable> boolean orElseThrow(Supplier<X> supplier) throws Throwable {
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
        if (!(obj instanceof OptionalBoolean)) {
            return false;
        }
        OptionalBoolean optionalBoolean = (OptionalBoolean) obj;
        if (this.isPresent && optionalBoolean.isPresent) {
            if (this.value == optionalBoolean.value) {
                return z;
            }
        } else if (this.isPresent == optionalBoolean.isPresent) {
            return z;
        }
        z = false;
        return z;
    }

    public int hashCode() {
        if (this.isPresent) {
            return this.value ? 1231 : 1237;
        } else {
            return 0;
        }
    }

    public String toString() {
        if (this.isPresent) {
            return this.value ? "OptionalBoolean[true]" : "OptionalBoolean[false]";
        } else {
            return "OptionalBoolean.empty";
        }
    }
}
