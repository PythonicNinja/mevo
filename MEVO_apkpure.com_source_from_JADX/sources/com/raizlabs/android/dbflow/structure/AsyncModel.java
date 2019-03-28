package com.raizlabs.android.dbflow.structure;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.BaseAsyncObject;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.Builder;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.ProcessModel;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import java.lang.ref.WeakReference;

public class AsyncModel<TModel> extends BaseAsyncObject<AsyncModel<TModel>> implements Model {
    private final TModel model;
    private ModelAdapter<TModel> modelAdapter;
    private transient WeakReference<OnModelChangedListener<TModel>> onModelChangedListener;

    public interface OnModelChangedListener<T> {
        void onModelChanged(@NonNull T t);
    }

    /* renamed from: com.raizlabs.android.dbflow.structure.AsyncModel$1 */
    class C08731 implements ProcessModel<TModel> {
        C08731() {
        }

        public void processModel(TModel tModel, DatabaseWrapper databaseWrapper) {
            AsyncModel.this.getModelAdapter().save(tModel, databaseWrapper);
        }
    }

    /* renamed from: com.raizlabs.android.dbflow.structure.AsyncModel$2 */
    class C08742 implements ProcessModel<TModel> {
        C08742() {
        }

        public void processModel(TModel tModel, DatabaseWrapper databaseWrapper) {
            AsyncModel.this.getModelAdapter().delete(tModel, databaseWrapper);
        }
    }

    /* renamed from: com.raizlabs.android.dbflow.structure.AsyncModel$3 */
    class C08753 implements ProcessModel<TModel> {
        C08753() {
        }

        public void processModel(TModel tModel, DatabaseWrapper databaseWrapper) {
            AsyncModel.this.getModelAdapter().update(tModel, databaseWrapper);
        }
    }

    /* renamed from: com.raizlabs.android.dbflow.structure.AsyncModel$4 */
    class C08764 implements ProcessModel<TModel> {
        C08764() {
        }

        public void processModel(TModel tModel, DatabaseWrapper databaseWrapper) {
            AsyncModel.this.getModelAdapter().insert(tModel, databaseWrapper);
        }
    }

    /* renamed from: com.raizlabs.android.dbflow.structure.AsyncModel$5 */
    class C08775 implements ProcessModel<TModel> {
        C08775() {
        }

        public void processModel(TModel tModel, DatabaseWrapper databaseWrapper) {
            AsyncModel.this.getModelAdapter().load(tModel, databaseWrapper);
        }
    }

    @NonNull
    public AsyncModel<? extends Model> async() {
        return this;
    }

    public AsyncModel(@NonNull TModel tModel) {
        super(tModel.getClass());
        this.model = tModel;
    }

    public AsyncModel<TModel> withListener(@Nullable OnModelChangedListener<TModel> onModelChangedListener) {
        this.onModelChangedListener = new WeakReference(onModelChangedListener);
        return this;
    }

    private ModelAdapter<TModel> getModelAdapter() {
        if (this.modelAdapter == null) {
            this.modelAdapter = FlowManager.getModelAdapter(this.model.getClass());
        }
        return this.modelAdapter;
    }

    public boolean save(@NonNull DatabaseWrapper databaseWrapper) {
        return save();
    }

    public boolean save() {
        executeTransaction(new Builder(new C08731()).add(this.model).build());
        return false;
    }

    public boolean delete(@NonNull DatabaseWrapper databaseWrapper) {
        return delete();
    }

    public boolean delete() {
        executeTransaction(new Builder(new C08742()).add(this.model).build());
        return false;
    }

    public boolean update(@NonNull DatabaseWrapper databaseWrapper) {
        return update();
    }

    public boolean update() {
        executeTransaction(new Builder(new C08753()).add(this.model).build());
        return false;
    }

    public long insert(DatabaseWrapper databaseWrapper) {
        return insert();
    }

    public long insert() {
        executeTransaction(new Builder(new C08764()).add(this.model).build());
        return -1;
    }

    public void load(@NonNull DatabaseWrapper databaseWrapper) {
        load();
    }

    public void load() {
        executeTransaction(new Builder(new C08775()).add(this.model).build());
    }

    public boolean exists(@NonNull DatabaseWrapper databaseWrapper) {
        return exists();
    }

    public boolean exists() {
        return getModelAdapter().exists(this.model);
    }

    protected void onSuccess(@NonNull Transaction transaction) {
        if (this.onModelChangedListener != null && this.onModelChangedListener.get() != null) {
            ((OnModelChangedListener) this.onModelChangedListener.get()).onModelChanged(this.model);
        }
    }
}
