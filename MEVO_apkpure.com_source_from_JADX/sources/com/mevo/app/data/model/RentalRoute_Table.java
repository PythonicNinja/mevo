package com.mevo.app.data.model;

import android.content.ContentValues;
import com.google.android.gms.maps.model.LatLng;
import com.mevo.app.data.database.adapters.EncodedRoutePolylineConverter;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.sql.language.property.TypeConvertedProperty;
import com.raizlabs.android.dbflow.sql.language.property.TypeConvertedProperty.TypeConverterGetter;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.util.List;

public final class RentalRoute_Table extends ModelAdapter<RentalRoute> {
    public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{rentalId, rentalRoute};
    public static final Property<Integer> rentalId = new Property(RentalRoute.class, "rentalId");
    public static final TypeConvertedProperty<String, List<LatLng>> rentalRoute = new TypeConvertedProperty(RentalRoute.class, "rentalRoute", true, new C07991());
    private final EncodedRoutePolylineConverter typeConverterEncodedRoutePolylineConverter = new EncodedRoutePolylineConverter();

    /* renamed from: com.mevo.app.data.model.RentalRoute_Table$1 */
    static class C07991 implements TypeConverterGetter {
        C07991() {
        }

        public TypeConverter getTypeConverter(Class<?> cls) {
            return ((RentalRoute_Table) FlowManager.getInstanceAdapter(cls)).typeConverterEncodedRoutePolylineConverter;
        }
    }

    public final String getCompiledStatementQuery() {
        return "INSERT INTO `RentalRoute`(`rentalId`,`rentalRoute`) VALUES (?,?)";
    }

    public final String getCreationQuery() {
        return "CREATE TABLE IF NOT EXISTS `RentalRoute`(`rentalId` INTEGER, `rentalRoute` TEXT, PRIMARY KEY(`rentalId`))";
    }

    public final String getDeleteStatementQuery() {
        return "DELETE FROM `RentalRoute` WHERE `rentalId`=?";
    }

    public final String getTableName() {
        return "`RentalRoute`";
    }

    public final String getUpdateStatementQuery() {
        return "UPDATE `RentalRoute` SET `rentalId`=?,`rentalRoute`=? WHERE `rentalId`=?";
    }

    public RentalRoute_Table(DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }

    public final Class<RentalRoute> getModelClass() {
        return RentalRoute.class;
    }

    public final RentalRoute newInstance() {
        return new RentalRoute();
    }

    public final Property getProperty(String str) {
        str = QueryBuilder.quoteIfNeeded(str);
        int hashCode = str.hashCode();
        if (hashCode != 45942235) {
            if (hashCode == 608389761) {
                if (str.equals("`rentalId`") != null) {
                    str = null;
                    switch (str) {
                        case null:
                            return rentalId;
                        case 1:
                            return rentalRoute;
                        default:
                            throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
                    }
                }
            }
        } else if (str.equals("`rentalRoute`") != null) {
            str = true;
            switch (str) {
                case null:
                    return rentalId;
                case 1:
                    return rentalRoute;
                default:
                    throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
            }
        }
        str = -1;
        switch (str) {
            case null:
                return rentalId;
            case 1:
                return rentalRoute;
            default:
                throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
        }
    }

    public final IProperty[] getAllColumnProperties() {
        return ALL_COLUMN_PROPERTIES;
    }

    public final void bindToInsertValues(ContentValues contentValues, RentalRoute rentalRoute) {
        contentValues.put("`rentalId`", Integer.valueOf(rentalRoute.getRentalId()));
        rentalRoute = rentalRoute.getRentalRoute() != null ? this.typeConverterEncodedRoutePolylineConverter.getDBValue(rentalRoute.getRentalRoute()) : null;
        String str = "`rentalRoute`";
        if (rentalRoute == null) {
            rentalRoute = null;
        }
        contentValues.put(str, rentalRoute);
    }

    public final void bindToInsertStatement(DatabaseStatement databaseStatement, RentalRoute rentalRoute, int i) {
        databaseStatement.bindLong(i + 1, (long) rentalRoute.getRentalId());
        databaseStatement.bindStringOrNull(i + 2, rentalRoute.getRentalRoute() != null ? this.typeConverterEncodedRoutePolylineConverter.getDBValue(rentalRoute.getRentalRoute()) : null);
    }

    public final void bindToUpdateStatement(DatabaseStatement databaseStatement, RentalRoute rentalRoute) {
        databaseStatement.bindLong(1, (long) rentalRoute.getRentalId());
        databaseStatement.bindStringOrNull(2, rentalRoute.getRentalRoute() != null ? this.typeConverterEncodedRoutePolylineConverter.getDBValue(rentalRoute.getRentalRoute()) : null);
        databaseStatement.bindLong(3, (long) rentalRoute.getRentalId());
    }

    public final void bindToDeleteStatement(DatabaseStatement databaseStatement, RentalRoute rentalRoute) {
        databaseStatement.bindLong(1, (long) rentalRoute.getRentalId());
    }

    public final void loadFromCursor(FlowCursor flowCursor, RentalRoute rentalRoute) {
        rentalRoute.setRentalId(flowCursor.getIntOrDefault("rentalId"));
        int columnIndex = flowCursor.getColumnIndex("rentalRoute");
        if (columnIndex == -1 || flowCursor.isNull(columnIndex)) {
            rentalRoute.setRentalRoute(this.typeConverterEncodedRoutePolylineConverter.getModelValue(null));
        } else {
            rentalRoute.setRentalRoute(this.typeConverterEncodedRoutePolylineConverter.getModelValue(flowCursor.getString(columnIndex)));
        }
    }

    public final boolean exists(RentalRoute rentalRoute, DatabaseWrapper databaseWrapper) {
        return SQLite.selectCountOf(new IProperty[0]).from(RentalRoute.class).where(getPrimaryConditionClause(rentalRoute)).hasData(databaseWrapper);
    }

    public final OperatorGroup getPrimaryConditionClause(RentalRoute rentalRoute) {
        OperatorGroup clause = OperatorGroup.clause();
        clause.and(rentalId.eq(Integer.valueOf(rentalRoute.getRentalId())));
        return clause;
    }
}
