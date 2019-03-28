package com.mevo.app.presentation.main.city_card.without_nfc;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class CityCardAddFragment$$Lambda$5 implements ResponseListener {
    private final CityCardAddFragment arg$1;

    CityCardAddFragment$$Lambda$5(CityCardAddFragment cityCardAddFragment) {
        this.arg$1 = cityCardAddFragment;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$null$188$CityCardAddFragment((String) obj, z, exception);
    }
}
