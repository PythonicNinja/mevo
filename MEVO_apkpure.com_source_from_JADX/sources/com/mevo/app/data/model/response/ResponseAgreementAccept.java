package com.mevo.app.data.model.response;

import com.mevo.app.data.model.AgreementAccept;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponseAgreementAccept {
    @Element(name = "agreementaccept")
    public AgreementAccept agreementAccept;
}
