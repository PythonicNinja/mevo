package com.mevo.app.tools.map;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.ImageView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.constants.StationTypes;
import com.mevo.app.data.model.Place;
import com.mevo.app.presentation.custom_views.PinView;

public class BikeClusterRenderer extends DefaultClusterRenderer<Place> {
    private static final int DEFAULT_MIN_MARKERS_DENSITY = 5;
    private static BitmapDescriptor pinDefault;
    private static BitmapDescriptor pinSingleBike;
    private static SparseArray<BitmapDescriptor> stationsPinsCache;
    private IconGenerator clusterIconGeneratorBasic;
    private IconGenerator clusterIconGeneratorElectric;
    private IconGenerator clusterIconGeneratorKids;
    private BitmapDescriptor markerKidsBitmap;
    private int minMarkersDensity;

    public BikeClusterRenderer(Context context, GoogleMap googleMap, ClusterManager<Place> clusterManager) {
        super(context, googleMap, clusterManager);
        setup(context, 5);
    }

    public BikeClusterRenderer(Context context, GoogleMap googleMap, ClusterManager<Place> clusterManager, int i) {
        super(context, googleMap, clusterManager);
        setup(context, i);
    }

    private void setup(Context context, int i) {
        prepareCacheIfShould();
        this.markerKidsBitmap = BitmapDescriptorFactory.fromResource(C0434R.drawable.marker_kids_32dp);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.clusterIconGeneratorBasic = createIconGenerator(context, layoutInflater, C0434R.drawable.cluster_basic);
        this.clusterIconGeneratorKids = createIconGenerator(context, layoutInflater, C0434R.drawable.cluster_kids);
        this.clusterIconGeneratorElectric = createIconGenerator(context, layoutInflater, C0434R.drawable.cluster_electric);
        this.minMarkersDensity = i;
    }

    private static void prepareCacheIfShould() {
        if (stationsPinsCache == null || stationsPinsCache.size() <= 0) {
            stationsPinsCache = new SparseArray();
            for (int i = 0; i < 16; i++) {
                stationsPinsCache.put(i, createPinForStation(i));
            }
            IconGenerator iconGenerator = new IconGenerator(IM.activity());
            iconGenerator.setContentView(new PinView(IM.activity()).bindData(1, true));
            iconGenerator.setBackground(null);
            pinSingleBike = BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon());
            pinDefault = BitmapDescriptorFactory.fromResource(C0434R.drawable.marker_basic_32dp);
        }
    }

    private static BitmapDescriptor createPinForStation(int i) {
        IconGenerator iconGenerator = new IconGenerator(IM.activity());
        iconGenerator.setContentView(new PinView(IM.activity()).bindData(i, false));
        iconGenerator.setBackground(0);
        return BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon());
    }

    private IconGenerator createIconGenerator(Context context, LayoutInflater layoutInflater, @DrawableRes int i) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.cluster_view, null, false);
        ((ImageView) layoutInflater.findViewById(C0434R.id.cluster_view_image)).setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), i, null));
        i = new IconGenerator(context);
        i.setContentView(layoutInflater);
        i.setBackground(null);
        return i;
    }

    private BitmapDescriptor getPinForStation(int i) {
        BitmapDescriptor bitmapDescriptor = (BitmapDescriptor) stationsPinsCache.get(i);
        if (bitmapDescriptor != null) {
            return bitmapDescriptor;
        }
        bitmapDescriptor = createPinForStation(i);
        stationsPinsCache.put(i, bitmapDescriptor);
        return bitmapDescriptor;
    }

    protected void onBeforeClusterItemRendered(Place place, MarkerOptions markerOptions) {
        markerOptions.icon(getPinBitmapForPlace(place));
    }

    protected void onBeforeClusterRendered(Cluster<Place> cluster, MarkerOptions markerOptions) {
        IconGenerator iconGeneratorForCluster = getIconGeneratorForCluster(cluster);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(cluster.getSize());
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(iconGeneratorForCluster.makeIcon(stringBuilder.toString())));
    }

    private BitmapDescriptor getPinBitmapForPlace(Place place) {
        switch (StationTypes.getStationType(place.getNumber(), place.getPlaceType())) {
            case SINGLE_BIKE:
                return pinSingleBike;
            case KIDS:
                return this.markerKidsBitmap;
            default:
                return getPinForStation(place.getBikesNumAvailableToRent());
        }
    }

    private IconGenerator getIconGeneratorForCluster(Cluster<Place> cluster) {
        return this.clusterIconGeneratorBasic;
    }

    protected boolean shouldRenderAsCluster(Cluster cluster) {
        return cluster.getSize() >= this.minMarkersDensity ? true : null;
    }
}
