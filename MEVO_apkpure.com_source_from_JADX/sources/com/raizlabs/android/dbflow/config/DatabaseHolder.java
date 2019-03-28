package com.raizlabs.android.dbflow.config;

import com.raizlabs.android.dbflow.converter.TypeConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DatabaseHolder {
    protected final Map<Class<?>, DatabaseDefinition> databaseClassLookupMap = new HashMap();
    protected final Map<Class<?>, DatabaseDefinition> databaseDefinitionMap = new HashMap();
    protected final Map<String, DatabaseDefinition> databaseNameMap = new HashMap();
    protected final Map<Class<?>, TypeConverter> typeConverters = new HashMap();

    public TypeConverter getTypeConverterForClass(Class<?> cls) {
        return (TypeConverter) this.typeConverters.get(cls);
    }

    public DatabaseDefinition getDatabaseForTable(Class<?> cls) {
        return (DatabaseDefinition) this.databaseDefinitionMap.get(cls);
    }

    public DatabaseDefinition getDatabase(Class<?> cls) {
        return (DatabaseDefinition) this.databaseClassLookupMap.get(cls);
    }

    public DatabaseDefinition getDatabase(String str) {
        return (DatabaseDefinition) this.databaseNameMap.get(str);
    }

    public void putDatabaseForTable(Class<?> cls, DatabaseDefinition databaseDefinition) {
        this.databaseDefinitionMap.put(cls, databaseDefinition);
        this.databaseNameMap.put(databaseDefinition.getDatabaseName(), databaseDefinition);
        this.databaseClassLookupMap.put(databaseDefinition.getAssociatedDatabaseClassFile(), databaseDefinition);
    }

    public void reset() {
        this.databaseDefinitionMap.clear();
        this.databaseNameMap.clear();
        this.databaseClassLookupMap.clear();
        this.typeConverters.clear();
    }

    public List<DatabaseDefinition> getDatabaseDefinitions() {
        return new ArrayList(this.databaseNameMap.values());
    }
}
