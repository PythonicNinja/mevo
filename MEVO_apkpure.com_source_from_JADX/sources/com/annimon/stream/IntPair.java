package com.annimon.stream;

public final class IntPair<T> {
    private final int first;
    private final T second;

    public IntPair(int i, T t) {
        this.first = i;
        this.second = t;
    }

    public int getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public int hashCode() {
        return ((679 + this.first) * 97) + (this.second != null ? this.second.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IntPair intPair = (IntPair) obj;
        if (this.first != intPair.first) {
            return false;
        }
        if (this.second != intPair.second) {
            if (this.second == null || this.second.equals(intPair.second) == null) {
                z = false;
            }
        }
        return z;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IntPair[");
        stringBuilder.append(this.first);
        stringBuilder.append(", ");
        stringBuilder.append(this.second);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
