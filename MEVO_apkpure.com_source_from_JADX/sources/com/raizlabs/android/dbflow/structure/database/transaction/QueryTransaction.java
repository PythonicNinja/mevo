package com.raizlabs.android.dbflow.structure.database.transaction;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.List;

public class QueryTransaction<TResult> implements ITransaction {
    final ModelQueriable<TResult> modelQueriable;
    final QueryResultCallback<TResult> queryResultCallback;
    final QueryResultListCallback<TResult> queryResultListCallback;
    final QueryResultSingleCallback<TResult> queryResultSingleCallback;
    final boolean runResultCallbacksOnSameThread;

    public static final class Builder<TResult> {
        final ModelQueriable<TResult> modelQueriable;
        QueryResultCallback<TResult> queryResultCallback;
        QueryResultListCallback<TResult> queryResultListCallback;
        QueryResultSingleCallback<TResult> queryResultSingleCallback;
        boolean runResultCallbacksOnSameThread;

        public Builder(@NonNull ModelQueriable<TResult> modelQueriable) {
            this.modelQueriable = modelQueriable;
        }

        public Builder<TResult> queryResult(QueryResultCallback<TResult> queryResultCallback) {
            this.queryResultCallback = queryResultCallback;
            return this;
        }

        public Builder<TResult> queryListResult(QueryResultListCallback<TResult> queryResultListCallback) {
            this.queryResultListCallback = queryResultListCallback;
            return this;
        }

        public Builder<TResult> querySingleResult(QueryResultSingleCallback<TResult> queryResultSingleCallback) {
            this.queryResultSingleCallback = queryResultSingleCallback;
            return this;
        }

        public Builder<TResult> runResultCallbacksOnSameThread(boolean z) {
            this.runResultCallbacksOnSameThread = z;
            return this;
        }

        public QueryTransaction<TResult> build() {
            return new QueryTransaction(this);
        }
    }

    public interface QueryResultCallback<TResult> {
        void onQueryResult(QueryTransaction<TResult> queryTransaction, @NonNull CursorResult<TResult> cursorResult);
    }

    public interface QueryResultListCallback<TResult> {
        void onListQueryResult(QueryTransaction queryTransaction, @NonNull List<TResult> list);
    }

    public interface QueryResultSingleCallback<TResult> {
        void onSingleQueryResult(QueryTransaction queryTransaction, @Nullable TResult tResult);
    }

    QueryTransaction(Builder<TResult> builder) {
        this.modelQueriable = builder.modelQueriable;
        this.queryResultCallback = builder.queryResultCallback;
        this.queryResultListCallback = builder.queryResultListCallback;
        this.queryResultSingleCallback = builder.queryResultSingleCallback;
        this.runResultCallbacksOnSameThread = builder.runResultCallbacksOnSameThread;
    }

    public void execute(DatabaseWrapper databaseWrapper) {
        databaseWrapper = this.modelQueriable.queryResults();
        if (this.queryResultCallback != null) {
            if (this.runResultCallbacksOnSameThread) {
                this.queryResultCallback.onQueryResult(this, databaseWrapper);
            } else {
                Transaction.getTransactionHandler().post(new Runnable() {
                    public void run() {
                        QueryTransaction.this.queryResultCallback.onQueryResult(QueryTransaction.this, databaseWrapper);
                    }
                });
            }
        }
        if (this.queryResultListCallback != null) {
            final List toListClose = databaseWrapper.toListClose();
            if (this.runResultCallbacksOnSameThread) {
                this.queryResultListCallback.onListQueryResult(this, toListClose);
            } else {
                Transaction.getTransactionHandler().post(new Runnable() {
                    public void run() {
                        QueryTransaction.this.queryResultListCallback.onListQueryResult(QueryTransaction.this, toListClose);
                    }
                });
            }
        }
        if (this.queryResultSingleCallback != null) {
            databaseWrapper = databaseWrapper.toModelClose();
            if (this.runResultCallbacksOnSameThread) {
                this.queryResultSingleCallback.onSingleQueryResult(this, databaseWrapper);
            } else {
                Transaction.getTransactionHandler().post(new Runnable() {
                    public void run() {
                        QueryTransaction.this.queryResultSingleCallback.onSingleQueryResult(QueryTransaction.this, databaseWrapper);
                    }
                });
            }
        }
    }
}
