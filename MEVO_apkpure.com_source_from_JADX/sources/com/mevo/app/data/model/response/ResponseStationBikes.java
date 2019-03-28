package com.mevo.app.data.model.response;

import com.mevo.app.data.model.Place;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseStationBikes {
    @Element(name = "place")
    private Place place;
    private String server_time;
    private String version;

    public String getServer_time() {
        return this.server_time;
    }

    public Place getPlace() {
        return this.place;
    }

    public String getVersion() {
        return this.version;
    }
}
