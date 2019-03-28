package com.mevo.app.presentation.main.subscriptions;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.annimon.stream.Stream;
import com.mevo.app.C0434R;
import com.mevo.app.constants.Tariffs;
import com.mevo.app.data.model.Tariff;
import com.mevo.app.data.model.TariffExtraInfo;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.formatters.CurrencyFormatter;
import com.mevo.app.tools.formatters.Formatter;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionsAdapter extends Adapter<SubscriptionViewHolder> {
    private Drawable bgClickableSubscription;
    private int bgUnclickableSubscription;
    private String currency;
    private List<Tariff> data;
    private SubscriptionClickListener listener;

    public interface SubscriptionClickListener {
        void onSubscriptionClick(Tariff tariff);
    }

    class SubscriptionViewHolder extends ViewHolder {
        View container;
        ViewGroup extendedInfoContainer;
        TextView maxBikeRentedSimultaneously;
        TextView packageMinutesPerDay;
        TextView price;
        TextView priceAfterLimit;
        TextView subscriptionName;

        SubscriptionViewHolder(View view) {
            super(view);
            this.container = view.findViewById(C0434R.id.item_subscription_root);
            this.subscriptionName = (TextView) view.findViewById(C0434R.id.item_subscription_name);
            this.price = (TextView) view.findViewById(C0434R.id.item_subscription_price);
            this.packageMinutesPerDay = (TextView) view.findViewById(C0434R.id.item_subscription_minutes_per_day);
            this.priceAfterLimit = (TextView) view.findViewById(C0434R.id.item_subscription_price_after_limit);
            this.maxBikeRentedSimultaneously = (TextView) view.findViewById(C0434R.id.item_subscription_max_bikes_rented);
            this.extendedInfoContainer = (ViewGroup) view.findViewById(C0434R.id.item_subscription_extended_info_container);
        }

        void bindData(Tariff tariff) {
            Context context = this.subscriptionName.getContext();
            String formatMoney = Formatter.formatMoney(tariff.price / 100.0d);
            if (SubscriptionsAdapter.this.currency != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(formatMoney);
                stringBuilder.append(" ");
                stringBuilder.append(CurrencyFormatter.formatCurrencyAbbreviation(SubscriptionsAdapter.this.currency));
                formatMoney = stringBuilder.toString();
            }
            this.price.setText(context.getString(C0434R.string.item_subscription_price, new Object[]{formatMoney}));
            if (TextUtils.isEmpty(tariff.code)) {
                this.container.setBackgroundColor(SubscriptionsAdapter.this.bgUnclickableSubscription);
            } else {
                this.container.setBackground(SubscriptionsAdapter.this.bgClickableSubscription);
            }
            this.container.setOnClickListener(new SubscriptionsAdapter$SubscriptionViewHolder$$Lambda$0(this, tariff));
            TariffExtraInfo tariffExtraInfo = (TariffExtraInfo) Stream.of(Tariffs.ALL_SUBSCRIPTIONS).filter(new SubscriptionsAdapter$SubscriptionViewHolder$$Lambda$1(tariff)).findFirst().orElse(null);
            if (tariffExtraInfo == null) {
                this.extendedInfoContainer.setVisibility(8);
                return;
            }
            this.extendedInfoContainer.setVisibility(0);
            this.subscriptionName.setText(tariffExtraInfo.getNameRes());
            this.packageMinutesPerDay.setText(context.getString(C0434R.string.item_subscription_package_minutes_per_day, new Object[]{Integer.valueOf(tariffExtraInfo.getMinutesPerDay())}));
            formatMoney = Formatter.formatMoney(((double) tariffExtraInfo.getCentsPerMinuteAfterDailyLimit()) / 100.0d);
            if (SubscriptionsAdapter.this.currency != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(formatMoney);
                stringBuilder.append(" ");
                stringBuilder.append(CurrencyFormatter.formatCurrencyAbbreviation(SubscriptionsAdapter.this.currency));
                formatMoney = stringBuilder.toString();
            }
            this.priceAfterLimit.setText(context.getString(C0434R.string.item_subscription_price_per_minute_after_daily_limit, new Object[]{formatMoney}));
            this.maxBikeRentedSimultaneously.setText(context.getString(C0434R.string.item_subscription_max_bikes_rented_simultaneously, new Object[]{Integer.valueOf(tariffExtraInfo.getMaxBikesRentedSimultaneously())}));
        }

        final /* synthetic */ void lambda$bindData$272$SubscriptionsAdapter$SubscriptionViewHolder(Tariff tariff, View view) {
            if (SubscriptionsAdapter.this.listener != null) {
                SubscriptionsAdapter.this.listener.onSubscriptionClick(tariff);
            }
        }
    }

    public SubscriptionsAdapter(List<Tariff> list, SubscriptionClickListener subscriptionClickListener, Context context) {
        if (list == null) {
            list = new ArrayList();
        }
        this.data = list;
        this.listener = subscriptionClickListener;
        this.bgClickableSubscription = ResourcesCompat.getDrawable(context.getResources(), C0434R.drawable.button_bg_dark, null);
        this.bgUnclickableSubscription = ResourcesCompat.getColor(context.getResources(), C0434R.color.bgInactiveSubscription, null);
        this.currency = UserManager.getUser().getCurrency();
    }

    public SubscriptionsAdapter setListener(SubscriptionClickListener subscriptionClickListener) {
        this.listener = subscriptionClickListener;
        return this;
    }

    public SubscriptionsAdapter setData(List<Tariff> list) {
        if (list == null) {
            list = new ArrayList();
        }
        this.data = list;
        notifyDataSetChanged();
        return this;
    }

    @NonNull
    public SubscriptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SubscriptionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0434R.layout.item_subscription, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull SubscriptionViewHolder subscriptionViewHolder, int i) {
        subscriptionViewHolder.bindData((Tariff) this.data.get(i));
    }

    public int getItemCount() {
        return this.data.size();
    }
}
