package org.simpleframework.xml.strategy;

import java.lang.reflect.Array;
import java.util.Map;
import org.simpleframework.xml.stream.NodeMap;

public class TreeStrategy implements Strategy {
    private final String label;
    private final String length;
    private final Loader loader;

    public TreeStrategy() {
        this(Name.LABEL, Name.LENGTH);
    }

    public TreeStrategy(String str, String str2) {
        this.loader = new Loader();
        this.length = str2;
        this.label = str;
    }

    public Value read(Type type, NodeMap nodeMap, Map map) throws Exception {
        Type readValue = readValue(type, nodeMap);
        type = type.getType();
        if (type.isArray()) {
            return readArray(readValue, nodeMap);
        }
        return type != readValue ? new ObjectValue(readValue) : null;
    }

    private Value readArray(Class cls, NodeMap nodeMap) throws Exception {
        nodeMap = nodeMap.remove(this.length);
        return new ArrayValue(cls, nodeMap != null ? Integer.parseInt(nodeMap.getValue()) : null);
    }

    private Class readValue(Type type, NodeMap nodeMap) throws Exception {
        nodeMap = nodeMap.remove(this.label);
        type = type.getType();
        if (type.isArray()) {
            type = type.getComponentType();
        }
        if (nodeMap == null) {
            return type;
        }
        return this.loader.load(nodeMap.getValue());
    }

    public boolean write(Type type, Object obj, NodeMap nodeMap, Map map) {
        map = obj.getClass();
        Map type2 = type.getType();
        obj = map.isArray() ? writeArray(type2, obj, nodeMap) : map;
        if (map != type2) {
            nodeMap.put(this.label, obj.getName());
        }
        return null;
    }

    private Class writeArray(Class cls, Object obj, NodeMap nodeMap) {
        obj = Array.getLength(obj);
        if (this.length != null) {
            nodeMap.put(this.length, String.valueOf(obj));
        }
        return cls.getComponentType();
    }
}
