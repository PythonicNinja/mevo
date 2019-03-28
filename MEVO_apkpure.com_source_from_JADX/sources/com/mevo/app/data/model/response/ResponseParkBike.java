package com.mevo.app.data.model.response;

import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseParkBike {
    private String bikeNumber;
    private boolean bikeParked;

    public String getBikeNumber() {
        return this.bikeNumber;
    }

    public ResponseParkBike setBikeNumber(String str) {
        this.bikeNumber = str;
        return this;
    }

    public boolean isBikeParked() {
        return this.bikeParked;
    }

    public ResponseParkBike setBikeParked(boolean z) {
        this.bikeParked = z;
        return this;
    }
}
