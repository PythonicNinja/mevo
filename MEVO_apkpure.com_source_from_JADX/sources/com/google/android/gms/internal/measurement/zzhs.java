package com.google.android.gms.internal.measurement;

final class zzhs implements Runnable {
    private final /* synthetic */ zzhm zzaps;
    private final /* synthetic */ long zzapv;

    zzhs(zzhm zzhm, long j) {
        this.zzaps = zzhm;
        this.zzapv = j;
    }

    public final void run() {
        zzhi zzhi = this.zzaps;
        long j = this.zzapv;
        zzhi.zzab();
        zzhi.zzfv();
        zzhi.zzch();
        zzhi.zzgi().zzjb().log("Resetting analytics data (FE)");
        zzhi.zzgd().zzkv();
        if (zzhi.zzgk().zzbd(zzhi.zzfz().zzah())) {
            zzhi.zzgj().zzaly.set(j);
        }
        boolean isEnabled = zzhi.zzacv.isEnabled();
        if (!zzhi.zzgk().zzho()) {
            zzhi.zzgj().zzh(isEnabled ^ 1);
        }
        zzhi.zzga().resetAnalyticsData();
        zzhi.zzapq = isEnabled ^ 1;
    }
}
