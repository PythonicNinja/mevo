package com.mevo.app.tools;

import com.mevo.app.data.model.Place;
import java.util.Comparator;

public class PlacesComparator implements Comparator<Place> {
    private Place currentPlace;

    public PlacesComparator(Place place) {
        this.currentPlace = place;
    }

    public int compare(Place place, Place place2) {
        double lat = place.getLat();
        double lng = place.getLng();
        double lat2 = place2.getLat();
        double lng2 = place2.getLng();
        return (int) (distance(this.currentPlace.getLat(), this.currentPlace.getLng(), lat, lng) - distance(this.currentPlace.getLat(), this.currentPlace.getLng(), lat2, lng2));
    }

    public double distance(double d, double d2, double d3, double d4) {
        return (Math.asin(Math.sqrt(Math.pow(Math.sin((d3 - d) / 2.0d), 2.0d) + ((Math.cos(d) * Math.cos(d3)) * Math.pow(Math.sin((d4 - d2) / 2.0d), 2.0d)))) * 2.0d) * 6378137.0d;
    }
}
