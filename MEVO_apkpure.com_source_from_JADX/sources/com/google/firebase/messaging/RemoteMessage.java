package com.google.firebase.messaging;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.Map.Entry;

@Class(creator = "RemoteMessageCreator")
@Reserved({1})
public final class RemoteMessage extends AbstractSafeParcelable {
    public static final Creator<RemoteMessage> CREATOR = new zzd();
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN = 0;
    @Field(id = 2)
    Bundle zzdm;
    private Map<String, String> zzdn;
    private Notification zzdo;

    public static class Builder {
        private final Bundle zzdm = new Bundle();
        private final Map<String, String> zzdn = new ArrayMap();

        public Builder(String str) {
            if (TextUtils.isEmpty(str)) {
                String str2 = "Invalid to: ";
                str = String.valueOf(str);
                throw new IllegalArgumentException(str.length() != 0 ? str2.concat(str) : new String(str2));
            }
            this.zzdm.putString("google.to", str);
        }

        public Builder addData(String str, String str2) {
            this.zzdn.put(str, str2);
            return this;
        }

        public RemoteMessage build() {
            Bundle bundle = new Bundle();
            for (Entry entry : this.zzdn.entrySet()) {
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
            bundle.putAll(this.zzdm);
            this.zzdm.remove("from");
            return new RemoteMessage(bundle);
        }

        public Builder clearData() {
            this.zzdn.clear();
            return this;
        }

        public Builder setCollapseKey(String str) {
            this.zzdm.putString("collapse_key", str);
            return this;
        }

        public Builder setData(Map<String, String> map) {
            this.zzdn.clear();
            this.zzdn.putAll(map);
            return this;
        }

        public Builder setMessageId(String str) {
            this.zzdm.putString("google.message_id", str);
            return this;
        }

        public Builder setMessageType(String str) {
            this.zzdm.putString("message_type", str);
            return this;
        }

        public Builder setTtl(@IntRange(from = 0, to = 86400) int i) {
            this.zzdm.putString("google.ttl", String.valueOf(i));
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MessagePriority {
    }

    public static class Notification {
        private final String tag;
        private final String zzdp;
        private final String zzdq;
        private final String[] zzdr;
        private final String zzds;
        private final String zzdt;
        private final String[] zzdu;
        private final String zzdv;
        private final String zzdw;
        private final String zzdx;
        private final String zzdy;
        private final Uri zzdz;

        private Notification(Bundle bundle) {
            this.zzdp = zza.zza(bundle, "gcm.n.title");
            this.zzdq = zza.zzb(bundle, "gcm.n.title");
            this.zzdr = zze(bundle, "gcm.n.title");
            this.zzds = zza.zza(bundle, "gcm.n.body");
            this.zzdt = zza.zzb(bundle, "gcm.n.body");
            this.zzdu = zze(bundle, "gcm.n.body");
            this.zzdv = zza.zza(bundle, "gcm.n.icon");
            this.zzdw = zza.zzi(bundle);
            this.tag = zza.zza(bundle, "gcm.n.tag");
            this.zzdx = zza.zza(bundle, "gcm.n.color");
            this.zzdy = zza.zza(bundle, "gcm.n.click_action");
            this.zzdz = zza.zzg(bundle);
        }

        private static String[] zze(Bundle bundle, String str) {
            Object[] zzc = zza.zzc(bundle, str);
            if (zzc == null) {
                return null;
            }
            String[] strArr = new String[zzc.length];
            for (int i = 0; i < zzc.length; i++) {
                strArr[i] = String.valueOf(zzc[i]);
            }
            return strArr;
        }

        @Nullable
        public String getBody() {
            return this.zzds;
        }

        @Nullable
        public String[] getBodyLocalizationArgs() {
            return this.zzdu;
        }

        @Nullable
        public String getBodyLocalizationKey() {
            return this.zzdt;
        }

        @Nullable
        public String getClickAction() {
            return this.zzdy;
        }

        @Nullable
        public String getColor() {
            return this.zzdx;
        }

        @Nullable
        public String getIcon() {
            return this.zzdv;
        }

        @Nullable
        public Uri getLink() {
            return this.zzdz;
        }

        @Nullable
        public String getSound() {
            return this.zzdw;
        }

        @Nullable
        public String getTag() {
            return this.tag;
        }

        @Nullable
        public String getTitle() {
            return this.zzdp;
        }

        @Nullable
        public String[] getTitleLocalizationArgs() {
            return this.zzdr;
        }

        @Nullable
        public String getTitleLocalizationKey() {
            return this.zzdq;
        }
    }

    @Constructor
    public RemoteMessage(@Param(id = 2) Bundle bundle) {
        this.zzdm = bundle;
    }

    private static int zzo(String str) {
        return "high".equals(str) ? 1 : "normal".equals(str) ? 2 : 0;
    }

    @Nullable
    public final String getCollapseKey() {
        return this.zzdm.getString("collapse_key");
    }

    public final Map<String, String> getData() {
        if (this.zzdn == null) {
            this.zzdn = new ArrayMap();
            for (String str : this.zzdm.keySet()) {
                Object obj = this.zzdm.get(str);
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (!(str.startsWith("google.") || str.startsWith("gcm.") || str.equals("from") || str.equals("message_type") || str.equals("collapse_key"))) {
                        this.zzdn.put(str, str2);
                    }
                }
            }
        }
        return this.zzdn;
    }

    @Nullable
    public final String getFrom() {
        return this.zzdm.getString("from");
    }

    @Nullable
    public final String getMessageId() {
        String string = this.zzdm.getString("google.message_id");
        return string == null ? this.zzdm.getString("message_id") : string;
    }

    @Nullable
    public final String getMessageType() {
        return this.zzdm.getString("message_type");
    }

    @Nullable
    public final Notification getNotification() {
        if (this.zzdo == null && zza.zzf(this.zzdm)) {
            this.zzdo = new Notification(this.zzdm);
        }
        return this.zzdo;
    }

    public final int getOriginalPriority() {
        String string = this.zzdm.getString("google.original_priority");
        if (string == null) {
            string = this.zzdm.getString("google.priority");
        }
        return zzo(string);
    }

    public final int getPriority() {
        String string = this.zzdm.getString("google.delivered_priority");
        if (string == null) {
            if ("1".equals(this.zzdm.getString("google.priority_reduced"))) {
                return 2;
            }
            string = this.zzdm.getString("google.priority");
        }
        return zzo(string);
    }

    public final long getSentTime() {
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
        r0 = r4.zzdm;
        r1 = "google.sent_time";
        r0 = r0.get(r1);
        r1 = r0 instanceof java.lang.Long;
        if (r1 == 0) goto L_0x0013;
    L_0x000c:
        r0 = (java.lang.Long) r0;
        r0 = r0.longValue();
        return r0;
    L_0x0013:
        r1 = r0 instanceof java.lang.String;
        if (r1 == 0) goto L_0x0043;
    L_0x0017:
        r1 = r0;	 Catch:{ NumberFormatException -> 0x001f }
        r1 = (java.lang.String) r1;	 Catch:{ NumberFormatException -> 0x001f }
        r1 = java.lang.Long.parseLong(r1);	 Catch:{ NumberFormatException -> 0x001f }
        return r1;
    L_0x001f:
        r1 = "FirebaseMessaging";
        r0 = java.lang.String.valueOf(r0);
        r2 = java.lang.String.valueOf(r0);
        r2 = r2.length();
        r2 = r2 + 19;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r2 = "Invalid sent time: ";
        r3.append(r2);
        r3.append(r0);
        r0 = r3.toString();
        android.util.Log.w(r1, r0);
    L_0x0043:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.RemoteMessage.getSentTime():long");
    }

    @Nullable
    public final String getTo() {
        return this.zzdm.getString("google.to");
    }

    public final int getTtl() {
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
        r0 = r4.zzdm;
        r1 = "google.ttl";
        r0 = r0.get(r1);
        r1 = r0 instanceof java.lang.Integer;
        if (r1 == 0) goto L_0x0013;
    L_0x000c:
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        return r0;
    L_0x0013:
        r1 = r0 instanceof java.lang.String;
        if (r1 == 0) goto L_0x0043;
    L_0x0017:
        r1 = r0;	 Catch:{ NumberFormatException -> 0x001f }
        r1 = (java.lang.String) r1;	 Catch:{ NumberFormatException -> 0x001f }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ NumberFormatException -> 0x001f }
        return r1;
    L_0x001f:
        r1 = "FirebaseMessaging";
        r0 = java.lang.String.valueOf(r0);
        r2 = java.lang.String.valueOf(r0);
        r2 = r2.length();
        r2 = r2 + 13;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r2 = "Invalid TTL: ";
        r3.append(r2);
        r3.append(r0);
        r0 = r3.toString();
        android.util.Log.w(r1, r0);
    L_0x0043:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.RemoteMessage.getTtl():int");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzdm, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
