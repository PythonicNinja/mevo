package com.raizlabs.android.dbflow.structure.listener;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;

public interface SQLiteStatementListener {
    void onBindToDeleteStatement(@NonNull DatabaseStatement databaseStatement);

    void onBindToInsertStatement(@NonNull DatabaseStatement databaseStatement);

    void onBindToStatement(@NonNull DatabaseStatement databaseStatement);

    void onBindToUpdateStatement(@NonNull DatabaseStatement databaseStatement);
}
