package com.raizlabs.android.dbflow.runtime;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DirectModelNotifier implements ModelNotifier {
    private static DirectModelNotifier notifier;
    private final Map<Class<?>, Set<OnModelStateChangedListener>> modelChangedListenerMap = new LinkedHashMap();
    private final TableNotifierRegister singleRegister = new DirectTableNotifierRegister();
    private final Map<Class<?>, Set<OnTableChangedListener>> tableChangedListenerMap = new LinkedHashMap();

    public interface OnModelStateChangedListener<T> {
        void onModelChanged(@NonNull T t, @NonNull Action action);
    }

    private class DirectTableNotifierRegister implements TableNotifierRegister {
        private final OnTableChangedListener internalChangeListener;
        @Nullable
        private OnTableChangedListener modelChangedListener;
        private List<Class> registeredTables;

        /* renamed from: com.raizlabs.android.dbflow.runtime.DirectModelNotifier$DirectTableNotifierRegister$1 */
        class C08691 implements OnTableChangedListener {
            C08691() {
            }

            public void onTableChanged(@Nullable Class<?> cls, @NonNull Action action) {
                if (DirectTableNotifierRegister.this.modelChangedListener != null) {
                    DirectTableNotifierRegister.this.modelChangedListener.onTableChanged(cls, action);
                }
            }
        }

        private DirectTableNotifierRegister() {
            this.registeredTables = new ArrayList();
            this.internalChangeListener = new C08691();
        }

        public <T> void register(@NonNull Class<T> cls) {
            this.registeredTables.add(cls);
            DirectModelNotifier.this.registerForTableChanges(cls, this.internalChangeListener);
        }

        public <T> void unregister(@NonNull Class<T> cls) {
            this.registeredTables.remove(cls);
            DirectModelNotifier.this.unregisterForTableChanges(cls, this.internalChangeListener);
        }

        public void unregisterAll() {
            for (Class unregisterForTableChanges : this.registeredTables) {
                DirectModelNotifier.this.unregisterForTableChanges(unregisterForTableChanges, this.internalChangeListener);
            }
            this.modelChangedListener = null;
        }

        public void setListener(@Nullable OnTableChangedListener onTableChangedListener) {
            this.modelChangedListener = onTableChangedListener;
        }

        public boolean isSubscribed() {
            return this.registeredTables.isEmpty() ^ 1;
        }
    }

    public interface ModelChangedListener<T> extends OnModelStateChangedListener<T>, OnTableChangedListener {
    }

    @NonNull
    public static DirectModelNotifier get() {
        if (notifier == null) {
            notifier = new DirectModelNotifier();
        }
        return notifier;
    }

    private DirectModelNotifier() {
        if (notifier != null) {
            throw new IllegalStateException("Cannot instantiate more than one DirectNotifier. Use DirectNotifier.get()");
        }
    }

    public <T> void notifyModelChanged(@NonNull T t, @NonNull ModelAdapter<T> modelAdapter, @NonNull Action action) {
        Set<OnModelStateChangedListener> set = (Set) this.modelChangedListenerMap.get(modelAdapter.getModelClass());
        if (set != null) {
            for (OnModelStateChangedListener onModelStateChangedListener : set) {
                if (onModelStateChangedListener != null) {
                    onModelStateChangedListener.onModelChanged(t, action);
                }
            }
        }
    }

    public <T> void notifyTableChanged(@NonNull Class<T> cls, @NonNull Action action) {
        Set<OnTableChangedListener> set = (Set) this.tableChangedListenerMap.get(cls);
        if (set != null) {
            for (OnTableChangedListener onTableChangedListener : set) {
                if (onTableChangedListener != null) {
                    onTableChangedListener.onTableChanged(cls, action);
                }
            }
        }
    }

    public TableNotifierRegister newRegister() {
        return this.singleRegister;
    }

    public <T> void registerForModelChanges(@NonNull Class<T> cls, @NonNull ModelChangedListener<T> modelChangedListener) {
        registerForModelStateChanges(cls, modelChangedListener);
        registerForTableChanges(cls, modelChangedListener);
    }

    public <T> void registerForModelStateChanges(@NonNull Class<T> cls, @NonNull OnModelStateChangedListener<T> onModelStateChangedListener) {
        Set set = (Set) this.modelChangedListenerMap.get(cls);
        if (set == null) {
            set = new LinkedHashSet();
            this.modelChangedListenerMap.put(cls, set);
        }
        set.add(onModelStateChangedListener);
    }

    public <T> void registerForTableChanges(@NonNull Class<T> cls, @NonNull OnTableChangedListener onTableChangedListener) {
        Set set = (Set) this.tableChangedListenerMap.get(cls);
        if (set == null) {
            set = new LinkedHashSet();
            this.tableChangedListenerMap.put(cls, set);
        }
        set.add(onTableChangedListener);
    }

    public <T> void unregisterForModelChanges(@NonNull Class<T> cls, @NonNull ModelChangedListener<T> modelChangedListener) {
        unregisterForModelStateChanges(cls, modelChangedListener);
        unregisterForTableChanges(cls, modelChangedListener);
    }

    public <T> void unregisterForModelStateChanges(@NonNull Class<T> cls, @NonNull OnModelStateChangedListener<T> onModelStateChangedListener) {
        Set set = (Set) this.modelChangedListenerMap.get(cls);
        if (set != null) {
            set.remove(onModelStateChangedListener);
        }
    }

    public <T> void unregisterForTableChanges(@NonNull Class<T> cls, @NonNull OnTableChangedListener onTableChangedListener) {
        Set set = (Set) this.tableChangedListenerMap.get(cls);
        if (set != null) {
            set.remove(onTableChangedListener);
        }
    }
}
