package okhttp3.internal.cache;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.Internal;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.http.HttpHeaders;

public final class CacheStrategy {
    @Nullable
    public final Response cacheResponse;
    @Nullable
    public final Request networkRequest;

    public static class Factory {
        private int ageSeconds = -1;
        final Response cacheResponse;
        private String etag;
        private Date expires;
        private Date lastModified;
        private String lastModifiedString;
        final long nowMillis;
        private long receivedResponseMillis;
        final Request request;
        private long sentRequestMillis;
        private Date servedDate;
        private String servedDateString;

        public Factory(long j, Request request, Response response) {
            this.nowMillis = j;
            this.request = request;
            this.cacheResponse = response;
            if (response != null) {
                this.sentRequestMillis = response.sentRequestAtMillis();
                this.receivedResponseMillis = response.receivedResponseAtMillis();
                j = response.headers();
                request = j.size();
                for (int i = 0; i < request; i++) {
                    response = j.name(i);
                    String value = j.value(i);
                    if (HttpRequest.HEADER_DATE.equalsIgnoreCase(response)) {
                        this.servedDate = HttpDate.parse(value);
                        this.servedDateString = value;
                    } else if (HttpRequest.HEADER_EXPIRES.equalsIgnoreCase(response)) {
                        this.expires = HttpDate.parse(value);
                    } else if (HttpRequest.HEADER_LAST_MODIFIED.equalsIgnoreCase(response)) {
                        this.lastModified = HttpDate.parse(value);
                        this.lastModifiedString = value;
                    } else if (HttpRequest.HEADER_ETAG.equalsIgnoreCase(response)) {
                        this.etag = value;
                    } else if ("Age".equalsIgnoreCase(response) != null) {
                        this.ageSeconds = HttpHeaders.parseSeconds(value, -1);
                    }
                }
            }
        }

        public CacheStrategy get() {
            CacheStrategy candidate = getCandidate();
            return (candidate.networkRequest == null || !this.request.cacheControl().onlyIfCached()) ? candidate : new CacheStrategy(null, null);
        }

        private CacheStrategy getCandidate() {
            if (this.cacheResponse == null) {
                return new CacheStrategy(this.request, null);
            }
            if (this.request.isHttps() && this.cacheResponse.handshake() == null) {
                return new CacheStrategy(this.request, null);
            }
            if (!CacheStrategy.isCacheable(this.cacheResponse, this.request)) {
                return new CacheStrategy(this.request, null);
            }
            CacheControl cacheControl = this.request.cacheControl();
            if (!cacheControl.noCache()) {
                if (!hasConditions(this.request)) {
                    CacheControl cacheControl2 = this.cacheResponse.cacheControl();
                    if (cacheControl2.immutable()) {
                        return new CacheStrategy(null, this.cacheResponse);
                    }
                    String str;
                    String str2;
                    long cacheResponseAge = cacheResponseAge();
                    long computeFreshnessLifetime = computeFreshnessLifetime();
                    if (cacheControl.maxAgeSeconds() != -1) {
                        computeFreshnessLifetime = Math.min(computeFreshnessLifetime, TimeUnit.SECONDS.toMillis((long) cacheControl.maxAgeSeconds()));
                    }
                    long j = 0;
                    long toMillis = cacheControl.minFreshSeconds() != -1 ? TimeUnit.SECONDS.toMillis((long) cacheControl.minFreshSeconds()) : 0;
                    if (!(cacheControl2.mustRevalidate() || cacheControl.maxStaleSeconds() == -1)) {
                        j = TimeUnit.SECONDS.toMillis((long) cacheControl.maxStaleSeconds());
                    }
                    if (!cacheControl2.noCache()) {
                        long j2 = cacheResponseAge + toMillis;
                        if (j2 < computeFreshnessLifetime + j) {
                            Builder newBuilder = this.cacheResponse.newBuilder();
                            if (j2 >= computeFreshnessLifetime) {
                                newBuilder.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
                            }
                            if (cacheResponseAge > 86400000 && isFreshnessLifetimeHeuristic()) {
                                newBuilder.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                            }
                            return new CacheStrategy(null, newBuilder.build());
                        }
                    }
                    if (this.etag != null) {
                        str = HttpRequest.HEADER_IF_NONE_MATCH;
                        str2 = this.etag;
                    } else if (this.lastModified != null) {
                        str = "If-Modified-Since";
                        str2 = this.lastModifiedString;
                    } else if (this.servedDate == null) {
                        return new CacheStrategy(this.request, null);
                    } else {
                        str = "If-Modified-Since";
                        str2 = this.servedDateString;
                    }
                    Headers.Builder newBuilder2 = this.request.headers().newBuilder();
                    Internal.instance.addLenient(newBuilder2, str, str2);
                    return new CacheStrategy(this.request.newBuilder().headers(newBuilder2.build()).build(), this.cacheResponse);
                }
            }
            return new CacheStrategy(this.request, null);
        }

