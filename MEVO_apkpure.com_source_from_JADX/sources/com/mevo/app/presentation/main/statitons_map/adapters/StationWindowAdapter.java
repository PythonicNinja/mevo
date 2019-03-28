package com.mevo.app.presentation.main.statitons_map.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.constants.BikeTypes;
import com.mevo.app.constants.StationTypes;
import com.mevo.app.data.bike_data_handlers.BikeDataHandlers;
import com.mevo.app.data.model.BikeJson;
import com.mevo.app.data.model.MapStationData;
import com.mevo.app.data.model.Place;
import com.mevo.app.tools.SpanUtils;
import com.mevo.app.tools.Utils;
import com.mevo.app.tools.formatters.BikeDataFormatter;
import com.mevo.app.tools.formatters.Formatter;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public class StationWindowAdapter implements InfoWindowAdapter {
    private BikeDataFormatter bikeDataFormatter;
    private Context context;
    private LayoutInflater inflater;

    private class ViewHolder {
        private TextView stationDescriptionText;
        private TextView stationNameText;
        private TextView walkTimeText;

        private ViewHolder(View view) {
            this.stationNameText = (TextView) view.findViewById(C0434R.id.view_map_station_name);
            this.stationDescriptionText = (TextView) view.findViewById(C0434R.id.view_map_station_description);
            this.walkTimeText = (TextView) view.findViewById(C0434R.id.view_map_station_walk_time);
        }
    }

    private class ViewHolderOneBike {
        private TextView stationDescriptionText;
        private TextView stationNameText;
        private TextView status;
        private TextView walkTimeText;

        private ViewHolderOneBike(View view) {
            this.stationNameText = (TextView) view.findViewById(C0434R.id.view_smartbike_id);
            this.stationDescriptionText = (TextView) view.findViewById(C0434R.id.view_map_station_description);
            this.status = (TextView) view.findViewById(C0434R.id.view_smartbike_status_text);
            this.walkTimeText = (TextView) view.findViewById(C0434R.id.view_map_station_walk_time);
        }
    }

    public View getInfoContents(Marker marker) {
        return null;
    }

    public StationWindowAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.bikeDataFormatter = new BikeDataFormatter(context);
    }

    public View getInfoWindow(Marker marker) {
        if (!(marker.getTag() instanceof MapStationData)) {
            return null;
        }
        MapStationData mapStationData = (MapStationData) marker.getTag();
        if (StationTypes.isOneBikeType(mapStationData.place)) {
            return createSingleBikeView(mapStationData);
        }
        return createStationView(mapStationData);
    }

    private View createSingleBikeView(MapStationData mapStationData) {
        String stringBuilder;
        View inflate = this.inflater.inflate(C0434R.layout.view_smartbike_rent, null);
        ViewHolderOneBike viewHolderOneBike = new ViewHolderOneBike(inflate);
        int intValue = (mapStationData.stationJson == null || mapStationData.stationJson.getBikes() == null || mapStationData.stationJson.getBikes().isEmpty() || ((BikeJson) mapStationData.stationJson.getBikes().get(0)).getPedelecBatteryPercent() == null) ? -1 : ((BikeJson) mapStationData.stationJson.getBikes().get(0)).getPedelecBatteryPercent().intValue();
        if (intValue >= 0) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(intValue);
            stringBuilder2.append(Operation.MOD);
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder = Operation.MINUS;
        }
        String formatBikeRange = intValue >= 0 ? Formatter.formatBikeRange(BikeDataHandlers.getBikeRangeMeters(intValue)) : Operation.MINUS;
        SpanUtils.on(viewHolderOneBike.stationDescriptionText).normalText((int) C0434R.string.battery_smartbike).normalText(" ").coloredText(stringBuilder, this.bikeDataFormatter.getColorResForBatteryPercent(intValue, C0434R.color.textStationLightBlue)).normalText(", ").normalText(this.context.getString(C0434R.string.range_smartbike, new Object[]{formatBikeRange})).done();
        viewHolderOneBike.status.setText(IM.resources().getString(C0434R.string.active));
        SpanUtils on = SpanUtils.on(viewHolderOneBike.stationNameText);
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(this.bikeDataFormatter.getBikeNameForBikeType(BikeTypes.getBikeGroupByBikeType(null)));
        stringBuilder3.append(" ");
        on.normalText(stringBuilder3.toString()).boldText(mapStationData.place.getBikeNumbers()).done();
        viewHolderOneBike.walkTimeText.setText(getWalkTimeString(mapStationData.walkToStationDurationSeconds, this.context));
        return inflate;
    }

    private View createStationView(MapStationData mapStationData) {
        View inflate = this.inflater.inflate(C0434R.layout.view_station_map, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        viewHolder.stationNameText.setText(Utils.emptyIfNull(mapStationData.place.getName()));
        viewHolder.stationDescriptionText.setText(Utils.emptyIfNull(createStationDescription(mapStationData.place)));
        viewHolder.walkTimeText.setText(getWalkTimeString(mapStationData.walkToStationDurationSeconds, this.context));
        return inflate;
    }

    private String getWalkTimeString(@Nullable Long l, Context context) {
        if (l == null) {
            return context.getString(C0434R.string.walking_no_time);
        }
        return context.getString(C0434R.string.walking_time, new Object[]{Integer.valueOf((int) (l.longValue() / 60))});
    }

    private String createStationDescription(Place place) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.context.getString(C0434R.string.station));
        stringBuilder.append(": ");
        stringBuilder.append(Utils.emptyIfNull(place.getNumber()));
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append(", ");
        stringBuilder2 = stringBuilder3.toString();
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append(this.context.getString(C0434R.string.bikes_num));
        stringBuilder3.append(": ");
        stringBuilder3.append(Utils.emptyIfNull(place.getBikesNum()));
        return stringBuilder3.toString();
    }
}
