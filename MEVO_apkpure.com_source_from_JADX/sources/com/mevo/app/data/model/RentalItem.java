package com.mevo.app.data.model;

import android.content.Context;
import android.content.res.Resources;
import android.util.Pair;
import com.mevo.app.C0434R;
import com.mevo.app.tools.Log;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import org.simpleframework.xml.Attribute;

public class RentalItem implements TransactionInfo, StartEndItem {
    public static final int RENTAL_PREMIUM_RETURN_AMOUNT = 200;
    @Attribute(name = "bike", required = false)
    public String bikeNumber;
    @Attribute(name = "biketype", required = false)
    public String bikeType;
    private long bookTimestampSeconds;
    @Attribute(name = "break", required = false)
    private int breakRent;
    @Attribute(name = "city", required = false)
    public String city;
    @Attribute(name = "city_id", required = false)
    public int cityId;
    @Attribute(name = "current_place_lat", required = false)
    private String currentPlaceLat;
    @Attribute(name = "current_place_lng", required = false)
    private String currentPlaceLng;
    @Attribute(name = "domain", required = false)
    public String domain;
    @Attribute(name = "end_place", required = false)
    public String endPlaceId;
    @Attribute(name = "end_place_lat", required = false)
    public String endPlaceLat;
    @Attribute(name = "end_place_lng", required = false)
    public String endPlaceLng;
    @Attribute(name = "end_place_name", required = false)
    public String endPlaceName;
    @Attribute(name = "end_time", required = false)
    public long endTimestampSeconds;
    @Attribute(name = "id", required = false)
    public int id;
    @Attribute(name = "invalid", required = false)
    public int isInvalid;
    @Attribute(name = "return_via_app", required = false)
    public int isReturnedViaApp;
    @Attribute(name = "code", required = false)
    public String lockCode;
    @Attribute(name = "price", required = false)
    public int price;
    @Attribute(name = "price_service", required = false)
    public int serviceFee;
    @Attribute(name = "customer_comment", required = false)
    public String serviceFeeInfo;
    @Attribute(name = "start_place", required = false)
    public String startPlaceId;
    @Attribute(name = "start_place_lat", required = false)
    public String startPlaceLat;
    @Attribute(name = "start_place_lng", required = false)
    public String startPlaceLng;
    @Attribute(name = "start_place_name", required = false)
    public String startPlaceName;
    @Attribute(name = "start_time", required = false)
    public long startTimestampSeconds;
    @Attribute(name = "used_free_seconds", required = false)
    public long usedSubscriptionSeconds;

    public enum BikeStatus {
        RETURNED(1),
        RENTED(2),
        PARKED(4);
        
        int statusInt;

        private BikeStatus(int i) {
            this.statusInt = i;
        }
    }

    public static class ReversedStartDateComparator implements Comparator<RentalItem> {
        public int compare(RentalItem rentalItem, RentalItem rentalItem2) {
            if (rentalItem.startTimestampSeconds > rentalItem2.startTimestampSeconds) {
                return -1;
            }
            return rentalItem.startTimestampSeconds < rentalItem2.startTimestampSeconds ? 1 : null;
        }
    }

    public static class StartDateComparator implements Comparator<RentalItem> {
        public int compare(RentalItem rentalItem, RentalItem rentalItem2) {
            return Long.compare(rentalItem.startTimestampSeconds, rentalItem2.startTimestampSeconds);
        }
    }

    public static class StartDateComparatorWithRentalRoute implements Comparator<Pair<RentalItem, RentalDistance>> {
        public int compare(Pair<RentalItem, RentalDistance> pair, Pair<RentalItem, RentalDistance> pair2) {
            if (((RentalItem) pair.first).startTimestampSeconds > ((RentalItem) pair2.first).startTimestampSeconds) {
                return -1;
            }
            return ((RentalItem) pair.first).startTimestampSeconds < ((RentalItem) pair2.first).startTimestampSeconds ? 1 : null;
        }
    }

    public String getCurrentPlaceLat() {
        return this.currentPlaceLat;
    }

    public RentalItem setCurrentPlaceLat(String str) {
        this.currentPlaceLat = str;
        return this;
    }

    public String getCurrentPlaceLng() {
        return this.currentPlaceLng;
    }

    public RentalItem setCurrentPlaceLng(String str) {
        this.currentPlaceLng = str;
        return this;
    }

    public long getBookTimestampSeconds() {
        return this.bookTimestampSeconds;
    }

    public long getDurationSeconds() {
        return (this.endTimestampSeconds > 0 ? this.endTimestampSeconds : System.currentTimeMillis() / 1000) - this.startTimestampSeconds;
    }

    public BigDecimal getPriceInWholeUnits() {
        return new BigDecimal(this.price).divide(new BigDecimal(100), 2, RoundingMode.HALF_DOWN);
    }

    public RentalItem setBookTimestampSeconds(long j) {
        this.bookTimestampSeconds = j;
        return this;
    }

