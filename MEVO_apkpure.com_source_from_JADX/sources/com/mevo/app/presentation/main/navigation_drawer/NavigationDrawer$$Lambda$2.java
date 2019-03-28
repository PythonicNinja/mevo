package com.mevo.app.presentation.main.navigation_drawer;

import com.mevo.app.data.model.repository.ResponseActiveTariff;
import com.mevo.app.data.model.repository.ResponseCurrentTariffUsedSeconds;
import com.mevo.app.data.model.response.ResponseUserDetails;

final /* synthetic */ class NavigationDrawer$$Lambda$2 implements Runnable {
    private final NavigationDrawer arg$1;
    private final ResponseCurrentTariffUsedSeconds arg$2;
    private final ResponseActiveTariff arg$3;
    private final String arg$4;
    private final ResponseUserDetails arg$5;

    NavigationDrawer$$Lambda$2(NavigationDrawer navigationDrawer, ResponseCurrentTariffUsedSeconds responseCurrentTariffUsedSeconds, ResponseActiveTariff responseActiveTariff, String str, ResponseUserDetails responseUserDetails) {
        this.arg$1 = navigationDrawer;
        this.arg$2 = responseCurrentTariffUsedSeconds;
        this.arg$3 = responseActiveTariff;
        this.arg$4 = str;
        this.arg$5 = responseUserDetails;
    }

    public void run() {
        this.arg$1.lambda$null$198$NavigationDrawer(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
