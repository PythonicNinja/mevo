package com.mevo.app.presentation.main.city_card;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class CityCardAddScan$$Lambda$0 implements ResponseListener {
    private final CityCardAddScan arg$1;

    CityCardAddScan$$Lambda$0(CityCardAddScan cityCardAddScan) {
        this.arg$1 = cityCardAddScan;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$hasCardAdded$179$CityCardAddScan((String) obj, z, exception);
    }
}
