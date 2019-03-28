package com.raizlabs.android.dbflow.structure.database;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.config.NaturalOrderComparator;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import com.raizlabs.android.dbflow.sql.migration.Migration;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.ModelViewAdapter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDatabaseHelper {
    public static final String MIGRATION_PATH = "migrations";
    private final DatabaseDefinition databaseDefinition;

    public BaseDatabaseHelper(@NonNull DatabaseDefinition databaseDefinition) {
        this.databaseDefinition = databaseDefinition;
    }

    @NonNull
    public DatabaseDefinition getDatabaseDefinition() {
        return this.databaseDefinition;
    }

    public void onCreate(@NonNull DatabaseWrapper databaseWrapper) {
        checkForeignKeySupport(databaseWrapper);
        executeCreations(databaseWrapper);
        executeMigrations(databaseWrapper, -1, databaseWrapper.getVersion());
    }

    public void onUpgrade(@NonNull DatabaseWrapper databaseWrapper, int i, int i2) {
        checkForeignKeySupport(databaseWrapper);
        executeCreations(databaseWrapper);
        executeMigrations(databaseWrapper, i, i2);
    }

    public void onOpen(@NonNull DatabaseWrapper databaseWrapper) {
        checkForeignKeySupport(databaseWrapper);
    }

    public void onDowngrade(@NonNull DatabaseWrapper databaseWrapper, int i, int i2) {
        checkForeignKeySupport(databaseWrapper);
    }

    protected void checkForeignKeySupport(@NonNull DatabaseWrapper databaseWrapper) {
        if (this.databaseDefinition.isForeignKeysSupported()) {
            databaseWrapper.execSQL("PRAGMA foreign_keys=ON;");
            FlowLog.log(Level.I, "Foreign Keys supported. Enabling foreign key features.");
        }
    }

    protected void executeCreations(@NonNull DatabaseWrapper databaseWrapper) {
        try {
            databaseWrapper.beginTransaction();
            for (ModelAdapter creationQuery : this.databaseDefinition.getModelAdapters()) {
                databaseWrapper.execSQL(creationQuery.getCreationQuery());
            }
            for (ModelViewAdapter modelViewAdapter : this.databaseDefinition.getModelViewAdapters()) {
                try {
                    databaseWrapper.execSQL(new QueryBuilder().append("CREATE VIEW IF NOT EXISTS").appendSpaceSeparated(modelViewAdapter.getViewName()).append("AS ").append(modelViewAdapter.getCreationQuery()).getQuery());
                } catch (Throwable e) {
                    FlowLog.logError(e);
                }
            }
            databaseWrapper.setTransactionSuccessful();
            databaseWrapper.endTransaction();
        } catch (Throwable e2) {
            FlowLog.logError(e2);
        } catch (Throwable th) {
            databaseWrapper.endTransaction();
        }
    }

    protected void executeMigrations(@NonNull DatabaseWrapper databaseWrapper, int i, int i2) {
        try {
            Level level;
            StringBuilder stringBuilder;
            AssetManager assets = FlowManager.getContext().getAssets();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("migrations/");
            stringBuilder2.append(this.databaseDefinition.getDatabaseName());
            List<String> asList = Arrays.asList(assets.list(stringBuilder2.toString()));
            Collections.sort(asList, new NaturalOrderComparator());
            Map hashMap = new HashMap();
            for (String str : asList) {
                try {
                    Integer valueOf = Integer.valueOf(str.replace(".sql", ""));
                    List list = (List) hashMap.get(valueOf);
                    if (list == null) {
                        list = new ArrayList();
                        hashMap.put(valueOf, list);
                    }
                    list.add(str);
                } catch (Throwable e) {
                    level = Level.W;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Skipping invalidly named file: ");
                    stringBuilder.append(str);
                    FlowLog.log(level, stringBuilder.toString(), e);
                }
            }
            Map migrations = this.databaseDefinition.getMigrations();
            databaseWrapper.beginTransaction();
            for (i++; i <= i2; i++) {
                List<String> list2 = (List) hashMap.get(Integer.valueOf(i));
                if (list2 != null) {
                    for (String str2 : list2) {
                        executeSqlScript(databaseWrapper, str2);
                        level = Level.I;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(str2);
                        stringBuilder.append(" executed successfully.");
                        FlowLog.log(level, stringBuilder.toString());
                    }
                }
                List<Migration> list3 = (List) migrations.get(Integer.valueOf(i));
                if (list3 != null) {
                    for (Migration migration : list3) {
                        migration.onPreMigrate();
                        migration.migrate(databaseWrapper);
                        migration.onPostMigrate();
                        level = Level.I;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(migration.getClass());
                        stringBuilder.append(" executed successfully.");
                        FlowLog.log(level, stringBuilder.toString());
                    }
                }
            }
            databaseWrapper.setTransactionSuccessful();
            databaseWrapper.endTransaction();
        } catch (DatabaseWrapper databaseWrapper2) {
            FlowLog.log(Level.E, "Failed to execute migrations.", databaseWrapper2);
        } catch (Throwable th) {
            databaseWrapper2.endTransaction();
        }
    }

    private void executeSqlScript(@NonNull DatabaseWrapper databaseWrapper, @NonNull String str) {
        StringBuilder stringBuilder;
        try {
            AssetManager assets = FlowManager.getContext().getAssets();
            stringBuilder = new StringBuilder();
            stringBuilder.append("migrations/");
            stringBuilder.append(getDatabaseDefinition().getDatabaseName());
            stringBuilder.append(Operation.DIVISION);
            stringBuilder.append(str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assets.open(stringBuilder.toString())));
            String str2 = ";";
            String str3 = "--";
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                readLine = readLine.trim();
                boolean endsWith = readLine.endsWith(str2);
                if (!readLine.startsWith(str3)) {
                    if (endsWith) {
                        readLine = readLine.substring(0, readLine.length() - str2.length());
                    }
                    stringBuffer.append(" ");
                    stringBuffer.append(readLine);
                    if (endsWith) {
                        databaseWrapper.execSQL(stringBuffer.toString());
                        stringBuffer = new StringBuffer();
                    }
                }
            }
            str2 = stringBuffer.toString();
            if (str2.trim().length() > 0) {
                databaseWrapper.execSQL(str2);
            }
        } catch (DatabaseWrapper databaseWrapper2) {
            Level level = Level.E;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to execute ");
            stringBuilder.append(str);
            FlowLog.log(level, stringBuilder.toString(), databaseWrapper2);
        }
    }
}
