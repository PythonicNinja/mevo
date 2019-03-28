package com.raizlabs.android.dbflow.sql.queriable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.util.ArrayList;
import java.util.List;

public class ListModelLoader<TModel> extends ModelLoader<TModel, List<TModel>> {
    public ListModelLoader(@NonNull Class<TModel> cls) {
        super(cls);
    }

    @NonNull
    public List<TModel> load(String str) {
        return (List) super.load(str);
    }

    @NonNull
    public List<TModel> load(String str, @Nullable List<TModel> list) {
        return (List) super.load(str, (Object) list);
    }

    @NonNull
    public List<TModel> load(@NonNull DatabaseWrapper databaseWrapper, String str) {
        return (List) super.load(databaseWrapper, str);
    }

    @NonNull
    public List<TModel> load(@NonNull DatabaseWrapper databaseWrapper, String str, @Nullable List<TModel> list) {
        return (List) super.load(databaseWrapper, str, list);
    }

    @NonNull
    public List<TModel> load(@Nullable FlowCursor flowCursor) {
        return (List) super.load(flowCursor);
    }

    @NonNull
    public List<TModel> load(@Nullable FlowCursor flowCursor, @Nullable List<TModel> list) {
        Object arrayList;
        if (list == null) {
            arrayList = new ArrayList();
        } else {
            list.clear();
        }
        return (List) super.load(flowCursor, arrayList);
    }

    @NonNull
    public List<TModel> convertToData(@NonNull FlowCursor flowCursor, @Nullable List<TModel> list) {
        if (list == null) {
            list = new ArrayList();
        } else {
            list.clear();
        }
        if (flowCursor.moveToFirst()) {
            do {
                Object newInstance = getInstanceAdapter().newInstance();
                getInstanceAdapter().loadFromCursor(flowCursor, newInstance);
                list.add(newInstance);
            } while (flowCursor.moveToNext());
        }
        return list;
    }
}
