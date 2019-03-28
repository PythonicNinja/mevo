package com.mevo.app.presentation.main.top_up;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class TopUpFragment$$Lambda$0 implements ResponseListener {
    private final TopUpFragment arg$1;

    TopUpFragment$$Lambda$0(TopUpFragment topUpFragment) {
        this.arg$1 = topUpFragment;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$fetchFormHtml$279$TopUpFragment((String) obj, z, exception);
    }
}
