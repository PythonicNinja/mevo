package com.mevo.app.data.repository;

import com.annimon.stream.function.ToLongFunction;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeUsage;

final /* synthetic */ class TariffRepository$$Lambda$5 implements ToLongFunction {
    static final ToLongFunction $instance = new TariffRepository$$Lambda$5();

    private TariffRepository$$Lambda$5() {
    }

    public long applyAsLong(Object obj) {
        return ((TariffTimeUsage) obj).usedFreeSeconds;
    }
}
