package okhttp3;

import java.util.Collections;
import java.util.List;

public interface CookieJar {
    public static final CookieJar NO_COOKIES = new C08941();

    /* renamed from: okhttp3.CookieJar$1 */
    class C08941 implements CookieJar {
        public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        }

        C08941() {
        }

        public List<Cookie> loadForRequest(HttpUrl httpUrl) {
            return Collections.emptyList();
        }
    }

    List<Cookie> loadForRequest(HttpUrl httpUrl);

    void saveFromResponse(HttpUrl httpUrl, List<Cookie> list);
}
