package com.mevo.app.data.model;

import com.annimon.stream.function.Predicate;

final /* synthetic */ class PurchasedTariff$$Lambda$1 implements Predicate {
    private final long arg$1;

    PurchasedTariff$$Lambda$1(long j) {
        this.arg$1 = j;
    }

    public boolean test(Object obj) {
        return PurchasedTariff.lambda$getActiveOnTs$7$PurchasedTariff(this.arg$1, (PurchasedTariff) obj);
    }
}
