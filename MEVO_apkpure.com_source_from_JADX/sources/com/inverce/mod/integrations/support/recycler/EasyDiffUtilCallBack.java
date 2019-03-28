package com.inverce.mod.integrations.support.recycler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil.Callback;
import java.util.ArrayList;
import java.util.List;

public class EasyDiffUtilCallBack<T> extends Callback {
    @NonNull
    private List<? extends T> newList;
    @NonNull
    private List<? extends T> oldList;

    public EasyDiffUtilCallBack(@Nullable List<? extends T> list, @Nullable List<? extends T> list2) {
        if (list == null) {
            list = new ArrayList();
        }
        this.newList = list;
        if (list2 == null) {
            list2 = new ArrayList();
        }
        this.oldList = list2;
    }

    public int getOldListSize() {
        return this.oldList.size();
    }

    public int getNewListSize() {
        return this.newList.size();
    }

    public boolean areItemsTheSame(int i, int i2) {
        return this.oldList.get(i).equals(this.newList.get(i2));
    }

    public boolean areContentsTheSame(int i, int i2) {
        return this.oldList.get(i).equals(this.newList.get(i2));
    }

    @Nullable
    public Object getChangePayload(int i, int i2) {
        return super.getChangePayload(i, i2);
    }
}
