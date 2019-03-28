package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;
import io.fabric.sdk.android.services.common.AbstractSpiCall;

final class AI01320xDecoder extends AI013x0xDecoder {
    protected int checkWeight(int i) {
        return i < AbstractSpiCall.DEFAULT_TIMEOUT ? i : i - AbstractSpiCall.DEFAULT_TIMEOUT;
    }

    AI01320xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    protected void addWeightCode(StringBuilder stringBuilder, int i) {
        if (i < AbstractSpiCall.DEFAULT_TIMEOUT) {
            stringBuilder.append("(3202)");
        } else {
            stringBuilder.append("(3203)");
        }
    }
}
