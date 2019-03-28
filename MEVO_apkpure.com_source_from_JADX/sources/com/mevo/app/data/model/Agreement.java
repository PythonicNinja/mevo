package com.mevo.app.data.model;

import org.simpleframework.xml.Attribute;

public class Agreement {
    @Attribute(name = "agreement_type", required = false)
    private String agreementType;
    @Attribute(name = "country_id", required = false)
    private int countryId;
    @Attribute(name = "crdate", required = false)
    private long crdate;
    @Attribute(name = "tstamp", required = false)
    private long timestamp;
    @Attribute(name = "uid")
    private int uid;
    @Attribute(name = "agreement_url", required = false)
    private String url;
    @Attribute(name = "valid_from", required = false)
    private long validFrom;

    public int getUid() {
        return this.uid;
    }

    public void setUid(int i) {
        this.uid = i;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public long getCrdate() {
        return this.crdate;
    }

    public void setCrdate(long j) {
        this.crdate = j;
    }

    public int getCountryId() {
        return this.countryId;
    }

    public void setCountryId(int i) {
        this.countryId = i;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public long getValidFrom() {
        return this.validFrom;
    }

    public void setValidFrom(long j) {
        this.validFrom = j;
    }

    public String getAgreementType() {
        return this.agreementType;
    }

    public void setAgreementType(String str) {
        this.agreementType = str;
    }
}
