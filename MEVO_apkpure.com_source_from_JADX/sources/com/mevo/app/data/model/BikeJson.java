package com.mevo.app.data.model;

import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;

public class BikeJson implements Bike {
    @SerializedName("city_id")
    private String cityId;
    @SerializedName("bike_domain")
    private String domain;
    @SerializedName("active")
    private boolean isActive;
    @SerializedName("number")
    private String number;
    @Nullable
    @SerializedName("pedelec_battery")
    private Integer pedelecBatteryPercent;
    @SerializedName("place_id")
    private int placeId;
    @SerializedName("place_name")
    private String placeName;
    @SerializedName("rfid")
    private String rfid;
    @SerializedName("state")
    private String status;
    @SerializedName("bike_type")
    private String type;

    public boolean isActive() {
        return this.isActive;
    }

    public BikeJson setActive(boolean z) {
        this.isActive = z;
        return this;
    }

    public String getNumber() {
        return this.number;
    }

    public BikeJson setNumber(String str) {
        this.number = str;
        return this;
    }

    public String getRfid() {
        return this.rfid;
    }

    public BikeJson setRfid(String str) {
        this.rfid = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public BikeJson setStatus(String str) {
        this.status = str;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public BikeJson setType(String str) {
        this.type = str;
        return this;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public BikeJson setPlaceName(String str) {
        this.placeName = str;
        return this;
    }

    public int getPlaceId() {
        return this.placeId;
    }

    public BikeJson setPlaceId(int i) {
        this.placeId = i;
        return this;
    }

    public String getCityId() {
        return this.cityId;
    }

    public BikeJson setCityId(String str) {
        this.cityId = str;
        return this;
    }

    public String getDomain() {
        return this.domain;
    }

    public BikeJson setDomain(String str) {
        this.domain = str;
        return this;
    }

    @Nullable
    public Integer getPedelecBatteryPercent() {
        return this.pedelecBatteryPercent;
    }

    public BikeJson setPedelecBatteryPercent(Integer num) {
        this.pedelecBatteryPercent = num;
        return this;
    }
}
