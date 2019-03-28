package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;

public abstract /* synthetic */ class ComponentContainer$$CC {
    @KeepForSdk
    public static Object get(ComponentContainer componentContainer, Class cls) {
        componentContainer = componentContainer.getProvider(cls);
        if (componentContainer == null) {
            return null;
        }
        return componentContainer.get();
    }
}
