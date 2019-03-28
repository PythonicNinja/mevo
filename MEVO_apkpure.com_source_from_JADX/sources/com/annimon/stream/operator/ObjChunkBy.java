package com.annimon.stream.operator;

import com.annimon.stream.function.Function;
import com.annimon.stream.iterator.LsaIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjChunkBy<T, K> extends LsaIterator<List<T>> {
    private final Function<? super T, ? extends K> classifier;
    private final Iterator<? extends T> iterator;
    private T next;
    private boolean peekedNext;

    public ObjChunkBy(Iterator<? extends T> it, Function<? super T, ? extends K> function) {
        this.iterator = it;
        this.classifier = function;
    }

    public boolean hasNext() {
        if (!this.peekedNext) {
            if (!this.iterator.hasNext()) {
                return false;
            }
        }
        return true;
    }

    public List<T> nextIteration() {
        Object apply = this.classifier.apply(peek());
        List<T> arrayList = new ArrayList();
        do {
            arrayList.add(takeNext());
            if (!this.iterator.hasNext()) {
                break;
            }
        } while (apply.equals(this.classifier.apply(peek())));
        return arrayList;
    }

    private T takeNext() {
        T peek = peek();
        this.peekedNext = false;
        return peek;
    }

    private T peek() {
        if (!this.peekedNext) {
            this.next = this.iterator.next();
            this.peekedNext = true;
        }
        return this.next;
    }
}
