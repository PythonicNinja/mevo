package com.mevo.app.data.repository;

import com.annimon.stream.function.Function;
import com.mevo.app.data.model.AgreementWithConsent;

final /* synthetic */ class RegisterRepository$$Lambda$4 implements Function {
    static final Function $instance = new RegisterRepository$$Lambda$4();

    private RegisterRepository$$Lambda$4() {
    }

    public Object apply(Object obj) {
        return Integer.valueOf(((AgreementWithConsent) obj).getRegisterAgreement().agreementId);
    }
}
