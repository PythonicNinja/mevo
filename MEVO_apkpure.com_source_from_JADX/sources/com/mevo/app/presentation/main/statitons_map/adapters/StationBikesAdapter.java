package com.mevo.app.presentation.main.statitons_map.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.model.LatLng;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.constants.BikeTypes;
import com.mevo.app.data.bike_data_handlers.BikeDataHandlers;
import com.mevo.app.data.model.BikeJson;
import com.mevo.app.tools.SpanUtils;
import com.mevo.app.tools.formatters.BikeDataFormatter;
import com.mevo.app.tools.formatters.Formatter;
import com.mevo.app.tools.location.LocationTool;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.util.ArrayList;
import java.util.List;

public class StationBikesAdapter extends Adapter<ViewHolder> {
    private BikeDataFormatter bikeDataFormatter = new BikeDataFormatter(IM.context());
    private List<BikeJson> data;
    private ReservationListener reservationListener;
    private LatLng stationLocation;
    private double stationUserDistanceKm;
    private LatLng userLocation;

    public interface ReservationListener {
        void onTryBookBike(String str);
    }

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        TextView bikeInfo;
        TextView bikeNameNumber;
        ImageView bikeStatusIcon;
        TextView distanceFromBike;

        public ViewHolder(View view) {
            super(view);
            this.bikeStatusIcon = (ImageView) view.findViewById(C0434R.id.list_item_station_bike_status);
            this.bikeNameNumber = (TextView) view.findViewById(C0434R.id.list_item_station_bike_name_number);
            this.distanceFromBike = (TextView) view.findViewById(C0434R.id.list_item_station_bike_distance);
            this.bikeInfo = (TextView) view.findViewById(C0434R.id.list_item_station_bike_info);
        }
    }

    public StationBikesAdapter(List<BikeJson> list, LatLng latLng, LatLng latLng2, ReservationListener reservationListener) {
        this.userLocation = latLng;
        this.stationLocation = latLng2;
        this.reservationListener = reservationListener;
        setData(list, latLng, latLng2);
    }

    public void setData(List<BikeJson> list, LatLng latLng, LatLng latLng2) {
        if (list == null) {
            list = new ArrayList();
        }
        this.data = new ArrayList(list);
        this.userLocation = latLng;
        this.stationLocation = latLng2;
        this.stationUserDistanceKm = LocationTool.calculateDistanceKm(latLng2, latLng);
        notifyDataSetChanged();
    }

    public StationBikesAdapter setUserLocation(LatLng latLng) {
        this.userLocation = latLng;
        this.stationUserDistanceKm = LocationTool.calculateDistanceKm(this.stationLocation, latLng);
        notifyDataSetChanged();
        return this;
    }

    public StationBikesAdapter setReservationListener(ReservationListener reservationListener) {
        this.reservationListener = reservationListener;
        return this;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0434R.layout.list_item_station_bike_x, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String stringBuilder;
        BikeJson bikeJson = (BikeJson) this.data.get(i);
        Context context = viewHolder.bikeInfo.getContext();
        SpanUtils on = SpanUtils.on(viewHolder.bikeNameNumber);
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(this.bikeDataFormatter.getBikeNameForBikeType(BikeTypes.getBikeGroupByBikeType(bikeJson.getType())));
        stringBuilder2.append(" ");
        on.normalText(stringBuilder2.toString()).boldText(bikeJson.getNumber()).done();
        i = bikeJson.getPedelecBatteryPercent() != null ? bikeJson.getPedelecBatteryPercent().intValue() : -1;
        if (i >= 0) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(i);
            stringBuilder3.append(Operation.MOD);
            stringBuilder = stringBuilder3.toString();
        } else {
            stringBuilder = Operation.MINUS;
        }
        String formatBikeRange = i >= 0 ? Formatter.formatBikeRange(BikeDataHandlers.getBikeRangeMeters(i)) : Operation.MINUS;
        SpanUtils.on(viewHolder.bikeInfo).normalText((int) C0434R.string.battery_smartbike).normalText(" ").coloredText(stringBuilder, this.bikeDataFormatter.getColorResForBatteryPercent(i, C0434R.color.textStationLightBlue)).normalText(", ").normalText(context.getString(C0434R.string.range_smartbike, new Object[]{formatBikeRange})).done();
        viewHolder = viewHolder.distanceFromBike;
        i = IM.resources();
        Object[] objArr = new Object[1];
        objArr[0] = this.stationUserDistanceKm > 0.0d ? Formatter.formatDistance((double) ((int) (this.stationUserDistanceKm * 1000.0d))) : Operation.MINUS;
        viewHolder.setText(i.getString(C0434R.string.distance_smartbike, objArr));
    }

    public int getItemCount() {
        return this.data.size();
    }
}
