package com.google.android.gms.internal.measurement;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzzw {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset UTF_8 = Charset.forName(HttpRequest.CHARSET_UTF8);
    public static final byte[] zzbux;
    private static final ByteBuffer zzbuy;
    private static final zzzj zzbuz;

    static {
        byte[] bArr = new byte[0];
        zzbux = bArr;
        zzbuy = ByteBuffer.wrap(bArr);
        bArr = zzbux;
        zzbuz = zzzj.zza(bArr, 0, bArr.length, false);
    }

    static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (i = i2; i < i2 + i3; i++) {
            i4 = (i4 * 31) + bArr[i];
        }
        return i4;
    }

    static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
