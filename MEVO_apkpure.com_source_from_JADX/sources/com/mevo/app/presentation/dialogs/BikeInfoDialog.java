package com.mevo.app.presentation.dialogs;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.tools.CustomTypefaceSpan;

public class BikeInfoDialog extends BaseDialogFragment {
    private static final String ARG_BIKE_INFO = "ARG_BIKE_INFO";
    private static final String ARG_BIKE_NUMBER = "ARG_BIKE_NUMBER";
    private static final String ARG_LOCK_CODE = "ARG_LOCK_CODE";
    private static final String ARG_NEGATIVE_BUTTON_TEXT = "ARG_NEGATIVE_BUTTON_TEXT";
    private static final String ARG_NEUTRAL_BUTTON_TEXT = "ARG_NEUTRAL_BUTTON_TEXT";
    private static final String ARG_POSITIVE_BUTTON_TEXT = "ARG_POSITIVE_BUTTON_TEXT";
    private static final String ARG_STYLED = "ARG_STYLED";
    private static final String TAG = "InfoDialog";
    private TextView bikeInfoText;
    private Button negativeButton;
    private OnClickListener negativeButtonListener;
    private Button neutralButton;
    private OnClickListener neutralButtonListener;
    private Button positiveButton;
    private OnClickListener positiveButtonListener;
    private View spacePreNeutral;
    private View spacePrePositive;

    public static class Builder {
        private Bundle arguments = new Bundle();
        private BikeInfoDialog infoDialog = BikeInfoDialog.newInstance();

        public Builder setBikeInfo(String str, String str2) {
            this.arguments.putString(BikeInfoDialog.ARG_BIKE_INFO, str);
            this.arguments.putString(BikeInfoDialog.ARG_BIKE_NUMBER, str2);
            return this;
        }

        public Builder setNeutralButton(@NonNull String str, OnClickListener onClickListener) {
            this.arguments.putString(BikeInfoDialog.ARG_NEUTRAL_BUTTON_TEXT, str);
            this.infoDialog.neutralButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(@NonNull String str, OnClickListener onClickListener) {
            this.arguments.putString(BikeInfoDialog.ARG_NEGATIVE_BUTTON_TEXT, str);
            this.infoDialog.negativeButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(@NonNull String str, OnClickListener onClickListener) {
            this.arguments.putString(BikeInfoDialog.ARG_POSITIVE_BUTTON_TEXT, str);
            this.infoDialog.positiveButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(@StringRes int i, OnClickListener onClickListener) {
            return setNeutralButton(IM.resources().getString(i), onClickListener);
        }

        public Builder setNegativeButton(@StringRes int i, OnClickListener onClickListener) {
            return setNegativeButton(IM.resources().getString(i), onClickListener);
        }

        public Builder setPositiveButton(@StringRes int i, OnClickListener onClickListener) {
            return setPositiveButton(IM.resources().getString(i), onClickListener);
        }

        public Builder setStyled() {
            this.arguments.putBoolean(BikeInfoDialog.ARG_STYLED, true);
            return this;
        }

        public BikeInfoDialog build() {
            this.infoDialog.setArguments(this.arguments);
            return this.infoDialog;
        }
    }

    public interface OnClickListener {
        void onClick(View view);
    }

    private static BikeInfoDialog newInstance() {
        return new BikeInfoDialog();
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        bundle = getArguments();
        if (bundle == null) {
            dismiss();
            return layoutInflater.inflate(C0434R.layout.dialog_bike_info, viewGroup, false);
        }
        viewGroup = layoutInflater.inflate(C0434R.layout.dialog_bike_info, viewGroup, false);
        findViews(viewGroup);
        layoutInflater = Typeface.create(ResourcesCompat.getFont(layoutInflater.getContext(), C0434R.font.roboto_medium), 0);
        String string = bundle.getString(ARG_BIKE_NUMBER);
        CharSequence string2 = bundle.getString(ARG_BIKE_INFO);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(string2)) {
                this.bikeInfoText.setVisibility(8);
                setActionButton(viewGroup, bundle.getString(ARG_NEUTRAL_BUTTON_TEXT), this.neutralButtonListener, C0434R.id.dialog_bike_info_neutral_btn);
                setActionButton(viewGroup, bundle.getString(ARG_NEGATIVE_BUTTON_TEXT), this.negativeButtonListener, C0434R.id.dialog_bike_info_negative_btn);
                setActionButton(viewGroup, bundle.getString(ARG_POSITIVE_BUTTON_TEXT), this.positiveButtonListener, C0434R.id.dialog_bike_info_positive_btn);
                this.spacePrePositive.setVisibility(this.positiveButton.getVisibility());
                this.spacePreNeutral.setVisibility(this.neutralButton.getVisibility());
                return viewGroup;
            }
        }
        CharSequence spannableString = new SpannableString(string2);
        int indexOf = string2.indexOf(string);
        if (indexOf < 0 || string.length() + indexOf >= string2.length()) {
            this.bikeInfoText.setText(string2);
        } else {
            spannableString.setSpan(new CustomTypefaceSpan("roboto_medium.ttf", layoutInflater), indexOf, string.length() + indexOf, 34);
            this.bikeInfoText.setText(spannableString);
        }
        this.bikeInfoText.setVisibility(0);
        setActionButton(viewGroup, bundle.getString(ARG_NEUTRAL_BUTTON_TEXT), this.neutralButtonListener, C0434R.id.dialog_bike_info_neutral_btn);
        setActionButton(viewGroup, bundle.getString(ARG_NEGATIVE_BUTTON_TEXT), this.negativeButtonListener, C0434R.id.dialog_bike_info_negative_btn);
        setActionButton(viewGroup, bundle.getString(ARG_POSITIVE_BUTTON_TEXT), this.positiveButtonListener, C0434R.id.dialog_bike_info_positive_btn);
        this.spacePrePositive.setVisibility(this.positiveButton.getVisibility());
        this.spacePreNeutral.setVisibility(this.neutralButton.getVisibility());
        return viewGroup;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.neutralButtonListener != null) {
            this.neutralButtonListener.onClick(null);
        } else if (this.negativeButtonListener != null) {
            this.negativeButtonListener.onClick(null);
        }
        super.onCancel(dialogInterface);
    }

    private void findViews(View view) {
        this.bikeInfoText = (TextView) view.findViewById(C0434R.id.dialog_bike_info_message_bike);
        this.positiveButton = (Button) view.findViewById(C0434R.id.dialog_bike_info_positive_btn);
        this.neutralButton = (Button) view.findViewById(C0434R.id.dialog_bike_info_neutral_btn);
        this.negativeButton = (Button) view.findViewById(C0434R.id.dialog_bike_info_negative_btn);
        this.spacePrePositive = view.findViewById(C0434R.id.dialog_bike_info_pre_positive);
        this.spacePreNeutral = view.findViewById(C0434R.id.dialog_bike_info_pre_neutral);
    }

    private void setActionButton(View view, String str, OnClickListener onClickListener, int i) {
        Button button = (Button) view.findViewById(i);
        if (TextUtils.isEmpty(str) == 0) {
            button.setVisibility(0);
            button.setOnClickListener(new BikeInfoDialog$$Lambda$0(this, onClickListener));
            button.setText(str);
            return;
        }
        button.setVisibility(8);
    }

    final /* synthetic */ void lambda$setActionButton$128$BikeInfoDialog(OnClickListener onClickListener, View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        dismiss();
    }
}
