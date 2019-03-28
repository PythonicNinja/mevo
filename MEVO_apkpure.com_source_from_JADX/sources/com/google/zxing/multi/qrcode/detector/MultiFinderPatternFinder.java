package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

final class MultiFinderPatternFinder extends FinderPatternFinder {
    private static final float DIFF_MODSIZE_CUTOFF = 0.5f;
    private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05f;
    private static final FinderPatternInfo[] EMPTY_RESULT_ARRAY = new FinderPatternInfo[0];
    private static final float MAX_MODULE_COUNT_PER_EDGE = 180.0f;
    private static final float MIN_MODULE_COUNT_PER_EDGE = 9.0f;

    private static final class ModuleSizeComparator implements Comparator<FinderPattern>, Serializable {
        private ModuleSizeComparator() {
        }

        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            finderPattern = (double) (finderPattern2.getEstimatedModuleSize() - finderPattern.getEstimatedModuleSize());
            if (finderPattern < null) {
                return -1;
            }
            return finderPattern > null ? 1 : null;
        }
    }

    MultiFinderPatternFinder(BitMatrix bitMatrix) {
        super(bitMatrix);
    }

    MultiFinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        super(bitMatrix, resultPointCallback);
    }

    private FinderPattern[][] selectMutipleBestPatterns() throws NotFoundException {
        List possibleCenters = getPossibleCenters();
        int size = possibleCenters.size();
        int i = 3;
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i2 = 0;
        if (size == 3) {
            FinderPattern[][] finderPatternArr = new FinderPattern[1][];
            finderPatternArr[0] = new FinderPattern[]{(FinderPattern) possibleCenters.get(0), (FinderPattern) possibleCenters.get(1), (FinderPattern) possibleCenters.get(2)};
            return finderPatternArr;
        }
        Collections.sort(possibleCenters, new ModuleSizeComparator());
        List arrayList = new ArrayList();
        int i3 = 0;
        while (i3 < size - 2) {
            FinderPattern finderPattern = (FinderPattern) possibleCenters.get(i3);
            if (finderPattern != null) {
                int i4 = i3 + 1;
                while (i4 < size - 1) {
                    FinderPattern finderPattern2 = (FinderPattern) possibleCenters.get(i4);
                    if (finderPattern2 != null) {
                        float estimatedModuleSize = (finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize()) / Math.min(finderPattern.getEstimatedModuleSize(), finderPattern2.getEstimatedModuleSize());
                        float abs = Math.abs(finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize());
                        float f = DIFF_MODSIZE_CUTOFF;
                        int i5 = (abs > DIFF_MODSIZE_CUTOFF ? 1 : (abs == DIFF_MODSIZE_CUTOFF ? 0 : -1));
                        float f2 = DIFF_MODSIZE_CUTOFF_PERCENT;
                        if (i5 > 0 && estimatedModuleSize >= DIFF_MODSIZE_CUTOFF_PERCENT) {
                            break;
                        }
                        int i6 = i4 + 1;
                        while (i6 < size) {
                            FinderPattern finderPattern3 = (FinderPattern) possibleCenters.get(i6);
                            if (finderPattern3 != null) {
                                float estimatedModuleSize2 = (finderPattern2.getEstimatedModuleSize() - finderPattern3.getEstimatedModuleSize()) / Math.min(finderPattern2.getEstimatedModuleSize(), finderPattern3.getEstimatedModuleSize());
                                if (Math.abs(finderPattern2.getEstimatedModuleSize() - finderPattern3.getEstimatedModuleSize()) > f && estimatedModuleSize2 >= r14) {
                                    break;
                                }
                                Object obj = new FinderPattern[i];
                                obj[i2] = finderPattern;
                                obj[1] = finderPattern2;
                                obj[2] = finderPattern3;
                                ResultPoint.orderBestPatterns(obj);
                                FinderPatternInfo finderPatternInfo = new FinderPatternInfo(obj);
                                float distance = ResultPoint.distance(finderPatternInfo.getTopLeft(), finderPatternInfo.getBottomLeft());
                                float distance2 = ResultPoint.distance(finderPatternInfo.getTopRight(), finderPatternInfo.getBottomLeft());
                                float distance3 = ResultPoint.distance(finderPatternInfo.getTopLeft(), finderPatternInfo.getTopRight());
                                abs = (distance + distance3) / (finderPattern.getEstimatedModuleSize() * 2.0f);
                                if (abs <= 180.0f) {
                                    if (abs >= MIN_MODULE_COUNT_PER_EDGE) {
                                        if (Math.abs((distance - distance3) / Math.min(distance, distance3)) < 0.1f) {
                                            distance = (float) Math.sqrt((double) ((distance * distance) + (distance3 * distance3)));
                                            if (Math.abs((distance2 - distance) / Math.min(distance2, distance)) < 0.1f) {
                                                arrayList.add(obj);
                                            }
                                        }
                                    }
                                }
                            }
                            i6++;
                            i = 3;
                            i2 = 0;
                            f = DIFF_MODSIZE_CUTOFF;
                            f2 = DIFF_MODSIZE_CUTOFF_PERCENT;
                        }
                    }
                    i4++;
                    i = 3;
                    i2 = 0;
                }
            }
            i3++;
            i = 3;
            i2 = 0;
        }
        if (!arrayList.isEmpty()) {
            return (FinderPattern[][]) arrayList.toArray(new FinderPattern[arrayList.size()][]);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public FinderPatternInfo[] findMulti(Map<DecodeHintType, ?> map) throws NotFoundException {
        int i = 0;
        Object obj = (map == null || !map.containsKey(DecodeHintType.TRY_HARDER)) ? null : 1;
        map = (map == null || map.containsKey(DecodeHintType.PURE_BARCODE) == null) ? null : true;
        BitMatrix image = getImage();
        int height = image.getHeight();
        int width = image.getWidth();
        int i2 = (int) ((((float) height) / 228.0f) * 3.0f);
        if (i2 < 3 || obj != null) {
            i2 = 3;
        }
        int[] iArr = new int[5];
        int i3 = i2 - 1;
        while (i3 < height) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i4 = 0;
            int i5 = 0;
            while (i4 < width) {
                if (image.get(i4, i3)) {
                    if ((i5 & 1) == 1) {
                        i5++;
                    }
                    iArr[i5] = iArr[i5] + 1;
                } else if ((i5 & 1) != 0) {
                    iArr[i5] = iArr[i5] + 1;
                } else if (i5 != 4) {
                    i5++;
                    iArr[i5] = iArr[i5] + 1;
                } else if (FinderPatternFinder.foundPatternCross(iArr) && handlePossibleCenter(iArr, i3, i4, map)) {
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    i5 = 0;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i5 = 3;
                }
                i4++;
            }
            if (FinderPatternFinder.foundPatternCross(iArr)) {
                handlePossibleCenter(iArr, i3, width, map);
            }
            i3 += i2;
        }
        map = selectMutipleBestPatterns();
        List arrayList = new ArrayList();
        int length = map.length;
        while (i < length) {
            ResultPoint[] resultPointArr = map[i];
            ResultPoint.orderBestPatterns(resultPointArr);
            arrayList.add(new FinderPatternInfo(resultPointArr));
            i++;
        }
        if (arrayList.isEmpty() != null) {
            return EMPTY_RESULT_ARRAY;
        }
        return (FinderPatternInfo[]) arrayList.toArray(new FinderPatternInfo[arrayList.size()]);
    }
}
