package com.mevo.app;

import com.mevo.app.constants.Domains;
import com.mevo.app.constants.Servers;
import com.mevo.app.data.model.Domain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cfg {
    private static AppConfig CURRENT_CONFIG = new CONFIG_RELEASE_PROD();

    public static abstract class AppConfig {
        protected String apiExtendedUrl;
        protected String apiNextbikeMapUrl;
        protected String apiNextbikeUrl;
        protected String apikeyNextbike;
        protected List<Domain> bikeDomains;
        protected boolean cacheEnabled;
        protected Domain domain;
        protected boolean fabricEnabled;
        protected boolean isRelease;
        protected boolean loggerEnabled;
        protected double rentMaxDistanceMeters;
        protected double returnMaxDistanceMeters;
        protected boolean serverSwitchAvailable;

        public boolean isRelease() {
            return this.isRelease;
        }

        public void setApiNextbikeUrl(String str) {
            this.apiNextbikeUrl = str;
        }

        public void setApiNextbikeMapUrl(String str) {
            this.apiNextbikeMapUrl = str;
        }

        public String apiNextbikeUrl() {
            return this.apiNextbikeUrl;
        }

        public String apiNextbikeMapUrl() {
            return this.apiNextbikeMapUrl;
        }

        public String apiExtendedUrl() {
            return this.apiExtendedUrl;
        }

        public boolean fabricEnabled() {
            return this.fabricEnabled;
        }

        public boolean loggerEnabled() {
            return this.loggerEnabled;
        }

        public boolean serverSwitchAvailable() {
            return this.serverSwitchAvailable;
        }

        public double returnMaxDistanceMeters() {
            return this.returnMaxDistanceMeters;
        }

        public double rentMaxDistanceMeters() {
            return this.rentMaxDistanceMeters;
        }

        public boolean isCacheEnabled() {
            return this.cacheEnabled;
        }

        public String apikeyNextbike() {
            return this.apikeyNextbike;
        }

        public Domain domain() {
            return this.domain;
        }

        public List<Domain> bikeDomains() {
            return new ArrayList(this.bikeDomains);
        }
    }

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

    public static AppConfig get() {
        return CURRENT_CONFIG;
    }
}
