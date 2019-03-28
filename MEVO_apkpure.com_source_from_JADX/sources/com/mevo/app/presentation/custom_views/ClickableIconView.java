package com.mevo.app.presentation.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.reportable_error.BikeProblem;

public class ClickableIconView extends RelativeLayout {
    BikeProblem bikeProblem;
    Button roundButton;

    public ClickableIconView(Context context) {
        super(context);
        init(context, null, -1, -1);
    }

    public ClickableIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, -1, -1);
    }

    public ClickableIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, -1);
    }

    @TargetApi(21)
    public ClickableIconView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.view_clicable_icon, this);
        this.roundButton = (Button) findViewById(C0434R.id.round_button);
    }

    public boolean isSelected() {
        return this.roundButton.isSelected();
    }

    public void setSelected(boolean z) {
        this.roundButton.setSelected(z);
    }

    public void toggleSelected() {
        setSelected(isSelected() ^ 1);
    }

    public void setOnButtonClickedListener(OnClickListener onClickListener) {
        this.roundButton.setOnClickListener(onClickListener);
    }

    public BikeProblem getBikeProblem() {
        return this.bikeProblem;
    }

    public ClickableIconView setBikeProblem(BikeProblem bikeProblem) {
        this.bikeProblem = bikeProblem;
        return this;
    }
}
