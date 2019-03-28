package com.mevo.app.data.model;

import com.google.android.gms.maps.model.LatLng;

public class Domain {
    private String cityName;
    private String domainCode;
    private String domainName;
    private LatLng northEast;
    private LatLng southWest;

    public Domain(String str, String str2, String str3, LatLng latLng, LatLng latLng2) {
        this.cityName = str;
        this.domainName = str2;
        this.domainCode = str3;
        this.northEast = latLng;
        this.southWest = latLng2;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public void setDomainName(String str) {
        this.domainName = str;
    }

    public String getDomainCode() {
        return this.domainCode;
    }

    public void setDomainCode(String str) {
        this.domainCode = str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        if (r4 != r5) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = 0;
        if (r5 == 0) goto L_0x0050;
    L_0x0007:
        r2 = r4.getClass();
        r3 = r5.getClass();
        if (r2 == r3) goto L_0x0012;
    L_0x0011:
        goto L_0x0050;
    L_0x0012:
        r5 = (com.mevo.app.data.model.Domain) r5;
        r2 = r4.cityName;
        if (r2 == 0) goto L_0x0023;
    L_0x0018:
        r2 = r4.cityName;
        r3 = r5.cityName;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0028;
    L_0x0022:
        goto L_0x0027;
    L_0x0023:
        r2 = r5.cityName;
        if (r2 == 0) goto L_0x0028;
    L_0x0027:
        return r1;
    L_0x0028:
        r2 = r4.domainName;
        if (r2 == 0) goto L_0x0037;
    L_0x002c:
        r2 = r4.domainName;
        r3 = r5.domainName;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x003c;
    L_0x0036:
        goto L_0x003b;
    L_0x0037:
        r2 = r5.domainName;
        if (r2 == 0) goto L_0x003c;
    L_0x003b:
        return r1;
    L_0x003c:
        r2 = r4.domainCode;
        if (r2 == 0) goto L_0x0049;
    L_0x0040:
        r0 = r4.domainCode;
        r5 = r5.domainCode;
        r0 = r0.equals(r5);
        goto L_0x004f;
    L_0x0049:
        r5 = r5.domainCode;
        if (r5 != 0) goto L_0x004e;
    L_0x004d:
        goto L_0x004f;
    L_0x004e:
        r0 = 0;
    L_0x004f:
        return r0;
    L_0x0050:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.model.Domain.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((this.cityName != null ? this.cityName.hashCode() : 0) * 31) + (this.domainName != null ? this.domainName.hashCode() : 0)) * 31;
        if (this.domainCode != null) {
            i = this.domainCode.hashCode();
        }
        return hashCode + i;
    }

    public LatLng getNorthEast() {
        return this.northEast;
    }

    public void setNorthEast(LatLng latLng) {
        this.northEast = latLng;
    }

    public LatLng getSouthWest() {
        return this.southWest;
    }

    public void setSouthWest(LatLng latLng) {
        this.southWest = latLng;
    }
}
