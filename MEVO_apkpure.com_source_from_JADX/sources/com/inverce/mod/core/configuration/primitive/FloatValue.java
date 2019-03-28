package com.inverce.mod.core.configuration.primitive;

import com.inverce.mod.core.configuration.Value;
import com.inverce.mod.core.functional.IConsumer;
import com.inverce.mod.core.functional.IPredicate;
import com.inverce.mod.core.functional.ISupplier;

public class FloatValue extends Value<Float> {
    public FloatValue(Float f) {
        super(f);
    }

    public FloatValue(Float f, IPredicate<Float> iPredicate) {
        super((Object) f, (IPredicate) iPredicate);
    }

    public FloatValue(ISupplier<Float> iSupplier, IConsumer<Float> iConsumer) {
        super((ISupplier) iSupplier, (IConsumer) iConsumer);
    }

    public FloatValue(ISupplier<Float> iSupplier, IConsumer<Float> iConsumer, IPredicate<Float> iPredicate) {
        super(iSupplier, iConsumer, iPredicate);
    }
}
