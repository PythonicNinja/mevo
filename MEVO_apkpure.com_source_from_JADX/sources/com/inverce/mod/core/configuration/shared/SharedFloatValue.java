package com.inverce.mod.core.configuration.shared;

public class SharedFloatValue extends SharedValue<Float> {
    public SharedFloatValue(String str) {
        this(str, "im_shared", null);
    }

    public SharedFloatValue(String str, Float f) {
        this(str, "im_shared", f);
    }

    public SharedFloatValue(String str, String str2) {
        this(str, str2, null);
    }

    public SharedFloatValue(String str, String str2, Float f) {
        super(Float.class, str, str2, f);
    }
}
