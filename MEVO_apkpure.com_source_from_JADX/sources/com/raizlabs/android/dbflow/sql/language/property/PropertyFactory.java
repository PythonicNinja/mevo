package com.raizlabs.android.dbflow.sql.language.property;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.Operator;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;

public class PropertyFactory {
    @NonNull
    public static Property<Character> from(char c) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("'");
        stringBuilder.append(c);
        stringBuilder.append("'");
        return new Property(null, NameAlias.rawBuilder(stringBuilder.toString()).build());
    }

    @NonNull
    public static Property<Integer> from(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append("");
        return new Property(null, NameAlias.rawBuilder(stringBuilder.toString()).build());
    }

    @NonNull
    public static Property<Double> from(double d) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(d);
        stringBuilder.append("");
        return new Property(null, NameAlias.rawBuilder(stringBuilder.toString()).build());
    }

    @NonNull
    public static Property<Long> from(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(j);
        stringBuilder.append("");
        return new Property(null, NameAlias.rawBuilder(stringBuilder.toString()).build());
    }

    @NonNull
    public static Property<Float> from(float f) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(f);
        stringBuilder.append("");
        return new Property(null, NameAlias.rawBuilder(stringBuilder.toString()).build());
    }

    @NonNull
    public static Property<Short> from(short s) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s);
        stringBuilder.append("");
        return new Property(null, NameAlias.rawBuilder(stringBuilder.toString()).build());
    }

    @NonNull
    public static Property<Byte> from(byte b) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b);
        stringBuilder.append("");
        return new Property(null, NameAlias.rawBuilder(stringBuilder.toString()).build());
    }

    @NonNull
    public static <T> Property<T> from(@Nullable T t) {
        return new Property(null, NameAlias.rawBuilder(Operator.convertValueToString(t)).build());
    }

    @NonNull
    public static <TModel> Property<TModel> from(@NonNull ModelQueriable<TModel> modelQueriable) {
        Class table = modelQueriable.getTable();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append(String.valueOf(modelQueriable.getQuery()).trim());
        stringBuilder.append(")");
        return from(table, stringBuilder.toString());
    }

    @NonNull
    public static <T> Property<T> from(@Nullable Class<T> cls, @Nullable String str) {
        return new Property(null, NameAlias.rawBuilder(str).build());
    }
}
