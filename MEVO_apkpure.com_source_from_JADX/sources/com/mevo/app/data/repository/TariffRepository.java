package com.mevo.app.data.repository;

import android.support.annotation.NonNull;
import com.annimon.stream.Stream;
import com.inverce.mod.core.IM;
import com.mevo.app.Cfg;
import com.mevo.app.constants.Tariffs;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.model.PurchasedTariff;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.SubscriptionBookingItem;
import com.mevo.app.data.model.Tariff;
import com.mevo.app.data.model.TariffExtraInfo;
import com.mevo.app.data.model.UserDetails;
import com.mevo.app.data.model.repository.ResponseActiveTariff;
import com.mevo.app.data.model.repository.ResponseCurrentTariffUsedSeconds;
import com.mevo.app.data.model.response.ResponseHistory;
import com.mevo.app.data.model.response.ResponsePurchaseVoucher;
import com.mevo.app.data.model.response.ResponsePurchasedTariffs;
import com.mevo.app.data.model.response.ResponseUpdateUser;
import com.mevo.app.data.model.response.ResponseUserDetails;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;
import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TariffRepository {
    public void getCurrentTariff(ResponseListener<ResponseActiveTariff> responseListener) {
        IM.onBg().execute(new TariffRepository$$Lambda$0(this, responseListener));
    }

    final /* synthetic */ void lambda$getCurrentTariff$67$TariffRepository(ResponseListener responseListener) {
        IM.onUi().execute(new TariffRepository$$Lambda$22(responseListener, getCurrentTariffSync()));
    }

    public void purchaseTariff(Tariff tariff, SimpleResponseListener simpleResponseListener) {
        if (((double) UserManager.getUser().getCredits()) < tariff.price) {
            IM.onUi().execute(new TariffRepository$$Lambda$1(simpleResponseListener));
        } else {
            IM.onBg().execute(new TariffRepository$$Lambda$2(this, simpleResponseListener, tariff));
        }
    }

    final /* synthetic */ void lambda$purchaseTariff$76$TariffRepository(SimpleResponseListener simpleResponseListener, Tariff tariff) {
        Throwable e;
        Throwable th;
        SimpleResponseListener simpleResponseListener2 = simpleResponseListener;
        TariffRepository tariffRepository;
        try {
            ResponseActiveTariff currentTariffSync = getCurrentTariffSync();
            ResponseHistory responseHistory = (ResponseHistory) Rest.getApiNextbike().fetchHistory(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), Integer.valueOf(20), Integer.valueOf(0)).execute().body();
            if (currentTariffSync.success && responseHistory != null) {
                if (responseHistory.accountHistory != null) {
                    if (currentTariffSync.currentActiveTariff != null) {
                        IM.onUi().execute(new TariffRepository$$Lambda$16(simpleResponseListener2));
                        return;
                    }
                    try {
                        if (hasActiveRentalOrBooking(responseHistory.accountHistory.getRentalItems(), responseHistory.accountHistory.getBookingItems())) {
                            IM.onUi().execute(new TariffRepository$$Lambda$17(simpleResponseListener2));
                            return;
                        }
                        long currentTimeMillis = System.currentTimeMillis() / 1000;
                        ResponseUpdateUser responseUpdateUser = (ResponseUpdateUser) Rest.getApiNextbike().updateSubscriptionExtraData(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), "", 0, 0, currentTimeMillis).execute().body();
                        if (responseUpdateUser != null) {
                            if (responseUpdateUser.userDetails.getTariffPurchaseTimestamp() == currentTimeMillis) {
                                ResponsePurchaseVoucher responsePurchaseVoucher = (ResponsePurchaseVoucher) Rest.getApiNextbike().purchaseVoucher(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), tariff.code).execute().body();
                                if (responsePurchaseVoucher == null || responsePurchaseVoucher.voucher == null) {
                                    IM.onUi().execute(new TariffRepository$$Lambda$20(simpleResponseListener2));
                                } else {
                                    IM.onUi().execute(new TariffRepository$$Lambda$19(simpleResponseListener2));
                                }
                            }
                        }
                        IM.onUi().execute(new TariffRepository$$Lambda$18(simpleResponseListener2));
                        return;
                    } catch (IOException e2) {
                        e = e2;
                        th = e;
                        Log.ex(th);
                        IM.onUi().execute(new TariffRepository$$Lambda$21(simpleResponseListener2, th));
                    }
                }
            }
            tariffRepository = this;
            IM.onUi().execute(new TariffRepository$$Lambda$15(simpleResponseListener2));
        } catch (IOException e3) {
            e = e3;
            tariffRepository = this;
            th = e;
            Log.ex(th);
            IM.onUi().execute(new TariffRepository$$Lambda$21(simpleResponseListener2, th));
        }
    }

    private boolean hasActiveRentalOrBooking(List<RentalItem> list, List<BookingItem> list2) {
        if (list == null) {
            Iterable arrayList = new ArrayList();
        }
        if (list2 == null) {
            Iterable arrayList2 = new ArrayList();
        }
        if (Stream.of(arrayList).filter(TariffRepository$$Lambda$3.$instance).count() <= 0) {
            if (Stream.of(arrayList2).filter(TariffRepository$$Lambda$4.$instance).count() <= null) {
                return null;
            }
        }
        return true;
    }

    @NonNull
    public ResponseCurrentTariffUsedSeconds getTariffSecondsUsedTodaySync() {
        TariffRepository tariffRepository = this;
        try {
            ResponseUserDetails responseUserDetails = (ResponseUserDetails) Rest.getApiNextbike().getUserDetails(Cfg.get().apikeyNextbike(), UserManager.getLoginKey()).execute().body();
            ResponseHistory responseHistory = (ResponseHistory) Rest.getApiNextbike().fetchHistory(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), Integer.valueOf(200), Integer.valueOf(0)).execute().body();
            ResponseActiveTariff currentTariffSync = getCurrentTariffSync();
            if (!(responseUserDetails == null || responseHistory == null || responseUserDetails.userDetails == null || responseHistory.accountHistory == null || !currentTariffSync.success)) {
                if (currentTariffSync.tariffExtraInfo != null) {
                    long sum = Stream.of(new TariffUsageCalculator().calculateTimeUsage(currentTariffSync.currentActiveTariff, currentTariffSync.tariffExtraInfo, Utils.getStartOfDayTimestampSeconds(), System.currentTimeMillis() / 1000, responseHistory.accountHistory.getRentalItems(), responseHistory.accountHistory.getBookingItems())).mapToLong(TariffRepository$$Lambda$5.$instance).sum();
                    long minutesPerDay = ((long) currentTariffSync.tariffExtraInfo.getMinutesPerDay()) * 60;
                    long startOfDayTimestampSeconds = Utils.getStartOfDayTimestampSeconds();
                    if (startOfDayTimestampSeconds > responseUserDetails.userDetails.getBookingHistoryLastUpdate()) {
                        Rest.getApiNextbike().updateUserBookingHistory(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), Rest.getJsonSerializer().toJson(getCusotmActiveBookingItems(responseUserDetails.userDetails, startOfDayTimestampSeconds)), System.currentTimeMillis() / 1000, calculateTariffSecondsUsedTodayByCustomBookingsHistory(responseUserDetails.userDetails, startOfDayTimestampSeconds, false).longValue()).execute();
                    }
                    return new ResponseCurrentTariffUsedSeconds(sum > minutesPerDay ? minutesPerDay : sum, minutesPerDay, true);
                }
            }
            return new ResponseCurrentTariffUsedSeconds(0, 0, false);
        } catch (Throwable e) {
            Log.ex(e);
            return new ResponseCurrentTariffUsedSeconds(0, 0, false);
        }
    }

    @Deprecated
    public boolean updateCustomBookingHistoryNewBookingSync(int i, long j) {
        TariffRepository tariffRepository = this;
        int i2 = i;
        boolean z = false;
        try {
            ResponseUserDetails responseUserDetails = (ResponseUserDetails) Rest.getApiNextbike().getUserDetails(Cfg.get().apikeyNextbike(), UserManager.getLoginKey()).execute().body();
            if (responseUserDetails != null) {
                if (responseUserDetails.userDetails != null) {
                    long startOfDayTimestampSeconds = Utils.getStartOfDayTimestampSeconds();
                    Object cusotmActiveBookingItems = getCusotmActiveBookingItems(responseUserDetails.userDetails, startOfDayTimestampSeconds);
                    long longValue = calculateTariffSecondsUsedTodayByCustomBookingsHistory(responseUserDetails.userDetails, startOfDayTimestampSeconds, false).longValue();
                    if ((Stream.of(responseUserDetails.userDetails.getBookingHistoryParsed()).filter(new TariffRepository$$Lambda$6(i2)).findFirst().orElse(null) != null ? 1 : null) == null) {
                        cusotmActiveBookingItems.add(new SubscriptionBookingItem(i2, j));
                    }
                    ResponseUpdateUser responseUpdateUser = (ResponseUpdateUser) Rest.getApiNextbike().updateUserBookingHistory(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), Rest.getJsonSerializer().toJson(cusotmActiveBookingItems), System.currentTimeMillis() / 1000, longValue).execute().body();
                    IM.onUi().execute(TariffRepository$$Lambda$7.$instance);
                    if (!(responseUpdateUser == null || responseUpdateUser.userDetails == null)) {
                        z = true;
                    }
                    return z;
                }
            }
            return false;
        } catch (Throwable e) {
            Log.ex(e);
            return false;
        }
    }

    /* renamed from: lambda$updateCustomBookingHistoryNewBookingSync$80$TariffRepository */
    static final /* synthetic */ boolean m79x870b14dc(int i, SubscriptionBookingItem subscriptionBookingItem) {
        return subscriptionBookingItem.getBookingId() == i;
    }

    @Deprecated
    public boolean updateCustomBookingHistoryBookingFinished(int i) {
        boolean z = false;
        try {
            ResponseUserDetails responseUserDetails = (ResponseUserDetails) Rest.getApiNextbike().getUserDetails(Cfg.get().apikeyNextbike(), UserManager.getLoginKey()).execute().body();
            if (responseUserDetails != null) {
                if (responseUserDetails.userDetails != null) {
                    long usedSubscriptionSeconds;
                    long startOfDayTimestampSeconds = Utils.getStartOfDayTimestampSeconds();
                    Object cusotmActiveBookingItems = getCusotmActiveBookingItems(responseUserDetails.userDetails, startOfDayTimestampSeconds);
                    long longValue = calculateTariffSecondsUsedTodayByCustomBookingsHistory(responseUserDetails.userDetails, startOfDayTimestampSeconds, false).longValue();
                    SubscriptionBookingItem subscriptionBookingItem = (SubscriptionBookingItem) Stream.of((Iterable) cusotmActiveBookingItems).filter(new TariffRepository$$Lambda$8(i)).findFirst().orElse(null);
                    if (subscriptionBookingItem != null) {
                        cusotmActiveBookingItems.remove(subscriptionBookingItem);
                        usedSubscriptionSeconds = longValue + subscriptionBookingItem.getUsedSubscriptionSeconds();
                    } else {
                        usedSubscriptionSeconds = longValue;
                    }
                    ResponseUpdateUser responseUpdateUser = (ResponseUpdateUser) Rest.getApiNextbike().updateUserBookingHistory(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), Rest.getJsonSerializer().toJson(cusotmActiveBookingItems), System.currentTimeMillis() / 1000, usedSubscriptionSeconds).execute().body();
                    IM.onUi().execute(TariffRepository$$Lambda$9.$instance);
                    if (!(responseUpdateUser == null || responseUpdateUser.userDetails == 0)) {
                        z = true;
                    }
                    return z;
                }
            }
            return false;
        } catch (int i2) {
            Log.ex(i2);
            return false;
        }
    }

    /* renamed from: lambda$updateCustomBookingHistoryBookingFinished$82$TariffRepository */
    static final /* synthetic */ boolean m77xa06faf4d(int i, SubscriptionBookingItem subscriptionBookingItem) {
        return subscriptionBookingItem.getBookingId() == i;
    }

    @Deprecated
    private List<SubscriptionBookingItem> getCusotmActiveBookingItems(UserDetails userDetails, long j) {
        return Stream.of(userDetails.getBookingHistoryParsed()).filter(new TariffRepository$$Lambda$10(j)).filter(TariffRepository$$Lambda$11.$instance).toList();
    }

    @Deprecated
    private Long calculateTariffSecondsUsedTodayByCustomBookingsHistory(UserDetails userDetails, long j, boolean z) {
        List toList = Stream.of(userDetails.getBookingHistoryParsed()).filter(new TariffRepository$$Lambda$12(j)).toList();
        if (!z) {
            toList = Stream.of((Iterable) toList).filter(TariffRepository$$Lambda$13.$instance).toList();
        }
        userDetails = userDetails.getBookingHistoryLastUpdate() >= j ? (long) userDetails.getSubscriptionDailySecondsUsed() : 0;
        for (SubscriptionBookingItem usedSubscriptionSeconds : r0) {
            userDetails += usedSubscriptionSeconds.getUsedSubscriptionSeconds();
        }
        return Long.valueOf(userDetails);
    }

    @NonNull
    public ResponseActiveTariff getCurrentTariffSync() {
        return getTariffActiveOnTsSync(System.currentTimeMillis() / 1000);
    }

    @NonNull
    public ResponseActiveTariff getTariffActiveOnTsSync(long j) {
        try {
            ResponsePurchasedTariffs responsePurchasedTariffs = (ResponsePurchasedTariffs) Rest.getApiNextbike().getPurchasedTariffs(Cfg.get().apikeyNextbike(), UserManager.getLoginKey()).execute().body();
            if (responsePurchasedTariffs == null) {
                return new ResponseActiveTariff(null, null, false, null);
            }
            j = responsePurchasedTariffs.getTariffActiveOnTs(j);
            TariffExtraInfo tariffExtraInfo = Tariffs.Tariff_PAY_AS_YOU_GO;
            if (j != null) {
                tariffExtraInfo = (TariffExtraInfo) Stream.of(Tariffs.ALL_SUBSCRIPTIONS).filter(new TariffRepository$$Lambda$14(j)).findFirst().orElse(Tariffs.Tariff_PAY_AS_YOU_GO);
            }
            return new ResponseActiveTariff(j, tariffExtraInfo, true, null);
        } catch (long j2) {
            return new ResponseActiveTariff(null, null, false, j2);
        }
    }

    static final /* synthetic */ boolean lambda$getTariffActiveOnTsSync$87$TariffRepository(PurchasedTariff purchasedTariff, TariffExtraInfo tariffExtraInfo) {
        return tariffExtraInfo.getUid() == purchasedTariff.id ? true : null;
    }
}
