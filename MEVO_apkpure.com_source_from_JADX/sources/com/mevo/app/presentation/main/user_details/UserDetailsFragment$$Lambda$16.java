package com.mevo.app.presentation.main.user_details;

import com.mevo.app.data.model.UserDetails;
import com.mevo.app.tools.UserManager.UserDetailsListener;
import retrofit2.Call;

final /* synthetic */ class UserDetailsFragment$$Lambda$16 implements UserDetailsListener {
    private final UserDetailsFragment arg$1;

    UserDetailsFragment$$Lambda$16(UserDetailsFragment userDetailsFragment) {
        this.arg$1 = userDetailsFragment;
    }

    public void onReceive(boolean z, Call call, UserDetails userDetails) {
        this.arg$1.lambda$fetchCurrentUserDetails$301$UserDetailsFragment(z, call, userDetails);
    }
}
