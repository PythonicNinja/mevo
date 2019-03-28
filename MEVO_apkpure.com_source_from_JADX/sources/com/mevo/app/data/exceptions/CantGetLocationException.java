package com.mevo.app.data.exceptions;

public class CantGetLocationException extends Exception {
    public String getMessage() {
        return "Can't get device location";
    }
}
