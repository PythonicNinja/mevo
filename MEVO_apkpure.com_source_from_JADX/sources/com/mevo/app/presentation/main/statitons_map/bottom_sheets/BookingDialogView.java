package com.mevo.app.presentation.main.statitons_map.bottom_sheets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.annimon.stream.Stream;
import com.google.android.gms.maps.model.LatLng;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Ui;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.BikeJson;
import com.mevo.app.data.model.StationJson;
import com.mevo.app.data.repository.NextbikeApiRepository;
import com.mevo.app.presentation.adapters.BikesListDividerDecoration;
import com.mevo.app.presentation.custom_views.BasicHeader;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.dialogs.BikeReservationDialog;
import com.mevo.app.presentation.dialogs.BikeReservationDialog.ReservationDecisionListener;
import com.mevo.app.presentation.dialogs.InfoDialog.Builder;
import com.mevo.app.presentation.main.MainActivityInterface;
import com.mevo.app.presentation.main.statitons_map.adapters.StationBikesAdapter;
import com.mevo.app.presentation.main.statitons_map.adapters.StationBikesAdapter.ReservationListener;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.Tools;
import com.mevo.app.tools.bike_tools.BookingRepository;
import com.mevo.app.tools.location.LocationTool;
import java.util.ArrayList;
import java.util.List;

public class BookingDialogView extends LinearLayout implements ReservationListener, ReservationDecisionListener {
    private static final String TAG = "ReservationDialogView";
    private RecyclerView bikesList;
    private BasicHeader header;
    private ReservationViewListener listener;
    private TextView noBikes;
    private int placeId = -1;
    private String placeName;
    private Button reserveButton;
    private LinearLayout reserveButtonLayout;
    private TextView reservedBikes;
    private StationBikesAdapter stationBikesAdapter;
    private LatLng stationLocation;

    public interface ReservationViewListener {
        void onHeaderClick();
    }

