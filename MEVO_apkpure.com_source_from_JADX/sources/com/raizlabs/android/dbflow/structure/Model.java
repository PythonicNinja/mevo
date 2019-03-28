package com.raizlabs.android.dbflow.structure;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public interface Model extends ReadOnlyModel {
    public static final long INVALID_ROW_ID = -1;

    @NonNull
    AsyncModel<? extends Model> async();

    boolean delete();

    boolean delete(@NonNull DatabaseWrapper databaseWrapper);

    long insert();

    long insert(DatabaseWrapper databaseWrapper);

    boolean save();

    boolean save(@NonNull DatabaseWrapper databaseWrapper);

    boolean update();

    boolean update(@NonNull DatabaseWrapper databaseWrapper);
}
