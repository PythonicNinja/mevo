package com.mevo.app.presentation.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import com.mevo.app.C0434R;

public class BikesListDividerDecoration extends ItemDecoration {
    private Drawable mDivider;

    public BikesListDividerDecoration(Context context) {
        this.mDivider = context.getResources().getDrawable(C0434R.drawable.view_bike_divider_decoration);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
        state = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int bottom = childAt.getBottom() + ((LayoutParams) childAt.getLayoutParams()).bottomMargin;
            this.mDivider.setBounds(state, bottom, width, this.mDivider.getIntrinsicHeight() + bottom);
            this.mDivider.draw(canvas);
        }
    }
}
