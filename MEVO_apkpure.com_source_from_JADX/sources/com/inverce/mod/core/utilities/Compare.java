package com.inverce.mod.core.utilities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inverce.mod.core.functional.IFunction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Compare<T> implements Comparator<T> {
    private List<Comparator<T>> comparators = new ArrayList();

    private <Y> int compareNulls(@Nullable Y y, @Nullable Y y2) {
        if (y != null) {
            return y2 == null ? 1 : Integer.MAX_VALUE;
        } else {
            return y2 == null ? 0 : -1;
        }
    }

    private <Y> void add(@NonNull IFunction<T, Y> iFunction, boolean z, @NonNull Comparator<Y> comparator) {
        this.comparators.add(new Compare$$Lambda$0(this, z, iFunction, comparator));
    }

    final /* synthetic */ int lambda$add$0$Compare(boolean z, @NonNull IFunction iFunction, @NonNull Comparator comparator, Object obj, Object obj2) {
        z = z ? true : true;
        int compareNulls = compareNulls(obj, obj2);
        if (compareNulls != Integer.MAX_VALUE) {
            return compareNulls * z;
        }
        obj = iFunction.apply(obj);
        iFunction = iFunction.apply(obj2);
        obj2 = compareNulls(obj, iFunction);
        if (obj2 != 2147483647) {
            return obj2 * z;
        }
        return comparator.compare(obj, iFunction) * z;
    }

    @NonNull
    public Compare<T> byString(@NonNull IFunction<T, String> iFunction, boolean z) {
        add(iFunction, z, Compare$$Lambda$1.$instance);
        return this;
    }

    @NonNull
    public Compare<T> byInt(@NonNull IFunction<T, Integer> iFunction, boolean z) {
        add(iFunction, z, Compare$$Lambda$2.$instance);
        return this;
    }

    public int compare(T t, T t2) {
        for (int i = 0; i < this.comparators.size(); i++) {
            int compare = ((Comparator) this.comparators.get(i)).compare(t, t2);
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }
}
