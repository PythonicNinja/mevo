package com.mevo.app.presentation.main.ongoing_rent_details;

import com.mevo.app.data.model.RentalRoute;
import com.mevo.app.data.repository.DirectionsRepository.ResponseListener;

final /* synthetic */ class OngoingRentDetailsFragment$$Lambda$7 implements ResponseListener {
    private final OngoingRentDetailsFragment arg$1;

    OngoingRentDetailsFragment$$Lambda$7(OngoingRentDetailsFragment ongoingRentDetailsFragment) {
        this.arg$1 = ongoingRentDetailsFragment;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$null$210$OngoingRentDetailsFragment((RentalRoute) obj, z, exception);
    }
}
