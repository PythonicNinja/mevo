package com.inverce.mod.core.reflection;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inverce.mod.core.verification.Preconditions;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

final class WildcardTypeImpl implements WildcardType, Serializable {
    private static final long serialVersionUID = 0;
    @Nullable
    private final Type lowerBound;
    private final Type upperBound;

    public WildcardTypeImpl(@NonNull Type[] typeArr, @NonNull Type[] typeArr2) {
        boolean z = true;
        Preconditions.checkArgument(typeArr2.length <= 1);
        Preconditions.checkArgument(typeArr.length == 1);
        if (typeArr2.length == 1) {
            Preconditions.checkNotNull(typeArr2[0]);
            Types.checkNotPrimitive(typeArr2[0]);
            if (typeArr[0] != Object.class) {
                z = false;
            }
            Preconditions.checkArgument(z);
            this.lowerBound = Types.canonicalize(typeArr2[0]);
            this.upperBound = Object.class;
            return;
        }
        Preconditions.checkNotNull(typeArr[0]);
        Types.checkNotPrimitive(typeArr[0]);
        this.lowerBound = null;
        this.upperBound = Types.canonicalize(typeArr[0]);
    }

    public Type[] getUpperBounds() {
        return new Type[]{this.upperBound};
    }

    @Nullable
    public Type[] getLowerBounds() {
        if (this.lowerBound == null) {
            return Types.EMPTY_TYPE_ARRAY;
        }
        return new Type[]{this.lowerBound};
    }

    public boolean equals(Object obj) {
        return (!(obj instanceof WildcardType) || Types.equals(this, (WildcardType) obj) == null) ? null : true;
    }

    public int hashCode() {
        return (this.lowerBound != null ? this.lowerBound.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
    }

    public String toString() {
        StringBuilder stringBuilder;
        if (this.lowerBound != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("? super ");
            stringBuilder.append(Types.typeToString(this.lowerBound));
            return stringBuilder.toString();
        } else if (this.upperBound == Object.class) {
            return Operation.EMPTY_PARAM;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("? extends ");
            stringBuilder.append(Types.typeToString(this.upperBound));
            return stringBuilder.toString();
        }
    }
}
