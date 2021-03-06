package okhttp3.internal.http;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http2.ConnectionShutdownException;

public final class RetryAndFollowUpInterceptor implements Interceptor {
    private static final int MAX_FOLLOW_UPS = 20;
    private Object callStackTrace;
    private volatile boolean canceled;
    private final OkHttpClient client;
    private final boolean forWebSocket;
    private volatile StreamAllocation streamAllocation;

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient, boolean z) {
        this.client = okHttpClient;
        this.forWebSocket = z;
    }

    public void cancel() {
        this.canceled = true;
        StreamAllocation streamAllocation = this.streamAllocation;
        if (streamAllocation != null) {
            streamAllocation.cancel();
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void setCallStackTrace(Object obj) {
        this.callStackTrace = obj;
    }

    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Call call = realInterceptorChain.call();
        EventListener eventListener = realInterceptorChain.eventListener();
        StreamAllocation streamAllocation = new StreamAllocation(this.client.connectionPool(), createAddress(request.url()), call, eventListener, this.callStackTrace);
        this.streamAllocation = streamAllocation;
        Response response = null;
        int i = 0;
        while (!this.canceled) {
            try {
                Response proceed = realInterceptorChain.proceed(request, r9, null, null);
                Response build = response != null ? proceed.newBuilder().priorResponse(response.newBuilder().body(null).build()).build() : proceed;
                Request followUpRequest = followUpRequest(build, r9.route());
                if (followUpRequest == null) {
                    if (this.forWebSocket == null) {
                        r9.release();
                    }
                    return build;
                }
                Util.closeQuietly(build.body());
                int i2 = i + 1;
                if (i2 > 20) {
                    r9.release();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Too many follow-up requests: ");
                    stringBuilder.append(i2);
                    throw new ProtocolException(stringBuilder.toString());
                } else if (followUpRequest.body() instanceof UnrepeatableRequestBody) {
                    r9.release();
                    throw new HttpRetryException("Cannot retry streamed HTTP body", build.code());
                } else {
                    if (!sameConnection(build, followUpRequest.url())) {
                        r9.release();
                        streamAllocation = new StreamAllocation(this.client.connectionPool(), createAddress(followUpRequest.url()), call, eventListener, this.callStackTrace);
                        this.streamAllocation = streamAllocation;
                    } else if (r9.codec() != null) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Closing the body of ");
                        stringBuilder2.append(build);
                        stringBuilder2.append(" didn't close its backing stream. Bad interceptor?");
                        throw new IllegalStateException(stringBuilder2.toString());
                    }
                    response = build;
                    request = followUpRequest;
                    i = i2;
                }
            } catch (RouteException e) {
                if (!recover(e.getLastConnectException(), r9, false, request)) {
                    throw e.getLastConnectException();
                }
            } catch (IOException e2) {
                if (!recover(e2, r9, !(e2 instanceof ConnectionShutdownException), request)) {
                    throw e2;
                }
            } catch (Throwable th) {
                r9.streamFailed(null);
                r9.release();
            }
        }
        r9.release();
        throw new IOException("Canceled");
    }

    private Address createAddress(HttpUrl httpUrl) {
        HostnameVerifier hostnameVerifier;
        SSLSocketFactory sSLSocketFactory;
        CertificatePinner certificatePinner;
        RetryAndFollowUpInterceptor retryAndFollowUpInterceptor = this;
        if (httpUrl.isHttps()) {
            SSLSocketFactory sslSocketFactory = retryAndFollowUpInterceptor.client.sslSocketFactory();
            hostnameVerifier = retryAndFollowUpInterceptor.client.hostnameVerifier();
            sSLSocketFactory = sslSocketFactory;
            certificatePinner = retryAndFollowUpInterceptor.client.certificatePinner();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = sSLSocketFactory;
            certificatePinner = hostnameVerifier;
        }
        return new Address(httpUrl.host(), httpUrl.port(), retryAndFollowUpInterceptor.client.dns(), retryAndFollowUpInterceptor.client.socketFactory(), sSLSocketFactory, hostnameVerifier, certificatePinner, retryAndFollowUpInterceptor.client.proxyAuthenticator(), retryAndFollowUpInterceptor.client.proxy(), retryAndFollowUpInterceptor.client.protocols(), retryAndFollowUpInterceptor.client.connectionSpecs(), retryAndFollowUpInterceptor.client.proxySelector());
    }

    private boolean recover(IOException iOException, StreamAllocation streamAllocation, boolean z, Request request) {
        streamAllocation.streamFailed(iOException);
        if (!this.client.retryOnConnectionFailure()) {
            return false;
        }
        if ((z && (request.body() instanceof UnrepeatableRequestBody) != null) || isRecoverable(iOException, z) == null || streamAllocation.hasMoreRoutes() == null) {
            return false;
        }
        return true;
    }

    private boolean isRecoverable(IOException iOException, boolean z) {
        boolean z2 = false;
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if (!((iOException instanceof SocketTimeoutException) == null || z)) {
                z2 = true;
            }
            return z2;
        } else if ((!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && (iOException instanceof SSLPeerUnverifiedException) == null) {
            return true;
        } else {
            return false;
        }
    }

    private Request followUpRequest(Response response, Route route) throws IOException {
        if (response == null) {
            throw new IllegalStateException();
        }
        int code = response.code();
        String method = response.request().method();
        RequestBody requestBody = null;
        switch (code) {
            case 300:
            case 301:
            case 302:
            case 303:
                break;
            case StatusLine.HTTP_TEMP_REDIRECT /*307*/:
            case StatusLine.HTTP_PERM_REDIRECT /*308*/:
                if (method.equals(HttpRequest.METHOD_GET) == null && method.equals(HttpRequest.METHOD_HEAD) == null) {
                    return null;
                }
            case 401:
                return this.client.authenticator().authenticate(route, response);
            case 407:
                Proxy proxy;
                if (route != null) {
                    proxy = route.proxy();
                } else {
                    proxy = this.client.proxy();
                }
                if (proxy.type() == Type.HTTP) {
                    return this.client.proxyAuthenticator().authenticate(route, response);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            case 408:
                if (this.client.retryOnConnectionFailure() == null || (response.request().body() instanceof UnrepeatableRequestBody) != null) {
                    return null;
                }
                if ((response.priorResponse() == null || response.priorResponse().code() != 408) && retryAfter(response, null) <= null) {
                    return response.request();
                }
                return null;
            case 503:
                if ((response.priorResponse() == null || response.priorResponse().code() != 503) && retryAfter(response, Integer.MAX_VALUE) == null) {
                    return response.request();
                }
                return null;
            default:
                return null;
        }
        if (this.client.followRedirects() == null) {
            return null;
        }
        route = response.header(HttpRequest.HEADER_LOCATION);
        if (route == null) {
            return null;
        }
        HttpUrl resolve = response.request().url().resolve(route);
        if (resolve == null) {
            return null;
        }
        if (!resolve.scheme().equals(response.request().url().scheme()) && !this.client.followSslRedirects()) {
            return null;
        }
        Builder newBuilder = response.request().newBuilder();
        if (HttpMethod.permitsRequestBody(method)) {
            boolean redirectsWithBody = HttpMethod.redirectsWithBody(method);
            if (HttpMethod.redirectsToGet(method)) {
                newBuilder.method(HttpRequest.METHOD_GET, null);
            } else {
                if (redirectsWithBody) {
                    requestBody = response.request().body();
                }
                newBuilder.method(method, requestBody);
            }
            if (!redirectsWithBody) {
                newBuilder.removeHeader("Transfer-Encoding");
                newBuilder.removeHeader(HttpRequest.HEADER_CONTENT_LENGTH);
                newBuilder.removeHeader(HttpRequest.HEADER_CONTENT_TYPE);
            }
        }
        if (sameConnection(response, resolve) == null) {
            newBuilder.removeHeader(HttpRequest.HEADER_AUTHORIZATION);
        }
        return newBuilder.url(resolve).build();
    }

    private int retryAfter(Response response, int i) {
        response = response.header("Retry-After");
        if (response == null) {
            return i;
        }
        return response.matches("\\d+") != 0 ? Integer.valueOf(response).intValue() : Integer.MAX_VALUE;
    }

    private boolean sameConnection(Response response, HttpUrl httpUrl) {
        response = response.request().url();
        return (response.host().equals(httpUrl.host()) && response.port() == httpUrl.port() && response.scheme().equals(httpUrl.scheme()) != null) ? true : null;
    }
}
