package okhttp3.internal.http;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okio.GzipSource;
import okio.Okio;
import okio.Source;

public final class BridgeInterceptor implements Interceptor {
    private final CookieJar cookieJar;

    public BridgeInterceptor(CookieJar cookieJar) {
        this.cookieJar = cookieJar;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Builder newBuilder = request.newBuilder();
        RequestBody body = request.body();
        if (body != null) {
            MediaType contentType = body.contentType();
            if (contentType != null) {
                newBuilder.header(HttpRequest.HEADER_CONTENT_TYPE, contentType.toString());
            }
            long contentLength = body.contentLength();
            if (contentLength != -1) {
                newBuilder.header(HttpRequest.HEADER_CONTENT_LENGTH, Long.toString(contentLength));
                newBuilder.removeHeader("Transfer-Encoding");
            } else {
                newBuilder.header("Transfer-Encoding", "chunked");
                newBuilder.removeHeader(HttpRequest.HEADER_CONTENT_LENGTH);
            }
        }
        boolean z = false;
        if (request.header("Host") == null) {
            newBuilder.header("Host", Util.hostHeader(request.url(), false));
        }
        if (request.header("Connection") == null) {
            newBuilder.header("Connection", "Keep-Alive");
        }
        if (request.header(HttpRequest.HEADER_ACCEPT_ENCODING) == null && request.header("Range") == null) {
            z = true;
            newBuilder.header(HttpRequest.HEADER_ACCEPT_ENCODING, HttpRequest.ENCODING_GZIP);
        }
        List loadForRequest = this.cookieJar.loadForRequest(request.url());
        if (!loadForRequest.isEmpty()) {
            newBuilder.header("Cookie", cookieHeader(loadForRequest));
        }
        if (request.header("User-Agent") == null) {
            newBuilder.header("User-Agent", Version.userAgent());
        }
        chain = chain.proceed(newBuilder.build());
        HttpHeaders.receiveHeaders(this.cookieJar, request.url(), chain.headers());
        Response.Builder request2 = chain.newBuilder().request(request);
        if (z && HttpRequest.ENCODING_GZIP.equalsIgnoreCase(chain.header(HttpRequest.HEADER_CONTENT_ENCODING)) && HttpHeaders.hasBody(chain)) {
            Source gzipSource = new GzipSource(chain.body().source());
            request2.headers(chain.headers().newBuilder().removeAll(HttpRequest.HEADER_CONTENT_ENCODING).removeAll(HttpRequest.HEADER_CONTENT_LENGTH).build());
            request2.body(new RealResponseBody(chain.header(HttpRequest.HEADER_CONTENT_TYPE), -1, Okio.buffer(gzipSource)));
        }
        return request2.build();
    }

    private String cookieHeader(List<Cookie> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append("; ");
            }
            Cookie cookie = (Cookie) list.get(i);
            stringBuilder.append(cookie.name());
            stringBuilder.append('=');
            stringBuilder.append(cookie.value());
        }
        return stringBuilder.toString();
    }
}
