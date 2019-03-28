package com.raizlabs.android.dbflow.structure;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public class BaseQueryModel extends NoModificationModel {
    public /* bridge */ /* synthetic */ RetrievalAdapter getRetrievalAdapter() {
        return super.getRetrievalAdapter();
    }

    public /* bridge */ /* synthetic */ void load() {
        super.load();
    }

    public /* bridge */ /* synthetic */ void load(@NonNull DatabaseWrapper databaseWrapper) {
        super.load(databaseWrapper);
    }

    public boolean exists() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Query ");
        stringBuilder.append(getClass().getName());
        stringBuilder.append(" does not exist as a table.It's a convenient representation of a complex SQLite query.");
        throw new InvalidSqlViewOperationException(stringBuilder.toString());
    }

    public boolean exists(@NonNull DatabaseWrapper databaseWrapper) {
        return exists();
    }
}
