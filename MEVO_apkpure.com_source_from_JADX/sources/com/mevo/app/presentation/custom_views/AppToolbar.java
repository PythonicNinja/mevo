package com.mevo.app.presentation.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.inverce.mod.core.Ui;
import com.mevo.app.C0434R;

public class AppToolbar extends RelativeLayout {
    private static final String TAG = "AppToolbar";
    private AppToolbarListener appToolbarListener;
    private ImageView backButton;
    private ImageView contactButton;
    private ImageView settingsButton;

    public interface AppToolbarListener {
        void onBackClick();

        void onContactClick();

        void onSettingsClick();
    }

    public interface OverrideBackButtonListener {
        void onOverrideBackClick();
    }

    public AppToolbar(Context context) {
        super(context);
        init(context, null, -1, -1);
    }

    public AppToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, -1, -1);
    }

    public AppToolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, -1);
    }

    @TargetApi(21)
    public AppToolbar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    public void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.view_app_toolbar, this);
        findViews();
        setListeners();
        context = context.getTheme().obtainStyledAttributes(attributeSet, C0434R.styleable.AppToolbar, 0, 0);
        try {
            setBackVisibility(context.getBoolean(0, true));
            setSettingsVisibility(context.getBoolean(3, true));
            setContactVisibility(context.getBoolean(1, true));
        } finally {
            context.recycle();
        }
    }

    private void findViews() {
        this.settingsButton = (ImageView) findViewById(C0434R.id.view_app_toolbar_settings);
        this.backButton = (ImageView) findViewById(C0434R.id.view_app_toolbar_back);
        this.contactButton = (ImageView) findViewById(C0434R.id.view_app_toolbar_contact);
    }

    private void setListeners() {
        this.settingsButton.setOnClickListener(new AppToolbar$$Lambda$0(this));
        this.backButton.setOnClickListener(new AppToolbar$$Lambda$1(this));
        this.contactButton.setOnClickListener(new AppToolbar$$Lambda$2(this));
    }

    final /* synthetic */ void lambda$setListeners$122$AppToolbar(View view) {
        if (this.appToolbarListener != null) {
            this.appToolbarListener.onSettingsClick();
        }
        Ui.hideSoftInput(view);
    }

    final /* synthetic */ void lambda$setListeners$123$AppToolbar(View view) {
        if (this.appToolbarListener != null) {
            this.appToolbarListener.onBackClick();
        }
        Ui.hideSoftInput(view);
    }

    final /* synthetic */ void lambda$setListeners$124$AppToolbar(View view) {
        if (this.appToolbarListener != null) {
            this.appToolbarListener.onContactClick();
        }
        Ui.hideSoftInput(this);
    }

    public void setAppToolbarListener(AppToolbarListener appToolbarListener) {
        this.appToolbarListener = appToolbarListener;
    }

    public void setBackVisibility(boolean z) {
        if (z) {
            this.backButton.setVisibility(0);
        } else {
            this.backButton.setVisibility(4);
        }
    }

    public void setSettingsVisibility(boolean z) {
        if (z) {
            this.settingsButton.setVisibility(0);
        } else {
            this.settingsButton.setVisibility(4);
        }
    }

    public void setContactVisibility(boolean z) {
        if (z) {
            this.contactButton.setVisibility(0);
        } else {
            this.contactButton.setVisibility(8);
        }
    }
}
