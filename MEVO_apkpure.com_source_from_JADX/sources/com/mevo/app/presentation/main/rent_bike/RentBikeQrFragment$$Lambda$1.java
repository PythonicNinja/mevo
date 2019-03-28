package com.mevo.app.presentation.main.rent_bike;

import android.view.View;
import android.view.View.OnClickListener;
import com.mevo.app.tools.Tools;

final /* synthetic */ class RentBikeQrFragment$$Lambda$1 implements OnClickListener {
    static final OnClickListener $instance = new RentBikeQrFragment$$Lambda$1();

    private RentBikeQrFragment$$Lambda$1() {
    }

    public void onClick(View view) {
        Tools.goToApplicationSystemSettings();
    }
}
