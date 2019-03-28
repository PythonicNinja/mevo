package com.raizlabs.android.dbflow.runtime;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransactionQueue;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

public abstract class BaseTransactionManager {
    private DBBatchSaveQueue saveQueue;
    private final ITransactionQueue transactionQueue;

    public BaseTransactionManager(@NonNull ITransactionQueue iTransactionQueue, @NonNull DatabaseDefinition databaseDefinition) {
        this.transactionQueue = iTransactionQueue;
        this.saveQueue = new DBBatchSaveQueue(databaseDefinition);
        checkQueue();
    }

    @NonNull
    public DBBatchSaveQueue getSaveQueue() {
        try {
            if (!this.saveQueue.isAlive()) {
                this.saveQueue.start();
            }
        } catch (Throwable e) {
            FlowLog.logError(e);
        }
        return this.saveQueue;
    }

    @NonNull
    public ITransactionQueue getQueue() {
        return this.transactionQueue;
    }

    public void checkQueue() {
        getQueue().startIfNotAlive();
    }

    public void stopQueue() {
        getQueue().quit();
    }

    public void addTransaction(@NonNull Transaction transaction) {
        getQueue().add(transaction);
    }

    public void cancelTransaction(@NonNull Transaction transaction) {
        getQueue().cancel(transaction);
    }
}
