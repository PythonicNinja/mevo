package me.dm7.barcodescanner.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class ViewFinderView extends View implements IViewFinder {
    private static final long ANIMATION_DELAY = 80;
    private static final float LANDSCAPE_HEIGHT_RATIO = 0.625f;
    private static final float LANDSCAPE_WIDTH_HEIGHT_RATIO = 1.4f;
    private static final int MIN_DIMENSION_DIFF = 50;
    private static final int POINT_SIZE = 10;
    private static final float PORTRAIT_WIDTH_HEIGHT_RATIO = 0.75f;
    private static final float PORTRAIT_WIDTH_RATIO = 0.75f;
    private static final int[] SCANNER_ALPHA = new int[]{0, 64, 128, 192, 255, 192, 128, 64};
    private static final float SQUARE_DIMENSION_RATIO = 0.625f;
    private static final String TAG = "ViewFinderView";
    protected int mBorderLineLength;
    protected Paint mBorderPaint;
    private final int mDefaultBorderColor = getResources().getColor(C0510R.color.viewfinder_border);
    private final int mDefaultBorderLineLength = getResources().getInteger(C0510R.integer.viewfinder_border_length);
    private final int mDefaultBorderStrokeWidth = getResources().getInteger(C0510R.integer.viewfinder_border_width);
    private final int mDefaultLaserColor = getResources().getColor(C0510R.color.viewfinder_laser);
    private final int mDefaultMaskColor = getResources().getColor(C0510R.color.viewfinder_mask);
    protected Paint mFinderMaskPaint;
    private Rect mFramingRect;
    protected Paint mLaserPaint;
    protected boolean mSquareViewFinder;
    private int scannerAlpha;

    public ViewFinderView(Context context) {
        super(context);
        init();
    }

    public ViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.mLaserPaint = new Paint();
        this.mLaserPaint.setColor(this.mDefaultLaserColor);
        this.mLaserPaint.setStyle(Style.FILL);
        this.mFinderMaskPaint = new Paint();
        this.mFinderMaskPaint.setColor(this.mDefaultMaskColor);
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setColor(this.mDefaultBorderColor);
        this.mBorderPaint.setStyle(Style.STROKE);
        this.mBorderPaint.setStrokeWidth((float) this.mDefaultBorderStrokeWidth);
        this.mBorderLineLength = this.mDefaultBorderLineLength;
    }

    public void setLaserColor(int i) {
        this.mLaserPaint.setColor(i);
    }

    public void setMaskColor(int i) {
        this.mFinderMaskPaint.setColor(i);
    }

    public void setBorderColor(int i) {
        this.mBorderPaint.setColor(i);
    }

    public void setBorderStrokeWidth(int i) {
        this.mBorderPaint.setStrokeWidth((float) i);
    }

    public void setBorderLineLength(int i) {
        this.mBorderLineLength = i;
    }

    public void setSquareViewFinder(boolean z) {
        this.mSquareViewFinder = z;
    }

    public void setupViewFinder() {
        updateFramingRect();
        invalidate();
    }

    public Rect getFramingRect() {
        return this.mFramingRect;
    }

    public void onDraw(Canvas canvas) {
        if (getFramingRect() != null) {
            drawViewFinderMask(canvas);
            drawViewFinderBorder(canvas);
            drawLaser(canvas);
        }
    }

    public void drawViewFinderMask(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Rect framingRect = getFramingRect();
        float f = (float) width;
        canvas.drawRect(0.0f, 0.0f, f, (float) framingRect.top, this.mFinderMaskPaint);
        canvas.drawRect(0.0f, (float) framingRect.top, (float) framingRect.left, (float) (framingRect.bottom + 1), this.mFinderMaskPaint);
        Canvas canvas2 = canvas;
        float f2 = f;
        canvas2.drawRect((float) (framingRect.right + 1), (float) framingRect.top, f2, (float) (framingRect.bottom + 1), this.mFinderMaskPaint);
        canvas2.drawRect(0.0f, (float) (framingRect.bottom + 1), f2, (float) height, this.mFinderMaskPaint);
    }

    public void drawViewFinderBorder(Canvas canvas) {
        Rect framingRect = getFramingRect();
        canvas.drawLine((float) (framingRect.left - 1), (float) (framingRect.top - 1), (float) (framingRect.left - 1), (float) ((framingRect.top - 1) + this.mBorderLineLength), this.mBorderPaint);
        canvas.drawLine((float) (framingRect.left - 1), (float) (framingRect.top - 1), (float) ((framingRect.left - 1) + this.mBorderLineLength), (float) (framingRect.top - 1), this.mBorderPaint);
        canvas.drawLine((float) (framingRect.left - 1), (float) (framingRect.bottom + 1), (float) (framingRect.left - 1), (float) ((framingRect.bottom + 1) - this.mBorderLineLength), this.mBorderPaint);
        canvas.drawLine((float) (framingRect.left - 1), (float) (framingRect.bottom + 1), (float) ((framingRect.left - 1) + this.mBorderLineLength), (float) (framingRect.bottom + 1), this.mBorderPaint);
        canvas.drawLine((float) (framingRect.right + 1), (float) (framingRect.top - 1), (float) (framingRect.right + 1), (float) ((framingRect.top - 1) + this.mBorderLineLength), this.mBorderPaint);
        canvas.drawLine((float) (framingRect.right + 1), (float) (framingRect.top - 1), (float) ((framingRect.right + 1) - this.mBorderLineLength), (float) (framingRect.top - 1), this.mBorderPaint);
        canvas.drawLine((float) (framingRect.right + 1), (float) (framingRect.bottom + 1), (float) (framingRect.right + 1), (float) ((framingRect.bottom + 1) - this.mBorderLineLength), this.mBorderPaint);
        canvas.drawLine((float) (framingRect.right + 1), (float) (framingRect.bottom + 1), (float) ((framingRect.right + 1) - this.mBorderLineLength), (float) (framingRect.bottom + 1), this.mBorderPaint);
    }

    public void drawLaser(Canvas canvas) {
        Rect framingRect = getFramingRect();
        this.mLaserPaint.setAlpha(SCANNER_ALPHA[this.scannerAlpha]);
        this.scannerAlpha = (this.scannerAlpha + 1) % SCANNER_ALPHA.length;
        int height = (framingRect.height() / 2) + framingRect.top;
        canvas.drawRect((float) (framingRect.left + 2), (float) (height - 1), (float) (framingRect.right - 1), (float) (height + 2), this.mLaserPaint);
        postInvalidateDelayed(ANIMATION_DELAY, framingRect.left - 10, framingRect.top - 10, framingRect.right + 10, framingRect.bottom + 10);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        updateFramingRect();
    }

    public synchronized void updateFramingRect() {
        int i;
        Point point = new Point(getWidth(), getHeight());
        int screenOrientation = DisplayUtils.getScreenOrientation(getContext());
        if (this.mSquareViewFinder) {
            if (screenOrientation != 1) {
                screenOrientation = (int) (((float) getHeight()) * 0.625f);
            } else {
                screenOrientation = (int) (((float) getWidth()) * 0.625f);
            }
            i = screenOrientation;
        } else if (screenOrientation != 1) {
            screenOrientation = (int) (((float) getHeight()) * 0.625f);
            i = screenOrientation;
            screenOrientation = (int) (((float) screenOrientation) * LANDSCAPE_WIDTH_HEIGHT_RATIO);
        } else {
            screenOrientation = (int) (((float) getWidth()) * 0.75f);
            i = (int) (((float) screenOrientation) * 0.75f);
        }
        if (screenOrientation > getWidth()) {
            screenOrientation = getWidth() - 50;
        }
        if (i > getHeight()) {
            i = getHeight() - 50;
        }
        int i2 = (point.x - screenOrientation) / 2;
        int i3 = (point.y - i) / 2;
        this.mFramingRect = new Rect(i2, i3, screenOrientation + i2, i + i3);
    }
}
