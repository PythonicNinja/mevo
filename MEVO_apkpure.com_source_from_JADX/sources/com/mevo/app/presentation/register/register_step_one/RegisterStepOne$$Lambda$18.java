package com.mevo.app.presentation.register.register_step_one;

import com.annimon.stream.function.Predicate;
import com.mevo.app.presentation.custom_views.CustomInput;

final /* synthetic */ class RegisterStepOne$$Lambda$18 implements Predicate {
    static final Predicate $instance = new RegisterStepOne$$Lambda$18();

    private RegisterStepOne$$Lambda$18() {
    }

    public boolean test(Object obj) {
        return (((CustomInput) obj).isValid().booleanValue() ^ 1);
    }
}
