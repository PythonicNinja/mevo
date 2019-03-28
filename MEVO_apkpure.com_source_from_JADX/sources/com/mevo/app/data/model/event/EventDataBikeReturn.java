package com.mevo.app.data.model.event;

public class EventDataBikeReturn {
    public static final String ACTION_BIKE_RETURN = "ACTION_BIKE_RETURN";
    public static final String EXTRA_BIKE_NUMBER = "EXTRA_BIKE_NUMBER";
    private String bikeNumer;

    public EventDataBikeReturn(String str) {
        this.bikeNumer = str;
    }

    public String getBikeNumer() {
        return this.bikeNumer;
    }

    public EventDataBikeReturn setBikeNumer(String str) {
        this.bikeNumer = str;
        return this;
    }
}
