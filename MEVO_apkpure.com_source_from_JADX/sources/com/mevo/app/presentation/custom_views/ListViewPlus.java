package com.mevo.app.presentation.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

@Deprecated
public class ListViewPlus extends ListView {
    int lastLimit = 0;
    int lastMeasSpecH;
    int lastMeasSpecW;
    int prevMeasure = 0;
    int skip = 0;
    SparseArray<View> viewsArray = new SparseArray(2);

    public void init(Context context, AttributeSet attributeSet, int i, int i2) {
    }

    public ListViewPlus(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public ListViewPlus(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public ListViewPlus(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public ListViewPlus(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    public void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        this.viewsArray.clear();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.viewsArray.clear();
    }

    protected void onMeasure(int i, int i2) {
        ListAdapter adapter = getAdapter();
        if (adapter != null) {
            if (adapter.getCount() != 0) {
                int count = adapter.getCount();
                StringBuilder stringBuilder;
                if (this.prevMeasure <= 0 || this.lastLimit != count) {
                    int measuredHeight;
                    if (count != 0) {
                        if (count == getChildCount()) {
                            if (getChildCount() > 1) {
                                int i3 = 0;
                                for (i2 = 0; i2 < Math.min(count, getChildCount()); i2++) {
                                    View childAt = getChildAt(i2);
                                    childAt.measure(i, MeasureSpec.makeMeasureSpec(0, 0));
                                    measuredHeight = childAt.getMeasuredHeight();
                                    if (measuredHeight > 0) {
                                        i3 += measuredHeight;
                                    }
                                }
                                i3 = (i3 + heightForDividers(count - 1)) + ((getListPaddingTop() + getListPaddingBottom()) + (getVerticalFadingEdgeLength() * 2));
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("onMeasureFast: limit=");
                                stringBuilder.append(count);
                                stringBuilder.append(" height:");
                                stringBuilder.append(i3);
                                Log.v("ListPlus", stringBuilder.toString());
                                i2 = MeasureSpec.makeMeasureSpec(i3, ErrorDialogData.SUPPRESSED);
                                this.prevMeasure = i3;
                                this.lastLimit = getChildCount();
                                super.onMeasure(i, i2);
                            } else {
                                Log.v("ListPlus", "onMeasureFast: limit=0");
                                super.onMeasure(i, i2);
                            }
                        }
                    }
                    this.viewsArray.clear();
                    measuredHeight = 0;
                    for (i2 = 0; i2 < count; i2++) {
                        int itemViewType = adapter.getItemViewType(i2);
                        View view = adapter.getView(i2, (View) this.viewsArray.get(itemViewType), this);
                        this.viewsArray.put(itemViewType, view);
                        view.setLayoutParams(new LayoutParams(0, 0));
                        view.measure(i, MeasureSpec.makeMeasureSpec(0, 0));
                        itemViewType = view.getMeasuredHeight();
                        if (itemViewType > 0) {
                            measuredHeight += itemViewType;
                        }
                    }
                    measuredHeight = (measuredHeight + heightForDividers(count - 1)) + ((getListPaddingTop() + getListPaddingBottom()) + (getVerticalFadingEdgeLength() * 2));
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("onMeasureSlow: limit=");
                    stringBuilder2.append(count);
                    stringBuilder2.append(" cc:");
                    stringBuilder2.append(getChildCount());
                    stringBuilder2.append(" height:");
                    stringBuilder2.append(measuredHeight);
                    stringBuilder2.append("|");
                    stringBuilder2.append(this.prevMeasure);
                    Log.v("ListPlus", stringBuilder2.toString());
                    this.prevMeasure = measuredHeight;
                    this.lastLimit = count;
                    i2 = MeasureSpec.makeMeasureSpec(measuredHeight, ErrorDialogData.SUPPRESSED);
                    super.onMeasure(i, i2);
                } else {
                    i2 = MeasureSpec.makeMeasureSpec(this.prevMeasure, ErrorDialogData.SUPPRESSED);
                    super.onMeasure(i, i2);
                    this.skip++;
                    if (i2 == this.lastMeasSpecH) {
                        this.skip++;
                    }
                    if (this.skip > 2) {
                        this.prevMeasure = 0;
                        this.skip = 0;
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("onMeasureSkip: limit=");
                    stringBuilder.append(count);
                    stringBuilder.append(" height:");
                    stringBuilder.append(0);
                    Log.v("ListPlus", stringBuilder.toString());
                }
                this.lastMeasSpecW = i;
                this.lastMeasSpecH = i2;
                return;
            }
        }
        super.onMeasure(i, i2);
    }

    private int heightForDividers(int i) {
        return i * Math.max(0, getDividerHeight());
    }
}
