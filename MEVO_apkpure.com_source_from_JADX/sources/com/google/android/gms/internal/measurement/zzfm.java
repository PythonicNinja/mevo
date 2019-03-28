package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public final class zzfm extends zzjs {
    private final SSLSocketFactory zzalf;

    public zzfm(zzjt zzjt) {
        super(zzjt);
        this.zzalf = VERSION.SDK_INT < 19 ? new zzke() : null;
    }

    @WorkerThread
    private static byte[] zzb(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream;
        Throwable th;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            inputStream = httpURLConnection.getInputStream();
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                bArr = byteArrayOutputStream.toByteArray();
                if (inputStream != null) {
                    inputStream.close();
                }
                return bArr;
            } catch (Throwable th2) {
                th = th2;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    @WorkerThread
    @VisibleForTesting
    protected final HttpURLConnection zzb(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (openConnection instanceof HttpURLConnection) {
            if (this.zzalf != null && (openConnection instanceof HttpsURLConnection)) {
                ((HttpsURLConnection) openConnection).setSSLSocketFactory(this.zzalf);
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setConnectTimeout(60000);
            httpURLConnection.setReadTimeout(61000);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        throw new IOException("Failed to obtain HTTP connection");
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final boolean zzex() {
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
        r2 = this;
        r2.zzch();
        r0 = r2.getContext();
        r1 = "connectivity";
        r0 = r0.getSystemService(r1);
        r0 = (android.net.ConnectivityManager) r0;
        r0 = r0.getActiveNetworkInfo();	 Catch:{ SecurityException -> 0x0014 }
        goto L_0x0015;
    L_0x0014:
        r0 = 0;
    L_0x0015:
        if (r0 == 0) goto L_0x001f;
    L_0x0017:
        r0 = r0.isConnected();
        if (r0 == 0) goto L_0x001f;
    L_0x001d:
        r0 = 1;
        return r0;
    L_0x001f:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfm.zzex():boolean");
    }

    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    protected final boolean zzgn() {
        return false;
    }

    public final /* bridge */ /* synthetic */ zzjz zzjf() {
        return super.zzjf();
    }

    public final /* bridge */ /* synthetic */ zzed zzjg() {
        return super.zzjg();
    }

    public final /* bridge */ /* synthetic */ zzek zzjh() {
        return super.zzjh();
    }
}
