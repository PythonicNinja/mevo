package com.raizlabs.android.dbflow.sql.migration;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public abstract class BaseMigration implements Migration {
    public abstract void migrate(@NonNull DatabaseWrapper databaseWrapper);

    public void onPostMigrate() {
    }

    public void onPreMigrate() {
    }
}
