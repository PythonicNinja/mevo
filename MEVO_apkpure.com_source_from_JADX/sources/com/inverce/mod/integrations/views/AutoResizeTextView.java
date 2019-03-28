package com.inverce.mod.integrations.views;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

@Deprecated
public class AutoResizeTextView extends AppCompatTextView {
    public static final float MIN_TEXT_SIZE = 20.0f;
    private static final String mEllipsis = "...";
    private boolean mAddEllipsis;
    private float mMaxTextSize;
    private float mMinTextSize;
    private boolean mNeedsResize;
    private float mSpacingAdd;
    private float mSpacingMult;
    private OnTextResizeListener mTextResizeListener;
    private float mTextSize;

    public interface OnTextResizeListener {
        void onTextResize(TextView textView, float f, float f2);
    }

    public AutoResizeTextView(@NonNull Context context) {
        this(context, null);
    }

    public AutoResizeTextView(@NonNull Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AutoResizeTextView(@NonNull Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mNeedsResize = null;
        this.mMaxTextSize = 0.0f;
        this.mMinTextSize = 20.0f;
        this.mSpacingMult = 1.0f;
        this.mSpacingAdd = 0.0f;
        this.mAddEllipsis = true;
        this.mTextSize = getTextSize();
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.mNeedsResize = true;
        resetTextSize();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i != i3 || i2 != i4) {
            this.mNeedsResize = true;
        }
    }

    public void setOnResizeListener(OnTextResizeListener onTextResizeListener) {
        this.mTextResizeListener = onTextResizeListener;
    }

    public void setTextSize(float f) {
        super.setTextSize(f);
        this.mTextSize = getTextSize();
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        this.mTextSize = getTextSize();
    }

    public void setLineSpacing(float f, float f2) {
        super.setLineSpacing(f, f2);
        this.mSpacingMult = f2;
        this.mSpacingAdd = f;
    }

    public void setMaxTextSize(float f) {
        this.mMaxTextSize = f;
        requestLayout();
        invalidate();
    }

    public float getMaxTextSize() {
        return this.mMaxTextSize;
    }

    public void setMinTextSize(float f) {
        this.mMinTextSize = f;
        requestLayout();
        invalidate();
    }

    public float getMinTextSize() {
        return this.mMinTextSize;
    }

    public void setAddEllipsis(boolean z) {
        this.mAddEllipsis = z;
    }

    public boolean getAddEllipsis() {
        return this.mAddEllipsis;
    }

    public void resetTextSize() {
        if (this.mTextSize > 0.0f) {
            super.setTextSize(0, this.mTextSize);
            this.mMaxTextSize = this.mTextSize;
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z || this.mNeedsResize) {
            resizeText(((i3 - i) - getCompoundPaddingLeft()) - getCompoundPaddingRight(), ((i4 - i2) - getCompoundPaddingBottom()) - getCompoundPaddingTop());
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    public void resizeText() {
        resizeText((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingBottom()) - getPaddingTop());
    }

    public void resizeText(int i, int i2) {
        View view = this;
        int i3 = i;
        int i4 = i2;
        CharSequence text = getText();
        if (text != null && text.length() != 0 && i4 > 0 && i3 > 0) {
            if (view.mTextSize != 0.0f) {
                boolean z;
                if (getTransformationMethod() != null) {
                    text = getTransformationMethod().getTransformation(text, view);
                }
                CharSequence charSequence = text;
                Paint paint = getPaint();
                float textSize = paint.getTextSize();
                float min = view.mMaxTextSize > 0.0f ? Math.min(view.mTextSize, view.mMaxTextSize) : view.mTextSize;
                int textHeight = getTextHeight(charSequence, paint, i3, min);
                float f = min;
                while (textHeight > i4 && f > view.mMinTextSize) {
                    f = Math.max(f - 2.0f, view.mMinTextSize);
                    textHeight = getTextHeight(charSequence, paint, i3, f);
                }
                if (view.mAddEllipsis && f == view.mMinTextSize && textHeight > i4) {
                    StaticLayout staticLayout = r1;
                    StaticLayout staticLayout2 = new StaticLayout(charSequence, new TextPaint(paint), i3, Alignment.ALIGN_NORMAL, view.mSpacingMult, view.mSpacingAdd, false);
                    if (staticLayout.getLineCount() > 0) {
                        int lineForVertical = staticLayout.getLineForVertical(i4) - 1;
                        if (lineForVertical < 0) {
                            setText("");
                        } else {
                            textHeight = staticLayout.getLineStart(lineForVertical);
                            int lineEnd = staticLayout.getLineEnd(lineForVertical);
                            min = staticLayout.getLineWidth(lineForVertical);
                            float measureText = paint.measureText(mEllipsis);
                            while (((float) i3) < min + measureText) {
                                lineEnd--;
                                min = paint.measureText(charSequence.subSequence(textHeight, lineEnd + 1).toString());
                            }
                            StringBuilder stringBuilder = new StringBuilder();
                            z = false;
                            stringBuilder.append(charSequence.subSequence(0, lineEnd));
                            stringBuilder.append(mEllipsis);
                            setText(stringBuilder.toString());
                            setTextSize(z, f);
                            setLineSpacing(view.mSpacingAdd, view.mSpacingMult);
                            if (view.mTextResizeListener != null) {
                                view.mTextResizeListener.onTextResize(view, textSize, f);
                            }
                            view.mNeedsResize = z;
                        }
                    }
                }
                z = false;
                setTextSize(z, f);
                setLineSpacing(view.mSpacingAdd, view.mSpacingMult);
                if (view.mTextResizeListener != null) {
                    view.mTextResizeListener.onTextResize(view, textSize, f);
                }
                view.mNeedsResize = z;
            }
        }
    }

    private int getTextHeight(CharSequence charSequence, TextPaint textPaint, int i, float f) {
        TextPaint textPaint2 = new TextPaint(textPaint);
        textPaint2.setTextSize(f);
        return new StaticLayout(charSequence, textPaint2, i, Alignment.ALIGN_NORMAL, this.mSpacingMult, this.mSpacingAdd, true).getHeight();
    }
}
