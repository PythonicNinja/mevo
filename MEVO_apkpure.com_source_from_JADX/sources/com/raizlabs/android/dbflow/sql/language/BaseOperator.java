package com.raizlabs.android.dbflow.sql.language;

import android.database.DatabaseUtils;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.data.Blob;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public abstract class BaseOperator implements SQLOperator {
    protected boolean isValueSet;
    @NonNull
    protected NameAlias nameAlias;
    protected String operation = "";
    protected String postArg;
    protected String separator;
    protected Object value;

    @Nullable
    public static String convertValueToString(Object obj, boolean z) {
        return convertValueToString(obj, z, true);
    }

    @Nullable
    public static String convertValueToString(@Nullable Object obj, boolean z, boolean z2) {
        if (obj == null) {
            return "NULL";
        }
        if (z2) {
            z2 = FlowManager.getTypeConverterForClass(obj.getClass());
            if (z2) {
                obj = z2.getDBValue(obj);
            }
        }
        if (obj instanceof Number) {
            obj = String.valueOf(obj);
        } else if (obj instanceof Enum) {
            obj = DatabaseUtils.sqlEscapeString(((Enum) obj).name());
        } else if (z && (obj instanceof BaseModelQueriable)) {
            obj = String.format("(%1s)", new Object[]{((BaseModelQueriable) obj).getQuery().trim()});
        } else if (obj instanceof NameAlias) {
            obj = ((NameAlias) obj).getQuery();
        } else if (obj instanceof SQLOperator) {
            z = new QueryBuilder();
            ((SQLOperator) obj).appendConditionToQuery(z);
            obj = z.toString();
        } else if (obj instanceof Query) {
            obj = ((Query) obj).getQuery();
        } else {
            z = obj instanceof Blob;
            if (!z) {
                if (!(obj instanceof byte[])) {
                    obj = String.valueOf(obj);
                    if (!obj.equals(Operation.EMPTY_PARAM)) {
                        obj = DatabaseUtils.sqlEscapeString(obj);
                    }
                }
            }
            if (z) {
                obj = ((Blob) obj).getBlob();
            } else {
                obj = (byte[]) obj;
            }
            z = new StringBuilder();
            z.append("X");
            z.append(DatabaseUtils.sqlEscapeString(SqlUtils.byteArrayToHexString(obj)));
            obj = z.toString();
        }
        return obj;
    }

    @NonNull
    public static String joinArguments(@NonNull CharSequence charSequence, @NonNull Iterable iterable, @NonNull BaseOperator baseOperator) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Object next : iterable) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(charSequence);
            }
            stringBuilder.append(baseOperator.convertObjectToString(next, false));
        }
        return stringBuilder.toString();
    }

    @NonNull
    public static String joinArguments(@NonNull CharSequence charSequence, @NonNull Object[] objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Object obj2 : objArr) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(charSequence);
            }
            stringBuilder.append(convertValueToString(obj2, false, true));
        }
        return stringBuilder.toString();
    }

    @NonNull
    public static String joinArguments(@NonNull CharSequence charSequence, @NonNull Iterable iterable) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Object next : iterable) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(charSequence);
            }
            stringBuilder.append(convertValueToString(next, false, true));
        }
        return stringBuilder.toString();
    }

    BaseOperator(@NonNull NameAlias nameAlias) {
        this.nameAlias = nameAlias;
    }

    public Object value() {
        return this.value;
    }

    @NonNull
    public String columnName() {
        return this.nameAlias.getQuery();
    }

    @NonNull
    public SQLOperator separator(@NonNull String str) {
        this.separator = str;
        return this;
    }

    @Nullable
    public String separator() {
        return this.separator;
    }

    public boolean hasSeparator() {
        return this.separator != null && this.separator.length() > 0;
    }

    @NonNull
    public String operation() {
        return this.operation;
    }

    public String postArgument() {
        return this.postArg;
    }

    NameAlias columnAlias() {
        return this.nameAlias;
    }

    public String convertObjectToString(Object obj, boolean z) {
        return convertValueToString(obj, z);
    }
}
