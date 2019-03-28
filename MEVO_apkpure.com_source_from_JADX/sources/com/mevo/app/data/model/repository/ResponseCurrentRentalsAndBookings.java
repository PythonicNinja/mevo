package com.mevo.app.data.model.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.model.RentalWithBattery;
import java.util.ArrayList;
import java.util.List;

public class ResponseCurrentRentalsAndBookings {
    @NonNull
    public final List<BookingItem> bookings;
    @NonNull
    public final List<RentalWithBattery> rentals;
    public final boolean success;

    public ResponseCurrentRentalsAndBookings(boolean z, @Nullable List<RentalWithBattery> list, @Nullable List<BookingItem> list2) {
        if (list == null) {
            list = new ArrayList();
        }
        if (list2 == null) {
            list2 = new ArrayList();
        }
        this.success = z;
        this.rentals = list;
        this.bookings = list2;
    }
}
