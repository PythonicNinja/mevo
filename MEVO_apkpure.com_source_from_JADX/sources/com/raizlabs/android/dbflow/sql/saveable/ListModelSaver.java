package com.raizlabs.android.dbflow.sql.saveable;

import android.support.annotation.NonNull;
import java.util.Collection;

public class ListModelSaver<TModel> {
    private final ModelSaver<TModel> modelSaver;

    public ListModelSaver(@NonNull ModelSaver<TModel> modelSaver) {
        this.modelSaver = modelSaver;
    }

    public synchronized void saveAll(@NonNull Collection<TModel> collection) {
        saveAll(collection, this.modelSaver.getWritableDatabase());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void saveAll(@android.support.annotation.NonNull java.util.Collection<TModel> r5, @android.support.annotation.NonNull com.raizlabs.android.dbflow.structure.database.DatabaseWrapper r6) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r5.isEmpty();	 Catch:{ all -> 0x003b }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r4);
        return;
    L_0x0009:
        r0 = r4.modelSaver;	 Catch:{ all -> 0x003b }
        r0 = r0.getModelAdapter();	 Catch:{ all -> 0x003b }
        r0 = r0.getInsertStatement(r6);	 Catch:{ all -> 0x003b }
        r1 = r4.modelSaver;	 Catch:{ all -> 0x003b }
        r1 = r1.getModelAdapter();	 Catch:{ all -> 0x003b }
        r1 = r1.getUpdateStatement(r6);	 Catch:{ all -> 0x003b }
        r5 = r5.iterator();	 Catch:{ all -> 0x0036 }
    L_0x0021:
        r2 = r5.hasNext();	 Catch:{ all -> 0x0036 }
        if (r2 == 0) goto L_0x0031;
    L_0x0027:
        r2 = r5.next();	 Catch:{ all -> 0x0036 }
        r3 = r4.modelSaver;	 Catch:{ all -> 0x0036 }
        r3.save(r2, r6, r0, r1);	 Catch:{ all -> 0x0036 }
        goto L_0x0021;
    L_0x0031:
        r0.close();	 Catch:{ all -> 0x003b }
        monitor-exit(r4);
        return;
    L_0x0036:
        r5 = move-exception;
        r0.close();	 Catch:{ all -> 0x003b }
        throw r5;	 Catch:{ all -> 0x003b }
    L_0x003b:
        r5 = move-exception;
        monitor-exit(r4);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.raizlabs.android.dbflow.sql.saveable.ListModelSaver.saveAll(java.util.Collection, com.raizlabs.android.dbflow.structure.database.DatabaseWrapper):void");
    }

    public synchronized void insertAll(@NonNull Collection<TModel> collection) {
        insertAll(collection, this.modelSaver.getWritableDatabase());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void insertAll(@android.support.annotation.NonNull java.util.Collection<TModel> r4, @android.support.annotation.NonNull com.raizlabs.android.dbflow.structure.database.DatabaseWrapper r5) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r4.isEmpty();	 Catch:{ all -> 0x0031 }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r3);
        return;
    L_0x0009:
        r0 = r3.modelSaver;	 Catch:{ all -> 0x0031 }
        r0 = r0.getModelAdapter();	 Catch:{ all -> 0x0031 }
        r0 = r0.getInsertStatement(r5);	 Catch:{ all -> 0x0031 }
        r4 = r4.iterator();	 Catch:{ all -> 0x002c }
    L_0x0017:
        r1 = r4.hasNext();	 Catch:{ all -> 0x002c }
        if (r1 == 0) goto L_0x0027;
    L_0x001d:
        r1 = r4.next();	 Catch:{ all -> 0x002c }
        r2 = r3.modelSaver;	 Catch:{ all -> 0x002c }
        r2.insert(r1, r0, r5);	 Catch:{ all -> 0x002c }
        goto L_0x0017;
    L_0x0027:
        r0.close();	 Catch:{ all -> 0x0031 }
        monitor-exit(r3);
        return;
    L_0x002c:
        r4 = move-exception;
        r0.close();	 Catch:{ all -> 0x0031 }
        throw r4;	 Catch:{ all -> 0x0031 }
    L_0x0031:
        r4 = move-exception;
        monitor-exit(r3);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.raizlabs.android.dbflow.sql.saveable.ListModelSaver.insertAll(java.util.Collection, com.raizlabs.android.dbflow.structure.database.DatabaseWrapper):void");
    }

    public synchronized void updateAll(@NonNull Collection<TModel> collection) {
        updateAll(collection, this.modelSaver.getWritableDatabase());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void updateAll(@android.support.annotation.NonNull java.util.Collection<TModel> r4, @android.support.annotation.NonNull com.raizlabs.android.dbflow.structure.database.DatabaseWrapper r5) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r4.isEmpty();	 Catch:{ all -> 0x0031 }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r3);
        return;
    L_0x0009:
        r0 = r3.modelSaver;	 Catch:{ all -> 0x0031 }
        r0 = r0.getModelAdapter();	 Catch:{ all -> 0x0031 }
        r0 = r0.getUpdateStatement(r5);	 Catch:{ all -> 0x0031 }
        r4 = r4.iterator();	 Catch:{ all -> 0x002c }
    L_0x0017:
        r1 = r4.hasNext();	 Catch:{ all -> 0x002c }
        if (r1 == 0) goto L_0x0027;
    L_0x001d:
        r1 = r4.next();	 Catch:{ all -> 0x002c }
        r2 = r3.modelSaver;	 Catch:{ all -> 0x002c }
        r2.update(r1, r5, r0);	 Catch:{ all -> 0x002c }
        goto L_0x0017;
    L_0x0027:
        r0.close();	 Catch:{ all -> 0x0031 }
        monitor-exit(r3);
        return;
    L_0x002c:
        r4 = move-exception;
        r0.close();	 Catch:{ all -> 0x0031 }
        throw r4;	 Catch:{ all -> 0x0031 }
    L_0x0031:
        r4 = move-exception;
        monitor-exit(r3);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.raizlabs.android.dbflow.sql.saveable.ListModelSaver.updateAll(java.util.Collection, com.raizlabs.android.dbflow.structure.database.DatabaseWrapper):void");
    }

    public synchronized void deleteAll(@NonNull Collection<TModel> collection) {
        deleteAll(collection, this.modelSaver.getWritableDatabase());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void deleteAll(@android.support.annotation.NonNull java.util.Collection<TModel> r4, @android.support.annotation.NonNull com.raizlabs.android.dbflow.structure.database.DatabaseWrapper r5) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r4.isEmpty();	 Catch:{ all -> 0x0031 }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r3);
        return;
    L_0x0009:
        r0 = r3.modelSaver;	 Catch:{ all -> 0x0031 }
        r0 = r0.getModelAdapter();	 Catch:{ all -> 0x0031 }
        r0 = r0.getDeleteStatement(r5);	 Catch:{ all -> 0x0031 }
        r4 = r4.iterator();	 Catch:{ all -> 0x002c }
    L_0x0017:
        r1 = r4.hasNext();	 Catch:{ all -> 0x002c }
        if (r1 == 0) goto L_0x0027;
    L_0x001d:
        r1 = r4.next();	 Catch:{ all -> 0x002c }
        r2 = r3.modelSaver;	 Catch:{ all -> 0x002c }
        r2.delete(r1, r0, r5);	 Catch:{ all -> 0x002c }
        goto L_0x0017;
    L_0x0027:
        r0.close();	 Catch:{ all -> 0x0031 }
        monitor-exit(r3);
        return;
    L_0x002c:
        r4 = move-exception;
        r0.close();	 Catch:{ all -> 0x0031 }
        throw r4;	 Catch:{ all -> 0x0031 }
    L_0x0031:
        r4 = move-exception;
        monitor-exit(r3);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.raizlabs.android.dbflow.sql.saveable.ListModelSaver.deleteAll(java.util.Collection, com.raizlabs.android.dbflow.structure.database.DatabaseWrapper):void");
    }

    @NonNull
    public ModelSaver<TModel> getModelSaver() {
        return this.modelSaver;
    }
}
