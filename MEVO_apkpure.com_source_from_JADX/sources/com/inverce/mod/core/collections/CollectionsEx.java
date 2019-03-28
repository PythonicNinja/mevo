package com.inverce.mod.core.collections;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CollectionsEx {
    @Nullable
    public static <T> List<T> join(@Nullable List<? extends T> list, @Nullable List<? extends T> list2) {
        List arrayList = list != null ? new ArrayList(list) : new ArrayList();
        if (list2 != null) {
            arrayList.addAll(list2);
        }
        return arrayList;
    }

    public static <T> boolean equals(@Nullable List<? extends T> list, @Nullable List<? extends T> list2) {
        boolean z = true;
        if (list != null) {
            if (list2 != null) {
                if (list.size() != list2.size()) {
                    return false;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (!list.get(i).equals(list2.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        if (list != list2) {
            z = false;
        }
        return z;
    }
}
