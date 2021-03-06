package org.simpleframework.xml.core;

import java.util.Collection;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.Mode;
import org.simpleframework.xml.stream.OutputNode;

class PrimitiveInlineList implements Repeater {
    private final Type entry;
    private final CollectionFactory factory;
    private final String parent;
    private final Primitive root;

    public PrimitiveInlineList(Context context, Type type, Type type2, String str) {
        this.factory = new CollectionFactory(context, type);
        this.root = new Primitive(context, type2);
        this.parent = str;
        this.entry = type2;
    }

    public Object read(InputNode inputNode) throws Exception {
        Collection collection = (Collection) this.factory.getInstance();
        return collection != null ? read(inputNode, collection) : null;
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Collection collection = (Collection) obj;
        if (collection != null) {
            return read(inputNode, collection);
        }
        return read(inputNode);
    }

    private Object read(InputNode inputNode, Collection collection) throws Exception {
        InputNode parent = inputNode.getParent();
        String name = inputNode.getName();
        while (inputNode != null) {
            inputNode = this.root.read(inputNode);
            if (inputNode != null) {
                collection.add(inputNode);
            }
            inputNode = parent.getNext(name);
        }
        return collection;
    }

    public boolean validate(InputNode inputNode) throws Exception {
        InputNode parent = inputNode.getParent();
        String name = inputNode.getName();
        while (inputNode != null) {
            if (this.root.validate(inputNode) == null) {
                return null;
            }
            inputNode = parent.getNext(name);
        }
        return true;
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        OutputNode parent = outputNode.getParent();
        Mode mode = outputNode.getMode();
        if (!outputNode.isCommitted()) {
            outputNode.remove();
        }
        write(parent, obj, mode);
    }

    private void write(OutputNode outputNode, Object obj, Mode mode) throws Exception {
        for (Object next : (Collection) obj) {
            if (next != null) {
                OutputNode child = outputNode.getChild(this.parent);
                if (!isOverridden(child, next)) {
                    child.setMode(mode);
                    this.root.write(child, next);
                }
            }
        }
    }

    private boolean isOverridden(OutputNode outputNode, Object obj) throws Exception {
        return this.factory.setOverride(this.entry, obj, outputNode);
    }
}
