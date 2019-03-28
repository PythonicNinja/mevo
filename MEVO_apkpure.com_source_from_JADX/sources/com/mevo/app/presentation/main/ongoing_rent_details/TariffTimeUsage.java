package com.mevo.app.presentation.main.ongoing_rent_details;

import android.support.annotation.Nullable;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeEvent.Type;

public class TariffTimeUsage {
    public final long betweenEndTs;
    public final long betweenStartTs;
    public final Object item;
    public final long itemEndTs;
    public final long itemStartTs;
    public long usedFreeSeconds;
    public long usedPaidSeconds;

    public TariffTimeUsage(long j, long j2, long j3, long j4, Object obj) {
        this.itemStartTs = j;
        this.itemEndTs = j2;
        this.betweenStartTs = j3;
        this.betweenEndTs = j4;
        this.item = obj;
    }

    @Nullable
    public TariffTimeEvent getStartEvent() {
        if (this.itemStartTs <= 0) {
            return null;
        }
        if (this.item instanceof RentalItem) {
            return new TariffTimeEvent(((RentalItem) this.item).id, this.itemStartTs, Type.RENT_START);
        }
        if (this.item instanceof BookingItem) {
            return new TariffTimeEvent(((BookingItem) this.item).id, this.itemStartTs, Type.BOOKING_START);
        }
        return null;
    }

    @Nullable
    public TariffTimeEvent getEndEvent() {
        if (this.itemEndTs <= 0) {
            return null;
        }
        if (this.item instanceof RentalItem) {
            return new TariffTimeEvent(((RentalItem) this.item).id, this.itemEndTs, Type.RENT_END);
        }
        if (this.item instanceof BookingItem) {
            return new TariffTimeEvent(((BookingItem) this.item).id, this.itemEndTs, Type.BOOKING_END);
        }
        return null;
    }
}
