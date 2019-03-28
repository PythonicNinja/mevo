package com.mevo.app.presentation.main.rent_summary;

import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Pair;
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
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.data.model.RentalDistance;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.RentalRoute;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.repository.DirectionsRepository;
import com.mevo.app.presentation.custom_views.ProgressOverlayView;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.Screen;
import com.mevo.app.tools.map.MapUtils;
import java.util.Collections;
import java.util.List;

public class RentSummaryFragment extends MainFragment implements OnMapReadyCallback, OnCameraIdleListener {
    private static final String ARG_RENTAL_ITEM_JSON = "ARG_RENTAL_ITEM_JSON";
    private static final String TAG = "RentSummaryFragment";
    private boolean centerOnNextIdle;
    private RentDetailsView detailsView;
    private GoogleMap googleMap;
    private MapView mapView;
    private ProgressOverlayView progressOverlayView;
    private RentalDistance rentalDistance;
    private RentalItem rentalItem;
    private RentalRoute rentalRoute;

    public static RentSummaryFragment newInstance(RentalItem rentalItem) {
        RentSummaryFragment rentSummaryFragment = new RentSummaryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_RENTAL_ITEM_JSON, Rest.getJsonSerializer().toJson((Object) rentalItem));
        rentSummaryFragment.setArguments(bundle);
        return rentSummaryFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_rent_summary, viewGroup, false);
        getArgsData();
        findViews(layoutInflater);
        this.centerOnNextIdle = true;
        this.mapView.onCreate(bundle);
        this.mapView.getMapAsync(this);
        fetchRoute();
        return layoutInflater;
    }

    public void onStart() {
        super.onStart();
        this.mapView.onStart();
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

    private void getArgsData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.rentalItem = (RentalItem) Rest.getJsonSerializer().fromJson(arguments.getString(ARG_RENTAL_ITEM_JSON), RentalItem.class);
        }
    }

    private void findViews(View view) {
        this.mapView = (MapView) view.findViewById(C0434R.id.fragment_rent_summary_map);
        this.progressOverlayView = (ProgressOverlayView) view.findViewById(C0434R.id.fragment_rent_summary_progress);
        this.detailsView = (RentDetailsView) view.findViewById(C0434R.id.detail_custom_view);
    }

    private void fetchRoute() {
        this.progressOverlayView.show();
        new DirectionsRepository().getRouteForRentalItem(this.rentalItem, new RentSummaryFragment$$Lambda$0(this));
    }

    final /* synthetic */ void lambda$fetchRoute$242$RentSummaryFragment(RentalRoute rentalRoute, boolean z, Exception exception) {
        this.rentalRoute = rentalRoute;
        tryDrawRoute();
        new DirectionsRepository().getDistancesForRentalItemsNew(Collections.singletonList(this.rentalItem), new RentSummaryFragment$$Lambda$1(this, z));
    }

    final /* synthetic */ void lambda$null$241$RentSummaryFragment(boolean z, List list, boolean z2, Exception exception) {
        if (z && list != null && list.get(0)) {
            this.rentalDistance = (RentalDistance) ((Pair) list.get(0)).second;
        }
        this.detailsView.setData(this.rentalItem, this.rentalDistance);
        this.progressOverlayView.hide();
    }

    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setOnCameraIdleListener(this);
        addMarkersToMap();
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        tryDrawRoute();
    }

    private void addMarkersToMap() {
        if (this.googleMap != null) {
            if (this.rentalItem.getStartLatLng() != null) {
                this.googleMap.addMarker(new MarkerOptions().position(this.rentalItem.getStartLatLng()).icon(BitmapDescriptorFactory.fromResource(C0434R.drawable.pin_rower1)));
            }
            if (this.rentalItem.getEndLatLng() != null) {
                this.googleMap.addMarker(new MarkerOptions().position(this.rentalItem.getEndLatLng()).icon(BitmapDescriptorFactory.fromResource(C0434R.drawable.pin_meta)));
            }
        }
    }

    private void moveCameraToRoute() {
        if (this.googleMap != null) {
            if (this.rentalItem.getStartLatLng() != null && this.rentalItem.getEndLatLng() != null) {
                Builder builder = new Builder();
                builder.include(this.rentalItem.getStartLatLng());
                builder.include(this.rentalItem.getEndLatLng());
                this.googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), Screen.dpToPx(App.getAppContext().getResources().getDimension(C0434R.dimen.space_standard))));
            } else if (this.rentalItem.getStartLatLng() != null) {
                this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(this.rentalItem.getStartLatLng(), 15.0f));
            } else {
                MapUtils.setMapPosToMevo(this.googleMap);
            }
        }
    }

    public void tryDrawRoute() {
        if (this.googleMap != null) {
            if (this.rentalRoute != null) {
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.color(ResourcesCompat.getColor(getResources(), C0434R.color.colorAccent, null));
                polylineOptions.width(7.0f);
                polylineOptions.addAll(this.rentalRoute.getRentalRoute());
                this.googleMap.addPolyline(polylineOptions);
            }
        }
    }

    public void onCameraIdle() {
        if (this.centerOnNextIdle) {
            moveCameraToRoute();
            this.centerOnNextIdle = false;
        }
    }
}
