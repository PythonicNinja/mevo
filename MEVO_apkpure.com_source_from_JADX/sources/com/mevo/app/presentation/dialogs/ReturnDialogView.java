package com.mevo.app.presentation.dialogs;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.mevo.app.C0434R;

public class ReturnDialogView extends BaseDialogFragment {
    private static final String TAG = "ReturnDialogView";
    private Button closeButton;
    private ReturnListener listener;

    public interface ReturnListener {
        void onCloseButtonClick();
    }

    public static ReturnDialogView newInstance() {
        return new ReturnDialogView();
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.return_dialog_view, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        return layoutInflater;
    }

    private void findViews(View view) {
        this.closeButton = (Button) view.findViewById(C0434R.id.return_dialog_close_button);
    }

    private void setListeners() {
        this.closeButton.setOnClickListener(new ReturnDialogView$$Lambda$0(this));
    }

    final /* synthetic */ void lambda$setListeners$149$ReturnDialogView(View view) {
        if (this.listener != null) {
            this.listener.onCloseButtonClick();
        }
        dismiss();
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

    public ReturnDialogView setListener(ReturnListener returnListener) {
        this.listener = returnListener;
        return this;
    }
}
