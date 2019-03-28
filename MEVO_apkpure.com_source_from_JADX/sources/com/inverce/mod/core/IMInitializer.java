package com.inverce.mod.core;

import android.content.Context;
import android.support.annotation.NonNull;
import com.inverce.mod.core.internal.IMInternal;

public class IMInitializer {
    public static void initialize(@NonNull Context context) {
        IMInternal.get().initialize(context);
    }
}