    public com.google.android.gms.maps.model.LatLng getStartLatLng() {
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
        r8 = this;
        r0 = 0;
        r1 = r8.startPlaceLat;	 Catch:{ Exception -> 0x001f }
        r1 = java.lang.Double.parseDouble(r1);	 Catch:{ Exception -> 0x001f }
        r3 = r8.startPlaceLng;	 Catch:{ Exception -> 0x001f }
        r3 = java.lang.Double.parseDouble(r3);	 Catch:{ Exception -> 0x001f }
        r5 = 0;	 Catch:{ Exception -> 0x001f }
        r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1));	 Catch:{ Exception -> 0x001f }
        if (r7 == 0) goto L_0x001e;	 Catch:{ Exception -> 0x001f }
    L_0x0013:
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));	 Catch:{ Exception -> 0x001f }
        if (r7 != 0) goto L_0x0018;	 Catch:{ Exception -> 0x001f }
    L_0x0017:
        goto L_0x001e;	 Catch:{ Exception -> 0x001f }
    L_0x0018:
        r5 = new com.google.android.gms.maps.model.LatLng;	 Catch:{ Exception -> 0x001f }
        r5.<init>(r1, r3);	 Catch:{ Exception -> 0x001f }
        return r5;
    L_0x001e:
        return r0;
    L_0x001f:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.model.RentalItem.getStartLatLng():com.google.android.gms.maps.model.LatLng");
    }

    public com.google.android.gms.maps.model.LatLng getEndLatLng() {
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
        r8 = this;
        r0 = 0;
        r1 = r8.endPlaceLat;	 Catch:{ Exception -> 0x001f }
        r1 = java.lang.Double.parseDouble(r1);	 Catch:{ Exception -> 0x001f }
        r3 = r8.endPlaceLng;	 Catch:{ Exception -> 0x001f }
        r3 = java.lang.Double.parseDouble(r3);	 Catch:{ Exception -> 0x001f }
        r5 = 0;	 Catch:{ Exception -> 0x001f }
        r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1));	 Catch:{ Exception -> 0x001f }
        if (r7 == 0) goto L_0x001e;	 Catch:{ Exception -> 0x001f }
    L_0x0013:
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));	 Catch:{ Exception -> 0x001f }
        if (r7 != 0) goto L_0x0018;	 Catch:{ Exception -> 0x001f }
    L_0x0017:
        goto L_0x001e;	 Catch:{ Exception -> 0x001f }
    L_0x0018:
        r5 = new com.google.android.gms.maps.model.LatLng;	 Catch:{ Exception -> 0x001f }
        r5.<init>(r1, r3);	 Catch:{ Exception -> 0x001f }
        return r5;
    L_0x001e:
        return r0;
    L_0x001f:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.model.RentalItem.getEndLatLng():com.google.android.gms.maps.model.LatLng");
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
        r1 = r5.startPlaceLat;	 Catch:{ Exception -> 0x0012 }
        r1 = java.lang.Double.parseDouble(r1);	 Catch:{ Exception -> 0x0012 }
        r3 = r5.startPlaceLng;	 Catch:{ Exception -> 0x0012 }
        r3 = java.lang.Double.parseDouble(r3);	 Catch:{ Exception -> 0x0012 }
        r0.<init>(r1, r3);	 Catch:{ Exception -> 0x0012 }
        return r0;
    L_0x0012:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.model.RentalItem.getCurrentLatLng():com.google.android.gms.maps.model.LatLng");
    }

    public boolean isReturned() {
        return this.endTimestampSeconds > 0;
    }

    public BikeStatus getBikeStatus() {
        if (isReturned()) {
            return BikeStatus.RETURNED;
        }
        if (this.breakRent == 1) {
            return BikeStatus.PARKED;
        }
        return BikeStatus.RENTED;
    }

    public int getPrice() {
        return this.price;
    }

    public long getPaymentDate() {
        return this.startTimestampSeconds;
    }

    public int getServiceFee() {
        return Math.abs(this.serviceFee);
    }

    public long getUsedTariffSeconds() {
        return this.usedSubscriptionSeconds;
    }

    public String getServiceFeeInfo(Context context) {
        if (getServiceFee() == 0 || context == null) {
            return null;
        }
        String str = "rent_service_fee_info_";
        if (getServiceFee() < 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("minus_");
            str = stringBuilder.toString();
        }
        try {
            Resources resources = context.getResources();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(Math.abs(getServiceFee()));
            str = resources.getString(resources.getIdentifier(stringBuilder2.toString(), "string", context.getPackageName()));
        } catch (Throwable e) {
            Log.ex("RentalItem", e);
            str = null;
        }
        if (str == null) {
            str = context.getString(C0434R.string.rent_service_fee_info_default);
        }
        return str;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (getClass() == obj.getClass()) {
                RentalItem rentalItem = (RentalItem) obj;
                if (this.id != rentalItem.id || this.startTimestampSeconds != rentalItem.startTimestampSeconds) {
                    return false;
                }
                if (this.bikeNumber != null) {
                    z = this.bikeNumber.equals(rentalItem.bikeNumber);
                } else if (rentalItem.bikeNumber != null) {
                    z = false;
                }
                return z;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((this.id * 31) + (this.bikeNumber != null ? this.bikeNumber.hashCode() : 0)) * 31) + ((int) (this.startTimestampSeconds ^ (this.startTimestampSeconds >>> 32)));
    }

    public long getStartTsSeconds() {
        return this.startTimestampSeconds;
    }

    public long getEndTsSeconds() {
        return this.endTimestampSeconds;
    }
}
