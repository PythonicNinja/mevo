package com.mevo.app.data.model.repository;

import android.support.annotation.Nullable;
import com.mevo.app.data.model.PurchasedTariff;
import com.mevo.app.data.model.TariffExtraInfo;

public class ResponseActiveTariff {
    @Nullable
    public final PurchasedTariff currentActiveTariff;
    @Nullable
    public final Exception error;
    public final boolean success;
    @Nullable
    public final TariffExtraInfo tariffExtraInfo;

    public ResponseActiveTariff(@Nullable PurchasedTariff purchasedTariff, @Nullable TariffExtraInfo tariffExtraInfo, boolean z, @Nullable Exception exception) {
        this.currentActiveTariff = purchasedTariff;
        this.tariffExtraInfo = tariffExtraInfo;
        this.success = z;
        this.error = exception;
    }
}
