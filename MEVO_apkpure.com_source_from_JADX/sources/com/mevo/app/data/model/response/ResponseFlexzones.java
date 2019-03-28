package com.mevo.app.data.model.response;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "nextbike")
public class ResponseFlexzones {
    @Element(name = "geojson", required = false)
    Geojson geojson;
    @Attribute(name = "server_time", required = false)
    float serverTimeSeconds;
    @Attribute(name = "version", required = false)
    public float version;

    public static class Geojson {
        @Text
        private String content;
        @Attribute(name = "hash", required = false)
        private String hash;

        public String getHash() {
            return this.hash;
        }

        public String getContent() {
            return this.content;
        }
    }

    public Geojson getGeojson() {
        return this.geojson;
    }

    public float getVersion() {
        return this.version;
    }

    public float getServerTimeSeconds() {
        return this.serverTimeSeconds;
    }
}
