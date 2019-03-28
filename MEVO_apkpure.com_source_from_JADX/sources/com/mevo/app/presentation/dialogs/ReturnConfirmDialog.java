package com.mevo.app.presentation.dialogs;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mevo.app.C0434R;
import com.mevo.app.tools.CustomTypefaceSpan;
import com.mevo.app.tools.Utils;
import com.mevo.app.tools.Utils.SpanUtil;

public class ReturnConfirmDialog extends BaseDialogFragment {
    public static final String BIKE_NUMBER = "BIKE_NUMBER";
    public static final String STATION_NAME = "STATION_NAME";
    private Button closeButton;
    private Button confirmButton;
    private DecisionCallback decisionCallback;
    private TextView infoText;

    public interface DecisionCallback {
        void onDecision(boolean z);
    }

    public static ReturnConfirmDialog newInstance(String str, String str2, DecisionCallback decisionCallback) {
        ReturnConfirmDialog returnConfirmDialog = new ReturnConfirmDialog();
        Bundle bundle = new Bundle();
        bundle.putString(BIKE_NUMBER, str);
        bundle.putString(STATION_NAME, str2);
        returnConfirmDialog.setArguments(bundle);
        returnConfirmDialog.setDecisionCallback(decisionCallback);
        return returnConfirmDialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.dialog_return_confirm, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        fillTextViews();
        return layoutInflater;
    }

    private void findViews(View view) {
        this.closeButton = (Button) view.findViewById(C0434R.id.dialog_return_confirm_close_button);
        this.confirmButton = (Button) view.findViewById(C0434R.id.dialog_return_confirm_confirm_button);
        this.infoText = (TextView) view.findViewById(C0434R.id.dialog_return_confirm_data);
    }

    private void setListeners() {
        this.closeButton.setOnClickListener(new ReturnConfirmDialog$$Lambda$0(this));
        this.confirmButton.setOnClickListener(new ReturnConfirmDialog$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$setListeners$147$ReturnConfirmDialog(View view) {
        decide(null);
        dismiss();
    }

    final /* synthetic */ void lambda$setListeners$148$ReturnConfirmDialog(View view) {
        decide(true);
        dismiss();
    }

    private void fillTextViews() {
        String emptyIfNull = Utils.emptyIfNull(getArguments().getString(BIKE_NUMBER));
        String string = getArguments().getString(STATION_NAME);
        String string2 = getContext().getString(C0434R.string.dialog_should_return_data, new Object[]{emptyIfNull, string});
        Typeface create = Typeface.create(ResourcesCompat.getFont(getContext(), C0434R.font.roboto_medium), 0);
        this.infoText.setText(SpanUtil.setSpan(SpanUtil.setSpan(string2, emptyIfNull, new CustomTypefaceSpan("roboto_medium.ttf", create)), string2, string, new CustomTypefaceSpan("roboto_medium.ttf", create)));
    }

    private void decide(boolean z) {
        if (this.decisionCallback != null) {
            this.decisionCallback.onDecision(z);
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        decide(null);
    }

    public void setDecisionCallback(DecisionCallback decisionCallback) {
        this.decisionCallback = decisionCallback;
    }
}
