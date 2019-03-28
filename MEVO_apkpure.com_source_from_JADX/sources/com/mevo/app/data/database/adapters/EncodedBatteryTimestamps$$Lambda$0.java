package com.mevo.app.data.database.adapters;

import com.annimon.stream.function.ToLongFunction;

final /* synthetic */ class EncodedBatteryTimestamps$$Lambda$0 implements ToLongFunction {
    static final ToLongFunction $instance = new EncodedBatteryTimestamps$$Lambda$0();

    private EncodedBatteryTimestamps$$Lambda$0() {
    }

    public long applyAsLong(Object obj) {
        return Long.parseLong((String) obj);
    }
}
