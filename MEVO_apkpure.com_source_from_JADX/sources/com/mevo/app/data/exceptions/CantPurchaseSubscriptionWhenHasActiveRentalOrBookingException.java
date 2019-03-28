package com.mevo.app.data.exceptions;

public class CantPurchaseSubscriptionWhenHasActiveRentalOrBookingException extends Exception {
    public String getMessage() {
        return "Can't purchase subscription when has active rental or booking";
    }
}
