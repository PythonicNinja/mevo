package com.mevo.app.data.network.interceptors;

import com.mevo.app.constants.Servers;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;

public class NextbikeUserAgentInterceptor implements Interceptor {
    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final String TAG = "NextbikeUserAgentInterceptor";
    private static final String USER_AGENT = "NBPL/Agent app";

    public Response intercept(Chain chain) throws IOException {
        if (chain.request().url().url().toString().contains(Servers.API_NEXTBIKE_MAP_PROD)) {
            return chain.proceed(chain.request().newBuilder().addHeader("User-Agent", USER_AGENT).build());
        }
        return chain.proceed(chain.request());
    }
}
