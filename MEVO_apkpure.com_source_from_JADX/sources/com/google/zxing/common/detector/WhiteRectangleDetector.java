package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class WhiteRectangleDetector {
    private static final int CORR = 1;
    private static final int INIT_SIZE = 10;
    private final int downInit;
    private final int height;
    private final BitMatrix image;
    private final int leftInit;
    private final int rightInit;
    private final int upInit;
    private final int width;

    public WhiteRectangleDetector(BitMatrix bitMatrix) throws NotFoundException {
        this(bitMatrix, 10, bitMatrix.getWidth() / 2, bitMatrix.getHeight() / 2);
    }

    public WhiteRectangleDetector(BitMatrix bitMatrix, int i, int i2, int i3) throws NotFoundException {
        this.image = bitMatrix;
        this.height = bitMatrix.getHeight();
        this.width = bitMatrix.getWidth();
        i /= 2;
        this.leftInit = i2 - i;
        this.rightInit = i2 + i;
        this.upInit = i3 - i;
        this.downInit = i3 + i;
        if (this.upInit >= null && this.leftInit >= null && this.downInit < this.height) {
            if (this.rightInit < this.width) {
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public ResultPoint[] detect() throws NotFoundException {
        int i = this.leftInit;
        int i2 = this.rightInit;
        int i3 = this.upInit;
        int i4 = this.downInit;
        boolean z = false;
        int i5 = 1;
        int i6 = i;
        Object obj = 1;
        Object obj2 = null;
        Object obj3 = null;
        Object obj4 = null;
        Object obj5 = null;
        Object obj6 = null;
        while (obj != null) {
            boolean z2 = true;
            Object obj7 = null;
            while (true) {
                if ((z2 || obj2 == null) && i2 < this.width) {
                    z2 = containsBlackPoint(i3, i4, i2, false);
                    if (z2) {
                        i2++;
                        obj2 = 1;
                        obj7 = 1;
                    } else if (obj2 == null) {
                        i2++;
                    }
                }
            }
            if (i2 < this.width) {
                z2 = true;
                while (true) {
                    if ((z2 || obj3 == null) && i4 < this.height) {
                        z2 = containsBlackPoint(i6, i2, i4, true);
                        if (z2) {
                            i4++;
                            obj3 = 1;
                            obj7 = 1;
                        } else if (obj3 == null) {
                            i4++;
                        }
                    }
                }
                if (i4 < this.height) {
                    z2 = true;
                    while (true) {
                        if ((z2 || obj4 == null) && i6 >= 0) {
                            z2 = containsBlackPoint(i3, i4, i6, false);
                            if (z2) {
                                i6--;
                                obj4 = 1;
                                obj7 = 1;
                            } else if (obj4 == null) {
                                i6--;
                            }
                        }
                    }
                    if (i6 >= 0) {
                        z2 = true;
                        while (true) {
                            if ((z2 || obj6 == null) && i3 >= 0) {
                                z2 = containsBlackPoint(i6, i2, i3, true);
                                if (z2) {
                                    i3--;
                                    obj6 = 1;
                                    obj7 = 1;
                                } else if (obj6 == null) {
                                    i3--;
                                }
                            }
                        }
                        if (i3 >= 0) {
                            if (obj7 != null) {
                                obj5 = 1;
                            }
                            obj = obj7;
                        }
                    }
                }
            }
            z = true;
            break;
        }
        if (z || r10 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i7;
        i = i2 - i6;
        ResultPoint resultPoint = null;
        ResultPoint resultPoint2 = null;
        for (i7 = 1; i7 < i; i7++) {
            resultPoint2 = getBlackPointOnSegment((float) i6, (float) (i4 - i7), (float) (i6 + i7), (float) i4);
            if (resultPoint2 != null) {
                break;
            }
        }
        if (resultPoint2 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint resultPoint3 = null;
        for (i7 = 1; i7 < i; i7++) {
            resultPoint3 = getBlackPointOnSegment((float) i6, (float) (i3 + i7), (float) (i6 + i7), (float) i3);
            if (resultPoint3 != null) {
                break;
            }
        }
        if (resultPoint3 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint resultPoint4 = null;
        for (i7 = 1; i7 < i; i7++) {
            resultPoint4 = getBlackPointOnSegment((float) i2, (float) (i3 + i7), (float) (i2 - i7), (float) i3);
            if (resultPoint4 != null) {
                break;
            }
        }
        if (resultPoint4 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        while (i5 < i) {
            resultPoint = getBlackPointOnSegment((float) i2, (float) (i4 - i5), (float) (i2 - i5), (float) i4);
            if (resultPoint != null) {
                break;
            }
            i5++;
        }
        if (resultPoint != null) {
            return centerEdges(resultPoint, resultPoint2, resultPoint4, resultPoint3);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private ResultPoint getBlackPointOnSegment(float f, float f2, float f3, float f4) {
        int round = MathUtils.round(MathUtils.distance(f, f2, f3, f4));
        float f5 = (float) round;
        f3 = (f3 - f) / f5;
        f4 = (f4 - f2) / f5;
        for (int i = 0; i < round; i++) {
            float f6 = (float) i;
            int round2 = MathUtils.round((f6 * f3) + f);
            int round3 = MathUtils.round((f6 * f4) + f2);
            if (this.image.get(round2, round3)) {
                return new ResultPoint((float) round2, (float) round3);
            }
        }
        return 0.0f;
    }

    private ResultPoint[] centerEdges(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) {
        float x = resultPoint.getX();
        resultPoint = resultPoint.getY();
        float x2 = resultPoint2.getX();
        resultPoint2 = resultPoint2.getY();
        float x3 = resultPoint3.getX();
        resultPoint3 = resultPoint3.getY();
        float x4 = resultPoint4.getX();
        resultPoint4 = resultPoint4.getY();
        if (x < ((float) this.width) / 2.0f) {
            return new ResultPoint[]{new ResultPoint(x4 - 1.0f, resultPoint4 + 1065353216), new ResultPoint(x2 + 1.0f, resultPoint2 + 1065353216), new ResultPoint(x3 - 1.0f, resultPoint3 - 1065353216), new ResultPoint(x + 1.0f, resultPoint - 1065353216)};
        }
        return new ResultPoint[]{new ResultPoint(x4 + 1.0f, resultPoint4 + 1065353216), new ResultPoint(x2 + 1.0f, resultPoint2 - 1065353216), new ResultPoint(x3 - 1.0f, resultPoint3 + 1065353216), new ResultPoint(x - 1.0f, resultPoint - 1065353216)};
    }

    private boolean containsBlackPoint(int i, int i2, int i3, boolean z) {
        if (z) {
            while (i <= i2) {
                if (this.image.get(i, i3)) {
                    return true;
                }
                i++;
            }
        } else {
            while (i <= i2) {
                if (this.image.get(i3, i)) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }
}
