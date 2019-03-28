package com.mevo.app.data.model;

import com.raizlabs.android.dbflow.structure.BaseModel;

public class RentalDistance extends BaseModel {
    private double distanceMeters;
    private long durationSeconds;
    private int rentalId;

    public RentalDistance(int i, long j, double d) {
        this.rentalId = i;
        this.durationSeconds = j;
        this.distanceMeters = d;
    }

    public int getRentalId() {
        return this.rentalId;
    }

    public RentalDistance setRentalId(int i) {
        this.rentalId = i;
        return this;
    }

    public long getDurationSeconds() {
        return this.durationSeconds;
    }

    public void setDurationSeconds(long j) {
        this.durationSeconds = j;
    }

    public double getDistanceMeters() {
        return this.distanceMeters;
    }

    public RentalDistance setDistanceMeters(double d) {
        this.distanceMeters = d;
        return this;
    }
}
