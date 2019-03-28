package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.StringUtils;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;

public class NameAlias implements Query {
    private final String aliasName;
    private final String keyword;
    private final String name;
    private final boolean shouldAddIdentifierToAliasName;
    private final boolean shouldAddIdentifierToQuery;
    private final boolean shouldStripAliasName;
    private final boolean shouldStripIdentifier;
    private final String tableName;

    public static class Builder {
        private String aliasName;
        private String keyword;
        private final String name;
        private boolean shouldAddIdentifierToAliasName = true;
        private boolean shouldAddIdentifierToQuery = true;
        private boolean shouldStripAliasName = true;
        private boolean shouldStripIdentifier = true;
        private String tableName;

        public Builder(String str) {
            this.name = str;
        }

        public Builder distinct() {
            return keyword("DISTINCT");
        }

        public Builder keyword(String str) {
            this.keyword = str;
            return this;
        }

        public Builder as(String str) {
            this.aliasName = str;
            return this;
        }

        public Builder withTable(String str) {
            this.tableName = str;
            return this;
        }

        public Builder shouldStripIdentifier(boolean z) {
            this.shouldStripIdentifier = z;
            return this;
        }

        public Builder shouldStripAliasName(boolean z) {
            this.shouldStripAliasName = z;
            return this;
        }

        public Builder shouldAddIdentifierToName(boolean z) {
            this.shouldAddIdentifierToQuery = z;
            return this;
        }

        public Builder shouldAddIdentifierToAliasName(boolean z) {
            this.shouldAddIdentifierToAliasName = z;
            return this;
        }

        public NameAlias build() {
            return new NameAlias();
        }
    }

    @NonNull
    public static NameAlias joinNames(@NonNull String str, String... strArr) {
        String str2 = "";
        for (int i = 0; i < strArr.length; i++) {
            StringBuilder stringBuilder;
            if (i > 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(" ");
                stringBuilder.append(str);
                stringBuilder.append(" ");
                str2 = stringBuilder.toString();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(strArr[i]);
            str2 = stringBuilder.toString();
        }
        return rawBuilder(str2).build();
    }

    @NonNull
    public static Builder builder(String str) {
        return new Builder(str);
    }

    @NonNull
    public static Builder rawBuilder(String str) {
        return new Builder(str).shouldStripIdentifier(false).shouldAddIdentifierToName(false);
    }

    @NonNull
    public static NameAlias of(String str) {
        return builder(str).build();
    }

    @NonNull
    public static NameAlias of(String str, String str2) {
        return builder(str).as(str2).build();
    }

    @NonNull
    public static NameAlias ofTable(String str, String str2) {
        return builder(str2).withTable(str).build();
    }

    private NameAlias(Builder builder) {
        if (builder.shouldStripIdentifier) {
            this.name = QueryBuilder.stripQuotes(builder.name);
        } else {
            this.name = builder.name;
        }
        this.keyword = builder.keyword;
        if (builder.shouldStripAliasName) {
            this.aliasName = QueryBuilder.stripQuotes(builder.aliasName);
        } else {
            this.aliasName = builder.aliasName;
        }
        if (StringUtils.isNotNullOrEmpty(builder.tableName)) {
            this.tableName = QueryBuilder.quoteIfNeeded(builder.tableName);
        } else {
            this.tableName = null;
        }
        this.shouldStripIdentifier = builder.shouldStripIdentifier;
        this.shouldStripAliasName = builder.shouldStripAliasName;
        this.shouldAddIdentifierToQuery = builder.shouldAddIdentifierToQuery;
        this.shouldAddIdentifierToAliasName = builder.shouldAddIdentifierToAliasName;
    }

    public String name() {
        return (StringUtils.isNotNullOrEmpty(this.name) && this.shouldAddIdentifierToQuery) ? QueryBuilder.quoteIfNeeded(this.name) : this.name;
    }

    public String nameRaw() {
        return this.shouldStripIdentifier ? this.name : QueryBuilder.stripQuotes(this.name);
    }

    public String aliasName() {
        return (StringUtils.isNotNullOrEmpty(this.aliasName) && this.shouldAddIdentifierToAliasName) ? QueryBuilder.quoteIfNeeded(this.aliasName) : this.aliasName;
    }

    public String aliasNameRaw() {
        return this.shouldStripAliasName ? this.aliasName : QueryBuilder.stripQuotes(this.aliasName);
    }

    public String tableName() {
        return this.tableName;
    }

    public String keyword() {
        return this.keyword;
    }

    public boolean shouldStripIdentifier() {
        return this.shouldStripIdentifier;
    }

    public boolean shouldStripAliasName() {
        return this.shouldStripAliasName;
    }

    public String fullName() {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        if (StringUtils.isNotNullOrEmpty(this.tableName)) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(tableName());
            stringBuilder3.append(".");
            stringBuilder = stringBuilder3.toString();
        } else {
            stringBuilder = "";
        }
        stringBuilder2.append(stringBuilder);
        stringBuilder2.append(name());
        return stringBuilder2.toString();
    }

    public String getQuery() {
        if (StringUtils.isNotNullOrEmpty(this.aliasName)) {
            return aliasName();
        }
        return StringUtils.isNotNullOrEmpty(this.name) ? fullName() : "";
    }

    public String getNameAsKey() {
        if (StringUtils.isNotNullOrEmpty(this.aliasName)) {
            return aliasNameRaw();
        }
        return nameRaw();
    }

    public String toString() {
        return getFullQuery();
    }

    public String getFullQuery() {
        String fullName = fullName();
        if (StringUtils.isNotNullOrEmpty(this.aliasName)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(fullName);
            stringBuilder.append(" AS ");
            stringBuilder.append(aliasName());
            fullName = stringBuilder.toString();
        }
        if (!StringUtils.isNotNullOrEmpty(this.keyword)) {
            return fullName;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(this.keyword);
        stringBuilder.append(" ");
        stringBuilder.append(fullName);
        return stringBuilder.toString();
    }

    public Builder newBuilder() {
        return new Builder(this.name).keyword(this.keyword).as(this.aliasName).shouldStripAliasName(this.shouldStripAliasName).shouldStripIdentifier(this.shouldStripIdentifier).shouldAddIdentifierToName(this.shouldAddIdentifierToQuery).shouldAddIdentifierToAliasName(this.shouldAddIdentifierToAliasName).withTable(this.tableName);
    }
}
