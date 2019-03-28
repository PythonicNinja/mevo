package com.mevo.app.data.model;

import com.google.gson.annotations.SerializedName;

public class SubscriptionBookingItem {
    @SerializedName("b")
    private int bookingId;
    @SerializedName("s")
    private long startTimestampSeconds;

    public SubscriptionBookingItem(int i, long j) {
        this.bookingId = i;
        this.startTimestampSeconds = j;
    }

    public long getStartTimestampSeconds() {
        return this.startTimestampSeconds;
    }

    public SubscriptionBookingItem setStartTimestampSeconds(long j) {
        this.startTimestampSeconds = j;
        return this;
    }

    public int getBookingId() {
        return this.bookingId;
    }

    public SubscriptionBookingItem setBookingId(int i) {
        this.bookingId = i;
        return this;
    }

    public boolean isFinished() {
        return (System.currentTimeMillis() / 1000) - this.startTimestampSeconds > 900;
    }

    public long getDurationSeconds() {
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - this.startTimestampSeconds;
        return currentTimeMillis > 900 ? 900 : currentTimeMillis;
    }

    public long getUsedSubscriptionSeconds() {
        long durationSeconds = getDurationSeconds() - 300;
        return durationSeconds >= 0 ? durationSeconds : 0;
    }

    public boolean countsTowardsToday(long j) {
        if (getStartTimestampSeconds() < j) {
            if (this.startTimestampSeconds + getDurationSeconds() < j) {
                return 0;
            }
        }
        return 1;
    }
}
