package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SwipeDismissBehavior<V extends View> extends Behavior<V> {
    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    float mAlphaEndSwipeDistance = 0.5f;
    float mAlphaStartSwipeDistance = 0.0f;
    private final Callback mDragCallback = new C05631();
    float mDragDismissThreshold = 0.5f;
    private boolean mInterceptingEvents;
    OnDismissListener mListener;
    private float mSensitivity = 0.0f;
    private boolean mSensitivitySet;
    int mSwipeDirection = 2;
    ViewDragHelper mViewDragHelper;

    public interface OnDismissListener {
        void onDismiss(View view);

        void onDragStateChanged(int i);
    }

    private class SettleRunnable implements Runnable {
        private final boolean mDismiss;
        private final View mView;

        SettleRunnable(View view, boolean z) {
            this.mView = view;
            this.mDismiss = z;
        }

        public void run() {
            if (SwipeDismissBehavior.this.mViewDragHelper != null && SwipeDismissBehavior.this.mViewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.mView, this);
            } else if (this.mDismiss && SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDismiss(this.mView);
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    private @interface SwipeDirection {
    }

    /* renamed from: android.support.design.widget.SwipeDismissBehavior$1 */
    class C05631 extends Callback {
        private static final int INVALID_POINTER_ID = -1;
        private int mActivePointerId = -1;
        private int mOriginalCapturedViewLeft;

        C05631() {
        }

        public boolean tryCaptureView(View view, int i) {
            return (this.mActivePointerId != -1 || SwipeDismissBehavior.this.canSwipeDismissView(view) == null) ? null : true;
        }

        public void onViewCaptured(View view, int i) {
            this.mActivePointerId = i;
            this.mOriginalCapturedViewLeft = view.getLeft();
            view = view.getParent();
            if (view != null) {
                view.requestDisallowInterceptTouchEvent(1);
            }
        }

        public void onViewDragStateChanged(int i) {
            if (SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDragStateChanged(i);
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            this.mActivePointerId = -1;
            f2 = view.getWidth();
            if (shouldDismiss(view, f) != null) {
                f = view.getLeft() < this.mOriginalCapturedViewLeft ? this.mOriginalCapturedViewLeft - f2 : this.mOriginalCapturedViewLeft + f2;
                f2 = Float.MIN_VALUE;
            } else {
                f = this.mOriginalCapturedViewLeft;
                f2 = 0.0f;
            }
            if (SwipeDismissBehavior.this.mViewDragHelper.settleCapturedViewAt(f, view.getTop()) != null) {
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, f2));
            } else if (f2 != null && SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDismiss(view);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean shouldDismiss(android.view.View r6, float r7) {
            /*
            r5 = this;
            r0 = 0;
            r1 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1));
            r2 = 0;
            r3 = 1;
            if (r1 == 0) goto L_0x0041;
        L_0x0007:
            r6 = android.support.v4.view.ViewCompat.getLayoutDirection(r6);
            if (r6 != r3) goto L_0x000f;
        L_0x000d:
            r6 = 1;
            goto L_0x0010;
        L_0x000f:
            r6 = 0;
        L_0x0010:
            r1 = android.support.design.widget.SwipeDismissBehavior.this;
            r1 = r1.mSwipeDirection;
            r4 = 2;
            if (r1 != r4) goto L_0x0018;
        L_0x0017:
            return r3;
        L_0x0018:
            r1 = android.support.design.widget.SwipeDismissBehavior.this;
            r1 = r1.mSwipeDirection;
            if (r1 != 0) goto L_0x002c;
        L_0x001e:
            if (r6 == 0) goto L_0x0026;
        L_0x0020:
            r6 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1));
            if (r6 >= 0) goto L_0x002b;
        L_0x0024:
            r2 = 1;
            goto L_0x002b;
        L_0x0026:
            r6 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1));
            if (r6 <= 0) goto L_0x002b;
        L_0x002a:
            goto L_0x0024;
        L_0x002b:
            return r2;
        L_0x002c:
            r1 = android.support.design.widget.SwipeDismissBehavior.this;
            r1 = r1.mSwipeDirection;
            if (r1 != r3) goto L_0x0040;
        L_0x0032:
            if (r6 == 0) goto L_0x003a;
        L_0x0034:
            r6 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1));
            if (r6 <= 0) goto L_0x003f;
        L_0x0038:
            r2 = 1;
            goto L_0x003f;
        L_0x003a:
            r6 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1));
            if (r6 >= 0) goto L_0x003f;
        L_0x003e:
            goto L_0x0038;
        L_0x003f:
            return r2;
        L_0x0040:
            return r2;
        L_0x0041:
            r7 = r6.getLeft();
            r0 = r5.mOriginalCapturedViewLeft;
            r7 = r7 - r0;
            r6 = r6.getWidth();
            r6 = (float) r6;
            r0 = android.support.design.widget.SwipeDismissBehavior.this;
            r0 = r0.mDragDismissThreshold;
            r6 = r6 * r0;
            r6 = java.lang.Math.round(r6);
            r7 = java.lang.Math.abs(r7);
            if (r7 < r6) goto L_0x005e;
        L_0x005d:
            r2 = 1;
        L_0x005e:
            return r2;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.SwipeDismissBehavior.1.shouldDismiss(android.view.View, float):boolean");
        }

        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            int i3;
            i2 = ViewCompat.getLayoutDirection(view) == 1 ? 1 : 0;
            if (SwipeDismissBehavior.this.mSwipeDirection == 0) {
                if (i2 != 0) {
                    i2 = this.mOriginalCapturedViewLeft - view.getWidth();
                    i3 = this.mOriginalCapturedViewLeft;
                } else {
                    i2 = this.mOriginalCapturedViewLeft;
                    i3 = view.getWidth() + this.mOriginalCapturedViewLeft;
                }
            } else if (SwipeDismissBehavior.this.mSwipeDirection != 1) {
                i2 = this.mOriginalCapturedViewLeft - view.getWidth();
                i3 = view.getWidth() + this.mOriginalCapturedViewLeft;
            } else if (i2 != 0) {
                i2 = this.mOriginalCapturedViewLeft;
                i3 = view.getWidth() + this.mOriginalCapturedViewLeft;
            } else {
                i2 = this.mOriginalCapturedViewLeft - view.getWidth();
                i3 = this.mOriginalCapturedViewLeft;
            }
            return SwipeDismissBehavior.clamp(i2, i, i3);
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            i2 = ((float) this.mOriginalCapturedViewLeft) + (((float) view.getWidth()) * SwipeDismissBehavior.this.mAlphaStartSwipeDistance);
            i3 = ((float) this.mOriginalCapturedViewLeft) + (((float) view.getWidth()) * SwipeDismissBehavior.this.mAlphaEndSwipeDistance);
            i = (float) i;
            if (i <= i2) {
                view.setAlpha(1.0f);
            } else if (i >= i3) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.clamp(0.0f, 1065353216 - SwipeDismissBehavior.fraction(i2, i3, i), 1.0f));
            }
        }
    }

    static float fraction(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    public boolean canSwipeDismissView(@NonNull View view) {
        return true;
    }

    public void setListener(OnDismissListener onDismissListener) {
        this.mListener = onDismissListener;
    }

    public void setSwipeDirection(int i) {
        this.mSwipeDirection = i;
    }

    public void setDragDismissDistance(float f) {
        this.mDragDismissThreshold = clamp(0.0f, f, 1.0f);
    }

    public void setStartAlphaSwipeDistance(float f) {
        this.mAlphaStartSwipeDistance = clamp(0.0f, f, 1.0f);
    }

    public void setEndAlphaSwipeDistance(float f) {
        this.mAlphaEndSwipeDistance = clamp(0.0f, f, 1.0f);
    }

    public void setSensitivity(float f) {
        this.mSensitivity = f;
        this.mSensitivitySet = true;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z = this.mInterceptingEvents;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 3) {
            switch (actionMasked) {
                case 0:
                    this.mInterceptingEvents = coordinatorLayout.isPointInChildBounds(v, (int) motionEvent.getX(), (int) motionEvent.getY());
                    z = this.mInterceptingEvents;
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }
        this.mInterceptingEvents = false;
        if (!z) {
            return false;
        }
        ensureViewDragHelper(coordinatorLayout);
        return this.mViewDragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.mViewDragHelper == null) {
            return null;
        }
        this.mViewDragHelper.processTouchEvent(motionEvent);
        return true;
    }

    private void ensureViewDragHelper(ViewGroup viewGroup) {
        if (this.mViewDragHelper == null) {
            if (this.mSensitivitySet) {
                viewGroup = ViewDragHelper.create(viewGroup, this.mSensitivity, this.mDragCallback);
            } else {
                viewGroup = ViewDragHelper.create(viewGroup, this.mDragCallback);
            }
            this.mViewDragHelper = viewGroup;
        }
    }

    static float clamp(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    static int clamp(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    public int getDragState() {
        return this.mViewDragHelper != null ? this.mViewDragHelper.getViewDragState() : 0;
    }
}
