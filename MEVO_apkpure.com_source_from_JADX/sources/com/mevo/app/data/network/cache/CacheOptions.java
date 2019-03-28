package com.mevo.app.data.network.cache;

public class CacheOptions {
    public static final String CACHE_DISABLED = "Cache-Control: no-cache";
    public static final String REPLACE = "replace";
    public static final String REPLACE_CACHE = "Cache-Control: replace";
    static final String cache = "Cache-Control: ";
    public static final long cacheSize = 25;
    static final String max_age = "max-age";
    static final String max_stale = "max-stale";
    static final String no_cache = "no-cache";
    static final String no_store = "no-store";
    static final String revalidate = "must-revalidate";
}
