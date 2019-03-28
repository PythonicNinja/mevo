package com.mevo.app.presentation.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.mevo.app.C0434R;

public class ProgressOverlayView extends RelativeLayout {
    public ProgressOverlayView(Context context) {
        super(context);
        init(context, null, -1, -1);
    }

    public ProgressOverlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, -1, -1);
    }

    public ProgressOverlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, -1);
    }

    @TargetApi(21)
    public ProgressOverlayView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    public void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.view_progress_overlay, this);
        setVisibility(8);
    }

    public void show() {
        setVisibility(0);
    }

    public void hide() {
        setVisibility(8);
    }
}
