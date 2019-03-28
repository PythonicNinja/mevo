package com.mevo.app.tools.bike_tools;

import android.app.Activity;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.iid.FirebaseInstanceId;
import com.inverce.mod.core.IM;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.constants.Servers;
import com.mevo.app.constants.Tariffs;
import com.mevo.app.data.model.Bike$$CC;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.model.City;
import com.mevo.app.data.model.Country;
import com.mevo.app.data.model.Domain;
import com.mevo.app.data.model.Place;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.User;
import com.mevo.app.data.model.UserDetails;
import com.mevo.app.data.model.repository.ResponseActiveTariff;
import com.mevo.app.data.model.repository.ResponseCurrentRentalsAndBookings;
import com.mevo.app.data.model.response.ResponseBikeState;
import com.mevo.app.data.model.response.ResponseOpenRentals;
import com.mevo.app.data.model.response.ResponseRentBike;
import com.mevo.app.data.model.response.ResponseShouldRegisterForPushMessages;
import com.mevo.app.data.model.response.ResponseStationsData;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.repository.NextbikeApiRepository;
import com.mevo.app.data.repository.TariffRepository;
import com.mevo.app.data.shared_prefs.SharedPrefs;
import com.mevo.app.modules.firebase_cloud_messaging.MyFirebaseMessagingService.PushData;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.dialogs.InfoDialog.Builder;
import com.mevo.app.presentation.dialogs.RentBikeDialog;
import com.mevo.app.tools.Lifecycle;
import com.mevo.app.tools.LocaleHelper;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.NetTools;
import com.mevo.app.tools.NetTools.CallsToken;
import com.mevo.app.tools.Permissions;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.Utils;
import com.mevo.app.tools.Validator;
import com.mevo.app.tools.location.LocationCallback;
import com.mevo.app.tools.location.LocationTool;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentBikeRepository {
    private static final long MIN_RENTAL_INTERVAL_QR_SCANNER = 5000;
    private static final double MIN_STATIONS_FETCH_DISTANCE_METERS = 1000.0d;
    private static final String TAG = "RentBikeRepository";
    private static long previousRentTimestamp;
    private String bikeDomain;
    private String bikeNumber;
    private CallsToken callsToken;
    private boolean isRentingFromQrScanner;
    private RentCallback rentCallback;
    private boolean serverSettingShouldRegisterForPushMessages = null;

    public interface RentCallback {
        void onRent(boolean z);
    }

    /* renamed from: com.mevo.app.tools.bike_tools.RentBikeRepository$1 */
    class C08421 implements Callback<ResponseBikeState> {
        C08421() {
        }

        public void onResponse(Call<ResponseBikeState> call, Response<ResponseBikeState> response) {
            if (response.body() == null || ((ResponseBikeState) response.body()).bike == null) {
                Log.m90e(RentBikeRepository.TAG, "Couldn't get bike state");
                RentBikeRepository.this.onRentFail(C0434R.string.rent_bike_error);
            } else if (Bike$$CC.isBikeAvailable$$STATIC$$(((ResponseBikeState) response.body()).bike) == null || TextUtils.isEmpty(((ResponseBikeState) response.body()).bike.getDomain()) != null) {
                Log.m90e(RentBikeRepository.TAG, "Bike is unavailable");
                TopToast.show(C0434R.string.bike_not_availaible_for_rent, 0, 5000);
                RentBikeRepository.this.notifyListenerAndClearData(false);
            } else {
                Log.m94i(RentBikeRepository.TAG, "Bike is available");
                RentBikeRepository.this.bikeDomain = ((ResponseBikeState) response.body()).bike.getDomain();
                RentBikeRepository.this.checkCanRentFromCurrentLocation(((ResponseBikeState) response.body()).bike.getPlaceId());
            }
        }

        public void onFailure(Call<ResponseBikeState> call, Throwable th) {
            if (call.isCanceled() == null) {
                Log.ex(RentBikeRepository.TAG, "Couldn't get bike state", th);
                RentBikeRepository.this.onRentFail(C0434R.string.rent_bike_error);
            }
        }
    }

    /* renamed from: com.mevo.app.tools.bike_tools.RentBikeRepository$4 */
    class C08454 implements Callback<ResponseShouldRegisterForPushMessages> {
        C08454() {
        }

        public void onResponse(Call<ResponseShouldRegisterForPushMessages> call, Response<ResponseShouldRegisterForPushMessages> response) {
            if (response.body() != null) {
                RentBikeRepository.this.serverSettingShouldRegisterForPushMessages = ((ResponseShouldRegisterForPushMessages) response.body()).getShouldRegisterForPushMessages();
            }
            RentBikeRepository.this.rentBike();
        }

        public void onFailure(Call<ResponseShouldRegisterForPushMessages> call, Throwable th) {
            RentBikeRepository.this.rentBike();
        }
    }

    /* renamed from: com.mevo.app.tools.bike_tools.RentBikeRepository$5 */
    class C08465 implements Callback<ResponseRentBike> {
        static final /* synthetic */ void lambda$onResponse$229$RentBikeRepository$5(boolean z, Exception exception) {
        }

        C08465() {
        }

        public void onResponse(Call<ResponseRentBike> call, Response<ResponseRentBike> response) {
            if (response.body() == null || ((ResponseRentBike) response.body()).rental == null) {
                Log.m90e(RentBikeRepository.TAG, "Renting bike failed, checking to make sure if really not rented...");
                RentBikeRepository.this.checkIfActuallyRentedOnRentFail();
                return;
            }
            Log.m94i(RentBikeRepository.TAG, "Renting bike success");
            new NextbikeApiRepository().updateBookingHistoryIfRentalWasBooked(((ResponseRentBike) response.body()).rental, RentBikeRepository$5$$Lambda$0.$instance);
            RentBikeRepository.this.onRentSuccess();
        }

        public void onFailure(Call<ResponseRentBike> call, Throwable th) {
            if (call.isCanceled() == null) {
                Log.m90e(RentBikeRepository.TAG, "Renting bike failed, checking to make sure if really not rented...");
                RentBikeRepository.this.checkIfActuallyRentedOnRentFail();
            }
        }
    }

    /* renamed from: com.mevo.app.tools.bike_tools.RentBikeRepository$6 */
    class C08476 implements Callback<ResponseOpenRentals> {
        static final /* synthetic */ void lambda$onResponse$230$RentBikeRepository$6(boolean z, Exception exception) {
        }

        C08476() {
        }

        public void onResponse(Call<ResponseOpenRentals> call, Response<ResponseOpenRentals> response) {
            if (response.body() == null || ((ResponseOpenRentals) response.body()).openRentals == null || ((ResponseOpenRentals) response.body()).openRentals.isEmpty() != null) {
                Log.m90e(RentBikeRepository.TAG, "Renting bike failed");
                RentBikeRepository.this.onRentFail(C0434R.string.rent_bike_error);
                return;
            }
            call = null;
            response = ((ResponseOpenRentals) response.body()).openRentals.iterator();
            while (response.hasNext()) {
                RentalItem rentalItem = (RentalItem) response.next();
                if (rentalItem.bikeNumber != null && rentalItem.bikeNumber.equals(RentBikeRepository.this.bikeNumber)) {
                    call = rentalItem;
                    break;
                }
            }
            if (call != null) {
                Log.m90e(RentBikeRepository.TAG, "Renting bike actually succeeded");
                new NextbikeApiRepository().updateBookingHistoryIfRentalWasBooked(call, RentBikeRepository$6$$Lambda$0.$instance);
                RentBikeRepository.this.onRentSuccess();
                return;
            }
            Log.m90e(RentBikeRepository.TAG, "Renting bike failed");
            RentBikeRepository.this.onRentFail(C0434R.string.rent_bike_error);
        }

        public void onFailure(Call<ResponseOpenRentals> call, Throwable th) {
            if (call.isCanceled() == null) {
                Log.m90e(RentBikeRepository.TAG, "Renting bike failed");
                RentBikeRepository.this.onRentFail(C0434R.string.rent_bike_error);
            }
        }
    }

    final /* bridge */ /* synthetic */ void bridge$lambda$0$RentBikeRepository() {
        checkBikeState();
    }

    public RentBikeRepository(@NonNull CallsToken callsToken, boolean z) {
        this.callsToken = callsToken;
        this.isRentingFromQrScanner = z;
    }

    public void tryRentBike(String str, RentCallback rentCallback) {
        if (this.rentCallback != null) {
            Log.m102w(TAG, "Process is already going");
            return;
        }
        this.rentCallback = rentCallback;
        if (Validator.isBikeNumberValid(str) != null) {
            this.bikeNumber = str;
            if (previousRentTimestamp + 5000 <= System.currentTimeMillis() || this.isRentingFromQrScanner == null) {
                previousRentTimestamp = System.currentTimeMillis();
                getUserDetails();
                return;
            }
            onRentFail(C0434R.string.error_too_frequent_qr_renting);
            return;
        }
        onRentFail(C0434R.string.error_wrong_bike_number);
    }

    private void getUserDetails() {
        Log.m94i(TAG, "Fetching user details");
        UserManager.fetchUserDetailsFromServer(this.callsToken, new RentBikeRepository$$Lambda$0(this));
    }

    final /* synthetic */ void lambda$getUserDetails$223$RentBikeRepository(boolean z, Call call, UserDetails userDetails) {
        if (z) {
            if (Utils.intToBool(userDetails.getIsActive())) {
                Log.m94i(TAG, "Fetching user details success, user is active");
                checkIfDidntExceedLimit();
                return;
            }
            Log.m90e(TAG, "User is inactive");
            TopToast.show(true, 0, 5000);
            notifyListenerAndClearData(false);
        } else if (!call.isCanceled()) {
            Log.m90e(TAG, "Fetching user details failed");
            onRentFail(true);
        }
    }

    private void checkIfDidntExceedLimit() {
        IM.onBg().execute(new RentBikeRepository$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$checkIfDidntExceedLimit$226$RentBikeRepository() {
        try {
            User user = UserManager.getUser();
            int maxBikesRentedSimultaneously = Tariffs.Tariff_PAY_AS_YOU_GO.getMaxBikesRentedSimultaneously();
            Object obj = null;
            ResponseCurrentRentalsAndBookings currentRentalsWithBatteryAndBookingsSync = new NextbikeApiRepository().getCurrentRentalsWithBatteryAndBookingsSync(user.getLoginkey());
            ResponseActiveTariff currentTariffSync = new TariffRepository().getCurrentTariffSync();
            ResponseBikeState responseBikeState = (ResponseBikeState) Rest.getApiNextbike().getBikeState(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), this.bikeNumber).execute().body();
            if (currentRentalsWithBatteryAndBookingsSync.success && currentTariffSync.success && responseBikeState != null) {
                if (responseBikeState.bike != null) {
                    int size = currentRentalsWithBatteryAndBookingsSync.bookings.size() + currentRentalsWithBatteryAndBookingsSync.rentals.size();
                    if (currentTariffSync.tariffExtraInfo != null) {
                        maxBikesRentedSimultaneously = currentTariffSync.tariffExtraInfo.getMaxBikesRentedSimultaneously();
                    }
                    for (BookingItem placeId : currentRentalsWithBatteryAndBookingsSync.bookings) {
                        if (placeId.getPlaceId().equals(Integer.toString(responseBikeState.bike.getPlaceId()))) {
                            obj = 1;
                            break;
                        }
                    }
                    if (size >= maxBikesRentedSimultaneously) {
                        if (size != maxBikesRentedSimultaneously || r2 == null) {
                            IM.onUi().execute(new RentBikeRepository$$Lambda$5(this));
                            return;
                        }
                    }
                    IM.onUi().execute(new RentBikeRepository$$Lambda$4(this));
                    return;
                }
            }
            onRentFail(C0434R.string.rent_bike_error);
        } catch (Throwable e) {
            Log.ex(e);
            IM.onUi().execute(new RentBikeRepository$$Lambda$6(this));
        }
    }

    final /* synthetic */ void lambda$null$224$RentBikeRepository() {
        onRentFail(C0434R.string.rent_bike_error_subscription_limit, true);
    }

    final /* synthetic */ void lambda$null$225$RentBikeRepository() {
        onRentFail(C0434R.string.rent_bike_error, true);
    }

    private void checkBikeState() {
        Log.m94i(TAG, "Checking bike state");
        NetTools.saveCall(this.callsToken, Rest.getApiNextbike().getBikeState(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), this.bikeNumber)).enqueue(new C08421());
    }

    private void checkCanRentFromCurrentLocation(final int i) {
        Activity activity = Lifecycle.getActivity();
        if (this.isRentingFromQrScanner) {
            checkBikeDomain(this.bikeDomain);
            Log.m94i(TAG, "User is renting using QR scanner, checking bike domain");
            return;
        }
        if (activity != null) {
            if (Permissions.hasPermissions(Permissions.PERMISSIONS_LOCATION, activity)) {
                LocationTool.get().getCurrentLocationAsync(new LocationCallback() {
                    boolean receivedLocation = null;

                    public void onReceive(@Nullable Location location) {
                        if (this.receivedLocation) {
                            Log.m90e(RentBikeRepository.TAG, "Received location more than once");
                            Crashlytics.logException(new Exception("Received location more than once"));
                            return;
                        }
                        this.receivedLocation = true;
                        if (location != null) {
                            RentBikeRepository.this.checkIfStationInRange(location, i);
                            Log.m94i(RentBikeRepository.TAG, "Got location checking stations.");
                        } else {
                            RentBikeRepository.this.onRentFail(C0434R.string.rent_bike_using_qr_scanner);
                            Log.m90e(RentBikeRepository.TAG, "Can't get location.");
                        }
                    }
                });
                return;
            }
        }
        onRentFail(C0434R.string.rent_bike_using_qr_scanner);
        Log.m90e(TAG, "User didnt grant location permissions or app cant check it.");
    }

    private void checkIfStationInRange(@NonNull final Location location, final int i) {
        Log.m94i(TAG, "Fetching stations in 1000.0m range");
        NetTools.saveCall(this.callsToken, Rest.getApiNextbikeMap().getStationsData(null, Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Integer.valueOf((int) (Cfg.get().rentMaxDistanceMeters() + MIN_STATIONS_FETCH_DISTANCE_METERS)))).enqueue(new Callback<ResponseStationsData>() {
            public void onResponse(Call<ResponseStationsData> call, Response<ResponseStationsData> response) {
                Place place = null;
                if (!(response.body() == null || ((ResponseStationsData) response.body()).countries == null)) {
                    call = ((ResponseStationsData) response.body()).countries.iterator();
                    while (call.hasNext() != null) {
                        Country country = (Country) call.next();
                        if (country.cities != null) {
                            response = country.cities.iterator();
                            while (response.hasNext()) {
                                City city = (City) response.next();
                                if (city.places != null) {
                                    for (Place place2 : city.places) {
                                        if (i == place2.getUid()) {
                                            place = place2;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (place == null) {
                    RentBikeRepository.this.onRentFail(C0434R.string.rent_bike_using_qr_scanner);
                    Log.m90e(RentBikeRepository.TAG, "Didn't receive bike station within 1000.0m radius from api.");
                } else if (LocationTool.calculateDistanceMeters(new LatLng(place.getLat(), place.getLng()), location) <= Cfg.get().rentMaxDistanceMeters()) {
                    RentBikeRepository.this.checkBikeDomain(RentBikeRepository.this.bikeDomain);
                } else {
                    RentBikeRepository.this.onRentFail(C0434R.string.rent_bike_using_qr_scanner);
                    String str = RentBikeRepository.TAG;
                    response = new StringBuilder();
                    response.append("Bike station is not in ");
                    response.append(Cfg.get().rentMaxDistanceMeters());
                    response.append("m range.");
                    Log.m90e(str, response.toString());
                }
            }

            public void onFailure(Call<ResponseStationsData> call, Throwable th) {
                Log.m90e(RentBikeRepository.TAG, "Fetching stations failed");
                RentBikeRepository.this.onRentFail(C0434R.string.rent_bike_error);
            }
        });
    }

    private void checkBikeDomain(String str) {
        for (Domain domain : Cfg.get().bikeDomains()) {
            if (domain.getDomainCode() != null && domain.getDomainCode().equalsIgnoreCase(str)) {
                str = true;
                break;
            }
        }
        str = null;
        if (str != null) {
            checkIfShouldRegisterForPushMessages();
        } else {
            new Builder().setTitle((int) C0434R.string.warning).setText((int) C0434R.string.warning_bike_outside_domain).setNegativeButton((int) C0434R.string.cancel, new RentBikeRepository$$Lambda$2(this)).setPositiveButton((int) C0434R.string.rent_bike, new RentBikeRepository$$Lambda$3(this)).build().show();
        }
    }

    final /* synthetic */ void lambda$checkBikeDomain$227$RentBikeRepository(View view) {
        notifyListenerAndClearData(null);
    }

    final /* synthetic */ void lambda$checkBikeDomain$228$RentBikeRepository(View view) {
        checkIfShouldRegisterForPushMessages();
    }

    private void checkIfShouldRegisterForPushMessages() {
        NetTools.saveCall(this.callsToken, Rest.getApiExtended().shouldRegisterForPushMessages()).enqueue(new C08454());
    }

    private void rentBike() {
        Log.m94i(TAG, "Renting bike");
        String createPushUrl = (this.serverSettingShouldRegisterForPushMessages && SharedPrefs.getReturnBikePushSettings()) ? createPushUrl("return") : null;
        Call saveCall = NetTools.saveCall(this.callsToken, Rest.getApiNextbike().rentBike(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), this.bikeNumber, null, createPushUrl));
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Push return callback url: ");
        stringBuilder.append(createPushUrl);
        Log.m94i(str, stringBuilder.toString());
        saveCall.enqueue(new C08465());
    }

    private void checkIfActuallyRentedOnRentFail() {
        NetTools.saveCall(this.callsToken, Rest.getApiNextbike().getCurrentRentals(Cfg.get().apikeyNextbike(), UserManager.getLoginKey())).enqueue(new C08476());
    }

    private void onRentFail(@StringRes int i) {
        onRentFail(i, false);
    }

    private void onRentFail(@StringRes int i, boolean z) {
        if (z) {
            new Builder().setText(i).setNeutralButton((int) true, null).build().show();
        } else {
            TopToast.show(i, 0, 5000);
        }
        notifyListenerAndClearData(false);
    }

    private void onRentSuccess() {
        RentBikeDialog.newInstance(this.bikeNumber).show();
        notifyListenerAndClearData(true);
    }

    private void notifyListenerAndClearData(boolean z) {
        this.rentCallback.onRent(z);
        this.rentCallback = false;
    }

    @Nullable
    private String createPushUrl(String str) {
        String token = FirebaseInstanceId.getInstance().getToken();
        if (token == null) {
            return null;
        }
        HttpUrl.Builder newBuilder = HttpUrl.parse("http://apiapp.veturilo.net.pl/v1/send-push").newBuilder();
        newBuilder.setQueryParameter(PushData.PARAM_TOKEN, token).setQueryParameter(PushData.PARAM_ACTION, str).setQueryParameter(PushData.PARAM_LANGUAGE, LocaleHelper.getLanguage(App.getAppContext())).setQueryParameter(PushData.PARAM_OS, "android").setQueryParameter(PushData.PARAM_MOBILE_NUMBER, UserManager.getUser().getPhoneNumber()).setQueryParameter(PushData.PARAM_BIKE_NUMBER, this.bikeNumber).setQueryParameter(PushData.PARAM_BIKES_SYSTEM_ID, Servers.EXTENDED_API_SYSTEM_ID);
        return newBuilder.build().toString();
    }
}
