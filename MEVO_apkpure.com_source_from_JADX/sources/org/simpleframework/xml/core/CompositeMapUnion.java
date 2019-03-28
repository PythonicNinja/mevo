package org.simpleframework.xml.core;

import java.util.Collections;
import java.util.Map;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class CompositeMapUnion implements Repeater {
    private final Context context;
    private final LabelMap elements;
    private final Group group;
    private final Expression path;
    private final Style style;
    private final Type type;

    public CompositeMapUnion(Context context, Group group, Expression expression, Type type) throws Exception {
        this.elements = group.getElements();
        this.style = context.getStyle();
        this.context = context;
        this.group = group;
        this.type = type;
        this.path = expression;
    }

    public Object read(InputNode inputNode) throws Exception {
        return ((Label) this.elements.get(this.path.getElement(inputNode.getName()))).getConverter(this.context).read(inputNode);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        return ((Label) this.elements.get(this.path.getElement(inputNode.getName()))).getConverter(this.context).read(inputNode, obj);
    }

    public boolean validate(InputNode inputNode) throws Exception {
        return ((Label) this.elements.get(this.path.getElement(inputNode.getName()))).getConverter(this.context).validate(inputNode);
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Map map = (Map) obj;
        if (!this.group.isInline()) {
            write(outputNode, map);
        } else if (!map.isEmpty()) {
            write(outputNode, map);
        } else if (outputNode.isCommitted() == null) {
            outputNode.remove();
        }
    }

    private void write(OutputNode outputNode, Map map) throws Exception {
        for (Object next : map.keySet()) {
            Object obj = map.get(next);
            if (obj != null) {
                Label label = this.group.getLabel(obj.getClass());
                if (label == null) {
                    throw new UnionException("Value of %s not declared in %s with annotation %s", r3, this.type, this.group);
                }
                write(outputNode, next, obj, label);
            }
        }
    }

    private void write(OutputNode outputNode, Object obj, Object obj2, Label label) throws Exception {
        Converter converter = label.getConverter(this.context);
        obj = Collections.singletonMap(obj, obj2);
        if (label.isInline() == null) {
            obj2 = this.style.getElement(label.getName());
            if (outputNode.isCommitted() == null) {
                outputNode.setName(obj2);
            }
        }
        converter.write(outputNode, obj);
    }
}
