package org.simpleframework.xml.stream;

abstract class Splitter {
    protected StringBuilder builder = new StringBuilder();
    protected int count;
    protected int off;
    protected char[] text;

    protected abstract void commit(char[] cArr, int i, int i2);

    protected abstract void parse(char[] cArr, int i, int i2);

    public Splitter(String str) {
        this.text = str.toCharArray();
        this.count = this.text.length;
    }

    public String process() {
        while (this.off < this.count) {
            while (this.off < this.count) {
                if (!isSpecial(this.text[this.off])) {
                    break;
                }
                this.off++;
            }
            if (!acronym()) {
                token();
                number();
            }
        }
        return this.builder.toString();
    }

    private void token() {
        int i = this.off;
        while (i < this.count) {
            char c = this.text[i];
            if (isLetter(c)) {
                if (i > this.off && isUpper(c)) {
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        if (i > this.off) {
            parse(this.text, this.off, i - this.off);
            commit(this.text, this.off, i - this.off);
        }
        this.off = i;
    }

    private boolean acronym() {
        int i = this.off;
        int i2 = 0;
        while (i < this.count && isUpper(this.text[i])) {
            i2++;
            i++;
        }
        if (i2 > 1) {
            if (i < this.count && isUpper(this.text[i - 1])) {
                i--;
            }
            commit(this.text, this.off, i - this.off);
            this.off = i;
        }
        if (i2 > 1) {
            return true;
        }
        return false;
    }

    private boolean number() {
        int i = this.off;
        int i2 = 0;
        while (i < this.count && isDigit(this.text[i])) {
            i2++;
            i++;
        }
        if (i2 > 0) {
            commit(this.text, this.off, i - this.off);
        }
        this.off = i;
        if (i2 > 0) {
            return true;
        }
        return false;
    }

    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }

    private boolean isSpecial(char c) {
        return Character.isLetterOrDigit(c) ^ 1;
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private boolean isUpper(char c) {
        return Character.isUpperCase(c);
    }

    protected char toUpper(char c) {
        return Character.toUpperCase(c);
    }

    protected char toLower(char c) {
        return Character.toLowerCase(c);
    }
}
