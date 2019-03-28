package com.mevo.app.constants;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.TariffExtraInfo;

final /* synthetic */ class Tariffs$$Lambda$0 implements Predicate {
    private final String arg$1;

    Tariffs$$Lambda$0(String str) {
        this.arg$1 = str;
    }

    public boolean test(Object obj) {
        return ((TariffExtraInfo) obj).getCode().equals(this.arg$1);
    }
}
