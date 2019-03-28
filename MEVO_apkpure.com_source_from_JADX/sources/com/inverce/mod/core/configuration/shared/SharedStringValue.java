package com.inverce.mod.core.configuration.shared;

public class SharedStringValue extends SharedValue<String> {
    public SharedStringValue(String str) {
        this(str, "im_shared", null);
    }

    public SharedStringValue(String str, String str2) {
        this(str, "im_shared", str2);
    }

    public SharedStringValue(String str, String str2, String str3) {
        super(String.class, str, str2, str3);
    }
}
