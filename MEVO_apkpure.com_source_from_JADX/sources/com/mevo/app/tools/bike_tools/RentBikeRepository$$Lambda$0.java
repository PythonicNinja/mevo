package com.mevo.app.tools.bike_tools;

import com.mevo.app.data.model.UserDetails;
import com.mevo.app.tools.UserManager.UserDetailsListener;
import retrofit2.Call;

final /* synthetic */ class RentBikeRepository$$Lambda$0 implements UserDetailsListener {
    private final RentBikeRepository arg$1;

    RentBikeRepository$$Lambda$0(RentBikeRepository rentBikeRepository) {
        this.arg$1 = rentBikeRepository;
    }

    public void onReceive(boolean z, Call call, UserDetails userDetails) {
        this.arg$1.lambda$getUserDetails$223$RentBikeRepository(z, call, userDetails);
    }
}
