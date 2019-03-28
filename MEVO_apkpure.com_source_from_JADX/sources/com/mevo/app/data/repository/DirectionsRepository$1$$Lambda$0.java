package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.repository.DirectionsRepository.C08011;

final /* synthetic */ class DirectionsRepository$1$$Lambda$0 implements Predicate {
    private final int arg$1;

    DirectionsRepository$1$$Lambda$0(int i) {
        this.arg$1 = i;
    }

    public boolean test(Object obj) {
        return C08011.lambda$onResponse$12$DirectionsRepository$1(this.arg$1, (RentalItem) obj);
    }
}
