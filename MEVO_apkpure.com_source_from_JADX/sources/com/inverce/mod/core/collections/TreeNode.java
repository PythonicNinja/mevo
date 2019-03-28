package com.inverce.mod.core.collections;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TreeNode<T extends TreeNode<T>> {
    @Nullable
    protected List<T> children;

    public TreeNode() {
        this(null);
    }

    public TreeNode(@Nullable List<T> list) {
        if (list != null) {
            this.children = new ArrayList(list);
        } else {
            this.children = new ArrayList();
        }
    }

    @NonNull
    public List<T> getChildren() {
        return this.children;
    }

    public synchronized int inSize() {
        int size;
        size = this.children.size();
        for (TreeNode inSize : this.children) {
            size += inSize.inSize();
        }
        return size;
    }
}
