package com.mevo.app.data.model.repository;

public class ResponseCurrentSubscriptionFinishDate {
    public final long subscriptionEndTsSeconds;
    public final boolean success;

    public ResponseCurrentSubscriptionFinishDate(boolean z, long j) {
        this.success = z;
        this.subscriptionEndTsSeconds = j;
    }
}
