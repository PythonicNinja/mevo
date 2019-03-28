package com.mevo.app.data.repository;

import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Pair;
import com.annimon.stream.Stream;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.constants.Servers;
import com.mevo.app.data.model.GoogleRoute;
import com.mevo.app.data.model.GoogleRoute.Leg;
import com.mevo.app.data.model.RentalDistance;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.RentalRoute;
import com.mevo.app.data.model.RentalRoute_Table;
import com.mevo.app.data.model.request.RequestArgsRentalsDistances;
import com.mevo.app.data.model.response.ResponseGoogleRoute;
import com.mevo.app.data.model.response.ResponseLatLon;
import com.mevo.app.data.model.response.ResponseRentalBasedPositions;
import com.mevo.app.data.model.response.ResponseRentalsDistances;
import com.mevo.app.data.network.ApiExtended;
import com.mevo.app.data.network.Rest;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.location.LocationTool;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DirectionsRepository {
    private static final double AVERAGE_SPEED_KM_H = 12.0d;
    private static final double AVERAGE_SPEED_M_S = 3.3333333333333335d;
    private static final String MODE_BICYCLE = "bicycling";
    private static final String MODE_DRIVING = "driving";
    private static final String MODE_WALK = "walking";
    private static final String TAG = "DirectionsRepository";

    public interface DirectionsResponseListener {
        void onResponse(List<LatLng> list, long j, boolean z, Exception exception);
    }

    public interface ResponseListener<T> {
        void onResponse(T t, boolean z, Exception exception);
    }

    public void getRoutesForRentalItems(@NonNull List<RentalItem> list, @NonNull ResponseListener<List<Pair<RentalItem, RentalRoute>>> responseListener) {
        if (list.isEmpty()) {
            responseListener.onResponse(new ArrayList(), true, null);
        }
        IM.onBg().execute(new DirectionsRepository$$Lambda$0(this, list, responseListener));
    }

    final /* synthetic */ void lambda$getRoutesForRentalItems$10$DirectionsRepository(@NonNull List list, @NonNull ResponseListener responseListener) {
        List arrayList = new ArrayList();
        for (RentalItem rentalItem : list) {
            arrayList.add(new Pair(rentalItem, getRouteForRentalItemSync(rentalItem)));
        }
        IM.onUi().execute(new DirectionsRepository$$Lambda$7(responseListener, arrayList, arrayList.size() > null ? true : null, arrayList.size() > 0 ? null : new Exception("Didn't get directions for single rental route")));
    }

    public void getDistancesForRentalItemsNew(@NonNull final List<RentalItem> list, @NonNull final ResponseListener<List<Pair<RentalItem, RentalDistance>>> responseListener) {
        Rest.getApiExtended().getDistanceForRentals(new RequestArgsRentalsDistances(Servers.POLLS_APIKEY, Stream.of((Iterable) list).map(DirectionsRepository$$Lambda$1.$instance).toList())).enqueue(new Callback<ResponseRentalsDistances>() {
            public void onResponse(Call<ResponseRentalsDistances> call, Response<ResponseRentalsDistances> response) {
                if (response.body() == null || ((ResponseRentalsDistances) response.body()).getDistances() == null) {
                    responseListener.onResponse(null, null, null);
                    return;
                }
                call = new ArrayList();
                for (Integer intValue : ((ResponseRentalsDistances) response.body()).getDistances().keySet()) {
                    Object obj;
                    int intValue2 = intValue.intValue();
                    Double d = (Double) ((ResponseRentalsDistances) response.body()).getDistances().get(Integer.valueOf(intValue2));
                    if (d != null) {
                        RentalDistance rentalDistance = new RentalDistance(intValue2, (((long) (d.doubleValue() / DirectionsRepository.AVERAGE_SPEED_KM_H)) * 60) * 60, 1000.0d * d.doubleValue());
                    } else {
                        obj = null;
                    }
                    call.add(new Pair(Stream.of(list).filter(new DirectionsRepository$1$$Lambda$0(intValue2)).findFirst().orElse(null), obj));
                }
                responseListener.onResponse(call, true, null);
            }

            static final /* synthetic */ boolean lambda$onResponse$12$DirectionsRepository$1(int i, RentalItem rentalItem) {
                return rentalItem.id == i;
            }

            public void onFailure(Call<ResponseRentalsDistances> call, Throwable th) {
                responseListener.onResponse(null, false, new Exception(th));
            }
        });
    }

    public void getRouteForRentalItem(@NonNull RentalItem rentalItem, @NonNull ResponseListener<RentalRoute> responseListener) {
        getRouteForRentalItem(rentalItem, true, responseListener);
    }

    public void getRouteForRentalItem(@NonNull RentalItem rentalItem, boolean z, @NonNull ResponseListener<RentalRoute> responseListener) {
        IM.onBg().execute(new DirectionsRepository$$Lambda$2(this, rentalItem, z, responseListener));
    }

    final /* synthetic */ void lambda$getRouteForRentalItem$14$DirectionsRepository(@NonNull RentalItem rentalItem, boolean z, @NonNull ResponseListener responseListener) {
        Exception exception;
        z = getRouteForRentalItemSync(rentalItem, z);
        boolean z2 = z;
        if (z) {
            exception = null;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Couldn't get route for rental item id: ");
            stringBuilder.append(rentalItem.id);
            exception = new Exception(stringBuilder.toString());
        }
        IM.onUi().execute(new DirectionsRepository$$Lambda$6(responseListener, z, z2, exception));
    }

    public RentalRoute getRouteForRentalItemSync(@NonNull RentalItem rentalItem) {
        return getRouteForRentalItemSync(rentalItem, true);
    }

    public RentalRoute getRouteForRentalItemSync(@NonNull RentalItem rentalItem, boolean z) {
        Response execute;
        if (z) {
            RentalRoute rentalRoute = (RentalRoute) SQLite.select(new IProperty[0]).from(RentalRoute.class).where(RentalRoute_Table.rentalId.eq(Integer.valueOf(rentalItem.id))).querySingle();
            if (rentalRoute != null) {
                return rentalRoute;
            }
        }
        try {
            execute = Rest.getApiExtended().getRentalBasedPositions(Servers.MEVO_HEATMAP_KEY, String.valueOf(rentalItem.id)).execute();
        } catch (Throwable e) {
            Log.ex(e);
            execute = null;
        }
        ResponseRentalBasedPositions responseRentalBasedPositions = execute != null ? (ResponseRentalBasedPositions) execute.body() : null;
        if (responseRentalBasedPositions == null || responseRentalBasedPositions.data.isEmpty()) {
            return null;
        }
        z = Stream.of(responseRentalBasedPositions.data).filter(DirectionsRepository$$Lambda$3.$instance).map(DirectionsRepository$$Lambda$4.$instance).toList();
        LatLng startLatLng = rentalItem.getStartLatLng();
        LatLng endLatLng = rentalItem.getEndLatLng();
        if (startLatLng != null) {
            z.add(0, startLatLng);
        }
        if (endLatLng != null) {
            z.add(endLatLng);
        }
        RentalRoute rentalRoute2 = new RentalRoute(rentalItem.id, z);
        rentalRoute2.save();
        return rentalRoute2;
    }

    static final /* synthetic */ boolean lambda$getRouteForRentalItemSync$15$DirectionsRepository(ResponseLatLon responseLatLon) {
        return (responseLatLon.lat == 0.0d || responseLatLon.lon == 0.0d) ? null : true;
    }

    public static long getGoogleRouteDurationSeconds(GoogleRoute googleRoute) {
        long j = 0;
        if (!(googleRoute.legs == null || googleRoute.legs.isEmpty())) {
            for (Leg leg : googleRoute.legs) {
                if (leg.duration != null) {
                    j += leg.duration.value;
                }
            }
        }
        return j;
    }

    public void getDirections(LatLng latLng, @NonNull DirectionsResponseListener directionsResponseListener) {
        if (LocationTool.areLocationServicesEnabled()) {
            Location lastKnownLocation = LocationTool.get().getLastKnownLocation();
            if (lastKnownLocation != null) {
                getRoute(lastKnownLocation, latLng, directionsResponseListener);
            } else {
                LocationTool.get().getCurrentLocationAsync(new DirectionsRepository$$Lambda$5(this, latLng, directionsResponseListener));
            }
            return;
        }
        directionsResponseListener.onResponse(null, 0, false, null);
    }

    final /* synthetic */ void lambda$getDirections$17$DirectionsRepository(LatLng latLng, @NonNull DirectionsResponseListener directionsResponseListener, Location location) {
        if (location != null) {
            getRoute(location, latLng, directionsResponseListener);
        } else {
            directionsResponseListener.onResponse(null, 0, false, null);
        }
    }

    private void getRoute(Location location, LatLng latLng, final DirectionsResponseListener directionsResponseListener) {
        ApiExtended apiExtended = Rest.getApiExtended();
        String string = IM.context().getString(C0434R.string.google_diractions_api_key);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(latLng.latitude);
        stringBuilder.append(",");
        stringBuilder.append(latLng.longitude);
        latLng = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(location.getLatitude());
        stringBuilder.append(",");
        stringBuilder.append(location.getLongitude());
        apiExtended.getRoute(string, latLng, stringBuilder.toString(), "walking").enqueue(new Callback<ResponseGoogleRoute>() {
            public void onResponse(Call<ResponseGoogleRoute> call, Response<ResponseGoogleRoute> response) {
                long j;
                ResponseGoogleRoute responseGoogleRoute = (ResponseGoogleRoute) response.body();
                if (response.isSuccessful() == null || responseGoogleRoute == null || responseGoogleRoute.routes == null || responseGoogleRoute.routes.isEmpty() != null) {
                    response = null;
                    j = 0;
                } else {
                    GoogleRoute googleRoute = (GoogleRoute) responseGoogleRoute.routes.get(0);
                    response = PolyUtil.decode(googleRoute.overviewPolyline.encodedPolyline);
                    j = DirectionsRepository.getGoogleRouteDurationSeconds(googleRoute);
                }
                Response<ResponseGoogleRoute> response2 = response;
                directionsResponseListener.onResponse(response2, j, response2 != null, null);
            }

            public void onFailure(Call<ResponseGoogleRoute> call, Throwable th) {
                directionsResponseListener.onResponse(null, 0, false, null);
            }
        });
    }
}
