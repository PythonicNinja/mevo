package com.mevo.app.presentation.dialogs;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.main.MainActivityInterface;
import com.mevo.app.presentation.main.rent_history.RentHistoryFragment;
import com.mevo.app.presentation.main.report_problem.ReportProblemFragment;
import com.mevo.app.tools.CustomTypefaceSpan;

public class ReturnSuccessDialog extends BaseDialogFragment {
    private static final String ARG_BIKE_NUMBER = "ARG_BIKE_NUMBER";
    String bikeNumber;
    private Button closeButton;
    private TextView infoText;
    private Button reportProblemButton;
    private Button showDetailsButton;

    public static ReturnSuccessDialog newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_BIKE_NUMBER, str);
        str = new ReturnSuccessDialog();
        str.setArguments(bundle);
        return str;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.bikeNumber = getArguments().getString(ARG_BIKE_NUMBER, "");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.dialog_return, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        bindData();
        return layoutInflater;
    }

    private void findViews(View view) {
        this.closeButton = (Button) view.findViewById(C0434R.id.dialog_return_close_button);
        this.reportProblemButton = (Button) view.findViewById(C0434R.id.dialog_return_report_problem_button);
        this.showDetailsButton = (Button) view.findViewById(C0434R.id.dialog_return_show_details_button);
        this.infoText = (TextView) view.findViewById(C0434R.id.dialog_return_rent_info);
    }

    private void bindData() {
        if (getArguments() != null) {
            Object string = getContext().getString(C0434R.string.dialog_return_bike_returned_info, new Object[]{this.bikeNumber});
            Typeface create = Typeface.create(ResourcesCompat.getFont(getContext(), C0434R.font.roboto_medium), 0);
            CharSequence spannableString = new SpannableString(string);
            int indexOf = string.indexOf(this.bikeNumber);
            spannableString.setSpan(new CustomTypefaceSpan("roboto_medium.ttf", create), indexOf, this.bikeNumber.length() + indexOf, 34);
            this.infoText.setText(spannableString);
        }
    }

    private void setListeners() {
        this.reportProblemButton.setOnClickListener(new ReturnSuccessDialog$$Lambda$0(this));
        this.closeButton.setOnClickListener(new ReturnSuccessDialog$$Lambda$1(this));
        this.showDetailsButton.setOnClickListener(new ReturnSuccessDialog$$Lambda$2(this));
    }

    final /* synthetic */ void lambda$setListeners$150$ReturnSuccessDialog(View view) {
        reportProblem();
        dismiss();
    }

    final /* synthetic */ void lambda$setListeners$151$ReturnSuccessDialog(View view) {
        dismiss();
    }

    final /* synthetic */ void lambda$setListeners$152$ReturnSuccessDialog(View view) {
        goToDetails();
        dismiss();
    }

    private void goToDetails() {
        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivityInterface) {
            ((MainActivityInterface) activity).changeFragment(RentHistoryFragment.newInstance());
        }
    }

    private void reportProblem() {
        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivityInterface) {
            ((MainActivityInterface) activity).changeFragment(ReportProblemFragment.newInstance());
        }
    }
}
