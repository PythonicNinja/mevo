package com.raizlabs.android.dbflow.structure;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public interface ReadOnlyModel {
    boolean exists();

    boolean exists(@NonNull DatabaseWrapper databaseWrapper);

    void load();

    void load(@NonNull DatabaseWrapper databaseWrapper);
}
