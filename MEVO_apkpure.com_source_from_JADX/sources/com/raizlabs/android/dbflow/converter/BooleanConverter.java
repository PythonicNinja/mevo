package com.raizlabs.android.dbflow.converter;

public class BooleanConverter extends TypeConverter<Integer, Boolean> {
    public Integer getDBValue(Boolean bool) {
        return bool == null ? null : Integer.valueOf(bool.booleanValue());
    }

    public Boolean getModelValue(Integer num) {
        if (num == null) {
            return null;
        }
        boolean z = true;
        if (num.intValue() != 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
