package org.simpleframework.xml.strategy;

import java.util.Map;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public class VisitorStrategy implements Strategy {
    private final Strategy strategy;
    private final Visitor visitor;

    public VisitorStrategy(Visitor visitor) {
        this(visitor, new TreeStrategy());
    }

    public VisitorStrategy(Visitor visitor, Strategy strategy) {
        this.strategy = strategy;
        this.visitor = visitor;
    }

    public Value read(Type type, NodeMap<InputNode> nodeMap, Map map) throws Exception {
        if (this.visitor != null) {
            this.visitor.read(type, nodeMap);
        }
        return this.strategy.read(type, nodeMap, map);
    }

    public boolean write(Type type, Object obj, NodeMap<OutputNode> nodeMap, Map map) throws Exception {
        obj = this.strategy.write(type, obj, nodeMap, map);
        if (this.visitor != null) {
            this.visitor.write(type, nodeMap);
        }
        return obj;
    }
}
