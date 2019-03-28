package com.inverce.mod.core;

import android.graphics.PointF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public final class MathEx {
    public static float lerp(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }

    public static int lerp(int i, int i2, float f) {
        return (int) (((float) i) + (((float) (i2 - i)) * f));
    }

    @ColorInt
    public static int lerpColor(@ColorInt int i, @ColorInt int i2, float f) {
        int i3 = (i >> 24) & 255;
        int i4 = (i >> 16) & 255;
        int i5 = (i >> 8) & 255;
        i &= 255;
        return (i + ((int) (f * ((float) ((i2 & 255) - i))))) | ((((i3 + ((int) (((float) (((i2 >> 24) & 255) - i3)) * f))) << 24) | ((i4 + ((int) (((float) (((i2 >> 16) & 255) - i4)) * f))) << 16)) | ((i5 + ((int) (((float) (((i2 >> 8) & 255) - i5)) * f))) << 8));
    }

    public static float normalize(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    @NonNull
    public static float[] lerp(@NonNull float[] fArr, float[] fArr2, float f) {
        float[] fArr3 = new float[fArr.length];
        for (int i = 0; i < fArr3.length; i++) {
            float f2 = fArr[i];
            fArr3[i] = f2 + ((fArr2[i] - f2) * f);
        }
        return fArr3;
    }

    @NonNull
    public static PointF lerp(@NonNull PointF pointF, @NonNull PointF pointF2, float f) {
        return new PointF(pointF.x + ((pointF2.x - pointF.x) * f), pointF.y + (f * (pointF2.y - pointF.y)));
    }

    public static float clamp(float f, float f2, float f3) {
        return Math.min(f3, Math.max(f2, f));
    }

    public static float distanceSquared(float f, float f2, float f3, float f4) {
        return (float) (Math.pow((double) (f2 - f), 2.0d) + Math.pow((double) (f4 - f3), 2.0d));
    }

    public static float distance(float f, float f2, float f3, float f4) {
        return (float) Math.sqrt(Math.pow((double) (f2 - f), 2.0d) + Math.pow((double) (f4 - f3), 2.0d));
    }

    @Nullable
    public static String toBase64(@Nullable String str) {
        return str == null ? null : Base64.encodeToString(str.getBytes(), 2);
    }

    @Nullable
    public static String toBase64(@Nullable byte[] bArr) {
        return bArr == null ? null : Base64.encodeToString(bArr, 2);
    }

    @Nullable
    public static String fromBase64(@Nullable String str) {
        return str == null ? null : new String(Base64.decode(str, 2));
    }

    @Nullable
    public static byte[] fromBase64Bytes(@Nullable String str) {
        return str == null ? null : Base64.decode(str, 2);
    }

    @NonNull
    public static <E> List<List<E>> generatePermutations(@NonNull List<E> list) {
        if (list.size() == 0) {
            list = new ArrayList();
            list.add(new ArrayList());
            return list;
        }
        Object remove = list.remove(0);
        List<List<E>> arrayList = new ArrayList();
        for (E e : generatePermutations(list)) {
            for (int i = 0; i <= e.size(); i++) {
                List arrayList2 = new ArrayList(e);
                arrayList2.add(i, remove);
                arrayList.add(arrayList2);
            }
        }
        return arrayList;
    }

    @NonNull
    public static String decimFormat(String str, double d) {
        return new DecimalFormat(str).format(d);
    }
}
