package com.google.zxing.oned.rss;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.OneDReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class RSS14Reader extends AbstractRSSReader {
    private static final int[][] FINDER_PATTERNS = new int[][]{new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    private static final int[] INSIDE_GSUM = new int[]{0, 336, 1036, 1516};
    private static final int[] INSIDE_ODD_TOTAL_SUBSET = new int[]{4, 20, 48, 81};
    private static final int[] INSIDE_ODD_WIDEST = new int[]{2, 4, 6, 8};
    private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET = new int[]{1, 10, 34, 70, 126};
    private static final int[] OUTSIDE_GSUM = new int[]{0, 161, 961, 2015, 2715};
    private static final int[] OUTSIDE_ODD_WIDEST = new int[]{8, 6, 4, 3, 1};
    private final List<Pair> possibleLeftPairs = new ArrayList();
    private final List<Pair> possibleRightPairs = new ArrayList();

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException {
        addOrTally(this.possibleLeftPairs, decodePair(bitArray, false, i, map));
        bitArray.reverse();
        addOrTally(this.possibleRightPairs, decodePair(bitArray, true, i, map));
        bitArray.reverse();
        i = this.possibleLeftPairs.size();
        for (bitArray = null; bitArray < i; bitArray++) {
            Pair pair = (Pair) this.possibleLeftPairs.get(bitArray);
            if (pair.getCount() > 1) {
                int size = this.possibleRightPairs.size();
                for (int i2 = 0; i2 < size; i2++) {
                    Pair pair2 = (Pair) this.possibleRightPairs.get(i2);
                    if (pair2.getCount() > 1 && checkChecksum(pair, pair2)) {
                        return constructResult(pair, pair2);
                    }
                }
                continue;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void addOrTally(Collection<Pair> collection, Pair pair) {
        if (pair != null) {
            Object obj = null;
            for (Pair pair2 : collection) {
                if (pair2.getValue() == pair.getValue()) {
                    pair2.incrementCount();
                    obj = 1;
                    break;
                }
            }
            if (obj == null) {
                collection.add(pair);
            }
        }
    }

    public void reset() {
        this.possibleLeftPairs.clear();
        this.possibleRightPairs.clear();
    }

    private static Result constructResult(Pair pair, Pair pair2) {
        int length;
        String valueOf = String.valueOf((((long) pair.getValue()) * 4537077) + ((long) pair2.getValue()));
        StringBuilder stringBuilder = new StringBuilder(14);
        for (length = 13 - valueOf.length(); length > 0; length--) {
            stringBuilder.append('0');
        }
        stringBuilder.append(valueOf);
        int i = 0;
        for (length = 0; length < 13; length++) {
            int charAt = stringBuilder.charAt(length) - 48;
            if ((length & 1) == 0) {
                charAt *= 3;
            }
            i += charAt;
        }
        int i2 = 10 - (i % 10);
        if (i2 == 10) {
            i2 = 0;
        }
        stringBuilder.append(i2);
        pair = pair.getFinderPattern().getResultPoints();
        pair2 = pair2.getFinderPattern().getResultPoints();
        return new Result(String.valueOf(stringBuilder.toString()), null, new ResultPoint[]{pair[0], pair[1], pair2[0], pair2[1]}, BarcodeFormat.RSS_14);
    }

    private static boolean checkChecksum(Pair pair, Pair pair2) {
        int checksumPortion = (pair.getChecksumPortion() + (pair2.getChecksumPortion() * 16)) % 79;
        pair = (pair.getFinderPattern().getValue() * 9) + pair2.getFinderPattern().getValue();
        if (pair > 72) {
            pair--;
        }
        if (pair > 8) {
            pair--;
        }
        return checksumPortion == pair ? true : null;
    }

    private com.google.zxing.oned.rss.Pair decodePair(com.google.zxing.common.BitArray r7, boolean r8, int r9, java.util.Map<com.google.zxing.DecodeHintType, ?> r10) {
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
        r0 = 0;
        r1 = 0;
        r2 = r6.findFinderPattern(r7, r1, r8);	 Catch:{ NotFoundException -> 0x0059 }
        r3 = r6.parseFoundFinderPattern(r7, r9, r8, r2);	 Catch:{ NotFoundException -> 0x0059 }
        if (r10 != 0) goto L_0x000e;	 Catch:{ NotFoundException -> 0x0059 }
    L_0x000c:
        r10 = r0;	 Catch:{ NotFoundException -> 0x0059 }
        goto L_0x0016;	 Catch:{ NotFoundException -> 0x0059 }
    L_0x000e:
        r4 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;	 Catch:{ NotFoundException -> 0x0059 }
        r10 = r10.get(r4);	 Catch:{ NotFoundException -> 0x0059 }
        r10 = (com.google.zxing.ResultPointCallback) r10;	 Catch:{ NotFoundException -> 0x0059 }
    L_0x0016:
        r4 = 1;	 Catch:{ NotFoundException -> 0x0059 }
        if (r10 == 0) goto L_0x0035;	 Catch:{ NotFoundException -> 0x0059 }
    L_0x0019:
        r5 = r2[r1];	 Catch:{ NotFoundException -> 0x0059 }
        r2 = r2[r4];	 Catch:{ NotFoundException -> 0x0059 }
        r5 = r5 + r2;	 Catch:{ NotFoundException -> 0x0059 }
        r2 = (float) r5;	 Catch:{ NotFoundException -> 0x0059 }
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;	 Catch:{ NotFoundException -> 0x0059 }
        r2 = r2 / r5;	 Catch:{ NotFoundException -> 0x0059 }
        if (r8 == 0) goto L_0x002c;	 Catch:{ NotFoundException -> 0x0059 }
    L_0x0024:
        r8 = r7.getSize();	 Catch:{ NotFoundException -> 0x0059 }
        r8 = r8 - r4;	 Catch:{ NotFoundException -> 0x0059 }
        r8 = (float) r8;	 Catch:{ NotFoundException -> 0x0059 }
        r2 = r8 - r2;	 Catch:{ NotFoundException -> 0x0059 }
    L_0x002c:
        r8 = new com.google.zxing.ResultPoint;	 Catch:{ NotFoundException -> 0x0059 }
        r9 = (float) r9;	 Catch:{ NotFoundException -> 0x0059 }
        r8.<init>(r2, r9);	 Catch:{ NotFoundException -> 0x0059 }
        r10.foundPossibleResultPoint(r8);	 Catch:{ NotFoundException -> 0x0059 }
    L_0x0035:
        r8 = r6.decodeDataCharacter(r7, r3, r4);	 Catch:{ NotFoundException -> 0x0059 }
        r7 = r6.decodeDataCharacter(r7, r3, r1);	 Catch:{ NotFoundException -> 0x0059 }
        r9 = new com.google.zxing.oned.rss.Pair;	 Catch:{ NotFoundException -> 0x0059 }
        r10 = r8.getValue();	 Catch:{ NotFoundException -> 0x0059 }
        r10 = r10 * 1597;	 Catch:{ NotFoundException -> 0x0059 }
        r1 = r7.getValue();	 Catch:{ NotFoundException -> 0x0059 }
        r10 = r10 + r1;	 Catch:{ NotFoundException -> 0x0059 }
        r8 = r8.getChecksumPortion();	 Catch:{ NotFoundException -> 0x0059 }
        r7 = r7.getChecksumPortion();	 Catch:{ NotFoundException -> 0x0059 }
        r7 = r7 * 4;	 Catch:{ NotFoundException -> 0x0059 }
        r8 = r8 + r7;	 Catch:{ NotFoundException -> 0x0059 }
        r9.<init>(r10, r8, r3);	 Catch:{ NotFoundException -> 0x0059 }
        return r9;
    L_0x0059:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.RSS14Reader.decodePair(com.google.zxing.common.BitArray, boolean, int, java.util.Map):com.google.zxing.oned.rss.Pair");
    }

    private DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z) throws NotFoundException {
        int i;
        int length;
        BitArray bitArray2 = bitArray;
        boolean z2 = z;
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
            OneDReader.recordPattern(bitArray2, finderPattern.getStartEnd()[1] + 1, dataCharacterCounters);
            i = 0;
            for (length = dataCharacterCounters.length - 1; i < length; length--) {
                int i2 = dataCharacterCounters[i];
                dataCharacterCounters[i] = dataCharacterCounters[length];
                dataCharacterCounters[length] = i2;
                i++;
            }
        }
        i = z2 ? 16 : 15;
        float count = ((float) AbstractRSSReader.count(dataCharacterCounters)) / ((float) i);
        int[] oddCounts = getOddCounts();
        int[] evenCounts = getEvenCounts();
        float[] oddRoundingErrors = getOddRoundingErrors();
        float[] evenRoundingErrors = getEvenRoundingErrors();
        for (int i3 = 0; i3 < dataCharacterCounters.length; i3++) {
            float f = ((float) dataCharacterCounters[i3]) / count;
            int i4 = (int) (0.5f + f);
            if (i4 < 1) {
                i4 = 1;
            } else if (i4 > 8) {
                i4 = 8;
            }
            int i5 = i3 / 2;
            if ((i3 & 1) == 0) {
                oddCounts[i5] = i4;
                oddRoundingErrors[i5] = f - ((float) i4);
            } else {
                evenCounts[i5] = i4;
                evenRoundingErrors[i5] = f - ((float) i4);
            }
        }
        adjustOddEvenCounts(z2, i);
        int i6 = 0;
        length = 0;
        for (i = oddCounts.length - 1; i >= 0; i--) {
            i6 = (i6 * 9) + oddCounts[i];
            length += oddCounts[i];
        }
        int i7 = 0;
        int i8 = 0;
        for (i = evenCounts.length - 1; i >= 0; i--) {
            i7 = (i7 * 9) + evenCounts[i];
            i8 += evenCounts[i];
        }
        i6 += i7 * 3;
        if (z2) {
            if ((length & 1) == 0 && length <= 12) {
                if (length >= 4) {
                    i = (12 - length) / 2;
                    int i9 = OUTSIDE_ODD_WIDEST[i];
                    int i10 = 9 - i9;
                    return new DataCharacter(((RSSUtils.getRSSvalue(oddCounts, i9, false) * OUTSIDE_EVEN_TOTAL_SUBSET[i]) + RSSUtils.getRSSvalue(evenCounts, i10, true)) + OUTSIDE_GSUM[i], i6);
                }
            }
            throw NotFoundException.getNotFoundInstance();
        }
        if ((i8 & 1) == 0 && i8 <= 10) {
            if (i8 >= 4) {
                i = (10 - i8) / 2;
                i9 = INSIDE_ODD_WIDEST[i];
                return new DataCharacter(((RSSUtils.getRSSvalue(evenCounts, 9 - i9, false) * INSIDE_ODD_TOTAL_SUBSET[i]) + RSSUtils.getRSSvalue(oddCounts, i9, true)) + INSIDE_GSUM[i], i6);
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private int[] findFinderPattern(BitArray bitArray, int i, boolean z) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        int size = bitArray.getSize();
        int i2 = 0;
        while (i < size) {
            i2 = bitArray.get(i) ^ 1;
            if (z == i2) {
                break;
            }
            i++;
        }
        int i3 = i;
        z = false;
        while (i < size) {
            if ((bitArray.get(i) ^ i2) != 0) {
                decodeFinderCounters[z] = decodeFinderCounters[z] + 1;
            } else {
                if (!z) {
                    z++;
                } else if (AbstractRSSReader.isFinderPattern(decodeFinderCounters)) {
                    return new int[]{i3, i};
                } else {
                    i3 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    z--;
                }
                decodeFinderCounters[z] = 1;
                i2 = i2 == 0 ? 1 : 0;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i, boolean z, int[] iArr) throws NotFoundException {
        int size;
        int size2;
        boolean z2 = bitArray.get(iArr[0]);
        int i2 = iArr[0] - 1;
        while (i2 >= 0 && (bitArray.get(i2) ^ z2) != 0) {
            i2--;
        }
        i2++;
        int i3 = iArr[0] - i2;
        Object decodeFinderCounters = getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = i3;
        int parseFinderValue = AbstractRSSReader.parseFinderValue(decodeFinderCounters, FINDER_PATTERNS);
        i3 = iArr[1];
        if (z) {
            size = (bitArray.getSize() - 1) - i3;
            size2 = (bitArray.getSize() - true) - i2;
        } else {
            size = i3;
            size2 = i2;
        }
        return new FinderPattern(parseFinderValue, new int[]{i2, iArr[1]}, size2, size, i);
    }

    private void adjustOddEvenCounts(boolean z, int i) throws NotFoundException {
        Object obj;
        Object obj2;
        boolean count = AbstractRSSReader.count(getOddCounts());
        boolean count2 = AbstractRSSReader.count(getEvenCounts());
        int i2 = (count + count2) - i;
        Object obj3 = null;
        i = (count & 1) == z ? 1 : 0;
        Object obj4 = (count2 & 1) == 1 ? 1 : null;
        if (z) {
            boolean z2;
            Object obj5;
            if (count > true) {
                z2 = false;
                obj5 = 1;
            } else {
                z2 = count < true;
                obj5 = null;
            }
            if (count2 > true) {
                z = z2;
                obj = obj5;
            } else if (count2 < true) {
                z = z2;
                obj = obj5;
                obj3 = 1;
                obj2 = null;
                if (i2 != 1) {
                    if (i2 != -1) {
                        if (i == 0) {
                            if (obj4 == null) {
                                throw NotFoundException.getNotFoundInstance();
                            }
                            z = true;
                        } else if (obj4 != null) {
                            throw NotFoundException.getNotFoundInstance();
                        } else {
                            obj3 = 1;
                        }
                    } else if (i2 != 0) {
                        throw NotFoundException.getNotFoundInstance();
                    } else if (i == 0) {
                        if (obj4 != null) {
                            throw NotFoundException.getNotFoundInstance();
                        } else if (count >= count2) {
                            z = true;
                            obj2 = 1;
                        } else {
                            obj3 = 1;
                        }
                    } else if (obj4 != null) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    if (z) {
                        if (obj == null) {
                            AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
                        } else {
                            throw NotFoundException.getNotFoundInstance();
                        }
                    }
                    if (obj != null) {
                        AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
                    }
                    if (obj3 != null) {
                        if (obj2 == null) {
                            AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
                        } else {
                            throw NotFoundException.getNotFoundInstance();
                        }
                    }
                    if (obj2 == null) {
                        AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
                    }
                } else if (i == 0) {
                    if (obj4 == null) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    obj2 = 1;
                    if (z) {
                        if (obj == null) {
                            throw NotFoundException.getNotFoundInstance();
                        }
                        AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
                    }
                    if (obj != null) {
                        AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
                    }
                    if (obj3 != null) {
                        if (obj2 == null) {
                            throw NotFoundException.getNotFoundInstance();
                        }
                        AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
                    }
                    if (obj2 == null) {
                        AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
                    }
                } else if (obj4 != null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                obj = 1;
                if (z) {
                    if (obj == null) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
                }
                if (obj != null) {
                    AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
                }
                if (obj3 != null) {
                    if (obj2 == null) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
                }
                if (obj2 == null) {
                    AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
                }
            } else {
                z = z2;
                obj = obj5;
                obj2 = null;
                if (i2 != 1) {
                    if (i2 != -1) {
                        if (i2 != 0) {
                            throw NotFoundException.getNotFoundInstance();
                        } else if (i == 0) {
                            if (obj4 != null) {
                                throw NotFoundException.getNotFoundInstance();
                            }
                        } else if (obj4 != null) {
                            throw NotFoundException.getNotFoundInstance();
                        } else if (count >= count2) {
                            obj3 = 1;
                        } else {
                            z = true;
                            obj2 = 1;
                        }
                    } else if (i == 0) {
                        if (obj4 != null) {
                            obj3 = 1;
                        } else {
                            throw NotFoundException.getNotFoundInstance();
                        }
                    } else if (obj4 == null) {
                        z = true;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    if (z) {
                        if (obj == null) {
                            AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
                        } else {
                            throw NotFoundException.getNotFoundInstance();
                        }
                    }
                    if (obj != null) {
                        AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
                    }
                    if (obj3 != null) {
                        if (obj2 == null) {
                            AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
                        } else {
                            throw NotFoundException.getNotFoundInstance();
                        }
                    }
                    if (obj2 == null) {
                        AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
                    }
                } else if (i == 0) {
                    if (obj4 == null) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    obj2 = 1;
                    if (z) {
                        if (obj == null) {
                            throw NotFoundException.getNotFoundInstance();
                        }
                        AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
                    }
                    if (obj != null) {
                        AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
                    }
                    if (obj3 != null) {
                        if (obj2 == null) {
                            throw NotFoundException.getNotFoundInstance();
                        }
                        AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
                    }
                    if (obj2 == null) {
                        AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
                    }
                } else if (obj4 != null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                obj = 1;
                if (z) {
                    if (obj == null) {
                        AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                if (obj != null) {
                    AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
                }
                if (obj3 != null) {
                    if (obj2 == null) {
                        AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                if (obj2 == null) {
                    AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
                }
            }
        }
        if (count > true) {
            z = false;
            obj = 1;
        } else {
            z = count < true;
            obj = null;
        }
        if (count2 <= true) {
            if (count2 < true) {
                obj3 = 1;
            }
            obj2 = null;
            if (i2 != 1) {
                if (i2 != -1) {
                    if (i == 0) {
                        if (obj4 == null) {
                            throw NotFoundException.getNotFoundInstance();
                        }
                        z = true;
                    } else if (obj4 != null) {
                        throw NotFoundException.getNotFoundInstance();
                    } else {
                        obj3 = 1;
                    }
                } else if (i2 != 0) {
                    throw NotFoundException.getNotFoundInstance();
                } else if (i == 0) {
                    if (obj4 != null) {
                        throw NotFoundException.getNotFoundInstance();
                    } else if (count >= count2) {
                        z = true;
                        obj2 = 1;
                    } else {
                        obj3 = 1;
                    }
                } else if (obj4 != null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (z) {
                    if (obj == null) {
                        AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                if (obj != null) {
                    AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
                }
                if (obj3 != null) {
                    if (obj2 == null) {
                        AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                if (obj2 == null) {
                    AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
                }
            } else if (i == 0) {
                if (obj4 == null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                obj2 = 1;
                if (z) {
                    if (obj == null) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
                }
                if (obj != null) {
                    AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
                }
                if (obj3 != null) {
                    if (obj2 == null) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
                }
                if (obj2 == null) {
                    AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
                }
            } else if (obj4 != null) {
                throw NotFoundException.getNotFoundInstance();
            }
            obj = 1;
            if (z) {
                if (obj == null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
            }
            if (obj != null) {
                AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
            }
            if (obj3 != null) {
                if (obj2 == null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
            }
            if (obj2 == null) {
                AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
            }
        }
        obj2 = 1;
        if (i2 != 1) {
            if (i2 != -1) {
                if (i2 != 0) {
                    throw NotFoundException.getNotFoundInstance();
                } else if (i == 0) {
                    if (obj4 != null) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                } else if (obj4 != null) {
                    throw NotFoundException.getNotFoundInstance();
                } else if (count >= count2) {
                    obj3 = 1;
                } else {
                    z = true;
                    obj2 = 1;
                }
            } else if (i == 0) {
                if (obj4 != null) {
                    obj3 = 1;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else if (obj4 == null) {
                z = true;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
            if (z) {
                if (obj == null) {
                    AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
            if (obj != null) {
                AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
            }
            if (obj3 != null) {
                if (obj2 == null) {
                    AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
            if (obj2 == null) {
                AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
            }
        } else if (i == 0) {
            if (obj4 == null) {
                throw NotFoundException.getNotFoundInstance();
            }
            obj2 = 1;
            if (z) {
                if (obj == null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
            }
            if (obj != null) {
                AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
            }
            if (obj3 != null) {
                if (obj2 == null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
            }
            if (obj2 == null) {
                AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
            }
        } else if (obj4 != null) {
            throw NotFoundException.getNotFoundInstance();
        }
        obj = 1;
        if (z) {
            if (obj == null) {
                AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (obj != null) {
            AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
        }
        if (obj3 != null) {
            if (obj2 == null) {
                AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (obj2 == null) {
            AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
        }
    }
}