    public BookingDialogView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public BookingDialogView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public BookingDialogView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    @RequiresApi(api = 21)
    public BookingDialogView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.view_reserve_sheet, this);
        findViews();
        setupRecycler(context);
        setListeners();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.stationBikesAdapter.setReservationListener(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.stationBikesAdapter.setReservationListener(null);
    }

    private void findViews() {
        this.bikesList = (RecyclerView) findViewById(C0434R.id.view_reserve_sheet_bikes_list);
        this.noBikes = (TextView) findViewById(C0434R.id.view_reserve_sheet_no_bikes);
        this.header = (BasicHeader) findViewById(C0434R.id.view_reserve_sheet_header);
        this.reserveButton = (Button) findViewById(C0434R.id.view_reserve_sheet_reserve_button);
        this.reserveButtonLayout = (LinearLayout) findViewById(C0434R.id.view_reserve_sheet_reserve_button_layout);
        this.reservedBikes = (TextView) findViewById(C0434R.id.view_reserve_sheet_reserved_bikes);
    }

    private void setupRecycler(Context context) {
        this.bikesList.setLayoutManager(new LinearLayoutManager(context));
        this.bikesList.addItemDecoration(new BikesListDividerDecoration(context));
        this.stationBikesAdapter = new StationBikesAdapter(null, Tools.locationToLatLng(LocationTool.get().getLastKnownLocation()), this.stationLocation, this);
        this.bikesList.setAdapter(this.stationBikesAdapter);
        this.bikesList.setNestedScrollingEnabled(false);
    }

    private void setListeners() {
        this.header.setOnRefreshClicked(new BookingDialogView$$Lambda$0(this));
        this.header.setOnClickListener(new BookingDialogView$$Lambda$1(this));
        this.reserveButton.setOnClickListener(new BookingDialogView$$Lambda$2(this));
    }

    final /* synthetic */ void lambda$setListeners$249$BookingDialogView() {
        fetchBikesForStation(this.placeName, this.stationLocation, this.placeId);
    }

    final /* synthetic */ void lambda$setListeners$250$BookingDialogView(View view) {
        if (this.listener != null) {
            this.listener.onHeaderClick();
        }
    }

    final /* synthetic */ void lambda$setListeners$251$BookingDialogView(View view) {
        onTryBookBike(null);
    }

    public BookingDialogView setListener(ReservationViewListener reservationViewListener) {
        this.listener = reservationViewListener;
        return this;
    }

    public void fetchBikesForStation(String str, LatLng latLng, int i) {
        if (str == null) {
            Log.m90e("ReservationDialog", "no bikes to fetched");
            return;
        }
        this.placeName = str;
        this.placeId = i;
        this.stationLocation = latLng;
        setHeaderText(str);
        this.header.setProgressBarVisibility(true);
        new NextbikeApiRepository().getStationDetails(i, new BookingDialogView$$Lambda$3(this));
    }

    final /* synthetic */ void lambda$fetchBikesForStation$252$BookingDialogView(StationJson stationJson, boolean z, Exception exception) {
        this.header.setProgressBarVisibility(false);
        if (!z) {
            TopToast.show(true, 0, TopToast.DURATION_SHORT);
        }
        bindBikesData(stationJson != null ? stationJson.getBikes() : new ArrayList());
        z = this.reservedBikes;
        exception = getContext();
        Object[] objArr = new Object[1];
        objArr[0] = stationJson != null ? String.valueOf(stationJson.getBookedBikesCount()) : Integer.valueOf(0);
        z.setText(exception.getString(C0434R.string.amount_of_reserved_bikes, objArr));
    }

    private void setHeaderText(String str) {
        str = str != null ? String.valueOf(str) : "";
        this.header.setHeader(IM.resources().getString(C0434R.string.bikes_stations, new Object[]{str}));
    }

    private void bindBikesData(@Nullable List<BikeJson> list) {
        Iterable arrayList;
        if (list == null) {
            arrayList = new ArrayList();
        }
        list = Stream.of(arrayList).filter(BookingDialogView$$Lambda$4.$instance).toList();
        boolean z = false;
        Ui.visible(this.reserveButtonLayout, list.size() != 0);
        View view = this.noBikes;
        if (list.size() == 0) {
            z = true;
        }
        Ui.visible(view, z);
        this.stationBikesAdapter.setData(list, Tools.locationToLatLng(LocationTool.get().getLastKnownLocation()), this.stationLocation);
    }

    public void onTryBookBike(String str) {
        MainActivityInterface mainActivityInterface = IM.activity() != null ? (MainActivityInterface) IM.activity() : null;
        if (mainActivityInterface == null) {
            TopToast.show(C0434R.string.error_ocurred, 0, TopToast.DURATION_SHORT);
            return;
        }
        mainActivityInterface.setProgressViewVisibility(true);
        new NextbikeApiRepository().isBookingAvailable(new BookingDialogView$$Lambda$5(this, mainActivityInterface, str));
    }

    final /* synthetic */ void lambda$onTryBookBike$253$BookingDialogView(MainActivityInterface mainActivityInterface, String str, boolean z, boolean z2) {
        mainActivityInterface.setProgressViewVisibility(false);
        if (!z) {
            TopToast.show(C0434R.string.error_ocurred, 0, 2500);
        } else if (z2) {
            mainActivityInterface = BikeReservationDialog.newInstance(str);
            mainActivityInterface.show();
            mainActivityInterface.setDecisionListener(this);
        } else {
            new Builder().setText((int) C0434R.string.limit_reached).setNeutralButton((int) C0434R.string.ok, null).build().show();
        }
    }

    public void onBookingDecision(boolean z, String str) {
        MainActivityInterface mainActivityInterface = IM.activity() != null ? (MainActivityInterface) IM.activity() : null;
        if (z && mainActivityInterface != null) {
            new BookingRepository().onBookingDecision(String.valueOf(this.placeId), str, new BookingDialogView$$Lambda$6(this));
        } else if (!z) {
            collapseBottomSheet();
        }
    }

    final /* synthetic */ void lambda$onBookingDecision$254$BookingDialogView(boolean z, boolean z2) {
        if (z) {
            collapseBottomSheet();
        }
    }

    private void collapseBottomSheet() {
        try {
            BottomSheetBehavior.from(this).setState(4);
        } catch (Throwable e) {
            Log.ex(TAG, e);
        }
    }
}
