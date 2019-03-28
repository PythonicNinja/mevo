package com.raizlabs.android.dbflow.data;

public class Blob {
    private byte[] blob;

    public Blob(byte[] bArr) {
        this.blob = bArr;
    }

    public void setBlob(byte[] bArr) {
        this.blob = bArr;
    }

    public byte[] getBlob() {
        return this.blob;
    }
}
