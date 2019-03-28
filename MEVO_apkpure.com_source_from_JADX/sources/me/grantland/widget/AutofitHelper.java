package me.grantland.widget;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.SingleLineTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

public class AutofitHelper {
    private static final int DEFAULT_MIN_TEXT_SIZE = 8;
    private static final float DEFAULT_PRECISION = 0.5f;
    private static final boolean SPEW = false;
    private static final String TAG = "AutoFitTextHelper";
    private boolean mEnabled;
    private boolean mIsAutofitting;
    private ArrayList<OnTextSizeChangeListener> mListeners;
    private int mMaxLines;
    private float mMaxTextSize;
    private float mMinTextSize;
    private OnLayoutChangeListener mOnLayoutChangeListener = new AutofitOnLayoutChangeListener();
    private TextPaint mPaint;
    private float mPrecision;
    private float mTextSize;
    private TextView mTextView;
    private TextWatcher mTextWatcher = new AutofitTextWatcher();

    private class AutofitOnLayoutChangeListener implements OnLayoutChangeListener {
        private AutofitOnLayoutChangeListener() {
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            AutofitHelper.this.autofit();
        }
    }

    private class AutofitTextWatcher implements TextWatcher {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        private AutofitTextWatcher() {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            AutofitHelper.this.autofit();
        }
    }

    public interface OnTextSizeChangeListener {
        void onTextSizeChange(float f, float f2);
    }

    public static AutofitHelper create(TextView textView) {
        return create(textView, null, 0);
    }

    public static AutofitHelper create(TextView textView, AttributeSet attributeSet) {
        return create(textView, attributeSet, 0);
    }

    public static AutofitHelper create(TextView textView, AttributeSet attributeSet, int i) {
        AutofitHelper autofitHelper = new AutofitHelper(textView);
        boolean z = true;
        if (attributeSet != null) {
            textView = textView.getContext();
            int minTextSize = (int) autofitHelper.getMinTextSize();
            float precision = autofitHelper.getPrecision();
            textView = textView.obtainStyledAttributes(attributeSet, C0514R.styleable.AutofitTextView, i, 0);
            z = textView.getBoolean(C0514R.styleable.AutofitTextView_sizeToFit, true);
            attributeSet = textView.getDimensionPixelSize(C0514R.styleable.AutofitTextView_minTextSize, minTextSize);
            i = textView.getFloat(C0514R.styleable.AutofitTextView_precision, precision);
            textView.recycle();
            autofitHelper.setMinTextSize(0, (float) attributeSet).setPrecision(i);
        }
        autofitHelper.setEnabled(z);
        return autofitHelper;
    }

