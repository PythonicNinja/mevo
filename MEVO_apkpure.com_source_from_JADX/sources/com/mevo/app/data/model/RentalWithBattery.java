package com.mevo.app.data.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class RentalWithBattery implements StartEndItem {
    @Nullable
    public final Integer batteryLevel;
    @NonNull
    public final RentalItem rentalItem;

    public RentalWithBattery(@NonNull RentalItem rentalItem, @Nullable Integer num) {
        this.rentalItem = rentalItem;
        this.batteryLevel = num;
    }

    public long getStartTsSeconds() {
        return this.rentalItem.getStartTsSeconds();
    }

    public long getEndTsSeconds() {
        return this.rentalItem.getEndTsSeconds();
    }
}
