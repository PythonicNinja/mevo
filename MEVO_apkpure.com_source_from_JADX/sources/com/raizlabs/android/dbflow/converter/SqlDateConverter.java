package com.raizlabs.android.dbflow.converter;

import com.raizlabs.android.dbflow.annotation.TypeConverter;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@TypeConverter(allowedSubtypes = {Time.class, Timestamp.class})
public class SqlDateConverter extends TypeConverter<Long, Date> {
    public Long getDBValue(Date date) {
        return date == null ? null : Long.valueOf(date.getTime());
    }

    public Date getModelValue(Long l) {
        return l == null ? null : new Date(l.longValue());
    }
}
