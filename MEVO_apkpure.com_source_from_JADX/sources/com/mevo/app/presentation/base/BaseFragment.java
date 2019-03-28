package com.mevo.app.presentation.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.inverce.mod.core.functional.IConsumer;
import com.mevo.app.presentation.ToolbarActivityInterface;

public class BaseFragment extends Fragment {
    String TAG = getClass().getSimpleName();
    protected ToolbarActivityInterface toolbarActivityInterface;

    @Nullable
    public ToolbarActivityInterface getToolbarInterface() {
        if (this.toolbarActivityInterface == null) {
            Log.d(this.TAG, String.format("Fragment %s accessing activity interaction when detached. Returning null", new Object[]{getClass().getSimpleName()}));
        }
        return this.toolbarActivityInterface;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ToolbarActivityInterface) {
            this.toolbarActivityInterface = (ToolbarActivityInterface) context;
            return;
        }
        throw new IllegalStateException("Parent activity must implement proper interface");
    }

    public void onDetach() {
        super.onDetach();
        this.toolbarActivityInterface = null;
    }

    public void onToolbarInterface(IConsumer<ToolbarActivityInterface> iConsumer) {
        if (this.toolbarActivityInterface != null) {
            iConsumer.accept(this.toolbarActivityInterface);
        }
    }
}
