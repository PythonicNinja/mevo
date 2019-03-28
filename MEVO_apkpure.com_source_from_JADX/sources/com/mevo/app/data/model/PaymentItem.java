package com.mevo.app.data.model;

import android.content.Context;
import java.util.Objects;
import org.simpleframework.xml.Attribute;

public class PaymentItem implements TransactionInfo {
    @Attribute(name = "amount")
    public int amount;
    @Attribute(name = "date")
    public long date;
    @Attribute(name = "text")
    public String infoText;

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
        return this.date;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (getClass() == obj.getClass()) {
                PaymentItem paymentItem = (PaymentItem) obj;
                if (this.date != paymentItem.date || getPrice() != paymentItem.getPrice()) {
                    z = false;
                }
                return z;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.date), Integer.valueOf(getPrice())});
    }
}
