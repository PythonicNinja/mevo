package com.mevo.app.data.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponseReportResult {
    @SerializedName("data")
    public ResponseReportData responseReportData;

    public static class ResponseCode {
        public static final int REPORT_SUCCESS_CODE = 200;
    }
}
