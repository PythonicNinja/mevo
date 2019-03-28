package com.mevo.app.presentation.main.statitons_map.bottom_sheets;

import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;
import com.mevo.app.presentation.dialogs.ResumeDrivingDialog;
import com.mevo.app.presentation.main.statitons_map.bottom_sheets.CurrentRentalsDialogView.C08292;

final /* synthetic */ class CurrentRentalsDialogView$2$$Lambda$0 implements SimpleResponseListener {
    private final C08292 arg$1;
    private final ResumeDrivingDialog arg$2;
    private final String arg$3;

    CurrentRentalsDialogView$2$$Lambda$0(C08292 c08292, ResumeDrivingDialog resumeDrivingDialog, String str) {
        this.arg$1 = c08292;
        this.arg$2 = resumeDrivingDialog;
        this.arg$3 = str;
    }

    public void onResponse(boolean z, Exception exception) {
        this.arg$1.lambda$onResumeButtonClick$259$CurrentRentalsDialogView$2(this.arg$2, this.arg$3, z, exception);
    }
}
