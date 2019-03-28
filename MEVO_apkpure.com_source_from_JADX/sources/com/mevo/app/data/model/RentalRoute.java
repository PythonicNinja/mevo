package com.mevo.app.data.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;
import com.raizlabs.android.dbflow.structure.BaseModel;
import java.util.List;

public class RentalRoute extends BaseModel {
    private double distanceMeters;
    private int rentalId;
    private List<LatLng> rentalRoute;

    public RentalRoute(int i, List<LatLng> list) {
        this.rentalId = i;
        this.rentalRoute = list;
    }

    public int getRentalId() {
        return this.rentalId;
    }

    public RentalRoute setRentalId(int i) {
        this.rentalId = i;
        return this;
    }

    public List<LatLng> getRentalRoute() {
        return this.rentalRoute;
    }

    public RentalRoute setRentalRoute(List<LatLng> list) {
        this.rentalRoute = list;
        return this;
    }

    public double getDistanceMeters() {
        if (this.rentalRoute != null) {
            if (!this.rentalRoute.isEmpty()) {
                if (this.distanceMeters == 0.0d) {
                    this.distanceMeters = SphericalUtil.computeLength(this.rentalRoute);
                }
                return this.distanceMeters;
            }
        }
        return 0.0d;
    }
}
