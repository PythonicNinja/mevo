package com.mevo.app.presentation.register;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.mevo.app.presentation.base.BaseFragment;

public abstract class RegisterFragment extends BaseFragment {
    private static final String TAG = "RegisterFragment";
    protected RegisterActivityInterface activityInterface;

    @Nullable
    public RegisterActivityInterface getActivityInterface() {
        if (this.activityInterface == null) {
            Log.d(TAG, String.format("Fragment %s accessing activity interaction when detached. Returning null", new Object[]{getClass().getSimpleName()}));
        }
        return this.activityInterface;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegisterActivityInterface) {
            this.activityInterface = (RegisterActivityInterface) context;
            return;
        }
        throw new IllegalStateException("Parent activity must implement proper interface");
    }

    public void onDetach() {
        super.onDetach();
        this.activityInterface = null;
    }
}
