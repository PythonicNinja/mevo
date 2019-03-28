package com.mevo.app.presentation.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.mevo.app.C0434R;

public class PinView extends FrameLayout {
    public PinView(Context context) {
        super(context);
        init(context, null, -1, -1);
    }

    public PinView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, -1, -1);
    }

    public PinView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, -1);
    }

    @TargetApi(21)
    public PinView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    public void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.view_map_pin, this);
    }

    public PinView bindData(int i, boolean z) {
        ((ImageView) findViewById(C0434R.id.view_map_pin_img)).setImageDrawable(ResourcesCompat.getDrawable(getResources(), z ? true : true, null));
        TextView textView = (TextView) findViewById(true);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append("");
        textView.setText(stringBuilder.toString());
        return this;
    }
}
