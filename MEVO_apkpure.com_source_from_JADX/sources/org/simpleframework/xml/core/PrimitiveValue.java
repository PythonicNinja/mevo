package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class PrimitiveValue implements Converter {
    private final Context context;
    private final Entry entry;
    private final PrimitiveFactory factory;
    private final Primitive root;
    private final Style style;
    private final Type type;

    public PrimitiveValue(Context context, Entry entry, Type type) {
        this.factory = new PrimitiveFactory(context, type);
        this.root = new Primitive(context, type);
        this.style = context.getStyle();
        this.context = context;
        this.entry = entry;
        this.type = type;
    }

    public Object read(InputNode inputNode) throws Exception {
        Class type = this.type.getType();
        String value = this.entry.getValue();
        if (this.entry.isInline()) {
            return readAttribute(inputNode, value);
        }
        if (value == null) {
            value = this.context.getName(type);
        }
        return readElement(inputNode, value);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Class type = this.type.getType();
        if (obj == null) {
            return read(inputNode);
        }
        throw new PersistenceException("Can not read value of %s for %s", type, this.entry);
    }

    private Object readElement(InputNode inputNode, String str) throws Exception {
        inputNode = inputNode.getNext(this.style.getAttribute(str));
        if (inputNode == null) {
            return null;
        }
        return this.root.read(inputNode);
    }

    private Object readAttribute(InputNode inputNode, String str) throws Exception {
        if (str != null) {
            inputNode = inputNode.getAttribute(this.style.getAttribute(str));
        }
        if (inputNode == null) {
            return null;
        }
        return this.root.read(inputNode);
    }

    public boolean validate(InputNode inputNode) throws Exception {
        Class type = this.type.getType();
        String value = this.entry.getValue();
        if (this.entry.isInline()) {
            return validateAttribute(inputNode, value);
        }
        if (value == null) {
            value = this.context.getName(type);
        }
        return validateElement(inputNode, value);
    }

    private boolean validateElement(InputNode inputNode, String str) throws Exception {
        if (inputNode.getNext(this.style.getAttribute(str)) == null) {
            return true;
        }
        return this.root.validate(inputNode);
    }

    private boolean validateAttribute(InputNode inputNode, String str) throws Exception {
        if (str != null) {
            inputNode = inputNode.getNext(this.style.getAttribute(str));
        }
        if (inputNode == null) {
            return true;
        }
        return this.root.validate(inputNode);
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Class type = this.type.getType();
        String value = this.entry.getValue();
        if (this.entry.isInline()) {
            writeAttribute(outputNode, obj, value);
            return;
        }
        if (value == null) {
            value = this.context.getName(type);
        }
        writeElement(outputNode, obj, value);
    }

    private void writeElement(OutputNode outputNode, Object obj, String str) throws Exception {
        outputNode = outputNode.getChild(this.style.getAttribute(str));
        if (obj != null && isOverridden(outputNode, obj) == null) {
            this.root.write(outputNode, obj);
        }
    }

    private void writeAttribute(OutputNode outputNode, Object obj, String str) throws Exception {
        if (obj != null) {
            if (str != null) {
                outputNode = outputNode.setAttribute(this.style.getAttribute(str), null);
            }
            this.root.write(outputNode, obj);
        }
    }

    private boolean isOverridden(OutputNode outputNode, Object obj) throws Exception {
        return this.factory.setOverride(this.type, obj, outputNode);
    }
}
