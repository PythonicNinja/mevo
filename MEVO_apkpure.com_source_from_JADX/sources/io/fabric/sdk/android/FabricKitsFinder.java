package io.fabric.sdk.android;

import android.os.SystemClock;
import android.text.TextUtils;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class FabricKitsFinder implements Callable<Map<String, KitInfo>> {
    private static final String FABRIC_BUILD_TYPE_KEY = "fabric-build-type";
    static final String FABRIC_DIR = "fabric/";
    private static final String FABRIC_IDENTIFIER_KEY = "fabric-identifier";
    private static final String FABRIC_VERSION_KEY = "fabric-version";
    final String apkFileName;

    FabricKitsFinder(String str) {
        this.apkFileName = str;
    }

    public Map<String, KitInfo> call() throws Exception {
        Map<String, KitInfo> hashMap = new HashMap();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        hashMap.putAll(findImplicitKits());
        hashMap.putAll(findRegisteredKits());
        Logger logger = Fabric.getLogger();
        String str = Fabric.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("finish scanning in ");
        stringBuilder.append(SystemClock.elapsedRealtime() - elapsedRealtime);
        logger.mo2505v(str, stringBuilder.toString());
        return hashMap;
    }

    private java.util.Map<java.lang.String, io.fabric.sdk.android.KitInfo> findImplicitKits() {
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
        r5 = this;
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = "com.google.android.gms.ads.AdView";	 Catch:{ Exception -> 0x0027 }
        java.lang.Class.forName(r1);	 Catch:{ Exception -> 0x0027 }
        r1 = new io.fabric.sdk.android.KitInfo;	 Catch:{ Exception -> 0x0027 }
        r2 = "com.google.firebase.firebase-ads";	 Catch:{ Exception -> 0x0027 }
        r3 = "0.0.0";	 Catch:{ Exception -> 0x0027 }
        r4 = "binary";	 Catch:{ Exception -> 0x0027 }
        r1.<init>(r2, r3, r4);	 Catch:{ Exception -> 0x0027 }
        r2 = r1.getIdentifier();	 Catch:{ Exception -> 0x0027 }
        r0.put(r2, r1);	 Catch:{ Exception -> 0x0027 }
        r1 = io.fabric.sdk.android.Fabric.getLogger();	 Catch:{ Exception -> 0x0027 }
        r2 = "Fabric";	 Catch:{ Exception -> 0x0027 }
        r3 = "Found kit: com.google.firebase.firebase-ads";	 Catch:{ Exception -> 0x0027 }
        r1.mo2505v(r2, r3);	 Catch:{ Exception -> 0x0027 }
    L_0x0027:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.FabricKitsFinder.findImplicitKits():java.util.Map<java.lang.String, io.fabric.sdk.android.KitInfo>");
    }

    private java.util.Map<java.lang.String, io.fabric.sdk.android.KitInfo> findRegisteredKits() throws java.lang.Exception {
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
        r10 = this;
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = r10.loadApkFile();
        r2 = r1.entries();
    L_0x000d:
        r3 = r2.hasMoreElements();
        if (r3 == 0) goto L_0x0063;
    L_0x0013:
        r3 = r2.nextElement();
        r3 = (java.util.zip.ZipEntry) r3;
        r4 = r3.getName();
        r5 = "fabric/";
        r4 = r4.startsWith(r5);
        if (r4 == 0) goto L_0x000d;
    L_0x0025:
        r4 = r3.getName();
        r4 = r4.length();
        r5 = "fabric/";
        r5 = r5.length();
        if (r4 <= r5) goto L_0x000d;
    L_0x0035:
        r3 = r10.loadKitInfo(r3, r1);
        if (r3 == 0) goto L_0x000d;
    L_0x003b:
        r4 = r3.getIdentifier();
        r0.put(r4, r3);
        r4 = io.fabric.sdk.android.Fabric.getLogger();
        r5 = "Fabric";
        r6 = "Found kit:[%s] version:[%s]";
        r7 = 2;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r9 = r3.getIdentifier();
        r7[r8] = r9;
        r8 = 1;
        r3 = r3.getVersion();
        r7[r8] = r3;
        r3 = java.lang.String.format(r6, r7);
        r4.mo2505v(r5, r3);
        goto L_0x000d;
    L_0x0063:
        if (r1 == 0) goto L_0x0068;
    L_0x0065:
        r1.close();	 Catch:{ IOException -> 0x0068 }
    L_0x0068:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.FabricKitsFinder.findRegisteredKits():java.util.Map<java.lang.String, io.fabric.sdk.android.KitInfo>");
    }

    private KitInfo loadKitInfo(ZipEntry zipEntry, ZipFile zipFile) {
        Throwable e;
        Logger logger;
        String str;
        StringBuilder stringBuilder;
        try {
            zipFile = zipFile.getInputStream(zipEntry);
            try {
                Properties properties = new Properties();
                properties.load(zipFile);
                Object property = properties.getProperty(FABRIC_IDENTIFIER_KEY);
                Object property2 = properties.getProperty(FABRIC_VERSION_KEY);
                String property3 = properties.getProperty(FABRIC_BUILD_TYPE_KEY);
                if (!TextUtils.isEmpty(property)) {
                    if (!TextUtils.isEmpty(property2)) {
                        KitInfo kitInfo = new KitInfo(property, property2, property3);
                        CommonUtils.closeQuietly(zipFile);
                        return kitInfo;
                    }
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Invalid format of fabric file,");
                stringBuilder2.append(zipEntry.getName());
                throw new IllegalStateException(stringBuilder2.toString());
            } catch (IOException e2) {
                e = e2;
                try {
                    logger = Fabric.getLogger();
                    str = Fabric.TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Error when parsing fabric properties ");
                    stringBuilder.append(zipEntry.getName());
                    logger.mo2497e(str, stringBuilder.toString(), e);
                    CommonUtils.closeQuietly(zipFile);
                    return null;
                } catch (Throwable th) {
                    zipEntry = th;
                    CommonUtils.closeQuietly(zipFile);
                    throw zipEntry;
                }
            }
        } catch (IOException e3) {
            e = e3;
            zipFile = null;
            logger = Fabric.getLogger();
            str = Fabric.TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Error when parsing fabric properties ");
            stringBuilder.append(zipEntry.getName());
            logger.mo2497e(str, stringBuilder.toString(), e);
            CommonUtils.closeQuietly(zipFile);
            return null;
        } catch (Throwable th2) {
            zipEntry = th2;
            zipFile = null;
            CommonUtils.closeQuietly(zipFile);
            throw zipEntry;
        }
    }

    protected ZipFile loadApkFile() throws IOException {
        return new ZipFile(this.apkFileName);
    }
}
