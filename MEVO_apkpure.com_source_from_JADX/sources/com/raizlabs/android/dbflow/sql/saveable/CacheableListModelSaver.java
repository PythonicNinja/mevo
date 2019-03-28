package com.raizlabs.android.dbflow.sql.saveable;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.Collection;

public class CacheableListModelSaver<TModel> extends ListModelSaver<TModel> {
    public CacheableListModelSaver(@NonNull ModelSaver<TModel> modelSaver) {
        super(modelSaver);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void saveAll(@android.support.annotation.NonNull java.util.Collection<TModel> r7, @android.support.annotation.NonNull com.raizlabs.android.dbflow.structure.database.DatabaseWrapper r8) {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r7.isEmpty();	 Catch:{ all -> 0x0041 }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r6);
        return;
    L_0x0009:
        r0 = r6.getModelSaver();	 Catch:{ all -> 0x0041 }
        r1 = r0.getModelAdapter();	 Catch:{ all -> 0x0041 }
        r2 = r1.getInsertStatement(r8);	 Catch:{ all -> 0x0041 }
        r3 = r1.getUpdateStatement(r8);	 Catch:{ all -> 0x0041 }
        r7 = r7.iterator();	 Catch:{ all -> 0x0039 }
    L_0x001d:
        r4 = r7.hasNext();	 Catch:{ all -> 0x0039 }
        if (r4 == 0) goto L_0x0031;
    L_0x0023:
        r4 = r7.next();	 Catch:{ all -> 0x0039 }
        r5 = r0.save(r4, r8, r2, r3);	 Catch:{ all -> 0x0039 }
        if (r5 == 0) goto L_0x001d;
    L_0x002d:
        r1.storeModelInCache(r4);	 Catch:{ all -> 0x0039 }
        goto L_0x001d;
    L_0x0031:
        r3.close();	 Catch:{ all -> 0x0041 }
        r2.close();	 Catch:{ all -> 0x0041 }
        monitor-exit(r6);
        return;
    L_0x0039:
        r7 = move-exception;
        r3.close();	 Catch:{ all -> 0x0041 }
        r2.close();	 Catch:{ all -> 0x0041 }
        throw r7;	 Catch:{ all -> 0x0041 }
    L_0x0041:
        r7 = move-exception;
        monitor-exit(r6);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.raizlabs.android.dbflow.sql.saveable.CacheableListModelSaver.saveAll(java.util.Collection, com.raizlabs.android.dbflow.structure.database.DatabaseWrapper):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void insertAll(@android.support.annotation.NonNull java.util.Collection<TModel> r10, @android.support.annotation.NonNull com.raizlabs.android.dbflow.structure.database.DatabaseWrapper r11) {
        /*
        r9 = this;
        monitor-enter(r9);
        r0 = r10.isEmpty();	 Catch:{ all -> 0x003b }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r9);
        return;
    L_0x0009:
        r0 = r9.getModelSaver();	 Catch:{ all -> 0x003b }
        r1 = r0.getModelAdapter();	 Catch:{ all -> 0x003b }
        r2 = r1.getInsertStatement(r11);	 Catch:{ all -> 0x003b }
        r10 = r10.iterator();	 Catch:{ all -> 0x0036 }
    L_0x0019:
        r3 = r10.hasNext();	 Catch:{ all -> 0x0036 }
        if (r3 == 0) goto L_0x0031;
    L_0x001f:
        r3 = r10.next();	 Catch:{ all -> 0x0036 }
        r4 = r0.insert(r3, r2, r11);	 Catch:{ all -> 0x0036 }
        r6 = 0;
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 <= 0) goto L_0x0019;
    L_0x002d:
        r1.storeModelInCache(r3);	 Catch:{ all -> 0x0036 }
        goto L_0x0019;
    L_0x0031:
        r2.close();	 Catch:{ all -> 0x003b }
        monitor-exit(r9);
        return;
    L_0x0036:
        r10 = move-exception;
        r2.close();	 Catch:{ all -> 0x003b }
        throw r10;	 Catch:{ all -> 0x003b }
    L_0x003b:
        r10 = move-exception;
        monitor-exit(r9);
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.raizlabs.android.dbflow.sql.saveable.CacheableListModelSaver.insertAll(java.util.Collection, com.raizlabs.android.dbflow.structure.database.DatabaseWrapper):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void updateAll(@android.support.annotation.NonNull java.util.Collection<TModel> r6, @android.support.annotation.NonNull com.raizlabs.android.dbflow.structure.database.DatabaseWrapper r7) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r6.isEmpty();	 Catch:{ all -> 0x0037 }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r5);
        return;
    L_0x0009:
        r0 = r5.getModelSaver();	 Catch:{ all -> 0x0037 }
        r1 = r0.getModelAdapter();	 Catch:{ all -> 0x0037 }
        r2 = r1.getUpdateStatement(r7);	 Catch:{ all -> 0x0037 }
        r6 = r6.iterator();	 Catch:{ all -> 0x0032 }
    L_0x0019:
        r3 = r6.hasNext();	 Catch:{ all -> 0x0032 }
        if (r3 == 0) goto L_0x002d;
    L_0x001f:
        r3 = r6.next();	 Catch:{ all -> 0x0032 }
        r4 = r0.update(r3, r7, r2);	 Catch:{ all -> 0x0032 }
        if (r4 == 0) goto L_0x0019;
    L_0x0029:
        r1.storeModelInCache(r3);	 Catch:{ all -> 0x0032 }
        goto L_0x0019;
    L_0x002d:
        r2.close();	 Catch:{ all -> 0x0037 }
        monitor-exit(r5);
        return;
    L_0x0032:
        r6 = move-exception;
        r2.close();	 Catch:{ all -> 0x0037 }
        throw r6;	 Catch:{ all -> 0x0037 }
    L_0x0037:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.raizlabs.android.dbflow.sql.saveable.CacheableListModelSaver.updateAll(java.util.Collection, com.raizlabs.android.dbflow.structure.database.DatabaseWrapper):void");
    }

    public synchronized void deleteAll(@NonNull Collection<TModel> collection, @NonNull DatabaseWrapper databaseWrapper) {
        if (!collection.isEmpty()) {
            ModelSaver modelSaver = getModelSaver();
            for (Object next : collection) {
                if (modelSaver.delete(next, databaseWrapper)) {
                    getModelSaver().getModelAdapter().removeModelFromCache(next);
                }
            }
        }
    }
}
