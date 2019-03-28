package com.mevo.app.presentation.custom_views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mevo.app.C0434R;
import com.mevo.app.constants.AppLanguageCode;
import com.mevo.app.tools.LocaleHelper;

public class QrView extends ConstraintLayout {
    private static final String TAG = "QrView";
    private ConstraintLayout containerClick;
    private TextView textView;

    public QrView(Context context) {
        super(context);
        init(context, null, -1, -1);
    }

    public QrView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, -1, -1);
    }

    public QrView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, -1);
    }

    public void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.view_qr_code, this);
        findViews();
        autoTextSize();
        context.getTheme().obtainStyledAttributes(attributeSet, C0434R.styleable.AppToolbar, 0, 0);
    }

    private void findViews() {
        this.containerClick = (ConstraintLayout) findViewById(C0434R.id.container_qr);
        this.textView = (TextView) findViewById(C0434R.id.qr_view_text);
    }

    private void autoTextSize() {
        if (LocaleHelper.getLanguage(getContext()).equals(AppLanguageCode.RU.getCode())) {
            this.textView.setTextSize(8.0f);
        }
    }

    public void setVisible() {
        this.containerClick.setVisibility(0);
    }

    public void setGone() {
        this.containerClick.setVisibility(8);
    }
}
