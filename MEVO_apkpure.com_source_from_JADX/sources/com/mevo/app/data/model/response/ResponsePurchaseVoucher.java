package com.mevo.app.data.model.response;

import com.mevo.app.data.model.Voucher;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class ResponsePurchaseVoucher {
    @Attribute(name = "version", required = false)
    public float version;
    @Element(name = "voucher", required = false)
    public Voucher voucher;
}
