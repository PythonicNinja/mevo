package com.mevo.app.presentation.register.register_step_two;

import com.annimon.stream.function.Function;
import com.mevo.app.data.model.AgreementWithConsent;
import com.mevo.app.data.model.RegisterAgreement;

final /* synthetic */ class RegisterStepTwo$$Lambda$0 implements Function {
    static final Function $instance = new RegisterStepTwo$$Lambda$0();

    private RegisterStepTwo$$Lambda$0() {
    }

    public Object apply(Object obj) {
        return new AgreementWithConsent((RegisterAgreement) obj);
    }
}
