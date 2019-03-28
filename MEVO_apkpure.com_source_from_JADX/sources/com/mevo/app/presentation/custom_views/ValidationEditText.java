package com.mevo.app.presentation.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import com.mevo.app.C0434R;

public class ValidationEditText extends EditText {
    private Drawable checkIcon;
    private boolean checkVisible;
    private Drawable invalidBackground;
    private TextWatcher textWatcher;
    private boolean valid = false;
    private Drawable validBackground;
    private ValidChangeListener validChangeListener;
    private Validator validator;

    /* renamed from: com.mevo.app.presentation.custom_views.ValidationEditText$1 */
    class C04421 implements TextWatcher {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        C04421() {
        }

        public void afterTextChanged(Editable editable) {
            ValidationEditText.this.checkValid();
        }
    }

    public interface ValidChangeListener {
        void onValidChanged(ValidationEditText validationEditText, boolean z);
    }

    public interface Validator {
        boolean isValid(String str);
    }

    public ValidationEditText(Context context) {
        super(context);
        init(context, null, -1, -1);
    }

    public ValidationEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, -1, -1);
    }

    public ValidationEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, -1);
    }

    @TargetApi(21)
    public ValidationEditText(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.checkIcon = ResourcesCompat.getDrawable(context.getResources(), C0434R.drawable.check_on_16dp, null);
        this.invalidBackground = ResourcesCompat.getDrawable(context.getResources(), C0434R.drawable.edit_text_bg_standard, null);
        this.validBackground = ResourcesCompat.getDrawable(context.getResources(), C0434R.drawable.edit_text_bg_valid, null);
        context = context.getTheme().obtainStyledAttributes(attributeSet, C0434R.styleable.ValidationEditText, 0, 0);
        try {
            this.checkVisible = context.getBoolean(0, true);
        } finally {
            context.recycle();
        }
    }

    public void checkValid() {
        boolean z = this.validator != null && this.validator.isValid(getText().toString());
        if (z != this.valid) {
            Drawable drawable;
            Drawable drawable2;
            this.valid = z;
            if (this.valid) {
                drawable = this.checkIcon;
                drawable2 = this.validBackground;
            } else {
                drawable2 = this.invalidBackground;
                drawable = null;
            }
            if (this.checkVisible) {
                setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            }
            setBackground(drawable2);
            if (this.validChangeListener != null) {
                this.validChangeListener.onValidChanged(this, this.valid);
            }
        }
    }

    public void setValidator(ValidChangeListener validChangeListener, Validator validator) {
        this.validChangeListener = validChangeListener;
        if (this.textWatcher != null) {
            removeTextChangedListener(this.textWatcher);
            this.textWatcher = null;
        }
        this.validator = validator;
        if (validator != null) {
            this.textWatcher = new C04421();
            addTextChangedListener(this.textWatcher);
        }
        checkValid();
    }

    public boolean isValid() {
        return this.valid;
    }
}
