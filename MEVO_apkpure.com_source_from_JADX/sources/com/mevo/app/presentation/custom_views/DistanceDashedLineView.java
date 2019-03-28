package com.mevo.app.presentation.custom_views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.IMInitializer;
import com.inverce.mod.core.Screen;
import com.mevo.app.C0434R;

public class DistanceDashedLineView extends View {
    private Bitmap iconBottom;
    private Rect iconBottomDestRect;
    private Rect iconBottomRect;
    private Bitmap iconMiddle;
    private Rect iconMiddleDestRect;
    private Rect iconMiddleRect;
    private Bitmap iconTop;
    private Rect iconTopDestRect;
    private Rect iconTopRect;
    private int lineWith;
    private Paint paint;

    public DistanceDashedLineView(Context context) {
        super(context);
        init(context);
    }

    public DistanceDashedLineView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public DistanceDashedLineView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    @RequiresApi(api = 21)
    public DistanceDashedLineView(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    private void init(Context context) {
        IMInitializer.initialize(context);
        this.lineWith = Screen.dpToPx(1.0f);
        this.iconTop = BitmapFactory.decodeResource(IM.resources(), C0434R.drawable.image_distance_top);
        this.iconMiddle = BitmapFactory.decodeResource(IM.resources(), C0434R.drawable.image_distance_middle);
        this.iconBottom = BitmapFactory.decodeResource(IM.resources(), C0434R.drawable.image_distance_bottom);
        this.iconBottomRect = new Rect(0, 0, this.iconBottom.getWidth(), this.iconBottom.getHeight());
        this.iconMiddleRect = new Rect(0, 0, this.iconMiddle.getWidth(), this.iconMiddle.getHeight());
        this.iconTopRect = new Rect(0, 0, this.iconTop.getWidth(), this.iconTop.getHeight());
        this.iconTopDestRect = new Rect(0, 0, 0, 0);
        this.iconMiddleDestRect = new Rect(0, 0, 0, 0);
        this.iconBottomDestRect = new Rect(0, 0, 0, 0);
        this.paint = new Paint();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float f = (float) measuredWidth;
        this.iconTopDestRect.set(0, 0, measuredWidth, (int) (aspectRatio(this.iconTop) * f));
        this.iconBottomDestRect.set(0, (int) (((float) measuredHeight) - (aspectRatio(this.iconBottom) * f)), measuredWidth, measuredHeight);
        measuredWidth /= 2;
        this.iconMiddleDestRect.set(measuredWidth - this.lineWith, this.iconTopDestRect.bottom, measuredWidth + this.lineWith, this.iconBottomDestRect.top);
        canvas.drawBitmap(this.iconTop, this.iconTopRect, this.iconTopDestRect, this.paint);
        canvas.drawBitmap(this.iconBottom, this.iconBottomRect, this.iconBottomDestRect, this.paint);
        canvas.drawBitmap(this.iconMiddle, this.iconMiddleRect, this.iconMiddleDestRect, this.paint);
    }

    private float aspectRatio(Bitmap bitmap) {
        return ((float) bitmap.getHeight()) / ((float) bitmap.getWidth());
    }
}
