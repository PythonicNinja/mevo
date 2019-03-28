package com.inverce.mod.integrations.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.inverce.mod.integrations.C0431R;

public class SquareImageView extends AppCompatImageView {
    private MajorDim majorDim;

    protected enum MajorDim {
        WIDTH(0),
        HEIGHT(1);
        
        private int id;

        private MajorDim(int i) {
            this.id = i;
        }

        @NonNull
        static MajorDim fromId(int i) {
            for (MajorDim majorDim : values()) {
                if (majorDim.id == i) {
                    return majorDim;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public SquareImageView(@NonNull Context context) {
        super(context);
        this.majorDim = MajorDim.WIDTH;
    }

    public SquareImageView(@NonNull Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SquareImageView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.majorDim = MajorDim.WIDTH;
        if (attributeSet != null) {
            context = context.obtainStyledAttributes(attributeSet, C0431R.styleable.SquareImageView, i, 0);
            this.majorDim = MajorDim.fromId(context.getInt(C0431R.styleable.SquareImageView_majorDim, 0));
            context.recycle();
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        i = getMeasuredWidth();
        if (MajorDim.HEIGHT.equals(this.majorDim) != 0) {
            i = getMeasuredHeight();
        }
        setMeasuredDimension(i, i);
    }

    @NonNull
    public SquareImageView setMajorDim(MajorDim majorDim) {
        this.majorDim = majorDim;
        return this;
    }
}
