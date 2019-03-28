package com.raizlabs.android.dbflow.structure.database.transaction;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowLog;

public final class Transaction {
    private static Handler TRANSACTION_HANDLER;
    final DatabaseDefinition databaseDefinition;
    final Error errorCallback;
    final String name;
    final boolean runCallbacksOnSameThread;
    final boolean shouldRunInTransaction;
    final Success successCallback;
    final ITransaction transaction;

    /* renamed from: com.raizlabs.android.dbflow.structure.database.transaction.Transaction$1 */
    class C04851 implements Runnable {
        C04851() {
        }

        public void run() {
            Transaction.this.successCallback.onSuccess(Transaction.this);
        }
    }

    public static final class Builder {
        @NonNull
        final DatabaseDefinition databaseDefinition;
        Error errorCallback;
        String name;
        private boolean runCallbacksOnSameThread;
        boolean shouldRunInTransaction = true;
        Success successCallback;
        final ITransaction transaction;

        public Builder(@NonNull ITransaction iTransaction, @NonNull DatabaseDefinition databaseDefinition) {
            this.transaction = iTransaction;
            this.databaseDefinition = databaseDefinition;
        }

        @NonNull
        public Builder error(@Nullable Error error) {
            this.errorCallback = error;
            return this;
        }

        @NonNull
        public Builder success(@Nullable Success success) {
            this.successCallback = success;
            return this;
        }

        @NonNull
        public Builder name(@Nullable String str) {
            this.name = str;
            return this;
        }

        @NonNull
        public Builder shouldRunInTransaction(boolean z) {
            this.shouldRunInTransaction = z;
            return this;
        }

        @NonNull
        public Builder runCallbacksOnSameThread(boolean z) {
            this.runCallbacksOnSameThread = z;
            return this;
        }

        @NonNull
        public Transaction build() {
            return new Transaction(this);
        }

        public void execute() {
            build().execute();
        }
    }

    public interface Error {
        void onError(@NonNull Transaction transaction, @NonNull Throwable th);
    }

    public interface Success {
        void onSuccess(@NonNull Transaction transaction);
    }

    static Handler getTransactionHandler() {
        if (TRANSACTION_HANDLER == null) {
            TRANSACTION_HANDLER = new Handler(Looper.getMainLooper());
        }
        return TRANSACTION_HANDLER;
    }

    Transaction(Builder builder) {
        this.databaseDefinition = builder.databaseDefinition;
        this.errorCallback = builder.errorCallback;
        this.successCallback = builder.successCallback;
        this.transaction = builder.transaction;
        this.name = builder.name;
        this.shouldRunInTransaction = builder.shouldRunInTransaction;
        this.runCallbacksOnSameThread = builder.runCallbacksOnSameThread;
    }

    @Nullable
    public Error error() {
        return this.errorCallback;
    }

    @Nullable
    public Success success() {
        return this.successCallback;
    }

    @NonNull
    public ITransaction transaction() {
        return this.transaction;
    }

    @Nullable
    public String name() {
        return this.name;
    }

    public void execute() {
        this.databaseDefinition.getTransactionManager().addTransaction(this);
    }

    public void cancel() {
        this.databaseDefinition.getTransactionManager().cancelTransaction(this);
    }

    public void executeSync() {
        try {
            if (this.shouldRunInTransaction) {
                this.databaseDefinition.executeTransaction(this.transaction);
            } else {
                this.transaction.execute(this.databaseDefinition.getWritableDatabase());
            }
            if (this.successCallback == null) {
                return;
            }
            if (this.runCallbacksOnSameThread) {
                this.successCallback.onSuccess(this);
            } else {
                getTransactionHandler().post(new C04851());
            }
        } catch (final Throwable th) {
            FlowLog.logError(th);
            if (this.errorCallback == null) {
                RuntimeException runtimeException = new RuntimeException("An exception occurred while executing a transaction", th);
            } else if (this.runCallbacksOnSameThread) {
                this.errorCallback.onError(this, th);
            } else {
                getTransactionHandler().post(new Runnable() {
                    public void run() {
                        Transaction.this.errorCallback.onError(Transaction.this, th);
                    }
                });
            }
        }
    }

    @NonNull
    public Builder newBuilder() {
        return new Builder(this.transaction, this.databaseDefinition).error(this.errorCallback).success(this.successCallback).name(this.name).shouldRunInTransaction(this.shouldRunInTransaction).runCallbacksOnSameThread(this.runCallbacksOnSameThread);
    }
}
