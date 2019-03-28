package com.mevo.app.presentation.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.mevo.app.presentation.base.BaseFragment;

public abstract class MainFragment extends BaseFragment {
    private static final String TAG = "MainFragment";
    protected MainActivityInterface activityInterface;

    @Nullable
    public MainActivityInterface getActivityInterface() {
        if (this.activityInterface == null) {
            Log.d(TAG, String.format("Fragment %s accessing activity interaction when detached. Returning null", new Object[]{getClass().getSimpleName()}));
        }
        return this.activityInterface;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityInterface) {
            this.activityInterface = (MainActivityInterface) context;
            return;
        }
        throw new IllegalStateException("Parent activity must implement proper interface");
    }

    public void onDetach() {
        super.onDetach();
        this.activityInterface = null;
    }
}
