package org.simpleframework.xml.strategy;

import java.util.Map;
import org.simpleframework.xml.stream.NodeMap;

public class CycleStrategy implements Strategy {
    private final Contract contract;
    private final ReadState read;
    private final WriteState write;

    public CycleStrategy() {
        this(Name.MARK, Name.REFER);
    }

    public CycleStrategy(String str, String str2) {
        this(str, str2, Name.LABEL);
    }

    public CycleStrategy(String str, String str2, String str3) {
        this(str, str2, str3, Name.LENGTH);
    }

    public CycleStrategy(String str, String str2, String str3, String str4) {
        this.contract = new Contract(str, str2, str3, str4);
        this.write = new WriteState(this.contract);
        this.read = new ReadState(this.contract);
    }

    public Value read(Type type, NodeMap nodeMap, Map map) throws Exception {
        map = this.read.find(map);
        return map != null ? map.read(type, nodeMap) : null;
    }

    public boolean write(Type type, Object obj, NodeMap nodeMap, Map map) {
        map = this.write.find(map);
        return map != null ? map.write(type, obj, nodeMap) : null;
    }
}
