package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.Preconditions;

public class SupportErrorDialogFragment extends DialogFragment {
    private Dialog mDialog = null;
    private OnCancelListener zzap = null;

    public static SupportErrorDialogFragment newInstance(Dialog dialog) {
        return newInstance(dialog, null);
    }

    public static SupportErrorDialogFragment newInstance(Dialog dialog, OnCancelListener onCancelListener) {
        SupportErrorDialogFragment supportErrorDialogFragment = new SupportErrorDialogFragment();
        dialog = (Dialog) Preconditions.checkNotNull(dialog, "Cannot display null dialog");
        dialog.setOnCancelListener(null);
        dialog.setOnDismissListener(null);
        supportErrorDialogFragment.mDialog = dialog;
        if (onCancelListener != null) {
            supportErrorDialogFragment.zzap = onCancelListener;
        }
        return supportErrorDialogFragment;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.zzap != null) {
            this.zzap.onCancel(dialogInterface);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (this.mDialog == null) {
            setShowsDialog(false);
        }
        return this.mDialog;
    }

    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }
}