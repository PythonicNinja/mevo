package com.inverce.mod.core.utilities;

import com.inverce.mod.core.functional.IFunction;
import java.util.Comparator;

final /* synthetic */ class Compare$$Lambda$0 implements Comparator {
    private final Compare arg$1;
    private final boolean arg$2;
    private final IFunction arg$3;
    private final Comparator arg$4;

    Compare$$Lambda$0(Compare compare, boolean z, IFunction iFunction, Comparator comparator) {
        this.arg$1 = compare;
        this.arg$2 = z;
        this.arg$3 = iFunction;
        this.arg$4 = comparator;
    }

    public int compare(Object obj, Object obj2) {
        return this.arg$1.lambda$add$0$Compare(this.arg$2, this.arg$3, this.arg$4, obj, obj2);
    }
}
