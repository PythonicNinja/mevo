package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
public class DependencyCycleException extends DependencyException {
    private final List<Component<?>> zza;

    @KeepForSdk
    public DependencyCycleException(List<Component<?>> list) {
        StringBuilder stringBuilder = new StringBuilder("Dependency cycle detected: ");
        stringBuilder.append(Arrays.toString(list.toArray()));
        super(stringBuilder.toString());
        this.zza = list;
    }

    @KeepForSdk
    public List<Component<?>> getComponentsInCycle() {
        return this.zza;
    }
}
