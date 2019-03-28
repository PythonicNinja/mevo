package io.fabric.sdk.android.services.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.crypto.Cipher;

public class CommonUtils {
    static final int BYTES_IN_A_GIGABYTE = 1073741824;
    static final int BYTES_IN_A_KILOBYTE = 1024;
    static final int BYTES_IN_A_MEGABYTE = 1048576;
    private static final String CLS_SHARED_PREFERENCES_NAME = "com.crashlytics.prefs";
    static final boolean CLS_TRACE_DEFAULT = false;
    static final String CLS_TRACE_PREFERENCE_NAME = "com.crashlytics.Trace";
    static final String CRASHLYTICS_BUILD_ID = "com.crashlytics.android.build_id";
    public static final int DEVICE_STATE_BETAOS = 8;
    public static final int DEVICE_STATE_COMPROMISEDLIBRARIES = 32;
    public static final int DEVICE_STATE_DEBUGGERATTACHED = 4;
    public static final int DEVICE_STATE_ISSIMULATOR = 1;
    public static final int DEVICE_STATE_JAILBROKEN = 2;
    public static final int DEVICE_STATE_VENDORINTERNAL = 16;
    static final String FABRIC_BUILD_ID = "io.fabric.android.build_id";
    public static final Comparator<File> FILE_MODIFIED_COMPARATOR = new C04911();
    public static final String GOOGLE_SDK = "google_sdk";
    private static final char[] HEX_VALUES = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String LOG_PRIORITY_NAME_ASSERT = "A";
    private static final String LOG_PRIORITY_NAME_DEBUG = "D";
    private static final String LOG_PRIORITY_NAME_ERROR = "E";
    private static final String LOG_PRIORITY_NAME_INFO = "I";
    private static final String LOG_PRIORITY_NAME_UNKNOWN = "?";
    private static final String LOG_PRIORITY_NAME_VERBOSE = "V";
    private static final String LOG_PRIORITY_NAME_WARN = "W";
    public static final String MD5_INSTANCE = "MD5";
    public static final String SDK = "sdk";
    public static final String SHA1_INSTANCE = "SHA-1";
    private static final long UNCALCULATED_TOTAL_RAM = -1;
    private static Boolean clsTrace = null;
    private static long totalRamInBytes = -1;

    /* renamed from: io.fabric.sdk.android.services.common.CommonUtils$1 */
    static class C04911 implements Comparator<File> {
        C04911() {
        }

        public int compare(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    }

    enum Architecture {
        X86_32,
        X86_64,
        ARM_UNKNOWN,
        PPC,
        PPC64,
        ARMV6,
        ARMV7,
        UNKNOWN,
        ARMV7S,
        ARM64;
        
        private static final Map<String, Architecture> matcher = null;

        static {
            matcher = new HashMap(4);
            matcher.put("armeabi-v7a", ARMV7);
            matcher.put("armeabi", ARMV6);
            matcher.put("arm64-v8a", ARM64);
            matcher.put("x86", X86_32);
        }

        static Architecture getValue() {
            Object obj = Build.CPU_ABI;
            if (TextUtils.isEmpty(obj)) {
                Fabric.getLogger().mo2494d(Fabric.TAG, "Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return UNKNOWN;
            }
            Architecture architecture = (Architecture) matcher.get(obj.toLowerCase(Locale.US));
            if (architecture == null) {
                architecture = UNKNOWN;
            }
            return architecture;
        }
    }

    @Deprecated
    public static boolean isLoggingEnabled(Context context) {
        return false;
    }

    public static String logPriorityToString(int i) {
        switch (i) {
            case 2:
                return LOG_PRIORITY_NAME_VERBOSE;
            case 3:
                return LOG_PRIORITY_NAME_DEBUG;
            case 4:
                return LOG_PRIORITY_NAME_INFO;
            case 5:
                return LOG_PRIORITY_NAME_WARN;
            case 6:
                return LOG_PRIORITY_NAME_ERROR;
            case 7:
                return LOG_PRIORITY_NAME_ASSERT;
            default:
                return "?";
        }
    }

    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(CLS_SHARED_PREFERENCES_NAME, 0);
    }

