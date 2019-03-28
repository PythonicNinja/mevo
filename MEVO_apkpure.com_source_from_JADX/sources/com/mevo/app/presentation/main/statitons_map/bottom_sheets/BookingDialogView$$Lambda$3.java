package com.mevo.app.presentation.main.statitons_map.bottom_sheets;

import com.mevo.app.data.model.StationJson;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class BookingDialogView$$Lambda$3 implements ResponseListener {
    private final BookingDialogView arg$1;

    BookingDialogView$$Lambda$3(BookingDialogView bookingDialogView) {
        this.arg$1 = bookingDialogView;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$fetchBikesForStation$252$BookingDialogView((StationJson) obj, z, exception);
    }
}
