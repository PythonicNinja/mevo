package android.support.v4.graphics;

import android.graphics.Path;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import java.util.ArrayList;

@RestrictTo({Scope.LIBRARY_GROUP})
public class PathParser {
    private static final String LOGTAG = "PathParser";

    private static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        ExtractFloatResult() {
        }
    }

    public static class PathDataNode {
        @RestrictTo({Scope.LIBRARY_GROUP})
        public float[] mParams;
        @RestrictTo({Scope.LIBRARY_GROUP})
        public char mType;

        PathDataNode(char c, float[] fArr) {
            this.mType = c;
            this.mParams = fArr;
        }

        PathDataNode(PathDataNode pathDataNode) {
            this.mType = pathDataNode.mType;
            this.mParams = PathParser.copyOfRange(pathDataNode.mParams, 0, pathDataNode.mParams.length);
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            float[] fArr = new float[6];
            char c = 'm';
            for (int i = 0; i < pathDataNodeArr.length; i++) {
                addCommand(path, fArr, c, pathDataNodeArr[i].mType, pathDataNodeArr[i].mParams);
                c = pathDataNodeArr[i].mType;
            }
        }

        public void interpolatePathDataNode(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f) {
            for (int i = 0; i < pathDataNode.mParams.length; i++) {
                this.mParams[i] = (pathDataNode.mParams[i] * (1.0f - f)) + (pathDataNode2.mParams[i] * f);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static void addCommand(android.graphics.Path r27, float[] r28, char r29, char r30, float[] r31) {
            /*
            r10 = r27;
            r13 = r31;
            r14 = 0;
            r0 = r28[r14];
            r15 = 1;
            r1 = r28[r15];
            r16 = 2;
            r2 = r28[r16];
            r17 = 3;
            r3 = r28[r17];
            r18 = 4;
            r4 = r28[r18];
            r19 = 5;
            r5 = r28[r19];
            switch(r30) {
                case 65: goto L_0x0035;
                case 67: goto L_0x0031;
                case 72: goto L_0x002e;
                case 76: goto L_0x001d;
                case 77: goto L_0x001d;
                case 81: goto L_0x002b;
                case 83: goto L_0x002b;
                case 84: goto L_0x001d;
                case 86: goto L_0x002e;
                case 90: goto L_0x0020;
                case 97: goto L_0x0035;
                case 99: goto L_0x0031;
                case 104: goto L_0x002e;
                case 108: goto L_0x001d;
                case 109: goto L_0x001d;
                case 113: goto L_0x002b;
                case 115: goto L_0x002b;
                case 116: goto L_0x001d;
                case 118: goto L_0x002e;
                case 122: goto L_0x0020;
                default: goto L_0x001d;
            };
        L_0x001d:
            r20 = 2;
            goto L_0x0038;
        L_0x0020:
            r27.close();
            r10.moveTo(r4, r5);
            r0 = r4;
            r2 = r0;
            r1 = r5;
            r3 = r1;
            goto L_0x001d;
        L_0x002b:
            r20 = 4;
            goto L_0x0038;
        L_0x002e:
            r20 = 1;
            goto L_0x0038;
        L_0x0031:
            r6 = 6;
            r20 = 6;
            goto L_0x0038;
        L_0x0035:
            r6 = 7;
            r20 = 7;
        L_0x0038:
            r8 = r0;
            r7 = r1;
            r21 = r4;
            r22 = r5;
            r9 = 0;
            r0 = r29;
        L_0x0041:
            r1 = r13.length;
            if (r9 >= r1) goto L_0x02dc;
        L_0x0044:
            r4 = 99;
            r5 = 84;
            r6 = 81;
            r15 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
            r14 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
            r23 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
            r1 = 0;
            switch(r30) {
                case 65: goto L_0x029a;
                case 67: goto L_0x0270;
                case 72: goto L_0x0262;
                case 76: goto L_0x024f;
                case 77: goto L_0x022d;
                case 81: goto L_0x020c;
                case 83: goto L_0x01d1;
                case 84: goto L_0x01aa;
                case 86: goto L_0x019c;
                case 97: goto L_0x0150;
                case 99: goto L_0x0124;
                case 104: goto L_0x0118;
                case 108: goto L_0x0105;
                case 109: goto L_0x00e3;
                case 113: goto L_0x00c3;
                case 115: goto L_0x008a;
                case 116: goto L_0x0065;
                case 118: goto L_0x005a;
                default: goto L_0x0054;
            };
        L_0x0054:
            r12 = r7;
            r11 = r8;
        L_0x0056:
            r26 = r9;
            goto L_0x02d4;
        L_0x005a:
            r0 = r9 + 0;
            r4 = r13[r0];
            r10.rLineTo(r1, r4);
            r0 = r13[r0];
            r7 = r7 + r0;
            goto L_0x0056;
        L_0x0065:
            if (r0 == r14) goto L_0x0070;
        L_0x0067:
            if (r0 == r15) goto L_0x0070;
        L_0x0069:
            if (r0 == r6) goto L_0x0070;
        L_0x006b:
            if (r0 != r5) goto L_0x006e;
        L_0x006d:
            goto L_0x0070;
        L_0x006e:
            r0 = 0;
            goto L_0x0074;
        L_0x0070:
            r1 = r8 - r2;
            r0 = r7 - r3;
        L_0x0074:
            r2 = r9 + 0;
            r3 = r13[r2];
            r4 = r9 + 1;
            r5 = r13[r4];
            r10.rQuadTo(r1, r0, r3, r5);
            r1 = r1 + r8;
            r0 = r0 + r7;
            r2 = r13[r2];
            r8 = r8 + r2;
            r2 = r13[r4];
            r7 = r7 + r2;
            r3 = r0;
            r2 = r1;
            goto L_0x0056;
        L_0x008a:
            if (r0 == r4) goto L_0x009b;
        L_0x008c:
            r4 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
            if (r0 == r4) goto L_0x009b;
        L_0x0090:
            r4 = 67;
            if (r0 == r4) goto L_0x009b;
        L_0x0094:
            r4 = 83;
            if (r0 != r4) goto L_0x0099;
        L_0x0098:
            goto L_0x009b;
        L_0x0099:
            r2 = 0;
            goto L_0x00a1;
        L_0x009b:
            r0 = r8 - r2;
            r1 = r7 - r3;
            r2 = r1;
            r1 = r0;
        L_0x00a1:
            r14 = r9 + 0;
            r3 = r13[r14];
            r15 = r9 + 1;
            r4 = r13[r15];
            r23 = r9 + 2;
            r5 = r13[r23];
            r24 = r9 + 3;
            r6 = r13[r24];
            r0 = r10;
            r0.rCubicTo(r1, r2, r3, r4, r5, r6);
            r0 = r13[r14];
            r0 = r0 + r8;
            r1 = r13[r15];
            r1 = r1 + r7;
            r2 = r13[r23];
            r8 = r8 + r2;
            r2 = r13[r24];
            r7 = r7 + r2;
            goto L_0x014c;
        L_0x00c3:
            r0 = r9 + 0;
            r1 = r13[r0];
            r2 = r9 + 1;
            r3 = r13[r2];
            r4 = r9 + 2;
            r5 = r13[r4];
            r6 = r9 + 3;
            r14 = r13[r6];
            r10.rQuadTo(r1, r3, r5, r14);
            r0 = r13[r0];
            r0 = r0 + r8;
            r1 = r13[r2];
            r1 = r1 + r7;
            r2 = r13[r4];
            r8 = r8 + r2;
            r2 = r13[r6];
            r7 = r7 + r2;
            goto L_0x014c;
        L_0x00e3:
            r0 = r9 + 0;
            r1 = r13[r0];
            r8 = r8 + r1;
            r1 = r9 + 1;
            r4 = r13[r1];
            r7 = r7 + r4;
            if (r9 <= 0) goto L_0x00f8;
        L_0x00ef:
            r0 = r13[r0];
            r1 = r13[r1];
            r10.rLineTo(r0, r1);
            goto L_0x0056;
        L_0x00f8:
            r0 = r13[r0];
            r1 = r13[r1];
            r10.rMoveTo(r0, r1);
            r22 = r7;
            r21 = r8;
            goto L_0x0056;
        L_0x0105:
            r0 = r9 + 0;
            r1 = r13[r0];
            r4 = r9 + 1;
            r5 = r13[r4];
            r10.rLineTo(r1, r5);
            r0 = r13[r0];
            r8 = r8 + r0;
            r0 = r13[r4];
            r7 = r7 + r0;
            goto L_0x0056;
        L_0x0118:
            r0 = r9 + 0;
            r4 = r13[r0];
            r10.rLineTo(r4, r1);
            r0 = r13[r0];
            r8 = r8 + r0;
            goto L_0x0056;
        L_0x0124:
            r0 = r9 + 0;
            r1 = r13[r0];
            r0 = r9 + 1;
            r2 = r13[r0];
            r14 = r9 + 2;
            r3 = r13[r14];
            r15 = r9 + 3;
            r4 = r13[r15];
            r23 = r9 + 4;
            r5 = r13[r23];
            r24 = r9 + 5;
            r6 = r13[r24];
            r0 = r10;
            r0.rCubicTo(r1, r2, r3, r4, r5, r6);
            r0 = r13[r14];
            r0 = r0 + r8;
            r1 = r13[r15];
            r1 = r1 + r7;
            r2 = r13[r23];
            r8 = r8 + r2;
            r2 = r13[r24];
            r7 = r7 + r2;
        L_0x014c:
            r2 = r0;
            r3 = r1;
            goto L_0x0056;
        L_0x0150:
            r14 = r9 + 5;
            r0 = r13[r14];
            r3 = r0 + r8;
            r15 = r9 + 6;
            r0 = r13[r15];
            r4 = r0 + r7;
            r0 = r9 + 0;
            r5 = r13[r0];
            r0 = r9 + 1;
            r6 = r13[r0];
            r0 = r9 + 2;
            r23 = r13[r0];
            r0 = r9 + 3;
            r0 = r13[r0];
            r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
            if (r0 == 0) goto L_0x0173;
        L_0x0170:
            r24 = 1;
            goto L_0x0175;
        L_0x0173:
            r24 = 0;
        L_0x0175:
            r0 = r9 + 4;
            r0 = r13[r0];
            r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
            if (r0 == 0) goto L_0x0180;
        L_0x017d:
            r25 = 1;
            goto L_0x0182;
        L_0x0180:
            r25 = 0;
        L_0x0182:
            r0 = r10;
            r1 = r8;
            r2 = r7;
            r12 = r7;
            r7 = r23;
            r11 = r8;
            r8 = r24;
            r26 = r9;
            r9 = r25;
            drawArc(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9);
            r0 = r13[r14];
            r8 = r11 + r0;
            r0 = r13[r15];
            r7 = r12 + r0;
            goto L_0x02d2;
        L_0x019c:
            r11 = r8;
            r26 = r9;
            r9 = r26 + 0;
            r0 = r13[r9];
            r10.lineTo(r11, r0);
            r7 = r13[r9];
            goto L_0x02d4;
        L_0x01aa:
            r12 = r7;
            r11 = r8;
            r26 = r9;
            if (r0 == r14) goto L_0x01b6;
        L_0x01b0:
            if (r0 == r15) goto L_0x01b6;
        L_0x01b2:
            if (r0 == r6) goto L_0x01b6;
        L_0x01b4:
            if (r0 != r5) goto L_0x01be;
        L_0x01b6:
            r8 = r11 * r23;
            r8 = r8 - r2;
            r7 = r12 * r23;
            r7 = r7 - r3;
            r12 = r7;
            r11 = r8;
        L_0x01be:
            r9 = r26 + 0;
            r0 = r13[r9];
            r1 = r26 + 1;
            r2 = r13[r1];
            r10.quadTo(r11, r12, r0, r2);
            r8 = r13[r9];
            r7 = r13[r1];
            r2 = r11;
            r3 = r12;
            goto L_0x02d4;
        L_0x01d1:
            r12 = r7;
            r11 = r8;
            r26 = r9;
            if (r0 == r4) goto L_0x01e7;
        L_0x01d7:
            r1 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
            if (r0 == r1) goto L_0x01e7;
        L_0x01db:
            r1 = 67;
            if (r0 == r1) goto L_0x01e7;
        L_0x01df:
            r1 = 83;
            if (r0 != r1) goto L_0x01e4;
        L_0x01e3:
            goto L_0x01e7;
        L_0x01e4:
            r1 = r11;
            r2 = r12;
            goto L_0x01ef;
        L_0x01e7:
            r8 = r11 * r23;
            r8 = r8 - r2;
            r7 = r12 * r23;
            r7 = r7 - r3;
            r2 = r7;
            r1 = r8;
        L_0x01ef:
            r9 = r26 + 0;
            r3 = r13[r9];
            r7 = r26 + 1;
            r4 = r13[r7];
            r8 = r26 + 2;
            r5 = r13[r8];
            r11 = r26 + 3;
            r6 = r13[r11];
            r0 = r10;
            r0.cubicTo(r1, r2, r3, r4, r5, r6);
            r0 = r13[r9];
            r1 = r13[r7];
            r8 = r13[r8];
            r7 = r13[r11];
            goto L_0x0229;
        L_0x020c:
            r26 = r9;
            r9 = r26 + 0;
            r0 = r13[r9];
            r1 = r26 + 1;
            r2 = r13[r1];
            r3 = r26 + 2;
            r4 = r13[r3];
            r5 = r26 + 3;
            r6 = r13[r5];
            r10.quadTo(r0, r2, r4, r6);
            r0 = r13[r9];
            r1 = r13[r1];
            r8 = r13[r3];
            r7 = r13[r5];
        L_0x0229:
            r2 = r0;
            r3 = r1;
            goto L_0x02d4;
        L_0x022d:
            r26 = r9;
            r9 = r26 + 0;
            r8 = r13[r9];
            r0 = r26 + 1;
            r7 = r13[r0];
            if (r26 <= 0) goto L_0x0242;
        L_0x0239:
            r1 = r13[r9];
            r0 = r13[r0];
            r10.lineTo(r1, r0);
            goto L_0x02d4;
        L_0x0242:
            r1 = r13[r9];
            r0 = r13[r0];
            r10.moveTo(r1, r0);
            r22 = r7;
            r21 = r8;
            goto L_0x02d4;
        L_0x024f:
            r26 = r9;
            r9 = r26 + 0;
            r0 = r13[r9];
            r1 = r26 + 1;
            r4 = r13[r1];
            r10.lineTo(r0, r4);
            r8 = r13[r9];
            r7 = r13[r1];
            goto L_0x02d4;
        L_0x0262:
            r12 = r7;
            r26 = r9;
            r9 = r26 + 0;
            r0 = r13[r9];
            r10.lineTo(r0, r12);
            r8 = r13[r9];
            goto L_0x02d4;
        L_0x0270:
            r26 = r9;
            r9 = r26 + 0;
            r1 = r13[r9];
            r9 = r26 + 1;
            r2 = r13[r9];
            r9 = r26 + 2;
            r3 = r13[r9];
            r7 = r26 + 3;
            r4 = r13[r7];
            r8 = r26 + 4;
            r5 = r13[r8];
            r11 = r26 + 5;
            r6 = r13[r11];
            r0 = r10;
            r0.cubicTo(r1, r2, r3, r4, r5, r6);
            r8 = r13[r8];
            r0 = r13[r11];
            r1 = r13[r9];
            r2 = r13[r7];
            r7 = r0;
            r3 = r2;
            r2 = r1;
            goto L_0x02d4;
        L_0x029a:
            r12 = r7;
            r11 = r8;
            r26 = r9;
            r14 = r26 + 5;
            r3 = r13[r14];
            r15 = r26 + 6;
            r4 = r13[r15];
            r9 = r26 + 0;
            r5 = r13[r9];
            r9 = r26 + 1;
            r6 = r13[r9];
            r9 = r26 + 2;
            r7 = r13[r9];
            r9 = r26 + 3;
            r0 = r13[r9];
            r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
            if (r0 == 0) goto L_0x02bc;
        L_0x02ba:
            r8 = 1;
            goto L_0x02bd;
        L_0x02bc:
            r8 = 0;
        L_0x02bd:
            r9 = r26 + 4;
            r0 = r13[r9];
            r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
            if (r0 == 0) goto L_0x02c7;
        L_0x02c5:
            r9 = 1;
            goto L_0x02c8;
        L_0x02c7:
            r9 = 0;
        L_0x02c8:
            r0 = r10;
            r1 = r11;
            r2 = r12;
            drawArc(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9);
            r8 = r13[r14];
            r7 = r13[r15];
        L_0x02d2:
            r3 = r7;
            r2 = r8;
        L_0x02d4:
            r9 = r26 + r20;
            r0 = r30;
            r14 = 0;
            r15 = 1;
            goto L_0x0041;
        L_0x02dc:
            r12 = r7;
            r1 = 0;
            r28[r1] = r8;
            r1 = 1;
            r28[r1] = r12;
            r28[r16] = r2;
            r28[r17] = r3;
            r28[r18] = r21;
            r28[r19] = r22;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.PathParser.PathDataNode.addCommand(android.graphics.Path, float[], char, char, float[]):void");
        }

        private static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            float f8 = f;
            float f9 = f3;
            float f10 = f5;
            float f11 = f6;
            boolean z3 = z2;
            double toRadians = Math.toRadians((double) f7);
            double cos = Math.cos(toRadians);
            double sin = Math.sin(toRadians);
            double d = (double) f8;
            double d2 = toRadians;
            toRadians = (double) f2;
            double d3 = d;
            d = (double) f10;
            double d4 = ((d * cos) + (toRadians * sin)) / d;
            double d5 = toRadians;
            toRadians = (double) f11;
            double d6 = ((((double) (-f8)) * sin) + (toRadians * cos)) / toRadians;
            double d7 = (double) f4;
            double d8 = ((((double) f9) * cos) + (d7 * sin)) / d;
            double d9 = d;
            d = ((((double) (-f9)) * sin) + (d7 * cos)) / toRadians;
            d7 = d4 - d8;
            double d10 = d6 - d;
            double d11 = (d4 + d8) / 2.0d;
            double d12 = (d6 + d) / 2.0d;
            double d13 = sin;
            sin = (d7 * d7) + (d10 * d10);
            if (sin == 0.0d) {
                Log.w(PathParser.LOGTAG, " Points are coincident");
                return;
            }
            double d14 = cos;
            cos = (1.0d / sin) - 0.25d;
            if (cos < 0.0d) {
                String str = PathParser.LOGTAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Points are too far apart ");
                stringBuilder.append(sin);
                Log.w(str, stringBuilder.toString());
                f8 = (float) (Math.sqrt(sin) / 1.99999d);
                drawArc(path, f, f2, f9, f4, f10 * f8, f11 * f8, f7, z, z2);
                return;
            }
            boolean z4 = z2;
            double sqrt = Math.sqrt(cos);
            d7 *= sqrt;
            sqrt *= d10;
            if (z == z4) {
                d11 -= sqrt;
                d12 += d7;
            } else {
                d11 += sqrt;
                d12 -= d7;
            }
            sqrt = Math.atan2(d6 - d12, d4 - d11);
            double atan2 = Math.atan2(d - d12, d8 - d11) - sqrt;
            if (z4 != (atan2 >= 0.0d)) {
                atan2 = atan2 > 0.0d ? atan2 - 6.283185307179586d : atan2 + 6.283185307179586d;
            }
            d11 *= d9;
            d12 *= toRadians;
            arcToBezier(path, (d11 * d14) - (d12 * d13), (d11 * d13) + (d12 * d14), d9, toRadians, d3, d5, d2, sqrt, atan2);
        }

        private static void arcToBezier(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
            double d10 = d3;
            int ceil = (int) Math.ceil(Math.abs((d9 * 4.0d) / 3.141592653589793d));
            double cos = Math.cos(d7);
            double sin = Math.sin(d7);
            double cos2 = Math.cos(d8);
            double sin2 = Math.sin(d8);
            double d11 = -d10;
            double d12 = d11 * cos;
            double d13 = d4 * sin;
            d11 *= sin;
            double d14 = d4 * cos;
            sin2 = (sin2 * d11) + (cos2 * d14);
            double d15 = d9 / ((double) ceil);
            int i = 0;
            double d16 = d6;
            double d17 = sin2;
            double d18 = (d12 * sin2) - (d13 * cos2);
            double d19 = d5;
            double d20 = d8;
            while (i < ceil) {
                double d21 = d11;
                d11 = d20 + d15;
                double sin3 = Math.sin(d11);
                double cos3 = Math.cos(d11);
                double d22 = d15;
                d15 = (d + ((d10 * cos) * cos3)) - (d13 * sin3);
                d10 = (d2 + ((d10 * sin) * cos3)) + (d14 * sin3);
                double d23 = (d12 * sin3) - (d13 * cos3);
                sin3 = (sin3 * d21) + (cos3 * d14);
                d20 = d11 - d20;
                double d24 = d14;
                d14 = Math.tan(d20 / 2.0d);
                double d25 = d11;
                d20 = (Math.sin(d20) * (Math.sqrt(((d14 * 3.0d) * d14) + 4.0d) - 1.0d)) / 3.0d;
                d11 = d19 + (d18 * d20);
                d14 = d16 + (d17 * d20);
                int i2 = ceil;
                double d26 = cos;
                double d27 = d15 - (d20 * d23);
                d20 = d10 - (d20 * sin3);
                double d28 = sin;
                Path path2 = path;
                path2.rLineTo(0.0f, 0.0f);
                path2.cubicTo((float) d11, (float) d14, (float) d27, (float) d20, (float) d15, (float) d10);
                i++;
                d16 = d10;
                d19 = d15;
                d11 = d21;
                d17 = sin3;
                d18 = d23;
                d15 = d22;
                d14 = d24;
                d20 = d25;
                ceil = i2;
                cos = d26;
                sin = d28;
                d10 = d3;
            }
        }
    }

    static float[] copyOfRange(float[] fArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (i >= 0) {
            if (i <= length) {
                i2 -= i;
                length = Math.min(i2, length - i);
                i2 = new float[i2];
                System.arraycopy(fArr, i, i2, 0, length);
                return i2;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        PathDataNode[] createNodesFromPathData = createNodesFromPathData(str);
        if (createNodesFromPathData == null) {
            return null;
        }
        try {
            PathDataNode.nodesToPath(createNodesFromPathData, path);
            return path;
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error in parsing ");
            stringBuilder.append(str);
            throw new RuntimeException(stringBuilder.toString(), e);
        }
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 0;
        while (i < str.length()) {
            i = nextStart(str, i);
            String trim = str.substring(i2, i).trim();
            if (trim.length() > 0) {
                addNode(arrayList, trim.charAt(0), getFloats(trim));
            }
            i2 = i;
            i++;
        }
        if (i - i2 == 1 && i2 < str.length()) {
            addNode(arrayList, str.charAt(i2), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[arrayList.size()]);
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        if (pathDataNodeArr == null) {
            return null;
        }
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            pathDataNodeArr2[i] = new PathDataNode(pathDataNodeArr[i]);
        }
        return pathDataNodeArr2;
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr != null) {
            if (pathDataNodeArr2 != null) {
                if (pathDataNodeArr.length != pathDataNodeArr2.length) {
                    return false;
                }
                int i = 0;
                while (i < pathDataNodeArr.length) {
                    if (pathDataNodeArr[i].mType == pathDataNodeArr2[i].mType) {
                        if (pathDataNodeArr[i].mParams.length == pathDataNodeArr2[i].mParams.length) {
                            i++;
                        }
                    }
                    return false;
                }
                return 1;
            }
        }
        return false;
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int i = 0; i < pathDataNodeArr2.length; i++) {
            pathDataNodeArr[i].mType = pathDataNodeArr2[i].mType;
            for (int i2 = 0; i2 < pathDataNodeArr2[i].mParams.length; i2++) {
                pathDataNodeArr[i].mParams[i2] = pathDataNodeArr2[i].mParams[i2];
            }
        }
    }

    private static int nextStart(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (((charAt - 65) * (charAt - 90) <= 0 || (charAt - 97) * (charAt - 122) <= 0) && charAt != 'e' && charAt != 'E') {
                return i;
            }
            i++;
        }
        return i;
    }

    private static void addNode(ArrayList<PathDataNode> arrayList, char c, float[] fArr) {
        arrayList.add(new PathDataNode(c, fArr));
    }

    private static float[] getFloats(String str) {
        if (str.charAt(0) != 'z') {
            if (str.charAt(0) != 'Z') {
                try {
                    float[] fArr = new float[str.length()];
                    ExtractFloatResult extractFloatResult = new ExtractFloatResult();
                    int length = str.length();
                    int i = 1;
                    int i2 = 0;
                    while (i < length) {
                        extract(str, i, extractFloatResult);
                        int i3 = extractFloatResult.mEndPosition;
                        if (i < i3) {
                            int i4 = i2 + 1;
                            fArr[i2] = Float.parseFloat(str.substring(i, i3));
                            i2 = i4;
                        }
                        i = extractFloatResult.mEndWithNegOrDot ? i3 : i3 + 1;
                    }
                    return copyOfRange(fArr, 0, i2);
                } catch (Throwable e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("error in parsing \"");
                    stringBuilder.append(str);
                    stringBuilder.append("\"");
                    throw new RuntimeException(stringBuilder.toString(), e);
                }
            }
        }
        return new float[0];
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void extract(java.lang.String r8, int r9, android.support.v4.graphics.PathParser.ExtractFloatResult r10) {
        /*
        r0 = 0;
        r10.mEndWithNegOrDot = r0;
        r1 = r9;
        r2 = 0;
        r3 = 0;
        r4 = 0;
    L_0x0007:
        r5 = r8.length();
        if (r1 >= r5) goto L_0x003d;
    L_0x000d:
        r5 = r8.charAt(r1);
        r6 = 32;
        r7 = 1;
        if (r5 == r6) goto L_0x0035;
    L_0x0016:
        r6 = 69;
        if (r5 == r6) goto L_0x0033;
    L_0x001a:
        r6 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r5 == r6) goto L_0x0033;
    L_0x001e:
        switch(r5) {
            case 44: goto L_0x0035;
            case 45: goto L_0x002a;
            case 46: goto L_0x0022;
            default: goto L_0x0021;
        };
    L_0x0021:
        goto L_0x0031;
    L_0x0022:
        if (r3 != 0) goto L_0x0027;
    L_0x0024:
        r2 = 0;
        r3 = 1;
        goto L_0x0037;
    L_0x0027:
        r10.mEndWithNegOrDot = r7;
        goto L_0x0035;
    L_0x002a:
        if (r1 == r9) goto L_0x0031;
    L_0x002c:
        if (r2 != 0) goto L_0x0031;
    L_0x002e:
        r10.mEndWithNegOrDot = r7;
        goto L_0x0035;
    L_0x0031:
        r2 = 0;
        goto L_0x0037;
    L_0x0033:
        r2 = 1;
        goto L_0x0037;
    L_0x0035:
        r2 = 0;
        r4 = 1;
    L_0x0037:
        if (r4 == 0) goto L_0x003a;
    L_0x0039:
        goto L_0x003d;
    L_0x003a:
        r1 = r1 + 1;
        goto L_0x0007;
    L_0x003d:
        r10.mEndPosition = r1;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.PathParser.extract(java.lang.String, int, android.support.v4.graphics.PathParser$ExtractFloatResult):void");
    }
}
