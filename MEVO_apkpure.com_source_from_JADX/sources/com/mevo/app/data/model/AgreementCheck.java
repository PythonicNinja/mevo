package com.mevo.app.data.model;

import org.simpleframework.xml.Attribute;

public class AgreementCheck {
    @Attribute(name = "status", required = false)
    private boolean status;
    @Attribute(name = "valid", required = false)
    private boolean valid;

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean z) {
        this.status = z;
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setValid(boolean z) {
        this.valid = z;
    }

    public boolean isAccepted() {
        return this.status && this.valid;
    }
}
