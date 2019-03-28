package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class PrimitiveKey implements Converter {
    private final Context context;
    private final Entry entry;
    private final PrimitiveFactory factory;
    private final Primitive root;
    private final Style style;
    private final Type type;

    public PrimitiveKey(Context context, Entry entry, Type type) {
        this.factory = new PrimitiveFactory(context, type);
        this.root = new Primitive(context, type);
        this.style = context.getStyle();
        this.context = context;
        this.entry = entry;
        this.type = type;
    }

    public Object read(InputNode inputNode) throws Exception {
        Class type = this.type.getType();
        String key = this.entry.getKey();
        if (key == null) {
            key = this.context.getName(type);
        }
        if (this.entry.isAttribute()) {
            return readAttribute(inputNode, key);
        }
        return readElement(inputNode, key);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Class type = this.type.getType();
        if (obj == null) {
            return read(inputNode);
        }
        throw new PersistenceException("Can not read key of %s for %s", type, this.entry);
    }

    private Object readAttribute(InputNode inputNode, String str) throws Exception {
        inputNode = inputNode.getAttribute(this.style.getAttribute(str));
        if (inputNode == null) {
            return null;
        }
        return this.root.read(inputNode);
    }

    private Object readElement(InputNode inputNode, String str) throws Exception {
        inputNode = inputNode.getNext(this.style.getElement(str));
        if (inputNode == null) {
            return null;
        }
        return this.root.read(inputNode);
    }

    public boolean validate(InputNode inputNode) throws Exception {
        Class type = this.type.getType();
        String key = this.entry.getKey();
        if (key == null) {
            key = this.context.getName(type);
        }
        if (this.entry.isAttribute()) {
            return validateAttribute(inputNode, key);
        }
        return validateElement(inputNode, key);
    }

    private boolean validateAttribute(InputNode inputNode, String str) throws Exception {
        inputNode = inputNode.getAttribute(this.style.getElement(str));
        if (inputNode == null) {
            return true;
        }
        return this.root.validate(inputNode);
    }

    private boolean validateElement(InputNode inputNode, String str) throws Exception {
        inputNode = inputNode.getNext(this.style.getElement(str));
        if (inputNode == null) {
            return true;
        }
        return this.root.validate(inputNode);
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        if (!this.entry.isAttribute()) {
            writeElement(outputNode, obj);
        } else if (obj != null) {
            writeAttribute(outputNode, obj);
        }
    }

    private void writeElement(OutputNode outputNode, Object obj) throws Exception {
        Class type = this.type.getType();
        String key = this.entry.getKey();
        if (key == null) {
            key = this.context.getName(type);
        }
        outputNode = outputNode.getChild(this.style.getElement(key));
        if (obj != null && !isOverridden(outputNode, obj)) {
            this.root.write(outputNode, obj);
        }
    }

    private void writeAttribute(OutputNode outputNode, Object obj) throws Exception {
        Class type = this.type.getType();
        obj = this.factory.getText(obj);
        String key = this.entry.getKey();
        if (key == null) {
            key = this.context.getName(type);
        }
        String attribute = this.style.getAttribute(key);
        if (obj != null) {
            outputNode.setAttribute(attribute, obj);
        }
    }

    private boolean isOverridden(OutputNode outputNode, Object obj) throws Exception {
        return this.factory.setOverride(this.type, obj, outputNode);
    }
}