    public static String extractFieldFromSystemFile(File file, String str) {
        String str2 = null;
        if (file.exists()) {
            Closeable bufferedReader;
            try {
                String[] split;
                bufferedReader = new BufferedReader(new FileReader(file), 1024);
                while (true) {
                    try {
                        CharSequence readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        split = Pattern.compile("\\s*:\\s*").split(readLine, 2);
                        if (split.length > 1 && split[0].equals(str)) {
                            break;
                        }
                    } catch (Exception e) {
                        str = e;
                    }
                }
                str2 = split[1];
            } catch (Exception e2) {
                str = e2;
                bufferedReader = null;
                try {
                    Logger logger = Fabric.getLogger();
                    String str3 = Fabric.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Error parsing ");
                    stringBuilder.append(file);
                    logger.mo2497e(str3, stringBuilder.toString(), str);
                    closeOrLog(bufferedReader, "Failed to close system file reader.");
                    return str2;
                } catch (Throwable th) {
                    file = th;
                    closeOrLog(bufferedReader, "Failed to close system file reader.");
                    throw file;
                }
            } catch (Throwable th2) {
                file = th2;
                bufferedReader = null;
                closeOrLog(bufferedReader, "Failed to close system file reader.");
                throw file;
            }
            closeOrLog(bufferedReader, "Failed to close system file reader.");
        }
        return str2;
    }

    public static int getCpuArchitectureInt() {
        return Architecture.getValue().ordinal();
    }

    public static synchronized long getTotalRamInBytes() {
        long j;
        synchronized (CommonUtils.class) {
            if (totalRamInBytes == -1) {
                j = 0;
                Object extractFieldFromSystemFile = extractFieldFromSystemFile(new File("/proc/meminfo"), "MemTotal");
                if (!TextUtils.isEmpty(extractFieldFromSystemFile)) {
                    String toUpperCase = extractFieldFromSystemFile.toUpperCase(Locale.US);
                    try {
                        long convertMemInfoToBytes;
                        if (toUpperCase.endsWith("KB")) {
                            convertMemInfoToBytes = convertMemInfoToBytes(toUpperCase, "KB", 1024);
                        } else if (toUpperCase.endsWith("MB")) {
                            convertMemInfoToBytes = convertMemInfoToBytes(toUpperCase, "MB", 1048576);
                        } else if (toUpperCase.endsWith("GB")) {
                            convertMemInfoToBytes = convertMemInfoToBytes(toUpperCase, "GB", 1073741824);
                        } else {
                            Logger logger = Fabric.getLogger();
                            String str = Fabric.TAG;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Unexpected meminfo format while computing RAM: ");
                            stringBuilder.append(toUpperCase);
                            logger.mo2494d(str, stringBuilder.toString());
                        }
                        j = convertMemInfoToBytes;
                    } catch (Throwable e) {
                        Logger logger2 = Fabric.getLogger();
                        String str2 = Fabric.TAG;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Unexpected meminfo format while computing RAM: ");
                        stringBuilder2.append(toUpperCase);
                        logger2.mo2497e(str2, stringBuilder2.toString(), e);
                    }
                }
                totalRamInBytes = j;
            }
            j = totalRamInBytes;
        }
        return j;
    }

    static long convertMemInfoToBytes(String str, String str2, int i) {
        return Long.parseLong(str.split(str2)[null].trim()) * ((long) i);
    }

