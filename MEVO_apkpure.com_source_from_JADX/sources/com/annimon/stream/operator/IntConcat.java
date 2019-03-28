package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class IntConcat extends OfInt {
    private boolean firstStreamIsCurrent = true;
    private final OfInt iterator1;
    private final OfInt iterator2;

    public IntConcat(OfInt ofInt, OfInt ofInt2) {
        this.iterator1 = ofInt;
        this.iterator2 = ofInt2;
    }

    public boolean hasNext() {
        if (this.firstStreamIsCurrent) {
            if (this.iterator1.hasNext()) {
                return true;
            }
            this.firstStreamIsCurrent = false;
        }
        return this.iterator2.hasNext();
    }

    public int nextInt() {
        return (this.firstStreamIsCurrent ? this.iterator1 : this.iterator2).nextInt();
    }
}
