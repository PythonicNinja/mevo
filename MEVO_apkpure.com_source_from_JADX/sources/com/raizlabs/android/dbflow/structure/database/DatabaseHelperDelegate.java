package com.raizlabs.android.dbflow.structure.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mevo.app.data.model.Bike;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelperDelegate extends BaseDatabaseHelper {
    public static final String TEMP_DB_NAME = "temp-";
    @Nullable
    private final OpenHelper backupHelper;
    private DatabaseHelperListener databaseHelperListener;

    /* renamed from: com.raizlabs.android.dbflow.structure.database.DatabaseHelperDelegate$1 */
    class C08781 implements ITransaction {
        C08781() {
        }

        public void execute(DatabaseWrapper databaseWrapper) {
            databaseWrapper = FlowManager.getContext();
            File databasePath = databaseWrapper.getDatabasePath(DatabaseHelperDelegate.this.getTempDbFileName());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("temp--2-");
            stringBuilder.append(DatabaseHelperDelegate.this.getDatabaseDefinition().getDatabaseFileName());
            File databasePath2 = databaseWrapper.getDatabasePath(stringBuilder.toString());
            if (databasePath2.exists()) {
                databasePath2.delete();
            }
            databasePath.renameTo(databasePath2);
            if (databasePath.exists()) {
                databasePath.delete();
            }
            databaseWrapper = databaseWrapper.getDatabasePath(DatabaseHelperDelegate.this.getDatabaseDefinition().getDatabaseFileName());
            try {
                databasePath.getParentFile().mkdirs();
                DatabaseHelperDelegate.this.writeDB(databasePath, new FileInputStream(databaseWrapper));
                databasePath2.delete();
            } catch (DatabaseWrapper databaseWrapper2) {
                FlowLog.logError(databaseWrapper2);
            }
        }
    }

    public static String getTempDbFileName(DatabaseDefinition databaseDefinition) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TEMP_DB_NAME);
        stringBuilder.append(databaseDefinition.getDatabaseName());
        stringBuilder.append(".db");
        return stringBuilder.toString();
    }

    public DatabaseHelperDelegate(DatabaseHelperListener databaseHelperListener, DatabaseDefinition databaseDefinition, @Nullable OpenHelper openHelper) {
        super(databaseDefinition);
        this.databaseHelperListener = databaseHelperListener;
        this.backupHelper = openHelper;
    }

    public void performRestoreFromBackup() {
        movePrepackagedDB(getDatabaseDefinition().getDatabaseFileName(), getDatabaseDefinition().getDatabaseFileName());
        if (!getDatabaseDefinition().backupEnabled()) {
            return;
        }
        if (this.backupHelper == null) {
            throw new IllegalStateException("the passed backup helper was null, even though backup is enabled. Ensure that its passed in.");
        }
        restoreDatabase(getTempDbFileName(), getDatabaseDefinition().getDatabaseFileName());
        this.backupHelper.getDatabase();
    }

    public void setDatabaseHelperListener(DatabaseHelperListener databaseHelperListener) {
        this.databaseHelperListener = databaseHelperListener;
    }

    public void onCreate(@NonNull DatabaseWrapper databaseWrapper) {
        if (this.databaseHelperListener != null) {
            this.databaseHelperListener.onCreate(databaseWrapper);
        }
        super.onCreate(databaseWrapper);
    }

    public void onUpgrade(@NonNull DatabaseWrapper databaseWrapper, int i, int i2) {
        if (this.databaseHelperListener != null) {
            this.databaseHelperListener.onUpgrade(databaseWrapper, i, i2);
        }
        super.onUpgrade(databaseWrapper, i, i2);
    }

    public void onOpen(@NonNull DatabaseWrapper databaseWrapper) {
        if (this.databaseHelperListener != null) {
            this.databaseHelperListener.onOpen(databaseWrapper);
        }
        super.onOpen(databaseWrapper);
    }

    public void onDowngrade(@NonNull DatabaseWrapper databaseWrapper, int i, int i2) {
        if (this.databaseHelperListener != null) {
            this.databaseHelperListener.onDowngrade(databaseWrapper, i, i2);
        }
        super.onDowngrade(databaseWrapper, i, i2);
    }

    private String getTempDbFileName() {
        return getTempDbFileName(getDatabaseDefinition());
    }

    public void movePrepackagedDB(String str, String str2) {
        str = FlowManager.getContext().getDatabasePath(str);
        if (!str.exists() || (getDatabaseDefinition().areConsistencyChecksEnabled() && !(getDatabaseDefinition().areConsistencyChecksEnabled() && isDatabaseIntegrityOk(getWritableDatabase())))) {
            str.getParentFile().mkdirs();
            try {
                File databasePath = FlowManager.getContext().getDatabasePath(getTempDbFileName());
                if (!databasePath.exists() || (getDatabaseDefinition().backupEnabled() && !(getDatabaseDefinition().backupEnabled() && this.backupHelper != null && isDatabaseIntegrityOk(this.backupHelper.getDatabase())))) {
                    str2 = FlowManager.getContext().getAssets().open(str2);
                } else {
                    str2 = new FileInputStream(databasePath);
                }
                writeDB(str, str2);
            } catch (String str3) {
                FlowLog.log(Level.W, "Failed to open file", str3);
            }
        }
    }

    public boolean isDatabaseIntegrityOk() {
        return isDatabaseIntegrityOk(getWritableDatabase());
    }

    public boolean isDatabaseIntegrityOk(DatabaseWrapper databaseWrapper) {
        Throwable th;
        try {
            databaseWrapper = databaseWrapper.compileStatement("PRAGMA quick_check(1)");
            try {
                boolean z;
                String simpleQueryForString = databaseWrapper.simpleQueryForString();
                if (simpleQueryForString.equalsIgnoreCase(Bike.STATUS_OK)) {
                    z = true;
                } else {
                    Level level = Level.E;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("PRAGMA integrity_check on ");
                    stringBuilder.append(getDatabaseDefinition().getDatabaseName());
                    stringBuilder.append(" returned: ");
                    stringBuilder.append(simpleQueryForString);
                    FlowLog.log(level, stringBuilder.toString());
                    z = false;
                    if (getDatabaseDefinition().backupEnabled()) {
                        z = restoreBackUp();
                    }
                }
                if (databaseWrapper != null) {
                    databaseWrapper.close();
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                if (databaseWrapper != null) {
                    databaseWrapper.close();
                }
                throw th;
            }
        } catch (DatabaseWrapper databaseWrapper2) {
            th = databaseWrapper2;
            databaseWrapper2 = null;
            if (databaseWrapper2 != null) {
                databaseWrapper2.close();
            }
            throw th;
        }
    }

    public boolean restoreBackUp() {
        Context context = FlowManager.getContext();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TEMP_DB_NAME);
        stringBuilder.append(getDatabaseDefinition().getDatabaseName());
        File databasePath = context.getDatabasePath(stringBuilder.toString());
        File databasePath2 = FlowManager.getContext().getDatabasePath(getDatabaseDefinition().getDatabaseName());
        if (databasePath2.delete()) {
            try {
                writeDB(databasePath2, new FileInputStream(databasePath));
            } catch (Throwable e) {
                FlowLog.logError(e);
                return false;
            }
        }
        FlowLog.log(Level.E, "Failed to delete DB");
        return true;
    }

    private void writeDB(File file, InputStream inputStream) throws IOException {
        OutputStream fileOutputStream = new FileOutputStream(file);
        file = new byte[1024];
        while (true) {
            int read = inputStream.read(file);
            if (read > 0) {
                fileOutputStream.write(file, 0, read);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
                return;
            }
        }
    }

    public void restoreDatabase(String str, String str2) {
        str = FlowManager.getContext().getDatabasePath(str);
        if (!str.exists()) {
            str.getParentFile().mkdirs();
            try {
                File databasePath = FlowManager.getContext().getDatabasePath(getDatabaseDefinition().getDatabaseFileName());
                if (databasePath.exists() && getDatabaseDefinition().backupEnabled() && this.backupHelper != null && isDatabaseIntegrityOk(this.backupHelper.getDatabase())) {
                    str2 = new FileInputStream(databasePath);
                } else {
                    str2 = FlowManager.getContext().getAssets().open(str2);
                }
                writeDB(str, str2);
            } catch (String str3) {
                FlowLog.logError(str3);
            }
        }
    }

    public void backupDB() {
        if (getDatabaseDefinition().backupEnabled()) {
            if (getDatabaseDefinition().areConsistencyChecksEnabled()) {
                getDatabaseDefinition().beginTransactionAsync(new C08781()).build().execute();
                return;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Backups are not enabled for : ");
        stringBuilder.append(getDatabaseDefinition().getDatabaseName());
        stringBuilder.append(". Please consider adding both backupEnabled and consistency checks enabled to the Database annotation");
        throw new IllegalStateException(stringBuilder.toString());
    }

    public DatabaseWrapper getWritableDatabase() {
        return getDatabaseDefinition().getWritableDatabase();
    }
}
