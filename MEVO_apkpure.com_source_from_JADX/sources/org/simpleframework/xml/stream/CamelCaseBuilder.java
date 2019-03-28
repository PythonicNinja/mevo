package org.simpleframework.xml.stream;

class CamelCaseBuilder implements Style {
    protected final boolean attribute;
    protected final boolean element;

    private class Attribute extends Splitter {
        private boolean capital;

        private Attribute(String str) {
            super(str);
        }

        protected void parse(char[] cArr, int i, int i2) {
            if (!(CamelCaseBuilder.this.attribute == 0 && this.capital == 0)) {
                cArr[i] = toUpper(cArr[i]);
            }
            this.capital = 1;
        }

        protected void commit(char[] cArr, int i, int i2) {
            this.builder.append(cArr, i, i2);
        }
    }

    private class Element extends Attribute {
        private boolean capital;

        private Element(String str) {
            super(str);
        }

        protected void parse(char[] cArr, int i, int i2) {
            if (!(CamelCaseBuilder.this.element == 0 && this.capital == 0)) {
                cArr[i] = toUpper(cArr[i]);
            }
            this.capital = 1;
        }
    }

    public CamelCaseBuilder(boolean z, boolean z2) {
        this.attribute = z2;
        this.element = z;
    }

    public String getAttribute(String str) {
        return str != null ? new Attribute(str).process() : null;
    }

    public String getElement(String str) {
        return str != null ? new Element(str).process() : null;
    }
}
