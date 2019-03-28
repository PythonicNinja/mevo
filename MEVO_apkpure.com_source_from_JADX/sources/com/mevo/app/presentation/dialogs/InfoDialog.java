package com.mevo.app.presentation.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;

public class InfoDialog extends BaseDialogFragment {
    private static final String ARG_NEGATIVE_BUTTON_TEXT = "ARG_NEGATIVE_BUTTON_TEXT";
    private static final String ARG_NEUTRAL_BUTTON_TEXT = "ARG_NEUTRAL_BUTTON_TEXT";
    private static final String ARG_POSITIVE_BUTTON_TEXT = "ARG_POSITIVE_BUTTON_TEXT";
    private static final String ARG_STYLED = "ARG_STYLED";
    private static final String ARG_TEXT = "ARG_TEXT";
    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String TAG = "InfoDialog";
    private OnClickListener negativeButtonListener;
    private OnClickListener neutralButtonListener;
    private OnClickListener positiveButtonListener;

    public static class Builder {
        private Bundle arguments = new Bundle();
        private InfoDialog infoDialog = InfoDialog.newInstance();

        public Builder setTitle(String str) {
            this.arguments.putString(InfoDialog.ARG_TITLE, str);
            return this;
        }

        public Builder setText(String str) {
            this.arguments.putString(InfoDialog.ARG_TEXT, str);
            return this;
        }

        public Builder setTitle(@StringRes int i) {
            this.arguments.putString(InfoDialog.ARG_TITLE, IM.resources().getString(i));
            return this;
        }

        public Builder setText(@StringRes int i) {
            this.arguments.putString(InfoDialog.ARG_TEXT, IM.resources().getString(i));
            return this;
        }

        public Builder setText(SpannableString spannableString) {
            this.arguments.putCharSequence(InfoDialog.ARG_TEXT, spannableString);
            return this;
        }

        public Builder setNeutralButton(@NonNull String str, OnClickListener onClickListener) {
            this.arguments.putString(InfoDialog.ARG_NEUTRAL_BUTTON_TEXT, str);
            this.infoDialog.neutralButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(@NonNull String str, OnClickListener onClickListener) {
            this.arguments.putString(InfoDialog.ARG_NEGATIVE_BUTTON_TEXT, str);
            this.infoDialog.negativeButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(@NonNull String str, OnClickListener onClickListener) {
            this.arguments.putString(InfoDialog.ARG_POSITIVE_BUTTON_TEXT, str);
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
            this.arguments.putBoolean(InfoDialog.ARG_STYLED, true);
            return this;
        }

        public InfoDialog build() {
            this.infoDialog.setArguments(this.arguments);
            return this.infoDialog;
        }
    }

    public interface OnClickListener {
        void onClick(View view);
    }

    private static InfoDialog newInstance() {
        return new InfoDialog();
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        bundle = getArguments();
        if (bundle == null) {
            dismiss();
            return layoutInflater.inflate(C0434R.layout.dialog_info, viewGroup, false);
        }
        layoutInflater = layoutInflater.inflate(C0434R.layout.dialog_info, viewGroup, false);
        setTextOrHideView(layoutInflater, bundle.getString(ARG_TITLE), C0434R.id.dialog_info_title);
        layoutInflater.findViewById(C0434R.id.dialog_info_title_margin).setVisibility(layoutInflater.findViewById(C0434R.id.dialog_info_title).getVisibility());
        viewGroup = bundle.getString(ARG_TEXT);
        if (viewGroup == null) {
            viewGroup = bundle.getCharSequence(ARG_TEXT);
        }
        setTextOrHideView(layoutInflater, viewGroup, C0434R.id.dialog_info_message);
        setActionButton(layoutInflater, bundle.getString(ARG_NEUTRAL_BUTTON_TEXT), this.neutralButtonListener, C0434R.id.dialog_info_neutral_btn);
        setActionButton(layoutInflater, bundle.getString(ARG_NEGATIVE_BUTTON_TEXT), this.negativeButtonListener, C0434R.id.dialog_info_negative_btn);
        setActionButton(layoutInflater, bundle.getString(ARG_POSITIVE_BUTTON_TEXT), this.positiveButtonListener, C0434R.id.dialog_info_positive_btn);
        return layoutInflater;
    }

    public static void setTextOrHideView(View view, CharSequence charSequence, int i) {
        TextView textView = (TextView) view.findViewById(i);
        if (charSequence == null || charSequence.equals("") != 0) {
            textView.setVisibility(8);
        } else {
            textView.setText(charSequence);
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.neutralButtonListener != null) {
            this.neutralButtonListener.onClick(null);
        } else if (this.negativeButtonListener != null) {
            this.negativeButtonListener.onClick(null);
        }
        super.onCancel(dialogInterface);
    }

    private void setActionButton(View view, String str, OnClickListener onClickListener, int i) {
        Button button = (Button) view.findViewById(i);
        if (TextUtils.isEmpty(str) == 0) {
            button.setVisibility(0);
            button.setOnClickListener(new InfoDialog$$Lambda$0(this, onClickListener));
            button.setText(str);
            return;
        }
        button.setVisibility(8);
    }

    final /* synthetic */ void lambda$setActionButton$134$InfoDialog(OnClickListener onClickListener, View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        dismiss();
    }
}
