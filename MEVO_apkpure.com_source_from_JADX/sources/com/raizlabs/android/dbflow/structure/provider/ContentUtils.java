package com.raizlabs.android.dbflow.structure.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.util.ArrayList;
import java.util.List;

public class ContentUtils {
    public static final String BASE_CONTENT_URI = "content://";

    public static Uri buildUriWithAuthority(String str, String... strArr) {
        return buildUri(BASE_CONTENT_URI, str, strArr);
    }

    public static Uri buildUri(String str, String str2, String... strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str2);
        str = Uri.parse(stringBuilder.toString()).buildUpon();
        for (String appendPath : strArr) {
            str.appendPath(appendPath);
        }
        return str.build();
    }

    public static <TableClass> Uri insert(Uri uri, TableClass tableClass) {
        return insert(FlowManager.getContext().getContentResolver(), uri, tableClass);
    }

    public static <TableClass> Uri insert(ContentResolver contentResolver, Uri uri, TableClass tableClass) {
        ModelAdapter modelAdapter = FlowManager.getModelAdapter(tableClass.getClass());
        ContentValues contentValues = new ContentValues();
        modelAdapter.bindToInsertValues(contentValues, tableClass);
        contentResolver = contentResolver.insert(uri, contentValues);
        modelAdapter.updateAutoIncrement(tableClass, Long.valueOf((String) contentResolver.getPathSegments().get(contentResolver.getPathSegments().size() - 1)));
        return contentResolver;
    }

    public static <TableClass> int bulkInsert(ContentResolver contentResolver, Uri uri, Class<TableClass> cls, List<TableClass> list) {
        ContentValues[] contentValuesArr = new ContentValues[(list == null ? 0 : list.size())];
        cls = FlowManager.getModelAdapter(cls);
        if (list != null) {
            for (int i = 0; i < contentValuesArr.length; i++) {
                contentValuesArr[i] = new ContentValues();
                cls.bindToInsertValues(contentValuesArr[i], list.get(i));
            }
        }
        return contentResolver.bulkInsert(uri, contentValuesArr);
    }

    public static <TableClass> int bulkInsert(Uri uri, Class<TableClass> cls, List<TableClass> list) {
        return bulkInsert(FlowManager.getContext().getContentResolver(), uri, cls, list);
    }

    public static <TableClass> int update(Uri uri, TableClass tableClass) {
        return update(FlowManager.getContext().getContentResolver(), uri, tableClass);
    }

    public static <TableClass> int update(ContentResolver contentResolver, Uri uri, TableClass tableClass) {
        ModelAdapter modelAdapter = FlowManager.getModelAdapter(tableClass.getClass());
        ContentValues contentValues = new ContentValues();
        modelAdapter.bindToContentValues(contentValues, tableClass);
        contentResolver = contentResolver.update(uri, contentValues, modelAdapter.getPrimaryConditionClause(tableClass).getQuery(), null);
        if (contentResolver == null) {
            Level level = Level.W;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Updated failed of: ");
            stringBuilder.append(tableClass.getClass());
            FlowLog.log(level, stringBuilder.toString());
        }
        return contentResolver;
    }

    public static <TableClass> int delete(Uri uri, TableClass tableClass) {
        return delete(FlowManager.getContext().getContentResolver(), uri, tableClass);
    }

    public static <TableClass> int delete(ContentResolver contentResolver, Uri uri, TableClass tableClass) {
        ModelAdapter modelAdapter = FlowManager.getModelAdapter(tableClass.getClass());
        contentResolver = contentResolver.delete(uri, modelAdapter.getPrimaryConditionClause(tableClass).getQuery(), null);
        if (contentResolver > null) {
            modelAdapter.updateAutoIncrement(tableClass, Integer.valueOf(null));
        } else {
            Level level = Level.W;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("A delete on ");
            stringBuilder.append(tableClass.getClass());
            stringBuilder.append(" within the ContentResolver appeared to fail.");
            FlowLog.log(level, stringBuilder.toString());
        }
        return contentResolver;
    }

    public static Cursor query(ContentResolver contentResolver, Uri uri, OperatorGroup operatorGroup, String str, String... strArr) {
        return contentResolver.query(uri, strArr, operatorGroup.getQuery(), null, str);
    }

    public static <TableClass> List<TableClass> queryList(Uri uri, Class<TableClass> cls, OperatorGroup operatorGroup, String str, String... strArr) {
        return queryList(FlowManager.getContext().getContentResolver(), uri, cls, operatorGroup, str, strArr);
    }

    public static <TableClass> List<TableClass> queryList(ContentResolver contentResolver, Uri uri, Class<TableClass> cls, OperatorGroup operatorGroup, String str, String... strArr) {
        FlowCursor from = FlowCursor.from(contentResolver.query(uri, strArr, operatorGroup.getQuery(), null, str));
        if (from != null) {
            return FlowManager.getModelAdapter(cls).getListModelLoader().load(from);
        }
        return new ArrayList();
    }

    public static <TableClass> TableClass querySingle(Uri uri, Class<TableClass> cls, OperatorGroup operatorGroup, String str, String... strArr) {
        return querySingle(FlowManager.getContext().getContentResolver(), uri, cls, operatorGroup, str, strArr);
    }

    public static <TableClass> TableClass querySingle(ContentResolver contentResolver, Uri uri, Class<TableClass> cls, OperatorGroup operatorGroup, String str, String... strArr) {
        contentResolver = queryList(contentResolver, uri, cls, operatorGroup, str, strArr);
        return contentResolver.size() > null ? contentResolver.get(null) : null;
    }
}
