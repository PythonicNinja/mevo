package com.google.maps.android.clustering;

import com.google.android.gms.maps.model.LatLng;
import java.util.Collection;

public interface Cluster<T extends ClusterItem> {
    Collection<T> getItems();

    LatLng getPosition();

    int getSize();
}
