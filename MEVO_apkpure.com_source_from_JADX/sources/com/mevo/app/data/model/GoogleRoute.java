package com.mevo.app.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GoogleRoute {
    @SerializedName("legs")
    public List<Leg> legs;
    @SerializedName("overview_polyline")
    public OverviewPolyline overviewPolyline;

    public static class Distance {
        @SerializedName("text")
        public String text;
        @SerializedName("value")
        public long value;
    }

    public static class Duration {
        @SerializedName("text")
        public String text;
        @SerializedName("value")
        public long value;
    }

    public static class Leg {
        @SerializedName("distance")
        public Distance distance;
        @SerializedName("duration")
        public Duration duration;
    }

    public static class OverviewPolyline {
        @SerializedName("points")
        public String encodedPolyline;
    }
}
