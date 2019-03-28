package com.annimon.stream.operator;

import com.annimon.stream.function.IntPredicate;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;
import java.util.NoSuchElementException;

public class IntFilter extends OfInt {
    private boolean hasNext;
    private boolean hasNextEvaluated;
    private final OfInt iterator;
    private int next;
    private final IntPredicate predicate;

    public IntFilter(OfInt ofInt, IntPredicate intPredicate) {
        this.iterator = ofInt;
        this.predicate = intPredicate;
    }

    public boolean hasNext() {
        if (!this.hasNextEvaluated) {
            nextIteration();
            this.hasNextEvaluated = true;
        }
        return this.hasNext;
    }

    public int nextInt() {
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
            this.next = this.iterator.nextInt();
            if (this.predicate.test(this.next)) {
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }
}
