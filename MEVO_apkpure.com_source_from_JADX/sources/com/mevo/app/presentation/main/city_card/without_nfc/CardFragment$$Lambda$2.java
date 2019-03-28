package com.mevo.app.presentation.main.city_card.without_nfc;

import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;

final /* synthetic */ class CardFragment$$Lambda$2 implements SimpleResponseListener {
    private final CardFragment arg$1;

    CardFragment$$Lambda$2(CardFragment cardFragment) {
        this.arg$1 = cardFragment;
    }

    public void onResponse(boolean z, Exception exception) {
        this.arg$1.lambda$onCardRemove$184$CardFragment(z, exception);
    }
}
