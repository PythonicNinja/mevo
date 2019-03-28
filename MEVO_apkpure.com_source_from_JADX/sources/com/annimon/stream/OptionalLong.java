package com.annimon.stream;

import com.annimon.stream.function.Function;
import com.annimon.stream.function.LongConsumer;
import com.annimon.stream.function.LongFunction;
import com.annimon.stream.function.LongPredicate;
import com.annimon.stream.function.LongPredicate.Util;
import com.annimon.stream.function.LongSupplier;
import com.annimon.stream.function.LongToIntFunction;
import com.annimon.stream.function.LongUnaryOperator;
import com.annimon.stream.function.Supplier;
import java.util.NoSuchElementException;

public final class OptionalLong {
    private static final OptionalLong EMPTY = new OptionalLong();
    private final boolean isPresent;
    private final long value;

    public static OptionalLong empty() {
        return EMPTY;
    }

    public static OptionalLong of(long j) {
        return new OptionalLong(j);
    }

    private OptionalLong() {
        this.isPresent = false;
        this.value = 0;
    }

    private OptionalLong(long j) {
        this.isPresent = true;
        this.value = j;
    }

    public long getAsLong() {
        if (this.isPresent) {
            return this.value;
        }
        throw new NoSuchElementException("No value present");
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public void ifPresent(LongConsumer longConsumer) {
        if (this.isPresent) {
            longConsumer.accept(this.value);
        }
    }

    public void ifPresentOrElse(LongConsumer longConsumer, Runnable runnable) {
        if (this.isPresent) {
            longConsumer.accept(this.value);
        } else {
            runnable.run();
        }
    }

    public OptionalLong executeIfPresent(LongConsumer longConsumer) {
        ifPresent(longConsumer);
        return this;
    }

    public OptionalLong executeIfAbsent(Runnable runnable) {
        if (!isPresent()) {
            runnable.run();
        }
        return this;
    }

    public <R> R custom(Function<OptionalLong, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public OptionalLong filter(LongPredicate longPredicate) {
        if (!isPresent()) {
            return this;
        }
        return longPredicate.test(this.value) != null ? this : empty();
    }

    public OptionalLong filterNot(LongPredicate longPredicate) {
        return filter(Util.negate(longPredicate));
    }

    public OptionalLong map(LongUnaryOperator longUnaryOperator) {
        if (!isPresent()) {
            return empty();
        }
        Objects.requireNonNull(longUnaryOperator);
        return of(longUnaryOperator.applyAsLong(this.value));
    }

    public <U> Optional<U> mapToObj(LongFunction<U> longFunction) {
        if (!isPresent()) {
            return Optional.empty();
        }
        Objects.requireNonNull(longFunction);
        return Optional.ofNullable(longFunction.apply(this.value));
    }

    public OptionalInt mapToInt(LongToIntFunction longToIntFunction) {
        if (!isPresent()) {
            return OptionalInt.empty();
        }
        Objects.requireNonNull(longToIntFunction);
        return OptionalInt.of(longToIntFunction.applyAsInt(this.value));
    }

    public LongStream stream() {
        if (isPresent()) {
            return LongStream.of(this.value);
        }
        return LongStream.empty();
    }

    public OptionalLong or(Supplier<OptionalLong> supplier) {
        if (isPresent()) {
            return this;
        }
        Objects.requireNonNull(supplier);
        return (OptionalLong) Objects.requireNonNull(supplier.get());
    }

    public long orElse(long j) {
        return this.isPresent ? this.value : j;
    }

    public long orElseGet(LongSupplier longSupplier) {
        return this.isPresent ? this.value : longSupplier.getAsLong();
    }

    public <X extends Throwable> long orElseThrow(Supplier<X> supplier) throws Throwable {
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
        if (!(obj instanceof OptionalLong)) {
            return false;
        }
        OptionalLong optionalLong = (OptionalLong) obj;
        if (this.isPresent && optionalLong.isPresent) {
            if (this.value == optionalLong.value) {
                return z;
            }
        } else if (this.isPresent == optionalLong.isPresent) {
            return z;
        }
        z = false;
        return z;
    }

    public int hashCode() {
        return this.isPresent ? Objects.hashCode(Long.valueOf(this.value)) : 0;
    }

    public String toString() {
        if (!this.isPresent) {
            return "OptionalLong.empty";
        }
        return String.format("OptionalLong[%s]", new Object[]{Long.valueOf(this.value)});
    }
}
