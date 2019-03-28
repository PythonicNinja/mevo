package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

final class zzav {
    private static final long zzcw = TimeUnit.DAYS.toMillis(7);
    private final long timestamp;
    final String zzbh;
    private final String zzcx;

    private zzav(String str, String str2, long j) {
        this.zzbh = str;
        this.zzcx = str2;
        this.timestamp = j;
    }

    static String zza(String str, String str2, long j) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("token", str);
            jSONObject.put("appVersion", str2);
            jSONObject.put(Param.TIMESTAMP, j);
            return jSONObject.toString();
        } catch (JSONException e) {
            str = String.valueOf(e);
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 24);
            stringBuilder.append("Failed to encode token: ");
            stringBuilder.append(str);
            Log.w("FirebaseInstanceId", stringBuilder.toString());
            return null;
        }
    }

    static zzav zzi(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!str.startsWith("{")) {
            return new zzav(str, null, 0);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new zzav(jSONObject.getString("token"), jSONObject.getString("appVersion"), jSONObject.getLong(Param.TIMESTAMP));
        } catch (JSONException e) {
            str = String.valueOf(e);
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 23);
            stringBuilder.append("Failed to parse token: ");
            stringBuilder.append(str);
            Log.w("FirebaseInstanceId", stringBuilder.toString());
            return null;
        }
    }

    final boolean zzj(String str) {
        if (System.currentTimeMillis() <= this.timestamp + zzcw) {
            if (str.equals(this.zzcx)) {
                return false;
            }
        }
        return true;
    }
}
