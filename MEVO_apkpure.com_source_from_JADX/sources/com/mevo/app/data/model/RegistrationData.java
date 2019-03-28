package com.mevo.app.data.model;

import com.annimon.stream.Stream;
import com.mevo.app.constants.RegisterAgreements;
import java.util.List;

public class RegistrationData {
    public List<AgreementWithConsent> agreements;
    public String city;
    public String country;
    public Domain domain;
    public String email;
    public String firstname;
    public String languageCode;
    public String lastname;
    public String pesel;
    public String phoneNumber;
    public String streetAdress;
    public String zipCode;

    public boolean getNewsletterAgreement() {
        boolean z = false;
        if (this.agreements != null) {
            if (!this.agreements.isEmpty()) {
                AgreementWithConsent agreementWithConsent = (AgreementWithConsent) Stream.of(this.agreements).filter(RegistrationData$$Lambda$0.$instance).findFirst().orElse(null);
                if (agreementWithConsent != null) {
                    z = agreementWithConsent.isConsent();
                }
                return z;
            }
        }
        return false;
    }

    static final /* synthetic */ boolean lambda$getNewsletterAgreement$8$RegistrationData(AgreementWithConsent agreementWithConsent) {
        return agreementWithConsent.getRegisterAgreement().agreementId == RegisterAgreements.TC_TRANSMISSION_2.agreementId ? true : null;
    }
}
