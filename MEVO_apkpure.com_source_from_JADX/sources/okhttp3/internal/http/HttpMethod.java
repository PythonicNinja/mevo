package okhttp3.internal.http;

import io.fabric.sdk.android.services.network.HttpRequest;

public final class HttpMethod {
    public static boolean invalidatesCache(String str) {
        if (!(str.equals(HttpRequest.METHOD_POST) || str.equals("PATCH") || str.equals(HttpRequest.METHOD_PUT) || str.equals("DELETE"))) {
            if (str.equals("MOVE") == null) {
                return null;
            }
        }
        return true;
    }

    public static boolean requiresRequestBody(String str) {
        if (!(str.equals(HttpRequest.METHOD_POST) || str.equals(HttpRequest.METHOD_PUT) || str.equals("PATCH") || str.equals("PROPPATCH"))) {
            if (str.equals("REPORT") == null) {
                return null;
            }
        }
        return true;
    }

    public static boolean permitsRequestBody(String str) {
        return (str.equals(HttpRequest.METHOD_GET) || str.equals(HttpRequest.METHOD_HEAD) != null) ? null : true;
    }

    public static boolean redirectsWithBody(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean redirectsToGet(String str) {
        return str.equals("PROPFIND") ^ 1;
    }

    private HttpMethod() {
    }
}
