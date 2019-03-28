package com.mevo.app.presentation.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.Button;
import com.mevo.app.C0434R;

public class HighlightButton extends Button {
    private Drawable disabledBackground;
    private ColorStateList disabledFontColor;
    private Drawable enabledBackground;
    private ColorStateList enabledFontColor;
    private boolean highlighted;

    public HighlightButton(Context context) {
        super(context);
        init(context, null, -1, -1);
    }

    public HighlightButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, -1, -1);
    }

    public HighlightButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, -1);
    }

    @TargetApi(21)
    public HighlightButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.disabledBackground = ResourcesCompat.getDrawable(context.getResources(), C0434R.drawable.button_bg_disabled, null);
        this.enabledBackground = ResourcesCompat.getDrawable(context.getResources(), C0434R.drawable.button_bg_enabled, null);
        this.enabledFontColor = ResourcesCompat.getColorStateList(context.getResources(), C0434R.color.white, null);
        this.disabledFontColor = ResourcesCompat.getColorStateList(context.getResources(), C0434R.color.white, null);
        parseAttributes(context, attributeSet);
    }

    private void parseAttributes(Context context, AttributeSet attributeSet) {
        context = context.getTheme().obtainStyledAttributes(attributeSet, C0434R.styleable.HighlightButton, 0, 0);
        try {
            setHighlighted(context.getBoolean(2, false));
            setHighlightFontColor(context.getColorStateList(4));
            setHighlightBackground(context.getDrawable(3));
            setLowlightFontColor(context.getColorStateList(1));
            setLowlightBackground(context.getDrawable(0));
            setHighlighted(this.highlighted);
        } finally {
            context.recycle();
        }
    }

    public void setHighlighted(boolean z) {
        this.highlighted = z;
        if (this.highlighted) {
            setTextColor(this.enabledFontColor);
            setBackground(this.enabledBackground);
            return;
        }
        setTextColor(this.disabledFontColor);
        setBackground(this.disabledBackground);
    }

    public boolean isHighlighted() {
        return this.highlighted;
    }

    public void setHighlightFontColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.enabledFontColor = colorStateList;
        }
    }

    public void setLowlightFontColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.disabledFontColor = colorStateList;
        }
    }

    public void setHighlightBackground(Drawable drawable) {
        if (drawable != null) {
            this.enabledBackground = drawable;
        }
    }

    public void setLowlightBackground(Drawable drawable) {
        if (drawable != null) {
            this.disabledBackground = drawable;
        }
    }
}
