package com.mevo.app.data.bike_data_handlers;

public class BikeDataHandlers {
    public static double getBikeRangeMeters(int i) {
        return i <= 0 ? 0.0d : ((((double) i) * 60.0d) / 100.0d) * 1000.0d;
    }
}
