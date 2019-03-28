package com.inverce.mod.core.configuration.shared;

import android.support.annotation.NonNull;
import com.inverce.mod.core.configuration.Value;

public class SharedValue<T> extends Value<T> {
    protected SharedValueImpl<T> impl;

    static final /* synthetic */ boolean lambda$new$0$SharedValue(Object obj) {
        return obj != null;
    }

    public SharedValue(@NonNull Class<T> cls, String str) {
        this(cls, str, "im_shared", null);
    }

    public SharedValue(@NonNull Class<T> cls, String str, T t) {
        this(cls, str, "im_shared", t);
    }

    public SharedValue(@NonNull Class<T> cls, String str, String str2) {
        this(cls, str, str2, null);
    }

    public SharedValue(@NonNull Class<T> cls, String str, String str2, T t) {
        this.impl = implementationForClass(cls.getName());
        this.impl.with(str, str2, t);
        cls = this.impl;
        cls.getClass();
        setSetter(SharedValue$$Lambda$0.get$Lambda(cls));
        cls = this.impl;
        cls.getClass();
        setGetter(SharedValue$$Lambda$1.get$Lambda(cls));
        setValidator(SharedValue$$Lambda$2.$instance);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.NonNull
    private com.inverce.mod.core.configuration.shared.SharedValueImpl<T> implementationForClass(@android.support.annotation.NonNull java.lang.String r2) {
        /*
        r1 = this;
        r0 = r2.hashCode();
        switch(r0) {
            case -2056817302: goto L_0x0030;
            case -527879800: goto L_0x0026;
            case 344809556: goto L_0x001c;
            case 398795216: goto L_0x0012;
            case 1195259493: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x003a;
    L_0x0008:
        r0 = "java.lang.String";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x003a;
    L_0x0010:
        r2 = 4;
        goto L_0x003b;
    L_0x0012:
        r0 = "java.lang.Long";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x003a;
    L_0x001a:
        r2 = 3;
        goto L_0x003b;
    L_0x001c:
        r0 = "java.lang.Boolean";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x003a;
    L_0x0024:
        r2 = 0;
        goto L_0x003b;
    L_0x0026:
        r0 = "java.lang.Float";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x003a;
    L_0x002e:
        r2 = 1;
        goto L_0x003b;
    L_0x0030:
        r0 = "java.lang.Integer";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x003a;
    L_0x0038:
        r2 = 2;
        goto L_0x003b;
    L_0x003a:
        r2 = -1;
    L_0x003b:
        switch(r2) {
            case 0: goto L_0x005e;
            case 1: goto L_0x0058;
            case 2: goto L_0x0052;
            case 3: goto L_0x004c;
            case 4: goto L_0x0046;
            default: goto L_0x003e;
        };
    L_0x003e:
        r2 = new java.lang.UnsupportedOperationException;
        r0 = "This type is unsupported";
        r2.<init>(r0);
        throw r2;
    L_0x0046:
        r2 = new com.inverce.mod.core.configuration.shared.SharedValueImpl$Text;
        r2.<init>();
        goto L_0x0063;
    L_0x004c:
        r2 = new com.inverce.mod.core.configuration.shared.SharedValueImpl$LongInt;
        r2.<init>();
        goto L_0x0063;
    L_0x0052:
        r2 = new com.inverce.mod.core.configuration.shared.SharedValueImpl$Int;
        r2.<init>();
        goto L_0x0063;
    L_0x0058:
        r2 = new com.inverce.mod.core.configuration.shared.SharedValueImpl$Floating;
        r2.<init>();
        goto L_0x0063;
    L_0x005e:
        r2 = new com.inverce.mod.core.configuration.shared.SharedValueImpl$Bool;
        r2.<init>();
    L_0x0063:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inverce.mod.core.configuration.shared.SharedValue.implementationForClass(java.lang.String):com.inverce.mod.core.configuration.shared.SharedValueImpl<T>");
    }

    public String getKey() {
        return this.impl.getKey();
    }
}
