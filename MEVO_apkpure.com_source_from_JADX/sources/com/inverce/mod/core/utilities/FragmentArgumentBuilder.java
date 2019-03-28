package com.inverce.mod.core.utilities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public class FragmentArgumentBuilder<T extends Fragment> extends SubBundleBuilder<T> {
    public FragmentArgumentBuilder(@NonNull T t) {
        super(t, t.getArguments() != null ? t.getArguments() : new Bundle());
    }

    public T create() {
        ((Fragment) this.parent).setArguments(this.extras);
        return (Fragment) super.create();
    }
}
