package com.raizlabs.android.dbflow.sql.queriable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;

public interface Queriable extends Query {
    @NonNull
    DatabaseStatement compileStatement();

    @NonNull
    DatabaseStatement compileStatement(@NonNull DatabaseWrapper databaseWrapper);

    long count();

    long count(@NonNull DatabaseWrapper databaseWrapper);

    void execute();

    void execute(@NonNull DatabaseWrapper databaseWrapper);

    long executeInsert();

    long executeInsert(@NonNull DatabaseWrapper databaseWrapper);

    long executeUpdateDelete();

    long executeUpdateDelete(@NonNull DatabaseWrapper databaseWrapper);

    @NonNull
    Action getPrimaryAction();

    boolean hasData();

    boolean hasData(@NonNull DatabaseWrapper databaseWrapper);

    @Nullable
    FlowCursor query();

    @Nullable
    FlowCursor query(@NonNull DatabaseWrapper databaseWrapper);
}
