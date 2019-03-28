package com.inverce.mod.core.verification;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Map;

public class Conditions {
    private Conditions() {
    }

    @SafeVarargs
    public static <T> T firstNonNull(@Nullable T t, @NonNull T... tArr) {
        if (t != null) {
            return t;
        }
        for (T t2 : tArr) {
            if (t2 != null) {
                return t2;
            }
        }
        return null;
    }

    public static boolean notNullOrEmpty(@Nullable String str) {
        return (str == null || str.length() <= null) ? null : true;
    }

    public static boolean notNullOrEmpty(@Nullable WeakReference<?> weakReference) {
        return (weakReference == null || weakReference.get() == null) ? null : true;
    }

    public static <T> boolean notNullOrEmpty(@Nullable T[] tArr) {
        return (tArr == null || tArr.length <= null) ? null : 1;
    }

    public static boolean notNullOrEmpty(@Nullable Collection<?> collection) {
        return (collection == null || collection.size() <= null) ? null : true;
    }

    public static boolean notNullOrEmpty(@Nullable Cursor cursor) {
        return (cursor == null || cursor.getCount() <= null) ? null : true;
    }

    public static boolean nullOrEmpty(@Nullable String str) {
        if (str != null) {
            if (str.length() != null) {
                return null;
            }
        }
        return true;
    }

    public static boolean nullOrEmpty(@Nullable WeakReference<?> weakReference) {
        if (weakReference != null) {
            if (weakReference.get() != null) {
                return null;
            }
        }
        return true;
    }

    public static <T> boolean nullOrEmpty(@Nullable T[] tArr) {
        if (tArr != null) {
            if (tArr.length != null) {
                return null;
            }
        }
        return 1;
    }

    public static boolean nullOrEmpty(@Nullable Collection<?> collection) {
        if (collection != null) {
            if (collection.size() != null) {
                return null;
            }
        }
        return true;
    }

    public static boolean nullOrEmpty(@Nullable Map<?, ?> map) {
        if (map != null) {
            if (map.size() != null) {
                return null;
            }
        }
        return true;
    }

    public static boolean nullOrEmpty(@Nullable Cursor cursor) {
        if (cursor != null) {
            if (cursor.getCount() != null) {
                return null;
            }
        }
        return true;
    }

    public static boolean isVisible(@Nullable View view) {
        return (view == null || view.getVisibility() != null) ? null : true;
    }
}
