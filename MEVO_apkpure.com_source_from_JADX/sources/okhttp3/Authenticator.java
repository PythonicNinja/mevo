package okhttp3;

import java.io.IOException;
import javax.annotation.Nullable;

public interface Authenticator {
    public static final Authenticator NONE = new C08921();

    /* renamed from: okhttp3.Authenticator$1 */
    class C08921 implements Authenticator {
        public Request authenticate(Route route, Response response) {
            return null;
        }

        C08921() {
        }
    }

    @Nullable
    Request authenticate(Route route, Response response) throws IOException;
}
