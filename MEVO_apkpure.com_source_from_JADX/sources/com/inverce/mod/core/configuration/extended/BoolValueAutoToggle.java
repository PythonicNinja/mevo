package com.inverce.mod.core.configuration.extended;

import com.inverce.mod.core.configuration.primitive.BoolValue;

public class BoolValueAutoToggle extends BoolValue {
    protected boolean toggled;

    public BoolValueAutoToggle(boolean z, boolean z2) {
        super(Boolean.valueOf(z));
        this.toggled = z2;
    }

    public Boolean get() {
        boolean booleanValue = ((Boolean) super.get()).booleanValue();
        if (booleanValue != this.toggled) {
            set(Boolean.valueOf(this.toggled));
        }
        return Boolean.valueOf(booleanValue);
    }
}
