package com.mevo.app.presentation.dialogs;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class InfoDialog$$Lambda$0 implements OnClickListener {
    private final InfoDialog arg$1;
    private final InfoDialog.OnClickListener arg$2;

    InfoDialog$$Lambda$0(InfoDialog infoDialog, InfoDialog.OnClickListener onClickListener) {
        this.arg$1 = infoDialog;
        this.arg$2 = onClickListener;
    }

    public void onClick(View view) {
        this.arg$1.lambda$setActionButton$134$InfoDialog(this.arg$2, view);
    }
}
