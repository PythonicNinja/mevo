package com.mevo.app.presentation.main.ongoing_rent_details;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.annimon.stream.Stream;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.model.PurchasedTariff;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.TariffExtraInfo;
import com.mevo.app.data.repository.TariffUsageCalculator;
import com.mevo.app.tools.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OngoingRentCalculator {
    @NonNull
    private List<BookingItem> bookingsHistory;
    private final long calculationsStartTsSeconds;
    private final long rentDayStartTsSeconds;
    @Nullable
    private final BookingItem rentalItemBooking;
    @NonNull
    private List<RentalItem> rentalsHistory;
    @NonNull
    private TariffExtraInfo tariffExtraInfoOnRentStart;
    @Nullable
    private final PurchasedTariff tariffOnRentStart;
    @NonNull
    private final RentalItem trackedRentalItem;

    public OngoingRentCalculator(@NonNull RentalItem rentalItem, @Nullable PurchasedTariff purchasedTariff, @NonNull TariffExtraInfo tariffExtraInfo, @Nullable BookingItem bookingItem, @NonNull List<RentalItem> list, @NonNull List<BookingItem> list2) {
        this.rentDayStartTsSeconds = Utils.getStartOfDayTimestampSecondsForTimestampUTC(rentalItem.startTimestampSeconds);
        this.calculationsStartTsSeconds = purchasedTariff != null ? Math.max(purchasedTariff.createdAtTsSeconds, this.rentDayStartTsSeconds) : this.rentDayStartTsSeconds;
        this.trackedRentalItem = rentalItem;
        this.tariffExtraInfoOnRentStart = tariffExtraInfo;
        this.tariffOnRentStart = purchasedTariff;
        this.rentalItemBooking = bookingItem;
        this.rentalsHistory = list;
        this.bookingsHistory = list2;
        filterUnusedHistoryItems();
    }

    private void filterUnusedHistoryItems() {
        this.rentalsHistory = Stream.of(this.rentalsHistory).filter(new OngoingRentCalculator$$Lambda$0(this)).toList();
        this.bookingsHistory = Stream.of(this.bookingsHistory).filter(new OngoingRentCalculator$$Lambda$1(this)).toList();
    }

    final /* synthetic */ boolean lambda$filterUnusedHistoryItems$201$OngoingRentCalculator(RentalItem rentalItem) {
        if (rentalItem.endTimestampSeconds <= this.calculationsStartTsSeconds) {
            if (rentalItem.startTimestampSeconds < this.calculationsStartTsSeconds) {
                return null;
            }
        }
        return true;
    }

    final /* synthetic */ boolean lambda$filterUnusedHistoryItems$202$OngoingRentCalculator(BookingItem bookingItem) {
        if (bookingItem.getEndTimestampSecondsForTariffSecondsCalculation() <= this.calculationsStartTsSeconds) {
            if (bookingItem.getStartTimestampSeconds() + bookingItem.getDurationSeconds() <= this.calculationsStartTsSeconds) {
                return null;
            }
        }
        return true;
    }

    public RentUsedFreeTimeAndCost calculateUsedFreeSecondsAndCost() {
        if (this.tariffOnRentStart == null) {
            return getFinalUsedFreeTimeAndCost(0, this.trackedRentalItem.getDurationSeconds(), 0, this.rentalItemBooking != null ? this.rentalItemBooking.getNonFreeDurationCalculatedByUs() : 0);
        }
        long j;
        Iterable arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        for (j = this.rentDayStartTsSeconds + 86400; j < currentTimeMillis; j += 86400) {
            arrayList2.add(Long.valueOf(j));
        }
        arrayList2.add(Long.valueOf(this.calculationsStartTsSeconds));
        arrayList2.add(Long.valueOf(currentTimeMillis));
        Collections.sort(arrayList2, OngoingRentCalculator$$Lambda$2.$instance);
        TariffUsageCalculator tariffUsageCalculator = new TariffUsageCalculator();
        int i = 0;
        while (i < arrayList2.size() - 1) {
            j = ((Long) arrayList2.get(i)).longValue();
            int i2 = i + 1;
            long longValue = ((Long) arrayList2.get(i2)).longValue();
            arrayList.addAll(tariffUsageCalculator.calculateTimeUsage(this.tariffOnRentStart, this.tariffExtraInfoOnRentStart, j, longValue - 1, this.rentalsHistory, this.bookingsHistory));
            i = i2;
        }
        Iterable toList = Stream.of(arrayList).filter(new OngoingRentCalculator$$Lambda$3(this)).toList();
        Iterable arrayList3 = new ArrayList();
        if (this.rentalItemBooking != null) {
            arrayList3 = Stream.of(arrayList).filter(new OngoingRentCalculator$$Lambda$4(this)).toList();
        }
        return getFinalUsedFreeTimeAndCost(Stream.of(toList).mapToLong(OngoingRentCalculator$$Lambda$5.$instance).sum(), Stream.of(toList).mapToLong(OngoingRentCalculator$$Lambda$6.$instance).sum(), Stream.of(arrayList3).mapToLong(OngoingRentCalculator$$Lambda$7.$instance).sum(), Stream.of(arrayList3).mapToLong(OngoingRentCalculator$$Lambda$8.$instance).sum());
    }

    final /* synthetic */ boolean lambda$calculateUsedFreeSecondsAndCost$203$OngoingRentCalculator(TariffTimeUsage tariffTimeUsage) {
        return ((tariffTimeUsage.item instanceof RentalItem) && ((RentalItem) tariffTimeUsage.item).id == this.trackedRentalItem.id) ? true : null;
    }

    final /* synthetic */ boolean lambda$calculateUsedFreeSecondsAndCost$204$OngoingRentCalculator(TariffTimeUsage tariffTimeUsage) {
        return ((tariffTimeUsage.item instanceof BookingItem) && ((BookingItem) tariffTimeUsage.item).id == this.rentalItemBooking.id) ? true : null;
    }

    private RentUsedFreeTimeAndCost getFinalUsedFreeTimeAndCost(long j, long j2, long j3, long j4) {
        j2 = (((double) (j2 + j4)) / 4633641066610819072L) * ((double) this.tariffExtraInfoOnRentStart.getCentsPerMinuteAfterDailyLimit());
        if (this.trackedRentalItem.getDurationSeconds() > 43200) {
            j2 += (double) this.tariffExtraInfoOnRentStart.getAdditionalCostAfter12hRentCents();
        }
        return new RentUsedFreeTimeAndCost(j + j3, j2);
    }

    @NonNull
    public TariffExtraInfo getTariffExtraInfoOnRentStart() {
        return this.tariffExtraInfoOnRentStart;
    }
}
