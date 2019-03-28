package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;

class CardViewBaseImpl implements CardViewImpl {
    private final RectF mCornerRect = new RectF();

    /* renamed from: android.support.v7.widget.CardViewBaseImpl$1 */
    class C06141 implements RoundRectHelper {
        C06141() {
        }

        public void drawRoundRect(Canvas canvas, RectF rectF, float f, Paint paint) {
            C06141 c06141 = this;
            Canvas canvas2 = canvas;
            RectF rectF2 = rectF;
            float f2 = 2.0f * f;
            float width = (rectF.width() - f2) - 1.0f;
            float height = (rectF.height() - f2) - 1.0f;
            if (f >= 1.0f) {
                float f3 = f + 0.5f;
                float f4 = -f3;
                CardViewBaseImpl.this.mCornerRect.set(f4, f4, f3, f3);
                int save = canvas.save();
                canvas2.translate(rectF2.left + f3, rectF2.top + f3);
                canvas2.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint);
                canvas2.translate(width, 0.0f);
                canvas2.rotate(90.0f);
                Paint paint2 = paint;
                canvas2.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint2);
                canvas2.translate(height, 0.0f);
                canvas2.rotate(90.0f);
                canvas2.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint2);
                canvas2.translate(width, 0.0f);
                canvas2.rotate(90.0f);
                canvas2.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint2);
                canvas2.restoreToCount(save);
                canvas2.drawRect((rectF2.left + f3) - 1.0f, rectF2.top, (rectF2.right - f3) + 1.0f, rectF2.top + f3, paint);
                canvas2.drawRect((rectF2.left + f3) - 1.0f, rectF2.bottom - f3, (rectF2.right - f3) + 1.0f, rectF2.bottom, paint);
            }
            canvas2.drawRect(rectF2.left, rectF2.top + f, rectF2.right, rectF2.bottom - f, paint);
        }
    }

    public void onCompatPaddingChanged(CardViewDelegate cardViewDelegate) {
    }

    CardViewBaseImpl() {
    }

    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = new C06141();
    }

    public void initialize(CardViewDelegate cardViewDelegate, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        context = createBackground(context, colorStateList, f, f2, f3);
        context.setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
        cardViewDelegate.setCardBackground(context);
        updatePadding(cardViewDelegate);
    }

    private RoundRectDrawableWithShadow createBackground(Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        return new RoundRectDrawableWithShadow(context.getResources(), colorStateList, f, f2, f3);
    }

    public void updatePadding(CardViewDelegate cardViewDelegate) {
        Rect rect = new Rect();
        getShadowBackground(cardViewDelegate).getMaxShadowAndCornerPadding(rect);
        cardViewDelegate.setMinWidthHeightInternal((int) Math.ceil((double) getMinWidth(cardViewDelegate)), (int) Math.ceil((double) getMinHeight(cardViewDelegate)));
        cardViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void onPreventCornerOverlapChanged(CardViewDelegate cardViewDelegate) {
        getShadowBackground(cardViewDelegate).setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
        updatePadding(cardViewDelegate);
    }

    public void setBackgroundColor(CardViewDelegate cardViewDelegate, @Nullable ColorStateList colorStateList) {
        getShadowBackground(cardViewDelegate).setColor(colorStateList);
    }

    public ColorStateList getBackgroundColor(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getColor();
    }

    public void setRadius(CardViewDelegate cardViewDelegate, float f) {
        getShadowBackground(cardViewDelegate).setCornerRadius(f);
        updatePadding(cardViewDelegate);
    }

    public float getRadius(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getCornerRadius();
    }

    public void setElevation(CardViewDelegate cardViewDelegate, float f) {
        getShadowBackground(cardViewDelegate).setShadowSize(f);
    }

    public float getElevation(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getShadowSize();
    }

    public void setMaxElevation(CardViewDelegate cardViewDelegate, float f) {
        getShadowBackground(cardViewDelegate).setMaxShadowSize(f);
        updatePadding(cardViewDelegate);
    }

    public float getMaxElevation(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getMaxShadowSize();
    }

    public float getMinWidth(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getMinWidth();
    }

    public float getMinHeight(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getMinHeight();
    }

    private RoundRectDrawableWithShadow getShadowBackground(CardViewDelegate cardViewDelegate) {
        return (RoundRectDrawableWithShadow) cardViewDelegate.getCardBackground();
    }
}