package com.mevo.app.presentation.main.return_bike;

import android.location.Location;
import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.Place;
import com.mevo.app.presentation.main.return_bike.ReturnStationsAdapter.C04501;

final /* synthetic */ class ReturnStationsAdapter$1$$Lambda$0 implements Predicate {
    private final Location arg$1;

    ReturnStationsAdapter$1$$Lambda$0(Location location) {
        this.arg$1 = location;
    }

    public boolean test(Object obj) {
        return C04501.lambda$performFiltering$248$ReturnStationsAdapter$1(this.arg$1, (Place) obj);
    }
}
