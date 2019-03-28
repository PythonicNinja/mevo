package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {
    private static final int MAX_DEPTH = 4;
    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader delegate;

    public GenericMultipleBarcodeReader(Reader reader) {
        this.delegate = reader;
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) throws NotFoundException {
        return decodeMultiple(binaryBitmap, null);
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        List arrayList = new ArrayList();
        doDecodeMultiple(binaryBitmap, map, arrayList, 0, 0, 0);
        if (arrayList.isEmpty() == null) {
            return (Result[]) arrayList.toArray(new Result[arrayList.size()]);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void doDecodeMultiple(com.google.zxing.BinaryBitmap r23, java.util.Map<com.google.zxing.DecodeHintType, ?> r24, java.util.List<com.google.zxing.Result> r25, int r26, int r27, int r28) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r22 = this;
        r0 = r23;
        r8 = r26;
        r9 = r27;
        r10 = r28;
        r1 = 4;
        if (r10 <= r1) goto L_0x000c;
    L_0x000b:
        return;
    L_0x000c:
        r11 = r22;
        r1 = r11.delegate;	 Catch:{ ReaderException -> 0x010e }
        r12 = r24;	 Catch:{ ReaderException -> 0x010e }
        r1 = r1.decode(r0, r12);	 Catch:{ ReaderException -> 0x010e }
        r2 = r25.iterator();
    L_0x001a:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x0036;
    L_0x0020:
        r3 = r2.next();
        r3 = (com.google.zxing.Result) r3;
        r3 = r3.getText();
        r4 = r1.getText();
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x001a;
    L_0x0034:
        r2 = 1;
        goto L_0x0037;
    L_0x0036:
        r2 = 0;
    L_0x0037:
        if (r2 != 0) goto L_0x0043;
    L_0x0039:
        r2 = translateResultPoints(r1, r8, r9);
        r15 = r25;
        r15.add(r2);
        goto L_0x0045;
    L_0x0043:
        r15 = r25;
    L_0x0045:
        r1 = r1.getResultPoints();
        if (r1 == 0) goto L_0x010d;
    L_0x004b:
        r2 = r1.length;
        if (r2 != 0) goto L_0x0050;
    L_0x004e:
        goto L_0x010d;
    L_0x0050:
        r7 = r23.getWidth();
        r6 = r23.getHeight();
        r2 = (float) r7;
        r3 = (float) r6;
        r4 = r1.length;
        r5 = 0;
        r5 = r3;
        r13 = 0;
        r14 = 0;
        r3 = r2;
        r2 = 0;
    L_0x0061:
        if (r2 >= r4) goto L_0x008d;
    L_0x0063:
        r16 = r4;
        r4 = r1[r2];
        if (r4 != 0) goto L_0x006a;
    L_0x0069:
        goto L_0x0088;
    L_0x006a:
        r17 = r4.getX();
        r4 = r4.getY();
        r18 = (r17 > r3 ? 1 : (r17 == r3 ? 0 : -1));
        if (r18 >= 0) goto L_0x0078;
    L_0x0076:
        r3 = r17;
    L_0x0078:
        r18 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1));
        if (r18 >= 0) goto L_0x007d;
    L_0x007c:
        r5 = r4;
    L_0x007d:
        r18 = (r17 > r13 ? 1 : (r17 == r13 ? 0 : -1));
        if (r18 <= 0) goto L_0x0083;
    L_0x0081:
        r13 = r17;
    L_0x0083:
        r17 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1));
        if (r17 <= 0) goto L_0x0088;
    L_0x0087:
        r14 = r4;
    L_0x0088:
        r2 = r2 + 1;
        r4 = r16;
        goto L_0x0061;
    L_0x008d:
        r16 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r1 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1));
        if (r1 <= 0) goto L_0x00af;
    L_0x0093:
        r1 = (int) r3;
        r2 = 0;
        r3 = r0.crop(r2, r2, r1, r6);
        r17 = r10 + 1;
        r1 = r11;
        r2 = r3;
        r3 = r12;
        r4 = r15;
        r19 = r14;
        r14 = r5;
        r5 = r8;
        r20 = r6;
        r6 = r9;
        r21 = r13;
        r13 = r7;
        r7 = r17;
        r1.doDecodeMultiple(r2, r3, r4, r5, r6, r7);
        goto L_0x00b7;
    L_0x00af:
        r20 = r6;
        r21 = r13;
        r19 = r14;
        r14 = r5;
        r13 = r7;
    L_0x00b7:
        r1 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r1 <= 0) goto L_0x00cc;
    L_0x00bb:
        r1 = (int) r14;
        r2 = 0;
        r3 = r0.crop(r2, r2, r13, r1);
        r7 = r10 + 1;
        r1 = r11;
        r2 = r3;
        r3 = r12;
        r4 = r15;
        r5 = r8;
        r6 = r9;
        r1.doDecodeMultiple(r2, r3, r4, r5, r6, r7);
    L_0x00cc:
        r7 = r13 + -100;
        r1 = (float) r7;
        r1 = (r21 > r1 ? 1 : (r21 == r1 ? 0 : -1));
        if (r1 >= 0) goto L_0x00ec;
    L_0x00d3:
        r5 = r21;
        r1 = (int) r5;
        r7 = r13 - r1;
        r14 = r20;
        r2 = 0;
        r3 = r0.crop(r1, r2, r7, r14);
        r5 = r8 + r1;
        r7 = r10 + 1;
        r1 = r11;
        r2 = r3;
        r3 = r12;
        r4 = r15;
        r6 = r9;
        r1.doDecodeMultiple(r2, r3, r4, r5, r6, r7);
        goto L_0x00ee;
    L_0x00ec:
        r14 = r20;
    L_0x00ee:
        r6 = r14 + -100;
        r1 = (float) r6;
        r1 = (r19 > r1 ? 1 : (r19 == r1 ? 0 : -1));
        if (r1 >= 0) goto L_0x010c;
    L_0x00f5:
        r5 = r19;
        r1 = (int) r5;
        r6 = r14 - r1;
        r2 = 0;
        r2 = r0.crop(r2, r1, r13, r6);
        r5 = r9 + r1;
        r0 = 1;
        r6 = r10 + 1;
        r0 = r11;
        r1 = r2;
        r2 = r12;
        r3 = r15;
        r4 = r8;
        r0.doDecodeMultiple(r1, r2, r3, r4, r5, r6);
    L_0x010c:
        return;
    L_0x010d:
        return;
    L_0x010e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.multi.GenericMultipleBarcodeReader.doDecodeMultiple(com.google.zxing.BinaryBitmap, java.util.Map, java.util.List, int, int, int):void");
    }

    private static Result translateResultPoints(Result result, int i, int i2) {
        ResultPoint[] resultPoints = result.getResultPoints();
        if (resultPoints == null) {
            return result;
        }
        ResultPoint[] resultPointArr = new ResultPoint[resultPoints.length];
        for (int i3 = 0; i3 < resultPoints.length; i3++) {
            ResultPoint resultPoint = resultPoints[i3];
            if (resultPoint != null) {
                resultPointArr[i3] = new ResultPoint(resultPoint.getX() + ((float) i), resultPoint.getY() + ((float) i2));
            }
        }
        i = new Result(result.getText(), result.getRawBytes(), resultPointArr, result.getBarcodeFormat());
        i.putAllMetadata(result.getResultMetadata());
        return i;
    }
}
