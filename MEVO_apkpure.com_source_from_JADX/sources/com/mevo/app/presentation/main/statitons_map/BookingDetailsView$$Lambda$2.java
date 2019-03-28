package com.mevo.app.presentation.main.statitons_map;

import android.view.View;
import android.view.View.OnClickListener;
import com.mevo.app.data.model.BookingItem;

final /* synthetic */ class BookingDetailsView$$Lambda$2 implements OnClickListener {
    private final BookingDetailsView arg$1;
    private final BookingItem arg$2;

    BookingDetailsView$$Lambda$2(BookingDetailsView bookingDetailsView, BookingItem bookingItem) {
        this.arg$1 = bookingDetailsView;
        this.arg$2 = bookingItem;
    }

    public void onClick(View view) {
        this.arg$1.lambda$fillData$178$BookingDetailsView(this.arg$2, view);
    }
}
