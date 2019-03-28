package com.raizlabs.android.dbflow.sql.saveable;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.runtime.NotifyDistributor;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public class ModelSaver<TModel> {
    private static final int INSERT_FAILED = -1;
    private ModelAdapter<TModel> modelAdapter;

    public void setModelAdapter(@NonNull ModelAdapter<TModel> modelAdapter) {
        this.modelAdapter = modelAdapter;
    }

    public synchronized boolean save(@NonNull TModel tModel) {
        return save((Object) tModel, getWritableDatabase(), this.modelAdapter.getInsertStatement(), this.modelAdapter.getUpdateStatement());
    }

    public synchronized boolean save(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper) {
        return save((Object) tModel, databaseWrapper, this.modelAdapter.getInsertStatement(databaseWrapper), this.modelAdapter.getUpdateStatement(databaseWrapper));
    }

    public synchronized boolean save(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper, @NonNull DatabaseStatement databaseStatement, @NonNull DatabaseStatement databaseStatement2) {
        boolean exists;
        exists = this.modelAdapter.exists(tModel, databaseWrapper);
        if (exists) {
            exists = update((Object) tModel, databaseWrapper, databaseStatement2);
        }
        if (!exists) {
            exists = insert(tModel, databaseStatement, databaseWrapper) > -1;
        }
        if (exists) {
            NotifyDistributor.get().notifyModelChanged(tModel, this.modelAdapter, Action.SAVE);
        }
        return exists;
    }

    public synchronized boolean update(@NonNull TModel tModel) {
        return update((Object) tModel, getWritableDatabase(), this.modelAdapter.getUpdateStatement());
    }

    public synchronized boolean update(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper) {
        DatabaseStatement updateStatement = this.modelAdapter.getUpdateStatement(databaseWrapper);
        try {
            tModel = update((Object) tModel, databaseWrapper, updateStatement);
        } finally {
            updateStatement.close();
        }
        return tModel;
    }

    public synchronized boolean update(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper, @NonNull DatabaseStatement databaseStatement) {
        this.modelAdapter.saveForeignKeys(tModel, databaseWrapper);
        this.modelAdapter.bindToUpdateStatement(databaseStatement, tModel);
        databaseWrapper = databaseStatement.executeUpdateDelete() != 0 ? true : null;
        if (databaseWrapper != null) {
            NotifyDistributor.get().notifyModelChanged(tModel, this.modelAdapter, Action.UPDATE);
        }
        return databaseWrapper;
    }

    public synchronized long insert(@NonNull TModel tModel) {
        return insert(tModel, this.modelAdapter.getInsertStatement(), getWritableDatabase());
    }

    public synchronized long insert(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper) {
        DatabaseStatement insertStatement = this.modelAdapter.getInsertStatement(databaseWrapper);
        try {
            tModel = insert(tModel, insertStatement, databaseWrapper);
        } finally {
            insertStatement.close();
        }
        return tModel;
    }

    public synchronized long insert(@NonNull TModel tModel, @NonNull DatabaseStatement databaseStatement, @NonNull DatabaseWrapper databaseWrapper) {
        this.modelAdapter.saveForeignKeys(tModel, databaseWrapper);
        this.modelAdapter.bindToInsertStatement(databaseStatement, tModel);
        databaseStatement = databaseStatement.executeInsert();
        if (databaseStatement > -1) {
            this.modelAdapter.updateAutoIncrement(tModel, Long.valueOf(databaseStatement));
            NotifyDistributor.get().notifyModelChanged(tModel, this.modelAdapter, Action.INSERT);
        }
        return databaseStatement;
    }

    public synchronized boolean delete(@NonNull TModel tModel) {
        return delete(tModel, this.modelAdapter.getDeleteStatement(), getWritableDatabase());
    }

    public synchronized boolean delete(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper) {
        DatabaseStatement deleteStatement = this.modelAdapter.getDeleteStatement(databaseWrapper);
        try {
            tModel = delete(tModel, deleteStatement, databaseWrapper);
        } finally {
            deleteStatement.close();
        }
        return tModel;
    }

    public synchronized boolean delete(@NonNull TModel tModel, @NonNull DatabaseStatement databaseStatement, @NonNull DatabaseWrapper databaseWrapper) {
        this.modelAdapter.deleteForeignKeys(tModel, databaseWrapper);
        this.modelAdapter.bindToDeleteStatement(databaseStatement, tModel);
        databaseWrapper = databaseStatement.executeUpdateDelete() != 0 ? true : null;
        if (databaseWrapper != null) {
            NotifyDistributor.get().notifyModelChanged(tModel, this.modelAdapter, Action.DELETE);
        }
        this.modelAdapter.updateAutoIncrement(tModel, Integer.valueOf(null));
        return databaseWrapper;
    }

    @NonNull
    protected DatabaseWrapper getWritableDatabase() {
        return FlowManager.getDatabaseForTable(this.modelAdapter.getModelClass()).getWritableDatabase();
    }

    @NonNull
    public ModelAdapter<TModel> getModelAdapter() {
        return this.modelAdapter;
    }

    @Deprecated
    public synchronized boolean save(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper, @NonNull DatabaseStatement databaseStatement, @NonNull ContentValues contentValues) {
        boolean exists;
        exists = this.modelAdapter.exists(tModel, databaseWrapper);
        if (exists) {
            exists = update((Object) tModel, databaseWrapper, contentValues);
        }
        if (!exists) {
            exists = insert(tModel, databaseStatement, databaseWrapper) > -1;
        }
        if (exists) {
            NotifyDistributor.get().notifyModelChanged(tModel, this.modelAdapter, Action.SAVE);
        }
        return exists;
    }

    @Deprecated
    public synchronized boolean update(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper, @NonNull ContentValues contentValues) {
        this.modelAdapter.saveForeignKeys(tModel, databaseWrapper);
        this.modelAdapter.bindToContentValues(contentValues, tModel);
        databaseWrapper = databaseWrapper.updateWithOnConflict(this.modelAdapter.getTableName(), contentValues, this.modelAdapter.getPrimaryConditionClause(tModel).getQuery(), null, ConflictAction.getSQLiteDatabaseAlgorithmInt(this.modelAdapter.getUpdateOnConflictAction())) != 0 ? true : null;
        if (databaseWrapper != null) {
            NotifyDistributor.get().notifyModelChanged(tModel, this.modelAdapter, Action.UPDATE);
        }
        return databaseWrapper;
    }
}
