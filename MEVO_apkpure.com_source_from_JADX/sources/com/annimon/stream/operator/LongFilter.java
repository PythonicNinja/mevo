package com.annimon.stream.operator;

import com.annimon.stream.function.LongPredicate;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;
import java.util.NoSuchElementException;

public class LongFilter extends OfLong {
    private boolean hasNext;
    private boolean hasNextEvaluated;
    private final OfLong iterator;
    private long next;
    private final LongPredicate predicate;

    public LongFilter(OfLong ofLong, LongPredicate longPredicate) {
        this.iterator = ofLong;
        this.predicate = longPredicate;
    }

    public boolean hasNext() {
        if (!this.hasNextEvaluated) {
            nextIteration();
            this.hasNextEvaluated = true;
        }
        return this.hasNext;
    }

    public long nextLong() {
        if (!this.hasNextEvaluated) {
            this.hasNext = hasNext();
        }
        if (this.hasNext) {
            this.hasNextEvaluated = false;
            return this.next;
        }
        throw new NoSuchElementException();
    }

    private void nextIteration() {
        while (this.iterator.hasNext()) {
            this.next = this.iterator.nextLong();
            if (this.predicate.test(this.next)) {
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }
}
