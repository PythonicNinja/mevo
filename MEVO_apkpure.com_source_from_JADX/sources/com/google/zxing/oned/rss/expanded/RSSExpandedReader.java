package com.google.zxing.oned.rss.expanded;

import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.OneDReader;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.RSSUtils;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class RSSExpandedReader extends AbstractRSSReader {
    private static final int[] EVEN_TOTAL_SUBSET = new int[]{4, 20, 52, 104, 204};
    private static final int[][] FINDER_PATTERNS = new int[][]{new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] FINDER_PATTERN_SEQUENCES = new int[][]{new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    private static final int FINDER_PAT_A = 0;
    private static final int FINDER_PAT_B = 1;
    private static final int FINDER_PAT_C = 2;
    private static final int FINDER_PAT_D = 3;
    private static final int FINDER_PAT_E = 4;
    private static final int FINDER_PAT_F = 5;
    private static final int[] GSUM = new int[]{0, 348, 1388, 2948, 3988};
    private static final int MAX_PAIRS = 11;
    private static final int[] SYMBOL_WIDEST = new int[]{7, 5, 4, 3, 1};
    private static final int[][] WEIGHTS = new int[][]{new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, 145, 13, 39, 117, 140, 209, 205}, new int[]{193, 157, 49, 147, 19, 57, 171, 91}, new int[]{62, 186, 136, 197, 169, 85, 44, 132}, new int[]{185, 133, 188, 142, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, 158, 52, 156}, new int[]{46, 138, 203, 187, 139, 206, 196, 166}, new int[]{76, 17, 51, 153, 37, 111, 122, 155}, new int[]{43, GmsClientSupervisor.DEFAULT_BIND_FLAGS, 176, 106, 107, 110, 119, 146}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, 200, 178, 112, 125, 164}, new int[]{70, 210, 208, 202, 184, 130, 179, 115}, new int[]{134, 191, 151, 31, 93, 68, 204, 190}, new int[]{148, 22, 66, 198, 172, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{120, 149, 25, 75, 14, 42, 126, 167}, new int[]{79, 26, 78, 23, 69, 207, 199, 175}, new int[]{103, 98, 83, 38, 114, 131, 182, 124}, new int[]{161, 61, 183, 127, 170, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, 194, 160, 58, 174, 100, 89}};
    private final List<ExpandedPair> pairs = new ArrayList(11);
    private final List<ExpandedRow> rows = new ArrayList();
    private final int[] startEnd = new int[2];
    private boolean startFromEven;

    public com.google.zxing.Result decodeRow(int r1, com.google.zxing.common.BitArray r2, java.util.Map<com.google.zxing.DecodeHintType, ?> r3) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException {
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
        r0 = this;
        r3 = r0.pairs;
        r3.clear();
        r3 = 0;
        r0.startFromEven = r3;
        r3 = r0.decodeRow2pairs(r1, r2);	 Catch:{ NotFoundException -> 0x0011 }
        r3 = constructResult(r3);	 Catch:{ NotFoundException -> 0x0011 }
        return r3;
    L_0x0011:
        r3 = r0.pairs;
        r3.clear();
        r3 = 1;
        r0.startFromEven = r3;
        r1 = r0.decodeRow2pairs(r1, r2);
        r1 = constructResult(r1);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }

    public void reset() {
        this.pairs.clear();
        this.rows.clear();
    }

    List<ExpandedPair> decodeRow2pairs(int i, BitArray bitArray) throws NotFoundException {
        while (true) {
            try {
                this.pairs.add(retrieveNextPair(bitArray, this.pairs, i));
            } catch (BitArray bitArray2) {
                if (this.pairs.isEmpty()) {
                    throw bitArray2;
                } else if (checkChecksum() != null) {
                    return this.pairs;
                } else {
                    bitArray2 = this.rows.isEmpty() ^ 1;
                    storeRow(i, false);
                    if (bitArray2 != null) {
                        i = checkRows(false);
                        if (i != 0) {
                            return i;
                        }
                        i = checkRows(true);
                        if (i != 0) {
                            return i;
                        }
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
    }

    private java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair> checkRows(boolean r4) {
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
        r3 = this;
        r0 = r3.rows;
        r0 = r0.size();
        r1 = 0;
        r2 = 25;
        if (r0 <= r2) goto L_0x0011;
    L_0x000b:
        r4 = r3.rows;
        r4.clear();
        return r1;
    L_0x0011:
        r0 = r3.pairs;
        r0.clear();
        if (r4 == 0) goto L_0x001d;
    L_0x0018:
        r0 = r3.rows;
        java.util.Collections.reverse(r0);
    L_0x001d:
        r0 = new java.util.ArrayList;	 Catch:{ NotFoundException -> 0x0028 }
        r0.<init>();	 Catch:{ NotFoundException -> 0x0028 }
        r2 = 0;	 Catch:{ NotFoundException -> 0x0028 }
        r0 = r3.checkRows(r0, r2);	 Catch:{ NotFoundException -> 0x0028 }
        goto L_0x0029;
    L_0x0028:
        r0 = r1;
    L_0x0029:
        if (r4 == 0) goto L_0x0030;
    L_0x002b:
        r4 = r3.rows;
        java.util.Collections.reverse(r4);
    L_0x0030:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.checkRows(boolean):java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair>");
    }

    private java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair> checkRows(java.util.List<com.google.zxing.oned.rss.expanded.ExpandedRow> r6, int r7) throws com.google.zxing.NotFoundException {
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
        r5 = this;
    L_0x0000:
        r0 = r5.rows;
        r0 = r0.size();
        if (r7 >= r0) goto L_0x005e;
    L_0x0008:
        r0 = r5.rows;
        r0 = r0.get(r7);
        r0 = (com.google.zxing.oned.rss.expanded.ExpandedRow) r0;
        r1 = r5.pairs;
        r1.clear();
        r1 = r6.size();
        r2 = 0;
    L_0x001a:
        if (r2 >= r1) goto L_0x002e;
    L_0x001c:
        r3 = r5.pairs;
        r4 = r6.get(r2);
        r4 = (com.google.zxing.oned.rss.expanded.ExpandedRow) r4;
        r4 = r4.getPairs();
        r3.addAll(r4);
        r2 = r2 + 1;
        goto L_0x001a;
    L_0x002e:
        r1 = r5.pairs;
        r2 = r0.getPairs();
        r1.addAll(r2);
        r1 = r5.pairs;
        r1 = isValidSequence(r1);
        if (r1 != 0) goto L_0x0040;
    L_0x003f:
        goto L_0x005b;
    L_0x0040:
        r1 = r5.checkChecksum();
        if (r1 == 0) goto L_0x0049;
    L_0x0046:
        r6 = r5.pairs;
        return r6;
    L_0x0049:
        r1 = new java.util.ArrayList;
        r1.<init>();
        r1.addAll(r6);
        r1.add(r0);
        r0 = r7 + 1;
        r0 = r5.checkRows(r1, r0);	 Catch:{ NotFoundException -> 0x005b }
        return r0;
    L_0x005b:
        r7 = r7 + 1;
        goto L_0x0000;
    L_0x005e:
        r6 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.checkRows(java.util.List, int):java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair>");
    }

    private static boolean isValidSequence(List<ExpandedPair> list) {
        for (int[] iArr : FINDER_PATTERN_SEQUENCES) {
            if (list.size() <= iArr.length) {
                Object obj;
                for (int i = 0; i < list.size(); i++) {
                    if (((ExpandedPair) list.get(i)).getFinderPattern().getValue() != iArr[i]) {
                        obj = null;
                        break;
                    }
                }
                obj = 1;
                if (obj != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private void storeRow(int i, boolean z) {
        boolean z2 = false;
        int i2 = 0;
        boolean z3 = false;
        while (i2 < this.rows.size()) {
            ExpandedRow expandedRow = (ExpandedRow) this.rows.get(i2);
            if (expandedRow.getRowNumber() > i) {
                z2 = expandedRow.isEquivalent(this.pairs);
                break;
            } else {
                z3 = expandedRow.isEquivalent(this.pairs);
                i2++;
            }
        }
        if (!z2) {
            if (!z3) {
                if (!isPartialRow(this.pairs, this.rows)) {
                    this.rows.add(i2, new ExpandedRow(this.pairs, i, z));
                    removePartialRows(this.pairs, this.rows);
                }
            }
        }
    }

    private static void removePartialRows(List<ExpandedPair> list, List<ExpandedRow> list2) {
        list2 = list2.iterator();
        while (list2.hasNext()) {
            ExpandedRow expandedRow = (ExpandedRow) list2.next();
            if (expandedRow.getPairs().size() != list.size()) {
                Object obj;
                Iterator it = expandedRow.getPairs().iterator();
                Object obj2;
                do {
                    obj = null;
                    obj2 = 1;
                    if (!it.hasNext()) {
                        obj = 1;
                        break;
                    }
                    ExpandedPair expandedPair = (ExpandedPair) it.next();
                    for (ExpandedPair equals : list) {
                        if (expandedPair.equals(equals)) {
                            continue;
                            break;
                        }
                    }
                    obj2 = null;
                    continue;
                } while (obj2 != null);
                if (obj != null) {
                    list2.remove();
                }
            }
        }
    }

    private static boolean isPartialRow(Iterable<ExpandedPair> iterable, Iterable<ExpandedRow> iterable2) {
        iterable2 = iterable2.iterator();
        boolean z;
        do {
            z = false;
            if (!iterable2.hasNext()) {
                return false;
            }
            ExpandedRow expandedRow = (ExpandedRow) iterable2.next();
            for (ExpandedPair expandedPair : iterable) {
                Object obj;
                for (ExpandedPair equals : expandedRow.getPairs()) {
                    if (expandedPair.equals(equals)) {
                        obj = 1;
                        continue;
                        break;
                    }
                }
                obj = null;
                continue;
                if (obj == null) {
                    continue;
                    break;
                }
            }
            z = true;
            continue;
        } while (!z);
        return true;
    }

    List<ExpandedRow> getRows() {
        return this.rows;
    }

    static Result constructResult(List<ExpandedPair> list) throws NotFoundException, FormatException {
        String parseInformation = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(list)).parseInformation();
        ResultPoint[] resultPoints = ((ExpandedPair) list.get(0)).getFinderPattern().getResultPoints();
        list = ((ExpandedPair) list.get(list.size() - 1)).getFinderPattern().getResultPoints();
        return new Result(parseInformation, null, new ResultPoint[]{resultPoints[0], resultPoints[1], list[0], list[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    private boolean checkChecksum() {
        boolean z = false;
        ExpandedPair expandedPair = (ExpandedPair) this.pairs.get(0);
        DataCharacter leftChar = expandedPair.getLeftChar();
        DataCharacter rightChar = expandedPair.getRightChar();
        if (rightChar == null) {
            return false;
        }
        int checksumPortion = rightChar.getChecksumPortion();
        int i = 2;
        for (int i2 = 1; i2 < this.pairs.size(); i2++) {
            ExpandedPair expandedPair2 = (ExpandedPair) this.pairs.get(i2);
            checksumPortion += expandedPair2.getLeftChar().getChecksumPortion();
            i++;
            DataCharacter rightChar2 = expandedPair2.getRightChar();
            if (rightChar2 != null) {
                checksumPortion += rightChar2.getChecksumPortion();
                i++;
            }
        }
        if (((i - 4) * 211) + (checksumPortion % 211) == leftChar.getValue()) {
            z = true;
        }
        return z;
    }

    private static int getNextSecondBar(BitArray bitArray, int i) {
        if (bitArray.get(i)) {
            return bitArray.getNextSet(bitArray.getNextUnset(i));
        }
        return bitArray.getNextUnset(bitArray.getNextSet(i));
    }

    com.google.zxing.oned.rss.expanded.ExpandedPair retrieveNextPair(com.google.zxing.common.BitArray r7, java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair> r8, int r9) throws com.google.zxing.NotFoundException {
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
        r0 = r8.size();
        r0 = r0 % 2;
        r1 = 0;
        r2 = 1;
        if (r0 != 0) goto L_0x000c;
    L_0x000a:
        r0 = 1;
        goto L_0x000d;
    L_0x000c:
        r0 = 0;
    L_0x000d:
        r3 = r6.startFromEven;
        if (r3 == 0) goto L_0x0013;
    L_0x0011:
        r0 = r0 ^ 1;
    L_0x0013:
        r3 = -1;
        r4 = 1;
    L_0x0015:
        r6.findNextPair(r7, r8, r3);
        r5 = r6.parseFoundFinderPattern(r7, r9, r0);
        if (r5 != 0) goto L_0x0027;
    L_0x001e:
        r3 = r6.startEnd;
        r3 = r3[r1];
        r3 = getNextSecondBar(r7, r3);
        goto L_0x0028;
    L_0x0027:
        r4 = 0;
    L_0x0028:
        if (r4 != 0) goto L_0x0015;
    L_0x002a:
        r9 = r6.decodeDataCharacter(r7, r5, r0, r2);
        r3 = r8.isEmpty();
        if (r3 != 0) goto L_0x004a;
    L_0x0034:
        r3 = r8.size();
        r3 = r3 - r2;
        r8 = r8.get(r3);
        r8 = (com.google.zxing.oned.rss.expanded.ExpandedPair) r8;
        r8 = r8.mustBeLast();
        if (r8 == 0) goto L_0x004a;
    L_0x0045:
        r7 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r7;
    L_0x004a:
        r7 = r6.decodeDataCharacter(r7, r5, r0, r1);	 Catch:{ NotFoundException -> 0x004f }
        goto L_0x0050;
    L_0x004f:
        r7 = 0;
    L_0x0050:
        r8 = new com.google.zxing.oned.rss.expanded.ExpandedPair;
        r8.<init>(r9, r7, r5, r2);
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.retrieveNextPair(com.google.zxing.common.BitArray, java.util.List, int):com.google.zxing.oned.rss.expanded.ExpandedPair");
    }

    private void findNextPair(BitArray bitArray, List<ExpandedPair> list, int i) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        int size = bitArray.getSize();
        if (i < 0) {
            if (list.isEmpty() != 0) {
                i = 0;
            } else {
                i = ((ExpandedPair) list.get(list.size() - 1)).getFinderPattern().getStartEnd()[1];
            }
        }
        list = list.size() % 2 != null ? true : null;
        if (this.startFromEven) {
            list ^= 1;
        }
        int i2 = 0;
        while (i < size) {
            i2 = bitArray.get(i) ^ 1;
            if (i2 == 0) {
                break;
            }
            i++;
        }
        int i3 = i;
        int i4 = 0;
        while (i < size) {
            if ((bitArray.get(i) ^ i2) != 0) {
                decodeFinderCounters[i4] = decodeFinderCounters[i4] + 1;
            } else {
                if (i4 == 3) {
                    if (list != null) {
                        reverseCounters(decodeFinderCounters);
                    }
                    if (AbstractRSSReader.isFinderPattern(decodeFinderCounters)) {
                        this.startEnd[0] = i3;
                        this.startEnd[1] = i;
                        return;
                    }
                    if (list != null) {
                        reverseCounters(decodeFinderCounters);
                    }
                    i3 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    i4--;
                } else {
                    i4++;
                }
                decodeFinderCounters[i4] = 1;
                i2 = i2 == 0 ? 1 : 0;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void reverseCounters(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length / 2; i++) {
            int i2 = iArr[i];
            int i3 = (length - i) - 1;
            iArr[i] = iArr[i3];
            iArr[i3] = i2;
        }
    }

    private com.google.zxing.oned.rss.FinderPattern parseFoundFinderPattern(com.google.zxing.common.BitArray r9, int r10, boolean r11) {
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
        r8 = this;
        r0 = 0;
        r1 = 1;
        if (r11 == 0) goto L_0x001f;
    L_0x0004:
        r11 = r8.startEnd;
        r11 = r11[r0];
        r11 = r11 - r1;
    L_0x0009:
        if (r11 < 0) goto L_0x0014;
    L_0x000b:
        r2 = r9.get(r11);
        if (r2 != 0) goto L_0x0014;
    L_0x0011:
        r11 = r11 + -1;
        goto L_0x0009;
    L_0x0014:
        r11 = r11 + r1;
        r9 = r8.startEnd;
        r9 = r9[r0];
        r9 = r9 - r11;
        r2 = r8.startEnd;
        r2 = r2[r1];
        goto L_0x0032;
    L_0x001f:
        r11 = r8.startEnd;
        r11 = r11[r0];
        r2 = r8.startEnd;
        r2 = r2[r1];
        r2 = r2 + r1;
        r2 = r9.getNextUnset(r2);
        r9 = r8.startEnd;
        r9 = r9[r1];
        r9 = r2 - r9;
    L_0x0032:
        r5 = r11;
        r6 = r2;
        r11 = r8.getDecodeFinderCounters();
        r2 = r11.length;
        r2 = r2 - r1;
        java.lang.System.arraycopy(r11, r0, r11, r1, r2);
        r11[r0] = r9;
        r9 = FINDER_PATTERNS;	 Catch:{ NotFoundException -> 0x0054 }
        r3 = com.google.zxing.oned.rss.AbstractRSSReader.parseFinderValue(r11, r9);	 Catch:{ NotFoundException -> 0x0054 }
        r9 = new com.google.zxing.oned.rss.FinderPattern;
        r11 = 2;
        r4 = new int[r11];
        r4[r0] = r5;
        r4[r1] = r6;
        r2 = r9;
        r7 = r10;
        r2.<init>(r3, r4, r5, r6, r7);
        return r9;
    L_0x0054:
        r9 = 0;
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.parseFoundFinderPattern(com.google.zxing.common.BitArray, int, boolean):com.google.zxing.oned.rss.FinderPattern");
    }

    DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z, boolean z2) throws NotFoundException {
        int i;
        int length;
        BitArray bitArray2 = bitArray;
        int[] dataCharacterCounters = getDataCharacterCounters();
        dataCharacterCounters[0] = 0;
        dataCharacterCounters[1] = 0;
        dataCharacterCounters[2] = 0;
        dataCharacterCounters[3] = 0;
        dataCharacterCounters[4] = 0;
        dataCharacterCounters[5] = 0;
        dataCharacterCounters[6] = 0;
        dataCharacterCounters[7] = 0;
        if (z2) {
            OneDReader.recordPatternInReverse(bitArray2, finderPattern.getStartEnd()[0], dataCharacterCounters);
        } else {
            OneDReader.recordPattern(bitArray2, finderPattern.getStartEnd()[1], dataCharacterCounters);
            i = 0;
            for (length = dataCharacterCounters.length - 1; i < length; length--) {
                int i2 = dataCharacterCounters[i];
                dataCharacterCounters[i] = dataCharacterCounters[length];
                dataCharacterCounters[length] = i2;
                i++;
            }
        }
        float count = ((float) AbstractRSSReader.count(dataCharacterCounters)) / ((float) 17);
        float f = ((float) (finderPattern.getStartEnd()[1] - finderPattern.getStartEnd()[0])) / 15.0f;
        if (Math.abs(count - f) / f > 0.3f) {
            throw NotFoundException.getNotFoundInstance();
        }
        int[] oddCounts = getOddCounts();
        int[] evenCounts = getEvenCounts();
        float[] oddRoundingErrors = getOddRoundingErrors();
        float[] evenRoundingErrors = getEvenRoundingErrors();
        for (int i3 = 0; i3 < dataCharacterCounters.length; i3++) {
            float f2 = (((float) dataCharacterCounters[i3]) * 1.0f) / count;
            int i4 = (int) (0.5f + f2);
            if (i4 < 1) {
                if (f2 < 0.3f) {
                    throw NotFoundException.getNotFoundInstance();
                }
                i4 = 1;
            } else if (i4 > 8) {
                if (f2 > 8.7f) {
                    throw NotFoundException.getNotFoundInstance();
                }
                i4 = 8;
            }
            int i5 = i3 / 2;
            if ((i3 & 1) == 0) {
                oddCounts[i5] = i4;
                oddRoundingErrors[i5] = f2 - ((float) i4);
            } else {
                evenCounts[i5] = i4;
                evenRoundingErrors[i5] = f2 - ((float) i4);
            }
        }
        adjustOddEvenCounts(17);
        i = (((finderPattern.getValue() * 4) + (z ? 0 : 2)) + (z2 ^ 1)) - 1;
        i2 = 0;
        int i6 = 0;
        for (length = oddCounts.length - 1; length >= 0; length--) {
            if (isNotA1left(finderPattern, z, z2)) {
                i2 += oddCounts[length] * WEIGHTS[i][length * 2];
            }
            i6 += oddCounts[length];
        }
        int i7 = 0;
        for (length = evenCounts.length - 1; length >= 0; length--) {
            if (isNotA1left(finderPattern, z, z2)) {
                i7 += evenCounts[length] * WEIGHTS[i][(length * 2) + 1];
            }
        }
        i2 += i7;
        if ((i6 & 1) == 0 && i6 <= 13) {
            if (i6 >= 4) {
                i = (13 - i6) / 2;
                int i8 = SYMBOL_WIDEST[i];
                int i9 = 9 - i8;
                return new DataCharacter(((RSSUtils.getRSSvalue(oddCounts, i8, true) * EVEN_TOTAL_SUBSET[i]) + RSSUtils.getRSSvalue(evenCounts, i9, false)) + GSUM[i], i2);
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static boolean isNotA1left(FinderPattern finderPattern, boolean z, boolean z2) {
        if (finderPattern.getValue() == null && z) {
            if (z2) {
                return null;
            }
        }
        return true;
    }

    private void adjustOddEvenCounts(int i) throws NotFoundException {
        Object obj;
        Object obj2;
        Object obj3;
        int count = AbstractRSSReader.count(getOddCounts());
        int count2 = AbstractRSSReader.count(getEvenCounts());
        int i2 = (count + count2) - i;
        Object obj4 = null;
        i = (count & 1) == 1 ? 1 : 0;
        Object obj5 = (count2 & 1) == 0 ? 1 : null;
        if (count > 13) {
            obj = null;
            obj2 = 1;
        } else {
            obj = count < 4 ? 1 : null;
            obj2 = null;
        }
        if (count2 > 13) {
            obj3 = 1;
        } else {
            if (count2 < 4) {
                obj4 = 1;
            }
            obj3 = null;
        }
        if (i2 != 1) {
            if (i2 == -1) {
                if (i != 0) {
                    if (obj5 != null) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                } else if (obj5 == null) {
                    throw NotFoundException.getNotFoundInstance();
                } else {
                    obj4 = 1;
                    if (obj != null) {
                        if (obj2 != null) {
                            AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
                        } else {
                            throw NotFoundException.getNotFoundInstance();
                        }
                    }
                    if (obj2 != null) {
                        AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
                    }
                    if (obj4 != null) {
                        if (obj3 != null) {
                            AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
                        } else {
                            throw NotFoundException.getNotFoundInstance();
                        }
                    }
                    if (obj3 != null) {
                        AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
                    }
                }
            } else if (i2 != 0) {
                throw NotFoundException.getNotFoundInstance();
            } else if (i == 0) {
                if (obj5 != null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (obj != null) {
                    if (obj2 != null) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
                }
                if (obj2 != null) {
                    AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
                }
                if (obj4 != null) {
                    if (obj3 != null) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
                }
                if (obj3 != null) {
                    AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
                }
            } else if (obj5 == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (count < count2) {
                obj3 = 1;
            } else {
                obj4 = 1;
            }
            obj = 1;
            if (obj != null) {
                if (obj2 != null) {
                    AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
            if (obj2 != null) {
                AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
            }
            if (obj4 != null) {
                if (obj3 != null) {
                    AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
            if (obj3 != null) {
                AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
            }
        } else if (i != 0) {
            if (obj5 != null) {
                throw NotFoundException.getNotFoundInstance();
            }
        } else if (obj5 == null) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            obj3 = 1;
            if (obj != null) {
                if (obj2 != null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
            }
            if (obj2 != null) {
                AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
            }
            if (obj4 != null) {
                if (obj3 != null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
            }
            if (obj3 != null) {
                AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
            }
        }
        obj2 = 1;
        if (obj != null) {
            if (obj2 != null) {
                throw NotFoundException.getNotFoundInstance();
            }
            AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
        }
        if (obj2 != null) {
            AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
        }
        if (obj4 != null) {
            if (obj3 != null) {
                throw NotFoundException.getNotFoundInstance();
            }
            AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
        }
        if (obj3 != null) {
            AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
        }
    }
}
