package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfInt;
import java.util.NoSuchElementException;

public class IntCodePoints extends OfInt {
    private final CharSequence charSequence;
    private int current = null;
    private final boolean isString;
    private int length = -1;

    public IntCodePoints(CharSequence charSequence) {
        this.charSequence = charSequence;
        this.isString = charSequence instanceof String;
    }

    public boolean hasNext() {
        return this.current < ensureLength();
    }

    public int nextInt() {
        int ensureLength = ensureLength();
        if (this.current >= ensureLength) {
            throw new NoSuchElementException();
        }
        CharSequence charSequence = this.charSequence;
        int i = this.current;
        this.current = i + 1;
        char charAt = charSequence.charAt(i);
        if (Character.isHighSurrogate(charAt) && this.current < ensureLength) {
            char charAt2 = this.charSequence.charAt(this.current);
            if (Character.isLowSurrogate(charAt2)) {
                this.current++;
                return Character.toCodePoint(charAt, charAt2);
            }
        }
        return charAt;
    }

    private int ensureLength() {
        if (!this.isString) {
            return this.charSequence.length();
        }
        if (this.length == -1) {
            this.length = this.charSequence.length();
        }
        return this.length;
    }
}
