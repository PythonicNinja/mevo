package com.google.zxing;

public final class Dimension {
    private final int height;
    private final int width;

    public Dimension(int i, int i2) {
        if (i >= 0) {
            if (i2 >= 0) {
                this.width = i;
                this.height = i2;
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof Dimension)) {
            return false;
        }
        Dimension dimension = (Dimension) obj;
        if (this.width == dimension.width && this.height == dimension.height) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (this.width * 32713) + this.height;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.width);
        stringBuilder.append("x");
        stringBuilder.append(this.height);
        return stringBuilder.toString();
    }
}
