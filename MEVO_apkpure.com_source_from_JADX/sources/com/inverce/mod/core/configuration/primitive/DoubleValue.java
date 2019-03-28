package com.inverce.mod.core.configuration.primitive;

import com.inverce.mod.core.configuration.Value;
import com.inverce.mod.core.functional.IConsumer;
import com.inverce.mod.core.functional.IPredicate;
import com.inverce.mod.core.functional.ISupplier;

public class DoubleValue extends Value<Double> {
    public DoubleValue(Double d) {
        super(d);
    }

    public DoubleValue(Double d, IPredicate<Double> iPredicate) {
        super((Object) d, (IPredicate) iPredicate);
    }

    public DoubleValue(ISupplier<Double> iSupplier, IConsumer<Double> iConsumer) {
        super((ISupplier) iSupplier, (IConsumer) iConsumer);
    }

    public DoubleValue(ISupplier<Double> iSupplier, IConsumer<Double> iConsumer, IPredicate<Double> iPredicate) {
        super(iSupplier, iConsumer, iPredicate);
    }
}
