package com.inverce.mod.core.configuration.primitive;

import com.inverce.mod.core.configuration.Value;
import com.inverce.mod.core.functional.IConsumer;
import com.inverce.mod.core.functional.IPredicate;
import com.inverce.mod.core.functional.ISupplier;

public class LongValue extends Value<Long> {
    public LongValue(Long l) {
        super(l);
    }

    public LongValue(Long l, IPredicate<Long> iPredicate) {
        super((Object) l, (IPredicate) iPredicate);
    }

    public LongValue(ISupplier<Long> iSupplier, IConsumer<Long> iConsumer) {
        super((ISupplier) iSupplier, (IConsumer) iConsumer);
    }

    public LongValue(ISupplier<Long> iSupplier, IConsumer<Long> iConsumer, IPredicate<Long> iPredicate) {
        super(iSupplier, iConsumer, iPredicate);
    }
}
