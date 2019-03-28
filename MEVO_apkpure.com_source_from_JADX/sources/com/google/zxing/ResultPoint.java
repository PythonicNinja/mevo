package com.google.zxing;

import com.google.zxing.common.detector.MathUtils;

public class ResultPoint {
    /* renamed from: x */
    private final float f16x;
    /* renamed from: y */
    private final float f17y;

    public ResultPoint(float f, float f2) {
        this.f16x = f;
        this.f17y = f2;
    }

    public final float getX() {
        return this.f16x;
    }

    public final float getY() {
        return this.f17y;
    }

    public final boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof ResultPoint)) {
            return false;
        }
        ResultPoint resultPoint = (ResultPoint) obj;
        if (this.f16x == resultPoint.f16x && this.f17y == resultPoint.f17y) {
            z = true;
        }
        return z;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f16x) * 31) + Float.floatToIntBits(this.f17y);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(25);
        stringBuilder.append('(');
        stringBuilder.append(this.f16x);
        stringBuilder.append(',');
        stringBuilder.append(this.f17y);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public static void orderBestPatterns(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        float distance = distance(resultPointArr[0], resultPointArr[1]);
        float distance2 = distance(resultPointArr[1], resultPointArr[2]);
        float distance3 = distance(resultPointArr[0], resultPointArr[2]);
        if (distance2 >= distance && distance2 >= distance3) {
            resultPoint = resultPointArr[0];
            resultPoint2 = resultPointArr[1];
            resultPoint3 = resultPointArr[2];
        } else if (distance3 < distance2 || distance3 < distance) {
            resultPoint = resultPointArr[2];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[1];
        } else {
            resultPoint = resultPointArr[1];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[2];
        }
        if (crossProductZ(resultPoint2, resultPoint, resultPoint3) < 0.0f) {
            ResultPoint resultPoint4 = resultPoint3;
            resultPoint3 = resultPoint2;
            resultPoint2 = resultPoint4;
        }
        resultPointArr[0] = resultPoint2;
        resultPointArr[1] = resultPoint;
        resultPointArr[2] = resultPoint3;
    }

    public static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.f16x, resultPoint.f17y, resultPoint2.f16x, resultPoint2.f17y);
    }

    private static float crossProductZ(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        float f = resultPoint2.f16x;
        resultPoint2 = resultPoint2.f17y;
        return ((resultPoint3.f16x - f) * (resultPoint.f17y - resultPoint2)) - ((resultPoint3.f17y - resultPoint2) * (resultPoint.f16x - f));
    }
}
