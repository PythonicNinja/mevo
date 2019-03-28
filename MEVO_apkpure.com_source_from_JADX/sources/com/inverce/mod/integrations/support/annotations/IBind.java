package com.inverce.mod.integrations.support.annotations;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.inverce.mod.integrations.support.recycler.BindViewHolder;

public interface IBind<I, VH extends ViewHolder> {

    public interface ToHolder<T> {
        void bind(T t, BindViewHolder bindViewHolder, int i);
    }

    public interface ToView<T, V extends View> {
        void bind(T t, V v, int i);
    }

    void onBindViewHolder(VH vh, I i, int i2);
}
