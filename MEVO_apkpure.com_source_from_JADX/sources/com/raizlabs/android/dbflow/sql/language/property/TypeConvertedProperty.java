package com.raizlabs.android.dbflow.sql.language.property;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.Operator;

public class TypeConvertedProperty<T, V> extends Property<V> {
    private boolean convertToDB;
    private TypeConvertedProperty<V, T> databaseProperty;
    private final TypeConverterGetter getter;

    public interface TypeConverterGetter {
        TypeConverter getTypeConverter(Class<?> cls);
    }

    /* renamed from: com.raizlabs.android.dbflow.sql.language.property.TypeConvertedProperty$1 */
    class C08721 implements TypeConverterGetter {
        C08721() {
        }

        public TypeConverter getTypeConverter(Class<?> cls) {
            return TypeConvertedProperty.this.getter.getTypeConverter(cls);
        }
    }

    public TypeConvertedProperty(Class<?> cls, NameAlias nameAlias, boolean z, TypeConverterGetter typeConverterGetter) {
        super((Class) cls, nameAlias);
        this.convertToDB = z;
        this.getter = typeConverterGetter;
    }

    public TypeConvertedProperty(Class<?> cls, String str, boolean z, TypeConverterGetter typeConverterGetter) {
        super((Class) cls, str);
        this.convertToDB = z;
        this.getter = typeConverterGetter;
    }

    @NonNull
    protected Operator<V> getCondition() {
        return Operator.op(getNameAlias(), this.getter.getTypeConverter(this.table), this.convertToDB);
    }

    @NonNull
    public Property<T> invertProperty() {
        if (this.databaseProperty == null) {
            this.databaseProperty = new TypeConvertedProperty(this.table, this.nameAlias, this.convertToDB ^ 1, new C08721());
        }
        return this.databaseProperty;
    }
}
