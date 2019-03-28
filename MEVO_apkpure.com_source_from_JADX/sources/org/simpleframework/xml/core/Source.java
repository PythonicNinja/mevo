package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;
import org.simpleframework.xml.filter.Filter;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class Source implements Context {
    private TemplateEngine engine = new TemplateEngine(this.filter);
    private Filter filter;
    private Session session;
    private Strategy strategy;
    private Support support;

    public Source(Strategy strategy, Support support, Session session) {
        this.filter = new TemplateFilter(this, support);
        this.strategy = strategy;
        this.support = support;
        this.session = session;
    }

    public boolean isStrict() {
        return this.session.isStrict();
    }

    public Session getSession() {
        return this.session;
    }

    public Support getSupport() {
        return this.support;
    }

    public Style getStyle() {
        return this.support.getStyle();
    }

    public boolean isFloat(Class cls) throws Exception {
        Support support = this.support;
        return Support.isFloat(cls);
    }

    public boolean isFloat(Type type) throws Exception {
        return isFloat(type.getType());
    }

    public boolean isPrimitive(Class cls) throws Exception {
        return this.support.isPrimitive(cls);
    }

    public boolean isPrimitive(Type type) throws Exception {
        return isPrimitive(type.getType());
    }

    public Instance getInstance(Class cls) {
        return this.support.getInstance(cls);
    }

    public Instance getInstance(Value value) {
        return this.support.getInstance(value);
    }

    public String getName(Class cls) throws Exception {
        return this.support.getName(cls);
    }

    public Version getVersion(Class cls) throws Exception {
        return getScanner(cls).getRevision();
    }

    private Scanner getScanner(Class cls) throws Exception {
        return this.support.getScanner(cls);
    }

    public Decorator getDecorator(Class cls) throws Exception {
        return getScanner(cls).getDecorator();
    }

    public Caller getCaller(Class cls) throws Exception {
        return getScanner(cls).getCaller(this);
    }

    public Schema getSchema(Class cls) throws Exception {
        Scanner scanner = getScanner(cls);
        if (scanner != null) {
            return new ClassSchema(scanner, this);
        }
        throw new PersistenceException("Invalid schema class %s", cls);
    }

    public Object getAttribute(Object obj) {
        return this.session.get(obj);
    }

    public Value getOverride(Type type, InputNode inputNode) throws Exception {
        NodeMap attributes = inputNode.getAttributes();
        if (attributes != null) {
            return this.strategy.read(type, attributes, this.session);
        }
        throw new PersistenceException("No attributes for %s", inputNode);
    }

    public boolean setOverride(Type type, Object obj, OutputNode outputNode) throws Exception {
        NodeMap attributes = outputNode.getAttributes();
        if (attributes != null) {
            return this.strategy.write(type, obj, attributes, this.session);
        }
        throw new PersistenceException("No attributes for %s", outputNode);
    }

    public Class getType(Type type, Object obj) {
        if (obj != null) {
            return obj.getClass();
        }
        return type.getType();
    }

    public String getProperty(String str) {
        return this.engine.process(str);
    }
}
