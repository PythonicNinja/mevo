package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class Detector {
    private final BitMatrix image;
    private final WhiteRectangleDetector rectangleDetector;

    private static final class ResultPointsAndTransitions {
        private final ResultPoint from;
        private final ResultPoint to;
        private final int transitions;

        private ResultPointsAndTransitions(ResultPoint resultPoint, ResultPoint resultPoint2, int i) {
            this.from = resultPoint;
            this.to = resultPoint2;
            this.transitions = i;
        }

        ResultPoint getFrom() {
            return this.from;
        }

        ResultPoint getTo() {
            return this.to;
        }

        public int getTransitions() {
            return this.transitions;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.from);
            stringBuilder.append(Operation.DIVISION);
            stringBuilder.append(this.to);
            stringBuilder.append('/');
            stringBuilder.append(this.transitions);
            return stringBuilder.toString();
        }
    }

    private static final class ResultPointsAndTransitionsComparator implements Comparator<ResultPointsAndTransitions>, Serializable {
        private ResultPointsAndTransitionsComparator() {
        }

        public int compare(ResultPointsAndTransitions resultPointsAndTransitions, ResultPointsAndTransitions resultPointsAndTransitions2) {
            return resultPointsAndTransitions.getTransitions() - resultPointsAndTransitions2.getTransitions();
        }
    }

    public Detector(BitMatrix bitMatrix) throws NotFoundException {
        this.image = bitMatrix;
        this.rectangleDetector = new WhiteRectangleDetector(bitMatrix);
    }

    public DetectorResult detect() throws NotFoundException {
        ResultPoint[] detect = this.rectangleDetector.detect();
        ResultPoint resultPoint = detect[0];
        ResultPoint resultPoint2 = detect[1];
        ResultPoint resultPoint3 = detect[2];
        ResultPoint resultPoint4 = detect[3];
        List arrayList = new ArrayList(4);
        arrayList.add(transitionsBetween(resultPoint, resultPoint2));
        arrayList.add(transitionsBetween(resultPoint, resultPoint3));
        arrayList.add(transitionsBetween(resultPoint2, resultPoint4));
        arrayList.add(transitionsBetween(resultPoint3, resultPoint4));
        C04151 c04151 = null;
        Collections.sort(arrayList, new ResultPointsAndTransitionsComparator());
        ResultPointsAndTransitions resultPointsAndTransitions = (ResultPointsAndTransitions) arrayList.get(0);
        ResultPointsAndTransitions resultPointsAndTransitions2 = (ResultPointsAndTransitions) arrayList.get(1);
        Map hashMap = new HashMap();
        increment(hashMap, resultPointsAndTransitions.getFrom());
        increment(hashMap, resultPointsAndTransitions.getTo());
        increment(hashMap, resultPointsAndTransitions2.getFrom());
        increment(hashMap, resultPointsAndTransitions2.getTo());
        ResultPoint resultPoint5 = null;
        ResultPoint resultPoint6 = resultPoint5;
        for (Entry entry : hashMap.entrySet()) {
            ResultPoint resultPoint7 = (ResultPoint) entry.getKey();
            if (((Integer) entry.getValue()).intValue() == 2) {
                resultPoint5 = resultPoint7;
            } else if (c04151 == null) {
                c04151 = resultPoint7;
            } else {
                resultPoint6 = resultPoint7;
            }
        }
        if (!(c04151 == null || resultPoint5 == null)) {
            if (resultPoint6 != null) {
                BitMatrix sampleGrid;
                ResultPoint resultPoint8;
                ResultPoint[] resultPointArr = new ResultPoint[]{c04151, resultPoint5, resultPoint6};
                ResultPoint.orderBestPatterns(resultPointArr);
                resultPoint6 = resultPointArr[0];
                ResultPoint resultPoint9 = resultPointArr[1];
                ResultPoint resultPoint10 = resultPointArr[2];
                ResultPoint resultPoint11 = !hashMap.containsKey(resultPoint) ? resultPoint : !hashMap.containsKey(resultPoint2) ? resultPoint2 : !hashMap.containsKey(resultPoint3) ? resultPoint3 : resultPoint4;
                int transitions = transitionsBetween(resultPoint10, resultPoint11).getTransitions();
                int transitions2 = transitionsBetween(resultPoint6, resultPoint11).getTransitions();
                if ((transitions & 1) == 1) {
                    transitions++;
                }
                int i = transitions + 2;
                if ((transitions2 & 1) == 1) {
                    transitions2++;
                }
                int i2 = transitions2 + 2;
                if (i * 4 < i2 * 7) {
                    if (i2 * 4 < i * 7) {
                        resultPoint4 = correctTopRight(resultPoint9, resultPoint6, resultPoint10, resultPoint11, Math.min(i2, i));
                        if (resultPoint4 != null) {
                            resultPoint11 = resultPoint4;
                        }
                        transitions = Math.max(transitionsBetween(resultPoint10, resultPoint11).getTransitions(), transitionsBetween(resultPoint6, resultPoint11).getTransitions()) + 1;
                        if ((transitions & 1) == 1) {
                            transitions++;
                        }
                        int i3 = transitions;
                        sampleGrid = sampleGrid(r7.image, resultPoint10, resultPoint9, resultPoint6, resultPoint11, i3, i3);
                        resultPoint8 = resultPoint10;
                        return new DetectorResult(sampleGrid, new ResultPoint[]{resultPoint8, resultPoint9, resultPoint6, resultPoint11});
                    }
                }
                resultPoint8 = resultPoint10;
                resultPoint4 = correctTopRightRectangular(resultPoint9, resultPoint6, resultPoint10, resultPoint11, i, i2);
                if (resultPoint4 != null) {
                    resultPoint11 = resultPoint4;
                }
                transitions = transitionsBetween(resultPoint8, resultPoint11).getTransitions();
                transitions2 = transitionsBetween(resultPoint6, resultPoint11).getTransitions();
                if ((transitions & 1) == 1) {
                    transitions++;
                }
                int i4 = transitions;
                if ((transitions2 & 1) == 1) {
                    transitions2++;
                }
                sampleGrid = sampleGrid(r7.image, resultPoint8, resultPoint9, resultPoint6, resultPoint11, i4, transitions2);
                return new DetectorResult(sampleGrid, new ResultPoint[]{resultPoint8, resultPoint9, resultPoint6, resultPoint11});
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private ResultPoint correctTopRightRectangular(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) {
        float distance = ((float) distance(resultPoint, resultPoint2)) / ((float) i);
        float distance2 = (float) distance(resultPoint3, resultPoint4);
        ResultPoint resultPoint5 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint3.getX()) / distance2) * distance), resultPoint4.getY() + (distance * ((resultPoint4.getY() - resultPoint3.getY()) / distance2)));
        resultPoint = ((float) distance(resultPoint, resultPoint3)) / ((float) i2);
        distance = (float) distance(resultPoint2, resultPoint4);
        ResultPoint resultPoint6 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint2.getX()) / distance) * resultPoint), resultPoint4.getY() + (resultPoint * ((resultPoint4.getY() - resultPoint2.getY()) / distance)));
        return isValid(resultPoint5) == null ? isValid(resultPoint6) != null ? resultPoint6 : null : (isValid(resultPoint6) != null && Math.abs(i - transitionsBetween(resultPoint3, resultPoint5).getTransitions()) + Math.abs(i2 - transitionsBetween(resultPoint2, resultPoint5).getTransitions()) > Math.abs(i - transitionsBetween(resultPoint3, resultPoint6).getTransitions()) + Math.abs(i2 - transitionsBetween(resultPoint2, resultPoint6).getTransitions())) ? resultPoint6 : resultPoint5;
    }

    private ResultPoint correctTopRight(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i) {
        i = (float) i;
        float distance = ((float) distance(resultPoint, resultPoint2)) / i;
        float distance2 = (float) distance(resultPoint3, resultPoint4);
        ResultPoint resultPoint5 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint3.getX()) / distance2) * distance), resultPoint4.getY() + (distance * ((resultPoint4.getY() - resultPoint3.getY()) / distance2)));
        resultPoint = ((float) distance(resultPoint, resultPoint3)) / i;
        i = (float) distance(resultPoint2, resultPoint4);
        i = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint2.getX()) / i) * resultPoint), resultPoint4.getY() + (resultPoint * ((resultPoint4.getY() - resultPoint2.getY()) / i)));
        if (isValid(resultPoint5) == null) {
            return isValid(i) != null ? i : null;
        } else {
            if (isValid(i) == null) {
                return resultPoint5;
            }
            if (Math.abs(transitionsBetween(resultPoint3, resultPoint5).getTransitions() - transitionsBetween(resultPoint2, resultPoint5).getTransitions()) <= Math.abs(transitionsBetween(resultPoint3, i).getTransitions() - transitionsBetween(resultPoint2, i).getTransitions())) {
                i = resultPoint5;
            }
            return i;
        }
    }

    private boolean isValid(ResultPoint resultPoint) {
        return (resultPoint.getX() < 0.0f || resultPoint.getX() >= ((float) this.image.getWidth()) || resultPoint.getY() <= 0.0f || resultPoint.getY() >= ((float) this.image.getHeight())) ? null : true;
    }

    private static int distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.round(ResultPoint.distance(resultPoint, resultPoint2));
    }

    private static void increment(Map<ResultPoint, Integer> map, ResultPoint resultPoint) {
        Integer num = (Integer) map.get(resultPoint);
        int i = 1;
        if (num != null) {
            i = 1 + num.intValue();
        }
        map.put(resultPoint, Integer.valueOf(i));
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) throws NotFoundException {
        int i3 = i;
        float f = ((float) i3) - 0.5f;
        int i4 = i2;
        float f2 = ((float) i4) - 0.5f;
        return GridSampler.getInstance().sampleGrid(bitMatrix, i3, i4, 0.5f, 0.5f, f, 0.5f, f, f2, 0.5f, f2, resultPoint.getX(), resultPoint.getY(), resultPoint4.getX(), resultPoint4.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private ResultPointsAndTransitions transitionsBetween(ResultPoint resultPoint, ResultPoint resultPoint2) {
        Detector detector = this;
        int x = (int) resultPoint.getX();
        int y = (int) resultPoint.getY();
        int x2 = (int) resultPoint2.getX();
        int y2 = (int) resultPoint2.getY();
        int i = 0;
        Object obj = Math.abs(y2 - y) > Math.abs(x2 - x) ? 1 : null;
        if (obj != null) {
            int i2 = y;
            y = x;
            x = i2;
            int i3 = y2;
            y2 = x2;
            x2 = i3;
        }
        int abs = Math.abs(x2 - x);
        int abs2 = Math.abs(y2 - y);
        int i4 = (-abs) / 2;
        int i5 = -1;
        int i6 = y < y2 ? 1 : -1;
        if (x < x2) {
            i5 = 1;
        }
        boolean z = detector.image.get(obj != null ? y : x, obj != null ? x : y);
        while (x != x2) {
            boolean z2 = detector.image.get(obj != null ? y : x, obj != null ? x : y);
            if (z2 != z) {
                i++;
                z = z2;
            }
            i4 += abs2;
            if (i4 > 0) {
                if (y == y2) {
                    break;
                }
                y += i6;
                i4 -= abs;
            }
            x += i5;
        }
        return new ResultPointsAndTransitions(resultPoint, resultPoint2, i);
    }
}
