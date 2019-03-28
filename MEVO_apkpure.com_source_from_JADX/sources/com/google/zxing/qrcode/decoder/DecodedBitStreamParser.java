package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;

final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', '%', '*', '+', '-', '.', '/', ':'};
    private static final int GB2312_SUBSET = 1;

    private DecodedBitStreamParser() {
    }

    static com.google.zxing.common.DecoderResult decode(byte[] r18, com.google.zxing.qrcode.decoder.Version r19, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r20, java.util.Map<com.google.zxing.DecodeHintType, ?> r21) throws com.google.zxing.FormatException {
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
        r0 = r19;
        r7 = new com.google.zxing.common.BitSource;
        r8 = r18;
        r7.<init>(r8);
        r9 = new java.lang.StringBuilder;
        r1 = 50;
        r9.<init>(r1);
        r10 = new java.util.ArrayList;
        r11 = 1;
        r10.<init>(r11);
        r1 = 0;
        r2 = -1;
        r12 = 0;
        r14 = r12;
        r13 = 0;
        r15 = -1;
        r16 = -1;
    L_0x001e:
        r1 = r7.available();	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r2 = 4;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r1 >= r2) goto L_0x0029;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0025:
        r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0027:
        r6 = r1;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        goto L_0x0032;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0029:
        r1 = r7.readBits(r2);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r1 = com.google.zxing.qrcode.decoder.Mode.forBits(r1);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        goto L_0x0027;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0032:
        r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r6 == r1) goto L_0x005e;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0036:
        r1 = com.google.zxing.qrcode.decoder.Mode.FNC1_FIRST_POSITION;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r6 == r1) goto L_0x00bf;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x003a:
        r1 = com.google.zxing.qrcode.decoder.Mode.FNC1_SECOND_POSITION;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r6 != r1) goto L_0x0040;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x003e:
        goto L_0x00bf;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0040:
        r1 = com.google.zxing.qrcode.decoder.Mode.STRUCTURED_APPEND;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r6 != r1) goto L_0x0061;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0044:
        r1 = r7.available();	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r2 = 16;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r1 >= r2) goto L_0x0051;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x004c:
        r0 = com.google.zxing.FormatException.getFormatInstance();	 Catch:{ IllegalArgumentException -> 0x00e9 }
        throw r0;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0051:
        r1 = 8;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r2 = r7.readBits(r1);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r1 = r7.readBits(r1);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r16 = r1;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r15 = r2;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x005e:
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        goto L_0x00c1;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0061:
        r1 = com.google.zxing.qrcode.decoder.Mode.ECI;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r6 != r1) goto L_0x0074;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0065:
        r1 = parseECIValue(r7);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r14 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r1);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r14 != 0) goto L_0x005e;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x006f:
        r0 = com.google.zxing.FormatException.getFormatInstance();	 Catch:{ IllegalArgumentException -> 0x00e9 }
        throw r0;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0074:
        r1 = com.google.zxing.qrcode.decoder.Mode.HANZI;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r6 != r1) goto L_0x008a;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0078:
        r1 = r7.readBits(r2);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r2 = r6.getCharacterCountBits(r0);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r2 = r7.readBits(r2);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r1 != r11) goto L_0x005e;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0086:
        decodeHanziSegment(r7, r9, r2);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        goto L_0x005e;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x008a:
        r1 = r6.getCharacterCountBits(r0);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r3 = r7.readBits(r1);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r1 = com.google.zxing.qrcode.decoder.Mode.NUMERIC;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r6 != r1) goto L_0x009a;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x0096:
        decodeNumericSegment(r7, r9, r3);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        goto L_0x005e;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x009a:
        r1 = com.google.zxing.qrcode.decoder.Mode.ALPHANUMERIC;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r6 != r1) goto L_0x00a2;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x009e:
        decodeAlphanumericSegment(r7, r9, r3, r13);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        goto L_0x005e;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x00a2:
        r1 = com.google.zxing.qrcode.decoder.Mode.BYTE;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r6 != r1) goto L_0x00b1;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x00a6:
        r1 = r7;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r2 = r9;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r4 = r14;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r5 = r10;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r6 = r21;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        decodeByteSegment(r1, r2, r3, r4, r5, r6);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        goto L_0x00c1;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x00b1:
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r1 = com.google.zxing.qrcode.decoder.Mode.KANJI;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r11 != r1) goto L_0x00ba;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x00b6:
        decodeKanjiSegment(r7, r9, r3);	 Catch:{ IllegalArgumentException -> 0x00e9 }
        goto L_0x00c1;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x00ba:
        r0 = com.google.zxing.FormatException.getFormatInstance();	 Catch:{ IllegalArgumentException -> 0x00e9 }
        throw r0;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x00bf:
        r11 = r6;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        r13 = 1;	 Catch:{ IllegalArgumentException -> 0x00e9 }
    L_0x00c1:
        r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;	 Catch:{ IllegalArgumentException -> 0x00e9 }
        if (r11 != r1) goto L_0x00e6;
    L_0x00c5:
        r7 = new com.google.zxing.common.DecoderResult;
        r2 = r9.toString();
        r0 = r10.isEmpty();
        if (r0 == 0) goto L_0x00d3;
    L_0x00d1:
        r3 = r12;
        goto L_0x00d4;
    L_0x00d3:
        r3 = r10;
    L_0x00d4:
        if (r20 != 0) goto L_0x00d8;
    L_0x00d6:
        r4 = r12;
        goto L_0x00dd;
    L_0x00d8:
        r0 = r20.toString();
        r4 = r0;
    L_0x00dd:
        r0 = r7;
        r1 = r8;
        r5 = r15;
        r6 = r16;
        r0.<init>(r1, r2, r3, r4, r5, r6);
        return r7;
    L_0x00e6:
        r11 = 1;
        goto L_0x001e;
    L_0x00e9:
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decode(byte[], com.google.zxing.qrcode.decoder.Version, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Map):com.google.zxing.common.DecoderResult");
    }

    private static void decodeHanziSegment(com.google.zxing.common.BitSource r4, java.lang.StringBuilder r5, int r6) throws com.google.zxing.FormatException {
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
        r0 = r6 * 13;
        r1 = r4.available();
        if (r0 <= r1) goto L_0x000d;
    L_0x0008:
        r4 = com.google.zxing.FormatException.getFormatInstance();
        throw r4;
    L_0x000d:
        r0 = r6 * 2;
        r0 = new byte[r0];
        r1 = 0;
    L_0x0012:
        if (r6 <= 0) goto L_0x0041;
    L_0x0014:
        r2 = 13;
        r2 = r4.readBits(r2);
        r3 = r2 / 96;
        r3 = r3 << 8;
        r2 = r2 % 96;
        r2 = r2 | r3;
        r3 = 959; // 0x3bf float:1.344E-42 double:4.74E-321;
        if (r2 >= r3) goto L_0x002a;
    L_0x0025:
        r3 = 41377; // 0xa1a1 float:5.7982E-41 double:2.0443E-319;
        r2 = r2 + r3;
        goto L_0x002e;
    L_0x002a:
        r3 = 42657; // 0xa6a1 float:5.9775E-41 double:2.10754E-319;
        r2 = r2 + r3;
    L_0x002e:
        r3 = r2 >> 8;
        r3 = r3 & 255;
        r3 = (byte) r3;
        r0[r1] = r3;
        r3 = r1 + 1;
        r2 = r2 & 255;
        r2 = (byte) r2;
        r0[r3] = r2;
        r1 = r1 + 2;
        r6 = r6 + -1;
        goto L_0x0012;
    L_0x0041:
        r4 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x004c }
        r6 = "GB2312";	 Catch:{ UnsupportedEncodingException -> 0x004c }
        r4.<init>(r0, r6);	 Catch:{ UnsupportedEncodingException -> 0x004c }
        r5.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x004c }
        return;
    L_0x004c:
        r4 = com.google.zxing.FormatException.getFormatInstance();
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decodeHanziSegment(com.google.zxing.common.BitSource, java.lang.StringBuilder, int):void");
    }

    private static void decodeKanjiSegment(com.google.zxing.common.BitSource r4, java.lang.StringBuilder r5, int r6) throws com.google.zxing.FormatException {
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
        r0 = r6 * 13;
        r1 = r4.available();
        if (r0 <= r1) goto L_0x000d;
    L_0x0008:
        r4 = com.google.zxing.FormatException.getFormatInstance();
        throw r4;
    L_0x000d:
        r0 = r6 * 2;
        r0 = new byte[r0];
        r1 = 0;
    L_0x0012:
        if (r6 <= 0) goto L_0x003d;
    L_0x0014:
        r2 = 13;
        r2 = r4.readBits(r2);
        r3 = r2 / 192;
        r3 = r3 << 8;
        r2 = r2 % 192;
        r2 = r2 | r3;
        r3 = 7936; // 0x1f00 float:1.1121E-41 double:3.921E-320;
        if (r2 >= r3) goto L_0x002a;
    L_0x0025:
        r3 = 33088; // 0x8140 float:4.6366E-41 double:1.63476E-319;
        r2 = r2 + r3;
        goto L_0x002e;
    L_0x002a:
        r3 = 49472; // 0xc140 float:6.9325E-41 double:2.44424E-319;
        r2 = r2 + r3;
    L_0x002e:
        r3 = r2 >> 8;
        r3 = (byte) r3;
        r0[r1] = r3;
        r3 = r1 + 1;
        r2 = (byte) r2;
        r0[r3] = r2;
        r1 = r1 + 2;
        r6 = r6 + -1;
        goto L_0x0012;
    L_0x003d:
        r4 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0048 }
        r6 = "SJIS";	 Catch:{ UnsupportedEncodingException -> 0x0048 }
        r4.<init>(r0, r6);	 Catch:{ UnsupportedEncodingException -> 0x0048 }
        r5.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x0048 }
        return;
    L_0x0048:
        r4 = com.google.zxing.FormatException.getFormatInstance();
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decodeKanjiSegment(com.google.zxing.common.BitSource, java.lang.StringBuilder, int):void");
    }

    private static void decodeByteSegment(com.google.zxing.common.BitSource r3, java.lang.StringBuilder r4, int r5, com.google.zxing.common.CharacterSetECI r6, java.util.Collection<byte[]> r7, java.util.Map<com.google.zxing.DecodeHintType, ?> r8) throws com.google.zxing.FormatException {
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
        r0 = r5 * 8;
        r1 = r3.available();
        if (r0 <= r1) goto L_0x000d;
    L_0x0008:
        r3 = com.google.zxing.FormatException.getFormatInstance();
        throw r3;
    L_0x000d:
        r0 = new byte[r5];
        r1 = 0;
    L_0x0010:
        if (r1 >= r5) goto L_0x001e;
    L_0x0012:
        r2 = 8;
        r2 = r3.readBits(r2);
        r2 = (byte) r2;
        r0[r1] = r2;
        r1 = r1 + 1;
        goto L_0x0010;
    L_0x001e:
        if (r6 != 0) goto L_0x0025;
    L_0x0020:
        r3 = com.google.zxing.common.StringUtils.guessEncoding(r0, r8);
        goto L_0x0029;
    L_0x0025:
        r3 = r6.name();
    L_0x0029:
        r5 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0035 }
        r5.<init>(r0, r3);	 Catch:{ UnsupportedEncodingException -> 0x0035 }
        r4.append(r5);	 Catch:{ UnsupportedEncodingException -> 0x0035 }
        r7.add(r0);
        return;
    L_0x0035:
        r3 = com.google.zxing.FormatException.getFormatInstance();
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decodeByteSegment(com.google.zxing.common.BitSource, java.lang.StringBuilder, int, com.google.zxing.common.CharacterSetECI, java.util.Collection, java.util.Map):void");
    }

    private static char toAlphaNumericChar(int i) throws FormatException {
        if (i < ALPHANUMERIC_CHARS.length) {
            return ALPHANUMERIC_CHARS[i];
        }
        throw FormatException.getFormatInstance();
    }

    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuilder stringBuilder, int i, boolean z) throws FormatException {
        while (i > 1) {
            if (bitSource.available() < 11) {
                throw FormatException.getFormatInstance();
            }
            int readBits = bitSource.readBits(11);
            stringBuilder.append(toAlphaNumericChar(readBits / 45));
            stringBuilder.append(toAlphaNumericChar(readBits % 45));
            i -= 2;
        }
        if (i == 1) {
            if (bitSource.available() < 6) {
                throw FormatException.getFormatInstance();
            }
            stringBuilder.append(toAlphaNumericChar(bitSource.readBits(6)));
        }
        if (z) {
            for (int length = stringBuilder.length(); length < stringBuilder.length(); length++) {
                if (stringBuilder.charAt(length) == 37) {
                    if (length < stringBuilder.length() - 1) {
                        bitSource = length + 1;
                        if (stringBuilder.charAt(bitSource)) {
                            stringBuilder.deleteCharAt(bitSource);
                        }
                    }
                    stringBuilder.setCharAt(length, '\u001d');
                }
            }
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuilder stringBuilder, int i) throws FormatException {
        while (i >= 3) {
            if (bitSource.available() < 10) {
                throw FormatException.getFormatInstance();
            }
            int readBits = bitSource.readBits(10);
            if (readBits >= 1000) {
                throw FormatException.getFormatInstance();
            }
            stringBuilder.append(toAlphaNumericChar(readBits / 100));
            stringBuilder.append(toAlphaNumericChar((readBits / 10) % 10));
            stringBuilder.append(toAlphaNumericChar(readBits % 10));
            i -= 3;
        }
        if (i == 2) {
            if (bitSource.available() < 7) {
                throw FormatException.getFormatInstance();
            }
            bitSource = bitSource.readBits(7);
            if (bitSource >= 100) {
                throw FormatException.getFormatInstance();
            }
            stringBuilder.append(toAlphaNumericChar(bitSource / 10));
            stringBuilder.append(toAlphaNumericChar(bitSource % 10));
        } else if (i != 1) {
        } else {
            if (bitSource.available() < 4) {
                throw FormatException.getFormatInstance();
            }
            bitSource = bitSource.readBits(4);
            if (bitSource >= 10) {
                throw FormatException.getFormatInstance();
            }
            stringBuilder.append(toAlphaNumericChar(bitSource));
        }
    }

    private static int parseECIValue(BitSource bitSource) throws FormatException {
        int readBits = bitSource.readBits(8);
        if ((readBits & 128) == 0) {
            return readBits & 127;
        }
        if ((readBits & 192) == 128) {
            return bitSource.readBits(8) | ((readBits & 63) << 8);
        }
        if ((readBits & 224) == 192) {
            return bitSource.readBits(16) | ((readBits & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }
}
