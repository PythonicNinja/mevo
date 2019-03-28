package com.raizlabs.android.dbflow.converter;

import java.math.BigDecimal;

public class BigDecimalConverter extends TypeConverter<String, BigDecimal> {
    public String getDBValue(BigDecimal bigDecimal) {
        return bigDecimal == null ? null : bigDecimal.toString();
    }

    public BigDecimal getModelValue(String str) {
        return str == null ? null : new BigDecimal(str);
    }
}
