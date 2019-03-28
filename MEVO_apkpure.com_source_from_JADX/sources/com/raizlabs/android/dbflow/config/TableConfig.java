package com.raizlabs.android.dbflow.config;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.sql.queriable.ListModelLoader;
import com.raizlabs.android.dbflow.sql.queriable.SingleModelLoader;
import com.raizlabs.android.dbflow.sql.saveable.ModelSaver;

public final class TableConfig<TModel> {
    private final ListModelLoader<TModel> listModelLoader;
    private final ModelSaver<TModel> modelSaver;
    private final SingleModelLoader<TModel> singleModelLoader;
    private final Class<TModel> tableClass;

    public static final class Builder<TModel> {
        ListModelLoader<TModel> listModelLoader;
        ModelSaver<TModel> modelAdapterModelSaver;
        SingleModelLoader<TModel> singleModelLoader;
        final Class<TModel> tableClass;

        public Builder(@NonNull Class<TModel> cls) {
            this.tableClass = cls;
        }

        @NonNull
        public Builder<TModel> modelAdapterModelSaver(@NonNull ModelSaver<TModel> modelSaver) {
            this.modelAdapterModelSaver = modelSaver;
            return this;
        }

        @NonNull
        public Builder<TModel> singleModelLoader(@NonNull SingleModelLoader<TModel> singleModelLoader) {
            this.singleModelLoader = singleModelLoader;
            return this;
        }

        @NonNull
        public Builder<TModel> listModelLoader(@NonNull ListModelLoader<TModel> listModelLoader) {
            this.listModelLoader = listModelLoader;
            return this;
        }

        @NonNull
        public TableConfig build() {
            return new TableConfig(this);
        }
    }

    TableConfig(Builder<TModel> builder) {
        this.tableClass = builder.tableClass;
        this.modelSaver = builder.modelAdapterModelSaver;
        this.singleModelLoader = builder.singleModelLoader;
        this.listModelLoader = builder.listModelLoader;
    }

    @NonNull
    public Class<?> tableClass() {
        return this.tableClass;
    }

    @Nullable
    public ModelSaver<TModel> modelSaver() {
        return this.modelSaver;
    }

    @Nullable
    public ListModelLoader<TModel> listModelLoader() {
        return this.listModelLoader;
    }

    @Nullable
    public SingleModelLoader<TModel> singleModelLoader() {
        return this.singleModelLoader;
    }
}
