package com.mevo.app.data.model.repository;

import android.support.annotation.NonNull;
import com.mevo.app.data.model.TariffExtraInfo;

public class TariffTimeRange {
    public final long endTimestampSeconds;
    public final long startTimestampSeconds;
    @NonNull
    public final TariffExtraInfo tariffExtraInfo;

    public TariffTimeRange(long j, long j2, TariffExtraInfo tariffExtraInfo) {
        this.startTimestampSeconds = j;
        this.endTimestampSeconds = j2;
        this.tariffExtraInfo = tariffExtraInfo;
    }
}
