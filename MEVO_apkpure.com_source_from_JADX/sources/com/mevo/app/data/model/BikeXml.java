package com.mevo.app.data.model;

import com.mevo.app.tools.Utils;
import org.simpleframework.xml.Attribute;

public class BikeXml implements Bike {
    @Attribute(name = "city_id", required = false)
    private String cityId;
    @Attribute(name = "bike_domain", required = false)
    private String domain;
    @Attribute(name = "active", required = false)
    private int isActive;
    @Attribute(name = "number", required = false)
    private String number;
    @Attribute(name = "place_id", required = false)
    private int placeId;
    @Attribute(name = "place_name", required = false)
    private String placeName;
    @Attribute(name = "rfid", required = false)
    private String rfid;
    @Attribute(name = "state", required = false)
    private String status;
    @Attribute(name = "bike_type", required = false)
    private String type;

    public boolean isActive() {
        return Utils.intToBool(this.isActive);
    }

    public void setIsActive(boolean z) {
        this.isActive = Utils.boolToInt(z);
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public String getRfid() {
        return this.rfid;
    }

    public void setRfid(String str) {
        this.rfid = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public void setPlaceName(String str) {
        this.placeName = str;
    }

    public int getPlaceId() {
        return this.placeId;
    }

    public void setPlaceId(int i) {
        this.placeId = i;
    }

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String str) {
        this.cityId = str;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public BikeXml setIsActive(int i) {
        this.isActive = i;
        return this;
    }
}
