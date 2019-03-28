package com.inverce.mod.core.collections;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class TraverseTreeCollection<T extends TreeNode<T>> extends AbstractCollection<T> {
    protected final boolean isAsc;
    protected final boolean isDfs;
    @Nullable
    protected final T node;

    private class Iter implements Iterator<T> {
        Stack<T> box = new Stack();
        Queue<T> que = new LinkedList();

        Iter() {
            if (TraverseTreeCollection.this.isDfs) {
                this.box.push(TraverseTreeCollection.this.node);
            } else {
                pushChildren(this.que, TraverseTreeCollection.this.node);
            }
        }

        public boolean hasNext() {
            if (TraverseTreeCollection.this.isDfs) {
                if (this.box.empty()) {
                    return false;
                }
            } else if (this.que.isEmpty()) {
                return false;
            }
            return true;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("tree ran out of elements");
            } else if (TraverseTreeCollection.this.isDfs) {
                r0 = (TreeNode) this.box.pop();
                pushChildren(this.box, r0);
                return r0;
            } else {
                r0 = (TreeNode) this.que.remove();
                pushChildren(this.que, r0);
                return r0;
            }
        }

        private void pushChildren(@NonNull Collection<T> collection, @NonNull T t) {
            if (TraverseTreeCollection.this.isAsc) {
                collection.addAll(new ReverseOrderList(t.getChildren()));
            } else {
                collection.addAll(t.getChildren());
            }
        }
    }

    private static class ReverseOrderList<E> extends AbstractList<E> {
        @Nullable
        private List<E> inner;

        ReverseOrderList(@Nullable List<E> list) {
            if (list == null) {
                list = new ArrayList();
            }
            this.inner = list;
        }

        public E get(int i) {
            return this.inner.get((this.inner.size() - 1) - i);
        }

        public int size() {
            return this.inner.size();
        }
    }

    public TraverseTreeCollection(T t) {
        this(t, 23);
    }

    public TraverseTreeCollection(@Nullable T t, int i) {
        if ((i & 1) != 1) {
            throw new IllegalStateException("Specify method. DFS or BFS");
        } else if ((i & 13) == 13) {
            throw new IllegalStateException("Only one of DFS or BFS is allowed");
        } else if ((i & 50) == 50) {
            throw new IllegalStateException("Only one of ASC or DESC is allowed");
        } else if (t == null) {
            throw new IllegalStateException("Node must not be null");
        } else {
            this.node = t;
            boolean z = false;
            this.isDfs = (i & 5) == 5 ? true : null;
            if ((i & 18) == 18) {
                z = true;
            }
            this.isAsc = z;
        }
    }

    @NonNull
    public Iterator<T> iterator() {
        return new Iter();
    }

    public int size() {
        return this.node.inSize();
    }
}
