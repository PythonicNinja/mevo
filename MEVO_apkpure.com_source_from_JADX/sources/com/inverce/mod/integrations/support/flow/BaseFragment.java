package com.inverce.mod.integrations.support.flow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.inverce.mod.core.interfaces.LifecycleState;
import com.inverce.mod.core.utilities.SubBundleBuilder;

public class BaseFragment extends Fragment {
    @NonNull
    LifecycleState lifecycleState = LifecycleState.NotCreated;

    @Nullable
    public SubBundleBuilder<BaseFragment> setArguments() {
        return new SubBundleBuilder(this, getArguments(), new BaseFragment$$Lambda$0(this));
    }

    @NonNull
    public LifecycleState getLifecycleState() {
        return this.lifecycleState;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.lifecycleState = LifecycleState.Created;
    }

    public void onStart() {
        super.onStart();
        this.lifecycleState = LifecycleState.Started;
    }

    public void onResume() {
        super.onResume();
        this.lifecycleState = LifecycleState.Resumed;
    }

    public void onPause() {
        super.onPause();
        this.lifecycleState = LifecycleState.Paused;
    }

    public void onStop() {
        super.onStop();
        this.lifecycleState = LifecycleState.Stopped;
    }

    public void onDestroy() {
        super.onDestroy();
        this.lifecycleState = LifecycleState.Destroyed;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.lifecycleState = LifecycleState.SaveInstanceState;
    }
}
