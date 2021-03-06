package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class Traverser {
    private final Context context;
    private final Style style;

    public Traverser(Context context) {
        this.style = context.getStyle();
        this.context = context;
    }

    private Decorator getDecorator(Class cls) throws Exception {
        return this.context.getDecorator(cls);
    }

    public Object read(InputNode inputNode, Class cls) throws Exception {
        cls = getComposite(cls).read(inputNode);
        return cls != null ? read(inputNode, cls.getClass(), cls) : null;
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Class cls = obj.getClass();
        return read(inputNode, cls, getComposite(cls).read(inputNode, obj));
    }

    private Object read(InputNode inputNode, Class cls, Object obj) throws Exception {
        if (getName(cls) != null) {
            return obj;
        }
        throw new RootException("Root annotation required for %s", cls);
    }

    public boolean validate(InputNode inputNode, Class cls) throws Exception {
        Composite composite = getComposite(cls);
        if (getName(cls) != null) {
            return composite.validate(inputNode);
        }
        throw new RootException("Root annotation required for %s", cls);
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        write(outputNode, obj, obj.getClass());
    }

    public void write(OutputNode outputNode, Object obj, Class cls) throws Exception {
        String name = getName(obj.getClass());
        if (name == null) {
            throw new RootException("Root annotation required for %s", new Object[]{r0});
        } else {
            write(outputNode, obj, cls, name);
        }
    }

    public void write(OutputNode outputNode, Object obj, Class cls, String str) throws Exception {
        outputNode = outputNode.getChild(str);
        cls = getType(cls);
        if (obj != null) {
            str = obj.getClass();
            Decorator decorator = getDecorator(str);
            if (decorator != null) {
                decorator.decorate(outputNode);
            }
            if (this.context.setOverride(cls, obj, outputNode) == null) {
                getComposite(str).write(outputNode, obj);
            }
        }
        outputNode.commit();
    }

    private Composite getComposite(Class cls) throws Exception {
        Type type = getType(cls);
        if (cls != null) {
            return new Composite(this.context, type);
        }
        throw new RootException("Can not instantiate null class", new Object[0]);
    }

    private Type getType(Class cls) {
        return new ClassType(cls);
    }

    protected String getName(Class cls) throws Exception {
        return this.style.getElement(this.context.getName(cls));
    }
}
