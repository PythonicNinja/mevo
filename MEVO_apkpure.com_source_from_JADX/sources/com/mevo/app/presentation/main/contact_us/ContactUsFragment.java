package com.mevo.app.presentation.main.contact_us;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.base.BaseFragment;

public class ContactUsFragment extends BaseFragment {
    private View complaintAppealContainer;
    private String complaintAppealEmail;
    private TextView complaintAppealEmailText;
    private TextView contactHours;
    private String generalEmail;
    private View generalEmailContainer;
    private TextView generalEmailText;
    private boolean[] isEllipsized = new boolean[1];
    private View phoneContainer;
    private String phoneNumber;
    private TextView phoneText;

    final /* bridge */ /* synthetic */ void bridge$lambda$0$ContactUsFragment() {
        checkTextLine();
    }

    public static ContactUsFragment newInstance() {
        return new ContactUsFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_contact_us, viewGroup, false);
        findViews(layoutInflater);
        getData();
        setTexts();
        setListeners();
        return layoutInflater;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        checkTextLine();
    }

    private void findViews(View view) {
        this.phoneContainer = view.findViewById(C0434R.id.fragment_contact_us_phone_container);
        this.phoneText = (TextView) view.findViewById(C0434R.id.contact_phone);
        this.contactHours = (TextView) view.findViewById(C0434R.id.contact_hours);
        this.generalEmailContainer = view.findViewById(C0434R.id.fragment_contact_us_general_email_container);
        this.generalEmailText = (TextView) view.findViewById(C0434R.id.contact_email);
        this.complaintAppealEmailText = (TextView) view.findViewById(C0434R.id.contact_complaint_appeal_mail);
        this.complaintAppealContainer = view.findViewById(C0434R.id.fragment_contact_us_complaint_appeal_container);
    }

    private void getData() {
        this.phoneNumber = getString(C0434R.string.contact_phone_number);
        this.generalEmail = getString(C0434R.string.contact_email_address);
        this.complaintAppealEmail = getString(C0434R.string.contact_email_complaint_appeal);
    }

    private void setTexts() {
        this.phoneText.setText(getString(C0434R.string.contact_phone, this.phoneNumber));
        this.generalEmailText.setText(getString(C0434R.string.contact_email_label, this.generalEmail));
        this.complaintAppealEmailText.setText(getString(C0434R.string.contact_email_label, this.complaintAppealEmail));
    }

    public void onResume() {
        super.onResume();
        onToolbarInterface(ContactUsFragment$$Lambda$0.$instance);
    }

    public void onPause() {
        super.onPause();
        onToolbarInterface(ContactUsFragment$$Lambda$1.$instance);
    }

    private void checkTextLine() {
        this.contactHours.getViewTreeObserver().addOnGlobalLayoutListener(new ContactUsFragment$$Lambda$2(this));
    }

    final /* synthetic */ void lambda$checkTextLine$193$ContactUsFragment() {
        this.isEllipsized[0] = false;
        Layout layout = this.contactHours.getLayout();
        if (layout != null) {
            int lineCount = layout.getLineCount();
            if (lineCount > 0) {
                for (int i = 0; i < lineCount; i++) {
                    if (layout.getEllipsisCount(i) > 0) {
                        this.isEllipsized[0] = true;
                        break;
                    }
                }
            }
            this.contactHours.getViewTreeObserver().removeOnGlobalLayoutListener(new ContactUsFragment$$Lambda$6(this));
            setCorrectString();
        }
    }

    private void setCorrectString() {
        if (this.isEllipsized[0]) {
            this.contactHours.setMaxLines(1);
            this.contactHours.setText(IM.resources().getString(C0434R.string.contact_phone, new Object[]{this.phoneNumber}));
            this.phoneText.setMaxLines(2);
            this.phoneText.setText(IM.resources().getString(C0434R.string.contact_hours));
            checkStringAgain();
        }
    }

    private void checkStringAgain() {
        checkTextLine();
        if (this.isEllipsized[0]) {
            this.phoneText.setMaxLines(3);
            this.phoneText.setText(IM.resources().getString(C0434R.string.contact_hours_2));
        }
    }

    private void setListeners() {
        this.phoneContainer.setOnClickListener(new ContactUsFragment$$Lambda$3(this));
        this.generalEmailContainer.setOnClickListener(new ContactUsFragment$$Lambda$4(this));
        this.complaintAppealContainer.setOnClickListener(new ContactUsFragment$$Lambda$5(this));
    }

    final /* synthetic */ void lambda$setListeners$194$ContactUsFragment(View view) {
        startActivity(new Intent("android.intent.action.DIAL", Uri.fromParts("tel", this.phoneNumber, null)));
    }

    final /* synthetic */ void lambda$setListeners$195$ContactUsFragment(View view) {
        view = new Intent("android.intent.action.SEND");
        view.setType("plain/text");
        view.putExtra("android.intent.extra.EMAIL", new String[]{this.generalEmail});
        startActivity(Intent.createChooser(view, ""));
    }

    final /* synthetic */ void lambda$setListeners$196$ContactUsFragment(View view) {
        view = new Intent("android.intent.action.SEND");
        view.setType("plain/text");
        view.putExtra("android.intent.extra.EMAIL", new String[]{this.complaintAppealEmail});
        startActivity(Intent.createChooser(view, ""));
    }
}
