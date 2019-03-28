package com.mevo.app.presentation.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.Button;
import com.mevo.app.C0434R;

public class CheckButton2 extends Button {
    private Drawable checkIconOff;
    private Drawable checkIconOn;
    private boolean isCheckVisible;
    private boolean isChecked;

    public CheckButton2(Context context) {
        super(context);
        init(context, null);
    }

    public CheckButton2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public CheckButton2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    @TargetApi(21)
    public CheckButton2(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.isChecked = false;
        this.checkIconOn = ResourcesCompat.getDrawable(context.getResources(), C0434R.drawable.check_on_16dp, null);
        this.checkIconOff = ResourcesCompat.getDrawable(context.getResources(), C0434R.drawable.check_off_16dp, null);
        setBackground(ResourcesCompat.getDrawable(context.getResources(), C0434R.drawable.button_background_light_grey, null));
        context = context.getTheme().obtainStyledAttributes(attributeSet, C0434R.styleable.CheckButton, 0, 0);
        try {
            setChecked(context.getBoolean(1, false));
            setCheckVisible(context.getBoolean(0, true));
        } finally {
            context.recycle();
        }
    }

    public void setChecked(boolean z) {
        if (z) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, this.checkIconOn, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, this.checkIconOff, null);
        }
        this.isChecked = z;
    }

    public void setCheckVisible(boolean z) {
        this.isCheckVisible = z;
        if (z) {
            setChecked(this.isChecked);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    public boolean isCheckVisible() {
        return this.isCheckVisible;
    }

    public boolean isChecked() {
        return this.isChecked;
    }
}
