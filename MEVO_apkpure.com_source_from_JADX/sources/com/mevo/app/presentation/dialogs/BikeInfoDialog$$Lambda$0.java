package com.mevo.app.presentation.dialogs;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BikeInfoDialog$$Lambda$0 implements OnClickListener {
    private final BikeInfoDialog arg$1;
    private final BikeInfoDialog.OnClickListener arg$2;

    BikeInfoDialog$$Lambda$0(BikeInfoDialog bikeInfoDialog, BikeInfoDialog.OnClickListener onClickListener) {
        this.arg$1 = bikeInfoDialog;
        this.arg$2 = onClickListener;
    }

    public void onClick(View view) {
        this.arg$1.lambda$setActionButton$128$BikeInfoDialog(this.arg$2, view);
    }
}
