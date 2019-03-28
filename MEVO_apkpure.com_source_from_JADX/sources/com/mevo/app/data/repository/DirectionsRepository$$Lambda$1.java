package com.mevo.app.data.repository;

import com.annimon.stream.function.Function;
import com.mevo.app.data.model.RentalItem;

final /* synthetic */ class DirectionsRepository$$Lambda$1 implements Function {
    static final Function $instance = new DirectionsRepository$$Lambda$1();

    private DirectionsRepository$$Lambda$1() {
    }

    public Object apply(Object obj) {
        return Integer.valueOf(((RentalItem) obj).id);
    }
}
