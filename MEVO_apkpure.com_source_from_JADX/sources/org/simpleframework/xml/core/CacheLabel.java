package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.strategy.Type;

class CacheLabel implements Label {
    private final Annotation annotation;
    private final boolean attribute;
    private final boolean collection;
    private final Contact contact;
    private final boolean data;
    private final Decorator decorator;
    private final Type depend;
    private final String entry;
    private final Expression expression;
    private final boolean inline;
    private final Object key;
    private final Label label;
    private final boolean list;
    private final String name;
    private final String[] names;
    private final String override;
    private final String path;
    private final String[] paths;
    private final boolean required;
    private final boolean text;
    private final Class type;
    private final boolean union;

    public CacheLabel(Label label) throws Exception {
        this.annotation = label.getAnnotation();
        this.expression = label.getExpression();
        this.decorator = label.getDecorator();
        this.attribute = label.isAttribute();
        this.collection = label.isCollection();
        this.contact = label.getContact();
        this.depend = label.getDependent();
        this.required = label.isRequired();
        this.override = label.getOverride();
        this.list = label.isTextList();
        this.inline = label.isInline();
        this.union = label.isUnion();
        this.names = label.getNames();
        this.paths = label.getPaths();
        this.path = label.getPath();
        this.type = label.getType();
        this.name = label.getName();
        this.entry = label.getEntry();
        this.data = label.isData();
        this.text = label.isText();
        this.key = label.getKey();
        this.label = label;
    }

    public Type getType(Class cls) throws Exception {
        return this.label.getType(cls);
    }

    public Label getLabel(Class cls) throws Exception {
        return this.label.getLabel(cls);
    }

    public String[] getNames() throws Exception {
        return this.names;
    }

    public String[] getPaths() throws Exception {
        return this.paths;
    }

    public Annotation getAnnotation() {
        return this.annotation;
    }

    public Contact getContact() {
        return this.contact;
    }

    public Decorator getDecorator() throws Exception {
        return this.decorator;
    }

    public Converter getConverter(Context context) throws Exception {
        return this.label.getConverter(context);
    }

    public Object getEmpty(Context context) throws Exception {
        return this.label.getEmpty(context);
    }

    public Type getDependent() throws Exception {
        return this.depend;
    }

    public Object getKey() throws Exception {
        return this.key;
    }

    public String getEntry() throws Exception {
        return this.entry;
    }

    public String getName() throws Exception {
        return this.name;
    }

    public String getPath() throws Exception {
        return this.path;
    }

    public Expression getExpression() throws Exception {
        return this.expression;
    }

    public String getOverride() {
        return this.override;
    }

    public Class getType() {
        return this.type;
    }

    public boolean isData() {
        return this.data;
    }

    public boolean isText() {
        return this.text;
    }

    public boolean isTextList() {
        return this.list;
    }

    public boolean isInline() {
        return this.inline;
    }

    public boolean isAttribute() {
        return this.attribute;
    }

    public boolean isCollection() {
        return this.collection;
    }

    public boolean isRequired() {
        return this.required;
    }

    public boolean isUnion() {
        return this.union;
    }

    public String toString() {
        return this.label.toString();
    }
}
