package com.inverce.mod.core.configuration.primitive;

import com.inverce.mod.core.configuration.Value;
import com.inverce.mod.core.functional.IConsumer;
import com.inverce.mod.core.functional.IPredicate;
import com.inverce.mod.core.functional.ISupplier;

public class BoolValue extends Value<Boolean> {
    public BoolValue(Boolean bool) {
        super(bool);
    }

    public BoolValue(Boolean bool, IPredicate<Boolean> iPredicate) {
        super((Object) bool, (IPredicate) iPredicate);
    }

    public BoolValue(ISupplier<Boolean> iSupplier, IConsumer<Boolean> iConsumer) {
        super((ISupplier) iSupplier, (IConsumer) iConsumer);
    }

    public BoolValue(ISupplier<Boolean> iSupplier, IConsumer<Boolean> iConsumer, IPredicate<Boolean> iPredicate) {
        super(iSupplier, iConsumer, iPredicate);
    }
}
