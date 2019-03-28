package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.sql.language.property.PropertyFactory;
import java.util.ArrayList;
import java.util.List;

public class Method extends Property {
    private final IProperty methodProperty;
    private List<String> operationsList;
    private final List<IProperty> propertyList;

    public static class Cast {
        private final IProperty property;

        private Cast(@NonNull IProperty iProperty) {
            this.property = iProperty;
        }

        public IProperty as(SQLiteType sQLiteType) {
            Property property = new Property(this.property.getTable(), this.property.getNameAlias().newBuilder().shouldAddIdentifierToAliasName(false).as(sQLiteType.name()).build());
            return new Method("CAST", property);
        }
    }

    @NonNull
    public static Method avg(IProperty... iPropertyArr) {
        return new Method("AVG", iPropertyArr);
    }

    @NonNull
    public static Method count(IProperty... iPropertyArr) {
        return new Method("COUNT", iPropertyArr);
    }

    @NonNull
    public static Method group_concat(IProperty... iPropertyArr) {
        return new Method("GROUP_CONCAT", iPropertyArr);
    }

    @NonNull
    public static Method max(IProperty... iPropertyArr) {
        return new Method("MAX", iPropertyArr);
    }

    @NonNull
    public static Method min(IProperty... iPropertyArr) {
        return new Method("MIN", iPropertyArr);
    }

    @NonNull
    public static Method sum(IProperty... iPropertyArr) {
        return new Method("SUM", iPropertyArr);
    }

    @NonNull
    public static Method total(IProperty... iPropertyArr) {
        return new Method("TOTAL", iPropertyArr);
    }

    @NonNull
    public static Cast cast(@NonNull IProperty iProperty) {
        return new Cast(iProperty);
    }

    @NonNull
    public static Method replace(@NonNull IProperty iProperty, String str, String str2) {
        return new Method("REPLACE", iProperty, PropertyFactory.from((Object) str), PropertyFactory.from((Object) str2));
    }

    public static Method strftime(@NonNull String str, @NonNull String str2, String... strArr) {
        List arrayList = new ArrayList();
        arrayList.add(PropertyFactory.from((Object) str));
        arrayList.add(PropertyFactory.from((Object) str2));
        for (Object from : strArr) {
            arrayList.add(PropertyFactory.from(from));
        }
        return new Method("strftime", (IProperty[]) arrayList.toArray(new IProperty[arrayList.size()]));
    }

    public static Method datetime(long j, String... strArr) {
        List arrayList = new ArrayList();
        arrayList.add(PropertyFactory.from(j));
        for (Object from : strArr) {
            arrayList.add(PropertyFactory.from(from));
        }
        return new Method("datetime", (IProperty[]) arrayList.toArray(new IProperty[arrayList.size()]));
    }

    public static Method date(@NonNull String str, String... strArr) {
        List arrayList = new ArrayList();
        arrayList.add(PropertyFactory.from((Object) str));
        for (Object from : strArr) {
            arrayList.add(PropertyFactory.from(from));
        }
        return new Method("date", (IProperty[]) arrayList.toArray(new IProperty[arrayList.size()]));
    }

    public static Method ifNull(@NonNull IProperty iProperty, @NonNull IProperty iProperty2) {
        return new Method("IFNULL", iProperty, iProperty2);
    }

    public static Method nullIf(@NonNull IProperty iProperty, @NonNull IProperty iProperty2) {
        return new Method("NULLIF", iProperty, iProperty2);
    }

    public Method(IProperty... iPropertyArr) {
        this(null, iPropertyArr);
    }

    public Method(String str, IProperty... iPropertyArr) {
        super(null, (String) null);
        this.propertyList = new ArrayList();
        this.operationsList = new ArrayList();
        this.methodProperty = new Property(null, NameAlias.rawBuilder(str).build());
        if (iPropertyArr.length == null) {
            this.propertyList.add(Property.ALL_PROPERTY);
            return;
        }
        for (IProperty addProperty : iPropertyArr) {
            addProperty(addProperty);
        }
    }

    @NonNull
    public Method plus(@NonNull IProperty iProperty) {
        return append(iProperty, " +");
    }

    @NonNull
    public Method minus(@NonNull IProperty iProperty) {
        return append(iProperty, " -");
    }

    @NonNull
    public Property div(@NonNull IProperty iProperty) {
        return append(iProperty, " /");
    }

    public Property times(@NonNull IProperty iProperty) {
        return append(iProperty, " *");
    }

    @NonNull
    public Property rem(@NonNull IProperty iProperty) {
        return append(iProperty, " %");
    }

    public Method addProperty(@NonNull IProperty iProperty) {
        return append(iProperty, ",");
    }

    public Method append(IProperty iProperty, String str) {
        if (this.propertyList.size() == 1 && this.propertyList.get(0) == Property.ALL_PROPERTY) {
            this.propertyList.remove(0);
        }
        this.propertyList.add(iProperty);
        this.operationsList.add(str);
        return this;
    }

    @NonNull
    protected List<IProperty> getPropertyList() {
        return this.propertyList;
    }

    @NonNull
    public NameAlias getNameAlias() {
        if (this.nameAlias == null) {
            String query = this.methodProperty.getQuery();
            if (query == null) {
                query = "";
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(query);
            stringBuilder.append("(");
            query = stringBuilder.toString();
            List propertyList = getPropertyList();
            for (int i = 0; i < propertyList.size(); i++) {
                StringBuilder stringBuilder2;
                IProperty iProperty = (IProperty) propertyList.get(i);
                if (i > 0) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(query);
                    stringBuilder2.append((String) this.operationsList.get(i));
                    stringBuilder2.append(" ");
                    query = stringBuilder2.toString();
                }
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(query);
                stringBuilder2.append(iProperty.toString());
                query = stringBuilder2.toString();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(query);
            stringBuilder.append(")");
            this.nameAlias = NameAlias.rawBuilder(stringBuilder.toString()).build();
        }
        return this.nameAlias;
    }
}
