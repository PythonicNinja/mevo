package com.mevo.app.data.network.cache;

import com.inverce.mod.core.Log;
import com.mevo.app.data.shared_prefs.SharedPrefs;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

public class ApplyCacheConfigInterceptor implements Interceptor {
    SmartCache cache;

    public ApplyCacheConfigInterceptor(SmartCache smartCache) {
        this.cache = smartCache;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String header = request.header(HttpRequest.HEADER_CACHE_CONTROL);
        if (header == null) {
            header = this.cache.getDefaultControl();
        } else if (header.equals(CacheOptions.REPLACE)) {
            header = getNewCacheOptions(((Request) request.tag()).url());
            request = request.newBuilder().removeHeader(HttpRequest.HEADER_CACHE_CONTROL).addHeader(HttpRequest.HEADER_CACHE_CONTROL, header).build();
        }
        if (!(request.method().equals(HttpRequest.METHOD_GET) || header.contains("no-cache"))) {
            Log.m69w("ApplyCacheConfigInterceptor", "Non GET request cached: ${request.url()}");
        }
        return chain.proceed(request).newBuilder().removeHeader(HttpRequest.HEADER_CACHE_CONTROL).removeHeader(HttpRequest.HEADER_EXPIRES).removeHeader(HttpRequest.HEADER_ETAG).removeHeader("Pragma").removeHeader("Strict-Transport-Security").removeHeader("Keep-Alive").addHeader(HttpRequest.HEADER_CACHE_CONTROL, header).build();
    }

    private String getNewCacheOptions(HttpUrl httpUrl) {
        long cacheTimeListXml = SharedPrefs.getCacheTimeListXml();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("replace cache dynamically ");
        stringBuilder.append(httpUrl);
        String stringBuilder2 = stringBuilder.toString();
        Object[] objArr = new Object[1];
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("time: ");
        stringBuilder3.append(cacheTimeListXml);
        objArr[0] = stringBuilder3.toString();
        Log.m62v("cache", stringBuilder2, objArr);
        if (cacheTimeListXml == 0) {
            return "no-cache";
        }
        httpUrl = new StringBuilder();
        httpUrl.append("max-age=");
        httpUrl.append(cacheTimeListXml);
        return httpUrl.toString();
    }
}
