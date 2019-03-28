package com.mevo.app.data.model;

import android.support.annotation.Nullable;

public abstract /* synthetic */ class Bike$$CC {
    public static boolean isBikeAvailable$$STATIC$$(@Nullable Bike bike) {
        return (bike != null && bike.isActive() && (Bike.STATUS_OK.equals(bike.getStatus()) || Bike.STATUS_OK_BUT_FOREIGN.equals(bike.getStatus()) || Bike.STATUS_OK_BUT_FOREIGN_TERMS.equals(bike.getStatus()) || Bike.STATUS_OK_BUT_UNKNOWN_NUMBER.equals(bike.getStatus()) || Bike.STATUS_LOW_CREDITS.equals(bike.getStatus()) || Bike.STATUS_RECENTLY_RETURNED.equals(bike.getStatus()) || Bike.STATUS_NOT_AVAILABLE_OVER_NIGHT.equals(bike.getStatus()) || Bike.STATUS_LOCKED.equals(bike.getStatus()) || Bike.STATUS_LIMIT.equals(bike.getStatus()) || Bike.STATUS_API_KEY_FORBIDDEN.equals(bike.getStatus()) || Bike.STATUS_NO_ROAMING.equals(bike.getStatus()) != null)) ? true : null;
    }

    public static boolean canBikeBeReturned$$STATIC$$(@Nullable Bike bike) {
        return (bike == null || !bike.isActive() || (!"return".equals(bike.getStatus()) && Bike.STATUS_RETURN_BUT_UNKNOWN.equals(bike.getStatus()) == null)) ? null : true;
    }
}
