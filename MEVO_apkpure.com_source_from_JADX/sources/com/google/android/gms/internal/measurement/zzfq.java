package com.google.android.gms.internal.measurement;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

@WorkerThread
final class zzfq implements Runnable {
    private final String packageName;
    private final URL url;
    private final byte[] zzalk;
    private final zzfo zzall;
    private final Map<String, String> zzalm;
    private final /* synthetic */ zzfm zzaln;

    public zzfq(zzfm zzfm, String str, URL url, byte[] bArr, Map<String, String> map, zzfo zzfo) {
        this.zzaln = zzfm;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzfo);
        this.url = url;
        this.zzalk = bArr;
        this.zzall = zzfo;
        this.packageName = str;
        this.zzalm = map;
    }

    public final void run() {
        Map map;
        Throwable th;
        Throwable e;
        int i;
        zzgi zzgh;
        this.zzaln.zzfw();
        OutputStream outputStream = null;
        HttpURLConnection zzb;
        Runnable zzfp;
        try {
            zzb = this.zzaln.zzb(this.url);
            try {
                if (this.zzalm != null) {
                    for (Entry entry : this.zzalm.entrySet()) {
                        zzb.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                if (this.zzalk != null) {
                    byte[] zzb2 = this.zzaln.zzjf().zzb(this.zzalk);
                    this.zzaln.zzgi().zzjc().zzg("Uploading data. size", Integer.valueOf(zzb2.length));
                    zzb.setDoOutput(true);
                    zzb.addRequestProperty(HttpRequest.HEADER_CONTENT_ENCODING, HttpRequest.ENCODING_GZIP);
                    zzb.setFixedLengthStreamingMode(zzb2.length);
                    zzb.connect();
                    OutputStream outputStream2 = zzb.getOutputStream();
                    try {
                        outputStream2.write(zzb2);
                        outputStream2.close();
                    } catch (Throwable e2) {
                        map = null;
                        th = e2;
                        outputStream = outputStream2;
                        i = 0;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e3) {
                                this.zzaln.zzgi().zziv().zze("Error closing HTTP compressed POST connection output stream. appId", zzfi.zzbp(this.packageName), e3);
                            }
                        }
                        if (zzb != null) {
                            zzb.disconnect();
                        }
                        zzgh = this.zzaln.zzgh();
                        zzfp = new zzfp(this.packageName, this.zzall, i, th, null, map);
                        zzgh.zzc(r1);
                    } catch (Throwable th2) {
                        e2 = th2;
                        map = null;
                        outputStream = outputStream2;
                        i = 0;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e32) {
                                this.zzaln.zzgi().zziv().zze("Error closing HTTP compressed POST connection output stream. appId", zzfi.zzbp(this.packageName), e32);
                            }
                        }
                        if (zzb != null) {
                            zzb.disconnect();
                        }
                        this.zzaln.zzgh().zzc(new zzfp(this.packageName, this.zzall, i, null, null, map));
                        throw e2;
                    }
                }
                i = zzb.getResponseCode();
            } catch (IOException e4) {
                e2 = e4;
                map = null;
                th = e2;
                i = 0;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (zzb != null) {
                    zzb.disconnect();
                }
                zzgh = this.zzaln.zzgh();
                zzfp = new zzfp(this.packageName, this.zzall, i, th, null, map);
                zzgh.zzc(r1);
            } catch (Throwable th3) {
                e2 = th3;
                map = null;
                i = 0;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (zzb != null) {
                    zzb.disconnect();
                }
                this.zzaln.zzgh().zzc(new zzfp(this.packageName, this.zzall, i, null, null, map));
                throw e2;
            }
            try {
                map = zzb.getHeaderFields();
                try {
                    byte[] zza = zzfm.zzb(zzb);
                    if (zzb != null) {
                        zzb.disconnect();
                    }
                    zzgh = this.zzaln.zzgh();
                    zzfp = new zzfp(this.packageName, this.zzall, i, null, zza, map);
                } catch (IOException e5) {
                    e2 = e5;
                    th = e2;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (zzb != null) {
                        zzb.disconnect();
                    }
                    zzgh = this.zzaln.zzgh();
                    zzfp = new zzfp(this.packageName, this.zzall, i, th, null, map);
                    zzgh.zzc(r1);
                } catch (Throwable th4) {
                    e2 = th4;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (zzb != null) {
                        zzb.disconnect();
                    }
                    this.zzaln.zzgh().zzc(new zzfp(this.packageName, this.zzall, i, null, null, map));
                    throw e2;
                }
            } catch (IOException e6) {
                e2 = e6;
                map = null;
                th = e2;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (zzb != null) {
                    zzb.disconnect();
                }
                zzgh = this.zzaln.zzgh();
                zzfp = new zzfp(this.packageName, this.zzall, i, th, null, map);
                zzgh.zzc(r1);
            } catch (Throwable th5) {
                e2 = th5;
                map = null;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (zzb != null) {
                    zzb.disconnect();
                }
                this.zzaln.zzgh().zzc(new zzfp(this.packageName, this.zzall, i, null, null, map));
                throw e2;
            }
        } catch (IOException e7) {
            e2 = e7;
            zzb = null;
            map = zzb;
            th = e2;
            i = 0;
            if (outputStream != null) {
                outputStream.close();
            }
            if (zzb != null) {
                zzb.disconnect();
            }
            zzgh = this.zzaln.zzgh();
            zzfp = new zzfp(this.packageName, this.zzall, i, th, null, map);
            zzgh.zzc(r1);
        } catch (Throwable th6) {
            e2 = th6;
            zzb = null;
            map = zzb;
            i = 0;
            if (outputStream != null) {
                outputStream.close();
            }
            if (zzb != null) {
                zzb.disconnect();
            }
            this.zzaln.zzgh().zzc(new zzfp(this.packageName, this.zzall, i, null, null, map));
            throw e2;
        }
        zzgh.zzc(r1);
    }
}
