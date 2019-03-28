package com.mevo.app.presentation.dialogs;

import android.view.View;
import com.mevo.app.presentation.dialogs.InfoDialog.OnClickListener;

final /* synthetic */ class PermissionsDialogs$$Lambda$1 implements OnClickListener {
    static final OnClickListener $instance = new PermissionsDialogs$$Lambda$1();

    private PermissionsDialogs$$Lambda$1() {
    }

    public void onClick(View view) {
        PermissionsDialogs.goToAppSettings();
    }
}
