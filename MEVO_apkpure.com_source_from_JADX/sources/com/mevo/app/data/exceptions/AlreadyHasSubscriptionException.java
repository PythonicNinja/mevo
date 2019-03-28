package com.mevo.app.data.exceptions;

public class AlreadyHasSubscriptionException extends Exception {
    public String getMessage() {
        return "Already has subscription";
    }
}
