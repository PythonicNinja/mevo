package com.raizlabs.android.dbflow.config;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.FlowConfig.Builder;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.raizlabs.android.dbflow.runtime.ModelNotifier;
import com.raizlabs.android.dbflow.runtime.TableNotifierRegister;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.migration.Migration;
import com.raizlabs.android.dbflow.structure.InstanceAdapter;
import com.raizlabs.android.dbflow.structure.InvalidDBConfiguration;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.ModelViewAdapter;
import com.raizlabs.android.dbflow.structure.QueryModelAdapter;
import com.raizlabs.android.dbflow.structure.RetrievalAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FlowManager {
    private static final String DEFAULT_DATABASE_HOLDER_CLASSNAME;
    private static final String DEFAULT_DATABASE_HOLDER_NAME = "GeneratedDatabaseHolder";
    private static final String DEFAULT_DATABASE_HOLDER_PACKAGE_NAME = FlowManager.class.getPackage().getName();
    static FlowConfig config;
    private static GlobalDatabaseHolder globalDatabaseHolder = new GlobalDatabaseHolder();
    private static HashSet<Class<? extends DatabaseHolder>> loadedModules = new HashSet();

    public static class ModuleNotFoundException extends RuntimeException {
        public ModuleNotFoundException(String str) {
            super(str);
        }

        public ModuleNotFoundException(String str, Throwable th) {
            super(str, th);
        }

        public ModuleNotFoundException(Throwable th) {
            super(th);
        }
    }

    private static class GlobalDatabaseHolder extends DatabaseHolder {
        private boolean initialized;

        private GlobalDatabaseHolder() {
            this.initialized = false;
        }

        public void add(DatabaseHolder databaseHolder) {
            this.databaseDefinitionMap.putAll(databaseHolder.databaseDefinitionMap);
            this.databaseNameMap.putAll(databaseHolder.databaseNameMap);
            this.typeConverters.putAll(databaseHolder.typeConverters);
            this.databaseClassLookupMap.putAll(databaseHolder.databaseClassLookupMap);
            this.initialized = true;
        }

        public boolean isInitialized() {
            return this.initialized;
        }
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DEFAULT_DATABASE_HOLDER_PACKAGE_NAME);
        stringBuilder.append(".");
        stringBuilder.append(DEFAULT_DATABASE_HOLDER_NAME);
        DEFAULT_DATABASE_HOLDER_CLASSNAME = stringBuilder.toString();
    }

    @NonNull
    public static String getTableName(Class<?> cls) {
        ModelAdapter modelAdapterOrNull = getModelAdapterOrNull(cls);
        if (modelAdapterOrNull != null) {
            return modelAdapterOrNull.getTableName();
        }
        ModelViewAdapter modelViewAdapterOrNull = getModelViewAdapterOrNull(cls);
        if (modelViewAdapterOrNull != null) {
            return modelViewAdapterOrNull.getViewName();
        }
        throwCannotFindAdapter("ModelAdapter/ModelViewAdapter", cls);
        return null;
    }

    public static Class<?> getTableClassForName(String str, String str2) {
        DatabaseDefinition database = getDatabase(str);
        Class<?> modelClassForName = database.getModelClassForName(str2);
        if (modelClassForName == null) {
            modelClassForName = database.getModelClassForName(QueryBuilder.quote(str2));
            if (modelClassForName == null) {
                throw new IllegalArgumentException(String.format("The specified table %1s was not found. Did you forget to add the @Table annotation and point it to %1s?", new Object[]{str2, str}));
            }
        }
        return modelClassForName;
    }

    @NonNull
    public static DatabaseDefinition getDatabaseForTable(Class<?> cls) {
        checkDatabaseHolder();
        DatabaseDefinition databaseForTable = globalDatabaseHolder.getDatabaseForTable(cls);
        if (databaseForTable != null) {
            return databaseForTable;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Model object: ");
        stringBuilder.append(cls.getName());
        stringBuilder.append(" is not registered with a Database. Did you forget an annotation?");
        throw new InvalidDBConfiguration(stringBuilder.toString());
    }

    @NonNull
    public static DatabaseDefinition getDatabase(Class<?> cls) {
        checkDatabaseHolder();
        DatabaseDefinition database = globalDatabaseHolder.getDatabase((Class) cls);
        if (database != null) {
            return database;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Database: ");
        stringBuilder.append(cls.getName());
        stringBuilder.append(" is not a registered Database. Did you forget the @Database annotation?");
        throw new InvalidDBConfiguration(stringBuilder.toString());
    }

    @NonNull
    public static DatabaseWrapper getWritableDatabaseForTable(Class<?> cls) {
        return getDatabaseForTable(cls).getWritableDatabase();
    }

    @NonNull
    public static DatabaseDefinition getDatabase(String str) {
        checkDatabaseHolder();
        DatabaseDefinition database = globalDatabaseHolder.getDatabase(str);
        if (database != null) {
            return database;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The specified database");
        stringBuilder.append(str);
        stringBuilder.append(" was not found. Did you forget the @Database annotation?");
        throw new InvalidDBConfiguration(stringBuilder.toString());
    }

    @NonNull
    public static DatabaseWrapper getWritableDatabase(String str) {
        return getDatabase(str).getWritableDatabase();
    }

    @NonNull
    public static DatabaseWrapper getWritableDatabase(Class<?> cls) {
        return getDatabase((Class) cls).getWritableDatabase();
    }

    public static void initModule(Class<? extends DatabaseHolder> cls) {
        loadDatabaseHolder(cls);
    }

    public static FlowConfig getConfig() {
        if (config != null) {
            return config;
        }
        throw new IllegalStateException("Configuration is not initialized. Please call init(FlowConfig) in your application class.");
    }

    protected static void loadDatabaseHolder(Class<? extends DatabaseHolder> cls) {
        if (!loadedModules.contains(cls)) {
            try {
                DatabaseHolder databaseHolder = (DatabaseHolder) cls.newInstance();
                if (databaseHolder != null) {
                    globalDatabaseHolder.add(databaseHolder);
                    loadedModules.add(cls);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Cannot load ");
                stringBuilder.append(cls);
                ModuleNotFoundException moduleNotFoundException = new ModuleNotFoundException(stringBuilder.toString(), th);
            }
        }
    }

    public static void reset() {
        for (Entry value : globalDatabaseHolder.databaseClassLookupMap.entrySet()) {
            ((DatabaseDefinition) value.getValue()).reset(getContext());
        }
        globalDatabaseHolder.reset();
        loadedModules.clear();
    }

    @NonNull
    public static Context getContext() {
        if (config != null) {
            return config.getContext();
        }
        throw new IllegalStateException("You must provide a valid FlowConfig instance. We recommend calling init() in your application class.");
    }

    public static void init(@NonNull Context context) {
        init(new Builder(context).build());
    }

    public static void init(@android.support.annotation.NonNull com.raizlabs.android.dbflow.config.FlowConfig r2) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        config = r2;
        r0 = DEFAULT_DATABASE_HOLDER_CLASSNAME;	 Catch:{ ModuleNotFoundException -> 0x0014, ClassNotFoundException -> 0x000c }
        r0 = java.lang.Class.forName(r0);	 Catch:{ ModuleNotFoundException -> 0x0014, ClassNotFoundException -> 0x000c }
        loadDatabaseHolder(r0);	 Catch:{ ModuleNotFoundException -> 0x0014, ClassNotFoundException -> 0x000c }
        goto L_0x001e;
    L_0x000c:
        r0 = com.raizlabs.android.dbflow.config.FlowLog.Level.W;
        r1 = "Could not find the default GeneratedDatabaseHolder";
        com.raizlabs.android.dbflow.config.FlowLog.log(r0, r1);
        goto L_0x001e;
    L_0x0014:
        r0 = move-exception;
        r1 = com.raizlabs.android.dbflow.config.FlowLog.Level.W;
        r0 = r0.getMessage();
        com.raizlabs.android.dbflow.config.FlowLog.log(r1, r0);
    L_0x001e:
        r0 = r2.databaseHolders();
        if (r0 == 0) goto L_0x0046;
    L_0x0024:
        r0 = r2.databaseHolders();
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0046;
    L_0x002e:
        r0 = r2.databaseHolders();
        r0 = r0.iterator();
    L_0x0036:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x0046;
    L_0x003c:
        r1 = r0.next();
        r1 = (java.lang.Class) r1;
        loadDatabaseHolder(r1);
        goto L_0x0036;
    L_0x0046:
        r2 = r2.openDatabasesOnInit();
        if (r2 == 0) goto L_0x0066;
    L_0x004c:
        r2 = globalDatabaseHolder;
        r2 = r2.getDatabaseDefinitions();
        r2 = r2.iterator();
    L_0x0056:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x0066;
    L_0x005c:
        r0 = r2.next();
        r0 = (com.raizlabs.android.dbflow.config.DatabaseDefinition) r0;
        r0.getWritableDatabase();
        goto L_0x0056;
    L_0x0066:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.raizlabs.android.dbflow.config.FlowManager.init(com.raizlabs.android.dbflow.config.FlowConfig):void");
    }

    public static TypeConverter getTypeConverterForClass(Class<?> cls) {
        checkDatabaseHolder();
        return globalDatabaseHolder.getTypeConverterForClass(cls);
    }

    public static synchronized void destroy() {
        synchronized (FlowManager.class) {
            for (Entry value : globalDatabaseHolder.databaseClassLookupMap.entrySet()) {
                ((DatabaseDefinition) value.getValue()).destroy(getContext());
            }
            config = null;
            globalDatabaseHolder = new GlobalDatabaseHolder();
            loadedModules.clear();
        }
    }

    @NonNull
    public static <TModel> InstanceAdapter<TModel> getInstanceAdapter(Class<TModel> cls) {
        InstanceAdapter<TModel> modelAdapterOrNull = getModelAdapterOrNull(cls);
        if (modelAdapterOrNull == null) {
            modelAdapterOrNull = getModelViewAdapterOrNull(cls);
            if (modelAdapterOrNull == null) {
                modelAdapterOrNull = getQueryModelAdapterOrNull(cls);
            }
        }
        if (modelAdapterOrNull == null) {
            throwCannotFindAdapter("InstanceAdapter", cls);
        }
        return modelAdapterOrNull;
    }

    @NonNull
    public static <TModel> RetrievalAdapter<TModel> getRetrievalAdapter(Class<TModel> cls) {
        RetrievalAdapter<TModel> modelAdapterOrNull = getModelAdapterOrNull(cls);
        if (modelAdapterOrNull == null) {
            modelAdapterOrNull = getModelViewAdapterOrNull(cls);
            if (modelAdapterOrNull == null) {
                modelAdapterOrNull = getQueryModelAdapterOrNull(cls);
            }
        }
        if (modelAdapterOrNull == null) {
            throwCannotFindAdapter("RetrievalAdapter", cls);
        }
        return modelAdapterOrNull;
    }

    @NonNull
    public static <TModel> ModelAdapter<TModel> getModelAdapter(Class<TModel> cls) {
        ModelAdapter<TModel> modelAdapterOrNull = getModelAdapterOrNull(cls);
        if (modelAdapterOrNull == null) {
            throwCannotFindAdapter("ModelAdapter", cls);
        }
        return modelAdapterOrNull;
    }

    @NonNull
    public static <TModelView> ModelViewAdapter<TModelView> getModelViewAdapter(Class<TModelView> cls) {
        ModelViewAdapter<TModelView> modelViewAdapterOrNull = getModelViewAdapterOrNull(cls);
        if (modelViewAdapterOrNull == null) {
            throwCannotFindAdapter("ModelViewAdapter", cls);
        }
        return modelViewAdapterOrNull;
    }

    @NonNull
    public static <TQueryModel> QueryModelAdapter<TQueryModel> getQueryModelAdapter(Class<TQueryModel> cls) {
        QueryModelAdapter<TQueryModel> queryModelAdapterOrNull = getQueryModelAdapterOrNull(cls);
        if (queryModelAdapterOrNull == null) {
            throwCannotFindAdapter("QueryModelAdapter", cls);
        }
        return queryModelAdapterOrNull;
    }

    @NonNull
    public static ModelNotifier getModelNotifierForTable(Class<?> cls) {
        return getDatabaseForTable(cls).getModelNotifier();
    }

    @NonNull
    public static TableNotifierRegister newRegisterForTable(Class<?> cls) {
        return getModelNotifierForTable(cls).newRegister();
    }

    @Nullable
    private static <T> ModelAdapter<T> getModelAdapterOrNull(Class<T> cls) {
        return getDatabaseForTable(cls).getModelAdapterForTable(cls);
    }

    @Nullable
    private static <T> ModelViewAdapter<T> getModelViewAdapterOrNull(Class<T> cls) {
        return getDatabaseForTable(cls).getModelViewAdapterForTable(cls);
    }

    @Nullable
    private static <T> QueryModelAdapter<T> getQueryModelAdapterOrNull(Class<T> cls) {
        return getDatabaseForTable(cls).getQueryModelAdapterForQueryClass(cls);
    }

    static Map<Integer, List<Migration>> getMigrations(String str) {
        return getDatabase(str).getMigrations();
    }

    public static boolean isDatabaseIntegrityOk(String str) {
        return getDatabase(str).getHelper().isDatabaseIntegrityOk();
    }

    private static void throwCannotFindAdapter(String str, Class<?> cls) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cannot find ");
        stringBuilder.append(str);
        stringBuilder.append(" for ");
        stringBuilder.append(cls);
        stringBuilder.append(". Ensure the class is annotated with proper annotation.");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    private static void checkDatabaseHolder() {
        if (!globalDatabaseHolder.isInitialized()) {
            throw new IllegalStateException("The global database holder is not initialized. Ensure you call FlowManager.init() before accessing the database.");
        }
    }
}
