package com.inverce.mod.integrations.support.flow;

import android.content.Context;
import android.support.annotation.Nullable;

public class InteractionFragment<ACTIONS> extends BaseFragment {
    @Nullable
    protected ACTIONS actions;

    @Nullable
    public ACTIONS getActions() {
        return this.actions;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.actions = context;
        } catch (Context context2) {
            throw new IllegalStateException("Parent activity must implement proper interface", context2);
        }
    }

    public void onDetach() {
        super.onDetach();
        this.actions = null;
    }
}
