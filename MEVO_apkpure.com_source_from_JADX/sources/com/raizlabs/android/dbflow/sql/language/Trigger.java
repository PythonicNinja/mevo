package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;

public class Trigger implements Query {
    public static final String AFTER = "AFTER";
    public static final String BEFORE = "BEFORE";
    public static final String INSTEAD_OF = "INSTEAD OF";
    String beforeOrAfter;
    boolean temporary;
    final String triggerName;

    @NonNull
    public static Trigger create(@NonNull String str) {
        return new Trigger(str);
    }

    private Trigger(@NonNull String str) {
        this.triggerName = str;
    }

    @NonNull
    public Trigger temporary() {
        this.temporary = true;
        return this;
    }

    @NonNull
    public Trigger after() {
        this.beforeOrAfter = AFTER;
        return this;
    }

    @NonNull
    public Trigger before() {
        this.beforeOrAfter = BEFORE;
        return this;
    }

    @NonNull
    public Trigger insteadOf() {
        this.beforeOrAfter = INSTEAD_OF;
        return this;
    }

    @NonNull
    public <TModel> TriggerMethod<TModel> deleteOn(@NonNull Class<TModel> cls) {
        return new TriggerMethod(this, "DELETE", cls, new IProperty[0]);
    }

    @NonNull
    public <TModel> TriggerMethod<TModel> insertOn(@NonNull Class<TModel> cls) {
        return new TriggerMethod(this, TriggerMethod.INSERT, cls, new IProperty[0]);
    }

    @NonNull
    public <TModel> TriggerMethod<TModel> updateOn(@NonNull Class<TModel> cls, IProperty... iPropertyArr) {
        return new TriggerMethod(this, TriggerMethod.UPDATE, cls, iPropertyArr);
    }

    @NonNull
    public String getName() {
        return this.triggerName;
    }

    public String getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder("CREATE ");
        if (this.temporary) {
            queryBuilder.append("TEMP ");
        }
        QueryBuilder appendSpace = queryBuilder.append("TRIGGER IF NOT EXISTS ").appendQuotedIfNeeded(this.triggerName).appendSpace();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.beforeOrAfter);
        stringBuilder.append(" ");
        appendSpace.appendOptional(stringBuilder.toString());
        return queryBuilder.getQuery();
    }
}
