package com.mevo.app.constants;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.TariffExtraInfo;

final /* synthetic */ class Tariffs$$Lambda$1 implements Predicate {
    private final int arg$1;

    Tariffs$$Lambda$1(int i) {
        this.arg$1 = i;
    }

    public boolean test(Object obj) {
        return Tariffs.lambda$getForId$3$Tariffs(this.arg$1, (TariffExtraInfo) obj);
    }
}
