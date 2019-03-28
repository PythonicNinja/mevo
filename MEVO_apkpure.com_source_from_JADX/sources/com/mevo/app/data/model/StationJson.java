package com.mevo.app.data.model;

import android.location.Location;
import android.support.annotation.Nullable;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class StationJson implements ClusterItem {
    @SerializedName("available_bikes")
    private int availableBikesCount;
    @SerializedName("bikes_available_to_book")
    private int availableBikesToBookCount;
    @SerializedName("bike")
    private boolean bike;
    @SerializedName("bike_numbers")
    private List<String> bikeNumbers;
    @SerializedName("bike_types")
    private HashMap<String, Integer> bikeTypes;
    @SerializedName("bikes")
    private List<BikeJson> bikes;
    @SerializedName("num_booked_bikes")
    private int bookedBikesCount;
    @SerializedName("broken_racks")
    private int brokenRacks;
    @SerializedName("city_name")
    private String cityName;
    @SerializedName("domain")
    private String domain;
    @SerializedName("free_racks")
    private int freeBikeRacks;
    @SerializedName("spot")
    private boolean isSpot;
    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;
    @SerializedName("maintenance")
    private boolean maintenance;
    @SerializedName("name")
    private String name;
    @SerializedName("place_type")
    private int placeType;
    @Nullable
    private transient LatLng position;
    @SerializedName("number")
    private String stationNumber;
    @Nullable
    private transient Long timeSeconds;
    @SerializedName("bike_racks")
    private int totalBikeRacks;
    @SerializedName("uid")
    private int uid;

    public static class Sort {
        public static Comparator<StationJson> byName() {
            return StationJson$Sort$$Lambda$0.$instance;
        }

        static final /* synthetic */ int lambda$byName$4$StationJson$Sort(StationJson stationJson, StationJson stationJson2) {
            stationJson = stationJson.name;
            stationJson2 = stationJson2.name;
            if (stationJson == null) {
                return stationJson2 == null ? null : 1;
            } else {
                return stationJson.compareTo(stationJson2);
            }
        }

        public static Comparator<StationJson> byDistanceTo(Location location) {
            return new StationJson$Sort$$Lambda$1(location);
        }
    }

    public int getUid() {
        return this.uid;
    }

    public StationJson setUid(int i) {
        this.uid = i;
        return this;
    }

    public String getStationNumber() {
        return this.stationNumber;
    }

    public StationJson setStationNumber(String str) {
        this.stationNumber = str;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public StationJson setName(String str) {
        this.name = str;
        return this;
    }

    public int getTotalBikeRacks() {
        return this.totalBikeRacks;
    }

    public StationJson setTotalBikeRacks(int i) {
        this.totalBikeRacks = i;
        return this;
    }

    public double getLat() {
        return this.lat;
    }

    public StationJson setLat(double d) {
        this.lat = d;
        return this;
    }

    public double getLng() {
        return this.lng;
    }

    public StationJson setLng(double d) {
        this.lng = d;
        return this;
    }

    public boolean isSpot() {
        return this.isSpot;
    }

    public StationJson setSpot(boolean z) {
        this.isSpot = z;
        return this;
    }

    public String getCityName() {
        return this.cityName;
    }

    public StationJson setCityName(String str) {
        this.cityName = str;
        return this;
    }

    public int getPlaceType() {
        return this.placeType;
    }

    public StationJson setPlaceType(int i) {
        this.placeType = i;
        return this;
    }

    public String getDomain() {
        return this.domain;
    }

    public StationJson setDomain(String str) {
        this.domain = str;
        return this;
    }

    public int getFreeBikeRacks() {
        return this.freeBikeRacks;
    }

    public StationJson setFreeBikeRacks(int i) {
        this.freeBikeRacks = i;
        return this;
    }

    public int getBrokenRacks() {
        return this.brokenRacks;
    }

    public StationJson setBrokenRacks(int i) {
        this.brokenRacks = i;
        return this;
    }

    public boolean isMaintenance() {
        return this.maintenance;
    }

    public StationJson setMaintenance(boolean z) {
        this.maintenance = z;
        return this;
    }

    public boolean isBike() {
        return this.bike;
    }

    public StationJson setBike(boolean z) {
        this.bike = z;
        return this;
    }

    public List<String> getBikeNumbers() {
        return this.bikeNumbers;
    }

    public StationJson setBikeNumbers(List<String> list) {
        this.bikeNumbers = list;
        return this;
    }

    public List<BikeJson> getBikes() {
        return this.bikes;
    }

    public StationJson setBikes(List<BikeJson> list) {
        this.bikes = list;
        return this;
    }

    public HashMap<String, Integer> getBikeTypes() {
        return this.bikeTypes;
    }

    public StationJson setBikeTypes(HashMap<String, Integer> hashMap) {
        this.bikeTypes = hashMap;
        return this;
    }

    public int getBookedBikesCount() {
        return this.bookedBikesCount;
    }

    public StationJson setBookedBikesCount(int i) {
        this.bookedBikesCount = i;
        return this;
    }

    public int getAvailableBikesCount() {
        return this.availableBikesCount;
    }

    public StationJson setAvailableBikesCount(int i) {
        this.availableBikesCount = i;
        return this;
    }

    public int getAvailableBikesToBookCount() {
        return this.availableBikesToBookCount;
    }

    public StationJson setAvailableBikesToBookCount(int i) {
        this.availableBikesToBookCount = i;
        return this;
    }

    @Nullable
    public Long getTimeSeconds() {
        return this.timeSeconds;
    }

    public StationJson setTimeSeconds(@Nullable Long l) {
        this.timeSeconds = l;
        return this;
    }

    public LatLng getPosition() {
        if (this.position == null) {
            this.position = new LatLng(this.lat, this.lng);
        }
        return this.position;
    }

    public String getTitle() {
        return this.name;
    }

    public String getSnippet() {
        return this.stationNumber;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StationJson)) {
            return false;
        }
        StationJson stationJson = (StationJson) obj;
        if (getUid() != stationJson.getUid() || Double.compare(stationJson.getLat(), getLat()) != 0) {
            return false;
        }
        if (Double.compare(stationJson.getLng(), getLng()) != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int uid = getUid();
        long doubleToLongBits = Double.doubleToLongBits(getLat());
        uid = (uid * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        doubleToLongBits = Double.doubleToLongBits(getLng());
        return (uid * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }
}
