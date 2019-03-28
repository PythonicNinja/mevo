package com.mevo.app.data.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponseReportData {
    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public int status;
}
