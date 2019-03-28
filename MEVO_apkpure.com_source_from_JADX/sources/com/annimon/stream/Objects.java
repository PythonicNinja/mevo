package com.annimon.stream;

import java.util.Comparator;

public final class Objects {
    public static int compareInt(int i, int i2) {
        return i < i2 ? -1 : i == i2 ? 0 : 1;
    }

    public static int compareLong(long j, long j2) {
        return j < j2 ? -1 : j == j2 ? 0 : 1;
    }

    private Objects() {
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj != obj2) {
            if (obj == null || obj.equals(obj2) == null) {
                return null;
            }
        }
        return true;
    }

    public static int hashCode(Object obj) {
        return obj != null ? obj.hashCode() : null;
    }

    public static int hash(Object... objArr) {
        int i = 0;
        if (objArr == null) {
            return 0;
        }
        int i2 = 1;
        while (i < objArr.length) {
            i2 = (i2 * 31) + hashCode(objArr[i]);
            i++;
        }
        return i2;
    }

    public static String toString(Object obj, String str) {
        return obj != null ? obj.toString() : str;
    }

    public static <T> int compare(T t, T t2, Comparator<? super T> comparator) {
        return t == t2 ? null : comparator.compare(t, t2);
    }

    public static <T> T requireNonNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
