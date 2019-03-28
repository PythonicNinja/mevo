package com.raizlabs.android.dbflow.structure.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class AndroidDatabaseStatement extends BaseDatabaseStatement {
    private final SQLiteDatabase database;
    private final SQLiteStatement statement;

    public static AndroidDatabaseStatement from(@NonNull SQLiteStatement sQLiteStatement, @NonNull SQLiteDatabase sQLiteDatabase) {
        return new AndroidDatabaseStatement(sQLiteStatement, sQLiteDatabase);
    }

    AndroidDatabaseStatement(@NonNull SQLiteStatement sQLiteStatement, @NonNull SQLiteDatabase sQLiteDatabase) {
        this.statement = sQLiteStatement;
        this.database = sQLiteDatabase;
    }

    @NonNull
    public SQLiteStatement getStatement() {
        return this.statement;
    }

    public long executeUpdateDelete() {
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
        r6 = this;
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 0;
        r3 = 11;
        if (r0 < r3) goto L_0x0010;
    L_0x0008:
        r0 = r6.statement;
        r0 = r0.executeUpdateDelete();
        r1 = (long) r0;
        goto L_0x004d;
    L_0x0010:
        r0 = r6.statement;
        r0.execute();
        r0 = 0;
        r3 = r6.database;	 Catch:{ SQLException -> 0x0049, all -> 0x0040 }
        r4 = "SELECT changes() AS affected_row_count";	 Catch:{ SQLException -> 0x0049, all -> 0x0040 }
        r3 = r3.rawQuery(r4, r0);	 Catch:{ SQLException -> 0x0049, all -> 0x0040 }
        if (r3 == 0) goto L_0x003a;
    L_0x0020:
        r0 = r3.getCount();	 Catch:{ SQLException -> 0x004a, all -> 0x0038 }
        if (r0 <= 0) goto L_0x003a;	 Catch:{ SQLException -> 0x004a, all -> 0x0038 }
    L_0x0026:
        r0 = r3.moveToFirst();	 Catch:{ SQLException -> 0x004a, all -> 0x0038 }
        if (r0 == 0) goto L_0x003a;	 Catch:{ SQLException -> 0x004a, all -> 0x0038 }
    L_0x002c:
        r0 = "affected_row_count";	 Catch:{ SQLException -> 0x004a, all -> 0x0038 }
        r0 = r3.getColumnIndex(r0);	 Catch:{ SQLException -> 0x004a, all -> 0x0038 }
        r4 = r3.getLong(r0);	 Catch:{ SQLException -> 0x004a, all -> 0x0038 }
        r1 = r4;
        goto L_0x003a;
    L_0x0038:
        r0 = move-exception;
        goto L_0x0043;
    L_0x003a:
        if (r3 == 0) goto L_0x004d;
    L_0x003c:
        r3.close();
        goto L_0x004d;
    L_0x0040:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
    L_0x0043:
        if (r3 == 0) goto L_0x0048;
    L_0x0045:
        r3.close();
    L_0x0048:
        throw r0;
    L_0x0049:
        r3 = r0;
    L_0x004a:
        if (r3 == 0) goto L_0x004d;
    L_0x004c:
        goto L_0x003c;
    L_0x004d:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.raizlabs.android.dbflow.structure.database.AndroidDatabaseStatement.executeUpdateDelete():long");
    }

    public void execute() {
        this.statement.execute();
    }

    public void close() {
        this.statement.close();
    }

    public long simpleQueryForLong() {
        return this.statement.simpleQueryForLong();
    }

    @Nullable
    public String simpleQueryForString() {
        return this.statement.simpleQueryForString();
    }

    public long executeInsert() {
        return this.statement.executeInsert();
    }

    public void bindString(int i, String str) {
        this.statement.bindString(i, str);
    }

    public void bindNull(int i) {
        this.statement.bindNull(i);
    }

    public void bindLong(int i, long j) {
        this.statement.bindLong(i, j);
    }

    public void bindDouble(int i, double d) {
        this.statement.bindDouble(i, d);
    }

    public void bindBlob(int i, byte[] bArr) {
        this.statement.bindBlob(i, bArr);
    }
}
