package com.mevo.app.data.model.api_extended.response;

import com.google.gson.annotations.SerializedName;

public class ResponseCitibikeConsents {
    @SerializedName("consent_1")
    private boolean consent1;
    @SerializedName("consent_2")
    private boolean consent2;
    @SerializedName("consent_3")
    private boolean consent3;
    @SerializedName("consent_4")
    private boolean consent4;
    @SerializedName("consent_5")
    private boolean consent5;
    @SerializedName("newsletter")
    private boolean newsletter;

    public boolean getConsent1() {
        return this.consent1;
    }

    public void setConsent1(boolean z) {
        this.consent1 = z;
    }

    public boolean getConsent2() {
        return this.consent2;
    }

    public void setConsent2(boolean z) {
        this.consent2 = z;
    }

    public boolean getConsent3() {
        return this.consent3;
    }

    public void setConsent3(boolean z) {
        this.consent3 = z;
    }

    public boolean getConsent4() {
        return this.consent4;
    }

    public void setConsent4(boolean z) {
        this.consent4 = z;
    }

    public boolean getConsent5() {
        return this.consent5;
    }

    public void setConsent5(boolean z) {
        this.consent5 = z;
    }

    public boolean isNewsletter() {
        return this.newsletter;
    }

    public void setNewsletter(boolean z) {
        this.newsletter = z;
    }
}
