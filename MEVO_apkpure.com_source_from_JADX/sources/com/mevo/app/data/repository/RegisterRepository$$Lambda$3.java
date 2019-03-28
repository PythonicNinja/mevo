package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.AgreementWithConsent;

final /* synthetic */ class RegisterRepository$$Lambda$3 implements Predicate {
    static final Predicate $instance = new RegisterRepository$$Lambda$3();

    private RegisterRepository$$Lambda$3() {
    }

    public boolean test(Object obj) {
        return ((AgreementWithConsent) obj).isConsent();
    }
}
