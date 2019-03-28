package com.mevo.app.data.repository;

import android.location.Location;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;
import com.mevo.app.tools.location.LocationCallback;

final /* synthetic */ class NextbikeApiRepository$$Lambda$3 implements LocationCallback {
    private final NextbikeApiRepository arg$1;
    private final ResponseListener arg$2;
    private final String arg$3;
    private final String arg$4;

    NextbikeApiRepository$$Lambda$3(NextbikeApiRepository nextbikeApiRepository, ResponseListener responseListener, String str, String str2) {
        this.arg$1 = nextbikeApiRepository;
        this.arg$2 = responseListener;
        this.arg$3 = str;
        this.arg$4 = str2;
    }

    public void onReceive(Location location) {
        this.arg$1.lambda$returnBikeOutsideStation$34$NextbikeApiRepository(this.arg$2, this.arg$3, this.arg$4, location);
    }
}
