package com.mevo.app.presentation.main.user_details;

import android.util.Pair;
import com.mevo.app.presentation.custom_views.CustomInput.Validator;

final /* synthetic */ class UserDetailsFragment$$Lambda$7 implements Validator {
    private final UserDetailsFragment arg$1;

    UserDetailsFragment$$Lambda$7(UserDetailsFragment userDetailsFragment) {
        this.arg$1 = userDetailsFragment;
    }

    public Pair checkValid(String str) {
        return this.arg$1.lambda$setListeners$292$UserDetailsFragment(str);
    }
}
