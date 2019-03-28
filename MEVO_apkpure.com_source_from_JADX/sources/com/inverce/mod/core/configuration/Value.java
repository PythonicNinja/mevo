package com.inverce.mod.core.configuration;

import android.support.annotation.NonNull;
import com.inverce.mod.core.configuration.extended.BoxedValue;
import com.inverce.mod.core.functional.IConsumer;
import com.inverce.mod.core.functional.IPredicate;
import com.inverce.mod.core.functional.ISupplier;

public class Value<T> extends ReadOnlyValue<T> {
    protected ChangeValueHandler<T> changeValueHandler;
    protected IConsumer<T> setter;
    protected IPredicate<T> validator;

    protected Value() {
        this.changeValueHandler = new ChangeValueHandler();
    }

    public Value(T t) {
        this((Object) t, Value$$Lambda$0.$instance);
    }

    public Value(T t, IPredicate<T> iPredicate) {
        this();
        BoxedValue boxedValue = new BoxedValue(t);
        boxedValue.getClass();
        setSetter(Value$$Lambda$1.get$Lambda(boxedValue));
        boxedValue.getClass();
        setGetter(Value$$Lambda$2.get$Lambda(boxedValue));
        setValidator(iPredicate);
        set(t);
    }

    public Value(ISupplier<T> iSupplier, IConsumer<T> iConsumer) {
        this(iSupplier, iConsumer, Value$$Lambda$3.$instance);
    }

    public Value(ISupplier<T> iSupplier, IConsumer<T> iConsumer, IPredicate<T> iPredicate) {
        super((ISupplier) iSupplier);
        this.changeValueHandler = new ChangeValueHandler();
        this.validator = iPredicate;
        this.setter = iConsumer;
    }

    @NonNull
    public ValueChangedEvent<ValueChanged<T>> changeValueEvent() {
        return this.changeValueHandler;
    }

    public boolean set(T t) {
        if (!this.validator.test(t)) {
            return null;
        }
        this.setter.accept(t);
        this.changeValueHandler.postNewValue(this, t);
        return true;
    }

    protected void setValidator(IPredicate<T> iPredicate) {
        this.validator = iPredicate;
    }

    protected void setSetter(IConsumer<T> iConsumer) {
        this.setter = iConsumer;
    }

    @NonNull
    public ReadOnlyValue<T> asReadOnly() {
        return new ReadOnlyValue(this);
    }
}
