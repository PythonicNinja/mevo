package com.raizlabs.android.dbflow.structure;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public class BaseModel implements Model {
    private transient ModelAdapter modelAdapter;

    public enum Action {
        SAVE,
        INSERT,
        UPDATE,
        DELETE,
        CHANGE
    }

    public void load() {
        getModelAdapter().load(this);
    }

    public void load(@NonNull DatabaseWrapper databaseWrapper) {
        getModelAdapter().load(this, databaseWrapper);
    }

    public boolean save() {
        return getModelAdapter().save(this);
    }

    public boolean save(@NonNull DatabaseWrapper databaseWrapper) {
        return getModelAdapter().save(this, databaseWrapper);
    }

    public boolean delete() {
        return getModelAdapter().delete(this);
    }

    public boolean delete(@NonNull DatabaseWrapper databaseWrapper) {
        return getModelAdapter().delete(this, databaseWrapper);
    }

    public boolean update() {
        return getModelAdapter().update(this);
    }

    public boolean update(@NonNull DatabaseWrapper databaseWrapper) {
        return getModelAdapter().update(this, databaseWrapper);
    }

    public long insert() {
        return getModelAdapter().insert(this);
    }

    public long insert(DatabaseWrapper databaseWrapper) {
        return getModelAdapter().insert(this, databaseWrapper);
    }

    public boolean exists() {
        return getModelAdapter().exists(this);
    }

    public boolean exists(@NonNull DatabaseWrapper databaseWrapper) {
        return getModelAdapter().exists(this, databaseWrapper);
    }

    @NonNull
    public AsyncModel<? extends Model> async() {
        return new AsyncModel(this);
    }

    public ModelAdapter getModelAdapter() {
        if (this.modelAdapter == null) {
            this.modelAdapter = FlowManager.getModelAdapter(getClass());
        }
        return this.modelAdapter;
    }
}
