package com.mevo.app.data.model.repository;

import android.support.annotation.Nullable;
import com.mevo.app.data.model.Tariff;
import com.mevo.app.data.model.TariffExtraInfo;

public class ResponseCurrentSubscription {
    @Nullable
    public final Exception error;
    public final boolean success;
    @Nullable
    public final Tariff tariff;
    @Nullable
    public final TariffExtraInfo tariffExtraInfo;

    public ResponseCurrentSubscription(@Nullable Tariff tariff, @Nullable TariffExtraInfo tariffExtraInfo, boolean z, @Nullable Exception exception) {
        this.tariff = tariff;
        this.tariffExtraInfo = tariffExtraInfo;
        this.success = z;
        this.error = exception;
    }
}
