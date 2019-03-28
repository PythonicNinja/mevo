package me.grantland.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.WeakHashMap;

public class AutofitLayout extends FrameLayout {
    private boolean mEnabled;
    private WeakHashMap<View, AutofitHelper> mHelpers = new WeakHashMap();
    private float mMinTextSize;
    private float mPrecision;

    public AutofitLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public AutofitLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public AutofitLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        boolean z = true;
        int i2 = -1;
        float f = -1.0f;
        if (attributeSet != null) {
            context = context.obtainStyledAttributes(attributeSet, C0514R.styleable.AutofitTextView, i, 0);
            z = context.getBoolean(C0514R.styleable.AutofitTextView_sizeToFit, true);
            i2 = context.getDimensionPixelSize(C0514R.styleable.AutofitTextView_minTextSize, -1);
            f = context.getFloat(C0514R.styleable.AutofitTextView_precision, -1.0f);
            context.recycle();
        }
        this.mEnabled = z;
        this.mMinTextSize = (float) i2;
        this.mPrecision = f;
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        TextView textView = (TextView) view;
        i = AutofitHelper.create(textView).setEnabled(this.mEnabled);
        if (this.mPrecision > null) {
            i.setPrecision(this.mPrecision);
        }
        if (this.mMinTextSize > null) {
            i.setMinTextSize(null, this.mMinTextSize);
        }
        this.mHelpers.put(textView, i);
    }

    public AutofitHelper getAutofitHelper(TextView textView) {
        return (AutofitHelper) this.mHelpers.get(textView);
    }

    public AutofitHelper getAutofitHelper(int i) {
        return (AutofitHelper) this.mHelpers.get(getChildAt(i));
    }
}
