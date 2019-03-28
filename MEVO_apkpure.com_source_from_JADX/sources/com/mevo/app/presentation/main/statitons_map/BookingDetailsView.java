package com.mevo.app.presentation.main.statitons_map;

import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.maps.model.LatLng;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.presentation.custom_views.BasicHeader;
import com.mevo.app.tools.RepeatTask;
import com.mevo.app.tools.SpanUtils;
import com.mevo.app.tools.formatters.Formatter;
import com.mevo.app.tools.location.LocationTool;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public class BookingDetailsView extends LinearLayout {
    private TextView bikeInfo;
    private TextView bikeNameNumber;
    private TextView bikeStatusInfo;
    private BookingItem bookingItem;
    private Button cancelBookingButton;
    private TextView distanceFromBike;
    private BasicHeader header;
    @Nullable
    ReservationDetailsListener listener;
    private Button rentBookedBikeButton;
    private RepeatTask timeCounterTask;

    public interface ReservationDetailsListener {
        void onCancelBookingBike(BookingItem bookingItem);

        void onRentBookedBike(BookingItem bookingItem);
    }

    final /* bridge */ /* synthetic */ void bridge$lambda$0$BookingDetailsView() {
        updateBikeStatusInfo();
    }

    public BookingDetailsView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public BookingDetailsView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public BookingDetailsView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    @RequiresApi(api = 21)
    public BookingDetailsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.view_reservation_details, this);
        findViews();
        this.header.hideRefresh();
        this.timeCounterTask = new RepeatTask(1000, false, new BookingDetailsView$$Lambda$0(this));
    }

    private void findViews() {
        this.header = (BasicHeader) findViewById(C0434R.id.view_reservation_header);
        this.bikeNameNumber = (TextView) findViewById(C0434R.id.list_item_current_rental_name_number);
        this.distanceFromBike = (TextView) findViewById(C0434R.id.list_item_current_rental_distance);
        this.bikeInfo = (TextView) findViewById(C0434R.id.list_item_current_rental_bike_info);
        this.bikeStatusInfo = (TextView) findViewById(C0434R.id.list_item_current_rental_rent_status_info);
        this.rentBookedBikeButton = (Button) findViewById(C0434R.id.list_item_current_rental_rent_button);
        this.cancelBookingButton = (Button) findViewById(C0434R.id.list_item_current_rental_cancel_booking_button);
    }

    public void setListener(ReservationDetailsListener reservationDetailsListener) {
        this.listener = reservationDetailsListener;
    }

    public void onStart() {
        this.timeCounterTask.start();
    }

    public void onStop() {
        this.timeCounterTask.stop();
    }

    public void fillData(BookingItem bookingItem) {
        this.bookingItem = bookingItem;
        this.rentBookedBikeButton.setOnClickListener(new BookingDetailsView$$Lambda$1(this, bookingItem));
        this.cancelBookingButton.setOnClickListener(new BookingDetailsView$$Lambda$2(this, bookingItem));
        SpanUtils.on(this.bikeNameNumber).boldText(bookingItem.getPlaceName()).done();
        Location lastKnownLocation = LocationTool.get().getLastKnownLocation();
        LatLng toLatLng = lastKnownLocation != null ? LocationTool.toLatLng(lastKnownLocation) : null;
        double calculateDistanceKm = toLatLng != null ? LocationTool.calculateDistanceKm(bookingItem.getCurrentLatLng(), toLatLng) : 0.0d;
        TextView textView = this.distanceFromBike;
        Resources resources = IM.resources();
        Object[] objArr = new Object[1];
        objArr[0] = calculateDistanceKm > 0.0d ? Formatter.formatDistance((double) ((int) (calculateDistanceKm * 1000.0d))) : Operation.MINUS;
        textView.setText(resources.getString(C0434R.string.distance_smartbike, objArr));
        this.bikeStatusInfo.setText(IM.resources().getString(C0434R.string.bike_book_time, new Object[]{Formatter.formatDurationBooking(bookingItem.getTimeLeftToEndSeconds(), IM.context())}));
        this.bikeStatusInfo.setVisibility(0);
    }

    final /* synthetic */ void lambda$fillData$177$BookingDetailsView(BookingItem bookingItem, View view) {
        if (this.listener != null) {
            this.listener.onRentBookedBike(bookingItem);
        }
    }

    final /* synthetic */ void lambda$fillData$178$BookingDetailsView(BookingItem bookingItem, View view) {
        if (this.listener != null) {
            this.listener.onCancelBookingBike(bookingItem);
        }
    }

    private void updateBikeStatusInfo() {
        if (this.bookingItem != null) {
            this.bikeStatusInfo.setText(IM.resources().getString(C0434R.string.bike_book_time, new Object[]{Formatter.formatDurationBooking(this.bookingItem.getTimeLeftToEndSeconds(), IM.context())}));
            this.bikeStatusInfo.setVisibility(0);
        }
    }

    public void setWalkTime(@Nullable Long l) {
        if (l == null) {
            this.bikeInfo.setText(IM.resources().getString(C0434R.string.walking_no_time));
            return;
        }
        l = (int) (l.longValue() / 60);
        this.bikeInfo.setText(IM.resources().getString(C0434R.string.walking_time, new Object[]{Integer.valueOf(l)}));
    }
}
