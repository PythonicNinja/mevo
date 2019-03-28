package com.raizlabs.android.dbflow.structure;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.sql.saveable.ListModelSaver;
import com.raizlabs.android.dbflow.sql.saveable.ModelSaver;
import com.raizlabs.android.dbflow.structure.cache.IMultiKeyCacheConverter;
import com.raizlabs.android.dbflow.structure.cache.ModelCache;
import com.raizlabs.android.dbflow.structure.cache.SimpleMapCache;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.util.Collection;

public abstract class ModelAdapter<TModel> extends InstanceAdapter<TModel> implements InternalAdapter<TModel> {
    private String[] cachingColumns;
    private DatabaseStatement compiledStatement;
    private DatabaseStatement deleteStatement;
    private DatabaseStatement insertStatement;
    private ListModelSaver<TModel> listModelSaver;
    private ModelCache<TModel, ?> modelCache;
    private ModelSaver<TModel> modelSaver;
    private DatabaseStatement updateStatement;

    public boolean cachingEnabled() {
        return false;
    }

    public void deleteForeignKeys(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper) {
    }

    public abstract IProperty[] getAllColumnProperties();

    public int getCacheSize() {
        return 25;
    }

    protected abstract String getCompiledStatementQuery();

    public abstract String getCreationQuery();

    protected abstract String getDeleteStatementQuery();

    public abstract Property getProperty(String str);

    protected abstract String getUpdateStatementQuery();

    public void saveForeignKeys(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper) {
    }

    public void updateAutoIncrement(@NonNull TModel tModel, @NonNull Number number) {
    }

    public ModelAdapter(@NonNull DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
        if (getTableConfig() != null && getTableConfig().modelSaver() != null) {
            this.modelSaver = getTableConfig().modelSaver();
            this.modelSaver.setModelAdapter(this);
        }
    }

    @NonNull
    public DatabaseStatement getInsertStatement() {
        if (this.insertStatement == null) {
            this.insertStatement = getInsertStatement(FlowManager.getWritableDatabaseForTable(getModelClass()));
        }
        return this.insertStatement;
    }

    @NonNull
    public DatabaseStatement getUpdateStatement() {
        if (this.updateStatement == null) {
            this.updateStatement = getUpdateStatement(FlowManager.getWritableDatabaseForTable(getModelClass()));
        }
        return this.updateStatement;
    }

    @NonNull
    public DatabaseStatement getDeleteStatement() {
        if (this.deleteStatement == null) {
            this.deleteStatement = getDeleteStatement(FlowManager.getWritableDatabaseForTable(getModelClass()));
        }
        return this.deleteStatement;
    }

    public void closeInsertStatement() {
        if (this.insertStatement != null) {
            this.insertStatement.close();
            this.insertStatement = null;
        }
    }

    @NonNull
    public DatabaseStatement getInsertStatement(@NonNull DatabaseWrapper databaseWrapper) {
        return databaseWrapper.compileStatement(getInsertStatementQuery());
    }

    @NonNull
    public DatabaseStatement getUpdateStatement(@NonNull DatabaseWrapper databaseWrapper) {
        return databaseWrapper.compileStatement(getUpdateStatementQuery());
    }

    @NonNull
    public DatabaseStatement getDeleteStatement(@NonNull DatabaseWrapper databaseWrapper) {
        return databaseWrapper.compileStatement(getDeleteStatementQuery());
    }

    @NonNull
    public DatabaseStatement getCompiledStatement() {
        if (this.compiledStatement == null) {
            this.compiledStatement = getCompiledStatement(FlowManager.getWritableDatabaseForTable(getModelClass()));
        }
        return this.compiledStatement;
    }

    public void closeCompiledStatement() {
        if (this.compiledStatement != null) {
            this.compiledStatement.close();
            this.compiledStatement = null;
        }
    }

    public DatabaseStatement getCompiledStatement(@NonNull DatabaseWrapper databaseWrapper) {
        return databaseWrapper.compileStatement(getCompiledStatementQuery());
    }

    public TModel loadFromCursor(@NonNull FlowCursor flowCursor) {
        TModel newInstance = newInstance();
        loadFromCursor(flowCursor, newInstance);
        return newInstance;
    }

    public boolean save(@NonNull TModel tModel) {
        return getModelSaver().save(tModel);
    }

    public boolean save(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper) {
        return getModelSaver().save(tModel, databaseWrapper);
    }

    public void saveAll(@NonNull Collection<TModel> collection) {
        getListModelSaver().saveAll(collection);
    }

    public void saveAll(@NonNull Collection<TModel> collection, @NonNull DatabaseWrapper databaseWrapper) {
        getListModelSaver().saveAll(collection, databaseWrapper);
    }

    public long insert(@NonNull TModel tModel) {
        return getModelSaver().insert(tModel);
    }

    public long insert(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper) {
        return getModelSaver().insert(tModel, databaseWrapper);
    }

    public void insertAll(@NonNull Collection<TModel> collection) {
        getListModelSaver().insertAll(collection);
    }

    public void insertAll(@NonNull Collection<TModel> collection, @NonNull DatabaseWrapper databaseWrapper) {
        getListModelSaver().insertAll(collection, databaseWrapper);
    }

    public boolean update(@NonNull TModel tModel) {
        return getModelSaver().update(tModel);
    }

    public boolean update(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper) {
        return getModelSaver().update(tModel, databaseWrapper);
    }

    public void updateAll(@NonNull Collection<TModel> collection) {
        getListModelSaver().updateAll(collection);
    }

    public void updateAll(@NonNull Collection<TModel> collection, @NonNull DatabaseWrapper databaseWrapper) {
        getListModelSaver().updateAll(collection, databaseWrapper);
    }

