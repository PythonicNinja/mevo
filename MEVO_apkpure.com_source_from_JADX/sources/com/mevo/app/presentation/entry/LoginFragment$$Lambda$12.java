package com.mevo.app.presentation.entry;

import com.mevo.app.data.model.UserDetails;
import com.mevo.app.tools.UserManager.UserDetailsListener;
import retrofit2.Call;

final /* synthetic */ class LoginFragment$$Lambda$12 implements UserDetailsListener {
    private final LoginFragment arg$1;

    LoginFragment$$Lambda$12(LoginFragment loginFragment) {
        this.arg$1 = loginFragment;
    }

    public void onReceive(boolean z, Call call, UserDetails userDetails) {
        this.arg$1.lambda$fetchUserDetailsAndGo$169$LoginFragment(z, call, userDetails);
    }
}
