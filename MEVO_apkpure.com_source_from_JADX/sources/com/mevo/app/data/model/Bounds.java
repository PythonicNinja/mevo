package com.mevo.app.data.model;

import com.google.gson.annotations.SerializedName;

public class Bounds {
    @SerializedName("north_east")
    Bound northEast;
    @SerializedName("south_west")
    Bound southWest;

    public static class Bound {
        @SerializedName("lat")
        double lat;
        @SerializedName("lng")
        double lng;
    }
}
