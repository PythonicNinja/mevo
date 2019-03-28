package com.raizlabs.android.dbflow.converter;

@com.raizlabs.android.dbflow.annotation.TypeConverter
public abstract class TypeConverter<DataClass, ModelClass> {
    public abstract DataClass getDBValue(ModelClass modelClass);

    public abstract ModelClass getModelValue(DataClass dataClass);
}
