package com.mevo.app.data.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.annimon.stream.Stream;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.model.PurchasedTariff;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.TariffExtraInfo;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeEvent;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeEvent.TimestampComparator;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeEvent.Type;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeUsage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TariffUsageCalculator {
    public List<TariffTimeUsage> calculateTimeUsage(@Nullable PurchasedTariff purchasedTariff, @NonNull TariffExtraInfo tariffExtraInfo, long j, long j2, @Nullable List<RentalItem> list, @Nullable List<BookingItem> list2) {
        TariffUsageCalculator tariffUsageCalculator = this;
        PurchasedTariff purchasedTariff2 = purchasedTariff;
        long j3 = j;
        long j4 = j2;
        if (purchasedTariff2 == null) {
            return new ArrayList();
        }
        Iterable iterable;
        Iterable iterable2;
        if (list != null) {
            iterable = list;
        } else {
            iterable = new ArrayList();
        }
        if (list2 != null) {
            iterable2 = list2;
        } else {
            iterable2 = new ArrayList();
        }
        long minutesPerDay = purchasedTariff2.validTo >= j3 ? ((long) tariffExtraInfo.getMinutesPerDay()) * 60 : 0;
        Collection toList = Stream.of(iterable).filter(new TariffUsageCalculator$$Lambda$0(j3, j4)).map(new TariffUsageCalculator$$Lambda$1(j3, j4)).toList();
        Collection toList2 = Stream.of(iterable2).filter(new TariffUsageCalculator$$Lambda$2(j3, j4)).filter(TariffUsageCalculator$$Lambda$3.$instance).map(new TariffUsageCalculator$$Lambda$4(j3, j4)).toList();
        Iterable<TariffTimeUsage> arrayList = new ArrayList();
        arrayList.addAll(toList);
        arrayList.addAll(toList2);
        iterable2 = new ArrayList();
        iterable2.add(new TariffTimeEvent(-1, j3, Type.DAY_START));
        iterable2.add(new TariffTimeEvent(-1, j4, Type.DAY_END));
        if (purchasedTariff2.validTo >= j3 && purchasedTariff2.validTo <= j4) {
            iterable2.add(new TariffTimeEvent(-1, purchasedTariff2.validTo, Type.TARIFF_END));
        }
        for (TariffTimeUsage tariffTimeUsage : arrayList) {
            iterable2.add(tariffTimeUsage.getStartEvent());
            iterable2.add(tariffTimeUsage.getEndEvent());
        }
        List toList3 = Stream.of(iterable2).withoutNulls().filter(new TariffUsageCalculator$$Lambda$5(j3, j4)).toList();
        Collections.sort(toList3, new TimestampComparator());
        ArrayList arrayList2 = new ArrayList();
        List<TariffTimeUsage> toList4 = Stream.of((Iterable) arrayList).filter(new TariffUsageCalculator$$Lambda$6(j3)).toList();
        int i = 0;
        long j5 = minutesPerDay;
        while (i < toList3.size()) {
            List list3;
            TariffTimeEvent tariffTimeEvent = (TariffTimeEvent) toList3.get(i);
            if (tariffTimeEvent.type == Type.DAY_START) {
                list3 = toList3;
            } else {
                long size;
                long j6;
                long j7 = tariffTimeEvent.timestampSeconds - j3;
                if (i > 0) {
                    j7 = tariffTimeEvent.timestampSeconds - ((TariffTimeEvent) toList3.get(i - 1)).timestampSeconds;
                }
                if (((long) toList4.size()) * j7 > j5) {
                    size = (long) (((double) j5) / ((double) toList4.size()));
                    j6 = 0;
                } else {
                    j6 = j5 - (((long) toList4.size()) * j7);
                    size = j7;
                }
                long j8 = j7 - size;
                for (TariffTimeUsage tariffTimeUsage2 : toList4) {
                    list3 = toList3;
                    tariffTimeUsage2.usedFreeSeconds += size;
                    tariffTimeUsage2.usedPaidSeconds += j8;
                    toList3 = list3;
                    j3 = j;
                }
                list3 = toList3;
                if (tariffTimeEvent.type != Type.RENT_END) {
                    if (tariffTimeEvent.type != Type.BOOKING_END) {
                        if (tariffTimeEvent.type != Type.RENT_START) {
                            if (tariffTimeEvent.type != Type.BOOKING_START) {
                                if (tariffTimeEvent.type == Type.TARIFF_END) {
                                    j5 = 0;
                                }
                                j5 = j6;
                            }
                        }
                        toList4.add(findItemForEvent(arrayList, tariffTimeEvent));
                        j5 = j6;
                    }
                }
                toList4.remove(findItemForEvent(toList4, tariffTimeEvent));
                j5 = j6;
            }
            i++;
            toList3 = list3;
            j3 = j;
        }
        return arrayList;
    }

    static final /* synthetic */ boolean lambda$calculateTimeUsage$88$TariffUsageCalculator(long j, long j2, RentalItem rentalItem) {
        return ((rentalItem.startTimestampSeconds > j || rentalItem.endTimestampSeconds <= j) && ((rentalItem.startTimestampSeconds < j || rentalItem.startTimestampSeconds > j2) && (rentalItem.startTimestampSeconds > j || rentalItem.endTimestampSeconds > 0))) ? 0 : 1;
    }

    static final /* synthetic */ boolean lambda$calculateTimeUsage$90$TariffUsageCalculator(long j, long j2, BookingItem bookingItem) {
        return ((bookingItem.getStartTimestampSeconds() > j || bookingItem.getEndTimestampSecondsForTariffSecondsCalculation() <= j) && ((bookingItem.getStartTimestampSeconds() < j || bookingItem.getStartTimestampSeconds() > j2) && (bookingItem.getStartTimestampSeconds() > j || bookingItem.getEndTimestampSecondsForTariffSecondsCalculation() > 0))) ? 0 : 1;
    }

    static final /* synthetic */ boolean lambda$calculateTimeUsage$91$TariffUsageCalculator(BookingItem bookingItem) {
        return bookingItem.getDurationSeconds() > 300 ? true : null;
    }

    static final /* synthetic */ boolean lambda$calculateTimeUsage$93$TariffUsageCalculator(long j, long j2, TariffTimeEvent tariffTimeEvent) {
        return (tariffTimeEvent.timestampSeconds < j || tariffTimeEvent.timestampSeconds > j2) ? 0 : 1;
    }

    static final /* synthetic */ boolean lambda$calculateTimeUsage$94$TariffUsageCalculator(long j, TariffTimeUsage tariffTimeUsage) {
        return tariffTimeUsage.itemStartTs <= j ? 1 : 0;
    }

    @Nullable
    private TariffTimeUsage findItemForEvent(List<TariffTimeUsage> list, TariffTimeEvent tariffTimeEvent) {
        if (tariffTimeEvent.type != Type.RENT_END) {
            if (tariffTimeEvent.type != Type.RENT_START) {
                if (tariffTimeEvent.type != Type.BOOKING_END) {
                    if (tariffTimeEvent.type != Type.BOOKING_START) {
                        return null;
                    }
                }
                return (TariffTimeUsage) Stream.of((Iterable) list).filter(TariffUsageCalculator$$Lambda$9.$instance).filter(new TariffUsageCalculator$$Lambda$10(tariffTimeEvent)).findFirst().orElse(null);
            }
        }
        return (TariffTimeUsage) Stream.of((Iterable) list).filter(TariffUsageCalculator$$Lambda$7.$instance).filter(new TariffUsageCalculator$$Lambda$8(tariffTimeEvent)).findFirst().orElse(null);
    }

    static final /* synthetic */ boolean lambda$findItemForEvent$96$TariffUsageCalculator(TariffTimeEvent tariffTimeEvent, TariffTimeUsage tariffTimeUsage) {
        return ((RentalItem) tariffTimeUsage.item).id == tariffTimeEvent.itemId ? true : null;
    }

    static final /* synthetic */ boolean lambda$findItemForEvent$98$TariffUsageCalculator(TariffTimeEvent tariffTimeEvent, TariffTimeUsage tariffTimeUsage) {
        return ((BookingItem) tariffTimeUsage.item).getId() == tariffTimeEvent.itemId ? true : null;
    }
}
