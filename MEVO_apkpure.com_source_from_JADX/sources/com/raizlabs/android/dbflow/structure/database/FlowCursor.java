package com.raizlabs.android.dbflow.structure.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class FlowCursor extends CursorWrapper {
    private Cursor cursor;

    public static FlowCursor from(@NonNull Cursor cursor) {
        if (cursor instanceof FlowCursor) {
            return (FlowCursor) cursor;
        }
        return new FlowCursor(cursor);
    }

    private FlowCursor(@NonNull Cursor cursor) {
        super(cursor);
        this.cursor = cursor;
    }

    public Cursor getWrappedCursor() {
        return this.cursor;
    }

    public String getStringOrDefault(int i, String str) {
        return (i == -1 || this.cursor.isNull(i)) ? str : this.cursor.getString(i);
    }

    public String getStringOrDefault(String str) {
        return getStringOrDefault(this.cursor.getColumnIndex(str));
    }

    @Nullable
    public String getStringOrDefault(int i) {
        return (i == -1 || this.cursor.isNull(i)) ? 0 : this.cursor.getString(i);
    }

    public String getStringOrDefault(String str, String str2) {
        return getStringOrDefault(this.cursor.getColumnIndex(str), str2);
    }

    public int getIntOrDefault(String str) {
        return getIntOrDefault(this.cursor.getColumnIndex(str));
    }

    public int getIntOrDefault(int i) {
        return (i == -1 || this.cursor.isNull(i)) ? 0 : this.cursor.getInt(i);
    }

    public int getIntOrDefault(int i, int i2) {
        return (i == -1 || this.cursor.isNull(i)) ? i2 : this.cursor.getInt(i);
    }

    public int getIntOrDefault(String str, int i) {
        return getIntOrDefault(this.cursor.getColumnIndex(str), i);
    }

    public Integer getIntOrDefault(int i, Integer num) {
        return (i == -1 || this.cursor.isNull(i)) ? num : Integer.valueOf(this.cursor.getInt(i));
    }

    public Integer getIntOrDefault(String str, Integer num) {
        return getIntOrDefault(this.cursor.getColumnIndex(str), num);
    }

    public double getDoubleOrDefault(int i, double d) {
        return (i == -1 || this.cursor.isNull(i)) ? d : this.cursor.getDouble(i);
    }

    public double getDoubleOrDefault(String str) {
        return getDoubleOrDefault(this.cursor.getColumnIndex(str));
    }

    public double getDoubleOrDefault(int i) {
        return (i == -1 || this.cursor.isNull(i)) ? 0.0d : this.cursor.getDouble(i);
    }

    public double getDoubleOrDefault(String str, double d) {
        return getDoubleOrDefault(this.cursor.getColumnIndex(str), d);
    }

    public Double getDoubleOrDefault(int i, Double d) {
        return (i == -1 || this.cursor.isNull(i)) ? d : Double.valueOf(this.cursor.getDouble(i));
    }

    public Double getDoubleOrDefault(String str, Double d) {
        return getDoubleOrDefault(this.cursor.getColumnIndex(str), d);
    }

    public float getFloatOrDefault(int i, float f) {
        return (i == -1 || this.cursor.isNull(i)) ? f : this.cursor.getFloat(i);
    }

    public float getFloatOrDefault(String str) {
        return getFloatOrDefault(this.cursor.getColumnIndex(str));
    }

    public float getFloatOrDefault(int i) {
        return (i == -1 || this.cursor.isNull(i)) ? 0 : this.cursor.getFloat(i);
    }

    public float getFloatOrDefault(String str, float f) {
        return getFloatOrDefault(this.cursor.getColumnIndex(str), f);
    }

    public Float getFloatOrDefault(int i, Float f) {
        return (i == -1 || this.cursor.isNull(i)) ? f : Float.valueOf(this.cursor.getFloat(i));
    }

    public Float getFloatOrDefault(String str, Float f) {
        return getFloatOrDefault(this.cursor.getColumnIndex(str), f);
    }

    public long getLongOrDefault(int i, long j) {
        return (i == -1 || this.cursor.isNull(i)) ? j : this.cursor.getLong(i);
    }

    public long getLongOrDefault(String str) {
        return getLongOrDefault(this.cursor.getColumnIndex(str));
    }

    public long getLongOrDefault(int i) {
        return (i == -1 || this.cursor.isNull(i)) ? 0 : this.cursor.getLong(i);
    }

    public long getLongOrDefault(String str, long j) {
        return getLongOrDefault(this.cursor.getColumnIndex(str), j);
    }

    public Long getLongOrDefault(int i, Long l) {
        return (i == -1 || this.cursor.isNull(i)) ? l : Long.valueOf(this.cursor.getLong(i));
    }

    public Long getLongOrDefault(String str, Long l) {
        return getLongOrDefault(this.cursor.getColumnIndex(str), l);
    }

    public short getShortOrDefault(int i, short s) {
        return (i == -1 || this.cursor.isNull(i)) ? s : this.cursor.getShort(i);
    }

    public short getShortOrDefault(String str) {
        return getShortOrDefault(this.cursor.getColumnIndex(str));
    }

    public short getShortOrDefault(int i) {
        return (i == -1 || this.cursor.isNull(i)) ? (short) 0 : this.cursor.getShort(i);
    }

    public short getShortOrDefault(String str, short s) {
        return getShortOrDefault(this.cursor.getColumnIndex(str), s);
    }

    public Short getShortOrDefault(int i, Short sh) {
        return (i == -1 || this.cursor.isNull(i)) ? sh : Short.valueOf(this.cursor.getShort(i));
    }

    public Short getShortOrDefault(String str, Short sh) {
        return getShortOrDefault(this.cursor.getColumnIndex(str), sh);
    }

    public byte[] getBlobOrDefault(int i, byte[] bArr) {
        return (i == -1 || this.cursor.isNull(i)) ? bArr : this.cursor.getBlob(i);
    }

    public byte[] getBlobOrDefault(String str, byte[] bArr) {
        return getBlobOrDefault(this.cursor.getColumnIndex(str), bArr);
    }

    public boolean getBooleanOrDefault(int i, boolean z) {
        return (i == -1 || this.cursor.isNull(i)) ? z : getBoolean(i);
    }

    public boolean getBooleanOrDefault(String str) {
        return getBooleanOrDefault(this.cursor.getColumnIndex(str));
    }

    public boolean getBooleanOrDefault(int i) {
        return (i == -1 || this.cursor.isNull(i)) ? false : getBoolean(i);
    }

    public boolean getBooleanOrDefault(String str, boolean z) {
        return getBooleanOrDefault(this.cursor.getColumnIndex(str), z);
    }

    public boolean getBoolean(int i) {
        return this.cursor.getInt(i) == 1;
    }
}
