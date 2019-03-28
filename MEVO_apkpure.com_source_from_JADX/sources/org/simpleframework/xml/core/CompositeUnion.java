package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class CompositeUnion implements Converter {
    private final Context context;
    private final LabelMap elements;
    private final Group group;
    private final Expression path;
    private final Type type;

    public CompositeUnion(Context context, Group group, Expression expression, Type type) throws Exception {
        this.elements = group.getElements();
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
        Label label = this.group.getLabel(obj.getClass());
        if (label == null) {
            throw new UnionException("Value of %s not declared in %s with annotation %s", r0, this.type, this.group);
        } else {
            write(outputNode, obj, label);
        }
    }

    private void write(OutputNode outputNode, Object obj, Label label) throws Exception {
        label.getConverter(this.context).write(outputNode, obj);
    }
}
