package org.simpleframework.xml.filter;

public class EnvironmentFilter implements Filter {
    private Filter filter;

    public EnvironmentFilter() {
        this(null);
    }

    public EnvironmentFilter(Filter filter) {
        this.filter = filter;
    }

    public String replace(String str) {
        String str2 = System.getenv(str);
        if (str2 != null) {
            return str2;
        }
        return this.filter != null ? this.filter.replace(str) : null;
    }
}
