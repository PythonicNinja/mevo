package com.inverce.mod.core.utilities;

import java.util.Comparator;

final /* synthetic */ class Compare$$Lambda$2 implements Comparator {
    static final Comparator $instance = new Compare$$Lambda$2();

    private Compare$$Lambda$2() {
    }

    public int compare(Object obj, Object obj2) {
        return Integer.compare(((Integer) obj).intValue(), ((Integer) obj2).intValue());
    }
}
