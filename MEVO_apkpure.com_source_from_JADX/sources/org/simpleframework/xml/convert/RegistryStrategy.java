package org.simpleframework.xml.convert;

import java.util.Map;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public class RegistryStrategy implements Strategy {
    private final Registry registry;
    private final Strategy strategy;

    public RegistryStrategy(Registry registry) {
        this(registry, new TreeStrategy());
    }

    public RegistryStrategy(Registry registry, Strategy strategy) {
        this.registry = registry;
        this.strategy = strategy;
    }

    public Value read(Type type, NodeMap<InputNode> nodeMap, Map map) throws Exception {
        Value read = this.strategy.read(type, nodeMap, map);
        if (isReference(read)) {
            return read;
        }
        return read(type, (NodeMap) nodeMap, read);
    }

    private Value read(Type type, NodeMap<InputNode> nodeMap, Value value) throws Exception {
        Converter lookup = lookup(type, value);
        InputNode inputNode = (InputNode) nodeMap.getNode();
        if (lookup == null) {
            return value;
        }
        nodeMap = lookup.read(inputNode);
        type = type.getType();
        if (value != null) {
            value.setValue(nodeMap);
        }
        return new Reference(value, nodeMap, type);
    }

    public boolean write(Type type, Object obj, NodeMap<OutputNode> nodeMap, Map map) throws Exception {
        map = this.strategy.write(type, obj, nodeMap, map);
        return map == null ? write(type, obj, nodeMap) : map;
    }

    private boolean write(Type type, Object obj, NodeMap<OutputNode> nodeMap) throws Exception {
        type = lookup(type, obj);
        OutputNode outputNode = (OutputNode) nodeMap.getNode();
        if (type == null) {
            return null;
        }
        type.write(outputNode, obj);
        return true;
    }

    private Converter lookup(Type type, Value value) throws Exception {
        type = type.getType();
        if (value != null) {
            type = value.getType();
        }
        return this.registry.lookup(type);
    }

    private Converter lookup(Type type, Object obj) throws Exception {
        type = type.getType();
        if (obj != null) {
            type = obj.getClass();
        }
        return this.registry.lookup(type);
    }

    private boolean isReference(Value value) {
        return (value == null || value.isReference() == null) ? null : true;
    }
}
