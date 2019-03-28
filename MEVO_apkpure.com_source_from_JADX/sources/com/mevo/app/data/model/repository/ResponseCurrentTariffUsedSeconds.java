package com.mevo.app.data.model.repository;

public class ResponseCurrentTariffUsedSeconds {
    public final long dailyFreeSeconds;
    public final long secondsUsedToday;
    public final boolean success;

    public ResponseCurrentTariffUsedSeconds(long j, long j2, boolean z) {
        this.secondsUsedToday = j;
        this.dailyFreeSeconds = j2;
        this.success = z;
    }
}
