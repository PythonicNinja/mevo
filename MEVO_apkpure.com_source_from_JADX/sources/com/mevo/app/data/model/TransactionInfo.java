package com.mevo.app.data.model;

import android.content.Context;

public interface TransactionInfo {
    long getPaymentDate();

    int getPrice();

    int getServiceFee();

    String getServiceFeeInfo(Context context);

    long getUsedTariffSeconds();
}
