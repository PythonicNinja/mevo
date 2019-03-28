package org.simpleframework.xml.core;

import java.util.Collection;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class TextList implements Repeater {
    private final CollectionFactory factory;
    private final Primitive primitive;
    private final Type type = new ClassType(String.class);

    public boolean validate(InputNode inputNode) throws Exception {
        return true;
    }

    public TextList(Context context, Type type, Label label) {
        this.factory = new CollectionFactory(context, type);
        this.primitive = new Primitive(context, this.type);
    }

    public Object read(InputNode inputNode) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        Object instance2 = instance.getInstance();
        if (instance.isReference()) {
            return instance.getInstance();
        }
        return read(inputNode, instance2);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Collection collection = (Collection) obj;
        inputNode = this.primitive.read(inputNode);
        if (inputNode != null) {
            collection.add(inputNode);
        }
        return obj;
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Collection<Object> collection = (Collection) obj;
        outputNode = outputNode.getParent();
        for (Object write : collection) {
            this.primitive.write(outputNode, write);
        }
    }
}
