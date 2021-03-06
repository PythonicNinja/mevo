package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

final class zzjo {
    private final Clock clock;
    private long startTime;

    public zzjo(Clock clock) {
        Preconditions.checkNotNull(clock);
        this.clock = clock;
    }

    public final void clear() {
        this.startTime = 0;
    }

    public final void start() {
        this.startTime = this.clock.elapsedRealtime();
    }

    public final boolean zzj(long j) {
        return this.startTime == 0 || this.clock.elapsedRealtime() - this.startTime >= 3600000;
    }
}
