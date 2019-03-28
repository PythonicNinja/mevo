package com.mevo.app.data.model;

public interface Bike {
    public static final String STATUS_API_KEY_FORBIDDEN = "apikey_forbidden";
    public static final String STATUS_LIMIT = "limit";
    public static final String STATUS_LOCKED = "locked";
    public static final String STATUS_LOW_CREDITS = "low_credits";
    public static final String STATUS_NOT_AVAILABLE_OVER_NIGHT = "not_available_over_night";
    public static final String STATUS_NO_ROAMING = "no_roaming";
    public static final String STATUS_OK = "ok";
    public static final String STATUS_OK_BUT_FOREIGN = "ok_but_foreign";
    public static final String STATUS_OK_BUT_FOREIGN_TERMS = "ok_but_foreign_terms";
    public static final String STATUS_OK_BUT_UNKNOWN_NUMBER = "ok_but_unknown_number";
    public static final String STATUS_RECENTLY_RETURNED = "recently_returned";
    public static final String STATUS_RETURN = "return";
    public static final String STATUS_RETURN_BUT_UNKNOWN = "return_but_unknown_number";

    String getStatus();

    boolean isActive();
}
