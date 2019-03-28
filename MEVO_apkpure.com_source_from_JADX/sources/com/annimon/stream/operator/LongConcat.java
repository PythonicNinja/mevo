package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongConcat extends OfLong {
    private boolean firstStreamIsCurrent = true;
    private final OfLong iterator1;
    private final OfLong iterator2;

    public LongConcat(OfLong ofLong, OfLong ofLong2) {
        this.iterator1 = ofLong;
        this.iterator2 = ofLong2;
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

    public long nextLong() {
        return (this.firstStreamIsCurrent ? this.iterator1 : this.iterator2).nextLong();
    }
}
