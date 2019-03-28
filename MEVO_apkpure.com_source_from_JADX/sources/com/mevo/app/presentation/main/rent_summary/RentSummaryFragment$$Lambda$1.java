package com.mevo.app.presentation.main.rent_summary;

import com.mevo.app.data.repository.DirectionsRepository.ResponseListener;
import java.util.List;

final /* synthetic */ class RentSummaryFragment$$Lambda$1 implements ResponseListener {
    private final RentSummaryFragment arg$1;
    private final boolean arg$2;

    RentSummaryFragment$$Lambda$1(RentSummaryFragment rentSummaryFragment, boolean z) {
        this.arg$1 = rentSummaryFragment;
        this.arg$2 = z;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$null$241$RentSummaryFragment(this.arg$2, (List) obj, z, exception);
    }
}
