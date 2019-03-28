package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.strategy.Type;

class OverrideType implements Type {
    private final Class override;
    private final Type type;

    public OverrideType(Type type, Class cls) {
        this.override = cls;
        this.type = type;
    }

    public <T extends Annotation> T getAnnotation(Class<T> cls) {
        return this.type.getAnnotation(cls);
    }

    public Class getType() {
        return this.override;
    }

    public String toString() {
        return this.type.toString();
    }
}
