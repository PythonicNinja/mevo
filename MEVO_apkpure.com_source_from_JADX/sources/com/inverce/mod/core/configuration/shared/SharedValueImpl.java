package com.inverce.mod.core.configuration.shared;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import com.inverce.mod.core.IM;

abstract class SharedValueImpl<T> {
    protected T defaultValue;
    protected String key;
    protected String storeFile;

    static class Bool extends SharedValueImpl<Boolean> {
        Bool() {
        }

        public Boolean getValue() {
            return Boolean.valueOf(store().getBoolean(this.key, ((Boolean) defaultOr(Boolean.valueOf(false))).booleanValue()));
        }

        public void setValue(Boolean bool) {
            store().edit().putBoolean(this.key, bool.booleanValue()).apply();
        }
    }

    static class Floating extends SharedValueImpl<Float> {
        Floating() {
        }

        public Float getValue() {
            return Float.valueOf(store().getFloat(this.key, ((Float) defaultOr(Float.valueOf(0.0f))).floatValue()));
        }

        public void setValue(Float f) {
            store().edit().putFloat(this.key, f.floatValue()).apply();
        }
    }

    static class Int extends SharedValueImpl<Integer> {
        Int() {
        }

        public Integer getValue() {
            return Integer.valueOf(store().getInt(this.key, ((Integer) defaultOr(Integer.valueOf(0))).intValue()));
        }

        public void setValue(Integer num) {
            store().edit().putInt(this.key, num.intValue()).apply();
        }
    }

    static class LongInt extends SharedValueImpl<Long> {
        LongInt() {
        }

        public Long getValue() {
            return Long.valueOf(store().getLong(this.key, ((Long) defaultOr(Long.valueOf(0))).longValue()));
        }

        public void setValue(Long l) {
            store().edit().putLong(this.key, l.longValue()).apply();
        }
    }

    static class Text extends SharedValueImpl<String> {
        Text() {
        }

        @Nullable
        public String getValue() {
            return store().getString(this.key, (String) defaultOr(""));
        }

        public void setValue(String str) {
            store().edit().putString(this.key, str).apply();
        }
    }

    @Nullable
    public abstract T getValue();

    public abstract void setValue(T t);

    SharedValueImpl() {
    }

    public void with(String str, String str2, Object obj) {
        this.key = str;
        this.storeFile = str2;
        this.defaultValue = obj;
    }

    String getKey() {
        return this.key;
    }

    protected T defaultOr(T t) {
        return this.defaultValue != null ? this.defaultValue : t;
    }

    protected SharedPreferences store() {
        return IM.context().getSharedPreferences(this.storeFile, 0);
    }
}
