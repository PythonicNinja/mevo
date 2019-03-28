package com.mevo.app.presentation.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mevo.app.C0434R;
import com.mevo.app.tools.Utils;

public class QrScanResultDialog extends BaseDialogFragment {
    private static final String ARG_BIKE_NUMBER = "ARG_BIKE_NUMBER";
    private TextView bikeNumberText;
    private Button cancelButton;
    private DecisionCallback decisionCallback;
    private Button rentBikeButton;

    public interface DecisionCallback {
        void onDecision(boolean z);
    }

    public static QrScanResultDialog newInstance(String str, DecisionCallback decisionCallback) {
        QrScanResultDialog qrScanResultDialog = new QrScanResultDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_BIKE_NUMBER, str);
        qrScanResultDialog.setArguments(bundle);
        qrScanResultDialog.setDecisionCallback(decisionCallback);
        return qrScanResultDialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.dialog_scan_result, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        fillData();
        return layoutInflater;
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        if (this.decisionCallback != null) {
            this.decisionCallback.onDecision(false);
        }
    }

    private void findViews(View view) {
        this.rentBikeButton = (Button) view.findViewById(C0434R.id.dialog_scan_result_rent_button);
        this.cancelButton = (Button) view.findViewById(C0434R.id.dialog_scan_result_cancel_button);
        this.bikeNumberText = (TextView) view.findViewById(C0434R.id.dialog_scan_result_bike_number_textview);
    }

    private void setListeners() {
        this.rentBikeButton.setOnClickListener(new QrScanResultDialog$$Lambda$0(this));
        this.cancelButton.setOnClickListener(new QrScanResultDialog$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$setListeners$142$QrScanResultDialog(View view) {
        dismiss();
        if (this.decisionCallback != null) {
            this.decisionCallback.onDecision(true);
        }
    }

    final /* synthetic */ void lambda$setListeners$143$QrScanResultDialog(View view) {
        dismiss();
        if (this.decisionCallback != null) {
            this.decisionCallback.onDecision(false);
        }
    }

    private void fillData() {
        if (getArguments() != null) {
            this.bikeNumberText.setText(Utils.emptyIfNull(getArguments().getString(ARG_BIKE_NUMBER)));
        }
    }

    public void setDecisionCallback(DecisionCallback decisionCallback) {
        this.decisionCallback = decisionCallback;
    }
}
