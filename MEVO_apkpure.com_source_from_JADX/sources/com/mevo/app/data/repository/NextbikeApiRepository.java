package com.mevo.app.data.repository;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.annimon.stream.Stream;
import com.google.maps.android.PolyUtil;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.constants.GoogleDirectionsApi.Mode;
import com.mevo.app.constants.Tariffs;
import com.mevo.app.data.exceptions.CantGetLocationException;
import com.mevo.app.data.model.AccountHistory;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.model.GoogleRoute;
import com.mevo.app.data.model.MapStationData;
import com.mevo.app.data.model.PaymentLinkSingle;
import com.mevo.app.data.model.Place;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.RentalWithBattery;
import com.mevo.app.data.model.StationJson;
import com.mevo.app.data.model.Tariff;
import com.mevo.app.data.model.TariffExtraInfo;
import com.mevo.app.data.model.User;
import com.mevo.app.data.model.Voucher;
import com.mevo.app.data.model.event.EventDataBikeReturn;
import com.mevo.app.data.model.repository.ResponseActiveTariff;
import com.mevo.app.data.model.repository.ResponseCurrentRentalsAndBookings;
import com.mevo.app.data.model.repository.TariffTimeRange;
import com.mevo.app.data.model.response.ResponseActiveBookings;
import com.mevo.app.data.model.response.ResponseBikeStateJson;
import com.mevo.app.data.model.response.ResponseBookBike;
import com.mevo.app.data.model.response.ResponseCancelBooking;
import com.mevo.app.data.model.response.ResponseGoogleRoute;
import com.mevo.app.data.model.response.ResponseHistory;
import com.mevo.app.data.model.response.ResponsePaymentForm;
import com.mevo.app.data.model.response.ResponsePaymentLink;
import com.mevo.app.data.model.response.ResponsePurchaseVoucher;
import com.mevo.app.data.model.response.ResponseReturnBike;
import com.mevo.app.data.model.response.ResponseStationDetails;
import com.mevo.app.data.model.response.ResponseTariffs;
import com.mevo.app.data.model.response.ResponseUserDetails;
import com.mevo.app.data.network.ApiExtended;
import com.mevo.app.data.network.Rest;
import com.mevo.app.tools.LocaleHelper;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.Tools;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.Utils;
import com.mevo.app.tools.location.LocationTool;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NextbikeApiRepository {
    private static final long BOOKING_TIME_OFFSET_SECONDS = 3;
    private static final String TAG = "NextbikeApiRepository";

    public interface BookingAvailabilityListener {
        void onResponse(boolean z, boolean z2);
    }

    public interface BookingListener {
        void onResponse(BookingItem bookingItem, boolean z, boolean z2);
    }

    public interface ResponseBatteryReport {
        void onResponse(Boolean bool, Boolean bool2, @Nullable Exception exception);
    }

    public interface ResponseCurrentRentalsAndBookingsListener {
        void onResponse(List<BookingItem> list, List<RentalWithBattery> list2, boolean z, @Nullable Exception exception);
    }

    public interface ResponseListener2<T, V> {
        void onResponse(T t, V v, boolean z, @Nullable Exception exception);
    }

    public interface ResponseListener<T> {
        void onResponse(T t, boolean z, @Nullable Exception exception);
    }

    public interface SimpleResponseListener {
        void onResponse(boolean z, @Nullable Exception exception);
    }

    public void bookBike(String str, @NonNull BookingListener bookingListener) {
        IM.onBg().execute(new NextbikeApiRepository$$Lambda$0(bookingListener, str));
    }

    static final /* synthetic */ void lambda$bookBike$23$NextbikeApiRepository(@NonNull BookingListener bookingListener, String str) {
        try {
            ResponseUserDetails responseUserDetails = (ResponseUserDetails) Rest.getApiNextbike().getUserDetails(Cfg.get().apikeyNextbike(), UserManager.getLoginKey()).execute().body();
            long currentTimeMillis = (System.currentTimeMillis() - System.currentTimeMillis()) / 1000;
            if (responseUserDetails != null) {
                if (responseUserDetails.userDetails != null) {
                    if (Utils.intToBool(responseUserDetails.userDetails.getIsActive())) {
                        long j = (responseUserDetails.serverTime + currentTimeMillis) + 3;
                        String str2 = str;
                        ResponseBookBike responseBookBike = (ResponseBookBike) Rest.getApiNextbike().bookBike(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), 1, str2, Long.valueOf(j), Long.valueOf(j + 900)).execute().body();
                        if (responseBookBike == null || responseBookBike.getBookingItem() == null) {
                            IM.onUi().execute(new NextbikeApiRepository$$Lambda$31(bookingListener));
                        } else {
                            BookingItem bookingItem = responseBookBike.getBookingItem();
                            new TariffRepository().updateCustomBookingHistoryNewBookingSync(bookingItem.id, bookingItem.getStartTimestampSeconds());
                            IM.onUi().execute(new NextbikeApiRepository$$Lambda$30(bookingListener, responseBookBike));
                        }
                        return;
                    }
                    IM.onUi().execute(new NextbikeApiRepository$$Lambda$29(bookingListener));
                    return;
                }
            }
            IM.onUi().execute(new NextbikeApiRepository$$Lambda$28(bookingListener));
        } catch (String str3) {
            Log.ex(TAG, str3);
            IM.onUi().execute(new NextbikeApiRepository$$Lambda$32(bookingListener));
        }
    }

    public void parkBike(String str, String str2, final SimpleResponseListener simpleResponseListener) {
        String coordinateToApiString;
        String str3;
        str = LocationTool.get().getLastKnownLocation();
        if (str != null) {
            String coordinateToApiString2 = Tools.coordinateToApiString(str.getLatitude());
            coordinateToApiString = Tools.coordinateToApiString(str.getLongitude());
            str3 = coordinateToApiString2;
        } else {
            str3 = null;
            coordinateToApiString = str3;
        }
        Rest.getApiNextbike().rentalBreak(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), str2, null, str3, coordinateToApiString).enqueue(new Callback<ResponseReturnBike>() {
            public void onResponse(Call<ResponseReturnBike> call, Response<ResponseReturnBike> response) {
                ResponseReturnBike responseReturnBike = (ResponseReturnBike) response.body();
                if (response.isSuccessful() == null || responseReturnBike == null || responseReturnBike.rental == null) {
                    simpleResponseListener.onResponse(null, null);
                } else {
                    simpleResponseListener.onResponse(true, null);
                }
            }

            public void onFailure(Call<ResponseReturnBike> call, Throwable th) {
                simpleResponseListener.onResponse(null, null);
            }
        });
    }

    public void cancelBooking(final BookingItem bookingItem, final SimpleResponseListener simpleResponseListener) {
        Rest.getApiNextbike().cancelBooking(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), bookingItem.getId(), 2).enqueue(new Callback<ResponseCancelBooking>() {
            public void onResponse(Call<ResponseCancelBooking> call, Response<ResponseCancelBooking> response) {
                ResponseCancelBooking responseCancelBooking = (ResponseCancelBooking) response.body();
                if (response.isSuccessful() == null || responseCancelBooking == null || responseCancelBooking.getCanceledBooking() == null) {
                    simpleResponseListener.onResponse(null, null);
                    return;
                }
                IM.onBg().execute(new NextbikeApiRepository$2$$Lambda$0(bookingItem));
                simpleResponseListener.onResponse(true, null);
            }

            public void onFailure(Call<ResponseCancelBooking> call, Throwable th) {
                simpleResponseListener.onResponse(null, null);
            }
        });
    }

    public void cancelBookingForRental(RentalItem rentalItem, SimpleResponseListener simpleResponseListener) {
        IM.onBg().execute(new NextbikeApiRepository$$Lambda$1(rentalItem, simpleResponseListener));
    }

    static final /* synthetic */ void lambda$cancelBookingForRental$29$NextbikeApiRepository(RentalItem rentalItem, SimpleResponseListener simpleResponseListener) {
        try {
            ResponseActiveBookings responseActiveBookings = (ResponseActiveBookings) Rest.getApiNextbike().getActiveBookings(Cfg.get().apikeyNextbike(), UserManager.getLoginKey()).execute().body();
            BookingItem bookingItem = null;
            if (responseActiveBookings != null && responseActiveBookings.bookingItems != null) {
                for (BookingItem bookingItem2 : responseActiveBookings.bookingItems) {
                    if (bookingItem2.getPlaceId().equals(rentalItem.startPlaceId)) {
                        bookingItem = bookingItem2;
                        break;
                    }
                }
            }
            if (bookingItem == null) {
                IM.onUi().execute(new NextbikeApiRepository$$Lambda$24(simpleResponseListener));
                return;
            }
            ResponseCancelBooking responseCancelBooking = (ResponseCancelBooking) Rest.getApiNextbike().cancelBooking(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), bookingItem.id, 2).execute().body();
            if (responseCancelBooking == null || responseCancelBooking.getCanceledBooking() == null || responseCancelBooking.getCanceledBooking().getId().equals(Integer.valueOf(bookingItem.id)) == null) {
                IM.onUi().execute(new NextbikeApiRepository$$Lambda$26(simpleResponseListener));
            } else {
                IM.onUi().execute(new NextbikeApiRepository$$Lambda$25(simpleResponseListener));
            }
        } catch (RentalItem rentalItem2) {
            Log.ex(TAG, rentalItem2);
            IM.onUi().execute(new NextbikeApiRepository$$Lambda$27(simpleResponseListener));
        }
    }

    public void updateBookingHistoryIfRentalWasBooked(RentalItem rentalItem, SimpleResponseListener simpleResponseListener) {
        IM.onBg().execute(new NextbikeApiRepository$$Lambda$2(rentalItem, simpleResponseListener));
    }

    /* renamed from: lambda$updateBookingHistoryIfRentalWasBooked$33$NextbikeApiRepository */
    static final /* synthetic */ void m74x9ffe2ba1(RentalItem rentalItem, SimpleResponseListener simpleResponseListener) {
        try {
            ResponseActiveBookings responseActiveBookings = (ResponseActiveBookings) Rest.getApiNextbike().getActiveBookings(Cfg.get().apikeyNextbike(), UserManager.getLoginKey()).execute().body();
            BookingItem bookingItem = null;
            if (responseActiveBookings != null && responseActiveBookings.bookingItems != null) {
                for (BookingItem bookingItem2 : responseActiveBookings.bookingItems) {
                    if (bookingItem2.getPlaceId().equals(rentalItem.startPlaceId)) {
                        bookingItem = bookingItem2;
                        break;
                    }
                }
            }
            if (bookingItem == null) {
                IM.onUi().execute(new NextbikeApiRepository$$Lambda$21(simpleResponseListener));
                return;
            }
            IM.onUi().execute(new NextbikeApiRepository$$Lambda$22(simpleResponseListener, new TariffRepository().updateCustomBookingHistoryBookingFinished(bookingItem.id)));
        } catch (RentalItem rentalItem2) {
            Log.ex(TAG, rentalItem2);
            IM.onUi().execute(new NextbikeApiRepository$$Lambda$23(simpleResponseListener));
        }
    }

    public void resumeRenting(String str, String str2, final SimpleResponseListener simpleResponseListener) {
        String coordinateToApiString;
        String str3;
        str = LocationTool.get().getLastKnownLocation();
        if (str != null) {
            String coordinateToApiString2 = Tools.coordinateToApiString(str.getLatitude());
            coordinateToApiString = Tools.coordinateToApiString(str.getLongitude());
            str3 = coordinateToApiString2;
        } else {
            str3 = null;
            coordinateToApiString = str3;
        }
        Rest.getApiNextbike().rentalBreak(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), str2, Boolean.valueOf(true), str3, coordinateToApiString).enqueue(new Callback<ResponseReturnBike>() {
            public void onResponse(Call<ResponseReturnBike> call, Response<ResponseReturnBike> response) {
                ResponseReturnBike responseReturnBike = (ResponseReturnBike) response.body();
                if (response.isSuccessful() == null || responseReturnBike == null || responseReturnBike.rental == null) {
                    simpleResponseListener.onResponse(null, null);
                } else {
                    simpleResponseListener.onResponse(true, null);
                }
            }

            public void onFailure(Call<ResponseReturnBike> call, Throwable th) {
                simpleResponseListener.onResponse(null, null);
            }
        });
    }

    public void returnBikeOutsideStation(String str, String str2, ResponseListener<RentalItem> responseListener) {
        LocationTool.get().getCurrentLocationAsync(new NextbikeApiRepository$$Lambda$3(this, responseListener, str, str2));
    }

    final /* synthetic */ void lambda$returnBikeOutsideStation$34$NextbikeApiRepository(ResponseListener responseListener, String str, String str2, Location location) {
        final ResponseListener responseListener2 = responseListener;
        if (location == null) {
            responseListener2.onResponse(null, false, new CantGetLocationException());
            return;
        }
        Log.m94i(TAG, "Received correct location, proceeding with return bike request");
        String str3 = str;
        Rest.getApiNextbike().returnBikeOutsideStation(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), str3, "BIKE_".concat(str2), Tools.coordinateToApiString(location.getLatitude()), Tools.coordinateToApiString(location.getLongitude()), Tools.coordinateToApiString((double) location.getAccuracy()), 1, 1).enqueue(new Callback<ResponseReturnBike>() {
            public void onResponse(Call<ResponseReturnBike> call, Response<ResponseReturnBike> response) {
                ResponseReturnBike responseReturnBike = (ResponseReturnBike) response.body();
                if (response.isSuccessful() == null || responseReturnBike == null || responseReturnBike.rental == null) {
                    responseListener2.onResponse(null, null, null);
                    return;
                }
                EventBus.getDefault().post(new EventDataBikeReturn(responseReturnBike.rental.bikeNumber));
                responseListener2.onResponse(responseReturnBike.rental, true, null);
            }

            public void onFailure(Call<ResponseReturnBike> call, Throwable th) {
                responseListener2.onResponse(null, false, null);
            }
        });
    }

    public void getTransactionsHistory(@NonNull final ResponseListener<Pair<AccountHistory, List<TariffTimeRange>>> responseListener) {
        Rest.getApiNextbike().fetchHistoryNoCache(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), Integer.valueOf(1000), null).enqueue(new Callback<ResponseHistory>() {
            public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
                ResponseHistory responseHistory = (ResponseHistory) response.body();
                if (response.isSuccessful() == null || responseHistory == null || responseHistory.accountHistory == null) {
                    responseListener.onResponse(null, null, null);
                    return;
                }
                responseListener.onResponse(new Pair(responseHistory.accountHistory, NextbikeApiRepository.this.getSubscriptionsInTimeRanges(responseHistory.accountHistory)), true, null);
            }

            public void onFailure(Call<ResponseHistory> call, Throwable th) {
                responseListener.onResponse(null, false, null);
            }
        });
    }

    @Nullable
    public List<TariffTimeRange> getSubscriptionsInTimeRanges(@NonNull AccountHistory accountHistory) {
        List<TariffTimeRange> arrayList = new ArrayList();
        for (Voucher voucher : accountHistory.getVoucherHistoryItems()) {
            TariffExtraInfo forCode = Tariffs.getForCode(voucher.code);
            if (forCode != null) {
                arrayList.add(new TariffTimeRange(voucher.purchaseDateTimestampSeconds, voucher.purchaseDateTimestampSeconds + (((((long) forCode.getDaysValid()) * 24) * 60) * 60), forCode));
            }
        }
        return arrayList;
    }

    public void getPaymentForm(List<Integer> list, @NonNull ResponseListener<String> responseListener) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<style>");
        stringBuilder.append("input[type=submit] {\n    width: 85%;\n    margin: 35px auto 0;\n    display: block;\n    height: 100px;\n    line-height: 100px;\n    border: 2px solid rgb(40,52,98);\n    border-radius: 10px;\n    box-shadow: 0 3px 8px 0 rgba(0,0,0,.2),0 5px 3px -3px rgba(0,0,0,.12),0 3px 3px 0 rgba(0,0,0,.14);\n    background-color: #FFF;\n    font-size: 39px;\n    font-weight: 500;\n    color: rgb(40,52,98);\n}");
        stringBuilder.append("");
        stringBuilder.append("input[type=image]{width:100%;max-width:100%;margin-top: 84px; }");
        stringBuilder.append("</style>");
        IM.onBg().execute(new NextbikeApiRepository$$Lambda$4(list, responseListener, stringBuilder.toString()));
    }

    static final /* synthetic */ void lambda$getPaymentForm$39$NextbikeApiRepository(List list, @NonNull ResponseListener responseListener, String str) {
        ResponseListener responseListener2 = responseListener;
        String str2 = "";
        try {
            String stringBuilder;
            ResponsePaymentLink responsePaymentLink = (ResponsePaymentLink) Rest.getApiNextbike().getPaymentList(Cfg.get().apikeyNextbike(), UserManager.getLoginKey()).execute().body();
            String str3 = null;
            if (!(UserManager.getUserDetails() == null || responsePaymentLink == null)) {
                for (PaymentLinkSingle paymentLinkSingle : responsePaymentLink.paymentLinks.paymentLinkSingles) {
                    if (PaymentLinkSingle.PAYMENT_CREDIT_CARD.equals(paymentLinkSingle.option)) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("<form action=\"https://custom_amount_url.pl\" method=\"get\" target=\"_self\">\n<input type=\"hidden\" name=\"L\" value=\"");
                        stringBuilder2.append(UserManager.getUserDetails().getLanguage().toLowerCase());
                        stringBuilder2.append("\">\n");
                        stringBuilder2.append("<input type=\"submit\" value=\"");
                        stringBuilder2.append(IM.resources().getString(C0434R.string.topup_custom_amout).toUpperCase());
                        stringBuilder2.append("\"><br/>\n");
                        stringBuilder2.append("</form>");
                        str3 = stringBuilder2.toString();
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("<form  action=\"");
                        stringBuilder2.append(paymentLinkSingle.linkUrl);
                        stringBuilder2.append("\" method=\"get\" target=\"_self\">\n");
                        stringBuilder2.append("<input type=\"hidden\" name=\"L\" value=\"");
                        stringBuilder2.append(UserManager.getUserDetails().getLanguage().toLowerCase());
                        stringBuilder2.append("\">\n");
                        stringBuilder2.append("<input type=\"submit\" value=\"");
                        stringBuilder2.append(IM.resources().getString(C0434R.string.credit_card).toUpperCase());
                        stringBuilder2.append("\"><br/>\n");
                        stringBuilder2.append("</form>");
                        stringBuilder = stringBuilder2.toString();
                        break;
                    }
                }
            }
            stringBuilder = null;
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("<form action=\"https://custom_voucher_url.pl\" method=\"get\" target=\"_self\">\n<input type=\"hidden\" name=\"L\" value=\"");
            stringBuilder3.append(UserManager.getUserDetails().getLanguage().toLowerCase());
            stringBuilder3.append("\">\n");
            stringBuilder3.append("<input type=\"submit\" value=\"");
            stringBuilder3.append(IM.resources().getString(C0434R.string.use_voucher).toUpperCase());
            stringBuilder3.append("\"><br/>\n");
            stringBuilder3.append("</form>");
            String stringBuilder4 = stringBuilder3.toString();
            for (Integer num : list) {
                ResponsePaymentForm responsePaymentForm = (ResponsePaymentForm) Rest.getApiNextbike().getPaymentForm(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), num).execute().body();
                if (responsePaymentForm != null) {
                    if (stringBuilder != null) {
                        String str4 = responsePaymentForm.paymentForm;
                        if (str4 == null) {
                            str4 = "";
                        }
                        Object replaceFirst = str4.replaceFirst("<input.*?type=\\\"image\\\".*?>", "");
                        Matcher matcher = Pattern.compile("<input.*?type=\"submit\".*?value=\".*?>").matcher(replaceFirst);
                        if (matcher.find()) {
                            CharSequence group = matcher.group();
                            StringBuilder stringBuilder5 = new StringBuilder();
                            stringBuilder5.append("value=\"");
                            Context context = IM.context();
                            Integer[] numArr = new Object[1];
                            numArr[0] = Integer.valueOf((int) (((double) num.intValue()) / 100.0d));
                            stringBuilder5.append(context.getString(C0434R.string.mevo_payment_from, numArr));
                            stringBuilder5.append("\\\"");
                            String replace = replaceFirst.replace(group, group.replaceAll("value=\\\".*?\\\"", stringBuilder5.toString()));
                            StringBuilder stringBuilder6 = new StringBuilder();
                            stringBuilder6.append(str2);
                            stringBuilder6.append(replace);
                            str2 = stringBuilder6.toString();
                        }
                    }
                }
                IM.onUi().execute(new NextbikeApiRepository$$Lambda$17(responseListener2));
                return;
            }
            StringBuilder stringBuilder7 = new StringBuilder();
            stringBuilder7.append(str);
            stringBuilder7.append(str2);
            stringBuilder7.append(str3);
            stringBuilder7.append(stringBuilder);
            stringBuilder7.append(stringBuilder4);
            str2 = stringBuilder7.toString();
            if (str2.isEmpty()) {
                IM.onUi().execute(new NextbikeApiRepository$$Lambda$19(responseListener2));
            } else {
                IM.onUi().execute(new NextbikeApiRepository$$Lambda$18(responseListener2, str2));
            }
        } catch (Throwable e) {
            Throwable th = e;
            Log.ex(th);
            IM.onUi().execute(new NextbikeApiRepository$$Lambda$20(responseListener2, th));
        }
    }

    public void getSubscriptions(Context context, final ResponseListener<List<Tariff>> responseListener) {
        if (context == null) {
            responseListener.onResponse(null, null, null);
        } else {
            Rest.getApiNextbike().getTariffs(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), Cfg.get().domain().getDomainCode(), LocaleHelper.getLanguage(context)).enqueue(new Callback<ResponseTariffs>() {
                public void onResponse(Call<ResponseTariffs> call, Response<ResponseTariffs> response) {
                    if (response.body() == null || ((ResponseTariffs) response.body()).tarrifs == null) {
                        responseListener.onResponse(null, null, null);
                    } else {
                        responseListener.onResponse(((ResponseTariffs) response.body()).tarrifs, true, null);
                    }
                }

                public void onFailure(Call<ResponseTariffs> call, Throwable th) {
                    responseListener.onResponse(null, false, new Exception(th));
                }
            });
        }
    }

    public void hasActiveRentals(final ResponseListener<Boolean> responseListener) {
        User user = UserManager.getUser();
        if (user == null) {
            responseListener.onResponse(null, false, null);
        } else {
            Rest.getApiNextbike().fetchHistory(Cfg.get().apikeyNextbike(), user.getLoginkey(), Integer.valueOf(20), Integer.valueOf(0)).enqueue(new Callback<ResponseHistory>() {
                public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
                    ResponseHistory responseHistory = (ResponseHistory) response.body();
                    if (response.isSuccessful() == null || responseHistory == null) {
                        responseListener.onResponse(null, false, null);
                    } else if (responseHistory.accountHistory != null) {
                        responseListener.onResponse(Boolean.valueOf(Stream.of(responseHistory.accountHistory.getRentalItems()).filter(NextbikeApiRepository$7$$Lambda$0.$instance).toList().isEmpty() ^ 1), true, null);
                    } else {
                        responseListener.onResponse(Boolean.valueOf(false), true, null);
                    }
                }

                public void onFailure(Call<ResponseHistory> call, Throwable th) {
                    responseListener.onResponse(null, false, new Exception(th));
                }
            });
        }
    }

    public void getUserCardRfidUid(final ResponseListener<String> responseListener) {
        User user = UserManager.getUser();
        if (user == null) {
            responseListener.onResponse(null, false, null);
        } else {
            Rest.getApiNextbikeShortTimeout().getUserDetails(Cfg.get().apikeyNextbike(), user.getLoginkey()).enqueue(new Callback<ResponseUserDetails>() {
                public void onResponse(Call<ResponseUserDetails> call, Response<ResponseUserDetails> response) {
                    if (response.body() == null || ((ResponseUserDetails) response.body()).userDetails == null || response.isSuccessful() == null) {
                        responseListener.onResponse(null, true, null);
                    } else {
                        responseListener.onResponse(((ResponseUserDetails) response.body()).userDetails.getMultipleRfidUid(), true, null);
                    }
                }

                public void onFailure(Call<ResponseUserDetails> call, Throwable th) {
                    responseListener.onResponse(null, false, new Exception(th));
                }
            });
        }
    }

    public void getCurrentRentalsWithBatteryAndBookings(String str, @NonNull ResponseCurrentRentalsAndBookingsListener responseCurrentRentalsAndBookingsListener) {
        IM.onBg().execute(new NextbikeApiRepository$$Lambda$5(this, str, responseCurrentRentalsAndBookingsListener));
    }

    /* renamed from: lambda$getCurrentRentalsWithBatteryAndBookings$43$NextbikeApiRepository */
    final /* synthetic */ void m75xdcb83036(String str, @NonNull ResponseCurrentRentalsAndBookingsListener responseCurrentRentalsAndBookingsListener) {
        str = getCurrentRentalsWithBatteryAndBookingsSync(str);
        if (str.rentals == null || str.bookings == null) {
            IM.onUi().execute(new NextbikeApiRepository$$Lambda$16(responseCurrentRentalsAndBookingsListener));
        } else {
            IM.onUi().execute(new NextbikeApiRepository$$Lambda$15(responseCurrentRentalsAndBookingsListener, str));
        }
    }

    @NonNull
    public ResponseCurrentRentalsAndBookings getCurrentRentalsWithBatteryAndBookingsSync(String str) {
        List arrayList;
        User user = UserManager.getUser();
        String loginkey = user != null ? user.getLoginkey() : "";
        List toList;
        try {
            ResponseActiveBookings responseActiveBookings;
            Iterable arrayList2;
            ResponseHistory responseHistory = (ResponseHistory) Rest.getApiNextbike().fetchHistory(Cfg.get().apikeyNextbike(), str, Integer.valueOf(20), null).execute().body();
            if (responseHistory != null) {
                arrayList = new ArrayList();
                try {
                    List arrayList3 = new ArrayList();
                    if (responseHistory.accountHistory != null) {
                        arrayList3 = Stream.of(responseHistory.accountHistory.getRentalItems()).filter(NextbikeApiRepository$$Lambda$6.$instance).toList();
                    }
                    for (RentalItem rentalItem : r4) {
                        ResponseBikeStateJson responseBikeStateJson = (ResponseBikeStateJson) Rest.getApiNextbikeJson().getBikeState(Cfg.get().apikeyNextbike(), loginkey, rentalItem.bikeNumber).execute().body();
                        if (responseBikeStateJson != null && responseBikeStateJson.bike != null) {
                            arrayList.add(new RentalWithBattery(rentalItem, responseBikeStateJson.bike.getPedelecBatteryPercent()));
                        }
                    }
                    responseActiveBookings = (ResponseActiveBookings) Rest.getApiNextbike().getActiveBookings(Cfg.get().apikeyNextbike(), str).execute().body();
                    if (responseActiveBookings == null) {
                        arrayList2 = responseActiveBookings.bookingItems == null ? responseActiveBookings.bookingItems : new ArrayList();
                        try {
                            toList = Stream.of(arrayList2).filter(NextbikeApiRepository$$Lambda$7.$instance).toList();
                        } catch (IOException e) {
                            IOException iOException = e;
                            toList = arrayList2;
                            str = iOException;
                            Log.ex(str);
                            if (arrayList != null) {
                            }
                            return new ResponseCurrentRentalsAndBookings(false, null, null);
                        }
                    }
                    toList = null;
                } catch (IOException e2) {
                    str = e2;
                    toList = null;
                    Log.ex(str);
                    if (arrayList != null) {
                    }
                    return new ResponseCurrentRentalsAndBookings(false, null, null);
                }
                if (arrayList != null || toList == null) {
                    return new ResponseCurrentRentalsAndBookings(false, null, null);
                }
                return new ResponseCurrentRentalsAndBookings(true, arrayList, toList);
            }
            arrayList = null;
            responseActiveBookings = (ResponseActiveBookings) Rest.getApiNextbike().getActiveBookings(Cfg.get().apikeyNextbike(), str).execute().body();
            if (responseActiveBookings == null) {
                toList = null;
            } else {
                if (responseActiveBookings.bookingItems == null) {
                }
                toList = Stream.of(arrayList2).filter(NextbikeApiRepository$$Lambda$7.$instance).toList();
            }
        } catch (IOException e3) {
            str = e3;
            toList = null;
            arrayList = toList;
            Log.ex(str);
            if (arrayList != null) {
            }
            return new ResponseCurrentRentalsAndBookings(false, null, null);
        }
        if (arrayList != null) {
        }
        return new ResponseCurrentRentalsAndBookings(false, null, null);
    }

    /* renamed from: lambda$getCurrentRentalsWithBatteryAndBookingsSync$45$NextbikeApiRepository */
    static final /* synthetic */ boolean m73xc847b0dd(BookingItem bookingItem) {
        return bookingItem.getStateId() == 5 ? true : null;
    }

    public void isBookingAvailable(@NonNull BookingAvailabilityListener bookingAvailabilityListener) {
        IM.onBg().execute(new NextbikeApiRepository$$Lambda$8(this, bookingAvailabilityListener));
    }

    final /* synthetic */ void lambda$isBookingAvailable$50$NextbikeApiRepository(@NonNull BookingAvailabilityListener bookingAvailabilityListener) {
        User user = UserManager.getUser();
        if (user == null) {
            IM.onUi().execute(new NextbikeApiRepository$$Lambda$11(bookingAvailabilityListener));
            return;
        }
        int maxBikesRentedSimultaneously = Tariffs.Tariff_PAY_AS_YOU_GO.getMaxBikesRentedSimultaneously();
        ResponseCurrentRentalsAndBookings currentRentalsWithBatteryAndBookingsSync = getCurrentRentalsWithBatteryAndBookingsSync(user.getLoginkey());
        ResponseActiveTariff currentTariffSync = new TariffRepository().getCurrentTariffSync();
        if (currentRentalsWithBatteryAndBookingsSync.success && currentTariffSync != null) {
            if (currentTariffSync.success) {
                int size = currentRentalsWithBatteryAndBookingsSync.bookings.size() + currentRentalsWithBatteryAndBookingsSync.rentals.size();
                if (currentTariffSync.tariffExtraInfo != null) {
                    maxBikesRentedSimultaneously = currentTariffSync.tariffExtraInfo.getMaxBikesRentedSimultaneously();
                }
                if (size >= maxBikesRentedSimultaneously) {
                    IM.onUi().execute(new NextbikeApiRepository$$Lambda$13(bookingAvailabilityListener));
                } else {
                    IM.onUi().execute(new NextbikeApiRepository$$Lambda$14(bookingAvailabilityListener));
                }
                return;
            }
        }
        IM.onUi().execute(new NextbikeApiRepository$$Lambda$12(bookingAvailabilityListener));
    }

    public void getStationExtraMapData(Place place, ResponseListener<MapStationData> responseListener) {
        IM.onBg().execute(new NextbikeApiRepository$$Lambda$9(place, responseListener));
    }

    static final /* synthetic */ void lambda$getStationExtraMapData$52$NextbikeApiRepository(Place place, ResponseListener responseListener) {
        List list;
        Throwable e;
        Long l;
        List list2;
        ResponseStationDetails responseStationDetails;
        Location lastKnownLocation = LocationTool.get().getLastKnownLocation();
        StationJson stationJson = null;
        if (lastKnownLocation != null) {
            try {
                Long l2;
                ApiExtended apiExtended = Rest.getApiExtended();
                String string = IM.context().getString(C0434R.string.google_diractions_api_key);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(place.getPosition().latitude);
                stringBuilder.append(",");
                stringBuilder.append(place.getPosition().longitude);
                String stringBuilder2 = stringBuilder.toString();
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(lastKnownLocation.getLatitude());
                stringBuilder3.append(",");
                stringBuilder3.append(lastKnownLocation.getLongitude());
                ResponseGoogleRoute responseGoogleRoute = (ResponseGoogleRoute) apiExtended.getRoute(string, stringBuilder2, stringBuilder3.toString(), Mode.WALK).execute().body();
                if (responseGoogleRoute == null || responseGoogleRoute.routes == null || responseGoogleRoute.routes.isEmpty()) {
                    l2 = null;
                    list = l2;
                } else {
                    GoogleRoute googleRoute = (GoogleRoute) responseGoogleRoute.routes.get(0);
                    list = PolyUtil.decode(googleRoute.overviewPolyline.encodedPolyline);
                    try {
                        l2 = Long.valueOf(DirectionsRepository.getGoogleRouteDurationSeconds(googleRoute));
                    } catch (Exception e2) {
                        e = e2;
                        Log.ex(TAG, e);
                        l = null;
                        list2 = list;
                        responseStationDetails = (ResponseStationDetails) Rest.getApiNextbikeJson().getStationDetails(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), place.getUid()).execute().body();
                        if (responseStationDetails != null) {
                            stationJson = responseStationDetails.getPlace();
                        }
                        IM.onUi().execute(new NextbikeApiRepository$$Lambda$10(responseListener, place, stationJson, list2, l));
                    }
                }
                l = l2;
            } catch (Exception e3) {
                e = e3;
                list = null;
                Log.ex(TAG, e);
                l = null;
                list2 = list;
                responseStationDetails = (ResponseStationDetails) Rest.getApiNextbikeJson().getStationDetails(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), place.getUid()).execute().body();
                if (responseStationDetails != null) {
                    stationJson = responseStationDetails.getPlace();
                }
                IM.onUi().execute(new NextbikeApiRepository$$Lambda$10(responseListener, place, stationJson, list2, l));
            }
            list2 = list;
        } else {
            list2 = null;
            l = list2;
        }
        try {
            responseStationDetails = (ResponseStationDetails) Rest.getApiNextbikeJson().getStationDetails(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), place.getUid()).execute().body();
            if (responseStationDetails != null) {
                stationJson = responseStationDetails.getPlace();
            }
        } catch (Throwable e4) {
            Log.ex(TAG, e4);
        }
        IM.onUi().execute(new NextbikeApiRepository$$Lambda$10(responseListener, place, stationJson, list2, l));
    }

    public void getStationDetails(int i, final ResponseListener<StationJson> responseListener) {
        Rest.getApiNextbikeJson().getStationDetails(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), i).enqueue(new Callback<ResponseStationDetails>() {
            public void onResponse(Call<ResponseStationDetails> call, Response<ResponseStationDetails> response) {
                if (response.body() == null || ((ResponseStationDetails) response.body()).getPlace() == null) {
                    responseListener.onResponse(null, null, null);
                } else {
                    responseListener.onResponse(((ResponseStationDetails) response.body()).getPlace(), true, null);
                }
            }

            public void onFailure(Call<ResponseStationDetails> call, Throwable th) {
                responseListener.onResponse(null, false, null);
            }
        });
    }

    public void purchaseVoucher(String str, final SimpleResponseListener simpleResponseListener) {
        Rest.getApiNextbike().purchaseVoucher(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), str).enqueue(new Callback<ResponsePurchaseVoucher>() {
            public void onResponse(Call<ResponsePurchaseVoucher> call, Response<ResponsePurchaseVoucher> response) {
                ResponsePurchaseVoucher responsePurchaseVoucher = (ResponsePurchaseVoucher) response.body();
                if (response.isSuccessful() == null || responsePurchaseVoucher == null || responsePurchaseVoucher.voucher == null) {
                    simpleResponseListener.onResponse(null, null);
                } else {
                    simpleResponseListener.onResponse(true, null);
                }
            }

            public void onFailure(Call<ResponsePurchaseVoucher> call, Throwable th) {
                simpleResponseListener.onResponse(null, null);
            }
        });
    }
}
