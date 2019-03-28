package org.simpleframework.xml.filter;

public class SystemFilter implements Filter {
    private Filter filter;

    public SystemFilter() {
        this(null);
    }

    public SystemFilter(Filter filter) {
        this.filter = filter;
    }

    public String replace(String str) {
        String property = System.getProperty(str);
        if (property != null) {
            return property;
        }
        return this.filter != null ? this.filter.replace(str) : null;
    }
}
