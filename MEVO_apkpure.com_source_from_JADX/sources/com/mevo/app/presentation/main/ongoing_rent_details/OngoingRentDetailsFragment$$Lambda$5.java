package com.mevo.app.presentation.main.ongoing_rent_details;

import com.mevo.app.data.model.RentalRoute;
import com.mevo.app.data.model.repository.ResponseActiveTariff;
import com.mevo.app.data.model.response.ResponseHistory;
import com.mevo.app.data.model.response.ResponseUserDetails;

final /* synthetic */ class OngoingRentDetailsFragment$$Lambda$5 implements Runnable {
    private final OngoingRentDetailsFragment arg$1;
    private final RentalRoute arg$2;
    private final ResponseUserDetails arg$3;
    private final ResponseActiveTariff arg$4;
    private final ResponseHistory arg$5;

    OngoingRentDetailsFragment$$Lambda$5(OngoingRentDetailsFragment ongoingRentDetailsFragment, RentalRoute rentalRoute, ResponseUserDetails responseUserDetails, ResponseActiveTariff responseActiveTariff, ResponseHistory responseHistory) {
        this.arg$1 = ongoingRentDetailsFragment;
        this.arg$2 = rentalRoute;
        this.arg$3 = responseUserDetails;
        this.arg$4 = responseActiveTariff;
        this.arg$5 = responseHistory;
    }

    public void run() {
        this.arg$1.lambda$null$214$OngoingRentDetailsFragment(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
