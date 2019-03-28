package com.mevo.app.data.model.response;

import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseResumeRenting {
    private String bikeNumber;
    private boolean bikeRentingResumed;

    public String getBikeNumber() {
        return this.bikeNumber;
    }

    public ResponseResumeRenting setBikeNumber(String str) {
        this.bikeNumber = str;
        return this;
    }

    public boolean isBikeRentingResumed() {
        return this.bikeRentingResumed;
    }

    public ResponseResumeRenting setBikeRentingResumed(boolean z) {
        this.bikeRentingResumed = z;
        return this;
    }
}
