package com.mevo.app.constants;

import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.Arrays;
import java.util.List;

public class TopUp {
    public static final String CUSTOM_AMOUNT_URL = "https://custom_amount_url.pl";
    public static final String CUSTOM_VOUCHER_URL = "https://custom_voucher_url.pl";
    public static final List<Integer> TOP_UP_AMOUNTS = Arrays.asList(new Integer[]{Integer.valueOf(1000), Integer.valueOf(2000), Integer.valueOf(4000), Integer.valueOf(AbstractSpiCall.DEFAULT_TIMEOUT), Integer.valueOf(14000)});
}
