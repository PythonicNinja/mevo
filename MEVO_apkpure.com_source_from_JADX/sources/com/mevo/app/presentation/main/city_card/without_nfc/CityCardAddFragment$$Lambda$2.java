package com.mevo.app.presentation.main.city_card.without_nfc;

import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;

final /* synthetic */ class CityCardAddFragment$$Lambda$2 implements SimpleResponseListener {
    private final CityCardAddFragment arg$1;
    private final String arg$2;

    CityCardAddFragment$$Lambda$2(CityCardAddFragment cityCardAddFragment, String str) {
        this.arg$1 = cityCardAddFragment;
        this.arg$2 = str;
    }

    public void onResponse(boolean z, Exception exception) {
        this.arg$1.lambda$onAddCardButton$187$CityCardAddFragment(this.arg$2, z, exception);
    }
}
