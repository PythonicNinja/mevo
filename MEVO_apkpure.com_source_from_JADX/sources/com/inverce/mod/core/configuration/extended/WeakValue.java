package com.inverce.mod.core.configuration.extended;

import com.inverce.mod.core.configuration.Value;
import com.inverce.mod.core.functional.IPredicate;
import java.lang.ref.WeakReference;

public class WeakValue<T> extends Value<T> {
    protected WeakReference<T> weakValue;

    protected WeakValue() {
        this(null);
    }

    public WeakValue(T t) {
        this(t, WeakValue$$Lambda$0.$instance);
    }

    public WeakValue(T t, IPredicate<T> iPredicate) {
        setSetter(new WeakValue$$Lambda$1(this));
        setGetter(new WeakValue$$Lambda$2(this));
        setValidator(iPredicate);
        set(t);
    }

    final /* synthetic */ void lambda$new$1$WeakValue(Object obj) {
        this.weakValue = new WeakReference(obj);
    }

    final /* synthetic */ Object lambda$new$2$WeakValue() {
        return this.weakValue.get();
    }
}
