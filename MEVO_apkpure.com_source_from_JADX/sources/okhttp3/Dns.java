package okhttp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public interface Dns {
    public static final Dns SYSTEM = new C08951();

    /* renamed from: okhttp3.Dns$1 */
    class C08951 implements Dns {
        C08951() {
        }

        public List<InetAddress> lookup(String str) throws UnknownHostException {
            if (str == null) {
                throw new UnknownHostException("hostname == null");
            }
            try {
                return Arrays.asList(InetAddress.getAllByName(str));
            } catch (Throwable e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Broken system behaviour for dns lookup of ");
                stringBuilder.append(str);
                UnknownHostException unknownHostException = new UnknownHostException(stringBuilder.toString());
                unknownHostException.initCause(e);
                throw unknownHostException;
            }
        }
    }

    List<InetAddress> lookup(String str) throws UnknownHostException;
}
