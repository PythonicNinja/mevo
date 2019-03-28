package com.mevo.app.presentation.main.transactions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.annimon.stream.Stream;
import com.inverce.mod.core.IM;
import com.inverce.mod.integrations.support.recycler.RecyclerAdapter;
import com.mevo.app.C0434R;
import com.mevo.app.constants.Tariffs;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.model.PaymentItem;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.Transaction;
import com.mevo.app.data.model.TransactionInfo;
import com.mevo.app.data.model.Voucher;
import com.mevo.app.data.model.repository.TariffTimeRange;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.formatters.CurrencyFormatter;
import com.mevo.app.tools.formatters.Formatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionsAdapter extends RecyclerAdapter<TransactionInfo, TransactionViewHolder> {
    private List<TariffTimeRange> subscriptionTimeRanges;

    static class TransactionViewHolder extends ViewHolder {
        private TextView costTxt;
        private TextView dateTxt;
        private TextView rentalDurationExceededInfo;
        private TextView serviceFee;
        private ConstraintLayout serviceFeeContainer;
        private ImageView serviceFeeInfoBtn;
        private TextView transactionTypeTxt;
        private TextView usedSubscriptionMinutes;

        TransactionViewHolder(View view) {
            super(view);
            this.dateTxt = (TextView) view.findViewById(C0434R.id.item_transaction_date);
            this.transactionTypeTxt = (TextView) view.findViewById(C0434R.id.item_transaction_type);
            this.costTxt = (TextView) view.findViewById(C0434R.id.item_transaction_amount);
            this.usedSubscriptionMinutes = (TextView) view.findViewById(C0434R.id.item_transaction_used_subscription_seconds);
            this.rentalDurationExceededInfo = (TextView) view.findViewById(C0434R.id.item_transaction_rental_duration_exceeded_info);
            this.serviceFeeInfoBtn = (ImageView) view.findViewById(C0434R.id.item_transaction_service_fee_info_btn);
            this.serviceFeeContainer = (ConstraintLayout) view.findViewById(C0434R.id.item_transaction_service_fee_container);
            this.serviceFee = (TextView) view.findViewById(C0434R.id.item_transaction_service_fee);
        }
    }

    public TransactionsAdapter setSubscriptionTimeRanges(List<TariffTimeRange> list) {
        if (list == null) {
            list = new ArrayList();
        }
        this.subscriptionTimeRanges = list;
        return this;
    }

    @NonNull
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TransactionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0434R.layout.item_transaction, viewGroup, false));
    }

    public void onBindViewHolder(TransactionViewHolder transactionViewHolder, TransactionInfo transactionInfo, int i) {
        i = transactionViewHolder.dateTxt.getContext();
        transactionViewHolder.dateTxt.setText(formatPaymentDate(transactionInfo));
        CharSequence paymentType = getPaymentType(transactionInfo, i);
        if (TextUtils.isEmpty(paymentType)) {
            transactionViewHolder.transactionTypeTxt.setVisibility(8);
        } else {
            transactionViewHolder.transactionTypeTxt.setText(paymentType);
            transactionViewHolder.transactionTypeTxt.setVisibility(0);
        }
        transactionViewHolder.costTxt.setText(formatCost(transactionInfo, i));
        transactionViewHolder.costTxt.setVisibility(shouldCostBeVisible(transactionInfo) ? 0 : 8);
        showRentalTimeExceeded12hInfoIfShould(transactionInfo, transactionViewHolder, i);
        transactionViewHolder.usedSubscriptionMinutes.setVisibility(shouldUsedSubscriptionMinutesBeVisible(transactionInfo) ? 0 : 8);
        transactionViewHolder.usedSubscriptionMinutes.setText(i.getString(C0434R.string.item_transaction_used_subscription_seconds, new Object[]{Long.toString(transactionInfo.getUsedTariffSeconds() / 60)}));
        if (transactionInfo.getServiceFee() != 0) {
            transactionViewHolder.serviceFee.setText(formatServiceFee(transactionInfo, i));
            transactionViewHolder.serviceFeeContainer.setVisibility(0);
            if (TextUtils.isEmpty(transactionInfo.getServiceFeeInfo(i))) {
                transactionViewHolder.serviceFeeInfoBtn.setVisibility(8);
                transactionViewHolder.serviceFeeInfoBtn.setOnClickListener(null);
                return;
            }
            transactionViewHolder.serviceFeeInfoBtn.setVisibility(0);
            transactionViewHolder.serviceFeeInfoBtn.setOnClickListener(new TransactionsAdapter$$Lambda$0(transactionInfo, i));
            return;
        }
        transactionViewHolder.serviceFeeContainer.setVisibility(8);
    }

    private boolean shouldCostBeVisible(TransactionInfo transactionInfo) {
        boolean z = true;
        if (!(transactionInfo instanceof BookingItem)) {
            return true;
        }
        if (transactionInfo.getPrice() <= 0) {
            if (transactionInfo.getUsedTariffSeconds() > 0) {
                z = false;
            }
        }
        return z;
    }

    private boolean shouldUsedSubscriptionMinutesBeVisible(TransactionInfo transactionInfo) {
        boolean z = false;
        if (!(transactionInfo instanceof BookingItem)) {
            return false;
        }
        if (transactionInfo.getUsedTariffSeconds() > 0) {
            z = true;
        }
        return z;
    }

    private String formatPaymentDate(TransactionInfo transactionInfo) {
        if (transactionInfo instanceof RentalItem) {
            RentalItem rentalItem = (RentalItem) transactionInfo;
            return Formatter.formatDurationStartEndDates(rentalItem.startTimestampSeconds * 1000, rentalItem.endTimestampSeconds * 1000);
        } else if (!(transactionInfo instanceof BookingItem)) {
            return Formatter.formatDateAndTime(transactionInfo.getPaymentDate() * 1000);
        } else {
            BookingItem bookingItem = (BookingItem) transactionInfo;
            return Formatter.formatDurationStartEndDates(bookingItem.getStartTimestampSeconds() * 1000, bookingItem.getEndTimestampSeconds() * 1000);
        }
    }

    private void showRentalTimeExceeded12hInfoIfShould(TransactionInfo transactionInfo, TransactionViewHolder transactionViewHolder, Context context) {
        if (transactionInfo instanceof RentalItem) {
            int additionalCostAfter12hRentCents;
            RentalItem rentalItem = (RentalItem) transactionInfo;
            double d = ((double) (rentalItem.endTimestampSeconds - rentalItem.startTimestampSeconds)) / 3600.0d;
            TariffTimeRange tariffTimeRange = null;
            if (this.subscriptionTimeRanges != null) {
                tariffTimeRange = (TariffTimeRange) Stream.of(this.subscriptionTimeRanges).filter(new TransactionsAdapter$$Lambda$1(rentalItem)).findFirst().orElse(null);
            }
            if (tariffTimeRange != null) {
                additionalCostAfter12hRentCents = tariffTimeRange.tariffExtraInfo.getAdditionalCostAfter12hRentCents();
            } else {
                additionalCostAfter12hRentCents = Tariffs.Tariff_PAY_AS_YOU_GO.getAdditionalCostAfter12hRentCents();
            }
            if (d > 12.0d) {
                if (((double) rentalItem.price) / 100.0d >= ((double) additionalCostAfter12hRentCents)) {
                    transactionViewHolder.rentalDurationExceededInfo.setText(context.getString(C0434R.string.transaction_info_rental_aboce_12h, new Object[]{Formatter.formatMoneyFromCents((double) ((int) (r6 * 100.0d)))}));
                    transactionViewHolder.rentalDurationExceededInfo.setVisibility(0);
                    return;
                }
            }
            transactionViewHolder.rentalDurationExceededInfo.setVisibility(8);
            return;
        }
        transactionViewHolder.rentalDurationExceededInfo.setVisibility(8);
    }

    /* renamed from: lambda$showRentalTimeExceeded12hInfoIfShould$281$TransactionsAdapter */
    static final /* synthetic */ boolean m145x9d0dcb64(RentalItem rentalItem, TariffTimeRange tariffTimeRange) {
        return (tariffTimeRange.startTimestampSeconds >= rentalItem.endTimestampSeconds || tariffTimeRange.endTimestampSeconds >= rentalItem.endTimestampSeconds) ? null : true;
    }

    private static String formatCost(TransactionInfo transactionInfo, Context context) {
        int price = transactionInfo.getPrice();
        if ((transactionInfo instanceof Voucher) != null) {
            price = Math.abs(price);
        }
        transactionInfo = Formatter.formatMoneyFromCents((double) price);
        Object formatCurrencyAbbreviation = CurrencyFormatter.formatCurrencyAbbreviation(UserManager.getUser().getCurrency());
        if (!TextUtils.isEmpty(formatCurrencyAbbreviation)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(transactionInfo);
            stringBuilder.append(" ");
            stringBuilder.append(formatCurrencyAbbreviation);
            transactionInfo = stringBuilder.toString();
        }
        return context.getString(C0434R.string.item_transaction_price, new Object[]{transactionInfo});
    }

    private static String formatServiceFee(TransactionInfo transactionInfo, Context context) {
        Object formatMoneyFromCents = Formatter.formatMoneyFromCents((double) Math.abs(transactionInfo.getServiceFee()));
        String formatCurrencyAbbreviation = CurrencyFormatter.formatCurrencyAbbreviation(UserManager.getUser().getCurrency());
        if (!TextUtils.isEmpty(formatMoneyFromCents)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(formatMoneyFromCents);
            stringBuilder.append(" ");
            stringBuilder.append(formatCurrencyAbbreviation);
            formatMoneyFromCents = stringBuilder.toString();
        }
        if ((transactionInfo instanceof RentalItem) && Math.abs(transactionInfo.getServiceFee()) == 200) {
            return context.getString(C0434R.string.rent_official_station_premium_return, new Object[]{formatMoneyFromCents});
        }
        return context.getString(C0434R.string.item_transaction_service_fee, new Object[]{formatMoneyFromCents});
    }

    private String getPaymentType(TransactionInfo transactionInfo, Context context) {
        if (!(transactionInfo instanceof PaymentItem)) {
            if (!(transactionInfo instanceof Transaction)) {
                if (transactionInfo instanceof RentalItem) {
                    RentalItem rentalItem = (RentalItem) transactionInfo;
                    return IM.resources().getString(C0434R.string.item_transaction_transaction_type_rental, new Object[]{rentalItem.bikeNumber});
                } else if (transactionInfo instanceof Voucher) {
                    return context.getString(C0434R.string.item_transaction_transaction_type_voucher);
                } else {
                    if ((transactionInfo instanceof BookingItem) != null) {
                        return context.getString(C0434R.string.item_transaction_transaction_type_booking);
                    }
                    return null;
                }
            }
        }
        return context.getString(C0434R.string.item_transaction_transaction_type_topup);
    }
}
