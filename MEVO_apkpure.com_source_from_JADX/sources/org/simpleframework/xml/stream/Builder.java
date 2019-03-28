package org.simpleframework.xml.stream;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class Builder implements Style {
    private final Cache<String> attributes = new ConcurrentCache();
    private final Cache<String> elements = new ConcurrentCache();
    private final Style style;

    public Builder(Style style) {
        this.style = style;
    }

    public String getAttribute(String str) {
        String str2 = (String) this.attributes.fetch(str);
        if (str2 != null) {
            return str2;
        }
        str2 = this.style.getAttribute(str);
        if (str2 != null) {
            this.attributes.cache(str, str2);
        }
        return str2;
    }

    public String getElement(String str) {
        String str2 = (String) this.elements.fetch(str);
        if (str2 != null) {
            return str2;
        }
        str2 = this.style.getElement(str);
        if (str2 != null) {
            this.elements.cache(str, str2);
        }
        return str2;
    }

    public void setAttribute(String str, String str2) {
        this.attributes.cache(str, str2);
    }

    public void setElement(String str, String str2) {
        this.elements.cache(str, str2);
    }
}
