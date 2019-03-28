package com.mevo.app.presentation.custom_views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mevo.app.C0434R;
import com.mevo.app.tools.Screen;

public class CustomInput extends LinearLayout {
    private Drawable customBg;
    private TextInputEditText editText;
    private int errorColor;
    private TextView errorText;
    private Drawable invalidBackground;
    private boolean showErrorMessage;
    private TextInputLayout textInputLayout;
    private TextWatcher textWatcher;
    private Pair<Boolean, String> valid;
    private Drawable validBackground;
    private ValidChangeListener validChangeListener;
    private Validator validator;

    /* renamed from: com.mevo.app.presentation.custom_views.CustomInput$1 */
    class C04381 implements TextWatcher {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        C04381() {
        }

        public void afterTextChanged(Editable editable) {
            if (CustomInput.this.valid != null && ((Boolean) CustomInput.this.valid.first).booleanValue() == null) {
                CustomInput.this.clearError();
            }
        }
    }

    public interface ValidChangeListener {
        void onValidChanged(CustomInput customInput, Pair<Boolean, String> pair);
    }

    public interface Validator {
        @NonNull
        Pair<Boolean, String> checkValid(String str);
    }

    public CustomInput(Context context) {
        super(context);
        init(context, null, -1, -1);
    }

    public CustomInput(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, -1, -1);
    }

    public CustomInput(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, -1);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        setOrientation(1);
        parseAttributes(context, attributeSet);
        this.errorColor = ResourcesCompat.getColor(context.getResources(), C0434R.color.textColorError, 0);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        findViews();
        this.errorText = new TextView(getContext());
        addView(this.errorText);
        this.errorText.setTextColor(this.errorColor);
        this.errorText.setTextSize(0, getContext().getResources().getDimension(C0434R.dimen.text_size_input_error));
        int dpToPx = Screen.dpToPx(12.0f, getContext());
        int dpToPx2 = Screen.dpToPx(16.0f, getContext());
        int dpToPx3 = Screen.dpToPx(2.0f, getContext());
        int dpToPx4 = Screen.dpToPx(6.0f, getContext());
        this.errorText.setPadding(dpToPx, 0, dpToPx, 0);
        if (!this.showErrorMessage) {
            this.errorText.setVisibility(8);
        }
        if (this.textInputLayout != null) {
            this.textInputLayout.setBackground(this.customBg);
            this.textInputLayout.setPadding(0, dpToPx4, 0, 0);
        }
        if (this.editText != null) {
            this.editText.setPadding(dpToPx, dpToPx3, dpToPx, dpToPx2);
            this.editText.setTextSize(14.0f);
            this.validBackground = ResourcesCompat.getDrawable(getContext().getResources(), C0434R.drawable.custom_input_valid_edit_text_bg, null);
            this.invalidBackground = ResourcesCompat.getDrawable(getContext().getResources(), C0434R.drawable.custom_input_invalid_edit_text_bg, null);
            DrawableCompat.setTint(DrawableCompat.wrap(this.invalidBackground), this.errorColor);
            this.editText.setBackground(this.validBackground);
            setValidatorInternal();
        }
    }

    private void findViews() {
        this.textInputLayout = (TextInputLayout) getChildByType(this, TextInputLayout.class);
        this.editText = (TextInputEditText) getChildByType(this, TextInputEditText.class);
    }

    @Nullable
    private <T> T getChildByType(ViewGroup viewGroup, Class<T> cls) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            T childAt = viewGroup.getChildAt(i);
            if (cls.equals(childAt.getClass())) {
                return childAt;
            }
            if (childAt instanceof ViewGroup) {
                childAt = getChildByType((ViewGroup) childAt, cls);
                if (childAt != null) {
                    return childAt;
                }
            }
        }
        return null;
    }

    private void parseAttributes(Context context, AttributeSet attributeSet) {
        context = context.getTheme().obtainStyledAttributes(attributeSet, C0434R.styleable.CustomInput, 0, 0);
        try {
            this.customBg = context.getDrawable(0);
            this.showErrorMessage = context.getBoolean(1, true);
        } finally {
            context.recycle();
        }
    }

    public Boolean isValid() {
        if (this.valid == null) {
            checkValid();
        }
        return (Boolean) this.valid.first;
    }

    public void checkValid() {
        checkValid(false);
    }

    private void checkValid(boolean z) {
        Pair pair = this.valid;
        boolean z2 = true;
        this.valid = this.validator != null ? this.validator.checkValid(this.editText.getText().toString()) : new Pair(Boolean.valueOf(true), null);
        boolean isEmpty = TextUtils.isEmpty(this.editText.getText());
        if (!((Boolean) this.valid.first).booleanValue()) {
            if (!isEmpty || !z) {
                z2 = false;
            }
        }
        if (z2) {
            z = this.validBackground;
        } else {
            z = this.invalidBackground;
        }
        this.editText.setBackground(z);
        this.errorText.setText(z2 ? "" : (CharSequence) this.valid.second);
        if (this.validChangeListener && !pair.equals(this.valid)) {
            this.validChangeListener.onValidChanged(this, this.valid);
        }
    }

    private void clearError() {
        this.valid = new Pair(Boolean.valueOf(true), "");
        this.editText.setBackground(this.validBackground);
        this.errorText.setText("");
    }

    public void setValidator(ValidChangeListener validChangeListener, Validator validator) {
        this.validChangeListener = validChangeListener;
        this.validator = validator;
        setValidatorInternal();
    }

    public void setValidatorInternal() {
        if (this.editText != null) {
            if (this.textWatcher != null) {
                this.editText.removeTextChangedListener(this.textWatcher);
                this.textWatcher = null;
            }
            if (this.validator != null) {
                this.textWatcher = new C04381();
                this.editText.addTextChangedListener(this.textWatcher);
            }
            checkValid(true);
        }
    }

    public CustomInput setShowErrorMessage(boolean z) {
        this.showErrorMessage = z;
        if (this.errorText != null) {
            this.errorText.setVisibility(z ? false : true);
        }
        return this;
    }
}
