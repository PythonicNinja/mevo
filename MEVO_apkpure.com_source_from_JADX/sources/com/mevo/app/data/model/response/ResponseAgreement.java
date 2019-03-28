package com.mevo.app.data.model.response;

import com.mevo.app.data.model.AgreementCheck;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseAgreement {
    @Element(name = "agreement")
    public AgreementCheck agreementCheck;
}
