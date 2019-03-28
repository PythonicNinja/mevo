package com.inverce.mod.core.configuration.shared;

import android.support.annotation.Nullable;
import com.inverce.mod.core.MathEx;

public class SharedBase64Value extends SharedValue<String> {
    public SharedBase64Value(String str) {
        this(str, "im_shared", null);
    }

    public SharedBase64Value(String str, String str2) {
        this(str, "im_shared", str2);
    }

    public SharedBase64Value(String str, String str2, String str3) {
        super(String.class, str, str2, str3);
    }

    @Nullable
    public String get() {
        return MathEx.fromBase64((String) super.get());
    }

    public boolean set(String str) {
        return super.set(MathEx.toBase64(str));
    }
}
