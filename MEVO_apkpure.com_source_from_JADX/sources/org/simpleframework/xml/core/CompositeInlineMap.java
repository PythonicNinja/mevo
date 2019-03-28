package org.simpleframework.xml.core;

import java.util.Map;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.Mode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class CompositeInlineMap implements Repeater {
    private final Entry entry;
    private final MapFactory factory;
    private final Converter key;
    private final Style style;
    private final Converter value;

    public CompositeInlineMap(Context context, Entry entry, Type type) throws Exception {
        this.factory = new MapFactory(context, type);
        this.value = entry.getValue(context);
        this.key = entry.getKey(context);
        this.style = context.getStyle();
        this.entry = entry;
    }

    public Object read(InputNode inputNode) throws Exception {
        Map map = (Map) this.factory.getInstance();
        return map != null ? read(inputNode, map) : null;
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Map map = (Map) obj;
        if (map != null) {
            return read(inputNode, map);
        }
        return read(inputNode);
    }

    private Object read(InputNode inputNode, Map map) throws Exception {
        InputNode parent = inputNode.getParent();
        String name = inputNode.getName();
        while (inputNode != null) {
            Object read = this.key.read(inputNode);
            inputNode = this.value.read(inputNode);
            if (map != null) {
                map.put(read, inputNode);
            }
            inputNode = parent.getNext(name);
        }
        return map;
    }

    public boolean validate(InputNode inputNode) throws Exception {
        InputNode parent = inputNode.getParent();
        String name = inputNode.getName();
        while (inputNode != null) {
            if (!this.key.validate(inputNode) || this.value.validate(inputNode) == null) {
                return false;
            }
            inputNode = parent.getNext(name);
        }
        return true;
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        OutputNode parent = outputNode.getParent();
        Mode mode = outputNode.getMode();
        Map map = (Map) obj;
        if (!outputNode.isCommitted()) {
            outputNode.remove();
        }
        write(parent, map, mode);
    }

    private void write(OutputNode outputNode, Map map, Mode mode) throws Exception {
        String element = this.style.getElement(this.entry.getEntry());
        for (Object next : map.keySet()) {
            OutputNode child = outputNode.getChild(element);
            Object obj = map.get(next);
            child.setMode(mode);
            this.key.write(child, next);
            this.value.write(child, obj);
        }
    }
}
