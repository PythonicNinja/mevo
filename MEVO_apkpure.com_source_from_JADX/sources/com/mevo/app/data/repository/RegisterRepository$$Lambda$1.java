package com.mevo.app.data.repository;

import com.annimon.stream.function.Function;
import com.mevo.app.data.model.AgreementWithConsent;

final /* synthetic */ class RegisterRepository$$Lambda$1 implements Function {
    static final Function $instance = new RegisterRepository$$Lambda$1();

    private RegisterRepository$$Lambda$1() {
    }

    public Object apply(Object obj) {
        return Boolean.valueOf(((AgreementWithConsent) obj).isConsent());
    }
}
