package com.raizlabs.android.dbflow.structure.database.transaction;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.InternalAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FastStoreModelTransaction<TModel> implements ITransaction {
    final InternalAdapter<TModel> internalAdapter;
    final List<TModel> models;
    final ProcessModelList<TModel> processModelList;

    public static final class Builder<TModel> {
        @NonNull
        private final InternalAdapter<TModel> internalAdapter;
        List<TModel> models = new ArrayList();
        private final ProcessModelList<TModel> processModelList;

        Builder(@NonNull ProcessModelList<TModel> processModelList, @NonNull InternalAdapter<TModel> internalAdapter) {
            this.processModelList = processModelList;
            this.internalAdapter = internalAdapter;
        }

        @NonNull
        public Builder<TModel> add(TModel tModel) {
            this.models.add(tModel);
            return this;
        }

        @SafeVarargs
        @NonNull
        public final Builder<TModel> addAll(TModel... tModelArr) {
            this.models.addAll(Arrays.asList(tModelArr));
            return this;
        }

        @NonNull
        public Builder<TModel> addAll(Collection<? extends TModel> collection) {
            if (collection != null) {
                this.models.addAll(collection);
            }
            return this;
        }

        @NonNull
        public FastStoreModelTransaction<TModel> build() {
            return new FastStoreModelTransaction(this);
        }
    }

    interface ProcessModelList<TModel> {
        void processModel(@NonNull List<TModel> list, InternalAdapter<TModel> internalAdapter, DatabaseWrapper databaseWrapper);
    }

    /* renamed from: com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction$1 */
    static class C08791 implements ProcessModelList<TModel> {
        C08791() {
        }

        public void processModel(@NonNull List<TModel> list, InternalAdapter<TModel> internalAdapter, DatabaseWrapper databaseWrapper) {
            internalAdapter.saveAll(list, databaseWrapper);
        }
    }

    /* renamed from: com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction$2 */
    static class C08802 implements ProcessModelList<TModel> {
        C08802() {
        }

        public void processModel(@NonNull List<TModel> list, InternalAdapter<TModel> internalAdapter, DatabaseWrapper databaseWrapper) {
            internalAdapter.insertAll(list, databaseWrapper);
        }
    }

    /* renamed from: com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction$3 */
    static class C08813 implements ProcessModelList<TModel> {
        C08813() {
        }

        public void processModel(@NonNull List<TModel> list, InternalAdapter<TModel> internalAdapter, DatabaseWrapper databaseWrapper) {
            internalAdapter.updateAll(list, databaseWrapper);
        }
    }

    /* renamed from: com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction$4 */
    static class C08824 implements ProcessModelList<TModel> {
        C08824() {
        }

        public void processModel(@NonNull List<TModel> list, InternalAdapter<TModel> internalAdapter, DatabaseWrapper databaseWrapper) {
            internalAdapter.deleteAll(list, databaseWrapper);
        }
    }

    @NonNull
    public static <TModel> Builder<TModel> saveBuilder(@NonNull InternalAdapter<TModel> internalAdapter) {
        return new Builder(new C08791(), internalAdapter);
    }

    @NonNull
    public static <TModel> Builder<TModel> insertBuilder(@NonNull InternalAdapter<TModel> internalAdapter) {
        return new Builder(new C08802(), internalAdapter);
    }

    @NonNull
    public static <TModel> Builder<TModel> updateBuilder(@NonNull InternalAdapter<TModel> internalAdapter) {
        return new Builder(new C08813(), internalAdapter);
    }

    @NonNull
    public static <TModel> Builder<TModel> deleteBuilder(@NonNull InternalAdapter<TModel> internalAdapter) {
        return new Builder(new C08824(), internalAdapter);
    }

    FastStoreModelTransaction(Builder<TModel> builder) {
        this.models = builder.models;
        this.processModelList = builder.processModelList;
        this.internalAdapter = builder.internalAdapter;
    }

    public void execute(DatabaseWrapper databaseWrapper) {
        if (this.models != null) {
            this.processModelList.processModel(this.models, this.internalAdapter, databaseWrapper);
        }
    }
}
