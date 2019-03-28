package com.mevo.app.presentation.custom_views;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.constants.AppLanguageCode;
import com.mevo.app.tools.UserManager;

public class CustomCheckBox extends RelativeLayout implements OnCheckedChangeListener {
    private AppCompatCheckBox checkBox;
    private boolean checked;
    private RelativeLayout mainLayout;
    private AppCompatTextView text;

    public CustomCheckBox(Context context) {
        super(context);
        init(context, null, -1, -1);
    }

    public CustomCheckBox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, -1, -1);
    }

    public CustomCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, -1);
    }

    @TargetApi(21)
    public CustomCheckBox(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.view_checkbox, this);
        findViews();
        this.mainLayout.setOnClickListener(new CustomCheckBox$$Lambda$0(this));
        this.checkBox.setOnCheckedChangeListener(this);
        context = context.getTheme().obtainStyledAttributes(attributeSet, C0434R.styleable.CustomCheckBox, 0, 0);
        try {
            setButtonText(context.getString(0));
        } finally {
            context.recycle();
        }
    }

    final /* synthetic */ void lambda$init$127$CustomCheckBox(View view) {
        this.checkBox.setChecked(this.checkBox.isChecked() ^ 1);
    }

    private void findViews() {
        this.mainLayout = (RelativeLayout) findViewById(C0434R.id.view_checkbox_main_layout);
        this.checkBox = (AppCompatCheckBox) findViewById(C0434R.id.view_checkbox_check);
        this.text = (AppCompatTextView) findViewById(C0434R.id.view_checkbox_text);
    }

    public void setButtonText(String str) {
        if (str != null) {
            this.text.setText(str);
        }
    }

    @SuppressLint({"RestrictedApi"})
    public void setTextSizeForSmallLayout() {
        if (this.text == null) {
            return;
        }
        if (AppLanguageCode.PL.getCode().equalsIgnoreCase(UserManager.getUser().getLanguage())) {
            this.text.setAutoSizeTextTypeUniformWithPresetSizes(IM.resources().getIntArray(C0434R.array.autosize_text_sizes_pl), 2);
        } else {
            this.text.setAutoSizeTextTypeUniformWithPresetSizes(IM.resources().getIntArray(C0434R.array.autosize_text_sizes_others), 2);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.checked = z;
    }

    public boolean isChecked() {
        return this.checked;
    }
}
