package com.mevo.app.data.model;

import android.content.Context;
import java.util.Comparator;
import java.util.Objects;
import org.simpleframework.xml.Attribute;

public class Voucher implements TransactionInfo {
    @Attribute(name = "amount", required = false)
    public int amount;
    @Attribute(name = "code")
    public String code;
    @Attribute(name = "description", required = false)
    public String description;
    @Attribute(name = "text", required = false)
    public String infoText;
    @Attribute(name = "date")
    public long purchaseDateTimestampSeconds;

    public static class NewestDateComparator implements Comparator<Voucher> {
        public int compare(Voucher voucher, Voucher voucher2) {
            long j = voucher.purchaseDateTimestampSeconds - voucher2.purchaseDateTimestampSeconds;
            if (j > 0) {
                return -1;
            }
            return j < 0 ? 1 : null;
        }
    }

    public int getServiceFee() {
        return 0;
    }

    public String getServiceFeeInfo(Context context) {
        return null;
    }

    public long getUsedTariffSeconds() {
        return 0;
    }

    public int getPrice() {
        return this.amount;
    }

    public long getPaymentDate() {
        return this.purchaseDateTimestampSeconds;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (getClass() == obj.getClass()) {
                Voucher voucher = (Voucher) obj;
                if (this.purchaseDateTimestampSeconds != voucher.purchaseDateTimestampSeconds || getPrice() != voucher.getPrice() || Objects.equals(this.code, voucher.code) == null) {
                    z = false;
                }
                return z;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.purchaseDateTimestampSeconds), Integer.valueOf(getPrice()), this.code});
    }
}
