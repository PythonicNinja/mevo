package org.simpleframework.xml.core;

import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.LimitedCache;

class ExpressionBuilder {
    private final Cache<Expression> cache = new LimitedCache();
    private final Format format;
    private final Class type;

    public ExpressionBuilder(Detail detail, Support support) {
        this.format = support.getFormat();
        this.type = detail.getType();
    }

    public Expression build(String str) throws Exception {
        Expression expression = (Expression) this.cache.fetch(str);
        return expression == null ? create(str) : expression;
    }

    private Expression create(String str) throws Exception {
        Expression pathParser = new PathParser(str, new ClassType(this.type), this.format);
        if (this.cache != null) {
            this.cache.cache(str, pathParser);
        }
        return pathParser;
    }
}
