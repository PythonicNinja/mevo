package com.inverce.mod.core.reflection;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
    private static final long serialVersionUID = 0;
    private final Type componentType;

    public GenericArrayTypeImpl(Type type) {
        this.componentType = Types.canonicalize(type);
    }

    public Type getGenericComponentType() {
        return this.componentType;
    }

    public boolean equals(Object obj) {
        return (!(obj instanceof GenericArrayType) || Types.equals(this, (GenericArrayType) obj) == null) ? null : true;
    }

    public int hashCode() {
        return this.componentType.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Types.typeToString(this.componentType));
        stringBuilder.append("[]");
        return stringBuilder.toString();
    }
}
