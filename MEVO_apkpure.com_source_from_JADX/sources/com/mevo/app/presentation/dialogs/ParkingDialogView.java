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
import android.widget.TextView;
import com.mevo.app.C0434R;
import com.mevo.app.tools.SpanUtils;

public class ParkingDialogView extends BaseDialogFragment {
    private static final String TAG = "ParkingDialogView";
    private Button closeButton;
    private ParkingListener listener;
    private Button parkButton;
    private TextView parkInfoText;

    public interface ParkingListener {
        void onCloseButtonClick();

        void onParkButtonClick();
    }

    public static ParkingDialogView newInstance() {
        return new ParkingDialogView();
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.parking_dialog_view, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        return layoutInflater;
    }

    private void findViews(View view) {
        this.parkInfoText = (TextView) view.findViewById(C0434R.id.parking_dialog_instruction_text);
        this.closeButton = (Button) view.findViewById(C0434R.id.parking_dialog_close_button);
        this.parkButton = (Button) view.findViewById(C0434R.id.parking_dialog_view_park_button);
        SpanUtils.on(this.parkInfoText).coloredText((int) C0434R.string.park_info_part_1, (int) C0434R.color.colorAccent).space().coloredText((int) C0434R.string.park_info_part_2, (int) C0434R.color.secondAccent).space().coloredText((int) C0434R.string.park_info_part_3, (int) C0434R.color.colorAccent).done();
    }

    private void setListeners() {
        this.closeButton.setOnClickListener(new ParkingDialogView$$Lambda$0(this));
        this.parkButton.setOnClickListener(new ParkingDialogView$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$setListeners$136$ParkingDialogView(View view) {
        if (this.listener != null) {
            this.listener.onCloseButtonClick();
        }
        dismiss();
    }

    final /* synthetic */ void lambda$setListeners$137$ParkingDialogView(View view) {
        if (this.listener != null) {
            this.listener.onParkButtonClick();
        }
    }

    public ParkingDialogView setListener(ParkingListener parkingListener) {
        this.listener = parkingListener;
        return this;
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
}
