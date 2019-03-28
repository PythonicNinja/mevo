package com.inverce.mod.integrations.support.recycler;

import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import com.inverce.mod.core.IM;

public class SimpleDividerDecorator extends DividerItemDecoration {
    public SimpleDividerDecorator(@DrawableRes int i) {
        this(i, 1);
    }

    public SimpleDividerDecorator(@DrawableRes int i, int i2) {
        super(IM.context(), i2);
        setDrawable(ContextCompat.getDrawable(IM.context(), i));
    }
}
