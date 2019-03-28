package com.raizlabs.android.dbflow.sql;

import android.content.ContentValues;
import android.net.Uri;
import android.net.Uri.Builder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.StringUtils;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.runtime.NotifyDistributor;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.Operator;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.Map.Entry;

public class SqlUtils {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    @Deprecated
    public static void notifyModelChanged(Class<?> cls, Action action, Iterable<SQLOperator> iterable) {
        FlowManager.getContext().getContentResolver().notifyChange(getNotificationUri((Class) cls, action, (Iterable) iterable), null, true);
    }

    @Deprecated
    public static <TModel> void notifyModelChanged(@Nullable TModel tModel, @NonNull ModelAdapter<TModel> modelAdapter, @NonNull Action action) {
        NotifyDistributor.get().notifyModelChanged(tModel, modelAdapter, action);
    }

    @Deprecated
    public static <TModel> void notifyTableChanged(@NonNull Class<TModel> cls, @NonNull Action action) {
        NotifyDistributor.get().notifyTableChanged(cls, action);
    }

    public static Uri getNotificationUri(@NonNull Class<?> cls, @Nullable Action action, @Nullable Iterable<SQLOperator> iterable) {
        cls = new Builder().scheme("dbflow").authority(FlowManager.getTableName(cls));
        if (action != null) {
            cls.fragment(action.name());
        }
        if (iterable != null) {
            for (SQLOperator sQLOperator : iterable) {
                cls.appendQueryParameter(Uri.encode(sQLOperator.columnName()), Uri.encode(String.valueOf(sQLOperator.value())));
            }
        }
        return cls.build();
    }

    public static Uri getNotificationUri(@NonNull Class<?> cls, @NonNull Action action, @Nullable SQLOperator[] sQLOperatorArr) {
        cls = new Builder().scheme("dbflow").authority(FlowManager.getTableName(cls));
        if (action != null) {
            cls.fragment(action.name());
        }
        if (sQLOperatorArr != null && sQLOperatorArr.length > null) {
            for (SQLOperator sQLOperator : sQLOperatorArr) {
                if (sQLOperator != null) {
                    cls.appendQueryParameter(Uri.encode(sQLOperator.columnName()), Uri.encode(String.valueOf(sQLOperator.value())));
                }
            }
        }
        return cls.build();
    }

    public static Uri getNotificationUri(@NonNull Class<?> cls, @NonNull Action action, @NonNull String str, @Nullable Object obj) {
        str = StringUtils.isNotNullOrEmpty(str) ? Operator.op(new NameAlias.Builder(str).build()).value(obj) : null;
        return getNotificationUri((Class) cls, action, new SQLOperator[]{str});
    }

    public static Uri getNotificationUri(@NonNull Class<?> cls, @NonNull Action action) {
        return getNotificationUri(cls, action, null, null);
    }

    public static void dropTrigger(@NonNull Class<?> cls, @NonNull String str) {
        FlowManager.getDatabaseForTable(cls).getWritableDatabase().execSQL(new QueryBuilder("DROP TRIGGER IF EXISTS ").append(str).getQuery());
    }

    public static void dropIndex(@NonNull DatabaseWrapper databaseWrapper, @NonNull String str) {
        databaseWrapper.execSQL(new QueryBuilder("DROP INDEX IF EXISTS ").append(QueryBuilder.quoteIfNeeded(str)).getQuery());
    }

    public static void dropIndex(@NonNull Class<?> cls, @NonNull String str) {
        dropIndex(FlowManager.getDatabaseForTable(cls).getWritableDatabase(), str);
    }

    public static void addContentValues(@NonNull ContentValues contentValues, @NonNull OperatorGroup operatorGroup) {
        for (Entry key : contentValues.valueSet()) {
            String str = (String) key.getKey();
            operatorGroup.and(Operator.op(new NameAlias.Builder(str).build()).is(contentValues.get(str)));
        }
    }

    @NonNull
    public static String getContentValuesKey(ContentValues contentValues, String str) {
        String quoteIfNeeded = QueryBuilder.quoteIfNeeded(str);
        if (contentValues.containsKey(quoteIfNeeded)) {
            return quoteIfNeeded;
        }
        str = QueryBuilder.stripQuotes(str);
        if (contentValues.containsKey(str) != null) {
            return str;
        }
        throw new IllegalArgumentException("Could not find the specified key in the Content Values object.");
    }

    public static long longForQuery(@NonNull DatabaseWrapper databaseWrapper, @NonNull String str) {
        databaseWrapper = databaseWrapper.compileStatement(str);
        try {
            long simpleQueryForLong = databaseWrapper.simpleQueryForLong();
            return simpleQueryForLong;
        } finally {
            databaseWrapper.close();
        }
    }

    @NonNull
    public static String byteArrayToHexString(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr[i3] = hexArray[i2 >>> 4];
            cArr[i3 + 1] = hexArray[i2 & 15];
        }
        return new String(cArr);
    }
}
