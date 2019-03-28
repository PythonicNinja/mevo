package com.mevo.app.presentation.main.user_details;

public enum ReportUserEnum {
    EMAIL("email"),
    FORENAME("forename"),
    NAME("name"),
    ADDRESS("address"),
    ZIP("zip"),
    CITY("city"),
    COUNTRY("country"),
    MOBILE("mobile"),
    PESEL("pesel");
    
    String keyName;

    private ReportUserEnum(String str) {
        this.keyName = str;
    }
}
