package com.mevo.app.presentation.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Ui;
import com.mevo.app.C0434R;

public class BasicHeader extends RelativeLayout {
    private static final long CLICK_BLOCK_MS = 2000;
    private TextView headerText;
    private long lastClickTime;
    @Nullable
    BasicHeaderCallback onRefreshClicked;
    private ProgressBar progressBar;
    private ImageView refreshButton;

    public interface BasicHeaderCallback {
        void onRefresh();
    }

    static final /* synthetic */ void lambda$new$125$BasicHeader() {
    }

    public BasicHeader(Context context) {
        super(context);
        this.lastClickTime = 0;
        this.onRefreshClicked = BasicHeader$$Lambda$0.$instance;
        init(context, null, -1, -1);
    }

    public BasicHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lastClickTime = 0;
        this.onRefreshClicked = BasicHeader$$Lambda$1.$instance;
        init(context, attributeSet, -1, -1);
    }

    public BasicHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.lastClickTime = 0;
        this.onRefreshClicked = BasicHeader$$Lambda$2.$instance;
        init(context, attributeSet, i, -1);
    }

    @TargetApi(21)
    public BasicHeader(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.lastClickTime = 0;
        this.onRefreshClicked = BasicHeader$$Lambda$3.$instance;
        init(context, attributeSet, i, i2);
    }

    public void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.view_basic_header, this);
        findViews();
        setListeners();
        context = context.getTheme().obtainStyledAttributes(attributeSet, C0434R.styleable.BasicHeader, 0, 0);
        try {
            setHeader(context.getString(0));
            hideRefresh(context.getBoolean(1, false));
        } finally {
            context.recycle();
        }
    }

    private void findViews() {
        this.headerText = (TextView) findViewById(C0434R.id.view_basic_header_header_text);
        this.refreshButton = (ImageView) findViewById(C0434R.id.refresh_button);
        this.progressBar = (ProgressBar) findViewById(C0434R.id.progress_bar);
    }

    private void setListeners() {
        this.refreshButton.setOnClickListener(new BasicHeader$$Lambda$4(this));
    }

    final /* synthetic */ void lambda$setListeners$126$BasicHeader(View view) {
        if (SystemClock.elapsedRealtime() - this.lastClickTime >= CLICK_BLOCK_MS) {
            this.lastClickTime = SystemClock.elapsedRealtime();
            view.startAnimation(AnimationUtils.loadAnimation(IM.context(), C0434R.anim.refresh_animation));
            if (this.onRefreshClicked != null) {
                this.onRefreshClicked.onRefresh();
            }
        }
    }

    public void hideRefresh() {
        this.refreshButton.setVisibility(4);
    }

    private void hideRefresh(boolean z) {
        Ui.visible(this.refreshButton, z ^ 1);
    }

    public void setHeader(String str) {
        if (str != null) {
            this.headerText.setText(str);
        }
    }

    public BasicHeader setOnRefreshClicked(@Nullable BasicHeaderCallback basicHeaderCallback) {
        this.onRefreshClicked = basicHeaderCallback;
        return this;
    }

    public void setProgressBarVisibility(boolean z) {
        Ui.visible(this.progressBar, z);
    }
}