    private static void autofit(TextView textView, TextPaint textPaint, float f, float f2, int i, float f3) {
        View view = textView;
        TextPaint textPaint2 = textPaint;
        float f4 = f2;
        int i2 = i;
        if (i2 > 0) {
            if (i2 != Integer.MAX_VALUE) {
                int width = (view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight();
                if (width > 0) {
                    CharSequence text = view.getText();
                    TransformationMethod transformationMethod = view.getTransformationMethod();
                    if (transformationMethod != null) {
                        text = transformationMethod.getTransformation(text, view);
                    }
                    Context context = view.getContext();
                    Resources system = Resources.getSystem();
                    if (context != null) {
                        system = context.getResources();
                    }
                    DisplayMetrics displayMetrics = system.getDisplayMetrics();
                    textPaint2.set(view.getPaint());
                    textPaint2.setTextSize(f4);
                    if ((i2 == 1 && textPaint2.measureText(text, 0, text.length()) > ((float) width)) || getLineCount(text, textPaint2, f4, (float) width, displayMetrics) > i2) {
                        f4 = getAutofitTextSize(text, textPaint2, (float) width, i2, 0.0f, f4, f3, displayMetrics);
                    }
                    view.setTextSize(0, f4 < f ? f : f4);
                }
            }
        }
    }

    private static float getAutofitTextSize(CharSequence charSequence, TextPaint textPaint, float f, int i, float f2, float f3, float f4, DisplayMetrics displayMetrics) {
        StaticLayout staticLayout;
        int lineCount;
        TextPaint textPaint2 = textPaint;
        float f5 = f;
        int i2 = i;
        float f6 = (f2 + f3) / 2.0f;
        textPaint2.setTextSize(TypedValue.applyDimension(0, f6, displayMetrics));
        if (i2 != 1) {
            staticLayout = r0;
            StaticLayout staticLayout2 = new StaticLayout(charSequence, textPaint2, (int) f5, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            lineCount = staticLayout.getLineCount();
        } else {
            staticLayout = null;
            lineCount = 1;
        }
        if (lineCount > i2) {
            return f3 - f2 < f4 ? f2 : getAutofitTextSize(charSequence, textPaint2, f5, i2, f2, f6, f4, displayMetrics);
        } else {
            if (lineCount < i2) {
                return getAutofitTextSize(charSequence, textPaint2, f5, i2, f6, f3, f4, displayMetrics);
            }
            CharSequence charSequence2;
            float f7 = 0.0f;
            if (i2 == 1) {
                charSequence2 = charSequence;
                f7 = textPaint2.measureText(charSequence2, 0, charSequence.length());
            } else {
                charSequence2 = charSequence;
                for (int i3 = 0; i3 < lineCount; i3++) {
                    if (staticLayout.getLineWidth(i3) > f7) {
                        f7 = staticLayout.getLineWidth(i3);
                    }
                }
            }
            if (f3 - f2 < f4) {
                return f2;
            }
            if (f7 > f5) {
                return getAutofitTextSize(charSequence2, textPaint2, f5, i2, f2, f6, f4, displayMetrics);
            }
            return f7 < f5 ? getAutofitTextSize(charSequence2, textPaint2, f5, i2, f6, f3, f4, displayMetrics) : f6;
        }
    }

    private static int getLineCount(CharSequence charSequence, TextPaint textPaint, float f, float f2, DisplayMetrics displayMetrics) {
        textPaint.setTextSize(TypedValue.applyDimension(0, f, displayMetrics));
        return new StaticLayout(charSequence, textPaint, (int) f2, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true).getLineCount();
    }

    private static int getMaxLines(TextView textView) {
        TransformationMethod transformationMethod = textView.getTransformationMethod();
        if (transformationMethod == null || !(transformationMethod instanceof SingleLineTransformationMethod)) {
            return VERSION.SDK_INT >= 16 ? textView.getMaxLines() : -1;
        } else {
            return 1;
        }
    }

    private AutofitHelper(TextView textView) {
        float f = textView.getContext().getResources().getDisplayMetrics().scaledDensity;
        this.mTextView = textView;
        this.mPaint = new TextPaint();
        setRawTextSize(textView.getTextSize());
        this.mMaxLines = getMaxLines(textView);
        this.mMinTextSize = f * 8.0f;
        this.mMaxTextSize = this.mTextSize;
        this.mPrecision = DEFAULT_PRECISION;
    }

    public AutofitHelper addOnTextSizeChangeListener(OnTextSizeChangeListener onTextSizeChangeListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        this.mListeners.add(onTextSizeChangeListener);
        return this;
    }

    public AutofitHelper removeOnTextSizeChangeListener(OnTextSizeChangeListener onTextSizeChangeListener) {
        if (this.mListeners != null) {
            this.mListeners.remove(onTextSizeChangeListener);
        }
        return this;
    }

    public float getPrecision() {
        return this.mPrecision;
    }

    public AutofitHelper setPrecision(float f) {
        if (this.mPrecision != f) {
            this.mPrecision = f;
            autofit();
        }
        return this;
    }

    public float getMinTextSize() {
        return this.mMinTextSize;
    }

    public AutofitHelper setMinTextSize(float f) {
        return setMinTextSize(2, f);
    }

    public AutofitHelper setMinTextSize(int i, float f) {
        Context context = this.mTextView.getContext();
        Resources system = Resources.getSystem();
        if (context != null) {
            system = context.getResources();
        }
        setRawMinTextSize(TypedValue.applyDimension(i, f, system.getDisplayMetrics()));
        return this;
    }

    private void setRawMinTextSize(float f) {
        if (f != this.mMinTextSize) {
            this.mMinTextSize = f;
            autofit();
        }
    }

    public float getMaxTextSize() {
        return this.mMaxTextSize;
    }

    public AutofitHelper setMaxTextSize(float f) {
        return setMaxTextSize(2, f);
    }

    public AutofitHelper setMaxTextSize(int i, float f) {
        Context context = this.mTextView.getContext();
        Resources system = Resources.getSystem();
        if (context != null) {
            system = context.getResources();
        }
        setRawMaxTextSize(TypedValue.applyDimension(i, f, system.getDisplayMetrics()));
        return this;
    }

    private void setRawMaxTextSize(float f) {
        if (f != this.mMaxTextSize) {
            this.mMaxTextSize = f;
            autofit();
        }
    }

    public int getMaxLines() {
        return this.mMaxLines;
    }

    public AutofitHelper setMaxLines(int i) {
        if (this.mMaxLines != i) {
            this.mMaxLines = i;
            autofit();
        }
        return this;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public AutofitHelper setEnabled(boolean z) {
        if (this.mEnabled != z) {
            this.mEnabled = z;
            if (z) {
                this.mTextView.addTextChangedListener(this.mTextWatcher);
                this.mTextView.addOnLayoutChangeListener(this.mOnLayoutChangeListener);
                autofit();
            } else {
                this.mTextView.removeTextChangedListener(this.mTextWatcher);
                this.mTextView.removeOnLayoutChangeListener(this.mOnLayoutChangeListener);
                this.mTextView.setTextSize(0, this.mTextSize);
            }
        }
        return this;
    }

    public float getTextSize() {
        return this.mTextSize;
    }

    public void setTextSize(float f) {
        setTextSize(2, f);
    }

    public void setTextSize(int i, float f) {
        if (!this.mIsAutofitting) {
            Context context = this.mTextView.getContext();
            Resources system = Resources.getSystem();
            if (context != null) {
                system = context.getResources();
            }
            setRawTextSize(TypedValue.applyDimension(i, f, system.getDisplayMetrics()));
        }
    }

    private void setRawTextSize(float f) {
        if (this.mTextSize != f) {
            this.mTextSize = f;
        }
    }

    private void autofit() {
        float textSize = this.mTextView.getTextSize();
        this.mIsAutofitting = true;
        autofit(this.mTextView, this.mPaint, this.mMinTextSize, this.mMaxTextSize, this.mMaxLines, this.mPrecision);
        this.mIsAutofitting = false;
        float textSize2 = this.mTextView.getTextSize();
        if (textSize2 != textSize) {
            sendTextSizeChange(textSize2, textSize);
        }
    }

    private void sendTextSizeChange(float f, float f2) {
        if (this.mListeners != null) {
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((OnTextSizeChangeListener) it.next()).onTextSizeChange(f, f2);
            }
        }
    }
}
