package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class zzc {
    private final ConcurrentHashMap<zzd, List<Throwable>> zzd = new ConcurrentHashMap(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zze = new ReferenceQueue();

    zzc() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        while (true) {
            Reference poll = this.zze.poll();
            if (poll == null) {
                break;
            }
            this.zzd.remove(poll);
        }
        List<Throwable> list = (List) this.zzd.get(new zzd(th, null));
        if (list != null) {
            return list;
        }
        List vector = new Vector(2);
        List<Throwable> list2 = (List) this.zzd.putIfAbsent(new zzd(th, this.zze), vector);
        return list2 == null ? vector : list2;
    }
}
