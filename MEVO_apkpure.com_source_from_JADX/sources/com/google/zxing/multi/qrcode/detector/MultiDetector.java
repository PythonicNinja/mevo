package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.detector.Detector;

public final class MultiDetector extends Detector {
    private static final DetectorResult[] EMPTY_DETECTOR_RESULTS = new DetectorResult[0];

    public MultiDetector(BitMatrix bitMatrix) {
        super(bitMatrix);
    }

    public com.google.zxing.common.DetectorResult[] detectMulti(java.util.Map<com.google.zxing.DecodeHintType, ?> r5) throws com.google.zxing.NotFoundException {
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
        r4 = this;
        r0 = r4.getImage();
        if (r5 != 0) goto L_0x0008;
    L_0x0006:
        r1 = 0;
        goto L_0x0010;
    L_0x0008:
        r1 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;
        r1 = r5.get(r1);
        r1 = (com.google.zxing.ResultPointCallback) r1;
    L_0x0010:
        r2 = new com.google.zxing.multi.qrcode.detector.MultiFinderPatternFinder;
        r2.<init>(r0, r1);
        r5 = r2.findMulti(r5);
        r0 = r5.length;
        if (r0 != 0) goto L_0x0021;
    L_0x001c:
        r5 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r5;
    L_0x0021:
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r5.length;
        r2 = 0;
    L_0x0028:
        if (r2 >= r1) goto L_0x0036;
    L_0x002a:
        r3 = r5[r2];
        r3 = r4.processFinderPatternInfo(r3);	 Catch:{ ReaderException -> 0x0033 }
        r0.add(r3);	 Catch:{ ReaderException -> 0x0033 }
    L_0x0033:
        r2 = r2 + 1;
        goto L_0x0028;
    L_0x0036:
        r5 = r0.isEmpty();
        if (r5 == 0) goto L_0x003f;
    L_0x003c:
        r5 = EMPTY_DETECTOR_RESULTS;
        return r5;
    L_0x003f:
        r5 = r0.size();
        r5 = new com.google.zxing.common.DetectorResult[r5];
        r5 = r0.toArray(r5);
        r5 = (com.google.zxing.common.DetectorResult[]) r5;
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.multi.qrcode.detector.MultiDetector.detectMulti(java.util.Map):com.google.zxing.common.DetectorResult[]");
    }
}
