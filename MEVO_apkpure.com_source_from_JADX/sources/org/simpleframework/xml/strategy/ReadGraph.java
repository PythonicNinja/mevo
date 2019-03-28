package org.simpleframework.xml.strategy;

import java.util.HashMap;
import org.simpleframework.xml.stream.Node;
import org.simpleframework.xml.stream.NodeMap;

class ReadGraph extends HashMap {
    private final String label;
    private final String length;
    private final Loader loader;
    private final String mark;
    private final String refer;

    public ReadGraph(Contract contract, Loader loader) {
        this.refer = contract.getReference();
        this.mark = contract.getIdentity();
        this.length = contract.getLength();
        this.label = contract.getLabel();
        this.loader = loader;
    }

    public Value read(Type type, NodeMap nodeMap) throws Exception {
        Node remove = nodeMap.remove(this.label);
        Class type2 = type.getType();
        if (type2.isArray()) {
            type2 = type2.getComponentType();
        }
        if (remove != null) {
            type2 = this.loader.load(remove.getValue());
        }
        return readInstance(type, type2, nodeMap);
    }

    private Value readInstance(Type type, Class cls, NodeMap nodeMap) throws Exception {
        Node remove = nodeMap.remove(this.mark);
        if (remove == null) {
            return readReference(type, cls, nodeMap);
        }
        String value = remove.getValue();
        if (!containsKey(value)) {
            return readValue(type, cls, nodeMap, value);
        }
        throw new CycleException("Element '%s' already exists", new Object[]{value});
    }

    private Value readReference(Type type, Class cls, NodeMap nodeMap) throws Exception {
        Node remove = nodeMap.remove(this.refer);
        if (remove == null) {
            return readValue(type, cls, nodeMap);
        }
        type = remove.getValue();
        nodeMap = get(type);
        if (containsKey(type)) {
            return new Reference(nodeMap, cls);
        }
        throw new CycleException("Invalid reference '%s' found", type);
    }

    private Value readValue(Type type, Class cls, NodeMap nodeMap) throws Exception {
        if (type.getType().isArray()) {
            return readArray(type, cls, nodeMap);
        }
        return new ObjectValue(cls);
    }

    private Value readValue(Type type, Class cls, NodeMap nodeMap, String str) throws Exception {
        type = readValue(type, cls, nodeMap);
        return str != null ? new Allocate(type, this, str) : type;
    }

    private Value readArray(Type type, Class cls, NodeMap nodeMap) throws Exception {
        type = nodeMap.remove(this.length);
        return new ArrayValue(cls, type != null ? Integer.parseInt(type.getValue()) : null);
    }
}
