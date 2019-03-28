package com.mevo.app.constants;

import com.mevo.app.data.model.Place;

public class StationTypes {

    public enum StationType {
        STANDARD,
        ELECTRIC,
        KIDS,
        SINGLE_BIKE
    }

    public static StationType getStationType(String str, int i) {
        if (i == 12) {
            return StationType.SINGLE_BIKE;
        }
        return StationType.STANDARD;
    }

    public static boolean isOneBikeType(Place place) {
        return getStationType(place.getNumber(), place.getPlaceType()) == StationType.SINGLE_BIKE ? true : null;
    }
}