    public boolean delete(@NonNull TModel tModel) {
        return getModelSaver().delete(tModel);
    }

    public boolean delete(@NonNull TModel tModel, @NonNull DatabaseWrapper databaseWrapper) {
        return getModelSaver().delete(tModel, databaseWrapper);
    }

    public void deleteAll(@NonNull Collection<TModel> collection, @NonNull DatabaseWrapper databaseWrapper) {
        getListModelSaver().deleteAll(collection, databaseWrapper);
    }

    public void deleteAll(@NonNull Collection<TModel> collection) {
        getListModelSaver().deleteAll(collection);
    }

    public void bindToInsertStatement(@NonNull DatabaseStatement databaseStatement, @NonNull TModel tModel) {
        bindToInsertStatement(databaseStatement, tModel, 0);
    }

    public void bindToContentValues(@NonNull ContentValues contentValues, @NonNull TModel tModel) {
        bindToInsertValues(contentValues, tModel);
    }

    public void bindToStatement(@NonNull DatabaseStatement databaseStatement, @NonNull TModel tModel) {
        bindToInsertStatement(databaseStatement, tModel, 0);
    }

    @NonNull
    public Number getAutoIncrementingId(@NonNull TModel tModel) {
        throw new InvalidDBConfiguration(String.format("This method may have been called in error. The model class %1s must containa single primary key (if used in a ModelCache, this method may be called)", new Object[]{getModelClass()}));
    }

    @NonNull
    public String getAutoIncrementingColumnName() {
        throw new InvalidDBConfiguration(String.format("This method may have been called in error. The model class %1s must contain an autoincrementing or single int/long primary key (if used in a ModelCache, this method may be called)", new Object[]{getModelClass()}));
    }

    @NonNull
    public String[] createCachingColumns() {
        return new String[]{getAutoIncrementingColumnName()};
    }

    public String[] getCachingColumns() {
        if (this.cachingColumns == null) {
            this.cachingColumns = createCachingColumns();
        }
        return this.cachingColumns;
    }

    public Object[] getCachingColumnValuesFromCursor(@NonNull Object[] objArr, @NonNull FlowCursor flowCursor) {
        throwCachingError();
        return null;
    }

    public Object getCachingColumnValueFromCursor(@NonNull FlowCursor flowCursor) {
        throwSingleCachingError();
        return null;
    }

    public Object[] getCachingColumnValuesFromModel(@NonNull Object[] objArr, @NonNull TModel tModel) {
        throwCachingError();
        return null;
    }

    public Object getCachingColumnValueFromModel(@NonNull TModel tModel) {
        throwSingleCachingError();
        return null;
    }

    public void storeModelInCache(@NonNull TModel tModel) {
        getModelCache().addModel(getCachingId((Object) tModel), tModel);
    }

    public void removeModelFromCache(@NonNull TModel tModel) {
        getModelCache().removeModel(getCachingId((Object) tModel));
    }

    public ModelCache<TModel, ?> getModelCache() {
        if (this.modelCache == null) {
            this.modelCache = createModelCache();
        }
        return this.modelCache;
    }

    public Object getCachingId(@NonNull Object[] objArr) {
        if (objArr.length == 1) {
            return objArr[0];
        }
        return getCacheConverter().getCachingKey(objArr);
    }

    public Object getCachingId(@NonNull TModel tModel) {
        return getCachingId(getCachingColumnValuesFromModel(new Object[getCachingColumns().length], tModel));
    }

    public ModelSaver<TModel> getModelSaver() {
        if (this.modelSaver == null) {
            this.modelSaver = new ModelSaver();
            this.modelSaver.setModelAdapter(this);
        }
        return this.modelSaver;
    }

    public ListModelSaver<TModel> getListModelSaver() {
        if (this.listModelSaver == null) {
            this.listModelSaver = createListModelSaver();
        }
        return this.listModelSaver;
    }

    protected ListModelSaver<TModel> createListModelSaver() {
        return new ListModelSaver(getModelSaver());
    }

    public void setModelSaver(ModelSaver<TModel> modelSaver) {
        this.modelSaver = modelSaver;
        this.modelSaver.setModelAdapter(this);
    }

    public void reloadRelationships(@NonNull TModel tModel, @NonNull FlowCursor flowCursor) {
        if (cachingEnabled() == null) {
            throwCachingError();
        }
    }

    public IMultiKeyCacheConverter<?> getCacheConverter() {
        throw new InvalidDBConfiguration("For multiple primary keys, a public static IMultiKeyCacheConverter field mustbe  marked with @MultiCacheField in the corresponding model class. The resulting keymust be a unique combination of the multiple keys, otherwise inconsistencies may occur.");
    }

    public ModelCache<TModel, ?> createModelCache() {
        return new SimpleMapCache(getCacheSize());
    }

    protected String getInsertStatementQuery() {
        return getCompiledStatementQuery();
    }

    public ConflictAction getUpdateOnConflictAction() {
        return ConflictAction.ABORT;
    }

    public ConflictAction getInsertOnConflictAction() {
        return ConflictAction.ABORT;
    }

    private void throwCachingError() {
        throw new InvalidDBConfiguration(String.format("This method may have been called in error. The model class %1s must containan auto-incrementing or at least one primary key (if used in a ModelCache, this method may be called)", new Object[]{getModelClass()}));
    }

    private void throwSingleCachingError() {
        throw new InvalidDBConfiguration(String.format("This method may have been called in error. The model class %1s must containan auto-incrementing or one primary key (if used in a ModelCache, this method may be called)", new Object[]{getModelClass()}));
    }
}
