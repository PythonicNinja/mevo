package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.PurchasedTariff;
import com.mevo.app.data.model.TariffExtraInfo;

final /* synthetic */ class TariffRepository$$Lambda$14 implements Predicate {
    private final PurchasedTariff arg$1;

    TariffRepository$$Lambda$14(PurchasedTariff purchasedTariff) {
        this.arg$1 = purchasedTariff;
    }

    public boolean test(Object obj) {
        return TariffRepository.lambda$getTariffActiveOnTsSync$87$TariffRepository(this.arg$1, (TariffExtraInfo) obj);
    }
}
