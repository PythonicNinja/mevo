package com.mevo.app.data.model;

import android.content.ContentValues;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;

public final class UserDetails_Table extends ModelAdapter<UserDetails> {
    public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{id, phoneNumber, smsMobile, isActive, credits, currency, email, firstname, lastname, zipCode, address, city, payment, language, pesel, country, hasNewsletter, codeword, rfidUids, ticketId, bookingHistory, bookingHistoryLastUpdate, subscriptionDailySecondsUsed, subscriptionPurchaseTimestamp};
    public static final Property<String> address = new Property(UserDetails.class, "address");
    public static final Property<String> bookingHistory = new Property(UserDetails.class, "bookingHistory");
    public static final Property<Long> bookingHistoryLastUpdate = new Property(UserDetails.class, "bookingHistoryLastUpdate");
    public static final Property<String> city = new Property(UserDetails.class, "city");
    public static final Property<String> codeword = new Property(UserDetails.class, "codeword");
    public static final Property<String> country = new Property(UserDetails.class, "country");
    public static final Property<Integer> credits = new Property(UserDetails.class, "credits");
    public static final Property<String> currency = new Property(UserDetails.class, Param.CURRENCY);
    public static final Property<String> email = new Property(UserDetails.class, "email");
    public static final Property<String> firstname = new Property(UserDetails.class, "firstname");
    public static final Property<Integer> hasNewsletter = new Property(UserDetails.class, "hasNewsletter");
    public static final Property<Integer> id = new Property(UserDetails.class, Name.MARK);
    public static final Property<Integer> isActive = new Property(UserDetails.class, "isActive");
    public static final Property<String> language = new Property(UserDetails.class, "language");
    public static final Property<String> lastname = new Property(UserDetails.class, "lastname");
    public static final Property<String> payment = new Property(UserDetails.class, "payment");
    public static final Property<String> pesel = new Property(UserDetails.class, "pesel");
    public static final Property<String> phoneNumber = new Property(UserDetails.class, "phoneNumber");
    public static final Property<String> rfidUids = new Property(UserDetails.class, "rfidUids");
    public static final Property<String> smsMobile = new Property(UserDetails.class, "smsMobile");
    public static final Property<Integer> subscriptionDailySecondsUsed = new Property(UserDetails.class, "subscriptionDailySecondsUsed");
    public static final Property<Long> subscriptionPurchaseTimestamp = new Property(UserDetails.class, "subscriptionPurchaseTimestamp");
    public static final Property<String> ticketId = new Property(UserDetails.class, "ticketId");
    public static final Property<String> zipCode = new Property(UserDetails.class, "zipCode");

    public final String getCompiledStatementQuery() {
        return "INSERT INTO `UserDetails`(`id`,`phoneNumber`,`smsMobile`,`isActive`,`credits`,`currency`,`email`,`firstname`,`lastname`,`zipCode`,`address`,`city`,`payment`,`language`,`pesel`,`country`,`hasNewsletter`,`codeword`,`rfidUids`,`ticketId`,`bookingHistory`,`bookingHistoryLastUpdate`,`subscriptionDailySecondsUsed`,`subscriptionPurchaseTimestamp`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    public final String getCreationQuery() {
        return "CREATE TABLE IF NOT EXISTS `UserDetails`(`id` INTEGER, `phoneNumber` TEXT, `smsMobile` TEXT, `isActive` INTEGER, `credits` INTEGER, `currency` TEXT, `email` TEXT, `firstname` TEXT, `lastname` TEXT, `zipCode` TEXT, `address` TEXT, `city` TEXT, `payment` TEXT, `language` TEXT, `pesel` TEXT, `country` TEXT, `hasNewsletter` INTEGER, `codeword` TEXT, `rfidUids` TEXT, `ticketId` TEXT, `bookingHistory` TEXT, `bookingHistoryLastUpdate` INTEGER, `subscriptionDailySecondsUsed` INTEGER, `subscriptionPurchaseTimestamp` INTEGER, PRIMARY KEY(`id`))";
    }

    public final String getDeleteStatementQuery() {
        return "DELETE FROM `UserDetails` WHERE `id`=?";
    }

    public final String getTableName() {
        return "`UserDetails`";
    }

