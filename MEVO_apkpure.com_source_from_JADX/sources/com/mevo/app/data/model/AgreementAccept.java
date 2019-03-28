package com.mevo.app.data.model;

import org.simpleframework.xml.Attribute;

public class AgreementAccept {
    @Attribute(name = "status", required = false)
    private boolean status;

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean z) {
        this.status = z;
    }
}
