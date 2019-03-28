package com.mevo.app.presentation.dialogs;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.main.MainActivityInterface;
import com.mevo.app.tools.CustomTypefaceSpan;
import com.mevo.app.tools.Utils;

public class RentBikeDialog extends BaseDialogFragment {
    private static final String ARG_BIKE_NUMBER = "ARG_BIKE_NUMBER";
    private static final String ARG_LOCK_CODE = "ARG_LOCK_CODE";
    private TextView bikeNumberText;
    private Button closeButton;

    public static RentBikeDialog newInstance(String str) {
        RentBikeDialog rentBikeDialog = new RentBikeDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_BIKE_NUMBER, str);
        rentBikeDialog.setArguments(bundle);
        return rentBikeDialog;
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.dialog_rent, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        bindData();
        return layoutInflater;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if ((getActivity() instanceof MainActivityInterface) != null) {
            ((MainActivityInterface) getActivity()).goToHome();
        }
    }

    private void findViews(View view) {
        this.bikeNumberText = (TextView) view.findViewById(C0434R.id.dialog_rent_rent_info);
        this.closeButton = (Button) view.findViewById(C0434R.id.rent_dialog_close_button);
    }

    private void setListeners() {
        this.closeButton.setOnClickListener(new RentBikeDialog$$Lambda$0(this));
    }

    final /* synthetic */ void lambda$setListeners$144$RentBikeDialog(View view) {
        dismissAllowingStateLoss();
    }

    private void bindData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String emptyIfNull = Utils.emptyIfNull(arguments.getString(ARG_BIKE_NUMBER));
            Object string = getContext().getString(C0434R.string.dialog_rent_bike_rent_info, new Object[]{emptyIfNull});
            Typeface create = Typeface.create(ResourcesCompat.getFont(getContext(), C0434R.font.roboto_medium), 0);
            CharSequence spannableString = new SpannableString(string);
            int indexOf = string.indexOf(emptyIfNull);
            spannableString.setSpan(new CustomTypefaceSpan("roboto_medium.ttf", create), indexOf, emptyIfNull.length() + indexOf, 34);
            this.bikeNumberText.setText(spannableString);
        }
    }
}
