package com.mevo.app.presentation.entry;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.mevo.app.presentation.base.BaseFragment;

public abstract class EntryFragment extends BaseFragment {
    private static final String TAG = "EntryFragment";
    protected EntryActivityInterface activityInterface;

    @Nullable
    public EntryActivityInterface getActivityInterface() {
        if (this.activityInterface == null) {
            Log.d(TAG, String.format("Fragment %s accessing activity interaction when detached. Returning null", new Object[]{getClass().getSimpleName()}));
        }
        return this.activityInterface;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EntryActivityInterface) {
            this.activityInterface = (EntryActivityInterface) context;
            return;
        }
        throw new IllegalStateException("Parent activity must implement proper interface");
    }

    public void onDetach() {
        super.onDetach();
        this.activityInterface = null;
    }
}
