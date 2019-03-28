package com.mevo.app.data.model;

import android.support.annotation.Nullable;
import com.google.gson.reflect.TypeToken;
import com.raizlabs.android.dbflow.structure.BaseModel;
import java.util.List;
import org.simpleframework.xml.Attribute;

public class UserDetails extends BaseModel {
    public static final transient int MAX_TIMESTAMPS = 2;
    public static final transient String NO_CARD_NUMBER = "";
    @Attribute(name = "address", required = false)
    private String address;
    @Attribute(name = "data_battery_reports", required = false)
    private String batteryTimestamps;
    @Attribute(name = "data_daily_reservations", required = false)
    private String bookingHistory;
    @Attribute(name = "data_daily_reservations_last_update", required = false)
    private long bookingHistoryLastUpdate;
    @Attribute(name = "city", required = false)
    private String city;
    @Attribute(name = "codeword", required = false)
    private String codeword;
    @Attribute(name = "country", required = false)
    private String country;
    @Attribute(name = "credits", required = false)
    private int credits;
    @Attribute(name = "currency", required = false)
    private String currency;
    @Attribute(name = "email", required = false)
    private String email;
    @Attribute(name = "forename", required = false)
    private String firstname;
    @Attribute(name = "newsletter", required = false)
    private int hasNewsletter;
    private int id = 1;
    @Attribute(name = "active", required = false)
    private int isActive;
    @Attribute(name = "language", required = false)
    private String language;
    @Attribute(name = "name", required = false)
    private String lastname;
    @Attribute(name = "payment", required = false)
    private String payment;
    @Attribute(name = "pesel", required = false)
    private String pesel;
    @Attribute(name = "mobile", required = false)
    private String phoneNumber;
    @Attribute(name = "rfid_uids", required = false)
    private String rfidUids;
    @Attribute(name = "sms_mobile", required = false)
    private String smsMobile;
    @Attribute(name = "data_daily_subscription_seconds_used", required = false)
    private int subscriptionDailySecondsUsed;
    @Attribute(name = "data_subscription_purchase_timestamp", required = false)
    private long subscriptionPurchaseTimestamp;
    @Attribute(name = "ticket_ids", required = false)
    private String ticketId;
    @Attribute(name = "zip", required = false)
    private String zipCode;

    /* renamed from: com.mevo.app.data.model.UserDetails$1 */
    class C08001 extends TypeToken<List<SubscriptionBookingItem>> {
        C08001() {
        }
    }

    public long getTariffPurchaseTimestamp() {
        return this.subscriptionPurchaseTimestamp;
    }

    public long getSubscriptionPurchaseTimestamp() {
        return this.subscriptionPurchaseTimestamp;
    }

    public UserDetails setSubscriptionPurchaseTimestamp(long j) {
        this.subscriptionPurchaseTimestamp = j;
        return this;
    }

    public String getTicketId() {
        return this.ticketId;
    }

    public UserDetails setTicketId(String str) {
        this.ticketId = str;
        return this;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getRfidUids() {
        return this.rfidUids;
    }

    public UserDetails setRfidUids(String str) {
        this.rfidUids = str;
        return this;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public int getIsActive() {
        return this.isActive;
    }

    public void setIsActive(int i) {
        this.isActive = i;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String str) {
        this.firstname = str;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String str) {
        this.zipCode = str;
    }

    public String getSmsMobile() {
        return this.smsMobile;
    }

    public void setSmsMobile(String str) {
        this.smsMobile = str;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String str) {
        this.lastname = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getPayment() {
        return this.payment;
    }

    public void setPayment(String str) {
        this.payment = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public int getHasNewsletter() {
        return this.hasNewsletter;
    }

    public void setHasNewsletter(int i) {
        this.hasNewsletter = i;
    }

    public String getCodeword() {
        return this.codeword;
    }

    public void setCodeword(String str) {
        this.codeword = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getPesel() {
        return this.pesel;
    }

    public void setPesel(String str) {
        this.pesel = str;
    }

    public int getCredits() {
        return this.credits;
    }

    public UserDetails setCredits(int i) {
        this.credits = i;
        return this;
    }

    public String getCurrency() {
        return this.currency;
    }

    public UserDetails setCurrency(String str) {
        this.currency = str;
        return this;
    }

    public String getBookingHistory() {
        return this.bookingHistory;
    }

    public long getBookingHistoryLastUpdate() {
        return this.bookingHistoryLastUpdate;
    }

    public UserDetails setBookingHistoryLastUpdate(long j) {
        this.bookingHistoryLastUpdate = j;
        return this;
    }

    public int getSubscriptionDailySecondsUsed() {
        return this.subscriptionDailySecondsUsed;
    }

    public UserDetails setSubscriptionDailySecondsUsed(int i) {
        this.subscriptionDailySecondsUsed = i;
        return this;
    }

    public String getBatteryTimestamps() {
        return this.batteryTimestamps;
    }

    public void setBatteryTimestamps(String str) {
        this.batteryTimestamps = str;
    }

    @android.support.annotation.NonNull
    public java.util.List<com.mevo.app.data.model.SubscriptionBookingItem> getBookingHistoryParsed() {
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
        r0 = com.mevo.app.data.network.Rest.getJsonSerializer();	 Catch:{ Exception -> 0x001d }
        r1 = r3.bookingHistory;	 Catch:{ Exception -> 0x001d }
        r2 = new com.mevo.app.data.model.UserDetails$1;	 Catch:{ Exception -> 0x001d }
        r2.<init>();	 Catch:{ Exception -> 0x001d }
        r2 = r2.getType();	 Catch:{ Exception -> 0x001d }
        r0 = r0.fromJson(r1, r2);	 Catch:{ Exception -> 0x001d }
        r0 = (java.util.List) r0;	 Catch:{ Exception -> 0x001d }
        if (r0 != 0) goto L_0x001c;	 Catch:{ Exception -> 0x001d }
    L_0x0017:
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x001d }
        r0.<init>();	 Catch:{ Exception -> 0x001d }
    L_0x001c:
        return r0;
    L_0x001d:
        r0 = new java.util.ArrayList;
        r0.<init>();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.model.UserDetails.getBookingHistoryParsed():java.util.List<com.mevo.app.data.model.SubscriptionBookingItem>");
    }

    public UserDetails setBookingHistory(String str) {
        this.bookingHistory = str;
        return this;
    }

    @Nullable
    public String getMultipleRfidUid() {
        return this.rfidUids != null ? this.rfidUids.replace(",", ", ") : null;
    }

    @Nullable
    public String getRfidUid() {
        if (this.rfidUids == null) {
            return null;
        }
        String[] split = this.rfidUids.split(",");
        if (split.length != 0) {
            return split[0];
        }
        return null;
    }
}
