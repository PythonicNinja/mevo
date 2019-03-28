package com.mevo.app.presentation.main.statitons_map;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;
import com.google.maps.android.clustering.Cluster;
import com.inverce.mod.core.IM;
import com.inverce.mod.events.Event.Bus;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.constants.StationTypes;
import com.mevo.app.constants.StationTypes.StationType;
import com.mevo.app.data.model.MapStationData;
import com.mevo.app.data.model.Place;
import com.mevo.app.data.model.response.ResponseFlexzones;
import com.mevo.app.data.repository.FlexzonesRepository;
import com.mevo.app.data.repository.NextbikeApiRepository;
import com.mevo.app.data.repository.StationsDataRepository;
import com.mevo.app.presentation.custom_views.QrView;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.dialogs.BikeReservationDialog;
import com.mevo.app.presentation.dialogs.InfoDialog;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.presentation.main.rent_bike.RentBikeQrFragment;
import com.mevo.app.presentation.main.statitons_map.bottom_sheets.BookingDialogView;
import com.mevo.app.presentation.main.statitons_map.bottom_sheets.CurrentRentalsDialogView;
import com.mevo.app.presentation.main.statitons_map.bottom_sheets.CurrentRentalsDialogView.MapCallback;
import com.mevo.app.presentation.main.statitons_map.bottom_sheets.HomeBottomSheetManager;
import com.mevo.app.presentation.main.statitons_map.utils.StationsMapHelper;
import com.mevo.app.presentation.main.statitons_map.utils.StationsMapHelper.StationsMapListener;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.Permissions;
import com.mevo.app.tools.RepeatTask;
import com.mevo.app.tools.Screen;
import com.mevo.app.tools.bike_tools.BookingRepository;
import com.mevo.app.tools.location.LocationTool;
import com.mevo.app.tools.map.MapUtils;
import java.util.List;
import org.json.JSONObject;

public class StationsMapFragment extends MainFragment implements OnMapReadyCallback, RefreshMapAndSheetListener, StationsMapListener {
    private static final String ARG_CAMERA_POSITION = "ARG_CAMERA_POSITION";
    private static final String ARG_STATION_TO_SHOW_ID = "ARG_STATION_TO_SHOW_ID";
    private static final String ARG_STATION_TO_SHOW_LAT_LNG = "ARG_STATION_TO_SHOW_LAT_LNG";
    private static final long FLEXZONE_REFRESH_INTERVAL_MS = 3600000;
    private static final long REFRESH_INTERVAL_MS = 30000;
    private static final String TAG = "StationsMapFragment";
    private HomeBottomSheetManager bottomSheetManager;
    private CurrentRentalsDialogView currentRentalsDialogView;
    private GoogleMap googleMap;
    private long lastFlexzoneRefreshTs = 0;
    private View mapDimVIew;
    private MapView mapView;
    private View progressView;
    private QrView qrButton;
    private RepeatTask refreshRepeatTask;
    private BookingDialogView reserveSheetView;
    private StationsMapHelper stationsMapHelper;
    private Polyline walkRouteToStationPolyline;

    /* renamed from: com.mevo.app.presentation.main.statitons_map.StationsMapFragment$1 */
    class C08271 implements MapCallback {
        C08271() {
        }

        public void onRefreshClick() {
            StationsMapFragment.this.refreshAll();
        }

        public void refreshMapAndDialog() {
            StationsMapFragment.this.refreshAll();
        }

        public boolean isCurrentLocationInFlexzone() {
            Location lastKnownLocation = LocationTool.get().getLastKnownLocation();
            if (lastKnownLocation == null) {
                return false;
            }
            return MapUtils.checkIfPointInLayer(StationsMapFragment.this.stationsMapHelper.getFlexzonesMapLayer(), lastKnownLocation);
        }
    }

    static final /* synthetic */ void lambda$null$265$StationsMapFragment(boolean z, boolean z2) {
    }

    public static StationsMapFragment newInstance() {
        return new StationsMapFragment();
    }

