package com.inverce.mod.integrations.support.recycler;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.inverce.mod.core.Ui;
import com.inverce.mod.core.functional.IConsumer;

public class BindViewHolder extends ViewHolder {
    protected SparseArray<View> children = new SparseArray();
    protected boolean childrenInflated;

    public BindViewHolder(@NonNull View view) {
        super(view);
    }

    public SparseArray<View> inflateChildren() {
        if (this.childrenInflated) {
            return this.children;
        }
        this.childrenInflated = true;
        SparseArray<View> searchForViews = searchForViews(this.itemView, this.children);
        this.children = searchForViews;
        return searchForViews;
    }

    public SparseArray<View> getChildren() {
        if (this.childrenInflated) {
            return inflateChildren();
        }
        return this.children;
    }

    @NonNull
    protected static SparseArray<View> searchForViews(View view, @NonNull SparseArray<View> sparseArray) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                searchForViews(viewGroup.getChildAt(i), sparseArray);
            }
        }
        if (view.getId() != -1) {
            sparseArray.put(view.getId(), view);
        }
        return sparseArray;
    }

    @NonNull
    public <V extends View> V get(@IdRes int i) {
        View view = (View) this.children.get(i);
        if (view != null || this.childrenInflated || this.children.indexOfKey(i) >= 0) {
            return view;
        }
        SparseArray sparseArray = this.children;
        V findViewById = this.itemView.findViewById(i);
        sparseArray.put(i, findViewById);
        return findViewById;
    }

    public boolean has(@IdRes int i) {
        return get(i) != 0;
    }

    public <V extends View> boolean ifHas(@IdRes int i, @NonNull IConsumer<V> iConsumer) {
        i = get(i);
        if (i == 0) {
            return false;
        }
        iConsumer.accept(i);
        return true;
    }

    @NonNull
    public BindViewHolder bindText(@IdRes int i, String str) {
        TextView textView = (TextView) get(i);
        if (textView != null) {
            textView.setText(str);
        }
        return this;
    }

    @NonNull
    public BindViewHolder bindText(@IdRes int i, @StringRes int i2) {
        TextView textView = (TextView) get(i);
        if (textView != null) {
            textView.setText(i2);
        }
        return this;
    }

    @NonNull
    public BindViewHolder bindImage(@IdRes int i, @DrawableRes int i2) {
        ImageView imageView = (ImageView) get(i);
        if (imageView != null) {
            imageView.setImageResource(i2);
        }
        return this;
    }

    @NonNull
    public BindViewHolder bindBackground(@IdRes int i, @DrawableRes int i2) {
        i = get(i);
        if (i != 0) {
            i.setBackgroundResource(i2);
        }
        return this;
    }

    @NonNull
    public BindViewHolder bindBackground(@IdRes int i, Drawable drawable) {
        i = get(i);
        if (i != 0) {
            if (VERSION.SDK_INT >= 16) {
                i.setBackground(drawable);
            } else {
                i.setBackgroundDrawable(drawable);
            }
        }
        return this;
    }

    @NonNull
    public BindViewHolder bindVisibility(@IdRes int i, boolean z) {
        Ui.visible(get(i), z);
        return this;
    }

    @NonNull
    public BindViewHolder bindOnClickListener(@IdRes int i, OnClickListener onClickListener) {
        i = get(i);
        if (i != 0) {
            i.setOnClickListener(onClickListener);
        }
        return this;
    }
}
