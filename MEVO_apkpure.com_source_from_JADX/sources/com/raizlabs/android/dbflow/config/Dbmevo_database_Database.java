package com.raizlabs.android.dbflow.config;

import com.mevo.app.data.database.Db;
import com.mevo.app.data.model.RentalRoute_Table;
import com.mevo.app.data.model.UserDetails_Table;
import com.mevo.app.data.model.User_Table;

public final class Dbmevo_database_Database extends DatabaseDefinition {
    public final boolean areConsistencyChecksEnabled() {
        return false;
    }

    public final boolean backupEnabled() {
        return false;
    }

    public final String getDatabaseName() {
        return Db.NAME;
    }

    public final int getDatabaseVersion() {
        return 1;
    }

    public final boolean isForeignKeysSupported() {
        return false;
    }

    public final boolean isInMemory() {
        return false;
    }

    public Dbmevo_database_Database(DatabaseHolder databaseHolder) {
        addModelAdapter(new RentalRoute_Table(this), databaseHolder);
        addModelAdapter(new UserDetails_Table(this), databaseHolder);
        addModelAdapter(new User_Table(this), databaseHolder);
    }

    public final Class<?> getAssociatedDatabaseClassFile() {
        return Db.class;
    }
}
