package com.raizlabs.android.dbflow.config;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.StringUtils;
import com.raizlabs.android.dbflow.runtime.BaseTransactionManager;
import com.raizlabs.android.dbflow.runtime.ContentResolverNotifier;
import com.raizlabs.android.dbflow.runtime.ModelNotifier;
import com.raizlabs.android.dbflow.sql.migration.Migration;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.ModelViewAdapter;
import com.raizlabs.android.dbflow.structure.QueryModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseHelperListener;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowSQLiteOpenHelper;
import com.raizlabs.android.dbflow.structure.database.OpenHelper;
import com.raizlabs.android.dbflow.structure.database.transaction.DefaultTransactionManager;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction.Builder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class DatabaseDefinition {
    @Nullable
    private DatabaseConfig databaseConfig = ((DatabaseConfig) FlowManager.getConfig().databaseConfigMap().get(getAssociatedDatabaseClassFile()));
    private DatabaseHelperListener helperListener;
    private boolean isResetting = false;
    private final Map<Integer, List<Migration>> migrationMap = new HashMap();
    private final Map<Class<?>, ModelAdapter> modelAdapters = new HashMap();
    @Nullable
    private ModelNotifier modelNotifier;
    private final Map<String, Class<?>> modelTableNames = new HashMap();
    private final Map<Class<?>, ModelViewAdapter> modelViewAdapterMap = new LinkedHashMap();
    private OpenHelper openHelper;
    private final Map<Class<?>, QueryModelAdapter> queryModelAdapterMap = new LinkedHashMap();
    @NonNull
    private BaseTransactionManager transactionManager;

    public abstract boolean areConsistencyChecksEnabled();

    public abstract boolean backupEnabled();

    @NonNull
    public abstract Class<?> getAssociatedDatabaseClassFile();

    @NonNull
    public String getDatabaseExtensionName() {
        return "db";
    }

    @NonNull
    public abstract String getDatabaseName();

    public abstract int getDatabaseVersion();

    public abstract boolean isForeignKeysSupported();

    public abstract boolean isInMemory();

    public DatabaseDefinition() {
        if (this.databaseConfig != null) {
            for (TableConfig tableConfig : this.databaseConfig.tableConfigMap().values()) {
                ModelAdapter modelAdapter = (ModelAdapter) this.modelAdapters.get(tableConfig.tableClass());
                if (modelAdapter != null) {
                    if (tableConfig.listModelLoader() != null) {
                        modelAdapter.setListModelLoader(tableConfig.listModelLoader());
                    }
                    if (tableConfig.singleModelLoader() != null) {
                        modelAdapter.setSingleModelLoader(tableConfig.singleModelLoader());
                    }
                    if (tableConfig.modelSaver() != null) {
                        modelAdapter.setModelSaver(tableConfig.modelSaver());
                    }
                }
            }
            this.helperListener = this.databaseConfig.helperListener();
        }
        if (this.databaseConfig != null) {
            if (this.databaseConfig.transactionManagerCreator() != null) {
                this.transactionManager = this.databaseConfig.transactionManagerCreator().createManager(this);
                return;
            }
        }
        this.transactionManager = new DefaultTransactionManager(this);
    }

    protected <T> void addModelAdapter(ModelAdapter<T> modelAdapter, DatabaseHolder databaseHolder) {
        databaseHolder.putDatabaseForTable(modelAdapter.getModelClass(), this);
        this.modelTableNames.put(modelAdapter.getTableName(), modelAdapter.getModelClass());
        this.modelAdapters.put(modelAdapter.getModelClass(), modelAdapter);
    }

    protected <T> void addModelViewAdapter(ModelViewAdapter<T> modelViewAdapter, DatabaseHolder databaseHolder) {
        databaseHolder.putDatabaseForTable(modelViewAdapter.getModelClass(), this);
        this.modelViewAdapterMap.put(modelViewAdapter.getModelClass(), modelViewAdapter);
    }

    protected <T> void addQueryModelAdapter(QueryModelAdapter<T> queryModelAdapter, DatabaseHolder databaseHolder) {
        databaseHolder.putDatabaseForTable(queryModelAdapter.getModelClass(), this);
        this.queryModelAdapterMap.put(queryModelAdapter.getModelClass(), queryModelAdapter);
    }

    protected void addMigration(int i, Migration migration) {
        List list = (List) this.migrationMap.get(Integer.valueOf(i));
        if (list == null) {
            list = new ArrayList();
            this.migrationMap.put(Integer.valueOf(i), list);
        }
        list.add(migration);
    }

    @NonNull
    public List<Class<?>> getModelClasses() {
        return new ArrayList(this.modelAdapters.keySet());
    }

    @NonNull
    public BaseTransactionManager getTransactionManager() {
        return this.transactionManager;
    }

    @NonNull
    public List<ModelAdapter> getModelAdapters() {
        return new ArrayList(this.modelAdapters.values());
    }

    @Nullable
    public <T> ModelAdapter<T> getModelAdapterForTable(Class<T> cls) {
        return (ModelAdapter) this.modelAdapters.get(cls);
    }

    @Nullable
    public Class<?> getModelClassForName(String str) {
        return (Class) this.modelTableNames.get(str);
    }

    @NonNull
    public List<Class<?>> getModelViews() {
        return new ArrayList(this.modelViewAdapterMap.keySet());
    }

    @Nullable
    public <T> ModelViewAdapter<T> getModelViewAdapterForTable(Class<T> cls) {
        return (ModelViewAdapter) this.modelViewAdapterMap.get(cls);
    }

    @NonNull
    public List<ModelViewAdapter> getModelViewAdapters() {
        return new ArrayList(this.modelViewAdapterMap.values());
    }

    @NonNull
    public List<QueryModelAdapter> getModelQueryAdapters() {
        return new ArrayList(this.queryModelAdapterMap.values());
    }

    @Nullable
    public <T> QueryModelAdapter<T> getQueryModelAdapterForQueryClass(Class<T> cls) {
        return (QueryModelAdapter) this.queryModelAdapterMap.get(cls);
    }

    @NonNull
    public Map<Integer, List<Migration>> getMigrations() {
        return this.migrationMap;
    }

    @NonNull
    public synchronized OpenHelper getHelper() {
        if (this.openHelper == null) {
            DatabaseConfig databaseConfig = (DatabaseConfig) FlowManager.getConfig().databaseConfigMap().get(getAssociatedDatabaseClassFile());
            if (databaseConfig != null) {
                if (databaseConfig.helperCreator() != null) {
                    this.openHelper = databaseConfig.helperCreator().createHelper(this, this.helperListener);
                    this.openHelper.performRestoreFromBackup();
                }
            }
            this.openHelper = new FlowSQLiteOpenHelper(this, this.helperListener);
            this.openHelper.performRestoreFromBackup();
        }
        return this.openHelper;
    }

    @NonNull
    public DatabaseWrapper getWritableDatabase() {
        return getHelper().getDatabase();
    }

    @NonNull
    public ModelNotifier getModelNotifier() {
        if (this.modelNotifier == null) {
            DatabaseConfig databaseConfig = (DatabaseConfig) FlowManager.getConfig().databaseConfigMap().get(getAssociatedDatabaseClassFile());
            if (databaseConfig != null) {
                if (databaseConfig.modelNotifier() != null) {
                    this.modelNotifier = databaseConfig.modelNotifier();
                }
            }
            this.modelNotifier = new ContentResolverNotifier();
        }
        return this.modelNotifier;
    }

    @NonNull
    public Builder beginTransactionAsync(@NonNull ITransaction iTransaction) {
        return new Builder(iTransaction, this);
    }

    public void executeTransaction(@NonNull ITransaction iTransaction) {
        DatabaseWrapper writableDatabase = getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            iTransaction.execute(writableDatabase);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @NonNull
    public String getDatabaseFileName() {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(getDatabaseName());
        if (StringUtils.isNotNullOrEmpty(getDatabaseExtensionName())) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(".");
            stringBuilder3.append(getDatabaseExtensionName());
            stringBuilder = stringBuilder3.toString();
        } else {
            stringBuilder = "";
        }
        stringBuilder2.append(stringBuilder);
        return stringBuilder2.toString();
    }

    public void reset(@NonNull Context context) {
        if (!this.isResetting) {
            this.isResetting = true;
            getTransactionManager().stopQueue();
            getHelper().closeDB();
            for (ModelAdapter modelAdapter : this.modelAdapters.values()) {
                modelAdapter.closeInsertStatement();
                modelAdapter.closeCompiledStatement();
            }
            context.deleteDatabase(getDatabaseFileName());
            if (this.databaseConfig != null) {
                if (this.databaseConfig.transactionManagerCreator() != null) {
                    this.transactionManager = this.databaseConfig.transactionManagerCreator().createManager(this);
                    this.openHelper = null;
                    this.isResetting = null;
                    getHelper().getDatabase();
                }
            }
            this.transactionManager = new DefaultTransactionManager(this);
            this.openHelper = null;
            this.isResetting = null;
            getHelper().getDatabase();
        }
    }

    public void destroy(@NonNull Context context) {
        if (!this.isResetting) {
            this.isResetting = true;
            getTransactionManager().stopQueue();
            getHelper().closeDB();
            context.deleteDatabase(getDatabaseFileName());
            this.openHelper = null;
            this.isResetting = null;
        }
    }

    public boolean isDatabaseIntegrityOk() {
        return getHelper().isDatabaseIntegrityOk();
    }

    public void backupDatabase() {
        getHelper().backupDB();
    }
}
