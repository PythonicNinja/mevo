package com.mevo.app.presentation.main.return_bike;

import android.location.Location;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.TextView;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.data.model.Place;
import com.mevo.app.data.model.Place.Sort;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.Utils;
import com.mevo.app.tools.formatters.Formatter;
import com.mevo.app.tools.location.LocationTool;
import java.util.ArrayList;
import java.util.List;

public class ReturnStationsAdapter extends BaseAdapter implements Filterable {
    private List<Place> data = new ArrayList();
    private Filter filter;
    private String lastFilter = "{}";
    private Place selectedPlace;
    private List<Place> visibleElements = new ArrayList();

    /* renamed from: com.mevo.app.presentation.main.return_bike.ReturnStationsAdapter$1 */
    class C04501 extends Filter {
        C04501() {
        }

        protected synchronized FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults;
            charSequence = LocationTool.get().getLastKnownLocation();
            filterResults = new FilterResults();
            String str = "performFiltering";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(charSequence == null);
            Log.m90e(str, stringBuilder.toString());
            if (charSequence != null) {
                charSequence = (List) Stream.of(ReturnStationsAdapter.this.data).filter(new ReturnStationsAdapter$1$$Lambda$0(charSequence)).sorted(Sort.byDistanceTo(charSequence)).collect(Collectors.toList());
            } else {
                charSequence = new ArrayList();
            }
            filterResults.values = charSequence;
            filterResults.count = charSequence.size();
            return filterResults;
        }

        static final /* synthetic */ boolean lambda$performFiltering$248$ReturnStationsAdapter$1(Location location, Place place) {
            return LocationTool.calculateDistanceMeters(place.getPosition(), location) < Cfg.get().returnMaxDistanceMeters() ? true : null;
        }

        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ReturnStationsAdapter.this.lastFilter = map(charSequence);
            List list = (List) filterResults.values;
            if (Utils.areListEqual(list, ReturnStationsAdapter.this.visibleElements) == null) {
                ReturnStationsAdapter.this.visibleElements = list;
                ReturnStationsAdapter.this.notifyDataSetChanged();
            }
        }

        private String map(CharSequence charSequence) {
            return charSequence == null ? "{}" : charSequence.toString();
        }
    }

    private static class ViewHolder {
        TextView bikeDistance;
        TextView bikePlace;
        View elementBg;
        View root;

        public ViewHolder(View view) {
            this.root = view;
            this.bikePlace = (TextView) view.findViewById(C0434R.id.list_item_find_bike_place);
            this.bikeDistance = (TextView) view.findViewById(C0434R.id.list_item_find_bike_distance);
            this.elementBg = view.findViewById(C0434R.id.element_bg);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void setData(List<Place> list) {
        this.data = list;
        getFilter().filter(this.lastFilter);
    }

    public List<Place> getVisibleElements() {
        return this.visibleElements;
    }

    public List<Place> getData() {
        return this.data;
    }

    public void trySelectStation(String str) {
        Place place = this.selectedPlace;
        if (TextUtils.isEmpty(str) || getVisibleElements() == null) {
            this.selectedPlace = null;
        }
        Object obj = null;
        for (Place place2 : getVisibleElements()) {
            if (place2.getNumber() != null && place2.getNumber().equalsIgnoreCase(str)) {
                this.selectedPlace = place2;
                obj = 1;
                break;
            }
        }
        if (obj == null) {
            this.selectedPlace = null;
        }
        if (place != this.selectedPlace) {
            notifyDataSetChanged();
        }
    }

    public Place getSelected() {
        return this.selectedPlace;
    }

    public int getCount() {
        return this.visibleElements.size();
    }

    public Place getItem(int i) {
        return (Place) this.visibleElements.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        double calculateDistanceMeters;
        i = getItem(i);
        if (view != null) {
            if (view.getTag() instanceof ViewHolder) {
                viewGroup = (ViewHolder) view.getTag();
                viewGroup.bikePlace.setText(i.getName());
                calculateDistanceMeters = LocationTool.calculateDistanceMeters(i.getPosition(), LocationTool.get().getLastKnownLocation());
                if (calculateDistanceMeters != -1.0d) {
                    viewGroup.bikeDistance.setText("");
                } else {
                    viewGroup.bikeDistance.setText(Formatter.formatDistance((double) ((int) calculateDistanceMeters)));
                }
                if (i == this.selectedPlace) {
                    viewGroup.elementBg.setBackgroundResource(C0434R.color.grey);
                    viewGroup.bikeDistance.setTextColor(ContextCompat.getColor(IM.context(), C0434R.color.textBasic));
                    viewGroup.bikePlace.setTextColor(ContextCompat.getColor(IM.context(), C0434R.color.textBasic));
                } else {
                    viewGroup.elementBg.setBackgroundResource(C0434R.color.colorPrimary);
                    viewGroup.bikeDistance.setTextColor(ContextCompat.getColor(IM.context(), C0434R.color.white));
                    viewGroup.bikePlace.setTextColor(ContextCompat.getColor(IM.context(), C0434R.color.white));
                }
                return view;
            }
        }
        view = LayoutInflater.from(viewGroup.getContext()).inflate(C0434R.layout.list_item_find_bike, viewGroup, false);
        viewGroup = new ViewHolder(view);
        view.setTag(viewGroup);
        viewGroup.bikePlace.setText(i.getName());
        calculateDistanceMeters = LocationTool.calculateDistanceMeters(i.getPosition(), LocationTool.get().getLastKnownLocation());
        if (calculateDistanceMeters != -1.0d) {
            viewGroup.bikeDistance.setText(Formatter.formatDistance((double) ((int) calculateDistanceMeters)));
        } else {
            viewGroup.bikeDistance.setText("");
        }
        if (i == this.selectedPlace) {
            viewGroup.elementBg.setBackgroundResource(C0434R.color.colorPrimary);
            viewGroup.bikeDistance.setTextColor(ContextCompat.getColor(IM.context(), C0434R.color.white));
            viewGroup.bikePlace.setTextColor(ContextCompat.getColor(IM.context(), C0434R.color.white));
        } else {
            viewGroup.elementBg.setBackgroundResource(C0434R.color.grey);
            viewGroup.bikeDistance.setTextColor(ContextCompat.getColor(IM.context(), C0434R.color.textBasic));
            viewGroup.bikePlace.setTextColor(ContextCompat.getColor(IM.context(), C0434R.color.textBasic));
        }
        return view;
    }

    public Filter getFilter() {
        if (this.filter != null) {
            return this.filter;
        }
        Filter c04501 = new C04501();
        this.filter = c04501;
        return c04501;
    }
}
