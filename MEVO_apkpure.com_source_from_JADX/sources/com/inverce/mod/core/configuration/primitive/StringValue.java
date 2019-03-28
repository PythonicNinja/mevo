package com.inverce.mod.core.configuration.primitive;

import com.inverce.mod.core.configuration.Value;
import com.inverce.mod.core.functional.IConsumer;
import com.inverce.mod.core.functional.IPredicate;
import com.inverce.mod.core.functional.ISupplier;

public class StringValue extends Value<String> {
    public StringValue(String str) {
        super(str);
    }

    public StringValue(String str, IPredicate<String> iPredicate) {
        super((Object) str, (IPredicate) iPredicate);
    }

    public StringValue(ISupplier<String> iSupplier, IConsumer<String> iConsumer) {
        super((ISupplier) iSupplier, (IConsumer) iConsumer);
    }

    public StringValue(ISupplier<String> iSupplier, IConsumer<String> iConsumer, IPredicate<String> iPredicate) {
        super(iSupplier, iConsumer, iPredicate);
    }
}
