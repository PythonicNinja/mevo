package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.google.android.gms.stats.netstats.NetstatsParserPatterns;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Select implements Query {
    public static final int ALL = 1;
    public static final int DISTINCT = 0;
    public static final int NONE = -1;
    private int mSelectQualifier = -1;
    private final List<IProperty> propertyList = new ArrayList();

    public Select(IProperty... iPropertyArr) {
        Collections.addAll(this.propertyList, iPropertyArr);
        if (this.propertyList.isEmpty() != null) {
            this.propertyList.add(Property.ALL_PROPERTY);
        }
    }

    @NonNull
    public <TModel> From<TModel> from(@NonNull Class<TModel> cls) {
        return new From(this, cls);
    }

    @NonNull
    public Select distinct() {
        return selectQualifier(0);
    }

    @NonNull
    public String toString() {
        return getQuery();
    }

    public String getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder("SELECT ");
        if (this.mSelectQualifier != -1) {
            if (this.mSelectQualifier == 0) {
                queryBuilder.append("DISTINCT");
            } else if (this.mSelectQualifier == 1) {
                queryBuilder.append(NetstatsParserPatterns.TYPE_BOTH_PATTERN);
            }
            queryBuilder.appendSpace();
        }
        queryBuilder.append(QueryBuilder.join((CharSequence) ",", this.propertyList));
        queryBuilder.appendSpace();
        return queryBuilder.getQuery();
    }

    private Select selectQualifier(int i) {
        this.mSelectQualifier = i;
        return this;
    }
}
