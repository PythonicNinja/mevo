package com.mevo.app.data.model;

public class AgreementWithConsent {
    private boolean consent = null;
    private final RegisterAgreement registerAgreement;

    public AgreementWithConsent(RegisterAgreement registerAgreement) {
        this.registerAgreement = registerAgreement;
    }

    public RegisterAgreement getRegisterAgreement() {
        return this.registerAgreement;
    }

    public boolean isConsent() {
        return this.consent;
    }

    public AgreementWithConsent setConsent(boolean z) {
        this.consent = z;
        return this;
    }
}
