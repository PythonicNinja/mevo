package com.raizlabs.android.dbflow.config;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.runtime.BaseTransactionManager;
import com.raizlabs.android.dbflow.runtime.ModelNotifier;
import com.raizlabs.android.dbflow.structure.database.DatabaseHelperListener;
import com.raizlabs.android.dbflow.structure.database.OpenHelper;
import java.util.HashMap;
import java.util.Map;

public final class DatabaseConfig {
    private final Class<?> databaseClass;
    private final DatabaseHelperListener helperListener;
    private final ModelNotifier modelNotifier;
    private final OpenHelperCreator openHelperCreator;
    private final Map<Class<?>, TableConfig> tableConfigMap;
    private final TransactionManagerCreator transactionManagerCreator;

    public static final class Builder {
        final Class<?> databaseClass;
        DatabaseHelperListener helperListener;
        ModelNotifier modelNotifier;
        OpenHelperCreator openHelperCreator;
        final Map<Class<?>, TableConfig> tableConfigMap = new HashMap();
        TransactionManagerCreator transactionManagerCreator;

        public Builder(@NonNull Class<?> cls) {
            this.databaseClass = cls;
        }

        public Builder transactionManagerCreator(TransactionManagerCreator transactionManagerCreator) {
            this.transactionManagerCreator = transactionManagerCreator;
            return this;
        }

        public Builder helperListener(DatabaseHelperListener databaseHelperListener) {
            this.helperListener = databaseHelperListener;
            return this;
        }

        public Builder addTableConfig(TableConfig<?> tableConfig) {
            this.tableConfigMap.put(tableConfig.tableClass(), tableConfig);
            return this;
        }

        public Builder modelNotifier(ModelNotifier modelNotifier) {
            this.modelNotifier = modelNotifier;
            return this;
        }

        public Builder openHelper(OpenHelperCreator openHelperCreator) {
            this.openHelperCreator = openHelperCreator;
            return this;
        }

        public DatabaseConfig build() {
            return new DatabaseConfig(this);
        }
    }

    public interface OpenHelperCreator {
        OpenHelper createHelper(DatabaseDefinition databaseDefinition, DatabaseHelperListener databaseHelperListener);
    }

    public interface TransactionManagerCreator {
        BaseTransactionManager createManager(DatabaseDefinition databaseDefinition);
    }

    DatabaseConfig(Builder builder) {
        this.openHelperCreator = builder.openHelperCreator;
        this.databaseClass = builder.databaseClass;
        this.transactionManagerCreator = builder.transactionManagerCreator;
        this.helperListener = builder.helperListener;
        this.tableConfigMap = builder.tableConfigMap;
        this.modelNotifier = builder.modelNotifier;
    }

    @Nullable
    public OpenHelperCreator helperCreator() {
        return this.openHelperCreator;
    }

    @Nullable
    public DatabaseHelperListener helperListener() {
        return this.helperListener;
    }

    @NonNull
    public Class<?> databaseClass() {
        return this.databaseClass;
    }

    @Nullable
    public TransactionManagerCreator transactionManagerCreator() {
        return this.transactionManagerCreator;
    }

    @Nullable
    public ModelNotifier modelNotifier() {
        return this.modelNotifier;
    }

    @NonNull
    public Map<Class<?>, TableConfig> tableConfigMap() {
        return this.tableConfigMap;
    }

    @Nullable
    public <TModel> TableConfig<TModel> getTableConfigForTable(Class<TModel> cls) {
        return (TableConfig) tableConfigMap().get(cls);
    }
}
