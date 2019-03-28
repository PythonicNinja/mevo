package com.raizlabs.android.dbflow.structure.database.transaction;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ProcessModelTransaction<TModel> implements ITransaction {
    final List<TModel> models;
    final OnModelProcessListener<TModel> processListener;
    final ProcessModel<TModel> processModel;
    final boolean runProcessListenerOnSameThread;

    public static final class Builder<TModel> {
        List<TModel> models = new ArrayList();
        OnModelProcessListener<TModel> processListener;
        private final ProcessModel<TModel> processModel;
        private boolean runProcessListenerOnSameThread;

        public Builder(@NonNull ProcessModel<TModel> processModel) {
            this.processModel = processModel;
        }

        public Builder(Collection<TModel> collection, @NonNull ProcessModel<TModel> processModel) {
            this.processModel = processModel;
            this.models = new ArrayList(collection);
        }

        public Builder<TModel> add(TModel tModel) {
            this.models.add(tModel);
            return this;
        }

        @SafeVarargs
        public final Builder<TModel> addAll(TModel... tModelArr) {
            this.models.addAll(Arrays.asList(tModelArr));
            return this;
        }

        public Builder<TModel> addAll(Collection<? extends TModel> collection) {
            if (collection != null) {
                this.models.addAll(collection);
            }
            return this;
        }

        public Builder<TModel> processListener(OnModelProcessListener<TModel> onModelProcessListener) {
            this.processListener = onModelProcessListener;
            return this;
        }

        public Builder<TModel> runProcessListenerOnSameThread(boolean z) {
            this.runProcessListenerOnSameThread = z;
            return this;
        }

        public ProcessModelTransaction<TModel> build() {
            return new ProcessModelTransaction(this);
        }
    }

    public interface OnModelProcessListener<TModel> {
        void onModelProcessed(long j, long j2, TModel tModel);
    }

    public interface ProcessModel<TModel> {
        void processModel(TModel tModel, DatabaseWrapper databaseWrapper);
    }

    ProcessModelTransaction(Builder<TModel> builder) {
        this.processListener = builder.processListener;
        this.models = builder.models;
        this.processModel = builder.processModel;
        this.runProcessListenerOnSameThread = builder.runProcessListenerOnSameThread;
    }

    public void execute(DatabaseWrapper databaseWrapper) {
        if (this.models != null) {
            final int size = this.models.size();
            for (int i = 0; i < size; i++) {
                final Object obj = this.models.get(i);
                this.processModel.processModel(obj, databaseWrapper);
                if (this.processListener != null) {
                    if (this.runProcessListenerOnSameThread) {
                        this.processListener.onModelProcessed((long) i, (long) size, obj);
                    } else {
                        Transaction.getTransactionHandler().post(new Runnable() {
                            public void run() {
                                ProcessModelTransaction.this.processListener.onModelProcessed((long) i, (long) size, obj);
                            }
                        });
                    }
                }
            }
        }
    }
}
