package com.mevo.app.presentation.register.register_step_one;

import android.util.Pair;
import com.mevo.app.presentation.custom_views.CustomInput.Validator;

final /* synthetic */ class RegisterStepOne$$Lambda$0 implements Validator {
    private final RegisterStepOne arg$1;

    RegisterStepOne$$Lambda$0(RegisterStepOne registerStepOne) {
        this.arg$1 = registerStepOne;
    }

    public Pair checkValid(String str) {
        return this.arg$1.lambda$setListeners$305$RegisterStepOne(str);
    }
}