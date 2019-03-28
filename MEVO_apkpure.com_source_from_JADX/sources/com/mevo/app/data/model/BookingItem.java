package com.mevo.app.data.model;

import android.content.Context;
import java.util.Comparator;
import org.simpleframework.xml.Attribute;

public class BookingItem implements TransactionInfo, StartEndItem {
    public static final int STATE_ID_ACTIVE = 6;
    public static final int STATE_ID_READY_TO_RENT = 5;
    @Attribute(name = "biketypes", required = false)
    private String bikeTypes;
    @Attribute(name = "booked", required = false)
    private int booked;
    @Attribute(name = "booking_code", required = false)
    private String bookingCode;
    @Attribute(name = "confirm", required = false)
    private int confirm;
    @Attribute(name = "cust_id", required = false)
    private String custId;
    @Attribute(name = "end_time", required = false)
    private long endTimestampSeconds;
    @Attribute(name = "id", required = false)
    public int id;
    @Attribute(name = "lat", required = false)
    private String lat;
    @Attribute(name = "lng", required = false)
    private String lng;
    @Attribute(name = "max_bookingtime_before_rental", required = false)
    private long maxBookingTimeBeforeRental;
    @Attribute(name = "num_bikes", required = false)
    private int numBikes;
    @Attribute(name = "place_id", required = false)
    private String placeId;
    @Attribute(name = "place_name", required = false)
    private String placeName;
    @Attribute(name = "price", required = false)
    public int price;
    @Attribute(name = "price_service", required = false)
    public int serviceFee;
    @Attribute(name = "customer_comment", required = false)
    public String serviceFeeInfo;
    @Attribute(name = "spot", required = false)
    private String spot;
    @Attribute(name = "start_time", required = false)
    private long startTimestampSeconds;
    @Attribute(name = "state", required = false)
    private String state;
    @Attribute(name = "state_id", required = false)
    private int stateId;
    @Attribute(name = "used_free_seconds", required = false)
    public long usedTariffSeconds;

    public static class BookingStartComparator implements Comparator<BookingItem> {
        public int compare(BookingItem bookingItem, BookingItem bookingItem2) {
            return Long.compare(bookingItem.getStartTimestampSeconds(), bookingItem2.getStartTimestampSeconds());
        }
    }

    public int getId() {
        return this.id;
    }

    public String getPlaceId() {
        if (this.placeId == null) {
            this.placeId = "";
        }
        return this.placeId;
    }

