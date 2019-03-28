package com.mevo.app.data.repository;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.mevo.app.data.model.Country;

final /* synthetic */ class StationsDataRepository$$Lambda$3 implements Function {
    static final Function $instance = new StationsDataRepository$$Lambda$3();

    private StationsDataRepository$$Lambda$3() {
    }

    public Object apply(Object obj) {
        return Stream.of(((Country) obj).cities);
    }
}
