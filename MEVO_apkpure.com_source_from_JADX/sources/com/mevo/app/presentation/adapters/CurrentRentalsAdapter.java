package com.mevo.app.presentation.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.maps.model.LatLng;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Ui;
import com.inverce.mod.core.functional.IPredicate;
import com.inverce.mod.integrations.support.annotations.IBinder;
import com.inverce.mod.integrations.support.recycler.MultiRecyclerAdapter;
import com.mevo.app.C0434R;
import com.mevo.app.constants.BikeTypes;
import com.mevo.app.data.bike_data_handlers.BikeDataHandlers;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.model.RentalItem.BikeStatus;
import com.mevo.app.data.model.RentalWithBattery;
import com.mevo.app.data.model.StartEndItem.StartComparator;
import com.mevo.app.tools.RepeatTask;
import com.mevo.app.tools.SpanUtils;
import com.mevo.app.tools.Tools;
import com.mevo.app.tools.formatters.BikeDataFormatter;
import com.mevo.app.tools.formatters.CurrencyFormatter;
import com.mevo.app.tools.formatters.Formatter;
import com.mevo.app.tools.location.LocationTool;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrentRentalsAdapter extends MultiRecyclerAdapter<Object> {
    private BikeDataFormatter bikeDataFormatter = new BikeDataFormatter(IM.context());
    private String currency;
    @Nullable
    private CurrentRentalsAdapterListener currentRentalsAdapterListener;
    private LatLng userLocation;

    public interface CurrentRentalsAdapterListener {
        void onClickBookingDetails(BookingItem bookingItem);

        void onClickParkBike(String str, String str2);

        void onClickRentDetails(RentalItem rentalItem);

        void onClickResumeRentingBike(String str, String str2);

        void onClickReturnBike(String str);

        void onListItemViewMeasured(int i);
    }

    public class BaseViewHolder extends ViewHolder implements IBinder<Object> {
        public TextView bikeInfo;
        public TextView bikeNameNumber;
        public TextView bikeStatusInfo;
        public TextView distanceFromBike;
        @Nullable
        public ImageView durationIcon;
        public LinearLayout horizontalLayout;
        public RepeatTask repeatTask;
        public View root;
        public ImageView statusIcon;
        public LinearLayout verticalLayout;

        public BaseViewHolder(View view) {
            super(view);
            this.root = view;
            this.statusIcon = (ImageView) view.findViewById(C0434R.id.list_item_current_rental_status);
            this.bikeNameNumber = (TextView) view.findViewById(C0434R.id.list_item_current_rental_name_number);
            this.distanceFromBike = (TextView) view.findViewById(C0434R.id.list_item_current_rental_distance);
            this.bikeInfo = (TextView) view.findViewById(C0434R.id.list_item_current_rental_bike_info);
            this.bikeStatusInfo = (TextView) view.findViewById(C0434R.id.list_item_current_rental_rent_status_info);
            this.verticalLayout = (LinearLayout) view.findViewById(C0434R.id.list_item_current_rental_vertical_layout);
            this.horizontalLayout = (LinearLayout) view.findViewById(C0434R.id.list_item_current_rental_horizontal_layout);
            this.durationIcon = (ImageView) view.findViewById(C0434R.id.list_item_current_rental_rent_duration_icon);
        }

        public void onBindViewHolder(Object obj, int i) {
            String stringBuilder;
            RentalWithBattery rentalWithBattery = (RentalWithBattery) obj;
            i = rentalWithBattery.rentalItem;
            Context context = this.root.getContext();
            SpanUtils on = SpanUtils.on(this.bikeNameNumber);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(CurrentRentalsAdapter.this.bikeDataFormatter.getBikeNameForBikeType(BikeTypes.getBikeGroupByBikeType(i.bikeType)));
            stringBuilder2.append(" ");
            on.normalText(stringBuilder2.toString()).boldText(i.bikeNumber).done();
            obj = rentalWithBattery.batteryLevel != null ? rentalWithBattery.batteryLevel.intValue() : -1;
            if (obj >= null) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(obj);
                stringBuilder3.append(Operation.MOD);
                stringBuilder = stringBuilder3.toString();
            } else {
                stringBuilder = Operation.MINUS;
            }
            String formatBikeRange = obj >= null ? Formatter.formatBikeRange(BikeDataHandlers.getBikeRangeMeters(obj)) : Operation.MINUS;
            SpanUtils.on(this.bikeInfo).normalText((int) C0434R.string.battery_smartbike).normalText(" ").coloredText(stringBuilder, CurrentRentalsAdapter.this.bikeDataFormatter.getColorResForBatteryPercent(obj, C0434R.color.textStationLightBlue)).normalText(", ").normalText(context.getString(C0434R.string.range_smartbike, new Object[]{formatBikeRange})).done();
            double calculateDistanceKm = LocationTool.calculateDistanceKm(i.getCurrentLatLng(), CurrentRentalsAdapter.this.userLocation);
            obj = this.distanceFromBike;
            Resources resources = IM.resources();
            Object[] objArr = new Object[1];
            objArr[0] = calculateDistanceKm > 0.0d ? Formatter.formatDistance((double) ((int) (calculateDistanceKm * 1000.0d))) : Operation.MINUS;
            obj.setText(resources.getString(C0434R.string.distance_smartbike, objArr));
            setBikeStatusInfoText(i, context);
            if (this.durationIcon != null) {
                this.durationIcon.setVisibility(0);
                obj = new CurrentRentalsAdapter$BaseViewHolder$$Lambda$0(this, i);
                this.durationIcon.setOnClickListener(obj);
                this.bikeStatusInfo.setOnClickListener(obj);
            } else {
                this.bikeStatusInfo.setOnClickListener(null);
            }
            if (this.repeatTask != null) {
                this.repeatTask.stop();
            }
            this.repeatTask = new RepeatTask(Tools.ifLessThanMinute(i.getDurationSeconds()) ? 1000 : Formatter.MINUTE, false, new CurrentRentalsAdapter$BaseViewHolder$$Lambda$1(this, i, context));
            this.repeatTask.start();
        }

        final /* synthetic */ void lambda$onBindViewHolder$120$CurrentRentalsAdapter$BaseViewHolder(RentalItem rentalItem, View view) {
            if (CurrentRentalsAdapter.this.currentRentalsAdapterListener != null) {
                CurrentRentalsAdapter.this.currentRentalsAdapterListener.onClickRentDetails(rentalItem);
            }
        }

        final /* synthetic */ void lambda$onBindViewHolder$121$CurrentRentalsAdapter$BaseViewHolder(RentalItem rentalItem, Context context) {
            setBikeStatusInfoText(rentalItem, context);
        }

        private void setBikeStatusInfoText(RentalItem rentalItem, Context context) {
            this.bikeStatusInfo.setText(context.getString(C0434R.string.bike_rent_time, new Object[]{Formatter.formatDurationRent(rentalItem.getDurationSeconds(), context)}));
            this.bikeStatusInfo.setVisibility(0);
        }
    }

    public class BookedViewHolder extends BaseViewHolder implements IBinder<Object> {
        public Button bookingDetailsButton;

        public BookedViewHolder(View view) {
            super(view);
            this.bookingDetailsButton = (Button) view.findViewById(C0434R.id.list_item_current_rental_details_button);
        }

        public void onBindViewHolder(Object obj, int i) {
            BookingItem bookingItem = (BookingItem) obj;
            Context context = this.root.getContext();
            Ui.visible(this.statusIcon, false);
            Ui.visible(this.bookingDetailsButton, true);
            this.bookingDetailsButton.setOnClickListener(new CurrentRentalsAdapter$BookedViewHolder$$Lambda$0(this, bookingItem));
            SpanUtils.on(this.bikeNameNumber).boldText(bookingItem.getPlaceName()).done();
            Ui.visible(this.distanceFromBike, false);
            SpanUtils.on(this.bikeInfo).normalText((int) C0434R.string.bikes_number).space().normalText(String.valueOf(bookingItem.getNumBikes())).done();
            setBikeStatusInfo(bookingItem, context);
            if (this.repeatTask != null) {
                this.repeatTask.stop();
            }
            this.repeatTask = new RepeatTask(1000, false, new CurrentRentalsAdapter$BookedViewHolder$$Lambda$1(this, bookingItem, context));
            this.repeatTask.start();
            CurrentRentalsAdapter.this.measureLayout(this, i);
        }

        /* renamed from: lambda$onBindViewHolder$118$CurrentRentalsAdapter$BookedViewHolder */
        final /* synthetic */ void m137x79bbc46a(BookingItem bookingItem, View view) {
            if (CurrentRentalsAdapter.this.currentRentalsAdapterListener != null) {
                CurrentRentalsAdapter.this.currentRentalsAdapterListener.onClickBookingDetails(bookingItem);
            }
        }

        /* renamed from: lambda$onBindViewHolder$119$CurrentRentalsAdapter$BookedViewHolder */
        final /* synthetic */ void m138xd0d9b549(BookingItem bookingItem, Context context) {
            setBikeStatusInfo(bookingItem, context);
        }

        private void setBikeStatusInfo(BookingItem bookingItem, Context context) {
            this.bikeStatusInfo.setText(context.getString(C0434R.string.bike_book_time, new Object[]{Formatter.formatDurationBooking(bookingItem.getTimeLeftToEndSeconds(), context)}));
            this.bikeStatusInfo.setVisibility(0);
        }
    }

    public class ParkedViewHolder extends BaseViewHolder implements IBinder<Object> {
        public Button resumeRentingButton;
        public Button resumeRentingButtonHorizontal;

        public ParkedViewHolder(View view) {
            super(view);
            this.resumeRentingButton = (Button) view.findViewById(C0434R.id.list_item_current_rental_resume_renting_button);
            this.resumeRentingButtonHorizontal = (Button) view.findViewById(C0434R.id.list_item_current_rental_resume_renting_button_horizontal);
        }

        public void onBindViewHolder(Object obj, int i) {
            super.onBindViewHolder(obj, i);
            obj = ((RentalWithBattery) obj).rentalItem;
            Ui.visible(this.resumeRentingButton, true);
            Ui.visible(this.resumeRentingButtonHorizontal, true);
            this.resumeRentingButton.setOnClickListener(new CurrentRentalsAdapter$ParkedViewHolder$$Lambda$0(this, obj));
            this.resumeRentingButtonHorizontal.setOnClickListener(new CurrentRentalsAdapter$ParkedViewHolder$$Lambda$1(this, obj));
            CurrentRentalsAdapter.this.setLayoutVisibilityAndMeasure(this, i);
        }

        /* renamed from: lambda$onBindViewHolder$116$CurrentRentalsAdapter$ParkedViewHolder */
        final /* synthetic */ void m139xdd13922d(RentalItem rentalItem, View view) {
            if (CurrentRentalsAdapter.this.currentRentalsAdapterListener != null) {
                CurrentRentalsAdapter.this.currentRentalsAdapterListener.onClickResumeRentingBike(rentalItem.bikeNumber, String.valueOf(rentalItem.id));
            }
        }

        /* renamed from: lambda$onBindViewHolder$117$CurrentRentalsAdapter$ParkedViewHolder */
        final /* synthetic */ void m140x3431830c(RentalItem rentalItem, View view) {
            if (CurrentRentalsAdapter.this.currentRentalsAdapterListener != null) {
                CurrentRentalsAdapter.this.currentRentalsAdapterListener.onClickResumeRentingBike(rentalItem.bikeNumber, String.valueOf(rentalItem.id));
            }
        }
    }

    public class RentedViewHolder extends BaseViewHolder implements IBinder<Object> {
        public Button parkButton;
        public Button parkButtonHorizontal;
        public Button returnButton;
        public Button returnButtonHorizontal;

        public RentedViewHolder(View view) {
            super(view);
            this.parkButton = (Button) view.findViewById(C0434R.id.list_item_current_rental_park_button);
            this.returnButton = (Button) view.findViewById(C0434R.id.list_item_current_rental_return_button);
            this.parkButtonHorizontal = (Button) view.findViewById(C0434R.id.list_item_current_rental_park_button_horizontal);
            this.returnButtonHorizontal = (Button) view.findViewById(C0434R.id.list_item_current_rental_return_button_horizontal);
        }

        public void onBindViewHolder(Object obj, int i) {
            super.onBindViewHolder(obj, i);
            obj = ((RentalWithBattery) obj).rentalItem;
            Ui.visible(this.parkButton, true);
            Ui.visible(this.returnButton, true);
            Ui.visible(this.parkButtonHorizontal, true);
            Ui.visible(this.returnButtonHorizontal, true);
            this.parkButton.setOnClickListener(new CurrentRentalsAdapter$RentedViewHolder$$Lambda$0(this, obj));
            this.parkButtonHorizontal.setOnClickListener(new CurrentRentalsAdapter$RentedViewHolder$$Lambda$1(this, obj));
            this.returnButton.setOnClickListener(new CurrentRentalsAdapter$RentedViewHolder$$Lambda$2(this, obj));
            this.returnButtonHorizontal.setOnClickListener(new CurrentRentalsAdapter$RentedViewHolder$$Lambda$3(this, obj));
            CurrentRentalsAdapter.this.setLayoutVisibilityAndMeasure(this, i);
        }

        /* renamed from: lambda$onBindViewHolder$111$CurrentRentalsAdapter$RentedViewHolder */
        final /* synthetic */ void m141x2f3356a1(RentalItem rentalItem, View view) {
            if (CurrentRentalsAdapter.this.currentRentalsAdapterListener != null) {
                CurrentRentalsAdapter.this.currentRentalsAdapterListener.onClickParkBike(rentalItem.bikeNumber, String.valueOf(rentalItem.id));
            }
        }

        /* renamed from: lambda$onBindViewHolder$112$CurrentRentalsAdapter$RentedViewHolder */
        final /* synthetic */ void m142x86514780(RentalItem rentalItem, View view) {
            if (CurrentRentalsAdapter.this.currentRentalsAdapterListener != null) {
                CurrentRentalsAdapter.this.currentRentalsAdapterListener.onClickParkBike(rentalItem.bikeNumber, String.valueOf(rentalItem.id));
            }
        }

        /* renamed from: lambda$onBindViewHolder$113$CurrentRentalsAdapter$RentedViewHolder */
        final /* synthetic */ void m143xdd6f385f(RentalItem rentalItem, View view) {
            if (CurrentRentalsAdapter.this.currentRentalsAdapterListener != null) {
                CurrentRentalsAdapter.this.currentRentalsAdapterListener.onClickReturnBike(rentalItem.bikeNumber);
            }
        }

        /* renamed from: lambda$onBindViewHolder$114$CurrentRentalsAdapter$RentedViewHolder */
        final /* synthetic */ void m144x348d293e(RentalItem rentalItem, View view) {
            if (CurrentRentalsAdapter.this.currentRentalsAdapterListener != null) {
                CurrentRentalsAdapter.this.currentRentalsAdapterListener.onClickReturnBike(rentalItem.bikeNumber);
            }
        }
    }

    public CurrentRentalsAdapter(LatLng latLng) {
        this.userLocation = latLng;
        register((IPredicate) CurrentRentalsAdapter$$Lambda$0.$instance, new CurrentRentalsAdapter$$Lambda$1(this), (int) C0434R.layout.list_item_current_rental);
        register((IPredicate) CurrentRentalsAdapter$$Lambda$2.$instance, new CurrentRentalsAdapter$$Lambda$3(this), (int) C0434R.layout.list_item_current_rental_parked);
        register((IPredicate) CurrentRentalsAdapter$$Lambda$4.$instance, new CurrentRentalsAdapter$$Lambda$5(this), (int) C0434R.layout.list_item_current_rental_booking);
        register((IPredicate) CurrentRentalsAdapter$$Lambda$6.$instance, new CurrentRentalsAdapter$$Lambda$7(this), (int) C0434R.layout.list_item_current_rental);
    }

    static final /* synthetic */ boolean lambda$new$103$CurrentRentalsAdapter(Object obj) {
        return ((obj instanceof RentalWithBattery) && ((RentalWithBattery) obj).rentalItem.getBikeStatus() == BikeStatus.RENTED) ? true : null;
    }

    final /* synthetic */ RentedViewHolder lambda$new$104$CurrentRentalsAdapter(View view) {
        return new RentedViewHolder(view);
    }

    static final /* synthetic */ boolean lambda$new$105$CurrentRentalsAdapter(Object obj) {
        return ((obj instanceof RentalWithBattery) && ((RentalWithBattery) obj).rentalItem.getBikeStatus() == BikeStatus.PARKED) ? true : null;
    }

    final /* synthetic */ ParkedViewHolder lambda$new$106$CurrentRentalsAdapter(View view) {
        return new ParkedViewHolder(view);
    }

    final /* synthetic */ BookedViewHolder lambda$new$108$CurrentRentalsAdapter(View view) {
        return new BookedViewHolder(view);
    }

    final /* synthetic */ RentedViewHolder lambda$new$110$CurrentRentalsAdapter(View view) {
        return new RentedViewHolder(view);
    }

    public void setData(List<RentalWithBattery> list, List<BookingItem> list2, String str) {
        if (list == null) {
            list = new ArrayList();
        }
        if (list2 == null) {
            list2 = new ArrayList();
        }
        this.currency = CurrencyFormatter.formatCurrencyAbbreviation(str);
        str = new ArrayList();
        str.addAll(list2);
        str.addAll(list);
        Collections.sort(str, new StartComparator());
        setData(str);
        if (list.isEmpty() != null && list2.isEmpty() != null && this.currentRentalsAdapterListener != null) {
            this.currentRentalsAdapterListener.onListItemViewMeasured(null);
        }
    }

    public CurrentRentalsAdapter setUserLocation(LatLng latLng) {
        this.userLocation = latLng;
        return this;
    }

    private void setLayoutVisibilityAndMeasure(final BaseViewHolder baseViewHolder, final int i) {
        baseViewHolder.verticalLayout.setVisibility(4);
        baseViewHolder.bikeStatusInfo.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                baseViewHolder.bikeStatusInfo.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (baseViewHolder.bikeStatusInfo.getLineCount() <= 1) {
                    if (baseViewHolder.bikeInfo.getLineCount() <= 1) {
                        baseViewHolder.horizontalLayout.setVisibility(8);
                        baseViewHolder.verticalLayout.setVisibility(0);
                        CurrentRentalsAdapter.this.measureLayout(baseViewHolder, i);
                    }
                }
                baseViewHolder.verticalLayout.setVisibility(8);
                baseViewHolder.horizontalLayout.setVisibility(0);
                CurrentRentalsAdapter.this.measureLayout(baseViewHolder, i);
            }
        });
    }

    public void onViewRecycled(@NonNull ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        if (viewHolder instanceof BaseViewHolder) {
            BaseViewHolder baseViewHolder = (BaseViewHolder) viewHolder;
            if (baseViewHolder.repeatTask != null) {
                baseViewHolder.repeatTask.stop();
                baseViewHolder.repeatTask.destroy();
            }
        }
    }

    public void stopAllTimers(RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewHolder childViewHolder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
            if (childViewHolder instanceof BaseViewHolder) {
                BaseViewHolder baseViewHolder = (BaseViewHolder) childViewHolder;
                if (baseViewHolder.repeatTask != null) {
                    baseViewHolder.repeatTask.stop();
                    baseViewHolder.repeatTask = null;
                }
            }
        }
    }

    public CurrentRentalsAdapter setCurrentRentalsAdapterListener(@Nullable CurrentRentalsAdapterListener currentRentalsAdapterListener) {
        this.currentRentalsAdapterListener = currentRentalsAdapterListener;
        return this;
    }

    private void measureLayout(BaseViewHolder baseViewHolder, int i) {
        if (i == 0) {
            baseViewHolder = baseViewHolder.root;
            baseViewHolder.post(new CurrentRentalsAdapter$$Lambda$8(this, baseViewHolder));
        }
    }

    final /* synthetic */ void lambda$measureLayout$115$CurrentRentalsAdapter(View view) {
        view = view.getHeight();
        if (this.currentRentalsAdapterListener != null) {
            this.currentRentalsAdapterListener.onListItemViewMeasured(view);
        }
    }
}
