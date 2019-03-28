package io.fabric.sdk.android.services.network;

import io.fabric.sdk.android.DefaultLogger;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class DefaultHttpRequestFactory implements HttpRequestFactory {
    private static final String HTTPS = "https";
    private boolean attemptedSslInit;
    private final Logger logger;
    private PinningInfoProvider pinningInfo;
    private SSLSocketFactory sslSocketFactory;

    public DefaultHttpRequestFactory() {
        this(new DefaultLogger());
    }

    public DefaultHttpRequestFactory(Logger logger) {
        this.logger = logger;
    }

    public PinningInfoProvider getPinningInfoProvider() {
        return this.pinningInfo;
    }

    public void setPinningInfoProvider(PinningInfoProvider pinningInfoProvider) {
        if (this.pinningInfo != pinningInfoProvider) {
            this.pinningInfo = pinningInfoProvider;
            resetSSLSocketFactory();
        }
    }

    private synchronized void resetSSLSocketFactory() {
        this.attemptedSslInit = false;
        this.sslSocketFactory = null;
    }

    public HttpRequest buildHttpRequest(HttpMethod httpMethod, String str) {
        return buildHttpRequest(httpMethod, str, Collections.emptyMap());
    }

    public HttpRequest buildHttpRequest(HttpMethod httpMethod, String str, Map<String, String> map) {
        switch (httpMethod) {
            case GET:
                httpMethod = HttpRequest.get((CharSequence) str, (Map) map, true);
                break;
            case POST:
                httpMethod = HttpRequest.post((CharSequence) str, (Map) map, true);
                break;
            case PUT:
                httpMethod = HttpRequest.put((CharSequence) str);
                break;
            case DELETE:
                httpMethod = HttpRequest.delete((CharSequence) str);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method!");
        }
        if (!(isHttps(str) == null || this.pinningInfo == null)) {
            str = getSSLSocketFactory();
            if (str != null) {
                ((HttpsURLConnection) httpMethod.getConnection()).setSSLSocketFactory(str);
            }
        }
        return httpMethod;
    }

    private boolean isHttps(String str) {
        return (str == null || str.toLowerCase(Locale.US).startsWith(HTTPS) == null) ? null : true;
    }

    private synchronized SSLSocketFactory getSSLSocketFactory() {
        if (this.sslSocketFactory == null && !this.attemptedSslInit) {
            this.sslSocketFactory = initSSLSocketFactory();
        }
        return this.sslSocketFactory;
    }

    private synchronized SSLSocketFactory initSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory;
        this.attemptedSslInit = true;
        try {
            sSLSocketFactory = NetworkUtils.getSSLSocketFactory(this.pinningInfo);
            this.logger.mo2494d(Fabric.TAG, "Custom SSL pinning enabled");
        } catch (Throwable e) {
            this.logger.mo2497e(Fabric.TAG, "Exception while validating pinned certs", e);
            return null;
        }
        return sSLSocketFactory;
    }
}
