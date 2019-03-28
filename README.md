# mevo
mevo app decompiled


# interesting api routes
https://mevo-api.nextbike.net/maps/nextbike-live.xml


# other parts of the code with constants:

```
package com.mevo.app.constants;

public class Servers {
    public static final String API_EXTENDED_PUSH_NODE_URL = "v1/send-push";
    public static final String API_EXTENDED_URL_PROD = "http://apiapp.veturilo.net.pl/";
    public static final String API_NEXTBIKE_MAP_PROD = "https://mevo-maps.nextbike.net/";
    public static final String API_NEXTBIKE_MAP_STG = "https://px-15.nextbike.net/";
    public static final String API_NEXTBIKE_PROD = "https://mevo-api.nextbike.net";
    public static final String API_NEXTBIKE_STG = "https://px-15.nextbike.net/";
    public static final String EXTENDED_API_SYSTEM_ID = "mevo";
    public static final String MEVO_HEATMAP_KEY = "96dfb8fb4fb9";
    public static final String MEVO_REPORTS_KEY = "PAu8qBmuT9iH5pWs=";
    public static final String NEXTBIKE_APIKEY_PROD = "2QwXmCM6h7p6DMeE";
    public static final String NEXTBIKE_APIKEY_STG = "2QwXmCM6h7p6DMeE";
    public static final String POLLS_APIKEY = "AQKEbjwK5syQW6Ff";
}

```


```
public static class CONFIG_DEBUG extends AppConfig {
        public CONFIG_DEBUG() {
            this.isRelease = true;
            this.apiNextbikeUrl = "https://px-15.nextbike.net/";
            this.apikeyNextbike = "2QwXmCM6h7p6DMeE";
            this.apiNextbikeMapUrl = "https://px-15.nextbike.net/";
            this.domain = Domains.DOMAIN_MEVO;
            this.bikeDomains = Collections.singletonList(Domains.DOMAIN_MEVO);
            this.apiExtendedUrl = Servers.API_EXTENDED_URL_PROD;
            this.fabricEnabled = false;
            this.loggerEnabled = true;
            this.serverSwitchAvailable = false;
            this.returnMaxDistanceMeters = 700000.0d;
            this.rentMaxDistanceMeters = 700000.0d;
            this.cacheEnabled = true;
        }
    }

    public static class CONFIG_RELEASE_PROD extends AppConfig {
        public CONFIG_RELEASE_PROD() {
            this.isRelease = true;
            this.apiNextbikeUrl = Servers.API_NEXTBIKE_PROD;
            this.apikeyNextbike = "2QwXmCM6h7p6DMeE";
            this.apiNextbikeMapUrl = Servers.API_NEXTBIKE_MAP_PROD;
            this.domain = Domains.DOMAIN_MEVO;
            this.bikeDomains = Collections.singletonList(Domains.DOMAIN_MEVO);
            this.apiExtendedUrl = Servers.API_EXTENDED_URL_PROD;
            this.fabricEnabled = true;
            this.loggerEnabled = false;
            this.serverSwitchAvailable = false;
            this.returnMaxDistanceMeters = 30.0d;
            this.rentMaxDistanceMeters = 75.0d;
            this.cacheEnabled = true;
        }
    }

    public static class CONFIG_RELEASE_STG extends CONFIG_RELEASE_PROD {
        public CONFIG_RELEASE_STG() {
            this.apiNextbikeUrl = "https://px-15.nextbike.net/";
            this.apikeyNextbike = "2QwXmCM6h7p6DMeE";
            this.apiNextbikeMapUrl = "https://px-15.nextbike.net/";
            this.domain = Domains.DOMAIN_MEVO;
            this.bikeDomains = Collections.singletonList(Domains.DOMAIN_MEVO);
        }
    }
```
