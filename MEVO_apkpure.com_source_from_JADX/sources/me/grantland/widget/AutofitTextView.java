package me.grantland.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import me.grantland.widget.AutofitHelper.OnTextSizeChangeListener;

public class AutofitTextView extends TextView implements OnTextSizeChangeListener {
    private AutofitHelper mHelper;

    public void onTextSizeChange(float f, float f2) {
    }

    public AutofitTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public AutofitTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public AutofitTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        this.mHelper = AutofitHelper.create(this, attributeSet, i).addOnTextSizeChangeListener(this);
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        if (this.mHelper != null) {
            this.mHelper.setTextSize(i, f);
        }
    }

    public void setLines(int i) {
        super.setLines(i);
        if (this.mHelper != null) {
            this.mHelper.setMaxLines(i);
        }
    }

    public void setMaxLines(int i) {
        super.setMaxLines(i);
        if (this.mHelper != null) {
            this.mHelper.setMaxLines(i);
        }
    }

    public AutofitHelper getAutofitHelper() {
        return this.mHelper;
    }

    public boolean isSizeToFit() {
        return this.mHelper.isEnabled();
    }

    public void setSizeToFit() {
        setSizeToFit(true);
    }

    public void setSizeToFit(boolean z) {
        this.mHelper.setEnabled(z);
    }

    public float getMaxTextSize() {
        return this.mHelper.getMaxTextSize();
    }

    public void setMaxTextSize(float f) {
        this.mHelper.setMaxTextSize(f);
    }

    public void setMaxTextSize(int i, float f) {
        this.mHelper.setMaxTextSize(i, f);
    }

    public float getMinTextSize() {
        return this.mHelper.getMinTextSize();
    }

    public void setMinTextSize(int i) {
        this.mHelper.setMinTextSize(2, (float) i);
    }

    public void setMinTextSize(int i, float f) {
        this.mHelper.setMinTextSize(i, f);
    }

    public float getPrecision() {
        return this.mHelper.getPrecision();
    }

    public void setPrecision(float f) {
        this.mHelper.setPrecision(f);
    }
}