    public static RunningAppProcessInfo getAppProcessInfo(String str, Context context) {
        Context<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    return runningAppProcessInfo;
                }
            }
        }
        return null;
    }

    public static String streamToString(InputStream inputStream) throws IOException {
        inputStream = new Scanner(inputStream).useDelimiter("\\A");
        return inputStream.hasNext() ? inputStream.next() : "";
    }

    public static String md5(String str) {
        return hash(str, MD5_INSTANCE);
    }

    public static String md5(byte[] bArr) {
        return hash(bArr, MD5_INSTANCE);
    }

    public static String sha1(String str) {
        return hash(str, SHA1_INSTANCE);
    }

    public static String sha1(byte[] bArr) {
        return hash(bArr, SHA1_INSTANCE);
    }

    public static String sha1(InputStream inputStream) {
        return hash(inputStream, SHA1_INSTANCE);
    }

    private static String hash(InputStream inputStream, String str) {
        try {
            str = MessageDigest.getInstance(SHA1_INSTANCE);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return hexify(str.digest());
                }
                str.update(bArr, 0, read);
            }
        } catch (InputStream inputStream2) {
            Fabric.getLogger().mo2497e(Fabric.TAG, "Could not calculate hash for app icon.", inputStream2);
            return "";
        }
    }

    private static String hash(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return hexify(instance.digest());
        } catch (byte[] bArr2) {
            Logger logger = Fabric.getLogger();
            String str2 = Fabric.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Could not create hashing algorithm: ");
            stringBuilder.append(str);
            stringBuilder.append(", returning empty string.");
            logger.mo2497e(str2, stringBuilder.toString(), bArr2);
            return "";
        }
    }

    private static String hash(String str, String str2) {
        return hash(str.getBytes(), str2);
    }

    public static String createInstanceIdFrom(String... strArr) {
        String str = null;
        if (strArr != null) {
            if (strArr.length != 0) {
                List<String> arrayList = new ArrayList();
                for (String str2 : strArr) {
                    if (str2 != null) {
                        arrayList.add(str2.replace(Operation.MINUS, "").toLowerCase(Locale.US));
                    }
                }
                Collections.sort(arrayList);
                strArr = new StringBuilder();
                for (String append : arrayList) {
                    strArr.append(append);
                }
                String stringBuilder = strArr.toString();
                if (stringBuilder.length() > 0) {
                    str = sha1(stringBuilder);
                }
                return str;
            }
        }
        return null;
    }

    public static long calculateFreeRamInBytes(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long calculateUsedDiskSpaceInBytes(String str) {
        StatFs statFs = new StatFs(str);
        long blockSize = (long) statFs.getBlockSize();
        return (((long) statFs.getBlockCount()) * blockSize) - (blockSize * ((long) statFs.getAvailableBlocks()));
    }

    public static Float getBatteryLevel(Context context) {
        context = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (context == null) {
            return null;
        }
        return Float.valueOf(((float) context.getIntExtra(Param.LEVEL, -1)) / ((float) context.getIntExtra("scale", -1)));
    }

    public static boolean getProximitySensorEnabled(Context context) {
        boolean z = false;
        if (isEmulator(context)) {
            return false;
        }
        if (((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) != null) {
            z = true;
        }
        return z;
    }

    public static void logControlled(Context context, String str) {
        if (isClsTrace(context) != null) {
            Fabric.getLogger().mo2494d(Fabric.TAG, str);
        }
    }

    public static void logControlledError(Context context, String str, Throwable th) {
        if (isClsTrace(context) != null) {
            Fabric.getLogger().mo2496e(Fabric.TAG, str);
        }
    }

    public static void logControlled(Context context, int i, String str, String str2) {
        if (isClsTrace(context) != null) {
            Fabric.getLogger().log(i, Fabric.TAG, str2);
        }
    }

    public static boolean isClsTrace(Context context) {
        if (clsTrace == null) {
            clsTrace = Boolean.valueOf(getBooleanResourceValue(context, CLS_TRACE_PREFERENCE_NAME, false));
        }
        return clsTrace.booleanValue();
    }

    public static boolean getBooleanResourceValue(Context context, String str, boolean z) {
        if (context != null) {
            Resources resources = context.getResources();
            if (resources != null) {
                int resourcesIdentifier = getResourcesIdentifier(context, str, "bool");
                if (resourcesIdentifier > 0) {
                    return resources.getBoolean(resourcesIdentifier);
                }
                str = getResourcesIdentifier(context, str, "string");
                if (str > null) {
                    return Boolean.parseBoolean(context.getString(str));
                }
            }
        }
        return z;
    }

    public static int getResourcesIdentifier(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, getResourcePackageName(context));
    }

    public static boolean isEmulator(Context context) {
        context = Secure.getString(context.getContentResolver(), "android_id");
        if (!(SDK.equals(Build.PRODUCT) || GOOGLE_SDK.equals(Build.PRODUCT))) {
            if (context != null) {
                return null;
            }
        }
        return true;
    }

    public static boolean isRooted(Context context) {
        context = isEmulator(context);
        String str = Build.TAGS;
        if ((context == null && str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        File file = new File("/system/xbin/su");
        if (context != null || file.exists() == null) {
            return null;
        }
        return true;
    }

    public static boolean isDebuggerAttached() {
        if (!Debug.isDebuggerConnected()) {
            if (!Debug.waitingForDebugger()) {
                return false;
            }
        }
        return true;
    }

    public static int getDeviceState(Context context) {
        int i = isEmulator(context) ? 1 : 0;
        if (isRooted(context) != null) {
            i |= 2;
        }
        return isDebuggerAttached() != null ? i | 4 : i;
    }

    public static int getBatteryVelocity(Context context, boolean z) {
        context = getBatteryLevel(context);
        if (z) {
            if (context != null) {
                if (((double) context.floatValue()) >= 99.0d) {
                    return 3;
                }
                return ((double) context.floatValue()) < 4636666922610458624 ? 2 : null;
            }
        }
        return 1;
    }

    @Deprecated
    public static Cipher createCipher(int i, String str) throws InvalidKeyException {
        throw new InvalidKeyException("This method is deprecated");
    }

    public static String hexify(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr[i3] = HEX_VALUES[i2 >>> 4];
            cArr[i3 + 1] = HEX_VALUES[i2 & 15];
        }
        return new String(cArr);
    }

    public static byte[] dehexify(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static boolean isAppDebuggable(Context context) {
        return (context.getApplicationInfo().flags & 2) != null ? true : null;
    }

    public static String getStringsFileValue(Context context, String str) {
        str = getResourcesIdentifier(context, str, "string");
        return str > null ? context.getString(str) : "";
    }

    public static void closeOrLog(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Closeable closeable2) {
                Fabric.getLogger().mo2497e(Fabric.TAG, str, closeable2);
            }
        }
    }

    public static void flushOrLog(Flushable flushable, String str) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (Flushable flushable2) {
                Fabric.getLogger().mo2497e(Fabric.TAG, str, flushable2);
            }
        }
    }

    public static boolean isNullOrEmpty(String str) {
        if (str != null) {
            if (str.length() != null) {
                return null;
            }
        }
        return true;
    }

    public static String padWithZerosToMaxIntWidth(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("value must be zero or greater");
        }
        return String.format(Locale.US, "%1$10s", new Object[]{Integer.valueOf(i)}).replace(' ', '0');
    }

    public static boolean stringsEqualIncludingNull(String str, String str2) {
        if (str == str2) {
            return true;
        }
        return str != null ? str.equals(str2) : null;
    }

    public static String getResourcePackageName(Context context) {
        int i = context.getApplicationContext().getApplicationInfo().icon;
        if (i > 0) {
            return context.getResources().getResourcePackageName(i);
        }
        return context.getPackageName();
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static String getAppIconHashOrNull(Context context) {
        Throwable e;
        Throwable th;
        String str = null;
        try {
            context = context.getResources().openRawResource(getAppIconResourceId(context));
            try {
                String sha1 = sha1((InputStream) context);
                if (!isNullOrEmpty(sha1)) {
                    str = sha1;
                }
                closeOrLog(context, "Failed to close icon input stream.");
                return str;
            } catch (Exception e2) {
                e = e2;
                try {
                    Fabric.getLogger().mo2497e(Fabric.TAG, "Could not calculate hash for app icon.", e);
                    closeOrLog(context, "Failed to close icon input stream.");
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    closeOrLog(context, "Failed to close icon input stream.");
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            context = null;
            Fabric.getLogger().mo2497e(Fabric.TAG, "Could not calculate hash for app icon.", e);
            closeOrLog(context, "Failed to close icon input stream.");
            return null;
        } catch (Context context2) {
            th = context2;
            context2 = null;
            closeOrLog(context2, "Failed to close icon input stream.");
            throw th;
        }
    }

    public static int getAppIconResourceId(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String resolveBuildId(Context context) {
        int resourcesIdentifier = getResourcesIdentifier(context, FABRIC_BUILD_ID, "string");
        if (resourcesIdentifier == 0) {
            resourcesIdentifier = getResourcesIdentifier(context, CRASHLYTICS_BUILD_ID, "string");
        }
        if (resourcesIdentifier == 0) {
            return null;
        }
        context = context.getResources().getString(resourcesIdentifier);
        Logger logger = Fabric.getLogger();
        String str = Fabric.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Build ID is: ");
        stringBuilder.append(context);
        logger.mo2494d(str, stringBuilder.toString());
        return context;
    }

    public static void closeQuietly(java.io.Closeable r0) {
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
        if (r0 == 0) goto L_0x0008;
    L_0x0002:
        r0.close();	 Catch:{ RuntimeException -> 0x0006, Exception -> 0x0008 }
        goto L_0x0008;
    L_0x0006:
        r0 = move-exception;
        throw r0;
    L_0x0008:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.common.CommonUtils.closeQuietly(java.io.Closeable):void");
    }

    public static boolean checkPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == null ? true : null;
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void openKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }

    @TargetApi(16)
    public static void finishAffinity(Context context, int i) {
        if (context instanceof Activity) {
            finishAffinity((Activity) context, i);
        }
    }

    @TargetApi(16)
    public static void finishAffinity(Activity activity, int i) {
        if (activity != null) {
            if (VERSION.SDK_INT >= 16) {
                activity.finishAffinity();
            } else {
                activity.setResult(i);
                activity.finish();
            }
        }
    }

    public static boolean canTryConnection(Context context) {
        boolean z = true;
        if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        context = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (context == null || context.isConnectedOrConnecting() == null) {
            z = false;
        }
        return z;
    }

    public static void logOrThrowIllegalStateException(String str, String str2) {
        if (Fabric.isDebuggable()) {
            throw new IllegalStateException(str2);
        }
        Fabric.getLogger().mo2507w(str, str2);
    }

    public static void logOrThrowIllegalArgumentException(String str, String str2) {
        if (Fabric.isDebuggable()) {
            throw new IllegalArgumentException(str2);
        }
        Fabric.getLogger().mo2507w(str, str2);
    }
}
