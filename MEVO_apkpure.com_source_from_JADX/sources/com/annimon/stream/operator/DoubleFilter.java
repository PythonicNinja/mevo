package com.annimon.stream.operator;

import com.annimon.stream.function.DoublePredicate;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;
import java.util.NoSuchElementException;

public class DoubleFilter extends OfDouble {
    private boolean hasNext;
    private boolean hasNextEvaluated;
    private final OfDouble iterator;
    private double next;
    private final DoublePredicate predicate;

    public DoubleFilter(OfDouble ofDouble, DoublePredicate doublePredicate) {
        this.iterator = ofDouble;
        this.predicate = doublePredicate;
    }

    public boolean hasNext() {
        if (!this.hasNextEvaluated) {
            nextIteration();
            this.hasNextEvaluated = true;
        }
        return this.hasNext;
    }

    public double nextDouble() {
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
            this.next = this.iterator.nextDouble();
            if (this.predicate.test(this.next)) {
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }
}
