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

public final class User_Table extends ModelAdapter<User> {
    public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{id, phoneNumber, loginkey, domain, customerCardIds, isActive, language, currency, credits, payment, ticketIds, seconds};
    public static final Property<Integer> credits = new Property(User.class, "credits");
    public static final Property<String> currency = new Property(User.class, Param.CURRENCY);
    public static final Property<String> customerCardIds = new Property(User.class, "customerCardIds");
    public static final Property<String> domain = new Property(User.class, "domain");
    public static final Property<Integer> id = new Property(User.class, Name.MARK);
    public static final Property<Integer> isActive = new Property(User.class, "isActive");
    public static final Property<String> language = new Property(User.class, "language");
    public static final Property<String> loginkey = new Property(User.class, "loginkey");
    public static final Property<String> payment = new Property(User.class, "payment");
    public static final Property<String> phoneNumber = new Property(User.class, "phoneNumber");
    public static final Property<Integer> seconds = new Property(User.class, "seconds");
    public static final Property<String> ticketIds = new Property(User.class, "ticketIds");

    public final String getCompiledStatementQuery() {
        return "INSERT INTO `User`(`id`,`phoneNumber`,`loginkey`,`domain`,`customerCardIds`,`isActive`,`language`,`currency`,`credits`,`payment`,`ticketIds`,`seconds`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    public final String getCreationQuery() {
        return "CREATE TABLE IF NOT EXISTS `User`(`id` INTEGER, `phoneNumber` TEXT, `loginkey` TEXT, `domain` TEXT, `customerCardIds` TEXT, `isActive` INTEGER, `language` TEXT, `currency` TEXT, `credits` INTEGER, `payment` TEXT, `ticketIds` TEXT, `seconds` INTEGER, PRIMARY KEY(`id`))";
    }

    public final String getDeleteStatementQuery() {
        return "DELETE FROM `User` WHERE `id`=?";
    }

    public final String getTableName() {
        return "`User`";
    }

    public final String getUpdateStatementQuery() {
        return "UPDATE `User` SET `id`=?,`phoneNumber`=?,`loginkey`=?,`domain`=?,`customerCardIds`=?,`isActive`=?,`language`=?,`currency`=?,`credits`=?,`payment`=?,`ticketIds`=?,`seconds`=? WHERE `id`=?";
    }

    public User_Table(DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }

    public final Class<User> getModelClass() {
        return User.class;
    }

