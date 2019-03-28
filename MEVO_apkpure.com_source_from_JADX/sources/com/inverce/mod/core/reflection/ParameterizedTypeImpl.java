package com.inverce.mod.core.reflection;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inverce.mod.core.verification.Preconditions;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
    private static final long serialVersionUID = 0;
    @Nullable
    private final Type ownerType;
    private final Type rawType;
    private final Type[] typeArguments;

    public ParameterizedTypeImpl(@Nullable Type type, Type type2, @NonNull Type... typeArr) {
        if (type2 instanceof Class) {
            Object obj;
            Class cls = (Class) type2;
            boolean z = true;
            if (!Modifier.isStatic(cls.getModifiers())) {
                if (cls.getEnclosingClass() != null) {
                    obj = null;
                    if (type == null) {
                        if (obj != null) {
                            z = false;
                        }
                    }
                    Preconditions.checkArgument(z);
                }
            }
            obj = 1;
            if (type == null) {
                if (obj != null) {
                    z = false;
                }
            }
            Preconditions.checkArgument(z);
        }
        if (type == null) {
            type = null;
        } else {
            type = Types.canonicalize(type);
        }
        this.ownerType = type;
        this.rawType = Types.canonicalize(type2);
        this.typeArguments = (Type[]) typeArr.clone();
        parseTypeArguments();
    }

    private void parseTypeArguments() {
        int length = this.typeArguments.length;
        for (int i = 0; i < length; i++) {
            Preconditions.checkNotNull(this.typeArguments[i]);
            Types.checkNotPrimitive(this.typeArguments[i]);
            this.typeArguments[i] = Types.canonicalize(this.typeArguments[i]);
        }
    }

    public Type[] getActualTypeArguments() {
        return (Type[]) this.typeArguments.clone();
    }

    public Type getRawType() {
        return this.rawType;
    }

    @Nullable
    public Type getOwnerType() {
        return this.ownerType;
    }

    public boolean equals(Object obj) {
        return (!(obj instanceof ParameterizedType) || Types.equals(this, (ParameterizedType) obj) == null) ? null : true;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ Types.hashCodeOrZero(this.ownerType);
    }

    public String toString() {
        int length = this.typeArguments.length;
        if (length == 0) {
            return Types.typeToString(this.rawType);
        }
        StringBuilder stringBuilder = new StringBuilder((length + 1) * 30);
        appendTypeArguments(stringBuilder, length);
        stringBuilder.append(Operation.GREATER_THAN);
        return stringBuilder.toString();
    }

    private void appendTypeArguments(@NonNull StringBuilder stringBuilder, int i) {
        stringBuilder.append(Types.typeToString(this.rawType));
        stringBuilder.append(Operation.LESS_THAN);
        stringBuilder.append(Types.typeToString(this.typeArguments[0]));
        for (int i2 = 1; i2 < i; i2++) {
            stringBuilder.append(", ");
            stringBuilder.append(Types.typeToString(this.typeArguments[i2]));
        }
    }
}
