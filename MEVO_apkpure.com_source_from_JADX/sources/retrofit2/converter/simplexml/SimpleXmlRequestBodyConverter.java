package retrofit2.converter.simplexml;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import org.simpleframework.xml.Serializer;
import retrofit2.Converter;

final class SimpleXmlRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final String CHARSET = "UTF-8";
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/xml; charset=UTF-8");
    private final Serializer serializer;

    SimpleXmlRequestBodyConverter(Serializer serializer) {
        this.serializer = serializer;
    }

    public RequestBody convert(T t) throws IOException {
        Buffer buffer = new Buffer();
        try {
            Writer outputStreamWriter = new OutputStreamWriter(buffer.outputStream(), "UTF-8");
            this.serializer.write((Object) t, outputStreamWriter);
            outputStreamWriter.flush();
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        } catch (T t2) {
            throw new RuntimeException(t2);
        }
    }
}
