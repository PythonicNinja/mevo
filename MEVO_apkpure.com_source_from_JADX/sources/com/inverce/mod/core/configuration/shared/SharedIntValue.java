package com.inverce.mod.core.configuration.shared;

public class SharedIntValue extends SharedValue<Integer> {
    public SharedIntValue(String str) {
        this(str, "im_shared", null);
    }

    public SharedIntValue(String str, Integer num) {
        this(str, "im_shared", num);
    }

    public SharedIntValue(String str, String str2) {
        this(str, str2, null);
    }

    public SharedIntValue(String str, String str2, Integer num) {
        super(Integer.class, str, str2, num);
    }
}
