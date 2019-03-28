package com.mevo.app.tools;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.util.LinkedList;
import java.util.List;

public class SoftKeyboardStateWatcher implements OnGlobalLayoutListener {
    private final View activityRootView;
    private boolean isSoftKeyboardOpened;
    private int lastSoftKeyboardHeightInPx;
    private final List<SoftKeyboardStateListener> listeners;

    public interface SoftKeyboardStateListener {
        void onSoftKeyboardClosed();

        void onSoftKeyboardOpened(int i);
    }

    public SoftKeyboardStateWatcher(View view) {
        this(view, false);
    }

    public SoftKeyboardStateWatcher(View view, boolean z) {
        this.listeners = new LinkedList();
        this.activityRootView = view;
        this.isSoftKeyboardOpened = z;
        view.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public void onGlobalLayout() {
        Rect rect = new Rect();
        this.activityRootView.getWindowVisibleDisplayFrame(rect);
        int height = this.activityRootView.getRootView().getHeight() - (rect.bottom - rect.top);
        if (!this.isSoftKeyboardOpened && height > this.activityRootView.getRootView().getHeight() / 4) {
            this.isSoftKeyboardOpened = true;
            notifyOnSoftKeyboardOpened(height);
        } else if (this.isSoftKeyboardOpened && height < this.activityRootView.getRootView().getHeight() / 4) {
            this.isSoftKeyboardOpened = false;
            notifyOnSoftKeyboardClosed();
        }
    }

    public void setIsSoftKeyboardOpened(boolean z) {
        this.isSoftKeyboardOpened = z;
    }

    public boolean isSoftKeyboardOpened() {
        return this.isSoftKeyboardOpened;
    }

    public int getLastSoftKeyboardHeightInPx() {
        return this.lastSoftKeyboardHeightInPx;
    }

    public void addSoftKeyboardStateListener(SoftKeyboardStateListener softKeyboardStateListener) {
        this.listeners.add(softKeyboardStateListener);
    }

    public void removeSoftKeyboardStateListener(SoftKeyboardStateListener softKeyboardStateListener) {
        this.listeners.remove(softKeyboardStateListener);
    }

    private void notifyOnSoftKeyboardOpened(int i) {
        this.lastSoftKeyboardHeightInPx = i;
        for (SoftKeyboardStateListener softKeyboardStateListener : this.listeners) {
            if (softKeyboardStateListener != null) {
                softKeyboardStateListener.onSoftKeyboardOpened(i);
            }
        }
    }

    private void notifyOnSoftKeyboardClosed() {
        for (SoftKeyboardStateListener softKeyboardStateListener : this.listeners) {
            if (softKeyboardStateListener != null) {
                softKeyboardStateListener.onSoftKeyboardClosed();
            }
        }
    }
}
