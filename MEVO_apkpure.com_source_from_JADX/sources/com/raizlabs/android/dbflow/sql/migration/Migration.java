package com.raizlabs.android.dbflow.sql.migration;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public interface Migration {
    void migrate(@NonNull DatabaseWrapper databaseWrapper);

    void onPostMigrate();

    void onPreMigrate();
}
