package com.mevo.app.presentation.main.ongoing_rent_details;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.annimon.stream.Stream;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.inverce.mod.core.IM;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.constants.BikeTypes;
import com.mevo.app.constants.Tariffs;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.model.PurchasedTariff;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.RentalRoute;
import com.mevo.app.data.model.TariffExtraInfo;
import com.mevo.app.data.model.event.EventDataBikeReturn;
import com.mevo.app.data.model.repository.ResponseActiveTariff;
import com.mevo.app.data.model.response.ResponseHistory;
import com.mevo.app.data.model.response.ResponseUserDetails;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.repository.DirectionsRepository;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.tools.CustomTypefaceSpan;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.RepeatTask;
import com.mevo.app.tools.Screen;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.Utils.SpanUtil;
import com.mevo.app.tools.formatters.BikeDataFormatter;
import com.mevo.app.tools.formatters.CurrencyFormatter;
import com.mevo.app.tools.formatters.Formatter;
import com.mevo.app.tools.map.MapUtils;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OngoingRentDetailsFragment extends MainFragment implements OnMapReadyCallback, OnCameraIdleListener {
    private static final String ARG_RENTAL_ITEM_JSON = "ARG_RENTAL_ITEM_JSON";
    private static final long ROUTE_DATA_UPDATE_INTERVAL = 5000;
    private static final long SCREEN_DATA_UPDATE_INTERVAL = 1000;
    private static final String TAG = "OngoingRentDetailsFragment";
    private TextView bikeNumber;
    private boolean centerOnNextIdle;
    @NonNull
    private String currency = "";
    private boolean firstCameraUpdateDone = false;
    private GoogleMap googleMap;
    private MapView mapView;
    private OngoingRentCalculator ongoingRentCalculator;
    private TextView priceText;
    private TextView rentDurationText;
    private RentalRoute rentalRoute;
    private Polyline routePolyline;
    @Nullable
    private RepeatTask routeTimerTask;
    @Nullable
    private RepeatTask screenDataTask;
    private Marker startMarker = null;
    private boolean startMarkerAdded = false;
    private TextView subscriptionMinutesUsedText;
    private RentalItem trackedRental;

    /* renamed from: com.mevo.app.presentation.main.ongoing_rent_details.OngoingRentDetailsFragment$1 */
    class C08251 implements Callback<ResponseHistory> {
        public void onFailure(Call<ResponseHistory> call, Throwable th) {
        }

        C08251() {
        }

        public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
            if (response != null && response.body() != null && ((ResponseHistory) response.body()).accountHistory != null) {
                RentalItem rentalItem = (RentalItem) Stream.of(((ResponseHistory) response.body()).accountHistory.getRentalItems()).filter(new OngoingRentDetailsFragment$1$$Lambda$0(this)).findFirst().orElse(null);
                if ((rentalItem != null && rentalItem.isReturned()) || (rentalItem == null && ((ResponseHistory) response.body()).accountHistory.getRentalItems().size() > 5)) {
                    OngoingRentDetailsFragment.this.onRentFinished();
                }
            }
        }

        final /* synthetic */ boolean lambda$onResponse$217$OngoingRentDetailsFragment$1(RentalItem rentalItem) {
            return rentalItem.id == OngoingRentDetailsFragment.this.trackedRental.id ? true : null;
        }
    }

    final /* bridge */ /* synthetic */ void bridge$lambda$0$OngoingRentDetailsFragment() {
        calculateAndUpdateScreenData();
    }

    public static OngoingRentDetailsFragment newInstance(RentalItem rentalItem) {
        OngoingRentDetailsFragment ongoingRentDetailsFragment = new OngoingRentDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_RENTAL_ITEM_JSON, Rest.getJsonSerializer().toJson((Object) rentalItem));
        ongoingRentDetailsFragment.setArguments(bundle);
        return ongoingRentDetailsFragment;
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_rent_details, viewGroup, false);
        getArgsData();
        findViews(layoutInflater);
        this.centerOnNextIdle = true;
        this.mapView.onCreate(bundle);
        this.mapView.getMapAsync(this);
        setBaseData();
        fetchRequiredApiData();
        return layoutInflater;
    }

    public void onStart() {
        super.onStart();
        this.mapView.onStart();
        checkIfRentFinished();
        createRouteTimerTask();
        createScreenDataTask();
        this.screenDataTask.start();
        this.routeTimerTask.start();
    }

    public void onResume() {
        super.onResume();
        this.mapView.onResume();
    }

    public void onPause() {
        super.onPause();
        this.mapView.onPause();
    }

    public void onStop() {
        super.onStop();
        this.mapView.onStop();
        if (this.screenDataTask != null) {
            this.screenDataTask.stop();
            this.screenDataTask.destroy();
        }
        if (this.routeTimerTask != null) {
            this.routeTimerTask.stop();
            this.routeTimerTask.destroy();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.googleMap != null) {
            this.googleMap.setOnCameraIdleListener(null);
        }
        this.googleMap = null;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mapView != null) {
            this.mapView.onDestroy();
        }
        this.mapView = null;
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.mapView != null) {
            this.mapView.onLowMemory();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mapView != null) {
            try {
                this.mapView.onSaveInstanceState(bundle);
            } catch (Bundle bundle2) {
                Log.ex(TAG, bundle2);
                if (Cfg.get().fabricEnabled()) {
                    Crashlytics.logException(bundle2);
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBikeReturnEvent(EventDataBikeReturn eventDataBikeReturn) {
        if (!TextUtils.isEmpty(eventDataBikeReturn.getBikeNumer()) && eventDataBikeReturn.getBikeNumer().equals(this.trackedRental.bikeNumber) != null) {
            onRentFinished();
        }
    }

    private void onRentFinished() {
        if (getActivityInterface() != null) {
            getActivityInterface().popFragment();
            getActivityInterface().goToHome();
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setOnCameraIdleListener(this);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        updateMapData();
    }

    private void getArgsData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.trackedRental = (RentalItem) Rest.getJsonSerializer().fromJson(arguments.getString(ARG_RENTAL_ITEM_JSON), RentalItem.class);
        }
    }

    private void findViews(View view) {
        this.mapView = (MapView) view.findViewById(C0434R.id.fragment_rent_details_map);
        this.rentDurationText = (TextView) view.findViewById(C0434R.id.fragment_rent_details_rent_duration);
        this.priceText = (TextView) view.findViewById(C0434R.id.fragment_rent_details_price);
        this.subscriptionMinutesUsedText = (TextView) view.findViewById(C0434R.id.fragment_rent_details_subscription_minutes_used);
        this.bikeNumber = (TextView) view.findViewById(C0434R.id.fragment_rent_details_bike_number);
    }

    private void setBaseData() {
        String bikeNameForBikeType = new BikeDataFormatter(getContext()).getBikeNameForBikeType(BikeTypes.getBikeGroupByBikeType(this.trackedRental.bikeType));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(bikeNameForBikeType);
        stringBuilder.append(" ");
        stringBuilder.append(this.trackedRental.bikeNumber);
        this.bikeNumber.setText(SpanUtil.setSpan(stringBuilder.toString(), this.trackedRental.bikeNumber, new CustomTypefaceSpan("roboto_medium.ttf", Typeface.create(ResourcesCompat.getFont(getContext(), C0434R.font.roboto_medium), 0))));
        this.rentDurationText.setText(getString(C0434R.string.fragment_rent_details_rent_duration, Operation.MINUS));
        this.priceText.setText(getString(C0434R.string.fragment_rent_details_price, Operation.MINUS));
        this.subscriptionMinutesUsedText.setText(getString(C0434R.string.fragment_rent_details_subscription_minutes_used, Operation.MINUS));
    }

    private void calculateAndUpdateScreenData() {
        if (this.ongoingRentCalculator != null) {
            RentUsedFreeTimeAndCost calculateUsedFreeSecondsAndCost = this.ongoingRentCalculator.calculateUsedFreeSecondsAndCost();
            fillData(this.ongoingRentCalculator.getTariffExtraInfoOnRentStart() != Tariffs.Tariff_PAY_AS_YOU_GO, calculateUsedFreeSecondsAndCost.usedTariffSeconds, calculateUsedFreeSecondsAndCost.costCents, this.trackedRental.getDurationSeconds());
        }
    }

    private void fillData(boolean z, long j, double d, long j2) {
        Context context = getContext();
        if (context != null) {
            if (z) {
                this.subscriptionMinutesUsedText.setVisibility(0);
            } else {
                this.subscriptionMinutesUsedText.setVisibility(8);
            }
            z = Formatter.formatDurationRent(j2, context);
            j2 = new StringBuilder();
            j2.append(Formatter.formatMoneyFromCents(d));
            j2.append(" ");
            j2.append(this.currency);
            d = j2.toString();
            j = Long.toString(j / 60);
            this.rentDurationText.setText(context.getString(C0434R.string.fragment_rent_details_rent_duration, new Object[]{z}));
            this.priceText.setText(context.getString(C0434R.string.fragment_rent_details_price, new Object[]{d}));
            this.subscriptionMinutesUsedText.setText(context.getString(C0434R.string.fragment_rent_details_subscription_minutes_used, new Object[]{j}));
        }
    }

    private void createRouteTimerTask() {
        this.routeTimerTask = new RepeatTask(5000, true, new OngoingRentDetailsFragment$$Lambda$0(this));
    }

    final /* synthetic */ void lambda$createRouteTimerTask$211$OngoingRentDetailsFragment() {
        new DirectionsRepository().getRouteForRentalItem(this.trackedRental, false, new OngoingRentDetailsFragment$$Lambda$7(this));
    }

    final /* synthetic */ void lambda$null$210$OngoingRentDetailsFragment(RentalRoute rentalRoute, boolean z, Exception exception) {
        IM.onUi().execute(new OngoingRentDetailsFragment$$Lambda$8(this, rentalRoute));
    }

    final /* synthetic */ void lambda$null$209$OngoingRentDetailsFragment(RentalRoute rentalRoute) {
        if (isAdded() && rentalRoute != null && rentalRoute.getRentalRoute() != null && !rentalRoute.getRentalRoute().isEmpty()) {
            this.rentalRoute = rentalRoute;
            updateMapData();
        }
    }

    private void createScreenDataTask() {
        this.screenDataTask = new RepeatTask(1000, true, new OngoingRentDetailsFragment$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$createScreenDataTask$212$OngoingRentDetailsFragment() {
        IM.onUi().execute(new OngoingRentDetailsFragment$$Lambda$6(this));
    }

    private void fetchRequiredApiData() {
        if (getActivityInterface() != null) {
            getActivityInterface().setProgressViewVisibility(true);
            IM.onBg().execute(new OngoingRentDetailsFragment$$Lambda$2(this));
        }
    }

    final /* synthetic */ void lambda$fetchRequiredApiData$215$OngoingRentDetailsFragment() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r9 = this;
        r0 = new com.mevo.app.data.repository.TariffRepository;
        r0.<init>();
        r1 = r9.trackedRental;
        r1 = r1.startTimestampSeconds;
        r7 = r0.getTariffActiveOnTsSync(r1);
        r0 = new com.mevo.app.data.repository.DirectionsRepository;
        r0.<init>();
        r1 = r9.trackedRental;
        r2 = 0;
        r5 = r0.getRouteForRentalItemSync(r1, r2);
        r0 = 0;
        r1 = com.mevo.app.data.network.Rest.getApiNextbike();	 Catch:{ IOException -> 0x0063 }
        r3 = com.mevo.app.Cfg.get();	 Catch:{ IOException -> 0x0063 }
        r3 = r3.apikeyNextbike();	 Catch:{ IOException -> 0x0063 }
        r4 = com.mevo.app.tools.UserManager.getLoginKey();	 Catch:{ IOException -> 0x0063 }
        r1 = r1.getUserDetails(r3, r4);	 Catch:{ IOException -> 0x0063 }
        r1 = r1.execute();	 Catch:{ IOException -> 0x0063 }
        r1 = r1.body();	 Catch:{ IOException -> 0x0063 }
        r1 = (com.mevo.app.data.model.response.ResponseUserDetails) r1;	 Catch:{ IOException -> 0x0063 }
        r3 = com.mevo.app.data.network.Rest.getApiNextbike();	 Catch:{ IOException -> 0x0064 }
        r4 = com.mevo.app.Cfg.get();	 Catch:{ IOException -> 0x0064 }
        r4 = r4.apikeyNextbike();	 Catch:{ IOException -> 0x0064 }
        r6 = com.mevo.app.tools.UserManager.getLoginKey();	 Catch:{ IOException -> 0x0064 }
        r8 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;	 Catch:{ IOException -> 0x0064 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ IOException -> 0x0064 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ IOException -> 0x0064 }
        r2 = r3.fetchHistoryNoCache(r4, r6, r8, r2);	 Catch:{ IOException -> 0x0064 }
        r2 = r2.execute();	 Catch:{ IOException -> 0x0064 }
        r2 = r2.body();	 Catch:{ IOException -> 0x0064 }
        r2 = (com.mevo.app.data.model.response.ResponseHistory) r2;	 Catch:{ IOException -> 0x0064 }
        r6 = r1;
        r8 = r2;
        goto L_0x0066;
    L_0x0063:
        r1 = r0;
    L_0x0064:
        r8 = r0;
        r6 = r1;
    L_0x0066:
        r0 = r7.success;
        if (r0 == 0) goto L_0x0086;
    L_0x006a:
        if (r6 == 0) goto L_0x0086;
    L_0x006c:
        r0 = r6.userDetails;
        if (r0 == 0) goto L_0x0086;
    L_0x0070:
        if (r8 == 0) goto L_0x0086;
    L_0x0072:
        r0 = r8.accountHistory;
        if (r0 != 0) goto L_0x0077;
    L_0x0076:
        goto L_0x0086;
    L_0x0077:
        r0 = com.inverce.mod.core.IM.onUi();
        r1 = new com.mevo.app.presentation.main.ongoing_rent_details.OngoingRentDetailsFragment$$Lambda$5;
        r3 = r1;
        r4 = r9;
        r3.<init>(r4, r5, r6, r7, r8);
        r0.execute(r1);
        return;
    L_0x0086:
        r0 = com.inverce.mod.core.IM.onUi();
        r1 = new com.mevo.app.presentation.main.ongoing_rent_details.OngoingRentDetailsFragment$$Lambda$4;
        r1.<init>(r9);
        r0.execute(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.presentation.main.ongoing_rent_details.OngoingRentDetailsFragment.lambda$fetchRequiredApiData$215$OngoingRentDetailsFragment():void");
    }

    final /* synthetic */ void lambda$null$213$OngoingRentDetailsFragment() {
        getActivityInterface().setProgressViewVisibility(false);
        Toast.makeText(getContext(), C0434R.string.error_ocurred, 0).show();
    }

    final /* synthetic */ void lambda$null$214$OngoingRentDetailsFragment(RentalRoute rentalRoute, ResponseUserDetails responseUserDetails, ResponseActiveTariff responseActiveTariff, ResponseHistory responseHistory) {
        this.rentalRoute = rentalRoute;
        this.currency = CurrencyFormatter.formatCurrencyAbbreviation(responseUserDetails.userDetails.getCurrency());
        if (getActivityInterface() != null) {
            getActivityInterface().setProgressViewVisibility(null);
            onRequiredApiDataFetched(responseActiveTariff.currentActiveTariff, responseActiveTariff.tariffExtraInfo, getBookingForTrackedRental(responseHistory.accountHistory.getBookingItems()), responseHistory.accountHistory.getRentalItems(), responseHistory.accountHistory.getBookingItems());
        }
    }

    @Nullable
    private BookingItem getBookingForTrackedRental(List<BookingItem> list) {
        return (BookingItem) Stream.of((Iterable) list).filter(new OngoingRentDetailsFragment$$Lambda$3(this)).findFirst().orElse(null);
    }

    final /* synthetic */ boolean lambda$getBookingForTrackedRental$216$OngoingRentDetailsFragment(BookingItem bookingItem) {
        return (bookingItem.getStartTimestampSeconds() >= this.trackedRental.startTimestampSeconds || bookingItem.getPlaceId() == null || !bookingItem.getPlaceId().equals(this.trackedRental.startPlaceId) || bookingItem.getStartTimestampSeconds() + 900 <= this.trackedRental.startTimestampSeconds) ? null : true;
    }

    private void onRequiredApiDataFetched(PurchasedTariff purchasedTariff, TariffExtraInfo tariffExtraInfo, BookingItem bookingItem, List<RentalItem> list, List<BookingItem> list2) {
        if (list2 == null) {
            list2 = new ArrayList();
        }
        List<BookingItem> list3 = list2;
        if (list == null) {
            list = new ArrayList();
        }
        this.ongoingRentCalculator = new OngoingRentCalculator(this.trackedRental, purchasedTariff, tariffExtraInfo, bookingItem, list, list3);
        calculateAndUpdateScreenData();
        updateMapData();
    }

    private void updateMapData() {
        addStartMarkerToMapIfShould();
        updateCameraToRoute(this.trackedRental, this.rentalRoute);
        updateRoute();
    }

    private void updateRoute() {
        if (this.googleMap != null) {
            if (this.rentalRoute != null) {
                if (this.routePolyline == null) {
                    PolylineOptions polylineOptions = new PolylineOptions();
                    polylineOptions.color(ResourcesCompat.getColor(getResources(), C0434R.color.colorAccent, null));
                    polylineOptions.width(7.0f);
                    polylineOptions.addAll(this.rentalRoute.getRentalRoute());
                    this.routePolyline = this.googleMap.addPolyline(polylineOptions);
                } else {
                    this.routePolyline.setPoints(this.rentalRoute.getRentalRoute());
                }
                if (this.rentalRoute.getRentalRoute().size() > 1) {
                    if (this.startMarker != null) {
                        this.startMarker.remove();
                    }
                    this.startMarker = this.googleMap.addMarker(new MarkerOptions().position((LatLng) this.rentalRoute.getRentalRoute().get(this.rentalRoute.getRentalRoute().size() - 1)).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(C0434R.drawable.marker_location_blue)));
                }
            }
        }
    }

    private void addStartMarkerToMapIfShould() {
        if (this.googleMap != null) {
            if (!this.startMarkerAdded) {
                if (this.trackedRental.getStartLatLng() != null) {
                    this.startMarkerAdded = true;
                    this.googleMap.addMarker(new MarkerOptions().position(this.trackedRental.getStartLatLng()).icon(BitmapDescriptorFactory.fromResource(C0434R.drawable.pin_ongoing_route_start)));
                }
            }
        }
    }

    public void onCameraIdle() {
        if (this.centerOnNextIdle) {
            updateCameraToRoute(this.trackedRental, this.rentalRoute);
            this.centerOnNextIdle = false;
        }
    }

    private void updateCameraToRoute(RentalItem rentalItem, RentalRoute rentalRoute) {
        if (this.googleMap != null && !this.firstCameraUpdateDone) {
            if (rentalRoute != null && rentalRoute.getRentalRoute().size() > 1) {
                this.firstCameraUpdateDone = true;
                rentalItem = new Builder();
                for (LatLng include : rentalRoute.getRentalRoute()) {
                    rentalItem.include(include);
                }
                this.googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(rentalItem.build(), Screen.dpToPx(App.getAppContext().getResources().getDimension(C0434R.dimen.space_standard))));
            } else if (rentalItem.getStartLatLng() != null) {
                this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rentalItem.getStartLatLng(), 15.0f));
            } else {
                MapUtils.setMapPosToMevo(this.googleMap);
            }
        }
    }

    private void checkIfRentFinished() {
        Rest.getApiNextbike().fetchHistory(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), Integer.valueOf(20), Integer.valueOf(0)).enqueue(new C08251());
    }
}
