package com.mevo.app.data.model;

public class FoundBike {
    private String bikeType;
    private String distance;
    private String place;

    public String getBikeType() {
        return this.bikeType;
    }

    public void setBikeType(String str) {
        this.bikeType = str;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String str) {
        this.place = str;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setDistance(String str) {
        this.distance = str;
    }
}
