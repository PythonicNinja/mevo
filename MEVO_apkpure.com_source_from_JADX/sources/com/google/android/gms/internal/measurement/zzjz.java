package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class zzjz extends zzjs {
    zzjz(zzjt zzjt) {
        super(zzjt);
    }

    static zzks zza(zzkr zzkr, String str) {
        for (zzks zzks : zzkr.zzava) {
            if (zzks.name.equals(str)) {
                return zzks;
            }
        }
        return null;
    }

    private static void zza(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append("  ");
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, zzki zzki) {
        if (zzki != null) {
            zza(stringBuilder, i);
            stringBuilder.append("filter {\n");
            zza(stringBuilder, i, "complement", zzki.zzats);
            zza(stringBuilder, i, "param_name", zzgf().zzbn(zzki.zzatt));
            int i2 = i + 1;
            String str = "string_filter";
            zzkl zzkl = zzki.zzatq;
            if (zzkl != null) {
                zza(stringBuilder, i2);
                stringBuilder.append(str);
                stringBuilder.append(" {\n");
                if (zzkl.zzaue != null) {
                    Object obj = "UNKNOWN_MATCH_TYPE";
                    switch (zzkl.zzaue.intValue()) {
                        case 1:
                            obj = "REGEXP";
                            break;
                        case 2:
                            obj = "BEGINS_WITH";
                            break;
                        case 3:
                            obj = "ENDS_WITH";
                            break;
                        case 4:
                            obj = "PARTIAL";
                            break;
                        case 5:
                            obj = "EXACT";
                            break;
                        case 6:
                            obj = "IN_LIST";
                            break;
                        default:
                            break;
                    }
                    zza(stringBuilder, i2, "match_type", obj);
                }
                zza(stringBuilder, i2, "expression", zzkl.zzauf);
                zza(stringBuilder, i2, "case_sensitive", zzkl.zzaug);
                if (zzkl.zzauh.length > 0) {
                    zza(stringBuilder, i2 + 1);
                    stringBuilder.append("expression_list {\n");
                    for (String str2 : zzkl.zzauh) {
                        zza(stringBuilder, i2 + 2);
                        stringBuilder.append(str2);
                        stringBuilder.append("\n");
                    }
                    stringBuilder.append("}\n");
                }
                zza(stringBuilder, i2);
                stringBuilder.append("}\n");
            }
            zza(stringBuilder, i2, "number_filter", zzki.zzatr);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, String str, zzkj zzkj) {
        if (zzkj != null) {
            zza(stringBuilder, i);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (zzkj.zzatw != null) {
                Object obj = "UNKNOWN_COMPARISON_TYPE";
                switch (zzkj.zzatw.intValue()) {
                    case 1:
                        obj = "LESS_THAN";
                        break;
                    case 2:
                        obj = "GREATER_THAN";
                        break;
                    case 3:
                        obj = "EQUAL";
                        break;
                    case 4:
                        obj = Operation.BETWEEN;
                        break;
                    default:
                        break;
                }
                zza(stringBuilder, i, "comparison_type", obj);
            }
            zza(stringBuilder, i, "match_as_float", zzkj.zzatx);
            zza(stringBuilder, i, "comparison_value", zzkj.zzaty);
            zza(stringBuilder, i, "min_comparison_value", zzkj.zzatz);
            zza(stringBuilder, i, "max_comparison_value", zzkj.zzaua);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, zzkv zzkv) {
        if (zzkv != null) {
            long[] jArr;
            int i2;
            zza(stringBuilder, 3);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            int i3 = 0;
            if (zzkv.zzawm != null) {
                zza(stringBuilder, 4);
                stringBuilder.append("results: ");
                jArr = zzkv.zzawm;
                int length = jArr.length;
                i2 = 0;
                int i4 = 0;
                while (i2 < length) {
                    Long valueOf = Long.valueOf(jArr[i2]);
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf);
                    i2++;
                    i4 = i5;
                }
                stringBuilder.append('\n');
            }
            if (zzkv.zzawl != null) {
                zza(stringBuilder, 4);
                stringBuilder.append("status: ");
                jArr = zzkv.zzawl;
                int length2 = jArr.length;
                int i6 = 0;
                while (i3 < length2) {
                    Long valueOf2 = Long.valueOf(jArr[i3]);
                    i2 = i6 + 1;
                    if (i6 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf2);
                    i3++;
                    i6 = i2;
                }
                stringBuilder.append('\n');
            }
            zza(stringBuilder, 3);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, Object obj) {
        if (obj != null) {
            zza(stringBuilder, i + 1);
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(obj);
            stringBuilder.append('\n');
        }
    }

    static boolean zza(long[] jArr, int i) {
        return i < (jArr.length << 6) && (jArr[i / 64] & (1 << (i % 64))) != 0;
    }

    static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i << 6) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
            }
        }
        return jArr;
    }

    static zzks[] zza(zzks[] zzksArr, String str, Object obj) {
        for (zzks zzks : zzksArr) {
            if (str.equals(zzks.name)) {
                zzks.zzave = null;
                zzks.zzale = null;
                zzks.zzasw = null;
                if (obj instanceof Long) {
                    zzks.zzave = (Long) obj;
                    return zzksArr;
                } else if (obj instanceof String) {
                    zzks.zzale = (String) obj;
                    return zzksArr;
                } else {
                    if (obj instanceof Double) {
                        zzks.zzasw = (Double) obj;
                    }
                    return zzksArr;
                }
            }
        }
        Object obj2 = new zzks[(zzksArr.length + 1)];
        System.arraycopy(zzksArr, 0, obj2, 0, zzksArr.length);
        zzks zzks2 = new zzks();
        zzks2.name = str;
        if (obj instanceof Long) {
            zzks2.zzave = (Long) obj;
        } else if (obj instanceof String) {
            zzks2.zzale = (String) obj;
        } else if (obj instanceof Double) {
            zzks2.zzasw = (Double) obj;
        }
        obj2[zzksArr.length] = zzks2;
        return obj2;
    }

    static Object zzb(zzkr zzkr, String str) {
        zzks zza = zza(zzkr, str);
        if (zza != null) {
            if (zza.zzale != null) {
                return zza.zzale;
            }
            if (zza.zzave != null) {
                return zza.zzave;
            }
            if (zza.zzasw != null) {
                return zza.zzasw;
            }
        }
        return null;
    }

    static boolean zzcf(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    final <T extends android.os.Parcelable> T zza(byte[] r5, android.os.Parcelable.Creator<T> r6) {
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
        r4 = this;
        r0 = 0;
        if (r5 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = android.os.Parcel.obtain();
        r2 = r5.length;	 Catch:{ ParseException -> 0x001c }
        r3 = 0;	 Catch:{ ParseException -> 0x001c }
        r1.unmarshall(r5, r3, r2);	 Catch:{ ParseException -> 0x001c }
        r1.setDataPosition(r3);	 Catch:{ ParseException -> 0x001c }
        r5 = r6.createFromParcel(r1);	 Catch:{ ParseException -> 0x001c }
        r5 = (android.os.Parcelable) r5;	 Catch:{ ParseException -> 0x001c }
        r1.recycle();
        return r5;
    L_0x001a:
        r5 = move-exception;
        goto L_0x002d;
    L_0x001c:
        r5 = r4.zzgi();	 Catch:{ all -> 0x001a }
        r5 = r5.zziv();	 Catch:{ all -> 0x001a }
        r6 = "Failed to load parcelable from buffer";	 Catch:{ all -> 0x001a }
        r5.log(r6);	 Catch:{ all -> 0x001a }
        r1.recycle();
        return r0;
    L_0x002d:
        r1.recycle();
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjz.zza(byte[], android.os.Parcelable$Creator):T");
    }

    final String zza(zzkh zzkh) {
        if (zzkh == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nevent_filter {\n");
        int i = 0;
        zza(stringBuilder, 0, "filter_id", zzkh.zzatk);
        zza(stringBuilder, 0, "event_name", zzgf().zzbm(zzkh.zzatl));
        zza(stringBuilder, 1, "event_count_filter", zzkh.zzato);
        stringBuilder.append("  filters {\n");
        zzki[] zzkiArr = zzkh.zzatm;
        int length = zzkiArr.length;
        while (i < length) {
            zza(stringBuilder, 2, zzkiArr[i]);
            i++;
        }
        zza(stringBuilder, 1);
        stringBuilder.append("}\n}\n");
        return stringBuilder.toString();
    }

    final String zza(zzkk zzkk) {
        if (zzkk == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nproperty_filter {\n");
        zza(stringBuilder, 0, "filter_id", zzkk.zzatk);
        zza(stringBuilder, 0, "property_name", zzgf().zzbo(zzkk.zzauc));
        zza(stringBuilder, 1, zzkk.zzaud);
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    final void zza(zzks zzks, Object obj) {
        Preconditions.checkNotNull(obj);
        zzks.zzale = null;
        zzks.zzave = null;
        zzks.zzasw = null;
        if (obj instanceof String) {
            zzks.zzale = (String) obj;
        } else if (obj instanceof Long) {
            zzks.zzave = (Long) obj;
        } else if (obj instanceof Double) {
            zzks.zzasw = (Double) obj;
        } else {
            zzgi().zziv().zzg("Ignoring invalid (type) event param value", obj);
        }
    }

    final void zza(zzkx zzkx, Object obj) {
        Preconditions.checkNotNull(obj);
        zzkx.zzale = null;
        zzkx.zzave = null;
        zzkx.zzasw = null;
        if (obj instanceof String) {
            zzkx.zzale = (String) obj;
        } else if (obj instanceof Long) {
            zzkx.zzave = (Long) obj;
        } else if (obj instanceof Double) {
            zzkx.zzasw = (Double) obj;
        } else {
            zzgi().zziv().zzg("Ignoring invalid (type) user attribute value", obj);
        }
    }

    final boolean zza(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzbt().currentTimeMillis() - j) > j2;
    }

    final byte[] zza(zzkt zzkt) {
        try {
            byte[] bArr = new byte[zzkt.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkt.zza(zzb);
            zzb.zzvt();
            return bArr;
        } catch (IOException e) {
            zzgi().zziv().zzg("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    final byte[] zza(byte[] bArr) throws IOException {
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzgi().zziv().zzg("Failed to ungzip content", e);
            throw e;
        }
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    final String zzb(zzkt zzkt) {
        zzkt zzkt2 = zzkt;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nbatch {\n");
        if (zzkt2.zzavf != null) {
            for (zzku zzku : zzkt2.zzavf) {
                if (!(zzku == null || zzku == null)) {
                    zza(stringBuilder, 1);
                    stringBuilder.append("bundle {\n");
                    zza(stringBuilder, 1, "protocol_version", zzku.zzavh);
                    zza(stringBuilder, 1, "platform", zzku.zzavp);
                    zza(stringBuilder, 1, "gmp_version", zzku.zzavt);
                    zza(stringBuilder, 1, "uploading_gmp_version", zzku.zzavu);
                    zza(stringBuilder, 1, "config_version", zzku.zzawf);
                    zza(stringBuilder, 1, "gmp_app_id", zzku.zzafa);
                    zza(stringBuilder, 1, "app_id", zzku.zzth);
                    zza(stringBuilder, 1, "app_version", zzku.zztg);
                    zza(stringBuilder, 1, "app_version_major", zzku.zzawb);
                    zza(stringBuilder, 1, "firebase_instance_id", zzku.zzafc);
                    zza(stringBuilder, 1, "dev_cert_hash", zzku.zzavx);
                    zza(stringBuilder, 1, "app_store", zzku.zzafh);
                    zza(stringBuilder, 1, "upload_timestamp_millis", zzku.zzavk);
                    zza(stringBuilder, 1, "start_timestamp_millis", zzku.zzavl);
                    zza(stringBuilder, 1, "end_timestamp_millis", zzku.zzavm);
                    zza(stringBuilder, 1, "previous_bundle_start_timestamp_millis", zzku.zzavn);
                    zza(stringBuilder, 1, "previous_bundle_end_timestamp_millis", zzku.zzavo);
                    zza(stringBuilder, 1, "app_instance_id", zzku.zzaez);
                    zza(stringBuilder, 1, "resettable_device_id", zzku.zzavv);
                    zza(stringBuilder, 1, "device_id", zzku.zzawe);
                    zza(stringBuilder, 1, "ds_id", zzku.zzawh);
                    zza(stringBuilder, 1, "limited_ad_tracking", zzku.zzavw);
                    zza(stringBuilder, 1, "os_version", zzku.zzavq);
                    zza(stringBuilder, 1, "device_model", zzku.zzavr);
                    zza(stringBuilder, 1, "user_default_language", zzku.zzahd);
                    zza(stringBuilder, 1, "time_zone_offset_minutes", zzku.zzavs);
                    zza(stringBuilder, 1, "bundle_sequential_index", zzku.zzavy);
                    zza(stringBuilder, 1, "service_upload", zzku.zzavz);
                    zza(stringBuilder, 1, "health_monitor", zzku.zzafy);
                    if (!(zzku.zzawg == null || zzku.zzawg.longValue() == 0)) {
                        zza(stringBuilder, 1, "android_id", zzku.zzawg);
                    }
                    if (zzku.zzawj != null) {
                        zza(stringBuilder, 1, "retry_counter", zzku.zzawj);
                    }
                    zzkx[] zzkxArr = zzku.zzavj;
                    if (zzkxArr != null) {
                        for (zzkx zzkx : zzkxArr) {
                            if (zzkx != null) {
                                zza(stringBuilder, 2);
                                stringBuilder.append("user_property {\n");
                                zza(stringBuilder, 2, "set_timestamp_millis", zzkx.zzaws);
                                zza(stringBuilder, 2, "name", zzgf().zzbo(zzkx.name));
                                zza(stringBuilder, 2, "string_value", zzkx.zzale);
                                zza(stringBuilder, 2, "int_value", zzkx.zzave);
                                zza(stringBuilder, 2, "double_value", zzkx.zzasw);
                                zza(stringBuilder, 2);
                                stringBuilder.append("}\n");
                            }
                        }
                    }
                    zzkp[] zzkpArr = zzku.zzawa;
                    if (zzkpArr != null) {
                        for (zzkp zzkp : zzkpArr) {
                            if (zzkp != null) {
                                zza(stringBuilder, 2);
                                stringBuilder.append("audience_membership {\n");
                                zza(stringBuilder, 2, "audience_id", zzkp.zzate);
                                zza(stringBuilder, 2, "new_audience", zzkp.zzauv);
                                zza(stringBuilder, 2, "current_data", zzkp.zzaut);
                                zza(stringBuilder, 2, "previous_data", zzkp.zzauu);
                                zza(stringBuilder, 2);
                                stringBuilder.append("}\n");
                            }
                        }
                    }
                    zzkr[] zzkrArr = zzku.zzavi;
                    if (zzkrArr != null) {
                        for (zzkr zzkr : zzkrArr) {
                            if (zzkr != null) {
                                zza(stringBuilder, 2);
                                stringBuilder.append("event {\n");
                                zza(stringBuilder, 2, "name", zzgf().zzbm(zzkr.name));
                                zza(stringBuilder, 2, "timestamp_millis", zzkr.zzavb);
                                zza(stringBuilder, 2, "previous_timestamp_millis", zzkr.zzavc);
                                zza(stringBuilder, 2, "count", zzkr.count);
                                zzks[] zzksArr = zzkr.zzava;
                                if (zzksArr != null) {
                                    for (zzks zzks : zzksArr) {
                                        if (zzks != null) {
                                            zza(stringBuilder, 3);
                                            stringBuilder.append("param {\n");
                                            zza(stringBuilder, 3, "name", zzgf().zzbn(zzks.name));
                                            zza(stringBuilder, 3, "string_value", zzks.zzale);
                                            zza(stringBuilder, 3, "int_value", zzks.zzave);
                                            zza(stringBuilder, 3, "double_value", zzks.zzasw);
                                            zza(stringBuilder, 3);
                                            stringBuilder.append("}\n");
                                        }
                                    }
                                }
                                zza(stringBuilder, 2);
                                stringBuilder.append("}\n");
                            }
                        }
                    }
                    zza(stringBuilder, 1);
                    stringBuilder.append("}\n");
                }
            }
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    final byte[] zzb(byte[] bArr) throws IOException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzgi().zziv().zzg("Failed to gzip content", e);
            throw e;
        }
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    @WorkerThread
    final boolean zzd(zzex zzex, zzeb zzeb) {
        Preconditions.checkNotNull(zzex);
        Preconditions.checkNotNull(zzeb);
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            return true;
        }
        zzgl();
        return false;
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
