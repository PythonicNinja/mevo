package com.inverce.mod.integrations.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.inverce.mod.integrations.C0431R;

public class ArrowView extends AppCompatTextView {
    @ColorInt
    int arrowColor;
    float arrowHeadAspect;
    boolean arrowOnLeft = false;
    Paint arrowPaint;
    Path path;

    public ArrowView(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public ArrowView(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public ArrowView(@NonNull Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        boolean z = true;
        if (attributeSet != null) {
            context = context.obtainStyledAttributes(attributeSet, C0431R.styleable.ArrowView, i, 0);
            this.arrowColor = context.getColor(C0431R.styleable.ArrowView_arrowColor, -986896);
            if (context.getInt(C0431R.styleable.ArrowView_direction, 0) != null) {
                z = false;
            }
            this.arrowOnLeft = z;
            this.arrowHeadAspect = context.getFloat(C0431R.styleable.ArrowView_arrowHeadAspect, 0.3f);
            context.recycle();
            return;
        }
        this.arrowColor = -986896;
        this.arrowOnLeft = true;
        this.arrowHeadAspect = 0.3f;
    }

    protected void onDraw(@NonNull Canvas canvas) {
        if (this.arrowPaint == null || this.arrowPaint.getColor() != this.arrowColor) {
            this.arrowPaint = new Paint();
            this.arrowPaint.setColor(this.arrowColor);
            this.arrowPaint.setStyle(Style.FILL);
            this.arrowPaint.setAntiAlias(true);
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float f = (float) measuredHeight;
        int i = (int) (this.arrowHeadAspect * f);
        if (this.path == null) {
            this.path = new Path();
            float f2;
            if (this.arrowOnLeft) {
                float f3 = (float) (measuredHeight / 2);
                this.path.moveTo(0.0f, f3);
                f2 = (float) i;
                this.path.lineTo(f2, 0.0f);
                float f4 = (float) measuredWidth;
                this.path.lineTo(f4, 0.0f);
                this.path.lineTo(f4, f);
                this.path.lineTo(f2, f);
                this.path.lineTo(0.0f, f3);
            } else {
                this.path.moveTo(0.0f, 0.0f);
                f2 = (float) (measuredWidth - i);
                this.path.lineTo(f2, 0.0f);
                this.path.lineTo((float) measuredWidth, (float) (measuredHeight / 2));
                this.path.lineTo(f2, f);
                this.path.lineTo(0.0f, f);
                this.path.lineTo(0.0f, 0.0f);
            }
        }
        canvas.drawPath(this.path, this.arrowPaint);
        super.onDraw(canvas);
    }
}
