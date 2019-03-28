package com.inverce.mod.integrations.support.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import java.io.Serializable;

public class EndlessRecyclerViewScrollListener extends OnScrollListener {
    public boolean loading = true;
    protected transient LayoutManager mLayoutManager;
    protected transient LoadMore onLoadMore;
    protected State state;
    public int visibleThreshold = 10;

    public interface LoadMore {
        void onLoadMore(int i, int i2, RecyclerView recyclerView);
    }

    public static class State implements Serializable {
        public int currentPage = 0;
        public int previousTotalItemCount = 0;
        public int startingPageIndex = 0;
    }

    public EndlessRecyclerViewScrollListener(@NonNull LayoutManager layoutManager, @NonNull LoadMore loadMore, int i) {
        this.mLayoutManager = layoutManager;
        this.state = new State();
        this.visibleThreshold = i;
        if (layoutManager instanceof GridLayoutManager) {
            this.visibleThreshold = ((GridLayoutManager) layoutManager).getSpanCount() * i;
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            this.visibleThreshold = i * ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        this.onLoadMore = loadMore;
    }

    public int getLastVisibleItem(@NonNull int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i2 == 0) {
                i = iArr[i2];
            } else if (iArr[i2] > i) {
                i = iArr[i2];
            }
        }
        return i;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        i = this.mLayoutManager.getItemCount();
        i2 = (this.mLayoutManager instanceof StaggeredGridLayoutManager) != 0 ? getLastVisibleItem(((StaggeredGridLayoutManager) this.mLayoutManager).findLastVisibleItemPositions(null)) : (this.mLayoutManager instanceof GridLayoutManager) != 0 ? ((GridLayoutManager) this.mLayoutManager).findLastVisibleItemPosition() : (this.mLayoutManager instanceof LinearLayoutManager) != 0 ? ((LinearLayoutManager) this.mLayoutManager).findLastVisibleItemPosition() : 0;
        if (i < this.state.previousTotalItemCount) {
            this.state.currentPage = this.state.startingPageIndex;
            this.state.previousTotalItemCount = i;
            if (i == 0) {
                this.loading = true;
            }
        }
        if (this.loading && i > this.state.previousTotalItemCount) {
            this.loading = false;
            this.state.previousTotalItemCount = i;
        }
        if (!this.loading && r7 + this.visibleThreshold > i) {
            i2 = this.state;
            i2.currentPage++;
            this.onLoadMore.onLoadMore(this.state.currentPage, i, recyclerView);
            this.loading = true;
        }
    }

    public void resetState() {
        this.state.currentPage = this.state.startingPageIndex;
        this.state.previousTotalItemCount = 0;
        this.loading = true;
    }
}
