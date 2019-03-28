package org.simpleframework.xml.stream;

class HyphenBuilder implements Style {

    private class Parser extends Splitter {
        private Parser(String str) {
            super(str);
        }

        protected void parse(char[] cArr, int i, int i2) {
            cArr[i] = toLower(cArr[i]);
        }

        protected void commit(char[] cArr, int i, int i2) {
            this.builder.append(cArr, i, i2);
            if (i + i2 < this.count) {
                this.builder.append(45);
            }
        }
    }

    HyphenBuilder() {
    }

    public String getAttribute(String str) {
        return str != null ? new Parser(str).process() : null;
    }

    public String getElement(String str) {
        return str != null ? new Parser(str).process() : null;
    }
}
