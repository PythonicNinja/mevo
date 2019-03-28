package com.mevo.app.presentation.main.rent_summary;

import com.mevo.app.data.model.RentalRoute;
import com.mevo.app.data.repository.DirectionsRepository.ResponseListener;

final /* synthetic */ class RentSummaryFragment$$Lambda$0 implements ResponseListener {
    private final RentSummaryFragment arg$1;

    RentSummaryFragment$$Lambda$0(RentSummaryFragment rentSummaryFragment) {
        this.arg$1 = rentSummaryFragment;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$fetchRoute$242$RentSummaryFragment((RentalRoute) obj, z, exception);
    }
}
