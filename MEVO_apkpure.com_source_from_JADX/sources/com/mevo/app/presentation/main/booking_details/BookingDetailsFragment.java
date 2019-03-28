package com.mevo.app.presentation.main.booking_details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.repository.DirectionsRepository;
import com.mevo.app.data.repository.NextbikeApiRepository;
import com.mevo.app.presentation.custom_views.ProgressOverlayView;
import com.mevo.app.presentation.dialogs.BikeInfoDialog;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.presentation.main.rent_bike.RentBikeQrFragment;
import com.mevo.app.presentation.main.statitons_map.BookingDetailsView;
import com.mevo.app.presentation.main.statitons_map.BookingDetailsView.ReservationDetailsListener;
import com.mevo.app.presentation.main.statitons_map.GoogleMapHelper;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.NetTools;
import com.mevo.app.tools.NetTools.CallsToken;
import com.mevo.app.tools.Permissions;
import com.mevo.app.tools.Screen;
import com.mevo.app.tools.location.LocationTool;
import com.mevo.app.tools.map.MapUtils;
import java.util.List;

public class BookingDetailsFragment extends MainFragment implements OnMapReadyCallback, OnCameraIdleListener, ReservationDetailsListener {
    private static final String ARG_BOOKING_ITEM_JSON = "ARG_BOOKING_ITEM_JSON";
    private static final String TAG = "BookingDetailsFragment";
    private BookingDetailsView bookingDetailsView;
    private BookingItem bookingItem;
    private CallsToken callsToken;
    private boolean centerOnNextIdle;
    private GoogleMap googleMap;
    private MapView mapView;
    private ProgressOverlayView progressOverlayView;
    private List<LatLng> routePoints;
    @Nullable
    LatLng startLocation;
    Polyline walkPolyline;

    public static BookingDetailsFragment newInstance(BookingItem bookingItem) {
        BookingDetailsFragment bookingDetailsFragment = new BookingDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_BOOKING_ITEM_JSON, Rest.getJsonSerializer().toJson((Object) bookingItem));
        bookingDetailsFragment.setArguments(bundle);
        return bookingDetailsFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_booking_details, viewGroup, false);
        getArgsData();
        viewGroup = LocationTool.get().getLastKnownLocation();
        if (viewGroup != null) {
            this.startLocation = LocationTool.toLatLng(viewGroup);
        }
        findViews(layoutInflater);
        this.centerOnNextIdle = true;
        this.callsToken = new CallsToken();
        this.mapView.onCreate(bundle);
        this.mapView.getMapAsync(this);
        fetchRoute();
        this.bookingDetailsView.setListener(this);
        this.bookingDetailsView.fillData(this.bookingItem);
        return layoutInflater;
    }

    public void onStart() {
        super.onStart();
        this.mapView.onStart();
        this.bookingDetailsView.onStart();
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
        this.bookingDetailsView.onStop();
    }

    public void onDestroyView() {
        super.onDestroyView();
        NetTools.cancelSavedOngoingCalls(this.callsToken);
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

    private void getArgsData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.bookingItem = (BookingItem) Rest.getJsonSerializer().fromJson(arguments.getString(ARG_BOOKING_ITEM_JSON), BookingItem.class);
        }
    }

    private void findViews(View view) {
        this.mapView = (MapView) view.findViewById(C0434R.id.fragment_rent_summary_map);
        this.progressOverlayView = (ProgressOverlayView) view.findViewById(C0434R.id.fragment_rent_summary_progress);
        this.bookingDetailsView = (BookingDetailsView) view.findViewById(C0434R.id.detail_custom_view);
    }

    @SuppressLint({"MissingPermission"})
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setOnCameraIdleListener(this);
        addMarkersToMap();
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        if (this.walkPolyline == null) {
            this.walkPolyline = GoogleMapHelper.tryDrawRoute(googleMap, this.routePoints);
        }
        if (Permissions.hasPermissions(Permissions.PERMISSIONS_LOCATION, getActivity()) != null) {
            this.googleMap.setMyLocationEnabled(true);
        }
    }

    private void addMarkersToMap() {
        if (!(this.googleMap == null || this.bookingItem.getCurrentLatLng() == null)) {
            this.googleMap.addMarker(new MarkerOptions().position(this.bookingItem.getCurrentLatLng()).icon(BitmapDescriptorFactory.fromResource(C0434R.drawable.pin_meta)));
        }
    }

    private void moveCameraToRoute() {
        if (this.googleMap != null) {
            if (this.startLocation != null && this.bookingItem.getCurrentLatLng() != null) {
                Builder builder = new Builder();
                builder.include(this.startLocation);
                builder.include(this.bookingItem.getCurrentLatLng());
                this.googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), Screen.dpToPx(App.getAppContext().getResources().getDimension(C0434R.dimen.space_standard))));
            } else if (this.startLocation != null) {
                this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(this.bookingItem.getCurrentLatLng(), 15.0f));
            } else {
                MapUtils.setMapPosToMevo(this.googleMap);
            }
        }
    }

    private void fetchRoute() {
        if (this.startLocation != null) {
            if (this.bookingItem.getCurrentLatLng() != null) {
                this.progressOverlayView.show();
                new DirectionsRepository().getDirections(this.bookingItem.getCurrentLatLng(), new BookingDetailsFragment$$Lambda$0(this));
            }
        }
    }

    final /* synthetic */ void lambda$fetchRoute$174$BookingDetailsFragment(List list, long j, boolean z, Exception exception) {
        if (z && list != null) {
            this.routePoints = list;
            this.walkPolyline = GoogleMapHelper.tryDrawRoute(this.googleMap, list);
        }
        this.bookingDetailsView.setWalkTime(Long.valueOf(j));
        this.progressOverlayView.hide();
    }

    public void onCameraIdle() {
        if (this.centerOnNextIdle) {
            moveCameraToRoute();
            this.centerOnNextIdle = false;
        }
    }

    public void onRentBookedBike(BookingItem bookingItem) {
        if (getActivityInterface() != null) {
            getActivityInterface().changeFragment(RentBikeQrFragment.newInstance());
        }
    }

    public void onCancelBookingBike(BookingItem bookingItem) {
        if (getActivityInterface() != null) {
            String valueOf = String.valueOf(bookingItem.id);
            getActivityInterface().setProgressViewVisibility(true);
            new NextbikeApiRepository().cancelBooking(bookingItem, new BookingDetailsFragment$$Lambda$1(this, valueOf));
        }
    }

    final /* synthetic */ void lambda$onCancelBookingBike$176$BookingDetailsFragment(String str, boolean z, Exception exception) {
        if (getActivityInterface() != null) {
            getActivityInterface().setProgressViewVisibility(false);
            if (z) {
                new BikeInfoDialog.Builder().setBikeInfo(getContext().getString(C0434R.string.bike_reservation_cancel_success), str).setNeutralButton((int) C0434R.string.close, new BookingDetailsFragment$$Lambda$2(this)).build().show();
            } else {
                new BikeInfoDialog.Builder().setBikeInfo(getContext().getString(C0434R.string.bike_reservation_cancel_fail), str).setNeutralButton((int) C0434R.string.close, null).build().show();
            }
        }
    }

    final /* synthetic */ void lambda$null$175$BookingDetailsFragment(View view) {
        if (getActivityInterface() != null && (getActivityInterface().getVisibleFragment() instanceof BookingDetailsFragment) != null) {
            getActivityInterface().popFragment();
        }
    }
}
