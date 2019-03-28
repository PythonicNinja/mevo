package com.mevo.app.presentation.main.statitons_map.bottom_sheets;

import com.mevo.app.data.model.User;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseCurrentRentalsAndBookingsListener;
import java.util.List;

final /* synthetic */ class CurrentRentalsDialogView$$Lambda$2 implements ResponseCurrentRentalsAndBookingsListener {
    private final CurrentRentalsDialogView arg$1;
    private final User arg$2;

    CurrentRentalsDialogView$$Lambda$2(CurrentRentalsDialogView currentRentalsDialogView, User user) {
        this.arg$1 = currentRentalsDialogView;
        this.arg$2 = user;
    }

    public void onResponse(List list, List list2, boolean z, Exception exception) {
        this.arg$1.m115x7d2178f9(this.arg$2, list, list2, z, exception);
    }
}
