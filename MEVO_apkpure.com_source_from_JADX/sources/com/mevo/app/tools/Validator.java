package com.mevo.app.tools;

import android.text.TextUtils;
import android.util.Patterns;
import java.util.regex.Pattern;

public class Validator {
    private static final String REGEX_ALPHANUMERIC = "^[a-zA-Z0-9\\-\\p{L}]+$";

    public static boolean isPhoneNumberValid(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str = str.replaceAll("\\+", "");
        if (str.length() >= 9 && str.length() <= 15) {
            z = true;
        }
        return z;
    }

    public static boolean isPeselValid(java.lang.String r9) {
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
        r0 = 2;
        r1 = 4;
        r2 = 0;
        r0 = r9.substring(r0, r1);	 Catch:{ Exception -> 0x0015 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0015 }
        r3 = 6;
        r1 = r9.substring(r1, r3);	 Catch:{ Exception -> 0x0016 }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Exception -> 0x0016 }
        goto L_0x0017;
    L_0x0015:
        r0 = 0;
    L_0x0016:
        r1 = 0;
    L_0x0017:
        r3 = 1;
        if (r0 < r3) goto L_0x005b;
    L_0x001a:
        r4 = 32;
        if (r0 > r4) goto L_0x005b;
    L_0x001e:
        if (r1 < r3) goto L_0x005b;
    L_0x0020:
        r0 = 31;
        if (r1 <= r0) goto L_0x0025;
    L_0x0024:
        goto L_0x005b;
    L_0x0025:
        r0 = 10;
        r1 = new int[r0];	 Catch:{ Exception -> 0x005a }
        r1 = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};	 Catch:{ Exception -> 0x005a }
        r4 = r9.length();	 Catch:{ Exception -> 0x005a }
        r5 = 11;	 Catch:{ Exception -> 0x005a }
        if (r4 == r5) goto L_0x0035;	 Catch:{ Exception -> 0x005a }
    L_0x0034:
        return r2;	 Catch:{ Exception -> 0x005a }
    L_0x0035:
        r4 = 0;	 Catch:{ Exception -> 0x005a }
        r6 = 0;	 Catch:{ Exception -> 0x005a }
    L_0x0037:
        if (r4 >= r0) goto L_0x004a;	 Catch:{ Exception -> 0x005a }
    L_0x0039:
        r7 = r4 + 1;	 Catch:{ Exception -> 0x005a }
        r8 = r9.substring(r4, r7);	 Catch:{ Exception -> 0x005a }
        r8 = java.lang.Integer.parseInt(r8);	 Catch:{ Exception -> 0x005a }
        r4 = r1[r4];	 Catch:{ Exception -> 0x005a }
        r8 = r8 * r4;	 Catch:{ Exception -> 0x005a }
        r6 = r6 + r8;	 Catch:{ Exception -> 0x005a }
        r4 = r7;	 Catch:{ Exception -> 0x005a }
        goto L_0x0037;	 Catch:{ Exception -> 0x005a }
    L_0x004a:
        r9 = r9.substring(r0, r5);	 Catch:{ Exception -> 0x005a }
        r9 = java.lang.Integer.parseInt(r9);	 Catch:{ Exception -> 0x005a }
        r6 = r6 % r0;	 Catch:{ Exception -> 0x005a }
        r1 = 10 - r6;	 Catch:{ Exception -> 0x005a }
        r1 = r1 % r0;	 Catch:{ Exception -> 0x005a }
        if (r1 != r9) goto L_0x0059;
    L_0x0058:
        r2 = 1;
    L_0x0059:
        return r2;
    L_0x005a:
        return r2;
    L_0x005b:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.tools.Validator.isPeselValid(java.lang.String):boolean");
    }

    public static boolean isPeselYearsOld(java.lang.String r8, int r9) {
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
        r0 = 4;
        r1 = 2;
        r2 = 0;
        r3 = r8.substring(r1, r0);	 Catch:{ Exception -> 0x001f }
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ Exception -> 0x001f }
        r4 = 6;
        r0 = r8.substring(r0, r4);	 Catch:{ Exception -> 0x001d }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x001d }
        r8 = r8.substring(r2, r1);	 Catch:{ Exception -> 0x0021 }
        r8 = java.lang.Integer.parseInt(r8);	 Catch:{ Exception -> 0x0021 }
        goto L_0x0022;
    L_0x001d:
        r0 = 0;
        goto L_0x0021;
    L_0x001f:
        r0 = 0;
        r3 = 0;
    L_0x0021:
        r8 = 0;
    L_0x0022:
        r4 = 1;
        if (r3 < r4) goto L_0x0068;
    L_0x0025:
        r5 = 32;
        if (r3 > r5) goto L_0x0068;
    L_0x0029:
        if (r0 < r4) goto L_0x0068;
    L_0x002b:
        r6 = 31;
        if (r0 <= r6) goto L_0x0030;
    L_0x002f:
        goto L_0x0068;
    L_0x0030:
        r8 = r8 + 1900;
        r2 = 12;
        if (r3 <= r2) goto L_0x003a;
    L_0x0036:
        if (r3 > r5) goto L_0x003a;
    L_0x0038:
        r8 = r8 + 100;
    L_0x003a:
        if (r3 <= r2) goto L_0x003f;
    L_0x003c:
        r3 = r3 + -20;
        goto L_0x003a;
    L_0x003f:
        r2 = new java.util.GregorianCalendar;
        r2.<init>();
        r5 = new java.util.Date;
        r6 = java.lang.System.currentTimeMillis();
        r5.<init>(r6);
        r2.setTime(r5);
        r9 = -r9;
        r2.add(r4, r9);
        r9 = new java.util.GregorianCalendar;
        r9.<init>();
        r9.set(r4, r8);
        r9.set(r1, r3);
        r8 = 5;
        r9.set(r8, r0);
        r8 = r9.before(r2);
        return r8;
    L_0x0068:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.tools.Validator.isPeselYearsOld(java.lang.String, int):boolean");
    }

    public static boolean isPeselValidOrEmpty(String str) {
        if (!isPeselValid(str)) {
            if (TextUtils.isEmpty(str) == null) {
                return null;
            }
        }
        return true;
    }

    public static boolean isPeselYearsOldOrEmpty(String str, int i) {
        if (isPeselYearsOld(str, i) == 0) {
            if (TextUtils.isEmpty(str) == null) {
                return null;
            }
        }
        return true;
    }

    public static boolean isZipCodeValid(String str) {
        return (TextUtils.isEmpty(str) || Pattern.matches("^\\d{2}-\\d{3}?", str) == null) ? null : true;
    }

    public static boolean isCityValid(String str) {
        return TextUtils.isEmpty(str) ^ 1;
    }

    public static boolean isZipCodeValidForAnotherCountry(String str) {
        return TextUtils.isEmpty(str) ^ 1;
    }

    public static boolean isCountryValid(String str) {
        return TextUtils.isEmpty(str) ^ 1;
    }

    public static boolean isStreetValid(String str) {
        return TextUtils.isEmpty(str) ^ 1;
    }

    public static boolean isEmailValid(String str) {
        return (TextUtils.isEmpty(str) || Patterns.EMAIL_ADDRESS.matcher(str).matches() == null) ? null : true;
    }

    public static boolean isPinValid(String str) {
        return (str == null || str.length() < 6) ? null : true;
    }

    public static boolean isBikeNumberValid(String str) {
        return (str == null || str.length() != 5) ? null : true;
    }

    public static boolean isBikeNumberValid(int i) {
        return Integer.toString(i).length() == 5;
    }

    public static boolean isStationNumberValid(String str) {
        return (str == null || str.length() != 5) ? null : true;
    }

    public static boolean isCouponCodeValid(String str) {
        return (str == null || str.length() <= 5) ? null : true;
    }

    public static boolean isFirstNameValid(String str) {
        return (TextUtils.isEmpty(str) || str.matches(REGEX_ALPHANUMERIC) == null) ? null : true;
    }

    public static boolean isLastNameValid(String str) {
        return (TextUtils.isEmpty(str) || str.matches(REGEX_ALPHANUMERIC) == null) ? null : true;
    }
}
