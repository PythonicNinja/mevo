package com.mevo.app.data.repository;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.mevo.app.data.model.City;

final /* synthetic */ class StationsDataRepository$$Lambda$5 implements Function {
    static final Function $instance = new StationsDataRepository$$Lambda$5();

    private StationsDataRepository$$Lambda$5() {
    }

    public Object apply(Object obj) {
        return Stream.of(((City) obj).places);
    }
}
