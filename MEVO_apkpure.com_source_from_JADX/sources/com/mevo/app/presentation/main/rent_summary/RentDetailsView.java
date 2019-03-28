package com.mevo.app.presentation.main.rent_summary;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.constants.BikeTypes;
import com.mevo.app.data.model.RentalDistance;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.tools.SpanUtils;
import com.mevo.app.tools.Tools;
import com.mevo.app.tools.Utils;
import com.mevo.app.tools.formatters.Formatter;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public class RentDetailsView extends LinearLayout {
    private static final double AVERAGE_SPEED_KM_H = 12.0d;
    private static final double AVERAGE_WEIGHT_KG = 80.0d;
    private TextView caloriesText;
    private TextView coSavedText;
    private TextView dateAndBikeText;
    private TextView rentCostText;
    private TextView rentDistanceText;
    private TextView rentEndPointText;
    private TextView rentStartPointText;
    private TextView rentTimeText;

    private double calcualteCalories(double d) {
        return (d / AVERAGE_SPEED_KM_H) * 240.0d;
    }

    private double calcualteCo2SavedGrams(double d) {
        return d * 160.0d;
    }

    public RentDetailsView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public RentDetailsView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public RentDetailsView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    @RequiresApi(api = 21)
    public RentDetailsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, C0434R.layout.view_rent_details, this);
        findViews();
    }

    private void findViews() {
        this.dateAndBikeText = (TextView) findViewById(C0434R.id.view_rent_date_and_bike_details);
        this.rentTimeText = (TextView) findViewById(C0434R.id.view_rent_details_rent_time);
        this.rentCostText = (TextView) findViewById(C0434R.id.view_rent_details_rent_cost);
        this.caloriesText = (TextView) findViewById(C0434R.id.view_rent_details_calories);
        this.coSavedText = (TextView) findViewById(C0434R.id.view_rent_details_saved_co2);
        this.rentDistanceText = (TextView) findViewById(C0434R.id.view_rent_details_distance);
        this.rentStartPointText = (TextView) findViewById(C0434R.id.view_rent_details_rent_start_point_details);
        this.rentEndPointText = (TextView) findViewById(C0434R.id.view_rent_details_rent_end_point_details);
    }

    public void setData(RentalItem rentalItem, @Nullable RentalDistance rentalDistance) {
        StringBuilder stringBuilder;
        SpanUtils.on(this.dateAndBikeText).normalText(Formatter.formatRentStartEndDatesWithoutHours(rentalItem)).normalText(",").space().normalText(Tools.getBikesType(BikeTypes.getBikeGroupByBikeType(rentalItem.bikeType))).space().normalText(Utils.emptyIfNull(rentalItem.bikeNumber)).done();
        String str = rentalItem.startPlaceName != null ? rentalItem.startPlaceName : "";
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(getContext().getString(C0434R.string.bike_rental));
        stringBuilder2.append(" ");
        stringBuilder2.append(Formatter.formatHourMinute(rentalItem.startTimestampSeconds * 1000));
        stringBuilder2.append("\n");
        if (str.contains(rentalItem.bikeNumber)) {
            str = Formatter.formatLatLngToPlace(rentalItem.getStartLatLng(), 4);
        }
        stringBuilder2.append(str);
        this.rentStartPointText.setText(stringBuilder2.toString());
        CharSequence charSequence = Operation.MINUS;
        if (rentalItem.endPlaceName != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(getContext().getString(C0434R.string.bike_return));
            stringBuilder.append(" ");
            stringBuilder.append(Formatter.formatHourMinute(rentalItem.endTimestampSeconds * 1000));
            stringBuilder.append("\n");
            stringBuilder.append(rentalItem.endPlaceName.contains(rentalItem.bikeNumber) ? Formatter.formatLatLngToPlace(rentalItem.getEndLatLng(), 4) : rentalItem.endPlaceName);
            charSequence = stringBuilder.toString();
        }
        this.rentEndPointText.setText(charSequence);
        this.rentTimeText.setText(Formatter.formatDurationRent(rentalItem.endTimestampSeconds - rentalItem.startTimestampSeconds, IM.context()));
        stringBuilder = new StringBuilder();
        stringBuilder.append(Formatter.formatMoney(rentalItem));
        stringBuilder.append(" ");
        stringBuilder.append(IM.resources().getString(C0434R.string.pln));
        this.rentCostText.setText(stringBuilder.toString());
        rentalItem = Operation.MINUS;
        charSequence = Operation.MINUS;
        if (rentalDistance != null && rentalDistance.getDistanceMeters() > 0.0d) {
            rentalItem = new StringBuilder();
            rentalItem.append(Formatter.formatStandardDecimal(calcualteCalories(rentalDistance.getDistanceMeters() / 1000.0d)));
            rentalItem.append(" ");
            rentalItem.append(IM.context().getString(C0434R.string.kcal));
            rentalItem = rentalItem.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(getContext().getString(C0434R.string.minus));
            stringBuilder.append(Formatter.formatStandardDecimal(calcualteCo2SavedGrams(rentalDistance.getDistanceMeters() / 1000.0d)));
            stringBuilder.append(" ");
            stringBuilder.append(getContext().getString(C0434R.string.g));
            charSequence = stringBuilder.toString();
        }
        this.caloriesText.setText(rentalItem);
        this.coSavedText.setText(charSequence);
        rentalItem = this.rentDistanceText;
        Context context = getContext();
        Object[] objArr = new Object[1];
        objArr[0] = rentalDistance != null ? Formatter.formatDistance(rentalDistance.getDistanceMeters()) : Operation.MINUS;
        rentalItem.setText(context.getString(C0434R.string.rent_history_distance, objArr));
    }
}
