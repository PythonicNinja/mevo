package com.mevo.app.presentation.dialogs;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import com.mevo.app.tools.Lifecycle;
import com.mevo.app.tools.Log;

public abstract class BaseDialogFragment extends DialogFragment {
    private static final String TAG = "BaseDialogFragment";

    public void show() {
        try {
            AppCompatActivity supportActivity = Lifecycle.getSupportActivity();
            if (supportActivity != null) {
                show(supportActivity.getSupportFragmentManager(), null);
            } else {
                Log.m90e(TAG, "Received null activity");
            }
        } catch (Throwable e) {
            Log.ex(TAG, "Couldn't show dialog", e);
        }
    }
}
