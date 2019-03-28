package com.mevo.app.presentation.main.subscriptions;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.mevo.app.C0434R;
import com.mevo.app.data.exceptions.AlreadyHasSubscriptionException;
import com.mevo.app.data.exceptions.CantPurchaseSubscriptionWhenHasActiveRentalOrBookingException;
import com.mevo.app.data.exceptions.InsufficientFundsException;
import com.mevo.app.data.model.Tariff;
import com.mevo.app.data.model.event.EventDataSubscriptionPurchased;
import com.mevo.app.data.model.repository.ResponseActiveTariff;
import com.mevo.app.data.repository.NextbikeApiRepository;
import com.mevo.app.data.repository.TariffRepository;
import com.mevo.app.presentation.dialogs.InfoDialog.Builder;
import com.mevo.app.presentation.main.MainActivityInterface;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.presentation.main.subscriptions.SubscriptionsAdapter.SubscriptionClickListener;
import com.mevo.app.presentation.main.top_up.TopUpFragment;
import com.mevo.app.tools.CustomTypefaceSpan;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.Utils.SpanUtil;
import com.mevo.app.tools.formatters.CurrencyFormatter;
import com.mevo.app.tools.formatters.Formatter;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class SubscriptionsFragment extends MainFragment implements SubscriptionClickListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "SubscriptionsFragment";
    private SubscriptionsAdapter adapter;
    private View dataContainer;
    private boolean hasActiveSubscription;
    private View noDataInfo;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public static SubscriptionsFragment newInstance() {
        return new SubscriptionsFragment();
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_subscriptions, viewGroup, false);
        findViews(layoutInflater);
        setupRecycler();
        fetchCurrentTariff();
        return layoutInflater;
    }

    private void findViews(View view) {
        this.recyclerView = (RecyclerView) view.findViewById(C0434R.id.fragment_subscriptions_recycler);
        this.progressBar = (ProgressBar) view.findViewById(C0434R.id.fragment_subscriptions_progress);
        this.noDataInfo = view.findViewById(C0434R.id.fragment_subscriptions_no_data_info);
        this.dataContainer = view.findViewById(C0434R.id.fragment_subscriptions_data_container);
    }

    private void setupRecycler() {
        this.adapter = new SubscriptionsAdapter(null, this, getContext());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setAdapter(this.adapter);
    }

    private void fetchCurrentTariff() {
        this.progressBar.setVisibility(0);
        new TariffRepository().getCurrentTariff(new SubscriptionsFragment$$Lambda$0(this));
    }

    final /* synthetic */ void lambda$fetchCurrentTariff$274$SubscriptionsFragment(ResponseActiveTariff responseActiveTariff, boolean z, Exception exception) {
        if (z) {
            this.hasActiveSubscription = responseActiveTariff.currentActiveTariff != null ? true : null;
            fetchAvailableTariffs();
            return;
        }
        this.progressBar.setVisibility(true);
        onDataReady(null);
    }

    private void fetchAvailableTariffs() {
        this.progressBar.setVisibility(0);
        new NextbikeApiRepository().getSubscriptions(getContext(), new SubscriptionsFragment$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$fetchAvailableTariffs$275$SubscriptionsFragment(List list, boolean z, Exception exception) {
        this.progressBar.setVisibility(8);
        if (z) {
            onDataReady(list);
        } else {
            onDataReady(null);
        }
    }

    private void onDataReady(List<Tariff> list) {
        if (list == null || list.isEmpty()) {
            this.dataContainer.setVisibility(8);
            this.noDataInfo.setVisibility(0);
            return;
        }
        this.adapter.setData(list);
        this.dataContainer.setVisibility(0);
        this.noDataInfo.setVisibility(8);
    }

    public void onSubscriptionClick(Tariff tariff) {
        Context context = getContext();
        MainActivityInterface activityInterface = getActivityInterface();
        if (!(context == null || activityInterface == null)) {
            if (!TextUtils.isEmpty(tariff.code)) {
                if (this.hasActiveSubscription) {
                    showHasActiveSubscriptionInfo();
                } else if (((double) UserManager.getUser().getCredits()) < tariff.price) {
                    showNotEnoughCreditsInfo();
                } else {
                    showPurchaseDialog(tariff);
                }
            }
        }
    }

    private void showPurchaseDialog(@NonNull Tariff tariff) {
        Context context = getContext();
        String currency = UserManager.getUser().getCurrency();
        String formatMoney = Formatter.formatMoney(tariff.price / 100.0d);
        if (!(currency == null || currency.isEmpty())) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(formatMoney);
            stringBuilder.append(" ");
            stringBuilder.append(CurrencyFormatter.formatCurrencyAbbreviation(currency));
            formatMoney = stringBuilder.toString();
        }
        Object[] objArr = new Object[]{tariff.tariffName, formatMoney};
        new Builder().setText(SpanUtil.setSpan(context.getString(C0434R.string.frag_subscriptions_confirm_subscription, objArr), formatMoney, new CustomTypefaceSpan("roboto_medium.ttf", Typeface.create(ResourcesCompat.getFont(context, C0434R.font.roboto_bold), 0)))).setPositiveButton((int) C0434R.string.frag_subscriptions_confirm_subscription_yes, new SubscriptionsFragment$$Lambda$2(this, tariff)).setNegativeButton((int) C0434R.string.cancel, null).build().show();
    }

    final /* synthetic */ void lambda$showPurchaseDialog$276$SubscriptionsFragment(@NonNull Tariff tariff, View view) {
        subscribe(tariff);
    }

    private void subscribe(Tariff tariff) {
        getActivityInterface().setProgressViewVisibility(true);
        new TariffRepository().purchaseTariff(tariff, new SubscriptionsFragment$$Lambda$3(this));
    }

    final /* synthetic */ void lambda$subscribe$277$SubscriptionsFragment(boolean z, Exception exception) {
        getActivityInterface().setProgressViewVisibility(false);
        if (z) {
            new Builder().setText((int) C0434R.string.frag_subscriptions_subscribe_success).setPositiveButton((int) C0434R.string.close, null).build().show();
            EventBus.getDefault().post(new EventDataSubscriptionPurchased());
        } else if (exception == null) {
            showPurchaseErrorInfo();
        } else if (exception instanceof AlreadyHasSubscriptionException) {
            showHasActiveSubscriptionInfo();
        } else if (exception instanceof InsufficientFundsException) {
            showNotEnoughCreditsInfo();
        } else if (exception instanceof CantPurchaseSubscriptionWhenHasActiveRentalOrBookingException) {
            showHasActiveRentalOrBooking();
        } else {
            showPurchaseErrorInfo();
        }
    }

    private void showHasActiveSubscriptionInfo() {
        new Builder().setText(getContext().getString(C0434R.string.frag_subscriptions_user_already_has_subscription)).setNeutralButton((int) C0434R.string.close, null).build().show();
    }

    private void showNotEnoughCreditsInfo() {
        new Builder().setText(getContext().getString(C0434R.string.frag_subscriptions_not_enough_funds)).setPositiveButton((int) C0434R.string.frag_subscriptions_not_enough_funds_topup, new SubscriptionsFragment$$Lambda$4(this)).setNegativeButton((int) C0434R.string.cancel, null).build().show();
    }

    final /* synthetic */ void lambda$showNotEnoughCreditsInfo$278$SubscriptionsFragment(View view) {
        this.activityInterface.changeFragment(TopUpFragment.newInstance());
    }

    private void showPurchaseErrorInfo() {
        new Builder().setText((int) C0434R.string.frag_subscriptions_subscribe_failure).setPositiveButton((int) C0434R.string.close, null).build().show();
    }

    private void showHasActiveRentalOrBooking() {
        new Builder().setText(getContext().getString(C0434R.string.frag_subscriptions_purchase_with_active_rental_or_booking)).setNeutralButton((int) C0434R.string.close, null).build().show();
    }
}
