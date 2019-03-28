package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatUPCEANReader extends OneDReader {
    private final UPCEANReader[] readers;

    public MultiFormatUPCEANReader(Map<DecodeHintType, ?> map) {
        if (map == null) {
            map = null;
        } else {
            map = (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        }
        Collection arrayList = new ArrayList();
        if (map != null) {
            if (map.contains(BarcodeFormat.EAN_13)) {
                arrayList.add(new EAN13Reader());
            } else if (map.contains(BarcodeFormat.UPC_A)) {
                arrayList.add(new UPCAReader());
            }
            if (map.contains(BarcodeFormat.EAN_8)) {
                arrayList.add(new EAN8Reader());
            }
            if (map.contains(BarcodeFormat.UPC_E) != null) {
                arrayList.add(new UPCEReader());
            }
        }
        if (arrayList.isEmpty() != null) {
            arrayList.add(new EAN13Reader());
            arrayList.add(new EAN8Reader());
            arrayList.add(new UPCEReader());
        }
        this.readers = (UPCEANReader[]) arrayList.toArray(new UPCEANReader[arrayList.size()]);
    }

    public com.google.zxing.Result decodeRow(int r7, com.google.zxing.common.BitArray r8, java.util.Map<com.google.zxing.DecodeHintType, ?> r9) throws com.google.zxing.NotFoundException {
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
        r6 = this;
        r0 = com.google.zxing.oned.UPCEANReader.findStartGuardPattern(r8);
        r1 = r6.readers;
        r2 = r1.length;
        r3 = 0;
        r4 = 0;
    L_0x0009:
        if (r4 >= r2) goto L_0x0067;
    L_0x000b:
        r5 = r1[r4];
        r5 = r5.decodeRow(r7, r8, r0, r9);	 Catch:{ ReaderException -> 0x0064 }
        r7 = r5.getBarcodeFormat();
        r8 = com.google.zxing.BarcodeFormat.EAN_13;
        r0 = 1;
        if (r7 != r8) goto L_0x0028;
    L_0x001a:
        r7 = r5.getText();
        r7 = r7.charAt(r3);
        r8 = 48;
        if (r7 != r8) goto L_0x0028;
    L_0x0026:
        r7 = 1;
        goto L_0x0029;
    L_0x0028:
        r7 = 0;
    L_0x0029:
        if (r9 != 0) goto L_0x002d;
    L_0x002b:
        r8 = 0;
        goto L_0x0035;
    L_0x002d:
        r8 = com.google.zxing.DecodeHintType.POSSIBLE_FORMATS;
        r8 = r9.get(r8);
        r8 = (java.util.Collection) r8;
    L_0x0035:
        if (r8 == 0) goto L_0x003f;
    L_0x0037:
        r9 = com.google.zxing.BarcodeFormat.UPC_A;
        r8 = r8.contains(r9);
        if (r8 == 0) goto L_0x0040;
    L_0x003f:
        r3 = 1;
    L_0x0040:
        if (r7 == 0) goto L_0x0063;
    L_0x0042:
        if (r3 == 0) goto L_0x0063;
    L_0x0044:
        r7 = new com.google.zxing.Result;
        r8 = r5.getText();
        r8 = r8.substring(r0);
        r9 = r5.getRawBytes();
        r0 = r5.getResultPoints();
        r1 = com.google.zxing.BarcodeFormat.UPC_A;
        r7.<init>(r8, r9, r0, r1);
        r8 = r5.getResultMetadata();
        r7.putAllMetadata(r8);
        return r7;
    L_0x0063:
        return r5;
    L_0x0064:
        r4 = r4 + 1;
        goto L_0x0009;
    L_0x0067:
        r7 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.MultiFormatUPCEANReader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }

    public void reset() {
        for (Reader reset : this.readers) {
            reset.reset();
        }
    }
}
