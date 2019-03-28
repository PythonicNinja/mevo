package com.mevo.app.presentation.main.transactions;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.repository.TariffTimeRange;

final /* synthetic */ class TransactionsAdapter$$Lambda$1 implements Predicate {
    private final RentalItem arg$1;

    TransactionsAdapter$$Lambda$1(RentalItem rentalItem) {
        this.arg$1 = rentalItem;
    }

    public boolean test(Object obj) {
        return TransactionsAdapter.m145x9d0dcb64(this.arg$1, (TariffTimeRange) obj);
    }
}
