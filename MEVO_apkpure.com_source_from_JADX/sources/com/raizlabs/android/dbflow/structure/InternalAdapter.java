package com.raizlabs.android.dbflow.structure;

import android.content.ContentValues;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.Collection;

public interface InternalAdapter<TModel> {
    void bindToContentValues(@NonNull ContentValues contentValues, @NonNull TModel tModel);

    void bindToDeleteStatement(@NonNull DatabaseStatement databaseStatement, @NonNull TModel tModel);

    void bindToInsertStatement(@NonNull DatabaseStatement databaseStatement, @NonNull TModel tModel);

    void bindToInsertStatement(@NonNull DatabaseStatement databaseStatement, @NonNull TModel tModel, @IntRange(from = 0, to = 1) int i);

    void bindToInsertValues(@NonNull ContentValues contentValues, @NonNull TModel tModel);

    void bindToStatement(@NonNull DatabaseStatement databaseStatement, @NonNull TModel tModel);

    void bindToUpdateStatement(@NonNull DatabaseStatement databaseStatement, @NonNull TModel tModel);

    boolean cachingEnabled();

    boolean delete(@NonNull TModel tModel);

    boolean delete(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper);

    void deleteAll(@NonNull Collection<TModel> collection);

    void deleteAll(@NonNull Collection<TModel> collection, @NonNull DatabaseWrapper databaseWrapper);

    @NonNull
    Number getAutoIncrementingId(@NonNull TModel tModel);

    @NonNull
    String getTableName();

    long insert(@NonNull TModel tModel);

    long insert(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper);

    void insertAll(@NonNull Collection<TModel> collection);

    void insertAll(@NonNull Collection<TModel> collection, @NonNull DatabaseWrapper databaseWrapper);

    boolean save(@NonNull TModel tModel);

    boolean save(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper);

    void saveAll(@NonNull Collection<TModel> collection);

    void saveAll(@NonNull Collection<TModel> collection, @NonNull DatabaseWrapper databaseWrapper);

    boolean update(@NonNull TModel tModel);

    boolean update(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper);

    void updateAll(@NonNull Collection<TModel> collection);

    void updateAll(@NonNull Collection<TModel> collection, @NonNull DatabaseWrapper databaseWrapper);

    void updateAutoIncrement(@NonNull TModel tModel, @NonNull Number number);
}
