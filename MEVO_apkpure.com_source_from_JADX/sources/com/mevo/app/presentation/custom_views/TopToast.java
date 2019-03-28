package com.mevo.app.presentation.custom_views;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.tools.Lifecycle;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopToast extends RelativeLayout {
    public static final long DURATION_LONG = 5000;
    public static final long DURATION_SHORT = 2500;
    private static final long SLIDE_DURATION = 200;
    public static final int TYPE_ERROR = 0;
    public static final int TYPE_SUCCESS = 1;
    private static boolean toastIsBeingDisplayed;
    private static ConcurrentLinkedQueue<QueuedToast> toastsQueue = new ConcurrentLinkedQueue();
    private long duration;
    private float heightPx;
    private float marginTopPx;
    private TextView messageText;

    /* renamed from: com.mevo.app.presentation.custom_views.TopToast$1 */
    class C04401 implements AnimatorListener {

        /* renamed from: com.mevo.app.presentation.custom_views.TopToast$1$1 */
        class C04391 implements Runnable {
            C04391() {
            }

            public void run() {
                TopToast.this.slideOut();
            }
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        C04401() {
        }

        public void onAnimationEnd(Animator animator) {
            TopToast.this.postDelayed(new C04391(), TopToast.this.duration);
        }
    }

    /* renamed from: com.mevo.app.presentation.custom_views.TopToast$2 */
    class C04412 implements AnimatorListener {
        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        C04412() {
        }

        public void onAnimationEnd(Animator animator) {
            ((ViewGroup) TopToast.this.getParent()).removeView(TopToast.this);
            TopToast.toastIsBeingDisplayed = null;
            TopToast.tryExecuteQueue();
        }
    }

    private static class QueuedToast {
        public long duration;
        @StringRes
        public int messageRes;
        public int type;

        private QueuedToast() {
        }
    }

    public static void show(@StringRes int i, int i2, long j) {
        QueuedToast queuedToast = new QueuedToast();
        queuedToast.messageRes = i;
        queuedToast.type = i2;
        queuedToast.duration = j;
        toastsQueue.add(queuedToast);
        tryExecuteQueue();
    }

    private static void tryExecuteQueue() {
        if (!(toastIsBeingDisplayed || toastsQueue.isEmpty())) {
            Context activity = Lifecycle.getActivity();
            if (activity == null) {
                toastsQueue.clear();
                return;
            }
            QueuedToast queuedToast = (QueuedToast) toastsQueue.poll();
            View topToast = new TopToast(activity);
            topToast.setText(activity.getString(queuedToast.messageRes));
            topToast.setType(activity, queuedToast.type);
            topToast.setDuration(queuedToast.duration);
            activity.getWindow().addContentView(topToast, new LayoutParams(-1, -1));
            topToast.slideIn();
        }
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public TopToast(Context context) {
        super(context);
        init(context);
    }

    public TopToast(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public TopToast(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    @TargetApi(21)
    public TopToast(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    public void init(Context context) {
        inflate(context, C0434R.layout.view_toast, this);
        findViews();
    }

    private void findViews() {
        this.messageText = (TextView) findViewById(C0434R.id.fragment_login_toast_message_text);
    }

    public void setText(String str) {
        if (str != null) {
            this.messageText.setText(str);
        }
    }

    public void setType(Context context, int i) {
        if (i == 0) {
            this.messageText.setTextColor(ResourcesCompat.getColor(context.getResources(), C0434R.color.textColorToastError, null));
            this.messageText.setBackground(ResourcesCompat.getDrawable(context.getResources(), C0434R.drawable.toast_background_error, null));
            return;
        }
        this.messageText.setTextColor(ResourcesCompat.getColor(context.getResources(), C0434R.color.textColorToastSuccess, null));
        this.messageText.setBackground(ResourcesCompat.getDrawable(context.getResources(), C0434R.drawable.toast_background_success, null));
    }

    private void slideIn() {
        toastIsBeingDisplayed = true;
        this.messageText.measure(0, 0);
        this.marginTopPx = App.getAppContext().getResources().getDimension(C0434R.dimen.custom_toast_margin_top);
        this.heightPx = (float) this.messageText.getMeasuredHeight();
        this.messageText.setY(-this.heightPx);
        this.messageText.animate().yBy(this.marginTopPx + this.heightPx).setDuration(SLIDE_DURATION).setListener(new C04401()).start();
    }

    private void slideOut() {
        this.messageText.animate().yBy((-this.heightPx) - this.marginTopPx).setDuration(SLIDE_DURATION).setListener(new C04412()).start();
    }
}