        private long computeFreshnessLifetime() {
            CacheControl cacheControl = this.cacheResponse.cacheControl();
            if (cacheControl.maxAgeSeconds() != -1) {
                return TimeUnit.SECONDS.toMillis((long) cacheControl.maxAgeSeconds());
            }
            long j = 0;
            long time;
            long time2;
            if (this.expires != null) {
                if (this.servedDate != null) {
                    time = this.servedDate.getTime();
                } else {
                    time = this.receivedResponseMillis;
                }
                time2 = this.expires.getTime() - time;
                if (time2 > 0) {
                    j = time2;
                }
                return j;
            } else if (this.lastModified == null || this.cacheResponse.request().url().query() != null) {
                return 0;
            } else {
                if (this.servedDate != null) {
                    time = this.servedDate.getTime();
                } else {
                    time = this.sentRequestMillis;
                }
                time2 = time - this.lastModified.getTime();
                if (time2 > 0) {
                    j = time2 / 10;
                }
                return j;
            }
        }

        private long cacheResponseAge() {
            long j = 0;
            if (this.servedDate != null) {
                j = Math.max(0, this.receivedResponseMillis - this.servedDate.getTime());
            }
            if (this.ageSeconds != -1) {
                j = Math.max(j, TimeUnit.SECONDS.toMillis((long) this.ageSeconds));
            }
            return (j + (this.receivedResponseMillis - this.sentRequestMillis)) + (this.nowMillis - this.receivedResponseMillis);
        }

        private boolean isFreshnessLifetimeHeuristic() {
            return this.cacheResponse.cacheControl().maxAgeSeconds() == -1 && this.expires == null;
        }

        private static boolean hasConditions(Request request) {
            if (request.header("If-Modified-Since") == null) {
                if (request.header(HttpRequest.HEADER_IF_NONE_MATCH) == null) {
                    return null;
                }
            }
            return true;
        }
    }

    CacheStrategy(Request request, Response response) {
        this.networkRequest = request;
        this.cacheResponse = response;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isCacheable(okhttp3.Response r3, okhttp3.Request r4) {
        /*
        r0 = r3.code();
        r1 = 0;
        switch(r0) {
            case 200: goto L_0x0030;
            case 203: goto L_0x0030;
            case 204: goto L_0x0030;
            case 300: goto L_0x0030;
            case 301: goto L_0x0030;
            case 302: goto L_0x0009;
            case 307: goto L_0x0009;
            case 308: goto L_0x0030;
            case 404: goto L_0x0030;
            case 405: goto L_0x0030;
            case 410: goto L_0x0030;
            case 414: goto L_0x0030;
            case 501: goto L_0x0030;
            default: goto L_0x0008;
        };
    L_0x0008:
        goto L_0x0046;
    L_0x0009:
        r0 = "Expires";
        r0 = r3.header(r0);
        if (r0 != 0) goto L_0x0030;
    L_0x0011:
        r0 = r3.cacheControl();
        r0 = r0.maxAgeSeconds();
        r2 = -1;
        if (r0 != r2) goto L_0x0030;
    L_0x001c:
        r0 = r3.cacheControl();
        r0 = r0.isPublic();
        if (r0 != 0) goto L_0x0030;
    L_0x0026:
        r0 = r3.cacheControl();
        r0 = r0.isPrivate();
        if (r0 == 0) goto L_0x0046;
    L_0x0030:
        r3 = r3.cacheControl();
        r3 = r3.noStore();
        if (r3 != 0) goto L_0x0045;
    L_0x003a:
        r3 = r4.cacheControl();
        r3 = r3.noStore();
        if (r3 != 0) goto L_0x0045;
    L_0x0044:
        r1 = 1;
    L_0x0045:
        return r1;
    L_0x0046:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.CacheStrategy.isCacheable(okhttp3.Response, okhttp3.Request):boolean");
    }
}
