package com.raizlabs.android.dbflow.runtime;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

public class ContentResolverNotifier implements ModelNotifier {

    public static class FlowContentTableNotifierRegister implements TableNotifierRegister {
        private final FlowContentObserver flowContentObserver = new FlowContentObserver();
        private final OnTableChangedListener internalContentChangeListener = new C08651();
        @Nullable
        private OnTableChangedListener tableChangedListener;

        /* renamed from: com.raizlabs.android.dbflow.runtime.ContentResolverNotifier$FlowContentTableNotifierRegister$1 */
        class C08651 implements OnTableChangedListener {
            C08651() {
            }

            public void onTableChanged(@Nullable Class<?> cls, @NonNull Action action) {
                if (FlowContentTableNotifierRegister.this.tableChangedListener != null) {
                    FlowContentTableNotifierRegister.this.tableChangedListener.onTableChanged(cls, action);
                }
            }
        }

        public FlowContentTableNotifierRegister() {
            this.flowContentObserver.addOnTableChangedListener(this.internalContentChangeListener);
        }

        public <T> void register(@NonNull Class<T> cls) {
            this.flowContentObserver.registerForContentChanges(FlowManager.getContext(), (Class) cls);
        }

        public <T> void unregister(@NonNull Class<T> cls) {
            this.flowContentObserver.unregisterForContentChanges(FlowManager.getContext());
        }

        public void unregisterAll() {
            this.flowContentObserver.removeTableChangedListener(this.internalContentChangeListener);
            this.tableChangedListener = null;
        }

        public void setListener(@Nullable OnTableChangedListener onTableChangedListener) {
            this.tableChangedListener = onTableChangedListener;
        }

        public boolean isSubscribed() {
            return this.flowContentObserver.isSubscribed() ^ 1;
        }
    }

    public <T> void notifyModelChanged(@NonNull T t, @NonNull ModelAdapter<T> modelAdapter, @NonNull Action action) {
        if (FlowContentObserver.shouldNotify()) {
            FlowManager.getContext().getContentResolver().notifyChange(SqlUtils.getNotificationUri(modelAdapter.getModelClass(), action, modelAdapter.getPrimaryConditionClause(t).getConditions()), null, true);
        }
    }

    public <T> void notifyTableChanged(@NonNull Class<T> cls, @NonNull Action action) {
        if (FlowContentObserver.shouldNotify()) {
            FlowManager.getContext().getContentResolver().notifyChange(SqlUtils.getNotificationUri((Class) cls, action, (SQLOperator[]) null), null, true);
        }
    }

    public TableNotifierRegister newRegister() {
        return new FlowContentTableNotifierRegister();
    }
}
