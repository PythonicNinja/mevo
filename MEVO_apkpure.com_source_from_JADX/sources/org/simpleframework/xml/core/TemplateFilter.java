package org.simpleframework.xml.core;

import org.simpleframework.xml.filter.Filter;

class TemplateFilter implements Filter {
    private Context context;
    private Filter filter;

    public TemplateFilter(Context context, Filter filter) {
        this.context = context;
        this.filter = filter;
    }

    public String replace(String str) {
        Object attribute = this.context.getAttribute(str);
        if (attribute != null) {
            return attribute.toString();
        }
        return this.filter.replace(str);
    }
}
