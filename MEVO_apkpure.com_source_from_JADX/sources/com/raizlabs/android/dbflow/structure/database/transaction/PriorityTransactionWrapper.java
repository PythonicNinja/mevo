package com.raizlabs.android.dbflow.structure.database.transaction;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PriorityTransactionWrapper implements ITransaction, Comparable<PriorityTransactionWrapper> {
    public static final int PRIORITY_HIGH = 2;
    public static final int PRIORITY_LOW = 0;
    public static final int PRIORITY_NORMAL = 1;
    public static final int PRIORITY_UI = 5;
    private final int priority;
    private final ITransaction transaction;

    public static class Builder {
        private int priority;
        private final ITransaction transaction;

        public Builder(@NonNull ITransaction iTransaction) {
            this.transaction = iTransaction;
        }

        public Builder priority(int i) {
            this.priority = i;
            return this;
        }

        public PriorityTransactionWrapper build() {
            return new PriorityTransactionWrapper(this);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Priority {
    }

    PriorityTransactionWrapper(Builder builder) {
        if (builder.priority == 0) {
            this.priority = 1;
        } else {
            this.priority = builder.priority;
        }
        this.transaction = builder.transaction;
    }

    public void execute(DatabaseWrapper databaseWrapper) {
        this.transaction.execute(databaseWrapper);
    }

    public int compareTo(@NonNull PriorityTransactionWrapper priorityTransactionWrapper) {
        return priorityTransactionWrapper.priority - this.priority;
    }
}
