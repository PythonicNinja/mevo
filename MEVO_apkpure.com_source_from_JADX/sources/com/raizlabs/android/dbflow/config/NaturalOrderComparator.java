package com.raizlabs.android.dbflow.config;

import java.util.Comparator;

public class NaturalOrderComparator implements Comparator<Object> {
    public int compare(Object obj, Object obj2) {
        obj = obj.toString();
        obj2 = obj2.toString();
        int i = 0;
        int i2 = 0;
        while (true) {
            char charAt = charAt(obj, i);
            char charAt2 = charAt(obj2, i2);
            int i3 = i;
            i = 0;
            while (true) {
                if (!Character.isSpaceChar(charAt)) {
                    if (charAt != '0') {
                        break;
                    }
                }
                i = charAt == '0' ? i + 1 : 0;
                i3++;
                charAt = charAt(obj, i3);
            }
            int i4 = i2;
            i2 = 0;
            while (true) {
                if (!Character.isSpaceChar(charAt2)) {
                    if (charAt2 != '0') {
                        break;
                    }
                }
                i2 = charAt2 == '0' ? i2 + 1 : 0;
                i4++;
                charAt2 = charAt(obj2, i4);
            }
            if (Character.isDigit(charAt) && Character.isDigit(charAt2)) {
                int compareRight = compareRight(obj.substring(i3), obj2.substring(i4));
                if (compareRight != 0) {
                    return compareRight;
                }
            }
            if (charAt == '\u0000' && charAt2 == '\u0000') {
                return i - i2;
            }
            if (charAt < charAt2) {
                return -1;
            }
            if (charAt > charAt2) {
                return 1;
            }
            i = i3 + 1;
            i2 = i4 + 1;
        }
    }

    static char charAt(String str, int i) {
        if (i >= str.length()) {
            return null;
        }
        return str.charAt(i);
    }

    int compareRight(String str, String str2) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            char charAt = charAt(str, i);
            char charAt2 = charAt(str2, i2);
            if (!Character.isDigit(charAt) && !Character.isDigit(charAt2)) {
                return i3;
            }
            if (!Character.isDigit(charAt)) {
                return -1;
            }
            if (!Character.isDigit(charAt2)) {
                return 1;
            }
            if (charAt < charAt2) {
                if (i3 == 0) {
                    i3 = -1;
                }
            } else if (charAt > charAt2) {
                if (i3 == 0) {
                    i3 = 1;
                }
            } else if (charAt == '\u0000' && charAt2 == '\u0000') {
                return i3;
            }
            i++;
            i2++;
        }
    }
}
