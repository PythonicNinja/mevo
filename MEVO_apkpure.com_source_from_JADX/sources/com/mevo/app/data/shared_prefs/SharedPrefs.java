package com.mevo.app.data.shared_prefs;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import com.google.gson.reflect.TypeToken;
import com.mevo.app.App;
import com.mevo.app.data.network.Rest;
import com.mevo.app.tools.UserManager;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefs {
    private static final String APIKEY_IS_PRODUCTION = "APIKEY_IS_PRODUCTION";
    private static final String CACHE_TIME_LIST_XML = "CACHE_LIST_XML";
    private static final String CURRENT_SUBSCRIPTION_CACHE_TIMESTAMP = "CURRENT_SUBSCRIPTION_CACHE_TIMESTAMP";
    private static final String CURRENT_SUBSCRIPTION_VOUCHER_JSON = "CURRENT_SUBSCRIPTION_VOUCHER_JSON";
    private static final String FAILED_REQUESTS_CONSENTED_AGREEMENT_IDS_PREFIX = "FAILED_REQUESTS_CONSENTED_AGREEMENT_IDS_PREFIX_";
    public static final int INT_INVALID = Integer.MIN_VALUE;
    public static final long LONG_INVALID = Long.MIN_VALUE;
    private static final String MOBILE_NUMBER = "MOBILE_NUMBER";
    private static final String RETURN_BIKE_PUSH_SETTINGS = "RETURN_BIKE_PUSH_SETTINGS";
    private static final String SHARED_PREFS_NAME = "veturilo.tools.shared_prefs";

    /* renamed from: com.mevo.app.data.shared_prefs.SharedPrefs$1 */
    static class C08181 extends TypeToken<ArrayList<Integer>> {
        C08181() {
        }
    }

    public static SharedPreferences getPrefs() {
        return App.getAppContext().getSharedPreferences(SHARED_PREFS_NAME, 0);
    }

    private static int getInt(String str) {
        return getPrefs().getInt(str, Integer.MIN_VALUE);
    }

    private static void setInt(String str, int i) {
        getPrefs().edit().putInt(str, i).apply();
    }

    private static long getLong(String str) {
        return getPrefs().getLong(str, Long.MIN_VALUE);
    }

    private static long getLong0Fallback(String str) {
        return getPrefs().getLong(str, 0);
    }

    private static void setLong(String str, long j) {
        getPrefs().edit().putLong(str, j).apply();
    }

    private static String getString(String str) {
        return getPrefs().getString(str, null);
    }

    private static void setString(String str, String str2) {
        getPrefs().edit().putString(str, str2).apply();
    }

    private static boolean getBoolean(String str) {
        return getPrefs().getBoolean(str, false);
    }

    private static boolean getBoolean(String str, boolean z) {
        return getPrefs().getBoolean(str, z);
    }

    private static void setBoolean(String str, boolean z) {
        getPrefs().edit().putBoolean(str, z).apply();
    }

    public static void setMobileNumber(String str) {
        setString(MOBILE_NUMBER, str);
    }

    public static String getMobileNumber() {
        return getString(MOBILE_NUMBER);
    }

    public static void setHostIsProduction(boolean z) {
        setBoolean(APIKEY_IS_PRODUCTION, z);
    }

    public static boolean getApikeyIsProduction() {
        return getBoolean(APIKEY_IS_PRODUCTION);
    }

    public static void setReturnBikePushSettings(boolean z) {
        setBoolean(RETURN_BIKE_PUSH_SETTINGS, z);
    }

    public static boolean getReturnBikePushSettings() {
        return getBoolean(RETURN_BIKE_PUSH_SETTINGS, true);
    }

    public static void setCacheTimeListXml(long j) {
        setLong(CACHE_TIME_LIST_XML, j);
    }

    public static long getCacheTimeListXml() {
        return getLong0Fallback(CACHE_TIME_LIST_XML);
    }

    public static void setCurrentSubscriptionCacheTimestamp(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CURRENT_SUBSCRIPTION_CACHE_TIMESTAMP);
        stringBuilder.append(UserManager.getCurrentUserMobileNumber());
        setLong(stringBuilder.toString(), j);
    }

    public static long getCurrentSubscriptionCacheTimestamp() {
        String currentUserMobileNumber = UserManager.getCurrentUserMobileNumber();
        if (currentUserMobileNumber == null) {
            return 0;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CURRENT_SUBSCRIPTION_CACHE_TIMESTAMP);
        stringBuilder.append(currentUserMobileNumber);
        return getLong0Fallback(stringBuilder.toString());
    }

    public static void setCurrentSubscriptionVoucherJson(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CURRENT_SUBSCRIPTION_VOUCHER_JSON);
        stringBuilder.append(UserManager.getCurrentUserMobileNumber());
        setString(stringBuilder.toString(), str);
    }

    @Nullable
    public static String getCurrentSubscriptionVoucherJson() {
        String currentUserMobileNumber = UserManager.getCurrentUserMobileNumber();
        if (currentUserMobileNumber == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CURRENT_SUBSCRIPTION_VOUCHER_JSON);
        stringBuilder.append(currentUserMobileNumber);
        return getString(stringBuilder.toString());
    }

    public static void saveFailedRequestsForConsentedAgreements(String str, List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FAILED_REQUESTS_CONSENTED_AGREEMENT_IDS_PREFIX);
        stringBuilder.append(str);
        setString(stringBuilder.toString(), Rest.getJsonSerializer().toJson((Object) list));
    }

    @android.support.annotation.NonNull
    public static java.util.List<java.lang.Integer> getFailedRequestsConsentedAgreementIds(java.lang.String r2) {
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
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "FAILED_REQUESTS_CONSENTED_AGREEMENT_IDS_PREFIX_";
        r0.append(r1);
        r0.append(r2);
        r2 = r0.toString();
        r2 = getString(r2);
        r0 = android.text.TextUtils.isEmpty(r2);
        if (r0 == 0) goto L_0x0021;
    L_0x001b:
        r2 = new java.util.ArrayList;
        r2.<init>();
        return r2;
    L_0x0021:
        r0 = new com.mevo.app.data.shared_prefs.SharedPrefs$1;	 Catch:{ Exception -> 0x0035 }
        r0.<init>();	 Catch:{ Exception -> 0x0035 }
        r0 = r0.getType();	 Catch:{ Exception -> 0x0035 }
        r1 = com.mevo.app.data.network.Rest.getJsonSerializer();	 Catch:{ Exception -> 0x0035 }
        r2 = r1.fromJson(r2, r0);	 Catch:{ Exception -> 0x0035 }
        r2 = (java.util.List) r2;	 Catch:{ Exception -> 0x0035 }
        return r2;
    L_0x0035:
        r2 = new java.util.ArrayList;
        r2.<init>();
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.shared_prefs.SharedPrefs.getFailedRequestsConsentedAgreementIds(java.lang.String):java.util.List<java.lang.Integer>");
    }

    public static void clearFailedRequestsConsentedAgreementIds(String str) {
        Editor edit = getPrefs().edit();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FAILED_REQUESTS_CONSENTED_AGREEMENT_IDS_PREFIX);
        stringBuilder.append(str);
        edit.remove(stringBuilder.toString()).apply();
    }
}
