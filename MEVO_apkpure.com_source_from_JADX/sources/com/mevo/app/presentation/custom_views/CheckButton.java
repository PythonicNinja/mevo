package com.mevo.app.presentation.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mevo.app.C0434R;

public class CheckButton extends RelativeLayout {
    private TextView buttonText;
    private ImageView checkIcon;
    private boolean checked;

    public CheckButton(Context context) {
        super(context);
        init(context, null, -1, -1);
    }

    public CheckButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, -1, -1);
    }

    public CheckButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, -1);
    }

    @TargetApi(21)
    public CheckButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    public void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.button_check, this);
        findViews();
        this.checked = false;
        context = context.getTheme().obtainStyledAttributes(attributeSet, C0434R.styleable.CheckButton, 0, 0);
        try {
            setButtonText(context.getString(2));
            setChecked(context.getBoolean(1, false));
            setCheckVisible(context.getBoolean(0, true));
        } finally {
            context.recycle();
        }
    }

    private void findViews() {
        this.checkIcon = (ImageView) findViewById(C0434R.id.button_check_icon);
        this.buttonText = (TextView) findViewById(C0434R.id.button_check_text);
    }

    public void setButtonText(String str) {
        if (str != null) {
            this.buttonText.setText(str);
        }
    }

    public String getButtonText() {
        return this.buttonText.getText().toString();
    }

    public void setChecked(boolean z) {
        this.checked = z;
        if (z) {
            this.checkIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), C0434R.drawable.check_on_16dp, null));
        } else {
            this.checkIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), C0434R.drawable.check_off_16dp, null));
        }
    }

    public boolean getChecked() {
        return this.checked;
    }

    public void setCheckVisible(boolean z) {
        if (z) {
            this.checkIcon.setVisibility(0);
        } else {
            this.checkIcon.setVisibility(8);
        }
    }
}
