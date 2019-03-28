package org.simpleframework.xml.core;

import java.util.Collection;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class CompositeInlineList implements Repeater {
    private final Type entry;
    private final CollectionFactory factory;
    private final String name;
    private final Traverser root;
    private final Type type;

    public CompositeInlineList(Context context, Type type, Type type2, String str) {
        this.factory = new CollectionFactory(context, type);
        this.root = new Traverser(context);
        this.entry = type2;
        this.type = type;
        this.name = str;
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
            inputNode = read(inputNode, this.entry.getType());
            if (inputNode != null) {
                collection.add(inputNode);
            }
            inputNode = parent.getNext(name);
        }
        return collection;
    }

    private Object read(InputNode inputNode, Class cls) throws Exception {
        inputNode = this.root.read(inputNode, cls);
        if (this.entry.getType().isAssignableFrom(inputNode.getClass())) {
            return inputNode;
        }
        throw new PersistenceException("Entry %s does not match %s for %s", inputNode.getClass(), this.entry, this.type);
    }

    public boolean validate(InputNode inputNode) throws Exception {
        InputNode parent = inputNode.getParent();
        Class type = this.entry.getType();
        String name = inputNode.getName();
        while (inputNode != null) {
            if (this.root.validate(inputNode, type) == null) {
                return null;
            }
            inputNode = parent.getNext(name);
        }
        return true;
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Collection collection = (Collection) obj;
        OutputNode parent = outputNode.getParent();
        if (!outputNode.isCommitted()) {
            outputNode.remove();
        }
        write(parent, collection);
    }

    public void write(OutputNode outputNode, Collection collection) throws Exception {
        for (Object next : collection) {
            if (next != null) {
                Class type = this.entry.getType();
                if (type.isAssignableFrom(next.getClass())) {
                    this.root.write(outputNode, next, type, this.name);
                } else {
                    throw new PersistenceException("Entry %s does not match %s for %s", next.getClass(), type, this.type);
                }
            }
        }
    }
}
