package com.raizlabs.android.dbflow.runtime;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

public interface ModelNotifier {
    TableNotifierRegister newRegister();

    <T> void notifyModelChanged(@NonNull T t, @NonNull ModelAdapter<T> modelAdapter, @NonNull Action action);

    <T> void notifyTableChanged(@NonNull Class<T> cls, @NonNull Action action);
}
