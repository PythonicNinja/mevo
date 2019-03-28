package retrofit2.converter.simplexml;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

public final class SimpleXmlConverterFactory extends Factory {
    private final Serializer serializer;
    private final boolean strict;

    public static SimpleXmlConverterFactory create() {
        return create(new Persister());
    }

    public static SimpleXmlConverterFactory create(Serializer serializer) {
        return new SimpleXmlConverterFactory(serializer, true);
    }

    public static SimpleXmlConverterFactory createNonStrict() {
        return createNonStrict(new Persister());
    }

    public static SimpleXmlConverterFactory createNonStrict(Serializer serializer) {
        return new SimpleXmlConverterFactory(serializer, false);
    }

    private SimpleXmlConverterFactory(Serializer serializer, boolean z) {
        if (serializer == null) {
            throw new NullPointerException("serializer == null");
        }
        this.serializer = serializer;
        this.strict = z;
    }

    public boolean isStrict() {
        return this.strict;
    }

    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if ((type instanceof Class) == null) {
            return null;
        }
        return new SimpleXmlResponseBodyConverter((Class) type, this.serializer, this.strict);
    }

    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        if ((type instanceof Class) == null) {
            return null;
        }
        return new SimpleXmlRequestBodyConverter(this.serializer);
    }
}
