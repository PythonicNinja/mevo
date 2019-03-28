package com.inverce.mod.core.configuration.shared;

public class SharedBoolAutoToggle extends SharedBoolValue {
    protected boolean initialized;

    public SharedBoolAutoToggle(String str, boolean z, boolean z2) {
        super(str, Boolean.valueOf(z));
        this.initialized = z2;
    }

    public SharedBoolAutoToggle(String str, String str2, boolean z, boolean z2) {
        super(str, str2, Boolean.valueOf(z));
        this.initialized = z2;
    }

    public Boolean get() {
        boolean booleanValue = ((Boolean) super.get()).booleanValue();
        if (booleanValue != this.initialized) {
            set(Boolean.valueOf(this.initialized));
        }
        return Boolean.valueOf(booleanValue);
    }
}
