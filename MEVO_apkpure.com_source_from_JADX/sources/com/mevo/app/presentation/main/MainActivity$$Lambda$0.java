package com.mevo.app.presentation.main;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class MainActivity$$Lambda$0 implements ResponseListener {
    private final MainActivity arg$1;

    MainActivity$$Lambda$0(MainActivity mainActivity) {
        this.arg$1 = mainActivity;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$navigateToCityCard$197$MainActivity((String) obj, z, exception);
    }
}
