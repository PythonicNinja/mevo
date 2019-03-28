package com.mevo.app.presentation.main.rent_history;

import com.mevo.app.data.repository.DirectionsRepository.ResponseListener;
import java.util.List;

final /* synthetic */ class RentHistoryFragment$$Lambda$2 implements ResponseListener {
    private final RentHistoryFragment arg$1;
    private final List arg$2;

    RentHistoryFragment$$Lambda$2(RentHistoryFragment rentHistoryFragment, List list) {
        this.arg$1 = rentHistoryFragment;
        this.arg$2 = list;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$onHistoryReceived$240$RentHistoryFragment(this.arg$2, (List) obj, z, exception);
    }
}
