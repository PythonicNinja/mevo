package com.mevo.app.data.model;

import com.raizlabs.android.dbflow.structure.BaseModel;
import org.simpleframework.xml.Attribute;

public class User extends BaseModel {
    @Attribute(name = "credits", required = false)
    private int credits;
    @Attribute(name = "currency", required = false)
    private String currency;
    @Attribute(name = "rfid_uids", required = false)
    private String customerCardIds;
    @Attribute(name = "domain", required = false)
    private String domain;
    private int id = 1;
    @Attribute(name = "active", required = false)
    private int isActive;
    @Attribute(name = "lang", required = false)
    private String language;
    @Attribute(name = "loginkey", required = false)
    private String loginkey;
    @Attribute(name = "payment", required = false)
    private String payment;
    @Attribute(name = "mobile", required = false)
    private String phoneNumber;
    @Attribute(name = "seconds", required = false)
    private int seconds;
    @Attribute(name = "ticket_ids", required = false)
    private String ticketIds;

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getLoginkey() {
        return this.loginkey;
    }

    public void setLoginkey(String str) {
        this.loginkey = str;
    }

    public String getCustomerCardIds() {
        return this.customerCardIds;
    }

    public void setCustomerCardIds(String str) {
        this.customerCardIds = str;
    }

    public int getIsActive() {
        return this.isActive;
    }

    public void setIsActive(int i) {
        this.isActive = i;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int i) {
        this.credits = i;
    }

    public String getPayment() {
        return this.payment;
    }

    public void setPayment(String str) {
        this.payment = str;
    }

    public String getTicketIds() {
        return this.ticketIds;
    }

    public void setTicketIds(String str) {
        this.ticketIds = str;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int i) {
        this.seconds = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = str;
    }
}
