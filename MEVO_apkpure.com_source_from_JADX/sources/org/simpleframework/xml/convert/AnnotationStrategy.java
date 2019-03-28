package org.simpleframework.xml.convert;

import java.util.Map;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public class AnnotationStrategy implements Strategy {
    private final ConverterScanner scanner;
    private final Strategy strategy;

    public AnnotationStrategy() {
        this(new TreeStrategy());
    }

    public AnnotationStrategy(Strategy strategy) {
        this.scanner = new ConverterScanner();
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
        Converter converter = this.scanner.getConverter(type, value);
        InputNode inputNode = (InputNode) nodeMap.getNode();
        if (converter == null) {
            return value;
        }
        nodeMap = converter.read(inputNode);
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
        type = this.scanner.getConverter(type, obj);
        OutputNode outputNode = (OutputNode) nodeMap.getNode();
        if (type == null) {
            return null;
        }
        type.write(outputNode, obj);
        return true;
    }

    private boolean isReference(Value value) {
        return (value == null || value.isReference() == null) ? null : true;
    }
}
