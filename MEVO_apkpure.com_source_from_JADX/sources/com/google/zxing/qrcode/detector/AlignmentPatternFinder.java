package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.List;

final class AlignmentPatternFinder {
    private final int[] crossCheckStateCount;
    private final int height;
    private final BitMatrix image;
    private final float moduleSize;
    private final List<AlignmentPattern> possibleCenters = new ArrayList(5);
    private final ResultPointCallback resultPointCallback;
    private final int startX;
    private final int startY;
    private final int width;

    AlignmentPatternFinder(BitMatrix bitMatrix, int i, int i2, int i3, int i4, float f, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.startX = i;
        this.startY = i2;
        this.width = i3;
        this.height = i4;
        this.moduleSize = f;
        this.crossCheckStateCount = new int[3];
        this.resultPointCallback = resultPointCallback;
    }

    AlignmentPattern find() throws NotFoundException {
        int i = this.startX;
        int i2 = this.height;
        int i3 = this.width + i;
        int i4 = this.startY + (i2 / 2);
        int[] iArr = new int[3];
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = ((i5 & 1) == 0 ? (i5 + 1) / 2 : -((i5 + 1) / 2)) + i4;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i7 = i;
            while (i7 < i3 && !this.image.get(i7, i6)) {
                i7++;
            }
            int i8 = 0;
            while (i7 < i3) {
                if (!this.image.get(i7, i6)) {
                    if (i8 == 1) {
                        i8++;
                    }
                    iArr[i8] = iArr[i8] + 1;
                } else if (i8 == 1) {
                    iArr[i8] = iArr[i8] + 1;
                } else if (i8 == 2) {
                    if (foundPatternCross(iArr)) {
                        AlignmentPattern handlePossibleCenter = handlePossibleCenter(iArr, i6, i7);
                        if (handlePossibleCenter != null) {
                            return handlePossibleCenter;
                        }
                    }
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i8 = 1;
                } else {
                    i8++;
                    iArr[i8] = iArr[i8] + 1;
                }
                i7++;
            }
            if (foundPatternCross(iArr)) {
                AlignmentPattern handlePossibleCenter2 = handlePossibleCenter(iArr, i6, i3);
                if (handlePossibleCenter2 != null) {
                    return handlePossibleCenter2;
                }
            }
        }
        if (!this.possibleCenters.isEmpty()) {
            return (AlignmentPattern) this.possibleCenters.get(0);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static float centerFromEnd(int[] iArr, int i) {
        return ((float) (i - iArr[2])) - (((float) iArr[1]) / 2.0f);
    }

    private boolean foundPatternCross(int[] iArr) {
        float f = this.moduleSize;
        float f2 = f / 2.0f;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(f - ((float) iArr[i])) >= f2) {
                return false;
            }
        }
        return 1;
    }

    private float crossCheckVertical(int i, int i2, int i3, int i4) {
        BitMatrix bitMatrix = this.image;
        int height = bitMatrix.getHeight();
        int[] iArr = this.crossCheckStateCount;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        int i5 = i;
        while (i5 >= 0 && bitMatrix.get(i2, i5) && iArr[1] <= i3) {
            iArr[1] = iArr[1] + 1;
            i5--;
        }
        float f = Float.NaN;
        if (i5 >= 0) {
            if (iArr[1] <= i3) {
                while (i5 >= 0 && !bitMatrix.get(i2, i5) && iArr[0] <= i3) {
                    iArr[0] = iArr[0] + 1;
                    i5--;
                }
                if (iArr[0] > i3) {
                    return Float.NaN;
                }
                i++;
                while (i < height && bitMatrix.get(i2, i) && iArr[1] <= i3) {
                    iArr[1] = iArr[1] + 1;
                    i++;
                }
                if (i != height) {
                    if (iArr[1] <= i3) {
                        while (i < height && !bitMatrix.get(i2, i) && iArr[2] <= i3) {
                            iArr[2] = iArr[2] + 1;
                            i++;
                        }
                        if (iArr[2] > i3 || Math.abs(((iArr[0] + iArr[1]) + iArr[2]) - i4) * 5 >= i4 * 2) {
                            return Float.NaN;
                        }
                        if (foundPatternCross(iArr) != 0) {
                            f = centerFromEnd(iArr, i);
                        }
                        return f;
                    }
                }
                return Float.NaN;
            }
        }
        return Float.NaN;
    }

    private AlignmentPattern handlePossibleCenter(int[] iArr, int i, int i2) {
        int i3 = (iArr[0] + iArr[1]) + iArr[2];
        i2 = centerFromEnd(iArr, i2);
        i = crossCheckVertical(i, (int) i2, iArr[1] * 2, i3);
        if (!Float.isNaN(i)) {
            iArr = ((float) ((iArr[0] + iArr[1]) + iArr[2])) / 3.0f;
            for (AlignmentPattern alignmentPattern : this.possibleCenters) {
                if (alignmentPattern.aboutEquals(iArr, i, i2)) {
                    return alignmentPattern.combineEstimate(i, i2, iArr);
                }
            }
            ResultPoint alignmentPattern2 = new AlignmentPattern(i2, i, iArr);
            this.possibleCenters.add(alignmentPattern2);
            if (this.resultPointCallback != null) {
                this.resultPointCallback.foundPossibleResultPoint(alignmentPattern2);
            }
        }
        return null;
    }
}
