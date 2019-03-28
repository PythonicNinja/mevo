package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class RotationLayout extends FrameLayout {
    private int mRotation;

    public RotationLayout(Context context) {
        super(context);
    }

    public RotationLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RotationLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        if (this.mRotation != 1) {
            if (this.mRotation != 3) {
                super.onMeasure(i, i2);
                return;
            }
        }
        super.onMeasure(i, i2);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    public void setViewRotation(int i) {
        this.mRotation = ((i + 360) % 360) / 90;
    }

    public void dispatchDraw(Canvas canvas) {
        if (this.mRotation == 0) {
            super.dispatchDraw(canvas);
            return;
        }
        if (this.mRotation == 1) {
            canvas.translate((float) getWidth(), 0.0f);
            canvas.rotate(90.0f, (float) (getWidth() / 2), 0.0f);
            canvas.translate((float) (getHeight() / 2), (float) (getWidth() / 2));
        } else if (this.mRotation == 2) {
            canvas.rotate(180.0f, (float) (getWidth() / 2), (float) (getHeight() / 2));
        } else {
            canvas.translate(0.0f, (float) getHeight());
            canvas.rotate(270.0f, (float) (getWidth() / 2), 0.0f);
            canvas.translate((float) (getHeight() / 2), (float) ((-getWidth()) / 2));
        }
        super.dispatchDraw(canvas);
    }
}
