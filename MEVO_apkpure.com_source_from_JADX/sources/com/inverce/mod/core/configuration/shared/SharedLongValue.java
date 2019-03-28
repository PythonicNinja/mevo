package com.inverce.mod.core.configuration.shared;

public class SharedLongValue extends SharedValue<Long> {
    public SharedLongValue(String str) {
        this(str, "im_shared", null);
    }

    public SharedLongValue(String str, Long l) {
        this(str, "im_shared", l);
    }

    public SharedLongValue(String str, String str2) {
        this(str, str2, null);
    }

    public SharedLongValue(String str, String str2, Long l) {
        super(Long.class, str, str2, l);
    }
}
