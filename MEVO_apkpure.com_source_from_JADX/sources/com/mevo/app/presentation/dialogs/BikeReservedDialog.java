package com.mevo.app.presentation.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.main.MainActivityInterface;

public class BikeReservedDialog extends BaseDialogFragment {
    private static final String ARG_BIKE_NUMBER = "ARG_BIKE_NUMBER";
    private TextView bikeNumberText;
    private Button closeButton;
    private FinishListener finishListener;

    public interface FinishListener {
        void onFinished();
    }

    public static BikeReservedDialog newInstance(String str) {
        BikeReservedDialog bikeReservedDialog = new BikeReservedDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_BIKE_NUMBER, str);
        bikeReservedDialog.setArguments(bundle);
        return bikeReservedDialog;
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.dialog_bike_reserved, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        return layoutInflater;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if ((getActivity() instanceof MainActivityInterface) != null) {
            ((MainActivityInterface) getActivity()).goToHome();
        }
    }

    private void findViews(View view) {
        this.bikeNumberText = (TextView) view.findViewById(C0434R.id.dialog_bike_reserved_info);
        this.closeButton = (Button) view.findViewById(C0434R.id.dialog_bike_reserved_close_btn);
    }

    private void setListeners() {
        this.closeButton.setOnClickListener(new BikeReservedDialog$$Lambda$0(this));
    }

    final /* synthetic */ void lambda$setListeners$131$BikeReservedDialog(View view) {
        dismissAllowingStateLoss();
        if (this.finishListener != null) {
            this.finishListener.onFinished();
        }
    }

    public BikeReservedDialog setFinishListener(FinishListener finishListener) {
        this.finishListener = finishListener;
        return this;
    }
}
