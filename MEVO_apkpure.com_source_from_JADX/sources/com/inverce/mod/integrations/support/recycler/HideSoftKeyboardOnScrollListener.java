package com.inverce.mod.integrations.support.recycler;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import com.inverce.mod.core.Ui;
import java.lang.ref.WeakReference;

public class HideSoftKeyboardOnScrollListener extends OnScrollListener {
    boolean isScrolling;
    WeakReference<View> rootView;

    public HideSoftKeyboardOnScrollListener(View view) {
        this.rootView = new WeakReference(view);
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        recyclerView = true;
        if (i != 1) {
            recyclerView = null;
        }
        this.isScrolling = recyclerView;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        if (this.rootView.get() != null && this.isScrolling != null) {
            Ui.hideSoftInput(((View) this.rootView.get()).findFocus());
        }
    }
}
