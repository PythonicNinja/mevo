package com.mevo.app.presentation.dialogs;

import android.view.View;
import com.mevo.app.presentation.dialogs.InfoDialog.OnClickListener;

final /* synthetic */ class PermissionsDialogs$$Lambda$3 implements OnClickListener {
    static final OnClickListener $instance = new PermissionsDialogs$$Lambda$3();

    private PermissionsDialogs$$Lambda$3() {
    }

    public void onClick(View view) {
        PermissionsDialogs.goToLocalizationSettings();
    }
}
