package com.mevo.app.presentation.dialogs;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.mevo.app.C0434R;

public class ResumeDrivingDialog extends BaseDialogFragment {
    private static final String TAG = "ResumeDialogView";
    private Button closeButton;
    private ResumeListener listener;
    private AppCompatButton resumeButton;

    public interface ResumeListener {
        void onCloseButtonClick();

        void onResumeButtonClick();
    }

    public static ResumeDrivingDialog newInstance() {
        return new ResumeDrivingDialog();
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.view_resume_drive, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        return layoutInflater;
    }

    private void findViews(View view) {
        this.closeButton = (Button) view.findViewById(C0434R.id.resume_dialog_close_button);
        this.resumeButton = (AppCompatButton) view.findViewById(C0434R.id.resume_dialog_view_button);
    }

    private void setListeners() {
        this.closeButton.setOnClickListener(new ResumeDrivingDialog$$Lambda$0(this));
        this.resumeButton.setOnClickListener(new ResumeDrivingDialog$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$setListeners$145$ResumeDrivingDialog(View view) {
        if (this.listener != null) {
            this.listener.onCloseButtonClick();
        }
        dismiss();
    }

    final /* synthetic */ void lambda$setListeners$146$ResumeDrivingDialog(View view) {
        if (this.listener != null) {
            this.listener.onResumeButtonClick();
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

    public ResumeDrivingDialog setListener(ResumeListener resumeListener) {
        this.listener = resumeListener;
        return this;
    }
}
