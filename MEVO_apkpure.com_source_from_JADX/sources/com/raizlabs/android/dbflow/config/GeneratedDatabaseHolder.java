package com.raizlabs.android.dbflow.config;

import com.raizlabs.android.dbflow.converter.BigDecimalConverter;
import com.raizlabs.android.dbflow.converter.BooleanConverter;
import com.raizlabs.android.dbflow.converter.CalendarConverter;
import com.raizlabs.android.dbflow.converter.CharConverter;
import com.raizlabs.android.dbflow.converter.DateConverter;
import com.raizlabs.android.dbflow.converter.SqlDateConverter;
import com.raizlabs.android.dbflow.converter.UUIDConverter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public final class GeneratedDatabaseHolder extends DatabaseHolder {
    public GeneratedDatabaseHolder() {
        this.typeConverters.put(Boolean.class, new BooleanConverter());
        this.typeConverters.put(Character.class, new CharConverter());
        this.typeConverters.put(BigDecimal.class, new BigDecimalConverter());
        this.typeConverters.put(Date.class, new SqlDateConverter());
        this.typeConverters.put(Time.class, new SqlDateConverter());
        this.typeConverters.put(Timestamp.class, new SqlDateConverter());
        this.typeConverters.put(Calendar.class, new CalendarConverter());
        this.typeConverters.put(GregorianCalendar.class, new CalendarConverter());
        this.typeConverters.put(java.util.Date.class, new DateConverter());
        this.typeConverters.put(UUID.class, new UUIDConverter());
        Dbmevo_database_Database dbmevo_database_Database = new Dbmevo_database_Database(this);
    }
}
