package com.mevo.app.presentation.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.main.MainActivityInterface;
import com.mevo.app.presentation.main.user_details.UserDetailsFragment;

public class FillDetailsDialog extends BaseDialogFragment {
    private Button closeButton;
    private Button goToDetailsButton;

    public static FillDetailsDialog newInstance() {
        return new FillDetailsDialog();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.dialog_fill_details, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        return layoutInflater;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        bundle = super.onCreateDialog(bundle);
        bundle.setCanceledOnTouchOutside(false);
        bundle.setCancelable(false);
        return bundle;
    }

    private void findViews(View view) {
        this.closeButton = (Button) view.findViewById(C0434R.id.dialog_fill_details_close);
        this.goToDetailsButton = (Button) view.findViewById(C0434R.id.dialog_fill_details_ok);
    }

    private void setListeners() {
        this.closeButton.setOnClickListener(new FillDetailsDialog$$Lambda$0(this));
        this.goToDetailsButton.setOnClickListener(new FillDetailsDialog$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$setListeners$132$FillDetailsDialog(View view) {
        dismiss();
    }

    final /* synthetic */ void lambda$setListeners$133$FillDetailsDialog(View view) {
        dismiss();
        goToUserDetails();
    }

    private void goToUserDetails() {
        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivityInterface) {
            ((MainActivityInterface) activity).changeFragment(UserDetailsFragment.newInstance());
        }
    }
}
