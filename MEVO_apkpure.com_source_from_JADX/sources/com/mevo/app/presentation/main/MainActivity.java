package com.mevo.app.presentation.main;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.constants.AppLanguageCode;
import com.mevo.app.data.model.UserDetails;
import com.mevo.app.data.model.event.EventDataBikeReturn;
import com.mevo.app.data.model.response.ResponseUpdateUser;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.repository.RegisterRepository;
import com.mevo.app.data.repository.UserRepository;
import com.mevo.app.modules.tracker.UserTracker;
import com.mevo.app.presentation.ToolbarActivityInterface;
import com.mevo.app.presentation.base.BaseActivity;
import com.mevo.app.presentation.custom_views.AppToolbar;
import com.mevo.app.presentation.custom_views.AppToolbar.AppToolbarListener;
import com.mevo.app.presentation.custom_views.ProgressOverlayView;
import com.mevo.app.presentation.dialogs.ReturnSuccessDialog;
import com.mevo.app.presentation.entry.EntryActivity;
import com.mevo.app.presentation.main.city_card.without_nfc.CardFragment;
import com.mevo.app.presentation.main.city_card.without_nfc.CityCardAddFragment;
import com.mevo.app.presentation.main.contact_us.ContactUsFragment;
import com.mevo.app.presentation.main.how_it_works.HowItWorksFragment;
import com.mevo.app.presentation.main.navigation_drawer.NavigationDrawer;
import com.mevo.app.presentation.main.navigation_drawer.NavigationDrawer.ItemClickListener;
import com.mevo.app.presentation.main.navigation_drawer.NavigationDrawer.MenuItem;
import com.mevo.app.presentation.main.rent_bike.RentBikeQrFragment;
import com.mevo.app.presentation.main.rent_history.RentHistoryFragment;
import com.mevo.app.presentation.main.report_problem.ReportProblemFragment;
import com.mevo.app.presentation.main.statitons_map.StationsMapFragment;
import com.mevo.app.presentation.main.subscriptions.SubscriptionsFragment;
import com.mevo.app.presentation.main.terms_of_use.TermsPricingFragment;
import com.mevo.app.presentation.main.top_up.TopUpFragment;
import com.mevo.app.presentation.main.transactions.TransactionsFragment;
import com.mevo.app.presentation.main.user_details.UserDetailsFragment;
import com.mevo.app.tools.FragmentNavigator;
import com.mevo.app.tools.Lifecycle;
import com.mevo.app.tools.Lifecycle.State;
import com.mevo.app.tools.LocaleHelper;
import com.mevo.app.tools.Permissions;
import com.mevo.app.tools.TermsRepository;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.Utils;
import com.mevo.app.tools.Validator;
import com.mevo.app.tools.VersionChecker;
import com.mevo.app.tools.location.LocationTool;
import java.util.Arrays;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements MainActivityInterface, AppToolbarListener, ItemClickListener, DrawerListener, OnBackStackChangedListener, ToolbarActivityInterface {
    private static final String CHANNEL_ID = "DEFAULT_CHANNEL";
    private static final int REQUEST_CODE_LOCATION = 8002;
    private AppToolbar appToolbar;
    private DrawerLayout drawerLayout;
    private ViewGroup fragmentContainer;
    private FragmentNavigator fragmentNavigationHelper;
    private boolean isDrawerMenuOpen;
    private NavigationDrawer navigationDrawer;
    private ProgressOverlayView progressOverlayView;

    private static class DrawerMenuItems {
        private static final MenuItem BICYCLE_RENTAL = new MenuItem(C0434R.string.drawer_item_bicycle_rental, C0434R.drawable.menu_qr);
        private static final MenuItem CITY_CARD = new MenuItem(C0434R.string.drawer_menu_item_proximity_card, C0434R.drawable.city_card);
        private static final MenuItem CONTACT_US = new MenuItem(C0434R.string.drawer_item_contact_us, C0434R.drawable.menu_kontakt);
        private static final MenuItem FAQ = new MenuItem(C0434R.string.header_how_it_works, C0434R.drawable.menu_faq);
        private static final MenuItem FIND_BIKE = new MenuItem(C0434R.string.drawer_item_find_bike, C0434R.drawable.menu_szukaj);
        public static final MenuItem[] LIST = new MenuItem[]{FIND_BIKE, MY_ACCOUNT, BICYCLE_RENTAL, SUBSCRIPTIONS, REPORT_PROBLEM, TERMS_OF_USE, FAQ, CONTACT_US, MY_RENTAL, SETTLEMENT, CITY_CARD, PRIVACY_POLICY, LOGOUT};
        private static final MenuItem LOGOUT = new MenuItem(C0434R.string.logout, C0434R.drawable.menu_wyloguj);
        private static final MenuItem MY_ACCOUNT = new MenuItem(C0434R.string.drawer_item_my_account, C0434R.drawable.menu_person);
        private static final MenuItem MY_RENTAL = new MenuItem(C0434R.string.rental_history_menu, C0434R.drawable.menu_rower);
        private static final MenuItem PRIVACY_POLICY = new MenuItem(C0434R.string.header_privacy_policy, C0434R.drawable.ico_privacy_policy);
        private static final MenuItem REPORT_PROBLEM = new MenuItem(C0434R.string.drawer_item_report_problem, C0434R.drawable.menu_problem);
        private static final MenuItem SETTLEMENT = new MenuItem(C0434R.string.frag_transactions_header, C0434R.drawable.icon_settlement);
        private static final MenuItem SUBSCRIPTIONS = new MenuItem(C0434R.string.nav_drawer_menu_item_subscriptions, C0434R.drawable.ic_menu_subscriptions);
        private static final MenuItem TERMS_OF_USE = new MenuItem(C0434R.string.drawer_item_terms_of_use_pricing_2, C0434R.drawable.menu_regulamin);

        private DrawerMenuItems() {
        }
    }

    /* renamed from: com.mevo.app.presentation.main.MainActivity$1 */
    class C08241 implements Callback<ResponseUpdateUser> {
        public void onFailure(Call<ResponseUpdateUser> call, Throwable th) {
        }

        public void onResponse(Call<ResponseUpdateUser> call, Response<ResponseUpdateUser> response) {
        }

        C08241() {
        }
    }

    public void onDrawerSlide(View view, float f) {
    }

    public void onDrawerStateChanged(int i) {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0434R.layout.activity_main);
        findViews();
        this.isDrawerMenuOpen = null;
        this.fragmentNavigationHelper = new FragmentNavigator();
        setListeners();
        this.appToolbar.setAppToolbarListener(this);
        prepareNavigationDrawer();
        if (UserManager.getUser() == null) {
            goToEntryActivityAndFinish();
            return;
        }
        this.fragmentNavigationHelper.showFirstFragmentIfShould(new StationsMapFragment(), getSupportFragmentManager(), this.fragmentContainer);
        updateUserLanguageIfShould();
        remindToFillUserDetailsIfShould();
        UserTracker.get().updateUserOncePerDay(UserManager.getUserDetails(), Utils.intToBool(UserManager.getUser().getIsActive()), this);
        VersionChecker.check();
        createNotificationChannel();
        checkIntentForBikeReturn(getIntent());
        checkIfShouldResendAgreements();
    }

    protected void onStart() {
        super.onStart();
        LocationTool.get().start();
        Permissions.requestIfShould(Permissions.PERMISSIONS_LOCATION, (int) REQUEST_CODE_LOCATION, (Activity) this);
        EventBus.getDefault().register(this);
    }

    protected void onPostResume() {
        super.onPostResume();
        this.fragmentNavigationHelper.onPostResume(this, this.fragmentContainer);
    }

    protected void onPause() {
        super.onPause();
        this.fragmentNavigationHelper.onPause();
    }

    protected void onStop() {
        super.onStop();
        LocationTool.get().stop();
        EventBus.getDefault().unregister(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.drawerLayout.removeDrawerListener(this);
        getSupportFragmentManager().removeOnBackStackChangedListener(this);
        this.appToolbar.setAppToolbarListener(null);
    }

    public void onBackPressed() {
        if (Lifecycle.getActivity() == this) {
            if (Lifecycle.getState() == State.Resumed) {
                super.onBackPressed();
            }
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        checkIntentForBikeReturn(intent);
    }

    private void checkIntentForBikeReturn(Intent intent) {
        if (EventDataBikeReturn.ACTION_BIKE_RETURN.equals(intent.getAction()) && !TextUtils.isEmpty(intent.getStringExtra(EventDataBikeReturn.EXTRA_BIKE_NUMBER))) {
            EventBus.getDefault().post(new EventDataBikeReturn(intent.getStringExtra(EventDataBikeReturn.EXTRA_BIKE_NUMBER)));
            setIntent(null);
        }
    }

    private boolean changeAppLanguageIfUserHasDifferent() {
        UserDetails userDetails = UserManager.getUserDetails();
        if (userDetails == null || !LocaleHelper.canLanguageBeChanged(this, userDetails.getLanguage())) {
            return false;
        }
        LocaleHelper.setLocaleIfCan(this, userDetails.getLanguage());
        Intent intent = getIntent();
        if (intent == null) {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        return true;
    }

    private void checkIfShouldResendAgreements() {
        new RegisterRepository().resendAgreementsIfShould();
    }

    private void updateUserLanguageIfShould() {
        if (UserManager.getUser().getLanguage() != null && !UserManager.getUser().getLanguage().equalsIgnoreCase(LocaleHelper.getLanguage(this))) {
            Rest.getApiNextbike().updateUserLanguage(Cfg.get().apikeyNextbike(), UserManager.getLoginKey(), LocaleHelper.getLanguage(this)).enqueue(new C08241());
        }
    }

    private void remindToFillUserDetailsIfShould() {
        UserDetails userDetails = UserManager.getUserDetails();
        if (userDetails != null) {
            boolean isPeselValid;
            if (AppLanguageCode.PL.getCode().equalsIgnoreCase(UserManager.getUser().getLanguage())) {
                isPeselValid = Validator.isPeselValid(userDetails.getPesel());
            } else {
                isPeselValid = Validator.isPeselValidOrEmpty(userDetails.getPesel());
            }
            if (Validator.isEmailValid(userDetails.getEmail()) && Validator.isFirstNameValid(userDetails.getFirstname()) && Validator.isLastNameValid(userDetails.getLastname()) && Validator.isStreetValid(userDetails.getAddress()) && Validator.isZipCodeValid(userDetails.getZipCode())) {
                boolean isCityValid = Validator.isCityValid(userDetails.getCity());
            }
        }
    }

    private void prepareNavigationDrawer() {
        this.navigationDrawer.setMenuItems(Arrays.asList(DrawerMenuItems.LIST));
        this.navigationDrawer.setItemClickListener(this);
    }

    private void findViews() {
        this.fragmentContainer = (ViewGroup) findViewById(C0434R.id.activity_main_fragment_container);
        this.appToolbar = (AppToolbar) findViewById(C0434R.id.activity_main_app_toolbar);
        this.navigationDrawer = (NavigationDrawer) findViewById(C0434R.id.activity_main_navigation_drawer);
        this.drawerLayout = (DrawerLayout) findViewById(C0434R.id.activity_main_drawer_layout);
        this.progressOverlayView = (ProgressOverlayView) findViewById(C0434R.id.activity_main_progress_view);
    }

    private void setListeners() {
        this.drawerLayout.addDrawerListener(this);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    public void closeDrawer() {
        this.drawerLayout.closeDrawer((int) GravityCompat.END);
    }

    private void openDrawer() {
        this.drawerLayout.openDrawer((int) GravityCompat.END);
    }

    public void goToHome() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        for (int i = 0; i < supportFragmentManager.getBackStackEntryCount(); i++) {
            this.fragmentNavigationHelper.popBackstack();
        }
    }

    public void changeFragment(@NonNull MainFragment mainFragment) {
        this.fragmentNavigationHelper.changeFragment(mainFragment, true);
    }

    public void changeFragment(Fragment fragment, boolean z) {
        fragment = getSupportFragmentManager().beginTransaction().replace(this.fragmentContainer.getId(), fragment);
        if (z) {
            fragment.addToBackStack(false);
        }
        fragment.commit();
    }

    public void changeFragment(@NonNull MainFragment mainFragment, boolean z) {
        this.fragmentNavigationHelper.changeFragment(mainFragment, z);
    }

    public void popFragment() {
        this.fragmentNavigationHelper.popBackstack();
    }

    public void goToEntryActivityAndFinish() {
        startActivity(new Intent(this, EntryActivity.class));
        finish();
    }

    public void setProgressViewVisibility(boolean z) {
        if (z) {
            this.progressOverlayView.show();
        } else {
            this.progressOverlayView.hide();
        }
    }

    public void setAppToolbarVisibility(boolean z) {
        if (z) {
            this.appToolbar.setVisibility(0);
        } else {
            this.appToolbar.setVisibility(8);
        }
    }

    public MainFragment getVisibleFragment() {
        return (MainFragment) getSupportFragmentManager().findFragmentById(this.fragmentContainer.getId());
    }

    public void refreshCurrentFragment() {
        this.fragmentNavigationHelper.refreshCurrentFragment();
    }

    public void onBackClick() {
        onBackPressed();
        closeDrawer();
    }

    public void onSettingsClick() {
        if (this.isDrawerMenuOpen) {
            closeDrawer();
        } else {
            openDrawer();
        }
    }

    public void onContactClick() {
        if (this.isDrawerMenuOpen) {
            closeDrawer();
        }
        changeFragment(ContactUsFragment.newInstance(), true);
    }

    public void onItemClicked(MenuItem menuItem) {
        if (menuItem.getId() == DrawerMenuItems.FIND_BIKE.getId()) {
            changeFragment(StationsMapFragment.newInstance());
        } else if (menuItem.getId() == DrawerMenuItems.BICYCLE_RENTAL.getId()) {
            changeFragment(RentBikeQrFragment.newInstance());
        } else if (menuItem.getId() == DrawerMenuItems.MY_RENTAL.getId()) {
            changeFragment(RentHistoryFragment.newInstance());
        } else if (menuItem.getId() == DrawerMenuItems.SETTLEMENT.getId()) {
            changeFragment(TransactionsFragment.newInstance());
        } else if (menuItem.getId() == DrawerMenuItems.MY_ACCOUNT.getId()) {
            changeFragment(UserDetailsFragment.newInstance());
        } else if (menuItem.getId() == DrawerMenuItems.CITY_CARD.getId()) {
            navigateToCityCard();
        } else if (menuItem.getId() == DrawerMenuItems.REPORT_PROBLEM.getId()) {
            changeFragment(ReportProblemFragment.newInstance());
        } else if (menuItem.getId() == DrawerMenuItems.TERMS_OF_USE.getId()) {
            changeFragment(TermsPricingFragment.newInstance());
        } else if (menuItem.getId() == DrawerMenuItems.FAQ.getId()) {
            changeFragment(HowItWorksFragment.newInstance());
        } else if (menuItem.getId() == DrawerMenuItems.PRIVACY_POLICY.getId()) {
            Utils.launchBrowser(this, TermsRepository.getPrivacyUrl());
        } else if (menuItem.getId() == DrawerMenuItems.CONTACT_US.getId()) {
            changeFragment(ContactUsFragment.newInstance(), true);
        } else if (menuItem.getId() == DrawerMenuItems.LOGOUT.getId()) {
            UserManager.logout();
            goToEntryActivityAndFinish();
        } else if (menuItem.getId() == DrawerMenuItems.SUBSCRIPTIONS.getId()) {
            changeFragment(SubscriptionsFragment.newInstance());
        }
        closeDrawer();
    }

    private void navigateToCityCard() {
        setProgressViewVisibility(true);
        new UserRepository().getCardNumber(new MainActivity$$Lambda$0(this));
    }

    final /* synthetic */ void lambda$navigateToCityCard$197$MainActivity(String str, boolean z, Exception exception) {
        setProgressViewVisibility(null);
        if (!z || str.equals("")) {
            changeFragment(CityCardAddFragment.newInstance(), true);
        } else {
            changeFragment(CardFragment.newInstance(str), true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBikeReturnEvent(EventDataBikeReturn eventDataBikeReturn) {
        if (!TextUtils.isEmpty(eventDataBikeReturn.getBikeNumer())) {
            ReturnSuccessDialog.newInstance(eventDataBikeReturn.getBikeNumer()).show();
        }
        if (this.navigationDrawer != null) {
            this.navigationDrawer.refreshInfo();
        }
    }

    public void closeNavigationDrawer() {
        closeDrawer();
    }

    public void toUpClicked() {
        changeFragment(TopUpFragment.newInstance());
        closeDrawer();
    }

    public void onDrawerOpened(View view) {
        this.isDrawerMenuOpen = true;
    }

    public void onDrawerClosed(View view) {
        this.isDrawerMenuOpen = null;
        setAppToolbarVisibility(true);
    }

    public void onBackStackChanged() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            this.appToolbar.setBackVisibility(false);
        } else {
            this.appToolbar.setBackVisibility(true);
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 8002 && Permissions.hasPermissions(Permissions.PERMISSIONS_LOCATION, this) != 0) {
            LocationTool.get().start();
        }
    }

    public AppToolbar getToolbar() {
        return this.appToolbar;
    }

    private void createNotificationChannel() {
        if (VERSION.SDK_INT >= 26) {
            CharSequence string = App.getAppContext().getString(C0434R.string.default_notification_channel_id);
            String string2 = App.getAppContext().getString(C0434R.string.channel_description);
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, string, 3);
            notificationChannel.setDescription(string2);
            ((NotificationManager) App.getAppContext().getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }
}
