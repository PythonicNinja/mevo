package com.mevo.app.data.model;

import org.simpleframework.xml.Attribute;

public class CanceledBooking {
    @Attribute(name = "canceled", required = false)
    private int canceled;
    @Attribute(name = "id", required = false)
    private String id;

    public String getId() {
        if (this.id == null) {
            this.id = "";
        }
        return this.id;
    }

    public int getCanceled() {
        return this.canceled;
    }
}
