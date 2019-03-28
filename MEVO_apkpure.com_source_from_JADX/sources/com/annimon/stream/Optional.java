package com.annimon.stream;

import com.annimon.stream.function.Consumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Predicate;
import com.annimon.stream.function.Predicate.Util;
import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.ToDoubleFunction;
import com.annimon.stream.function.ToIntFunction;
import com.annimon.stream.function.ToLongFunction;
import java.util.NoSuchElementException;

public class Optional<T> {
    private static final Optional<?> EMPTY = new Optional();
    private final T value;

    public static <T> Optional<T> of(T t) {
        return new Optional(t);
    }

    public static <T> Optional<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public static <T> Optional<T> empty() {
        return EMPTY;
    }

    private Optional() {
        this.value = null;
    }

    private Optional(T t) {
        this.value = Objects.requireNonNull(t);
    }

    public T get() {
        if (this.value != null) {
            return this.value;
        }
        throw new NoSuchElementException("No value present");
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (this.value != null) {
            consumer.accept(this.value);
        }
    }

    public void ifPresentOrElse(Consumer<? super T> consumer, Runnable runnable) {
        if (this.value != null) {
            consumer.accept(this.value);
        } else {
            runnable.run();
        }
    }

    public Optional<T> executeIfPresent(Consumer<? super T> consumer) {
        ifPresent(consumer);
        return this;
    }

    public Optional<T> executeIfAbsent(Runnable runnable) {
        if (this.value == null) {
            runnable.run();
        }
        return this;
    }

    public <R> R custom(Function<Optional<T>, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        if (!isPresent()) {
            return this;
        }
        return predicate.test(this.value) != null ? this : empty();
    }

    public Optional<T> filterNot(Predicate<? super T> predicate) {
        return filter(Util.negate(predicate));
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> function) {
        if (isPresent()) {
            return ofNullable(function.apply(this.value));
        }
        return empty();
    }

    public OptionalInt mapToInt(ToIntFunction<? super T> toIntFunction) {
        if (isPresent()) {
            return OptionalInt.of(toIntFunction.applyAsInt(this.value));
        }
        return OptionalInt.empty();
    }

    public OptionalLong mapToLong(ToLongFunction<? super T> toLongFunction) {
        if (isPresent()) {
            return OptionalLong.of(toLongFunction.applyAsLong(this.value));
        }
        return OptionalLong.empty();
    }

    public OptionalDouble mapToDouble(ToDoubleFunction<? super T> toDoubleFunction) {
        if (isPresent()) {
            return OptionalDouble.of(toDoubleFunction.applyAsDouble(this.value));
        }
        return OptionalDouble.empty();
    }

    public <U> Optional<U> flatMap(Function<? super T, Optional<U>> function) {
        if (isPresent()) {
            return (Optional) Objects.requireNonNull(function.apply(this.value));
        }
        return empty();
    }

    public Stream<T> stream() {
        if (!isPresent()) {
            return Stream.empty();
        }
        return Stream.of(this.value);
    }

    public <R> Optional<R> select(Class<R> cls) {
        Objects.requireNonNull(cls);
        if (!isPresent()) {
            return empty();
        }
        return ofNullable(cls.isInstance(this.value) != null ? this.value : null);
    }

    public Optional<T> or(Supplier<Optional<T>> supplier) {
        if (isPresent()) {
            return this;
        }
        Objects.requireNonNull(supplier);
        return (Optional) Objects.requireNonNull(supplier.get());
    }

    public T orElse(T t) {
        return this.value != null ? this.value : t;
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        return this.value != null ? this.value : supplier.get();
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> supplier) throws Throwable {
        if (this.value != null) {
            return this.value;
        }
        throw ((Throwable) supplier.get());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Optional)) {
            return null;
        }
        return Objects.equals(this.value, ((Optional) obj).value);
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    public String toString() {
        if (this.value == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{this.value});
    }
}
