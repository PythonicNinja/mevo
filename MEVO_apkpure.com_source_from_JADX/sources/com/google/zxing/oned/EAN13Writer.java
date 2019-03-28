package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class EAN13Writer extends UPCEANWriter {
    private static final int CODE_WIDTH = 95;

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.EAN_13) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        i = new StringBuilder();
        i.append("Can only encode EAN_13, but got ");
        i.append(barcodeFormat);
        throw new IllegalArgumentException(i.toString());
    }

    public boolean[] encode(java.lang.String r10) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r9 = this;
        r0 = r10.length();
        r1 = 13;
        if (r0 == r1) goto L_0x0023;
    L_0x0008:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Requested contents should be 13 digits long, but got ";
        r1.append(r2);
        r10 = r10.length();
        r1.append(r10);
        r10 = r1.toString();
        r0.<init>(r10);
        throw r0;
    L_0x0023:
        r0 = com.google.zxing.oned.UPCEANReader.checkStandardUPCEANChecksum(r10);	 Catch:{ FormatException -> 0x0093 }
        if (r0 != 0) goto L_0x0031;	 Catch:{ FormatException -> 0x0093 }
    L_0x0029:
        r10 = new java.lang.IllegalArgumentException;	 Catch:{ FormatException -> 0x0093 }
        r0 = "Contents do not pass checksum";	 Catch:{ FormatException -> 0x0093 }
        r10.<init>(r0);	 Catch:{ FormatException -> 0x0093 }
        throw r10;	 Catch:{ FormatException -> 0x0093 }
    L_0x0031:
        r0 = 0;
        r1 = 1;
        r2 = r10.substring(r0, r1);
        r2 = java.lang.Integer.parseInt(r2);
        r3 = com.google.zxing.oned.EAN13Reader.FIRST_DIGIT_ENCODINGS;
        r2 = r3[r2];
        r3 = 95;
        r3 = new boolean[r3];
        r4 = com.google.zxing.oned.UPCEANReader.START_END_PATTERN;
        r4 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r3, r0, r4, r1);
        r4 = r4 + r0;
        r5 = r4;
        r4 = 1;
    L_0x004c:
        r6 = 6;
        if (r4 > r6) goto L_0x006c;
    L_0x004f:
        r7 = r4 + 1;
        r8 = r10.substring(r4, r7);
        r8 = java.lang.Integer.parseInt(r8);
        r6 = r6 - r4;
        r4 = r2 >> r6;
        r4 = r4 & r1;
        if (r4 != r1) goto L_0x0061;
    L_0x005f:
        r8 = r8 + 10;
    L_0x0061:
        r4 = com.google.zxing.oned.UPCEANReader.L_AND_G_PATTERNS;
        r4 = r4[r8];
        r4 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r3, r5, r4, r0);
        r5 = r5 + r4;
        r4 = r7;
        goto L_0x004c;
    L_0x006c:
        r2 = com.google.zxing.oned.UPCEANReader.MIDDLE_PATTERN;
        r0 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r3, r5, r2, r0);
        r5 = r5 + r0;
        r0 = 7;
    L_0x0074:
        r2 = 12;
        if (r0 > r2) goto L_0x008d;
    L_0x0078:
        r2 = r0 + 1;
        r0 = r10.substring(r0, r2);
        r0 = java.lang.Integer.parseInt(r0);
        r4 = com.google.zxing.oned.UPCEANReader.L_PATTERNS;
        r0 = r4[r0];
        r0 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r3, r5, r0, r1);
        r5 = r5 + r0;
        r0 = r2;
        goto L_0x0074;
    L_0x008d:
        r10 = com.google.zxing.oned.UPCEANReader.START_END_PATTERN;
        com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r3, r5, r10, r1);
        return r3;
    L_0x0093:
        r10 = new java.lang.IllegalArgumentException;
        r0 = "Illegal contents";
        r10.<init>(r0);
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.EAN13Writer.encode(java.lang.String):boolean[]");
    }
}
