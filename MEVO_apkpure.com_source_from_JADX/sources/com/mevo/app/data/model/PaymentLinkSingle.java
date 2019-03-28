package com.mevo.app.data.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "nextbike")
public class PaymentLinkSingle {
    public static final String PAYMENT_CREDIT_CARD = "tpay_card_register";
    @Attribute(name = "amount", required = false)
    public int amount;
    @Attribute(name = "is_current_payment", required = false)
    public int isCurrentPayment;
    @Attribute(name = "link_url", required = false)
    public String linkUrl;
    @Attribute(name = "oneclick", required = false)
    public int oneclick;
    @Attribute(name = "option", required = false)
    public String option;
    @Attribute(name = "payment", required = false)
    public String payment;
    @Attribute(name = "title", required = false)
    public String title;

    public String getOption() {
        return this.option;
    }

    public String getPayment() {
        return this.payment;
    }

    public String getTitle() {
        return this.title;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public int getIsCurrentPayment() {
        return this.isCurrentPayment;
    }

    public int getOneclick() {
        return this.oneclick;
    }

    public int getAmount() {
        return this.amount;
    }
}
