package com.mevo.app.data.model.response;

import com.google.gson.annotations.SerializedName;
import com.mevo.app.data.model.BikeJson;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseBikeStateJson {
    @SerializedName("bike")
    @Element(name = "bike")
    public BikeJson bike;
    @SerializedName("server_time")
    @Element(name = "server_time")
    public long time;
}
