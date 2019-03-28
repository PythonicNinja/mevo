package com.mevo.app.data.model;

import android.support.annotation.StringRes;

public class RegisterAgreement {
    @StringRes
    public final int agreementContentString;
    public final int agreementId;
    public final boolean required;

    public RegisterAgreement(int i, @StringRes int i2, boolean z) {
        this.agreementId = i;
        this.agreementContentString = i2;
        this.required = z;
    }
}
