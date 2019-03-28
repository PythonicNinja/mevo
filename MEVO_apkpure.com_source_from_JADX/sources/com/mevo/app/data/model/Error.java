package com.mevo.app.data.model;

import org.simpleframework.xml.Attribute;

public class Error {
    @Attribute(name = "code", required = false)
    private String code;
    @Attribute(name = "message", required = false)
    private String message;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