    public String getBikeTypes() {
        return this.bikeTypes;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public String getState() {
        return this.state;
    }

    public String getLng() {
        return this.lng;
    }

    public long getMaxBookingTimeBeforeRental() {
        return this.maxBookingTimeBeforeRental;
    }

    public String getCustId() {
        return this.custId;
    }

    public BookingItem setId(int i) {
        this.id = i;
        return this;
    }

    public BookingItem setPlaceId(String str) {
        this.placeId = str;
        return this;
    }

    public BookingItem setBikeTypes(String str) {
        this.bikeTypes = str;
        return this;
    }

    public BookingItem setPlaceName(String str) {
        this.placeName = str;
        return this;
    }

    public BookingItem setState(String str) {
        this.state = str;
        return this;
    }

    public BookingItem setLng(String str) {
        this.lng = str;
        return this;
    }

    public BookingItem setMaxBookingTimeBeforeRental(long j) {
        this.maxBookingTimeBeforeRental = j;
        return this;
    }

    public BookingItem setCustId(String str) {
        this.custId = str;
        return this;
    }

    public BookingItem setSpot(String str) {
        this.spot = str;
        return this;
    }

    public BookingItem setStateId(int i) {
        this.stateId = i;
        return this;
    }

    public BookingItem setEndTimestampSeconds(long j) {
        this.endTimestampSeconds = j;
        return this;
    }

    public BookingItem setBooked(int i) {
        this.booked = i;
        return this;
    }

    public BookingItem setNumBikes(int i) {
        this.numBikes = i;
        return this;
    }

    public BookingItem setStartTimestampSeconds(long j) {
        this.startTimestampSeconds = j;
        return this;
    }

    public BookingItem setBookingCode(String str) {
        this.bookingCode = str;
        return this;
    }

    public BookingItem setConfirm(int i) {
        this.confirm = i;
        return this;
    }

    public BookingItem setLat(String str) {
        this.lat = str;
        return this;
    }

    public String getSpot() {
        return this.spot;
    }

    public int getStateId() {
        return this.stateId;
    }

    public long getEndTimestampSecondsForTariffSecondsCalculation() {
        if (this.endTimestampSeconds <= 0 || getUsedTariffSeconds() <= 0) {
            return this.endTimestampSeconds;
        }
        return (getStartTimestampSeconds() + getUsedTariffSeconds()) + 300;
    }

    public long getEndTimestampSeconds() {
        return this.endTimestampSeconds;
    }

    public int getBooked() {
        return this.booked;
    }

    public int getNumBikes() {
        return this.numBikes;
    }

    public long getStartTimestampSeconds() {
        return this.startTimestampSeconds;
    }

    public String getBookingCode() {
        return this.bookingCode;
    }

    public int getConfirm() {
        return this.confirm;
    }

    public String getLat() {
        return this.lat;
    }

    public int getPrice() {
        return this.price;
    }

    public BookingItem setPrice(int i) {
        this.price = i;
        return this;
    }

    public BookingItem setServiceFee(int i) {
        this.serviceFee = i;
        return this;
    }

    public String getServiceFeeInfo() {
        return this.serviceFeeInfo;
    }

    public BookingItem setServiceFeeInfo(String str) {
        this.serviceFeeInfo = str;
        return this;
    }

    public long getTimeLeftToEndSeconds() {
        long endTimestampSeconds = getEndTimestampSeconds() - (System.currentTimeMillis() / 1000);
        return endTimestampSeconds > 0 ? endTimestampSeconds : 0;
    }

    public long getDurationSeconds() {
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - getStartTimestampSeconds();
        return currentTimeMillis > 900 ? 900 : currentTimeMillis;
    }

    public boolean isFinishedAccordingToApi() {
        return getEndTimestampSeconds() <= System.currentTimeMillis() / 1000;
    }

    public boolean isFinishedAccordingToUs() {
        return getDurationSeconds() >= 900;
    }

    public com.google.android.gms.maps.model.LatLng getCurrentLatLng() {
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
        r5 = this;
        r0 = new com.google.android.gms.maps.model.LatLng;	 Catch:{ Exception -> 0x0012 }
        r1 = r5.lat;	 Catch:{ Exception -> 0x0012 }
        r1 = java.lang.Double.parseDouble(r1);	 Catch:{ Exception -> 0x0012 }
        r3 = r5.lng;	 Catch:{ Exception -> 0x0012 }
        r3 = java.lang.Double.parseDouble(r3);	 Catch:{ Exception -> 0x0012 }
        r0.<init>(r1, r3);	 Catch:{ Exception -> 0x0012 }
        return r0;
    L_0x0012:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.model.BookingItem.getCurrentLatLng():com.google.android.gms.maps.model.LatLng");
    }

    public BookingItem setUsedTariffSeconds(long j) {
        this.usedTariffSeconds = j;
        return this;
    }

    public long getPaymentDate() {
        return getStartTimestampSeconds();
    }

    public int getServiceFee() {
        return this.serviceFee;
    }

    public String getServiceFeeInfo(Context context) {
        return this.serviceFeeInfo;
    }

    public long getUsedTariffSeconds() {
        return this.usedTariffSeconds;
    }

    public long getStartTsSeconds() {
        return getStartTimestampSeconds();
    }

    public long getEndTsSeconds() {
        return getEndTimestampSeconds();
    }

    public long getNonFreeDurationCalculatedByUs() {
        long durationSeconds = getDurationSeconds() - 300;
        return durationSeconds >= 0 ? durationSeconds : 0;
    }
}
