package com.mevo.app.presentation.dialogs;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.mevo.app.C0434R;

public class TopUpDialog extends BaseDialogFragment {
    private static final String TAG = "TopUpDialogView";
    private Button cancelButton;
    private TextInputEditText editAmountEt;
    private TopUpListener listener;
    private Button minusButton;
    private Button plusButton;
    private Button topUpButton;

    public interface TopUpListener {
        void onTopUpClick(String str);
    }

    public static TopUpDialog newInstance() {
        return new TopUpDialog();
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.view_top_up, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        return layoutInflater;
    }

    private void findViews(View view) {
        this.cancelButton = (Button) view.findViewById(C0434R.id.view_top_up_cancel_button);
        this.editAmountEt = (TextInputEditText) view.findViewById(C0434R.id.view_top_up_edit_amount);
        this.plusButton = (Button) view.findViewById(C0434R.id.view_top_up_plus_button);
        this.minusButton = (Button) view.findViewById(C0434R.id.view_top_up_minus_button);
        this.topUpButton = (Button) view.findViewById(C0434R.id.view_top_up_top_up_button);
        disableEditText(this.editAmountEt);
    }

    private void disableEditText(EditText editText) {
        editText.setKeyListener(null);
    }

    private void setListeners() {
        this.cancelButton.setOnClickListener(new TopUpDialog$$Lambda$0(this));
        this.plusButton.setOnClickListener(new TopUpDialog$$Lambda$1(this));
        this.minusButton.setOnClickListener(new TopUpDialog$$Lambda$2(this));
        this.topUpButton.setOnClickListener(new TopUpDialog$$Lambda$3(this));
    }

    final /* synthetic */ void lambda$setListeners$153$TopUpDialog(View view) {
        dismiss();
    }

    final /* synthetic */ void lambda$setListeners$154$TopUpDialog(View view) {
        this.editAmountEt.setText(String.valueOf(Integer.valueOf(this.editAmountEt.getText().toString()).intValue() + 1));
    }

    final /* synthetic */ void lambda$setListeners$155$TopUpDialog(View view) {
        decrementValueIfPossible();
    }

    final /* synthetic */ void lambda$setListeners$156$TopUpDialog(View view) {
        if (this.listener != null) {
            this.listener.onTopUpClick(this.editAmountEt.getText().toString());
            dismiss();
        }
    }

    private void decrementValueIfPossible() {
        if (Integer.valueOf(this.editAmountEt.getText().toString()).intValue() <= 1) {
            this.editAmountEt.setText("1");
        } else if (Integer.valueOf(this.editAmountEt.getText().toString()).intValue() > 1) {
            this.editAmountEt.setText(String.valueOf(Integer.valueOf(this.editAmountEt.getText().toString()).intValue() - 1));
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        View relativeLayout = new RelativeLayout(getActivity());
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        bundle = super.onCreateDialog(bundle);
        bundle.requestWindowFeature(1);
        bundle.setContentView(relativeLayout);
        if (bundle.getWindow() != null) {
            bundle.getWindow().setLayout(-1, -2);
            bundle.getWindow().setGravity(17);
        }
        bundle.setCanceledOnTouchOutside(false);
        return bundle;
    }

    public void onResume() {
        Window window = getDialog().getWindow();
        Point point = new Point();
        if (window != null) {
            window.getWindowManager().getDefaultDisplay().getSize(point);
            window.setLayout((int) (((double) point.x) * 0.85d), -2);
            window.setGravity(17);
        }
        super.onResume();
    }

    public TopUpDialog setListener(TopUpListener topUpListener) {
        this.listener = topUpListener;
        return this;
    }
}
