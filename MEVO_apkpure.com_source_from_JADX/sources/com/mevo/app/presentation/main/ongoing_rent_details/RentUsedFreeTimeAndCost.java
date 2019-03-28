package com.mevo.app.presentation.main.ongoing_rent_details;

public class RentUsedFreeTimeAndCost {
    public final double costCents;
    public final long usedTariffSeconds;

    public RentUsedFreeTimeAndCost(long j, double d) {
        this.usedTariffSeconds = j;
        this.costCents = d;
    }
}
