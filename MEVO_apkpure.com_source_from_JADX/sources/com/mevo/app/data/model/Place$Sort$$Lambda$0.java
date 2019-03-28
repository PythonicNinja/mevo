package com.mevo.app.data.model;

import com.mevo.app.data.model.Place.Sort;
import java.util.Comparator;

final /* synthetic */ class Place$Sort$$Lambda$0 implements Comparator {
    static final Comparator $instance = new Place$Sort$$Lambda$0();

    private Place$Sort$$Lambda$0() {
    }

    public int compare(Object obj, Object obj2) {
        return Sort.lambda$byName$0$Place$Sort((Place) obj, (Place) obj2);
    }
}
