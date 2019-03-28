package com.mevo.app.data.model.api_extended.response;

import com.google.gson.annotations.SerializedName;

public class ResponseTrackUser {
    @SerializedName("msg")
    private String message;
    @SerializedName("status")
    private int status;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
