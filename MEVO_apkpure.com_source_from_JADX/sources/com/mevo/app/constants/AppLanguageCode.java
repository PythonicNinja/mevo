package com.mevo.app.constants;

public enum AppLanguageCode {
    PL("pl"),
    EN("en"),
    DE("de"),
    RU("ru");
    
    public static final AppLanguageCode DEFAULT_LANGUAGE = null;
    private String code;

    static {
        DEFAULT_LANGUAGE = PL;
    }

    private AppLanguageCode(String str) {
        this.code = str;
    }

    public String getCode() {
        return this.code;
    }
}
