package com.mevo.app.data.model.response;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class ErrorResponse {
    public static final String MOBILE_ERROR_CODE = "704";
    public static final String PESEL_ERROR_CODE = "26";
    @Attribute(name = "code", required = false)
    public String code;
    @Element(name = "error", required = false)
    public ErrorResponse errorResponse;
    @Attribute(name = "message", required = false)
    public String message;
    @Attribute(name = "server_time", required = false)
    public float serverTime;
    @Attribute(name = "version", required = false)
    public float version;
}
