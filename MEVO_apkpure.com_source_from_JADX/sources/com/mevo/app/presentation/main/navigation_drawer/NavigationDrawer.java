package com.mevo.app.presentation.main.navigation_drawer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.constants.Tariffs;
import com.mevo.app.data.model.TariffExtraInfo;
import com.mevo.app.data.model.User;
import com.mevo.app.data.model.UserDetails;
import com.mevo.app.data.model.event.EventDataSubscriptionPurchased;
import com.mevo.app.data.model.event.EventDataSubscriptionTimeChange;
import com.mevo.app.data.model.repository.ResponseActiveTariff;
import com.mevo.app.data.model.repository.ResponseCurrentTariffUsedSeconds;
import com.mevo.app.data.model.response.ResponseUserDetails;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.repository.TariffRepository;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.main.MainActivityInterface;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.RepeatTask;
import com.mevo.app.tools.Tools;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.formatters.Formatter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class NavigationDrawer extends LinearLayout implements OnItemClickListener, OnClickListener, DrawerListener {
    private static final long REFRESH_INTERAL_MS = 60000;
    private static final String TAG = "NavigationDrawer";
    private TextView accountActive;
    private TextView accountBalance;
    private TextView accountInactive;
    private LinearLayout accoutBalanceRefresh;
    private ImageView buttonRefresh;
    private boolean isRefreshing;
    private ItemClickListener itemClickListener;
    private MainActivityInterface mainActivityInterface;
    private NavigationDrawerAdapter menuAdapter;
    private ListView menuListView;
    private DrawerLayout parentDrawerLayout;
    private RepeatTask refreshRepeatTask;
    private TextView subscriptionEndDate;
    private TextView subscriptionText;
    private TextView subscriptionUsedMinutesText;
    private AppCompatButton topUpAcc;
    private TextView userName;

    public interface ItemClickListener {
        void closeNavigationDrawer();

        void onItemClicked(MenuItem menuItem);

        void toUpClicked();
    }

    public static class MenuItem {
        private static int lastId;
        @DrawableRes
        private int drawableRes;
        private int id = createNextId();
        @StringRes
        private int stringRes;

        public MenuItem(@StringRes int i, @DrawableRes int i2) {
            this.stringRes = i;
            this.drawableRes = i2;
        }

        public int getId() {
            return this.id;
        }

        @StringRes
        public int getStringRes() {
            return this.stringRes;
        }

        @DrawableRes
        public int getDrawableRes() {
            return this.drawableRes;
        }

        private int createNextId() {
            lastId++;
            return lastId;
        }
    }

    public void onDrawerSlide(@NonNull View view, float f) {
    }

    public void onDrawerStateChanged(int i) {
    }

    public NavigationDrawer(Context context) {
        super(context);
        init();
    }

    public NavigationDrawer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public NavigationDrawer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @TargetApi(21)
    public NavigationDrawer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        inflate(getContext(), C0434R.layout.view_navigation_drawer, this);
        this.isRefreshing = false;
        this.mainActivityInterface = IM.activity() instanceof MainActivityInterface ? (MainActivityInterface) IM.activity() : null;
        this.refreshRepeatTask = new RepeatTask(60000, true, new NavigationDrawer$$Lambda$0(this));
        findViews();
        prepareList();
        setListeners();
        setData(UserManager.getUserDetails(), null, 0, 0, 0);
        prefillSubscriptionData();
        refreshInfo();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
        findDrawerLayoutParentAndSetDrawerListener();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
        this.refreshRepeatTask.stop();
        if (this.parentDrawerLayout != null) {
            this.parentDrawerLayout.removeDrawerListener(this);
            this.parentDrawerLayout = null;
        }
    }

    private void findViews() {
        this.accountActive = (TextView) findViewById(C0434R.id.view_navigation_drawer_account_active);
        this.accountInactive = (TextView) findViewById(C0434R.id.view_navigation_drawer_account_inactive);
        this.menuListView = (ListView) findViewById(C0434R.id.navigation_drawer_menu_list);
        this.userName = (TextView) findViewById(C0434R.id.navigation_drawer_username);
        this.accountBalance = (TextView) findViewById(C0434R.id.navigation_drawer_account_balance);
        this.topUpAcc = (AppCompatButton) findViewById(C0434R.id.navigation_drawer_top_up_acc);
        this.accoutBalanceRefresh = (LinearLayout) findViewById(C0434R.id.navigation_drawer_refresh);
        this.buttonRefresh = (ImageView) findViewById(C0434R.id.button_refresh);
        this.subscriptionText = (TextView) findViewById(C0434R.id.view_navigation_drawer_current_subscription);
        this.subscriptionUsedMinutesText = (TextView) findViewById(C0434R.id.view_navigation_drawer_current_subscription_minutes);
        this.subscriptionEndDate = (TextView) findViewById(C0434R.id.view_navigation_drawer_current_subscription_end_date);
    }

    private void prepareList() {
        this.menuAdapter = new NavigationDrawerAdapter(new ArrayList());
        this.menuListView.setAdapter(this.menuAdapter);
    }

    private void setListeners() {
        this.menuListView.setOnItemClickListener(this);
        this.topUpAcc.setOnClickListener(this);
        this.accoutBalanceRefresh.setOnClickListener(this);
    }

    private void findDrawerLayoutParentAndSetDrawerListener() {
        ViewParent viewParent = this;
        while (viewParent != null && viewParent.getParent() != null && this.parentDrawerLayout == null) {
            if (viewParent.getParent() instanceof DrawerLayout) {
                this.parentDrawerLayout = (DrawerLayout) viewParent.getParent();
                break;
            }
            viewParent = viewParent.getParent();
        }
        if (this.parentDrawerLayout != null) {
            this.parentDrawerLayout.addDrawerListener(this);
        }
    }

    private void setData(@Nullable UserDetails userDetails, String str, long j, long j2, long j3) {
        NavigationDrawer navigationDrawer = this;
        if (userDetails != null) {
            navigationDrawer.userName.setText(IM.resources().getString(C0434R.string.drawer_name, new Object[]{Tools.ifStringIsNullOrEmpty(userDetails.getFirstname()), Tools.ifStringIsNullOrEmpty(userDetails.getLastname())}));
            double credits = (double) userDetails.getCredits();
            if (credits == 0.0d) {
                TextView textView = navigationDrawer.accountBalance;
                Resources resources = IM.resources();
                r15 = new Object[2];
                r15[0] = String.format(Locale.getDefault(), "%.2f", new Object[]{Double.valueOf(credits)}).replace(".", ",");
                r15[1] = IM.resources().getString(C0434R.string.pln);
                textView.setText(resources.getString(C0434R.string.drawer_balance, r15));
            } else {
                String formatMoney = Formatter.formatMoney(credits / 100.0d);
                navigationDrawer.accountBalance.setText(IM.resources().getString(C0434R.string.drawer_balance, new Object[]{formatMoney.replace(".", ","), IM.resources().getString(C0434R.string.pln)}));
            }
            if (userDetails.getIsActive() == 1) {
                navigationDrawer.accountActive.setVisibility(0);
                navigationDrawer.accountInactive.setVisibility(8);
            } else {
                navigationDrawer.accountActive.setVisibility(8);
                navigationDrawer.accountInactive.setVisibility(0);
            }
        }
        if (str != null) {
            navigationDrawer.subscriptionText.setText(getContext().getString(C0434R.string.nav_drawer_subscription_info, new Object[]{str}));
        } else {
            navigationDrawer.subscriptionText.setText(getContext().getString(C0434R.string.nav_drawer_subscription_default));
        }
        if (j2 > 0) {
            navigationDrawer.subscriptionUsedMinutesText.setText(getContext().getString(C0434R.string.nav_drawer_subscription_minutes_used, new Object[]{Long.valueOf(j), Long.valueOf(j2)}));
            navigationDrawer.subscriptionUsedMinutesText.setVisibility(0);
        } else {
            navigationDrawer.subscriptionUsedMinutesText.setVisibility(8);
        }
        if (j3 > 0) {
            navigationDrawer.subscriptionEndDate.setText(getContext().getString(C0434R.string.nav_drawer_subscription_info_end_date, new Object[]{new SimpleDateFormat("dd.MM.yyyy").format(new Date(j3 * 1000))}));
            navigationDrawer.subscriptionEndDate.setVisibility(0);
            return;
        }
        navigationDrawer.subscriptionEndDate.setVisibility(8);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.itemClickListener != null) {
            this.itemClickListener.onItemClicked(this.menuAdapter.getItem(i));
        }
    }

    public void setMenuItems(List<MenuItem> list) {
        this.menuAdapter.setMenuItems(list);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C0434R.id.navigation_drawer_refresh:
                refreshInfo();
                return;
            case C0434R.id.navigation_drawer_top_up_acc:
                this.itemClickListener.toUpClicked();
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSubscriptionPurchased(EventDataSubscriptionPurchased eventDataSubscriptionPurchased) {
        refreshInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSubscriptionTimeChanged(EventDataSubscriptionTimeChange eventDataSubscriptionTimeChange) {
        refreshInfo();
    }

    private void prefillSubscriptionData() {
        this.subscriptionText.setText(getContext().getString(C0434R.string.nav_drawer_subscription_default));
        this.subscriptionUsedMinutesText.setText(getContext().getString(C0434R.string.nav_drawer_subscription_minutes_used, new Object[]{Integer.valueOf(0), Integer.valueOf(0)}));
    }

    public void refreshInfo() {
        refreshInfoSync();
    }

    private void refreshInfoSync() {
        if (!this.isRefreshing) {
            Log.m94i(TAG, "Refreshing info");
            setRefreshProgress(true);
            IM.onBg().execute(new NavigationDrawer$$Lambda$1(this));
        }
    }

    final /* synthetic */ void lambda$refreshInfoSync$200$NavigationDrawer() {
        User user = UserManager.getUser();
        if (user == null) {
            setRefreshProgress(false);
            return;
        }
        try {
            TariffExtraInfo tariffExtraInfo;
            ResponseActiveTariff currentTariffSync = new TariffRepository().getCurrentTariffSync();
            ResponseCurrentTariffUsedSeconds tariffSecondsUsedTodaySync = new TariffRepository().getTariffSecondsUsedTodaySync();
            ResponseUserDetails responseUserDetails = (ResponseUserDetails) Rest.getApiNextbike().getUserDetails(Cfg.get().apikeyNextbike(), user.getLoginkey()).execute().body();
            if (!(responseUserDetails == null || responseUserDetails.userDetails == null)) {
                UserManager.saveUserCredits(responseUserDetails.userDetails.getCredits(), responseUserDetails.userDetails.getIsActive());
                UserManager.saveUserState(responseUserDetails.userDetails.getIsActive());
            }
            if (currentTariffSync.tariffExtraInfo != null) {
                tariffExtraInfo = currentTariffSync.tariffExtraInfo;
            } else {
                tariffExtraInfo = Tariffs.Tariff_PAY_AS_YOU_GO;
            }
            IM.onUi().execute(new NavigationDrawer$$Lambda$2(this, tariffSecondsUsedTodaySync, currentTariffSync, getContext().getString(tariffExtraInfo.getNameRes()), responseUserDetails));
        } catch (Throwable e) {
            Log.ex(e);
            IM.onUi().execute(new NavigationDrawer$$Lambda$3(this));
        }
    }

    final /* synthetic */ void lambda$null$198$NavigationDrawer(ResponseCurrentTariffUsedSeconds responseCurrentTariffUsedSeconds, ResponseActiveTariff responseActiveTariff, String str, ResponseUserDetails responseUserDetails) {
        long floor = (long) Math.floor(((double) responseCurrentTariffUsedSeconds.secondsUsedToday) / 60.0d);
        long minutesPerDay = responseActiveTariff.tariffExtraInfo != null ? (long) responseActiveTariff.tariffExtraInfo.getMinutesPerDay() : 0;
        long j = responseActiveTariff.currentActiveTariff != null ? responseActiveTariff.currentActiveTariff.validTo : 0;
        setRefreshProgress(false);
        setData(UserManager.getUserDetails(), str, floor, minutesPerDay, j);
        if (responseActiveTariff.success == null || responseCurrentTariffUsedSeconds.success == null || responseUserDetails == null || responseUserDetails.userDetails == null) {
            TopToast.show(C0434R.string.error_ocurred, 0, 2500);
        }
    }

    final /* synthetic */ void lambda$null$199$NavigationDrawer() {
        setRefreshProgress(false);
        TopToast.show(C0434R.string.error_ocurred, 0, TopToast.DURATION_SHORT);
    }

    private void setRefreshProgress(boolean z) {
        if (z) {
            this.isRefreshing = true;
            this.buttonRefresh.setAlpha(0.5f);
            this.buttonRefresh.animate().rotation(99999.0f).setDuration(599994).setInterpolator(new LinearInterpolator()).start();
            return;
        }
        this.isRefreshing = false;
        this.buttonRefresh.setAlpha(1.0f);
        this.buttonRefresh.setRotation(0.0f);
        this.buttonRefresh.animate().rotation(0.0f).setDuration(0).start();
    }

    private void onCreditsUpdatedFail() {
        TopToast.show(C0434R.string.error_ocurred, 0, TopToast.DURATION_SHORT);
    }

    public void onDrawerOpened(@NonNull View view) {
        Log.m98v(TAG, "onDrawerOpened");
        this.refreshRepeatTask.start();
    }

    public void onDrawerClosed(@NonNull View view) {
        Log.m98v(TAG, "onDrawerClosed");
        this.refreshRepeatTask.stop();
    }
}
