package com.raizlabs.android.dbflow.sql;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction.Error;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction.Success;

public class BaseAsyncObject<TAsync> {
    private Transaction currentTransaction;
    private final DatabaseDefinition databaseDefinition;
    private final Error error = new C08701();
    private Error errorCallback;
    private final Success success = new C08712();
    private Success successCallback;
    private final Class<?> table;

    /* renamed from: com.raizlabs.android.dbflow.sql.BaseAsyncObject$1 */
    class C08701 implements Error {
        C08701() {
        }

        public void onError(@NonNull Transaction transaction, @NonNull Throwable th) {
            if (BaseAsyncObject.this.errorCallback != null) {
                BaseAsyncObject.this.errorCallback.onError(transaction, th);
            }
            BaseAsyncObject.this.onError(transaction, th);
            BaseAsyncObject.this.currentTransaction = null;
        }
    }

    /* renamed from: com.raizlabs.android.dbflow.sql.BaseAsyncObject$2 */
    class C08712 implements Success {
        C08712() {
        }

        public void onSuccess(@NonNull Transaction transaction) {
            if (BaseAsyncObject.this.successCallback != null) {
                BaseAsyncObject.this.successCallback.onSuccess(transaction);
            }
            BaseAsyncObject.this.onSuccess(transaction);
            BaseAsyncObject.this.currentTransaction = null;
        }
    }

    protected void onError(@NonNull Transaction transaction, Throwable th) {
    }

    protected void onSuccess(@NonNull Transaction transaction) {
    }

    public BaseAsyncObject(@NonNull Class<?> cls) {
        this.table = cls;
        this.databaseDefinition = FlowManager.getDatabaseForTable(cls);
    }

    @NonNull
    public Class<?> getTable() {
        return this.table;
    }

    public TAsync error(@Nullable Error error) {
        this.errorCallback = error;
        return this;
    }

    public TAsync success(@Nullable Success success) {
        this.successCallback = success;
        return this;
    }

    public void cancel() {
        if (this.currentTransaction != null) {
            this.currentTransaction.cancel();
        }
    }

    protected void executeTransaction(@NonNull ITransaction iTransaction) {
        cancel();
        this.currentTransaction = this.databaseDefinition.beginTransactionAsync(iTransaction).error(this.error).success(this.success).build();
        this.currentTransaction.execute();
    }
}
