package com.mevo.app.data.model.response;

import com.google.gson.annotations.SerializedName;
import com.mevo.app.data.model.StationJson;

public class ResponseStationDetails {
    @SerializedName("place")
    private StationJson place;
    private String server_time;
    private String version;

    public String getServer_time() {
        return this.server_time;
    }

    public StationJson getPlace() {
        return this.place;
    }

    public String getVersion() {
        return this.version;
    }
}
