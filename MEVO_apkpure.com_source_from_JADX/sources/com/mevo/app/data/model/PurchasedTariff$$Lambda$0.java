package com.mevo.app.data.model;

import com.annimon.stream.function.Predicate;

final /* synthetic */ class PurchasedTariff$$Lambda$0 implements Predicate {
    static final Predicate $instance = new PurchasedTariff$$Lambda$0();

    private PurchasedTariff$$Lambda$0() {
    }

    public boolean test(Object obj) {
        return (((PurchasedTariff) obj).isDeleted() ^ 1);
    }
}
