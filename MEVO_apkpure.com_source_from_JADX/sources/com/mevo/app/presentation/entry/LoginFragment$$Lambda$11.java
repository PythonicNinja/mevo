package com.mevo.app.presentation.entry;

import android.util.Pair;
import com.mevo.app.presentation.custom_views.CustomInput.Validator;

final /* synthetic */ class LoginFragment$$Lambda$11 implements Validator {
    private final LoginFragment arg$1;

    LoginFragment$$Lambda$11(LoginFragment loginFragment) {
        this.arg$1 = loginFragment;
    }

    public Pair checkValid(String str) {
        return this.arg$1.lambda$setListeners$168$LoginFragment(str);
    }
}
