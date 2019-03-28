package com.mevo.app.data.model.response;

import com.mevo.app.data.model.PaymentLinkSingle;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponsePaymentLink {
    @Element(name = "paymentlinks")
    public PaymentLinks paymentLinks;

    public static class PaymentLinks {
        @ElementList(entry = "paymentlink", inline = true)
        public List<PaymentLinkSingle> paymentLinkSingles;
    }
}