    public final User newInstance() {
        return new User();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.raizlabs.android.dbflow.sql.language.property.Property getProperty(java.lang.String r2) {
        /*
        r1 = this;
        r2 = com.raizlabs.android.dbflow.sql.QueryBuilder.quoteIfNeeded(r2);
        r0 = r2.hashCode();
        switch(r0) {
            case -1027834353: goto L_0x0081;
            case -773478879: goto L_0x0076;
            case -724238007: goto L_0x006c;
            case -334254214: goto L_0x0061;
            case -167099480: goto L_0x0057;
            case 2964037: goto L_0x004d;
            case 105938790: goto L_0x0042;
            case 867964208: goto L_0x0038;
            case 890940522: goto L_0x002e;
            case 1016434230: goto L_0x0024;
            case 1380565556: goto L_0x0018;
            case 1643616188: goto L_0x000d;
            default: goto L_0x000b;
        };
    L_0x000b:
        goto L_0x008b;
    L_0x000d:
        r0 = "`domain`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x008b;
    L_0x0015:
        r2 = 3;
        goto L_0x008c;
    L_0x0018:
        r0 = "`ticketIds`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x008b;
    L_0x0020:
        r2 = 10;
        goto L_0x008c;
    L_0x0024:
        r0 = "`customerCardIds`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x008b;
    L_0x002c:
        r2 = 4;
        goto L_0x008c;
    L_0x002e:
        r0 = "`loginkey`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x008b;
    L_0x0036:
        r2 = 2;
        goto L_0x008c;
    L_0x0038:
        r0 = "`isActive`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x008b;
    L_0x0040:
        r2 = 5;
        goto L_0x008c;
    L_0x0042:
        r0 = "`credits`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x008b;
    L_0x004a:
        r2 = 8;
        goto L_0x008c;
    L_0x004d:
        r0 = "`id`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x008b;
    L_0x0055:
        r2 = 0;
        goto L_0x008c;
    L_0x0057:
        r0 = "`language`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x008b;
    L_0x005f:
        r2 = 6;
        goto L_0x008c;
    L_0x0061:
        r0 = "`payment`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x008b;
    L_0x0069:
        r2 = 9;
        goto L_0x008c;
    L_0x006c:
        r0 = "`phoneNumber`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x008b;
    L_0x0074:
        r2 = 1;
        goto L_0x008c;
    L_0x0076:
        r0 = "`seconds`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x008b;
    L_0x007e:
        r2 = 11;
        goto L_0x008c;
    L_0x0081:
        r0 = "`currency`";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x008b;
    L_0x0089:
        r2 = 7;
        goto L_0x008c;
    L_0x008b:
        r2 = -1;
    L_0x008c:
        switch(r2) {
            case 0: goto L_0x00b8;
            case 1: goto L_0x00b5;
            case 2: goto L_0x00b2;
            case 3: goto L_0x00af;
            case 4: goto L_0x00ac;
            case 5: goto L_0x00a9;
            case 6: goto L_0x00a6;
            case 7: goto L_0x00a3;
            case 8: goto L_0x00a0;
            case 9: goto L_0x009d;
            case 10: goto L_0x009a;
            case 11: goto L_0x0097;
            default: goto L_0x008f;
        };
    L_0x008f:
        r2 = new java.lang.IllegalArgumentException;
        r0 = "Invalid column name passed. Ensure you are calling the correct table's column";
        r2.<init>(r0);
        throw r2;
    L_0x0097:
        r2 = seconds;
        return r2;
    L_0x009a:
        r2 = ticketIds;
        return r2;
    L_0x009d:
        r2 = payment;
        return r2;
    L_0x00a0:
        r2 = credits;
        return r2;
    L_0x00a3:
        r2 = currency;
        return r2;
    L_0x00a6:
        r2 = language;
        return r2;
    L_0x00a9:
        r2 = isActive;
        return r2;
    L_0x00ac:
        r2 = customerCardIds;
        return r2;
    L_0x00af:
        r2 = domain;
        return r2;
    L_0x00b2:
        r2 = loginkey;
        return r2;
    L_0x00b5:
        r2 = phoneNumber;
        return r2;
    L_0x00b8:
        r2 = id;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.model.User_Table.getProperty(java.lang.String):com.raizlabs.android.dbflow.sql.language.property.Property");
    }

    public final IProperty[] getAllColumnProperties() {
        return ALL_COLUMN_PROPERTIES;
    }

    public final void bindToInsertValues(ContentValues contentValues, User user) {
        contentValues.put("`id`", Integer.valueOf(user.getId()));
        String str = null;
        contentValues.put("`phoneNumber`", user.getPhoneNumber() != null ? user.getPhoneNumber() : null);
        contentValues.put("`loginkey`", user.getLoginkey() != null ? user.getLoginkey() : null);
        contentValues.put("`domain`", user.getDomain() != null ? user.getDomain() : null);
        contentValues.put("`customerCardIds`", user.getCustomerCardIds() != null ? user.getCustomerCardIds() : null);
        contentValues.put("`isActive`", Integer.valueOf(user.getIsActive()));
        contentValues.put("`language`", user.getLanguage() != null ? user.getLanguage() : null);
        contentValues.put("`currency`", user.getCurrency() != null ? user.getCurrency() : null);
        contentValues.put("`credits`", Integer.valueOf(user.getCredits()));
        contentValues.put("`payment`", user.getPayment() != null ? user.getPayment() : null);
        String str2 = "`ticketIds`";
        if (user.getTicketIds() != null) {
            str = user.getTicketIds();
        }
        contentValues.put(str2, str);
        contentValues.put("`seconds`", Integer.valueOf(user.getSeconds()));
    }

    public final void bindToInsertStatement(DatabaseStatement databaseStatement, User user, int i) {
        databaseStatement.bindLong(i + 1, (long) user.getId());
        databaseStatement.bindStringOrNull(i + 2, user.getPhoneNumber());
        databaseStatement.bindStringOrNull(i + 3, user.getLoginkey());
        databaseStatement.bindStringOrNull(i + 4, user.getDomain());
        databaseStatement.bindStringOrNull(i + 5, user.getCustomerCardIds());
        databaseStatement.bindLong(i + 6, (long) user.getIsActive());
        databaseStatement.bindStringOrNull(i + 7, user.getLanguage());
        databaseStatement.bindStringOrNull(i + 8, user.getCurrency());
        databaseStatement.bindLong(i + 9, (long) user.getCredits());
        databaseStatement.bindStringOrNull(i + 10, user.getPayment());
        databaseStatement.bindStringOrNull(i + 11, user.getTicketIds());
        databaseStatement.bindLong(i + 12, (long) user.getSeconds());
    }

    public final void bindToUpdateStatement(DatabaseStatement databaseStatement, User user) {
        databaseStatement.bindLong(1, (long) user.getId());
        databaseStatement.bindStringOrNull(2, user.getPhoneNumber());
        databaseStatement.bindStringOrNull(3, user.getLoginkey());
        databaseStatement.bindStringOrNull(4, user.getDomain());
        databaseStatement.bindStringOrNull(5, user.getCustomerCardIds());
        databaseStatement.bindLong(6, (long) user.getIsActive());
        databaseStatement.bindStringOrNull(7, user.getLanguage());
        databaseStatement.bindStringOrNull(8, user.getCurrency());
        databaseStatement.bindLong(9, (long) user.getCredits());
        databaseStatement.bindStringOrNull(10, user.getPayment());
        databaseStatement.bindStringOrNull(11, user.getTicketIds());
        databaseStatement.bindLong(12, (long) user.getSeconds());
        databaseStatement.bindLong(13, (long) user.getId());
    }

    public final void bindToDeleteStatement(DatabaseStatement databaseStatement, User user) {
        databaseStatement.bindLong(1, (long) user.getId());
    }

    public final void loadFromCursor(FlowCursor flowCursor, User user) {
        user.setId(flowCursor.getIntOrDefault(Name.MARK));
        user.setPhoneNumber(flowCursor.getStringOrDefault("phoneNumber"));
        user.setLoginkey(flowCursor.getStringOrDefault("loginkey"));
        user.setDomain(flowCursor.getStringOrDefault("domain"));
        user.setCustomerCardIds(flowCursor.getStringOrDefault("customerCardIds"));
        user.setIsActive(flowCursor.getIntOrDefault("isActive"));
        user.setLanguage(flowCursor.getStringOrDefault("language"));
        user.setCurrency(flowCursor.getStringOrDefault(Param.CURRENCY));
        user.setCredits(flowCursor.getIntOrDefault("credits"));
        user.setPayment(flowCursor.getStringOrDefault("payment"));
        user.setTicketIds(flowCursor.getStringOrDefault("ticketIds"));
        user.setSeconds(flowCursor.getIntOrDefault("seconds"));
    }

    public final boolean exists(User user, DatabaseWrapper databaseWrapper) {
        return SQLite.selectCountOf(new IProperty[0]).from(User.class).where(getPrimaryConditionClause(user)).hasData(databaseWrapper);
    }

    public final OperatorGroup getPrimaryConditionClause(User user) {
        OperatorGroup clause = OperatorGroup.clause();
        clause.and(id.eq(Integer.valueOf(user.getId())));
        return clause;
    }
}
