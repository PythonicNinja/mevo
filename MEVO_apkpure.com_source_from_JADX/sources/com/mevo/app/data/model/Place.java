package com.mevo.app.data.model;

import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;
import com.mevo.app.constants.BikeTypes.BikeGroup;
import java.util.Comparator;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

public class Place implements ClusterItem {
    @Attribute(name = "bike_numbers", required = false)
    private String bikeNumbers;
    @Attribute(name = "bike_types", required = false)
    private String bikeTypes;
    @ElementList(name = "bikes", required = false)
    private List<BikeXml> bikes;
    @Attribute(name = "bikes", required = false)
    private String bikesNum;
    private int bikesNumInt;
    @Attribute(name = "booked_bikes", required = false)
    private int bookedBikes;
    @Attribute(name = "free_racks", required = false)
    private int freeBikeRacks;
    @Attribute(name = "spot", required = false)
    private int isSpot;
    @Attribute(name = "lat", required = false)
    private double lat;
    @Attribute(name = "lng", required = false)
    private double lng;
    @Attribute(name = "name", required = false)
    private String name;
    @Attribute(name = "number", required = false)
    private String number;
    @Attribute(name = "place_type", required = false)
    private int placeType;
    private transient LatLng position;
    @Attribute(name = "bike_racks", required = false)
    private int totalBikeRacks;
    @Attribute(name = "uid", required = false)
    private int uid;

    public static class Sort {
        public static Comparator<Place> byName() {
            return Place$Sort$$Lambda$0.$instance;
        }

        static final /* synthetic */ int lambda$byName$0$Place$Sort(Place place, Place place2) {
            place = place.name;
            place2 = place2.name;
            if (place == null) {
                return place2 == null ? null : 1;
            } else {
                return place.compareTo(place2);
            }
        }

        public static Comparator<Place> byDistanceTo(Location location) {
            return new Place$Sort$$Lambda$1(location);
        }
    }

    public Place() {
        this.bikesNumInt = -1;
    }

    public Place(Place place) {
        this();
        this.lat = place.getLat();
        this.lng = place.getLng();
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int i) {
        this.uid = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double d) {
        this.lng = d;
    }

    public int isSpot() {
        return this.isSpot;
    }

    public void setSpot(int i) {
        this.isSpot = i;
    }

    public String getBikesNum() {
        return this.bikesNum;
    }

    public int getBikesNumInt() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = r3.bikesNumInt;
        r1 = -1;
        if (r0 != r1) goto L_0x0019;
    L_0x0005:
        r0 = r3.bikesNum;	 Catch:{ Exception -> 0x0016 }
        r1 = "\\D";	 Catch:{ Exception -> 0x0016 }
        r2 = "";	 Catch:{ Exception -> 0x0016 }
        r0 = r0.replaceAll(r1, r2);	 Catch:{ Exception -> 0x0016 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0016 }
        r3.bikesNumInt = r0;	 Catch:{ Exception -> 0x0016 }
        goto L_0x0019;
    L_0x0016:
        r0 = 0;
        r3.bikesNumInt = r0;
    L_0x0019:
        r0 = r3.bikesNumInt;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.model.Place.getBikesNumInt():int");
    }

    public int getBikesNumAvailableToRent() {
        int bikesNumInt = getBikesNumInt() - getBookedBikes();
        return bikesNumInt < 0 ? 0 : bikesNumInt;
    }

    public void setBikesNum(String str) {
        this.bikesNum = str;
    }

    public String getBikeNumbers() {
        return this.bikeNumbers;
    }

    public void setBikeNumbers(String str) {
        this.bikeNumbers = str;
    }

    public String getBikeTypes() {
        return this.bikeTypes;
    }

    public void setBikeTypes(String str) {
        this.bikeTypes = str;
    }

    public int getTotalBikeRacks() {
        return this.totalBikeRacks;
    }

    public void setTotalBikeRacks(int i) {
        this.totalBikeRacks = i;
    }

    public List<BikeXml> getBikes() {
        return this.bikes;
    }

    public Place setPlaceType(int i) {
        this.placeType = i;
        return this;
    }

    public int getPlaceType() {
        return this.placeType;
    }

    public int getFreeBikeRacks() {
        if (this.freeBikeRacks == 0) {
            this.freeBikeRacks = getTotalBikeRacks() - getBikesNumInt();
            if (this.freeBikeRacks < 0) {
                this.freeBikeRacks = 0;
            }
        }
        return this.freeBikeRacks;
    }

    public int getBookedBikes() {
        return this.bookedBikes;
    }

    public Place setBookedBikes(int i) {
        this.bookedBikes = i;
        return this;
    }

    public void setFreeBikeRacks(int i) {
        this.freeBikeRacks = i;
    }

    public BikeGroup getType() {
        return BikeGroup.STANDARD;
    }

    public java.lang.Integer parseToInt(java.lang.String r1) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r0 = this;
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Exception -> 0x0009 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x0009 }
        return r1;
    L_0x0009:
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.model.Place.parseToInt(java.lang.String):java.lang.Integer");
    }

    public LatLng getPosition() {
        if (this.position == null) {
            this.position = new LatLng(this.lat, this.lng);
        }
        return this.position;
    }

    public String getTitle() {
        return this.name;
    }

    public String getSnippet() {
        return this.number;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Place)) {
            return false;
        }
        Place place = (Place) obj;
        if (getUid() != place.getUid() || Double.compare(place.getLat(), getLat()) != 0) {
            return false;
        }
        if (Double.compare(place.getLng(), getLng()) != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int uid = getUid();
        long doubleToLongBits = Double.doubleToLongBits(getLat());
        uid = (uid * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        doubleToLongBits = Double.doubleToLongBits(getLng());
        return (uid * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }
}
