package com.mevo.app.data.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mevo.app.data.model.Voucher.NewestDateComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.simpleframework.xml.ElementList;

public class AccountHistory {
    @ElementList(entry = "booking", inline = true, required = false)
    private List<BookingItem> bookingItems;
    @ElementList(entry = "payment", inline = true, required = false)
    private List<PaymentItem> paymentItems;
    @ElementList(entry = "rental", inline = true, required = false)
    private List<RentalItem> rentalItems;
    @ElementList(entry = "transaction", inline = true, required = false)
    private List<Transaction> transactionHistoryItems;
    @ElementList(entry = "voucher", inline = true, required = false)
    private List<Voucher> voucherHistoryItems;

    @NonNull
    public List<RentalItem> getRentalItems() {
        return this.rentalItems != null ? this.rentalItems : new ArrayList();
    }

    @NonNull
    public List<PaymentItem> getPaymentItems() {
        return this.paymentItems != null ? this.paymentItems : new ArrayList();
    }

    @NonNull
    public List<Transaction> getTransactionHistoryItems() {
        return this.transactionHistoryItems != null ? this.transactionHistoryItems : new ArrayList();
    }

    @NonNull
    public List<Voucher> getVoucherHistoryItems() {
        return this.voucherHistoryItems != null ? this.voucherHistoryItems : new ArrayList();
    }

    @NonNull
    public List<BookingItem> getBookingItems() {
        return this.bookingItems != null ? this.bookingItems : new ArrayList();
    }

    @Nullable
    public Voucher getMostRecentVoucher() {
        if (this.voucherHistoryItems != null) {
            if (!this.voucherHistoryItems.isEmpty()) {
                Collections.sort(this.voucherHistoryItems, new NewestDateComparator());
                return (Voucher) this.voucherHistoryItems.get(0);
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (getClass() == obj.getClass()) {
                AccountHistory accountHistory = (AccountHistory) obj;
                if (!Objects.equals(this.rentalItems, accountHistory.rentalItems) || !Objects.equals(this.paymentItems, accountHistory.paymentItems) || !Objects.equals(this.transactionHistoryItems, accountHistory.transactionHistoryItems) || Objects.equals(this.voucherHistoryItems, accountHistory.voucherHistoryItems) == null) {
                    z = false;
                }
                return z;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.rentalItems, this.paymentItems, this.transactionHistoryItems, this.voucherHistoryItems});
    }
}
