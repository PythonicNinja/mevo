package com.inverce.mod.core.configuration.primitive;

import com.inverce.mod.core.configuration.Value;
import com.inverce.mod.core.functional.IConsumer;
import com.inverce.mod.core.functional.IPredicate;
import com.inverce.mod.core.functional.ISupplier;

public class IntValue extends Value<Integer> {
    public IntValue(Integer num) {
        super(num);
    }

    public IntValue(Integer num, IPredicate<Integer> iPredicate) {
        super((Object) num, (IPredicate) iPredicate);
    }

    public IntValue(ISupplier<Integer> iSupplier, IConsumer<Integer> iConsumer) {
        super((ISupplier) iSupplier, (IConsumer) iConsumer);
    }

    public IntValue(ISupplier<Integer> iSupplier, IConsumer<Integer> iConsumer, IPredicate<Integer> iPredicate) {
        super(iSupplier, iConsumer, iPredicate);
    }
}
