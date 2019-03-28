package com.mevo.app.presentation.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.broadcast_receivers.ConnectivityBroadcastReceiver;
import com.mevo.app.broadcast_receivers.ConnectivityBroadcastReceiver.NetworkListener;
import com.mevo.app.tools.Lifecycle;
import com.mevo.app.tools.Lifecycle.State;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.NetTools;

public class NoInternetDialog extends BaseDialogFragment implements NetworkListener {
    private static final String TAG = "NoInternetDialog";
    public static boolean isShown = false;
    private Button closeButton;
    private ConnectivityBroadcastReceiver receiver;

    public static NoInternetDialog newInstance() {
        return new NoInternetDialog();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.dialog_no_internet, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        this.receiver = new ConnectivityBroadcastReceiver(this);
        App.getAppContext().registerReceiver(this.receiver, ConnectivityBroadcastReceiver.getIntentFilter());
        isShown = true;
        return layoutInflater;
    }

    private void findViews(View view) {
        this.closeButton = (Button) view.findViewById(C0434R.id.dialog_no_internet_close);
    }

    private void setListeners() {
        this.closeButton.setOnClickListener(new NoInternetDialog$$Lambda$0(this));
    }

    final /* synthetic */ void lambda$setListeners$135$NoInternetDialog(View view) {
        checkAndTryClose();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        App.getAppContext().unregisterReceiver(this.receiver);
        this.receiver.setListener(null);
        this.receiver = null;
        isShown = null;
        if (NetTools.isNetworkAvailable() == null) {
            newInstance().show();
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        bundle = super.onCreateDialog(bundle);
        bundle.setCanceledOnTouchOutside(false);
        bundle.setCancelable(false);
        return bundle;
    }

    private void checkAndTryClose() {
        try {
            dismiss();
        } catch (Throwable e) {
            Log.ex(TAG, e);
        }
    }

    public void onNetworkStatusChanged(boolean z) {
        if (z) {
            try {
                if (Lifecycle.getActivity() && Lifecycle.getState() == State.Resumed) {
                    dismiss();
                }
            } catch (boolean z2) {
                Log.ex(TAG, z2);
            }
        }
    }
}
