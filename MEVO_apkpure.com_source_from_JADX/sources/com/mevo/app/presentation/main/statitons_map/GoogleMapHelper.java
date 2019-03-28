package com.mevo.app.presentation.main.statitons_map;

import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.tools.Screen;
import java.util.Arrays;
import java.util.List;

public class GoogleMapHelper {
    @Nullable
    public static Polyline tryDrawRoute(GoogleMap googleMap, List<LatLng> list) {
        if (!(googleMap == null || list == null)) {
            if (!list.isEmpty()) {
                List asList = Arrays.asList(new PatternItem[]{new Dot(), new Gap(22.0f)});
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.color(ResourcesCompat.getColor(IM.resources(), C0434R.color.routeToPinColor, null));
                polylineOptions.width(28.0f);
                polylineOptions.addAll(list);
                polylineOptions.pattern(asList);
                return googleMap.addPolyline(polylineOptions);
            }
        }
        return null;
    }

    public static void removePolyline(@Nullable Polyline polyline) {
        if (polyline != null) {
            polyline.remove();
        }
    }

    public static void centerOnRouteWithWindowInfo(GoogleMap googleMap, List<LatLng> list) {
        Builder builder = LatLngBounds.builder();
        for (LatLng include : list) {
            builder.include(include);
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), Screen.dpToPx(160.0f)));
    }
}
