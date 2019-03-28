package com.mevo.app.data.model;

import android.support.annotation.StringRes;

public class TariffExtraInfo {
    private int additionalCostAfter12hRentCents;
    private int centsPerMinuteAfterDailyLimit;
    private String code;
    private String currency;
    private int daysValid;
    private int maxBikesRentedSimultaneously;
    private int minutesPerDay;
    private int nameRes;
    private double price;
    @StringRes
    private int uid;

    public TariffExtraInfo(int i, int i2, double d, String str, String str2, int i3, int i4, int i5, int i6, int i7) {
        this.uid = i;
        this.nameRes = i2;
        this.price = d;
        this.currency = str;
        this.code = str2;
        this.daysValid = i3;
        this.minutesPerDay = i4;
        this.centsPerMinuteAfterDailyLimit = i5;
        this.maxBikesRentedSimultaneously = i6;
        this.additionalCostAfter12hRentCents = i7;
    }

    public int getUid() {
        return this.uid;
    }

    public int getNameRes() {
        return this.nameRes;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getCode() {
        return this.code;
    }

    public int getDaysValid() {
        return this.daysValid;
    }

    public int getMinutesPerDay() {
        return this.minutesPerDay;
    }

    public int getCentsPerMinuteAfterDailyLimit() {
        return this.centsPerMinuteAfterDailyLimit;
    }

    public int getMaxBikesRentedSimultaneously() {
        return this.maxBikesRentedSimultaneously;
    }

    public int getAdditionalCostAfter12hRentCents() {
        return this.additionalCostAfter12hRentCents;
    }
}
