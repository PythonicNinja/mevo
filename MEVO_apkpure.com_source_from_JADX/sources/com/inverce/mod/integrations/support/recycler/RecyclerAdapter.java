package com.inverce.mod.integrations.support.recycler;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Ui;
import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerAdapter<ITEM, VH extends ViewHolder> extends Adapter<VH> {
    @NonNull
    protected List<? extends ITEM> data = new ArrayList();

    public abstract void onBindViewHolder(VH vh, ITEM item, int i);

    public ITEM getItem(int i) {
        return this.data.get(i);
    }

    public void onBindViewHolder(VH vh, int i) {
        onBindViewHolder(vh, getItem(i), i);
    }

    public int getItemCount() {
        return this.data.size();
    }

    public void setData(List<? extends ITEM> list) {
        setData(list, false);
    }

    public void setData(@Nullable List<? extends ITEM> list, boolean z) {
        if (list == null) {
            list = new ArrayList();
        }
        if (Ui.isUiThread()) {
            if (z) {
                DiffUtil.calculateDiff(new EasyDiffUtilCallBack(list, new ArrayList(this.data))).dispatchUpdatesTo((Adapter) this);
                this.data = list;
            } else {
                this.data = list;
                notifyDataSetChanged();
            }
            return;
        }
        IM.onUi().execute(new RecyclerAdapter$$Lambda$0(this, list, z));
    }

    final /* synthetic */ void lambda$setData$0$RecyclerAdapter(List list, boolean z) {
        setData(list, z);
    }

    protected View inflate(@NonNull ViewGroup viewGroup, @LayoutRes int i) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
    }
}
