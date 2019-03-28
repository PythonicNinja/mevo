package com.raizlabs.android.dbflow.structure.database;

import android.support.annotation.NonNull;

public interface DatabaseHelperListener {
    void onCreate(@NonNull DatabaseWrapper databaseWrapper);

    void onDowngrade(@NonNull DatabaseWrapper databaseWrapper, int i, int i2);

    void onOpen(@NonNull DatabaseWrapper databaseWrapper);

    void onUpgrade(@NonNull DatabaseWrapper databaseWrapper, int i, int i2);
}
