package com.raizlabs.android.dbflow.converter;

import java.util.Date;

public class DateConverter extends TypeConverter<Long, Date> {
    public Long getDBValue(Date date) {
        return date == null ? null : Long.valueOf(date.getTime());
    }

    public Date getModelValue(Long l) {
        return l == null ? null : new Date(l.longValue());
    }
}
