package org.simpleframework.xml.convert;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class RegistryBinder {
    private final Cache<Class> cache = new ConcurrentCache();
    private final ConverterFactory factory = new ConverterFactory();

    public Converter lookup(Class cls) throws Exception {
        cls = (Class) this.cache.fetch(cls);
        return cls != null ? create(cls) : null;
    }

    private Converter create(Class cls) throws Exception {
        return this.factory.getInstance(cls);
    }

    public void bind(Class cls, Class cls2) throws Exception {
        this.cache.cache(cls, cls2);
    }
}
