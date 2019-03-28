package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StaticCluster<T extends ClusterItem> implements Cluster<T> {
    private final LatLng mCenter;
    private final List<T> mItems = new ArrayList();

    public StaticCluster(LatLng latLng) {
        this.mCenter = latLng;
    }

    public boolean add(T t) {
        return this.mItems.add(t);
    }

    public LatLng getPosition() {
        return this.mCenter;
    }

    public boolean remove(T t) {
        return this.mItems.remove(t);
    }

    public Collection<T> getItems() {
        return this.mItems;
    }

    public int getSize() {
        return this.mItems.size();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("StaticCluster{mCenter=");
        stringBuilder.append(this.mCenter);
        stringBuilder.append(", mItems.size=");
        stringBuilder.append(this.mItems.size());
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public int hashCode() {
        return this.mCenter.hashCode() + this.mItems.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof StaticCluster)) {
            return false;
        }
        StaticCluster staticCluster = (StaticCluster) obj;
        if (staticCluster.mCenter.equals(this.mCenter) && staticCluster.mItems.equals(this.mItems) != null) {
            z = true;
        }
        return z;
    }
}
