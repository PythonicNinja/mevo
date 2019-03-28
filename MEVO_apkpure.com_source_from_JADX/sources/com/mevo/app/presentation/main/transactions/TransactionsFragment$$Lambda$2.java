package com.mevo.app.presentation.main.transactions;

import com.mevo.app.data.model.TransactionInfo;
import java.util.Comparator;

final /* synthetic */ class TransactionsFragment$$Lambda$2 implements Comparator {
    static final Comparator $instance = new TransactionsFragment$$Lambda$2();

    private TransactionsFragment$$Lambda$2() {
    }

    public int compare(Object obj, Object obj2) {
        return Long.compare(((TransactionInfo) obj2).getPaymentDate(), ((TransactionInfo) obj).getPaymentDate());
    }
}
