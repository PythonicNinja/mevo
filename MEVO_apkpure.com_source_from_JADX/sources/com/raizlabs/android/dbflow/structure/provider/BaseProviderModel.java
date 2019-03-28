package com.raizlabs.android.dbflow.structure.provider;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;

public abstract class BaseProviderModel extends BaseModel implements ModelProvider {
    public boolean delete() {
        return ContentUtils.delete(getDeleteUri(), this) > 0;
    }

    public boolean save() {
        int update = ContentUtils.update(getUpdateUri(), this);
        boolean z = false;
        if (update == 0) {
            if (ContentUtils.insert(getInsertUri(), this) != null) {
                z = true;
            }
            return z;
        }
        if (update > 0) {
            z = true;
        }
        return z;
    }

    public boolean update() {
        return ContentUtils.update(getUpdateUri(), this) > 0;
    }

    public long insert() {
        ContentUtils.insert(getInsertUri(), this);
        return 0;
    }

    public boolean exists() {
        boolean z = false;
        Cursor query = ContentUtils.query(FlowManager.getContext().getContentResolver(), getQueryUri(), getModelAdapter().getPrimaryConditionClause(this), "", new String[0]);
        if (query != null && query.getCount() > 0) {
            z = true;
        }
        if (query != null) {
            query.close();
        }
        return z;
    }

    public void load(@NonNull OperatorGroup operatorGroup, @Nullable String str, String... strArr) {
        operatorGroup = FlowCursor.from(ContentUtils.query(FlowManager.getContext().getContentResolver(), getQueryUri(), operatorGroup, str, strArr));
        if (operatorGroup != null && operatorGroup.moveToFirst() != null) {
            getModelAdapter().loadFromCursor(operatorGroup, this);
            operatorGroup.close();
        }
    }

    public void load() {
        load(getModelAdapter().getPrimaryConditionClause(this), "", new String[0]);
    }
}
