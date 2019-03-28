package com.mevo.app.presentation.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mevo.app.C0434R;

public class BikeReservationDialog extends BaseDialogFragment {
    private static final String ARG_BIKE_NUMBER = "ARG_BIKE_NUMBER";
    private String bikeNumber;
    private TextView closeButton;
    private ReservationDecisionListener reservationDecisionListener;
    private TextView reserveButton;

    public interface ReservationDecisionListener {
        void onBookingDecision(boolean z, String str);
    }

    public static BikeReservationDialog newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_BIKE_NUMBER, str);
        str = new BikeReservationDialog();
        str.setArguments(bundle);
        return str;
    }

    public void setDecisionListener(ReservationDecisionListener reservationDecisionListener) {
        this.reservationDecisionListener = reservationDecisionListener;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        bundle = getArguments();
        if (bundle != null) {
            this.bikeNumber = bundle.getString(ARG_BIKE_NUMBER, "");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.dialog_bike_reservation, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        return layoutInflater;
    }

    private void findViews(View view) {
        this.closeButton = (TextView) view.findViewById(C0434R.id.dialog_bike_reservation_close_btn);
        this.reserveButton = (TextView) view.findViewById(C0434R.id.dialog_bike_reservation_reserve_btn);
    }

    private void setListeners() {
        this.closeButton.setOnClickListener(new BikeReservationDialog$$Lambda$0(this));
        this.reserveButton.setOnClickListener(new BikeReservationDialog$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$setListeners$129$BikeReservationDialog(View view) {
        closeDialog();
    }

    final /* synthetic */ void lambda$setListeners$130$BikeReservationDialog(View view) {
        if (this.reservationDecisionListener != null) {
            this.reservationDecisionListener.onBookingDecision(true, this.bikeNumber);
        }
        dismiss();
    }

    private void closeDialog() {
        if (this.reservationDecisionListener != null) {
            this.reservationDecisionListener.onBookingDecision(false, "");
        }
        dismiss();
    }

    public void dismiss() {
        super.dismiss();
        this.reservationDecisionListener = null;
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        closeDialog();
    }
}
