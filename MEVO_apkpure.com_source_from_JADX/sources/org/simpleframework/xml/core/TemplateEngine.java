package org.simpleframework.xml.core;

import org.simpleframework.xml.filter.Filter;

class TemplateEngine {
    private Filter filter;
    private Template name = new Template();
    private int off;
    private Template source = new Template();
    private Template text = new Template();

    public TemplateEngine(Filter filter) {
        this.filter = filter;
    }

    public String process(String str) {
        if (str.indexOf(36) < 0) {
            return str;
        }
        try {
            this.source.append(str);
            parse();
            str = this.text.toString();
            return str;
        } finally {
            clear();
        }
    }

    private void parse() {
        while (this.off < this.source.count) {
            char[] cArr = this.source.buf;
            int i = this.off;
            this.off = i + 1;
            char c = cArr[i];
            if (c == '$' && this.off < this.source.count) {
                char[] cArr2 = this.source.buf;
                int i2 = this.off;
                this.off = i2 + 1;
                if (cArr2[i2] == '{') {
                    name();
                } else {
                    this.off--;
                }
            }
            this.text.append(c);
        }
    }

    private void name() {
        while (this.off < this.source.count) {
            char[] cArr = this.source.buf;
            int i = this.off;
            this.off = i + 1;
            char c = cArr[i];
            if (c == '}') {
                replace();
                break;
            }
            this.name.append(c);
        }
        if (this.name.length() > 0) {
            this.text.append("${");
            this.text.append(this.name);
        }
    }

    private void replace() {
        if (this.name.length() > 0) {
            replace(this.name);
        }
        this.name.clear();
    }

    private void replace(Template template) {
        replace(template.toString());
    }

    private void replace(String str) {
        String replace = this.filter.replace(str);
        if (replace == null) {
            this.text.append("${");
            this.text.append(str);
            this.text.append("}");
            return;
        }
        this.text.append(replace);
    }

    public void clear() {
        this.name.clear();
        this.text.clear();
        this.source.clear();
        this.off = 0;
    }
}
