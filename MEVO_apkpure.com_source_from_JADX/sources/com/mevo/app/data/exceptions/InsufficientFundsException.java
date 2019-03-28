package com.mevo.app.data.exceptions;

public class InsufficientFundsException extends Exception {
    public String getMessage() {
        return "Insufficient funds";
    }
}
