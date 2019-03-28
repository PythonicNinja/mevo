package com.inverce.mod.integrations.support.annotations;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface ICreateVH<VH extends ViewHolder> {
    VH onCreateViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater);
}
