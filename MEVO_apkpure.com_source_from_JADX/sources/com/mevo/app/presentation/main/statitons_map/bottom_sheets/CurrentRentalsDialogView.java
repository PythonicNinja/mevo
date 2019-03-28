package com.mevo.app.presentation.main.statitons_map.bottom_sheets;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.RentalWithBattery;
import com.mevo.app.data.model.User;
import com.mevo.app.data.model.event.EventDataBikeReturn;
import com.mevo.app.data.repository.NextbikeApiRepository;
import com.mevo.app.modules.firebase_cloud_messaging.NotificationsHelper;
import com.mevo.app.presentation.adapters.BikesListDividerDecoration;
import com.mevo.app.presentation.adapters.CurrentRentalsAdapter;
import com.mevo.app.presentation.adapters.CurrentRentalsAdapter.CurrentRentalsAdapterListener;
import com.mevo.app.presentation.custom_views.BasicHeader;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.dialogs.BikeInfoDialog.Builder;
import com.mevo.app.presentation.dialogs.ParkingDialogView;
import com.mevo.app.presentation.dialogs.ParkingDialogView.ParkingListener;
import com.mevo.app.presentation.dialogs.ResumeDrivingDialog;
import com.mevo.app.presentation.dialogs.ResumeDrivingDialog.ResumeListener;
import com.mevo.app.presentation.dialogs.ReturnDialogView;
import com.mevo.app.presentation.main.MainActivityInterface;
import com.mevo.app.presentation.main.booking_details.BookingDetailsFragment;
import com.mevo.app.presentation.main.ongoing_rent_details.OngoingRentDetailsFragment;
import com.mevo.app.tools.AlarmReceiver;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.Tools;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.location.LocationTool;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CurrentRentalsDialogView extends LinearLayout implements CurrentRentalsAdapterListener {
    private static final String BIKENUMBER_KEY = "bikeNumber";
    private static final String TAG = "MyRentalsDialog";
    private CurrentRentalsAdapter currentRentalsAdapter;
    private RecyclerView currentRentalsRecycler;
    private BasicHeader header;
    @Nullable
    private MyRentalsListener listener;
    private MainActivityInterface mainActivityInterface;
    @Nullable
    private MapCallback mapCallback;
    private NextbikeApiRepository nextbikeApiRepository;
    private TextView noRentalsTx;
    private boolean wasExpanded = false;

    public interface MapCallback {
        boolean isCurrentLocationInFlexzone();

        void onRefreshClick();

        void refreshMapAndDialog();
    }

    public interface MyRentalsListener {
        void onHeaderClick();

        void onItemsLoaded(CurrentRentalsAdapter currentRentalsAdapter, int i);
    }

    public CurrentRentalsDialogView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public CurrentRentalsDialogView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public CurrentRentalsDialogView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    @RequiresApi(api = 21)
    public CurrentRentalsDialogView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.view_my_rentals, this);
        this.nextbikeApiRepository = new NextbikeApiRepository();
        findViews();
        this.currentRentalsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.currentRentalsRecycler.addItemDecoration(new BikesListDividerDecoration(context));
        this.currentRentalsAdapter = new CurrentRentalsAdapter(Tools.locationToLatLng(LocationTool.get().getLastKnownLocation()));
        this.currentRentalsRecycler.setAdapter(this.currentRentalsAdapter);
        this.currentRentalsRecycler.setNestedScrollingEnabled(false);
        this.currentRentalsAdapter.setCurrentRentalsAdapterListener(this);
        this.noRentalsTx.setVisibility(4);
        setListeners();
    }

    private void setListeners() {
        this.header.setOnClickListener(new CurrentRentalsDialogView$$Lambda$0(this));
        this.header.setOnRefreshClicked(new CurrentRentalsDialogView$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$setListeners$255$CurrentRentalsDialogView(View view) {
        if (this.listener != null) {
            this.listener.onHeaderClick();
        }
    }

    final /* synthetic */ void lambda$setListeners$256$CurrentRentalsDialogView() {
        if (this.mapCallback != null) {
            this.mapCallback.onRefreshClick();
        }
        fetchCurrentRentalsAndBookings();
    }

    private void findViews() {
        this.currentRentalsRecycler = (RecyclerView) findViewById(C0434R.id.fragment_rent_history_recycler);
        this.noRentalsTx = (TextView) findViewById(C0434R.id.no_rentals_text);
        this.header = (BasicHeader) findViewById(C0434R.id.fragment_rent_history_header);
    }

    public void refreshData() {
        fetchCurrentRentalsAndBookings();
    }

    @Subscribe
    public void onBikeReturn(EventDataBikeReturn eventDataBikeReturn) {
        refreshData();
    }

    private void fetchCurrentRentalsAndBookings() {
        User user = UserManager.getUser();
        if (user != null) {
            this.header.setProgressBarVisibility(true);
            new NextbikeApiRepository().getCurrentRentalsWithBatteryAndBookings(user.getLoginkey(), new CurrentRentalsDialogView$$Lambda$2(this, user));
        }
    }

    /* renamed from: lambda$fetchCurrentRentalsAndBookings$257$CurrentRentalsDialogView */
    final /* synthetic */ void m115x7d2178f9(User user, List list, List list2, boolean z, Exception exception) {
        this.header.setProgressBarVisibility(false);
        if (z) {
            bindData(list, list2, user.getCurrency());
            return;
        }
        bindData(null, null, user.getCurrency());
        TopToast.show(C0434R.string.error_ocurred, 0, 2500);
    }

    private void bindData(List<BookingItem> list, List<RentalWithBattery> list2, String str) {
        if (list == null) {
            list = new ArrayList();
        }
        if (list2 == null) {
            list2 = new ArrayList();
        }
        int i = 0;
        Object obj = (list2.isEmpty() && list.isEmpty()) ? 1 : null;
        this.noRentalsTx.setVisibility(obj != null ? 0 : 8);
        RecyclerView recyclerView = this.currentRentalsRecycler;
        if (obj != null) {
            i = 8;
        }
        recyclerView.setVisibility(i);
        this.currentRentalsAdapter.setUserLocation(Tools.locationToLatLng(LocationTool.get().getLastKnownLocation()));
        this.currentRentalsAdapter.setData(list2, list, str);
    }

    public void onStart() {
        this.currentRentalsAdapter.setCurrentRentalsAdapterListener(this);
        this.mainActivityInterface = IM.activity() instanceof MainActivityInterface ? (MainActivityInterface) IM.activity() : null;
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
        this.currentRentalsAdapter.setCurrentRentalsAdapterListener(null);
        this.currentRentalsAdapter.stopAllTimers(this.currentRentalsRecycler);
        this.mainActivityInterface = null;
    }

    public CurrentRentalsDialogView setListener(MyRentalsListener myRentalsListener) {
        this.listener = myRentalsListener;
        return this;
    }

    public CurrentRentalsDialogView setMapCallback(MapCallback mapCallback) {
        this.mapCallback = mapCallback;
        return this;
    }

    public int getNoRentalsTxHeight() {
        this.noRentalsTx.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        return this.noRentalsTx.getMeasuredHeight() + 0;
    }

    public int getHeaderHeight() {
        this.header.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        return (this.header.getMeasuredHeight() + 0) + ((LayoutParams) this.header.getLayoutParams()).topMargin;
    }

    public void onClickBookingDetails(BookingItem bookingItem) {
        if (this.mainActivityInterface != null) {
            this.mainActivityInterface.changeFragment(BookingDetailsFragment.newInstance(bookingItem));
        }
    }

    public void onClickParkBike(final String str, final String str2) {
        if (this.mainActivityInterface != null) {
            final ParkingDialogView newInstance = ParkingDialogView.newInstance();
            newInstance.show();
            newInstance.setListener(new ParkingListener() {
                public void onCloseButtonClick() {
                }

                public void onParkButtonClick() {
                    CurrentRentalsDialogView.this.mainActivityInterface.setProgressViewVisibility(true);
                    CurrentRentalsDialogView.this.nextbikeApiRepository.parkBike(str, str2, new CurrentRentalsDialogView$1$$Lambda$0(this, newInstance, str));
                }

                final /* synthetic */ void lambda$onParkButtonClick$258$CurrentRentalsDialogView$1(ParkingDialogView parkingDialogView, String str, boolean z, Exception exception) {
                    CurrentRentalsDialogView.this.mainActivityInterface.setProgressViewVisibility(false);
                    parkingDialogView.dismiss();
                    CurrentRentalsDialogView.this.onBikeActionDone();
                    if (z) {
                        new Builder().setBikeInfo(CurrentRentalsDialogView.this.getContext().getString(C0434R.string.bike_parking_success, new Object[]{str}), str).setNeutralButton((int) C0434R.string.close, null).build().show();
                        CurrentRentalsDialogView.this.countDownOnPark(Boolean.valueOf(false), str);
                        return;
                    }
                    new Builder().setBikeInfo(CurrentRentalsDialogView.this.getContext().getString(C0434R.string.bike_parking_fail, new Object[]{str}), str).setNeutralButton((int) C0434R.string.close, null).build().show();
                }
            });
        }
    }

    public void onClickReturnBike(String str) {
        ReturnDialogView.newInstance().show();
    }

    public void onClickResumeRentingBike(final String str, final String str2) {
        final ResumeDrivingDialog newInstance = ResumeDrivingDialog.newInstance();
        newInstance.show();
        newInstance.setListener(new ResumeListener() {
            public void onCloseButtonClick() {
            }

            public void onResumeButtonClick() {
                if (CurrentRentalsDialogView.this.mainActivityInterface != null) {
                    CurrentRentalsDialogView.this.mainActivityInterface.setProgressViewVisibility(true);
                    CurrentRentalsDialogView.this.nextbikeApiRepository.resumeRenting(str, str2, new CurrentRentalsDialogView$2$$Lambda$0(this, newInstance, str));
                }
            }

            final /* synthetic */ void lambda$onResumeButtonClick$259$CurrentRentalsDialogView$2(ResumeDrivingDialog resumeDrivingDialog, String str, boolean z, Exception exception) {
                if (CurrentRentalsDialogView.this.mainActivityInterface != null) {
                    CurrentRentalsDialogView.this.mainActivityInterface.setProgressViewVisibility(false);
                }
                resumeDrivingDialog.dismiss();
                CurrentRentalsDialogView.this.onBikeActionDone();
                if (z) {
                    NotificationsHelper.sendNotification(CurrentRentalsDialogView.this.getContext().getString(C0434R.string.app_name), CurrentRentalsDialogView.this.getContext().getString(C0434R.string.resume_rent_notif_info), App.getAppContext());
                    new Builder().setBikeInfo(CurrentRentalsDialogView.this.getContext().getString(C0434R.string.bike_rent_resume_success, new Object[]{str}), str).setNeutralButton((int) C0434R.string.close, null).build().show();
                    CurrentRentalsDialogView.this.countDownOnPark(Boolean.valueOf(true), str);
                    return;
                }
                new Builder().setBikeInfo(CurrentRentalsDialogView.this.getContext().getString(C0434R.string.bike_rent_resume_fail, new Object[]{str}), str).setNeutralButton((int) C0434R.string.close, null).build().show();
            }
        });
    }

    public void onListItemViewMeasured(int i) {
        if (this.listener != null) {
            this.listener.onItemsLoaded(this.currentRentalsAdapter, i);
        }
    }

    public void onClickRentDetails(RentalItem rentalItem) {
        this.mainActivityInterface.changeFragment(OngoingRentDetailsFragment.newInstance(rentalItem));
    }

    public boolean wasExpanded() {
        return this.wasExpanded;
    }

    public CurrentRentalsDialogView setWasExpanded(boolean z) {
        this.wasExpanded = z;
        return this;
    }

    private void onBikeActionDone() {
        collapseBottomSheet();
        if (this.mapCallback != null) {
            this.mapCallback.refreshMapAndDialog();
        }
    }

    private void collapseBottomSheet() {
        try {
            BottomSheetBehavior.from(this).setState(4);
        } catch (Throwable e) {
            Log.ex(TAG, e);
        }
    }

    private void countDownOnPark(Boolean bool, String str) {
        AlarmManager alarmManager = (AlarmManager) App.getAppContext().getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(App.getAppContext(), AlarmReceiver.class);
        intent.putExtra(BIKENUMBER_KEY, str);
        str = PendingIntent.getBroadcast(App.getAppContext(), Integer.parseInt(str), intent, 0);
        if (alarmManager == null) {
            return;
        }
        if (bool.booleanValue() != null) {
            alarmManager.cancel(str);
            return;
        }
        bool = Calendar.getInstance();
        bool.setTimeInMillis(System.currentTimeMillis());
        bool.add(10, 12);
        alarmManager.set(0, bool.getTimeInMillis(), str);
    }
}
