package com.mevo.app.presentation.main.transactions;

import android.util.Pair;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class TransactionsFragment$$Lambda$0 implements ResponseListener {
    private final TransactionsFragment arg$1;

    TransactionsFragment$$Lambda$0(TransactionsFragment transactionsFragment) {
        this.arg$1 = transactionsFragment;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$fetchData$284$TransactionsFragment((Pair) obj, z, exception);
    }
}