    public final String getUpdateStatementQuery() {
        return "UPDATE `UserDetails` SET `id`=?,`phoneNumber`=?,`smsMobile`=?,`isActive`=?,`credits`=?,`currency`=?,`email`=?,`firstname`=?,`lastname`=?,`zipCode`=?,`address`=?,`city`=?,`payment`=?,`language`=?,`pesel`=?,`country`=?,`hasNewsletter`=?,`codeword`=?,`rfidUids`=?,`ticketId`=?,`bookingHistory`=?,`bookingHistoryLastUpdate`=?,`subscriptionDailySecondsUsed`=?,`subscriptionPurchaseTimestamp`=? WHERE `id`=?";
    }

    public UserDetails_Table(DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }

    public final Class<UserDetails> getModelClass() {
        return UserDetails.class;
    }

    public final UserDetails newInstance() {
        return new UserDetails();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.raizlabs.android.dbflow.sql.language.property.Property getProperty(java.lang.String r2) {
        /*
        r1 = this;
        r2 = com.raizlabs.android.dbflow.sql.QueryBuilder.quoteIfNeeded(r2);
        r0 = r2.hashCode();
        switch(r0) {
            case -2088944662: goto L_0x0110;
            case -1998757724: goto L_0x0106;
            case -1872087598: goto L_0x00fb;
            case -1756580967: goto L_0x00f0;
            case -1690692837: goto L_0x00e5;
            case -1585005523: goto L_0x00da;
            case -1451896843: goto L_0x00cf;
            case -1027834353: goto L_0x00c5;
            case -856147227: goto L_0x00bb;
            case -724238007: goto L_0x00b1;
            case -334254214: goto L_0x00a5;
            case -211294875: goto L_0x0099;
            case -167099480: goto L_0x008d;
            case -102281656: goto L_0x0081;
            case 2964037: goto L_0x0076;
            case 105938790: goto L_0x006b;
            case 172712133: goto L_0x0060;
            case 341171711: goto L_0x0054;
            case 867964208: goto L_0x0049;
            case 1359315788: goto L_0x003d;
            case 1504543849: goto L_0x0031;
            case 1623440358: goto L_0x0025;
            case 1739437358: goto L_0x0019;
            case 2045173184: goto L_0x000d;
            default: goto L_0x000b;
        };
    L_0x000b:
        goto L_0x011b;
    L_0x000d:
        r0 = "`subscriptionDailySecondsUsed`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x0015:
        r2 = 22;
        goto L_0x011c;
    L_0x0019:
        r0 = "`rfidUids`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x0021:
        r2 = 18;
        goto L_0x011c;
    L_0x0025:
        r0 = "`bookingHistoryLastUpdate`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x002d:
        r2 = 21;
        goto L_0x011c;
    L_0x0031:
        r0 = "`codeword`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x0039:
        r2 = 17;
        goto L_0x011c;
    L_0x003d:
        r0 = "`address`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x0045:
        r2 = 10;
        goto L_0x011c;
    L_0x0049:
        r0 = "`isActive`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x0051:
        r2 = 3;
        goto L_0x011c;
    L_0x0054:
        r0 = "`lastname`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x005c:
        r2 = 8;
        goto L_0x011c;
    L_0x0060:
        r0 = "`smsMobile`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x0068:
        r2 = 2;
        goto L_0x011c;
    L_0x006b:
        r0 = "`credits`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x0073:
        r2 = 4;
        goto L_0x011c;
    L_0x0076:
        r0 = "`id`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x007e:
        r2 = 0;
        goto L_0x011c;
    L_0x0081:
        r0 = "`subscriptionPurchaseTimestamp`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x0089:
        r2 = 23;
        goto L_0x011c;
    L_0x008d:
        r0 = "`language`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x0095:
        r2 = 13;
        goto L_0x011c;
    L_0x0099:
        r0 = "`bookingHistory`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x00a1:
        r2 = 20;
        goto L_0x011c;
    L_0x00a5:
        r0 = "`payment`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x00ad:
        r2 = 12;
        goto L_0x011c;
    L_0x00b1:
        r0 = "`phoneNumber`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x00b9:
        r2 = 1;
        goto L_0x011c;
    L_0x00bb:
        r0 = "`firstname`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x00c3:
        r2 = 7;
        goto L_0x011c;
    L_0x00c5:
        r0 = "`currency`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x00cd:
        r2 = 5;
        goto L_0x011c;
    L_0x00cf:
        r0 = "`city`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x00d7:
        r2 = 11;
        goto L_0x011c;
    L_0x00da:
        r0 = "`hasNewsletter`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x00e2:
        r2 = 16;
        goto L_0x011c;
    L_0x00e5:
        r0 = "`pesel`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x00ed:
        r2 = 14;
        goto L_0x011c;
    L_0x00f0:
        r0 = "`ticketId`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x00f8:
        r2 = 19;
        goto L_0x011c;
    L_0x00fb:
        r0 = "`zipCode`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x0103:
        r2 = 9;
        goto L_0x011c;
    L_0x0106:
        r0 = "`email`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x010e:
        r2 = 6;
        goto L_0x011c;
    L_0x0110:
        r0 = "`country`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x011b;
    L_0x0118:
        r2 = 15;
        goto L_0x011c;
    L_0x011b:
        r2 = -1;
    L_0x011c:
        switch(r2) {
            case 0: goto L_0x016c;
            case 1: goto L_0x0169;
            case 2: goto L_0x0166;
            case 3: goto L_0x0163;
            case 4: goto L_0x0160;
            case 5: goto L_0x015d;
            case 6: goto L_0x015a;
            case 7: goto L_0x0157;
            case 8: goto L_0x0154;
            case 9: goto L_0x0151;
            case 10: goto L_0x014e;
            case 11: goto L_0x014b;
            case 12: goto L_0x0148;
            case 13: goto L_0x0145;
            case 14: goto L_0x0142;
            case 15: goto L_0x013f;
            case 16: goto L_0x013c;
            case 17: goto L_0x0139;
            case 18: goto L_0x0136;
            case 19: goto L_0x0133;
            case 20: goto L_0x0130;
            case 21: goto L_0x012d;
            case 22: goto L_0x012a;
            case 23: goto L_0x0127;
            default: goto L_0x011f;
        };
    L_0x011f:
        r2 = new java.lang.IllegalArgumentException;
        r0 = "Invalid column name passed. Ensure you are calling the correct table's column";
        r2.<init>(r0);
        throw r2;
    L_0x0127:
        r2 = subscriptionPurchaseTimestamp;
        return r2;
    L_0x012a:
        r2 = subscriptionDailySecondsUsed;
        return r2;
    L_0x012d:
        r2 = bookingHistoryLastUpdate;
        return r2;
    L_0x0130:
        r2 = bookingHistory;
        return r2;
    L_0x0133:
        r2 = ticketId;
        return r2;
    L_0x0136:
        r2 = rfidUids;
        return r2;
    L_0x0139:
        r2 = codeword;
        return r2;
    L_0x013c:
        r2 = hasNewsletter;
        return r2;
    L_0x013f:
        r2 = country;
        return r2;
    L_0x0142:
        r2 = pesel;
        return r2;
    L_0x0145:
        r2 = language;
        return r2;
    L_0x0148:
        r2 = payment;
        return r2;
    L_0x014b:
        r2 = city;
        return r2;
    L_0x014e:
        r2 = address;
        return r2;
    L_0x0151:
        r2 = zipCode;
        return r2;
    L_0x0154:
        r2 = lastname;
        return r2;
    L_0x0157:
        r2 = firstname;
        return r2;
    L_0x015a:
        r2 = email;
        return r2;
    L_0x015d:
        r2 = currency;
        return r2;
    L_0x0160:
        r2 = credits;
        return r2;
    L_0x0163:
        r2 = isActive;
        return r2;
    L_0x0166:
        r2 = smsMobile;
        return r2;
    L_0x0169:
        r2 = phoneNumber;
        return r2;
    L_0x016c:
        r2 = id;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.model.UserDetails_Table.getProperty(java.lang.String):com.raizlabs.android.dbflow.sql.language.property.Property");
    }

    public final IProperty[] getAllColumnProperties() {
        return ALL_COLUMN_PROPERTIES;
    }

    public final void bindToInsertValues(ContentValues contentValues, UserDetails userDetails) {
        contentValues.put("`id`", Integer.valueOf(userDetails.getId()));
        String str = null;
        contentValues.put("`phoneNumber`", userDetails.getPhoneNumber() != null ? userDetails.getPhoneNumber() : null);
        contentValues.put("`smsMobile`", userDetails.getSmsMobile() != null ? userDetails.getSmsMobile() : null);
        contentValues.put("`isActive`", Integer.valueOf(userDetails.getIsActive()));
        contentValues.put("`credits`", Integer.valueOf(userDetails.getCredits()));
        contentValues.put("`currency`", userDetails.getCurrency() != null ? userDetails.getCurrency() : null);
        contentValues.put("`email`", userDetails.getEmail() != null ? userDetails.getEmail() : null);
        contentValues.put("`firstname`", userDetails.getFirstname() != null ? userDetails.getFirstname() : null);
        contentValues.put("`lastname`", userDetails.getLastname() != null ? userDetails.getLastname() : null);
        contentValues.put("`zipCode`", userDetails.getZipCode() != null ? userDetails.getZipCode() : null);
        contentValues.put("`address`", userDetails.getAddress() != null ? userDetails.getAddress() : null);
        contentValues.put("`city`", userDetails.getCity() != null ? userDetails.getCity() : null);
        contentValues.put("`payment`", userDetails.getPayment() != null ? userDetails.getPayment() : null);
        contentValues.put("`language`", userDetails.getLanguage() != null ? userDetails.getLanguage() : null);
        contentValues.put("`pesel`", userDetails.getPesel() != null ? userDetails.getPesel() : null);
        contentValues.put("`country`", userDetails.getCountry() != null ? userDetails.getCountry() : null);
        contentValues.put("`hasNewsletter`", Integer.valueOf(userDetails.getHasNewsletter()));
        contentValues.put("`codeword`", userDetails.getCodeword() != null ? userDetails.getCodeword() : null);
        contentValues.put("`rfidUids`", userDetails.getRfidUids() != null ? userDetails.getRfidUids() : null);
        contentValues.put("`ticketId`", userDetails.getTicketId() != null ? userDetails.getTicketId() : null);
        String str2 = "`bookingHistory`";
        if (userDetails.getBookingHistory() != null) {
            str = userDetails.getBookingHistory();
        }
        contentValues.put(str2, str);
        contentValues.put("`bookingHistoryLastUpdate`", Long.valueOf(userDetails.getBookingHistoryLastUpdate()));
        contentValues.put("`subscriptionDailySecondsUsed`", Integer.valueOf(userDetails.getSubscriptionDailySecondsUsed()));
        contentValues.put("`subscriptionPurchaseTimestamp`", Long.valueOf(userDetails.getSubscriptionPurchaseTimestamp()));
    }

    public final void bindToInsertStatement(DatabaseStatement databaseStatement, UserDetails userDetails, int i) {
        databaseStatement.bindLong(i + 1, (long) userDetails.getId());
        databaseStatement.bindStringOrNull(i + 2, userDetails.getPhoneNumber());
        databaseStatement.bindStringOrNull(i + 3, userDetails.getSmsMobile());
        databaseStatement.bindLong(i + 4, (long) userDetails.getIsActive());
        databaseStatement.bindLong(i + 5, (long) userDetails.getCredits());
        databaseStatement.bindStringOrNull(i + 6, userDetails.getCurrency());
        databaseStatement.bindStringOrNull(i + 7, userDetails.getEmail());
        databaseStatement.bindStringOrNull(i + 8, userDetails.getFirstname());
        databaseStatement.bindStringOrNull(i + 9, userDetails.getLastname());
        databaseStatement.bindStringOrNull(i + 10, userDetails.getZipCode());
        databaseStatement.bindStringOrNull(i + 11, userDetails.getAddress());
        databaseStatement.bindStringOrNull(i + 12, userDetails.getCity());
        databaseStatement.bindStringOrNull(i + 13, userDetails.getPayment());
        databaseStatement.bindStringOrNull(i + 14, userDetails.getLanguage());
        databaseStatement.bindStringOrNull(i + 15, userDetails.getPesel());
        databaseStatement.bindStringOrNull(i + 16, userDetails.getCountry());
        databaseStatement.bindLong(i + 17, (long) userDetails.getHasNewsletter());
        databaseStatement.bindStringOrNull(i + 18, userDetails.getCodeword());
        databaseStatement.bindStringOrNull(i + 19, userDetails.getRfidUids());
        databaseStatement.bindStringOrNull(i + 20, userDetails.getTicketId());
        databaseStatement.bindStringOrNull(i + 21, userDetails.getBookingHistory());
        databaseStatement.bindLong(i + 22, userDetails.getBookingHistoryLastUpdate());
        databaseStatement.bindLong(i + 23, (long) userDetails.getSubscriptionDailySecondsUsed());
        databaseStatement.bindLong(i + 24, userDetails.getSubscriptionPurchaseTimestamp());
    }

    public final void bindToUpdateStatement(DatabaseStatement databaseStatement, UserDetails userDetails) {
        databaseStatement.bindLong(1, (long) userDetails.getId());
        databaseStatement.bindStringOrNull(2, userDetails.getPhoneNumber());
        databaseStatement.bindStringOrNull(3, userDetails.getSmsMobile());
        databaseStatement.bindLong(4, (long) userDetails.getIsActive());
        databaseStatement.bindLong(5, (long) userDetails.getCredits());
        databaseStatement.bindStringOrNull(6, userDetails.getCurrency());
        databaseStatement.bindStringOrNull(7, userDetails.getEmail());
        databaseStatement.bindStringOrNull(8, userDetails.getFirstname());
        databaseStatement.bindStringOrNull(9, userDetails.getLastname());
        databaseStatement.bindStringOrNull(10, userDetails.getZipCode());
        databaseStatement.bindStringOrNull(11, userDetails.getAddress());
        databaseStatement.bindStringOrNull(12, userDetails.getCity());
        databaseStatement.bindStringOrNull(13, userDetails.getPayment());
        databaseStatement.bindStringOrNull(14, userDetails.getLanguage());
        databaseStatement.bindStringOrNull(15, userDetails.getPesel());
        databaseStatement.bindStringOrNull(16, userDetails.getCountry());
        databaseStatement.bindLong(17, (long) userDetails.getHasNewsletter());
        databaseStatement.bindStringOrNull(18, userDetails.getCodeword());
        databaseStatement.bindStringOrNull(19, userDetails.getRfidUids());
        databaseStatement.bindStringOrNull(20, userDetails.getTicketId());
        databaseStatement.bindStringOrNull(21, userDetails.getBookingHistory());
        databaseStatement.bindLong(22, userDetails.getBookingHistoryLastUpdate());
        databaseStatement.bindLong(23, (long) userDetails.getSubscriptionDailySecondsUsed());
        databaseStatement.bindLong(24, userDetails.getSubscriptionPurchaseTimestamp());
        databaseStatement.bindLong(25, (long) userDetails.getId());
    }

    public final void bindToDeleteStatement(DatabaseStatement databaseStatement, UserDetails userDetails) {
        databaseStatement.bindLong(1, (long) userDetails.getId());
    }

    public final void loadFromCursor(FlowCursor flowCursor, UserDetails userDetails) {
        userDetails.setId(flowCursor.getIntOrDefault(Name.MARK));
        userDetails.setPhoneNumber(flowCursor.getStringOrDefault("phoneNumber"));
        userDetails.setSmsMobile(flowCursor.getStringOrDefault("smsMobile"));
        userDetails.setIsActive(flowCursor.getIntOrDefault("isActive"));
        userDetails.setCredits(flowCursor.getIntOrDefault("credits"));
        userDetails.setCurrency(flowCursor.getStringOrDefault(Param.CURRENCY));
        userDetails.setEmail(flowCursor.getStringOrDefault("email"));
        userDetails.setFirstname(flowCursor.getStringOrDefault("firstname"));
        userDetails.setLastname(flowCursor.getStringOrDefault("lastname"));
        userDetails.setZipCode(flowCursor.getStringOrDefault("zipCode"));
        userDetails.setAddress(flowCursor.getStringOrDefault("address"));
        userDetails.setCity(flowCursor.getStringOrDefault("city"));
        userDetails.setPayment(flowCursor.getStringOrDefault("payment"));
        userDetails.setLanguage(flowCursor.getStringOrDefault("language"));
        userDetails.setPesel(flowCursor.getStringOrDefault("pesel"));
        userDetails.setCountry(flowCursor.getStringOrDefault("country"));
        userDetails.setHasNewsletter(flowCursor.getIntOrDefault("hasNewsletter"));
        userDetails.setCodeword(flowCursor.getStringOrDefault("codeword"));
        userDetails.setRfidUids(flowCursor.getStringOrDefault("rfidUids"));
        userDetails.setTicketId(flowCursor.getStringOrDefault("ticketId"));
        userDetails.setBookingHistory(flowCursor.getStringOrDefault("bookingHistory"));
        userDetails.setBookingHistoryLastUpdate(flowCursor.getLongOrDefault("bookingHistoryLastUpdate"));
        userDetails.setSubscriptionDailySecondsUsed(flowCursor.getIntOrDefault("subscriptionDailySecondsUsed"));
        userDetails.setSubscriptionPurchaseTimestamp(flowCursor.getLongOrDefault("subscriptionPurchaseTimestamp"));
    }

    public final boolean exists(UserDetails userDetails, DatabaseWrapper databaseWrapper) {
        return SQLite.selectCountOf(new IProperty[0]).from(UserDetails.class).where(getPrimaryConditionClause(userDetails)).hasData(databaseWrapper);
    }

    public final OperatorGroup getPrimaryConditionClause(UserDetails userDetails) {
        OperatorGroup clause = OperatorGroup.clause();
        clause.and(id.eq(Integer.valueOf(userDetails.getId())));
        return clause;
    }
}
