package com.raizlabs.android.dbflow.sql;

import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.util.List;
import java.util.regex.Pattern;

public class QueryBuilder<QueryClass extends QueryBuilder> implements Query {
    private static final char QUOTE = '`';
    private static final Pattern QUOTE_PATTERN = Pattern.compile("`.*`");
    protected StringBuilder query = new StringBuilder();

    protected QueryClass castThis() {
        return this;
    }

    public QueryBuilder(Object obj) {
        append(obj);
    }

    public QueryClass appendSpace() {
        return append(" ");
    }

    public QueryClass appendSpaceSeparated(Object obj) {
        return appendSpace().append(obj).appendSpace();
    }

    public QueryClass appendParenthesisEnclosed(Object obj) {
        return append("(").append(obj).append(")");
    }

    public QueryClass append(Object obj) {
        this.query.append(obj);
        return castThis();
    }

    public QueryClass appendOptional(Object obj) {
        if (obj != null) {
            append(obj);
        }
        return castThis();
    }

    public QueryClass appendType(String str) {
        return appendSQLiteType(SQLiteType.get(str));
    }

    public QueryClass appendSQLiteType(SQLiteType sQLiteType) {
        return append(sQLiteType.name());
    }

    public QueryClass appendArray(Object... objArr) {
        return append(join((CharSequence) ", ", objArr));
    }

    public QueryClass appendList(List<?> list) {
        return append(join((CharSequence) ", ", (Iterable) list));
    }

    public QueryClass appendQualifier(String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            if (str != null) {
                append(str);
            }
            appendSpaceSeparated(str2);
        }
        return castThis();
    }

    public QueryClass appendNotEmpty(String str) {
        if (!(str == null || str.isEmpty())) {
            append(str);
        }
        return castThis();
    }

    public QueryClass appendQuoted(String str) {
        if (str.equals(Operation.MULTIPLY)) {
            return append(str);
        }
        append(quote(str));
        return castThis();
    }

    public QueryClass appendQuotedIfNeeded(String str) {
        if (str.equals(Operation.MULTIPLY)) {
            return append(str);
        }
        append(quoteIfNeeded(str));
        return castThis();
    }

    public QueryClass appendQuotedList(List<?> list) {
        return appendQuoted(join((CharSequence) "`, `", (Iterable) list));
    }

    public QueryClass appendQuotedArray(Object... objArr) {
        return appendQuoted(join((CharSequence) "`, `", objArr));
    }

    public String toString() {
        return getQuery();
    }

    public String getQuery() {
        return this.query.toString();
    }

    public static String quote(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(QUOTE);
        stringBuilder.append(str.replace(".", "`.`"));
        stringBuilder.append(QUOTE);
        return stringBuilder.toString();
    }

    public static String quoteIfNeeded(String str) {
        return (str == null || isQuoted(str)) ? str : quote(str);
    }

    public static boolean isQuoted(String str) {
        return QUOTE_PATTERN.matcher(str).find();
    }

    public static String stripQuotes(String str) {
        return (str == null || !isQuoted(str)) ? str : str.replace("`", "");
    }

    public static String join(CharSequence charSequence, Object[] objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Object obj2 : objArr) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(charSequence);
            }
            stringBuilder.append(obj2);
        }
        return stringBuilder.toString();
    }

    public static String join(CharSequence charSequence, Iterable iterable) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Object next : iterable) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(charSequence);
            }
            stringBuilder.append(next);
        }
        return stringBuilder.toString();
    }
}