    public static StationsMapFragment newInstance(int i, LatLng latLng) {
        StationsMapFragment stationsMapFragment = new StationsMapFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_STATION_TO_SHOW_ID, i);
        bundle.putParcelable(ARG_STATION_TO_SHOW_LAT_LNG, latLng);
        stationsMapFragment.setArguments(bundle);
        return stationsMapFragment;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.stationsMapHelper.onCreate(getArguments().getInt(ARG_STATION_TO_SHOW_ID, 0), (LatLng) getArguments().getParcelable(ARG_STATION_TO_SHOW_LAT_LNG), (CameraPosition) getArguments().getParcelable(ARG_CAMERA_POSITION));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_stations_map, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        this.stationsMapHelper = new StationsMapHelper(this);
        this.bottomSheetManager = new HomeBottomSheetManager(this.currentRentalsDialogView, this.reserveSheetView, this.mapDimVIew, this.qrButton);
        this.refreshRepeatTask = new RepeatTask(REFRESH_INTERVAL_MS, false, new StationsMapFragment$$Lambda$0(this));
        this.mapView.getMapAsync(this);
        this.mapView.onCreate(bundle);
        refreshAll();
        return layoutInflater;
    }

    public void onStart() {
        super.onStart();
        Bus.register(RefreshMapAndSheetListener.class, this);
        this.mapView.onStart();
        this.currentRentalsDialogView.onStart();
        this.refreshRepeatTask.start();
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
        Bus.unregister(RefreshMapAndSheetListener.class, this);
        this.lastFlexzoneRefreshTs = 0;
        this.mapView.onStop();
        this.stationsMapHelper.onStop();
        this.currentRentalsDialogView.onStop();
        this.refreshRepeatTask.stop();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.stationsMapHelper.onDestroy();
        this.googleMap = null;
        this.bottomSheetManager.onDestroy();
        if (this.activityInterface != null) {
            this.activityInterface.setProgressViewVisibility(false);
        }
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
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (this.googleMap != null) {
            bundle.putParcelable(ARG_CAMERA_POSITION, this.googleMap.getCameraPosition());
        }
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

    private void findViews(View view) {
        this.mapView = (MapView) view.findViewById(C0434R.id.fragment_stations_map_map_view);
        this.progressView = view.findViewById(C0434R.id.fragment_stations_map_progress_bar);
        this.currentRentalsDialogView = (CurrentRentalsDialogView) view.findViewById(C0434R.id.fragment_stations_map_renting_sheet_root);
        this.reserveSheetView = (BookingDialogView) view.findViewById(C0434R.id.fragment_stations_map_reserve_sheet_root);
        this.qrButton = (QrView) view.findViewById(C0434R.id.button_qrcode);
        this.mapDimVIew = view.findViewById(C0434R.id.fragment_stations_map_map_dim_view);
    }

    private void setListeners() {
        this.qrButton.setOnClickListener(new StationsMapFragment$$Lambda$1(this));
        this.currentRentalsDialogView.setMapCallback(new C08271());
    }

    final /* synthetic */ void lambda$setListeners$261$StationsMapFragment(View view) {
        if (getActivityInterface() != null) {
            getActivityInterface().changeFragment(RentBikeQrFragment.newInstance());
        }
    }

    @SuppressLint({"MissingPermission"})
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.stationsMapHelper.onMapReady(googleMap);
        this.googleMap.getUiSettings().setZoomControlsEnabled(false);
        fetchStationsData();
        if (Permissions.hasPermissions(Permissions.PERMISSIONS_LOCATION, getActivity()) != null) {
            this.googleMap.setMyLocationEnabled(true);
        }
    }

    private void fetchStationsData() {
        this.progressView.setVisibility(0);
        StationsDataRepository.get().fetchStationsData(new StationsMapFragment$$Lambda$2(this));
    }

    final /* synthetic */ void lambda$fetchStationsData$262$StationsMapFragment(boolean z, List list) {
        this.progressView.setVisibility(8);
        if (z) {
            this.stationsMapHelper.setStationsOnMap(list, IM.activity());
            if (this.stationsMapHelper.didUserSelectPlaceToShow()) {
                centerOnPlaceAfterMapPrepared(list);
                return;
            }
            return;
        }
        TopToast.show(true, null, TopToast.DURATION_SHORT);
    }

    private void centerOnPlaceAfterMapPrepared(List<Place> list) {
        this.progressView.setVisibility(0);
        this.stationsMapHelper.centerOnPlaceAfterMapPrepared(list, new StationsMapFragment$$Lambda$3(this));
    }

    final /* synthetic */ void lambda$centerOnPlaceAfterMapPrepared$263$StationsMapFragment(boolean z) {
        this.progressView.setVisibility(8);
    }

    private void centerOnPin(LatLng latLng) {
        this.googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng), 300, null);
    }

    public boolean onStationsClusterClick(Cluster<Place> cluster) {
        if (this.googleMap == null) {
            return null;
        }
        Builder builder = new Builder();
        for (Place position : cluster.getItems()) {
            builder.include(position.getPosition());
        }
        this.googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), Screen.dpToPx(App.getAppContext().getResources().getDimension(C0434R.dimen.space_standard))));
        return true;
    }

    public void onStationPinClick(@NonNull Place place, @NonNull Marker marker) {
        centerOnPin(place.getPosition());
        GoogleMapHelper.removePolyline(this.walkRouteToStationPolyline);
        new NextbikeApiRepository().getStationExtraMapData(place, new StationsMapFragment$$Lambda$4(this, marker));
        marker.setTag(new MapStationData(place, null, null, null));
        marker.showInfoWindow();
        this.reserveSheetView.fetchBikesForStation(place.getName(), marker.getPosition(), place.getUid());
    }

    final /* synthetic */ void lambda$onStationPinClick$264$StationsMapFragment(@NonNull Marker marker, MapStationData mapStationData, boolean z, Exception exception) {
        if (marker.isInfoWindowShown()) {
            marker.setTag(mapStationData);
            marker.showInfoWindow();
        }
        GoogleMapHelper.removePolyline(this.walkRouteToStationPolyline);
        this.walkRouteToStationPolyline = GoogleMapHelper.tryDrawRoute(this.googleMap, mapStationData.routePoints);
    }

    public void onStationInfoWindowClick(Place place) {
        if (StationTypes.getStationType(place.getNumber(), place.getPlaceType()) != StationType.SINGLE_BIKE) {
            this.bottomSheetManager.showReserveSheet(true);
        } else if (this.activityInterface == null) {
            TopToast.show(C0434R.string.error_ocurred, 0, TopToast.DURATION_SHORT);
        } else {
            this.activityInterface.setProgressViewVisibility(true);
            new NextbikeApiRepository().isBookingAvailable(new StationsMapFragment$$Lambda$5(this, place));
        }
    }

    final /* synthetic */ void lambda$onStationInfoWindowClick$267$StationsMapFragment(Place place, boolean z, boolean z2) {
        this.activityInterface.setProgressViewVisibility(false);
        if (!z) {
            TopToast.show(C0434R.string.error_ocurred, 0, true);
        } else if (z2) {
            z = BikeReservationDialog.newInstance(place.getBikeNumbers());
            z.show();
            z.setDecisionListener(new StationsMapFragment$$Lambda$7(place));
        } else {
            new InfoDialog.Builder().setText((int) true).setNeutralButton((int) true, null).build().show();
        }
    }

    static final /* synthetic */ void lambda$null$266$StationsMapFragment(Place place, boolean z, String str) {
        if (z) {
            new BookingRepository().onBookingDecision(place, str, StationsMapFragment$$Lambda$8.$instance);
        }
    }

    private void setupFlexzonesIfShould() {
        if (this.lastFlexzoneRefreshTs + FLEXZONE_REFRESH_INTERVAL_MS <= System.currentTimeMillis()) {
            Log.m94i(TAG, "Refreshing flexzone");
            new FlexzonesRepository().getFlexzones(new StationsMapFragment$$Lambda$6(this));
        }
    }

    final /* synthetic */ void lambda$setupFlexzonesIfShould$268$StationsMapFragment(ResponseFlexzones responseFlexzones, boolean z, Exception exception) {
        if (z) {
            try {
                this.stationsMapHelper.setFlexzoneLayerOnMap(new JSONObject(responseFlexzones.getGeojson().getContent()));
                this.lastFlexzoneRefreshTs = System.currentTimeMillis();
            } catch (ResponseFlexzones responseFlexzones2) {
                Log.ex(responseFlexzones2);
            }
        }
    }

    public void refreshAll() {
        setupFlexzonesIfShould();
        GoogleMapHelper.removePolyline(this.walkRouteToStationPolyline);
        fetchStationsData();
        this.currentRentalsDialogView.refreshData();
    }
}
