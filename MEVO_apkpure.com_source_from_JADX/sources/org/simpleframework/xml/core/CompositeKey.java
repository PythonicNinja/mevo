package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;
import org.simpleframework.xml.stream.Style;

class CompositeKey implements Converter {
    private final Context context;
    private final Entry entry;
    private final Traverser root;
    private final Style style;
    private final Type type;

    public CompositeKey(Context context, Entry entry, Type type) throws Exception {
        this.root = new Traverser(context);
        this.style = context.getStyle();
        this.context = context;
        this.entry = entry;
        this.type = type;
    }

    public Object read(InputNode inputNode) throws Exception {
        Position position = inputNode.getPosition();
        Class type = this.type.getType();
        String key = this.entry.getKey();
        if (key == null) {
            key = this.context.getName(type);
        }
        if (!this.entry.isAttribute()) {
            return read(inputNode, key);
        }
        throw new AttributeException("Can not have %s as an attribute for %s at %s", type, this.entry, position);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Position position = inputNode.getPosition();
        Class type = this.type.getType();
        if (obj == null) {
            return read(inputNode);
        }
        throw new PersistenceException("Can not read key of %s for %s at %s", type, this.entry, position);
    }

    private Object read(InputNode inputNode, String str) throws Exception {
        str = this.style.getElement(str);
        Class type = this.type.getType();
        if (str != null) {
            inputNode = inputNode.getNext(str);
        }
        if (inputNode == null || inputNode.isEmpty()) {
            return null;
        }
        return this.root.read(inputNode, type);
    }

    public boolean validate(InputNode inputNode) throws Exception {
        Position position = inputNode.getPosition();
        Class type = this.type.getType();
        String key = this.entry.getKey();
        if (key == null) {
            key = this.context.getName(type);
        }
        if (!this.entry.isAttribute()) {
            return validate(inputNode, key);
        }
        throw new ElementException("Can not have %s as an attribute for %s at %s", type, this.entry, position);
    }

    private boolean validate(InputNode inputNode, String str) throws Exception {
        inputNode = inputNode.getNext(this.style.getElement(str));
        str = this.type.getType();
        if (inputNode == null || inputNode.isEmpty()) {
            return true;
        }
        return this.root.validate(inputNode, str);
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Class type = this.type.getType();
        String key = this.entry.getKey();
        if (this.entry.isAttribute()) {
            throw new ElementException("Can not have %s as an attribute for %s", type, this.entry);
        }
        if (key == null) {
            key = this.context.getName(type);
        }
        this.root.write(outputNode, obj, type, this.style.getElement(key));
    }
}
