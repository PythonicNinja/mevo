package retrofit2.converter.simplexml;

import java.io.IOException;
import okhttp3.ResponseBody;
import org.simpleframework.xml.Serializer;
import retrofit2.Converter;

final class SimpleXmlResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Class<T> cls;
    private final Serializer serializer;
    private final boolean strict;

    SimpleXmlResponseBodyConverter(Class<T> cls, Serializer serializer, boolean z) {
        this.cls = cls;
        this.serializer = serializer;
        this.strict = z;
    }

    public T convert(ResponseBody responseBody) throws IOException {
        try {
            T read = this.serializer.read(this.cls, responseBody.charStream(), this.strict);
            if (read == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Could not deserialize body as ");
                stringBuilder.append(this.cls);
                throw new IllegalStateException(stringBuilder.toString());
            }
            responseBody.close();
            return read;
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        } catch (Throwable th) {
            responseBody.close();
        }
    }
}
