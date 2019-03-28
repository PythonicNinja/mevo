package com.raizlabs.android.dbflow.sql.language;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

public class Insert<TModel> extends BaseQueriable<TModel> implements Query {
    private IProperty[] columns;
    private ConflictAction conflictAction = ConflictAction.NONE;
    private From<?> selectFrom;
    private List<Collection<Object>> valuesList;

    public Insert(@NonNull Class<TModel> cls) {
        super(cls);
    }

    @NonNull
    public Insert<TModel> columns(@NonNull String... strArr) {
        this.columns = new IProperty[strArr.length];
        ModelAdapter modelAdapter = FlowManager.getModelAdapter(getTable());
        for (int i = 0; i < strArr.length; i++) {
            this.columns[i] = modelAdapter.getProperty(strArr[i]);
        }
        return this;
    }

    @NonNull
    public Insert<TModel> columns(@NonNull IProperty... iPropertyArr) {
        this.columns = new IProperty[iPropertyArr.length];
        for (int i = 0; i < iPropertyArr.length; i++) {
            this.columns[i] = iPropertyArr[i];
        }
        return this;
    }

    @NonNull
    public Insert<TModel> columns(@NonNull List<IProperty> list) {
        return columns((IProperty[]) list.toArray(new IProperty[list.size()]));
    }

    @NonNull
    public Insert<TModel> asColumns() {
        columns(FlowManager.getModelAdapter(getTable()).getAllColumnProperties());
        return this;
    }

    @NonNull
    public Insert<TModel> asColumnValues() {
        asColumns();
        if (this.columns != null) {
            List arrayList = new ArrayList();
            for (int i = 0; i < this.columns.length; i++) {
                arrayList.add(Operation.EMPTY_PARAM);
            }
            this.valuesList.add(arrayList);
        }
        return this;
    }

    @NonNull
    public Insert<TModel> values(@Nullable Object... objArr) {
        if (this.valuesList == null) {
            this.valuesList = new ArrayList();
        }
        this.valuesList.add(Arrays.asList(objArr));
        return this;
    }

    @NonNull
    public Insert<TModel> values(@NonNull Collection<Object> collection) {
        if (this.valuesList == null) {
            this.valuesList = new ArrayList();
        }
        this.valuesList.add(collection);
        return this;
    }

    @NonNull
    public Insert<TModel> columnValues(@NonNull SQLOperator... sQLOperatorArr) {
        String[] strArr = new String[sQLOperatorArr.length];
        Object[] objArr = new Object[sQLOperatorArr.length];
        for (int i = 0; i < sQLOperatorArr.length; i++) {
            SQLOperator sQLOperator = sQLOperatorArr[i];
            strArr[i] = sQLOperator.columnName();
            objArr[i] = sQLOperator.value();
        }
        return columns(strArr).values(objArr);
    }

    @NonNull
    public Insert<TModel> columnValues(@NonNull OperatorGroup operatorGroup) {
        int size = operatorGroup.size();
        String[] strArr = new String[size];
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            SQLOperator sQLOperator = (SQLOperator) operatorGroup.getConditions().get(i);
            strArr[i] = sQLOperator.columnName();
            objArr[i] = sQLOperator.value();
        }
        return columns(strArr).values(objArr);
    }

    @NonNull
    public Insert<TModel> columnValues(@NonNull ContentValues contentValues) {
        String[] strArr = new String[contentValues.size()];
        Object[] objArr = new Object[contentValues.size()];
        int i = 0;
        for (Entry key : contentValues.valueSet()) {
            String str = (String) key.getKey();
            strArr[i] = str;
            objArr[i] = contentValues.get(str);
            i++;
        }
        return columns(strArr).values(objArr);
    }

    @NonNull
    public Insert<TModel> select(@NonNull From<?> from) {
        this.selectFrom = from;
        return this;
    }

    @NonNull
    public Insert<TModel> or(@NonNull ConflictAction conflictAction) {
        this.conflictAction = conflictAction;
        return this;
    }

    @NonNull
    public Insert<TModel> orReplace() {
        return or(ConflictAction.REPLACE);
    }

    @NonNull
    public Insert<TModel> orRollback() {
        return or(ConflictAction.ROLLBACK);
    }

    @NonNull
    public Insert<TModel> orAbort() {
        return or(ConflictAction.ABORT);
    }

    @NonNull
    public Insert<TModel> orFail() {
        return or(ConflictAction.FAIL);
    }

    @NonNull
    public Insert<TModel> orIgnore() {
        return or(ConflictAction.IGNORE);
    }

    public long executeUpdateDelete(@NonNull DatabaseWrapper databaseWrapper) {
        throw new IllegalStateException("Cannot call executeUpdateDelete() from an Insert");
    }

    public long executeUpdateDelete() {
        throw new IllegalStateException("Cannot call executeUpdateDelete() from an Insert");
    }

    public String getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder("INSERT ");
        if (!(this.conflictAction == null || this.conflictAction.equals(ConflictAction.NONE))) {
            queryBuilder.append(Operation.OR).appendSpaceSeparated(this.conflictAction);
        }
        queryBuilder.append("INTO").appendSpace().append(FlowManager.getTableName(getTable()));
        if (this.columns != null) {
            queryBuilder.append("(").appendArray((Object[]) this.columns).append(")");
        }
        if (this.selectFrom != null) {
            queryBuilder.appendSpace().append(this.selectFrom.getQuery());
        } else {
            StringBuilder stringBuilder;
            if (this.valuesList != null) {
                if (this.valuesList.size() >= 1) {
                    if (this.columns != null) {
                        for (Collection size : this.valuesList) {
                            if (size.size() != this.columns.length) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("The Insert of ");
                                stringBuilder.append(FlowManager.getTableName(getTable()));
                                stringBuilder.append(" when specifyingcolumns needs to have the same amount of values and columns");
                                throw new IllegalStateException(stringBuilder.toString());
                            }
                        }
                    }
                    queryBuilder.append(" VALUES(");
                    for (int i = 0; i < this.valuesList.size(); i++) {
                        if (i > 0) {
                            queryBuilder.append(",(");
                        }
                        queryBuilder.append(BaseOperator.joinArguments((CharSequence) ", ", (Iterable) this.valuesList.get(i))).append(")");
                    }
                }
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("The insert of ");
            stringBuilder.append(FlowManager.getTableName(getTable()));
            stringBuilder.append(" should haveat least one value specified for the insert");
            throw new IllegalStateException(stringBuilder.toString());
        }
        return queryBuilder.getQuery();
    }

    @NonNull
    public Action getPrimaryAction() {
        return Action.INSERT;
    }
}
