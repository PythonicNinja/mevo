package com.mevo.app.data.model;

public class LanguageCode {
    private String code;
    private String name;

    public LanguageCode(String str, String str2) {
        this.name = str;
        this.code = str2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }
}
