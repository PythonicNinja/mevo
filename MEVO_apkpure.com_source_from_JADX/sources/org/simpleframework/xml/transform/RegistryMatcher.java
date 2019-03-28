package org.simpleframework.xml.transform;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

public class RegistryMatcher implements Matcher {
    private final Cache<Transform> transforms = new ConcurrentCache();
    private final Cache<Class> types = new ConcurrentCache();

    public void bind(Class cls, Class cls2) {
        this.types.cache(cls, cls2);
    }

    public void bind(Class cls, Transform transform) {
        this.transforms.cache(cls, transform);
    }

    public Transform match(Class cls) throws Exception {
        Transform transform = (Transform) this.transforms.fetch(cls);
        return transform == null ? create(cls) : transform;
    }

    private Transform create(Class cls) throws Exception {
        Class cls2 = (Class) this.types.fetch(cls);
        return cls2 != null ? create(cls, cls2) : null;
    }

    private Transform create(Class cls, Class cls2) throws Exception {
        Transform transform = (Transform) cls2.newInstance();
        if (transform != null) {
            this.transforms.cache(cls, transform);
        }
        return transform;
    }
}
