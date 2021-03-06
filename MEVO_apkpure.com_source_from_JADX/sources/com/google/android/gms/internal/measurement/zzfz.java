package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.util.List;

public final class zzfz {
    private final zzgn zzacv;
    @VisibleForTesting
    volatile zzr zzamv;
    @VisibleForTesting
    private ServiceConnection zzamw;

    zzfz(zzgn zzgn) {
        this.zzacv = zzgn;
    }

    @VisibleForTesting
    private final boolean zzjs() {
        boolean z = false;
        try {
            PackageManagerWrapper packageManager = Wrappers.packageManager(this.zzacv.getContext());
            if (packageManager == null) {
                this.zzacv.zzgi().zzja().log("Failed to retrieve Package Manager to check Play Store compatibility");
                return false;
            }
            if (packageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            this.zzacv.zzgi().zzja().zzg("Failed to retrieve Play Store version", e);
            return false;
        }
    }

    @WorkerThread
    @VisibleForTesting
    final void zzc(Bundle bundle) {
        this.zzacv.zzgh().zzab();
        if (bundle != null) {
            zzfk zziv;
            String str;
            long j = bundle.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (j == 0) {
                zziv = this.zzacv.zzgi().zziv();
                str = "Service response is missing Install Referrer install timestamp";
            } else {
                str = bundle.getString("install_referrer");
                if (str != null) {
                    if (!str.isEmpty()) {
                        this.zzacv.zzgi().zzjc().zzg("InstallReferrer API result", str);
                        zzkd zzgg = this.zzacv.zzgg();
                        String str2 = Operation.EMPTY_PARAM;
                        str = String.valueOf(str);
                        Bundle zza = zzgg.zza(Uri.parse(str.length() != 0 ? str2.concat(str) : new String(str2)));
                        if (zza == null) {
                            zziv = this.zzacv.zzgi().zziv();
                            str = "No campaign params defined in install referrer result";
                        } else {
                            String string = zza.getString(Param.MEDIUM);
                            Object obj = (string == null || "(not set)".equalsIgnoreCase(string) || "organic".equalsIgnoreCase(string)) ? null : 1;
                            if (obj != null) {
                                long j2 = bundle.getLong("referrer_click_timestamp_seconds", 0) * 1000;
                                if (j2 == 0) {
                                    zziv = this.zzacv.zzgi().zziv();
                                    str = "Install Referrer is missing click timestamp for ad campaign";
                                } else {
                                    zza.putLong("click_timestamp", j2);
                                }
                            }
                            if (j == this.zzacv.zzgj().zzalz.get()) {
                                zziv = this.zzacv.zzgi().zzjc();
                                str = "Campaign has already been logged";
                            } else {
                                zza.putString("_cis", "referrer API");
                                this.zzacv.zzgj().zzalz.set(j);
                                this.zzacv.zzfy().logEvent("auto", "_cmp", zza);
                                if (this.zzamw != null) {
                                    ConnectionTracker.getInstance().unbindService(this.zzacv.getContext(), this.zzamw);
                                }
                                return;
                            }
                        }
                    }
                }
                zziv = this.zzacv.zzgi().zziv();
                str = "No referrer defined in install referrer response";
            }
            zziv.log(str);
        }
    }

    @WorkerThread
    protected final void zzjr() {
        this.zzacv.zzgl();
        this.zzacv.zzgh().zzab();
        if (zzjs()) {
            this.zzamw = new zzgb();
            this.zzacv.zzgi().zzja().log("Install Referrer Reporter is initializing");
            this.zzacv.zzgh().zzab();
            Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
            intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
            PackageManager packageManager = this.zzacv.getContext().getPackageManager();
            if (packageManager == null) {
                this.zzacv.zzgi().zziy().log("Failed to obtain Package Manager to verify binding conditions");
                return;
            }
            List queryIntentServices = packageManager.queryIntentServices(intent, 0);
            if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                this.zzacv.zzgi().zzja().log("Play Service for fetching Install Referrer is unavailable on device");
                return;
            }
            ResolveInfo resolveInfo = (ResolveInfo) queryIntentServices.get(0);
            if (resolveInfo.serviceInfo != null) {
                String str = resolveInfo.serviceInfo.packageName;
                if (resolveInfo.serviceInfo.name == null || this.zzamw == null || !"com.android.vending".equals(str) || !zzjs()) {
                    this.zzacv.zzgi().zzja().log("Play Store missing or incompatible. Version 8.3.73 or later required");
                } else {
                    try {
                        this.zzacv.zzgi().zzja().zzg("Install Referrer Service is", ConnectionTracker.getInstance().bindService(this.zzacv.getContext(), new Intent(intent), this.zzamw, 1) ? "available" : "not available");
                        return;
                    } catch (Exception e) {
                        this.zzacv.zzgi().zziv().zzg("Exception occurred while binding to Install Referrer Service", e.getMessage());
                        return;
                    }
                }
            }
            return;
        }
        this.zzacv.zzgi().zzja().log("Install Referrer Reporter is not available");
        this.zzamw = null;
    }

    @Nullable
    @WorkerThread
    @VisibleForTesting
    final Bundle zzjt() {
        this.zzacv.zzgh().zzab();
        if (this.zzamv == null) {
            this.zzacv.zzgi().zziy().log("Attempting to use Install Referrer Service while it is not initialized");
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package_name", this.zzacv.getContext().getPackageName());
        try {
            bundle = this.zzamv.zza(bundle);
            if (bundle != null) {
                return bundle;
            }
            this.zzacv.zzgi().zziv().log("Install Referrer Service returned a null response");
            return null;
        } catch (Exception e) {
            this.zzacv.zzgi().zziv().zzg("Exception occurred while retrieving the Install Referrer", e.getMessage());
            return null;
        }
    }
}
