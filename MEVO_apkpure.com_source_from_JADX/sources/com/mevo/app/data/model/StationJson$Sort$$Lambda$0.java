package com.mevo.app.data.model;

import com.mevo.app.data.model.StationJson.Sort;
import java.util.Comparator;

final /* synthetic */ class StationJson$Sort$$Lambda$0 implements Comparator {
    static final Comparator $instance = new StationJson$Sort$$Lambda$0();

    private StationJson$Sort$$Lambda$0() {
    }

    public int compare(Object obj, Object obj2) {
        return Sort.lambda$byName$4$StationJson$Sort((StationJson) obj, (StationJson) obj2);
    }
}
