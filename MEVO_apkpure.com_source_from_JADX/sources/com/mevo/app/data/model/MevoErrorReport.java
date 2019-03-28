package com.mevo.app.data.model;

import com.google.gson.annotations.SerializedName;

public class MevoErrorReport {
    @SerializedName("apikey")
    private String apikey;
    @SerializedName("date")
    private String date;
    @SerializedName("error_id")
    private int errorId;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;
    @SerializedName("loginkey")
    private String loginkey;
    @SerializedName("maincategory")
    private String mainCategory;
    @SerializedName("message")
    private String message;
    @SerializedName("mobile_number")
    private String mobileNumber;
    @SerializedName("number")
    private Integer number;
    @SerializedName("push_callback")
    private String pushCallback;
    @SerializedName("")
    private String reports;

    public MevoErrorReport(String str, String str2, String str3, String str4, String str5, Integer num, String str6, String str7, String str8, String str9, int i, String str10) {
        this.apikey = str;
        this.loginkey = str2;
        this.mobileNumber = str3;
        this.mainCategory = str4;
        this.reports = str5;
        this.number = num;
        this.message = str6;
        this.date = str7;
        this.lat = str8;
        this.lng = str9;
        this.errorId = i;
        this.pushCallback = str10;
    }

    public String getApikey() {
        return this.apikey;
    }

    public MevoErrorReport setApikey(String str) {
        this.apikey = str;
        return this;
    }

    public String getLoginkey() {
        return this.loginkey;
    }

    public MevoErrorReport setLoginkey(String str) {
        this.loginkey = str;
        return this;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public MevoErrorReport setMobileNumber(String str) {
        this.mobileNumber = str;
        return this;
    }

    public String getMainCategory() {
        return this.mainCategory;
    }

    public MevoErrorReport setMainCategory(String str) {
        this.mainCategory = str;
        return this;
    }

    public String getReports() {
        return this.reports;
    }

    public MevoErrorReport setReports(String str) {
        this.reports = str;
        return this;
    }

    public Integer getNumber() {
        return this.number;
    }

    public MevoErrorReport setNumber(Integer num) {
        this.number = num;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public MevoErrorReport setMessage(String str) {
        this.message = str;
        return this;
    }

    public String getDate() {
        return this.date;
    }

    public MevoErrorReport setDate(String str) {
        this.date = str;
        return this;
    }

    public String getLat() {
        return this.lat;
    }

    public MevoErrorReport setLat(String str) {
        this.lat = str;
        return this;
    }

    public String getLng() {
        return this.lng;
    }

    public MevoErrorReport setLng(String str) {
        this.lng = str;
        return this;
    }

    public int getErrorId() {
        return this.errorId;
    }

    public MevoErrorReport setErrorId(int i) {
        this.errorId = i;
        return this;
    }

    public String getPushCallback() {
        return this.pushCallback;
    }

    public MevoErrorReport setPushCallback(String str) {
        this.pushCallback = str;
        return this;
    }
}
