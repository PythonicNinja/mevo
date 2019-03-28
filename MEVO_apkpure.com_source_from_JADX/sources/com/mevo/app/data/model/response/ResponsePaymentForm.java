package com.mevo.app.data.model.response;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponsePaymentForm {
    @Element(name = "paymentform", required = true)
    public String paymentForm;
    @Attribute(name = "version", required = false)
    public float version;
}
