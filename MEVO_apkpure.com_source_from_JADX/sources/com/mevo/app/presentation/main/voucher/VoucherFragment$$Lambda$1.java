package com.mevo.app.presentation.main.voucher;

import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;

final /* synthetic */ class VoucherFragment$$Lambda$1 implements SimpleResponseListener {
    private final VoucherFragment arg$1;

    VoucherFragment$$Lambda$1(VoucherFragment voucherFragment) {
        this.arg$1 = voucherFragment;
    }

    public void onResponse(boolean z, Exception exception) {
        this.arg$1.lambda$getVoucher$304$VoucherFragment(z, exception);
    }
}
