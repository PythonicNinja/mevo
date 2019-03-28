package com.raizlabs.android.dbflow.structure.provider;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;

public abstract class BaseSyncableProviderModel extends BaseModel implements ModelProvider {
    public long insert() {
        long insert = super.insert();
        ContentUtils.insert(getInsertUri(), this);
        return insert;
    }

    public boolean save() {
        boolean z = false;
        if (exists()) {
            if (super.save() && ContentUtils.update(getUpdateUri(), this) > 0) {
                z = true;
            }
            return z;
        }
        if (super.save() && ContentUtils.insert(getInsertUri(), this) != null) {
            z = true;
        }
        return z;
    }

    public boolean delete() {
        return super.delete() && ContentUtils.delete(getDeleteUri(), this) > 0;
    }

    public boolean update() {
        return super.update() && ContentUtils.update(getUpdateUri(), this) > 0;
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
