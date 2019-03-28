package com.mevo.app.data.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseAgreementForDomain {
    @Element(name = "agreementdata")
    public AgreementsListWra agreementsWrapper;
}
