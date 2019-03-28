package com.annimon.stream.internal;

import com.annimon.stream.function.IntFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Operators {
    private Operators() {
    }

    public static <T> List<T> toList(Iterator<? extends T> it) {
        List<T> arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static <T, R> R[] toArray(Iterator<? extends T> it, IntFunction<R[]> intFunction) {
        it = toList(it);
        int size = it.size();
        Compat.checkMaxArraySize((long) size);
        Object[] objArr = (Object[]) intFunction.apply(size);
        System.arraycopy(it.toArray(Compat.newArray(size, new Object[0])), 0, objArr, 0, size);
        return objArr;
    }

    public static int[] toIntArray(OfInt ofInt) {
        OfInt ofInt2 = new OfInt();
        while (ofInt.hasNext()) {
            ofInt2.accept(ofInt.nextInt());
        }
        return (int[]) ofInt2.asPrimitiveArray();
    }

    public static long[] toLongArray(OfLong ofLong) {
        OfLong ofLong2 = new OfLong();
        while (ofLong.hasNext()) {
            ofLong2.accept(ofLong.nextLong());
        }
        return (long[]) ofLong2.asPrimitiveArray();
    }

    public static double[] toDoubleArray(OfDouble ofDouble) {
        OfDouble ofDouble2 = new OfDouble();
        while (ofDouble.hasNext()) {
            ofDouble2.accept(ofDouble.nextDouble());
        }
        return (double[]) ofDouble2.asPrimitiveArray();
    }
}
