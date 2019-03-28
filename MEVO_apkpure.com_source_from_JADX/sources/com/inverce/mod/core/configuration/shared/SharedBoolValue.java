package com.inverce.mod.core.configuration.shared;

public class SharedBoolValue extends SharedValue<Boolean> {
    public SharedBoolValue(String str) {
        this(str, "im_shared", null);
    }

    public SharedBoolValue(String str, Boolean bool) {
        this(str, "im_shared", bool);
    }

    public SharedBoolValue(String str, String str2) {
        this(str, str2, null);
    }

    public SharedBoolValue(String str, String str2, Boolean bool) {
        super(Boolean.class, str, str2, bool);
    }
}
