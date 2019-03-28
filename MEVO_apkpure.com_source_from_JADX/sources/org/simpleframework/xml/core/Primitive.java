package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class Primitive implements Converter {
    private final Context context;
    private final String empty;
    private final Class expect;
    private final PrimitiveFactory factory;
    private final Type type;

    public Primitive(Context context, Type type) {
        this(context, type, null);
    }

    public Primitive(Context context, Type type, String str) {
        this.factory = new PrimitiveFactory(context, type);
        this.expect = type.getType();
        this.context = context;
        this.empty = str;
        this.type = type;
    }

    public Object read(InputNode inputNode) throws Exception {
        if (inputNode.isElement()) {
            return readElement(inputNode);
        }
        return read(inputNode, this.expect);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        if (obj == null) {
            return read(inputNode);
        }
        throw new PersistenceException("Can not read existing %s for %s", this.expect, this.type);
    }

    public Object read(InputNode inputNode, Class cls) throws Exception {
        inputNode = inputNode.getValue();
        if (inputNode == null) {
            return null;
        }
        if (this.empty == null || !inputNode.equals(this.empty)) {
            return readTemplate(inputNode, cls);
        }
        return this.empty;
    }

    private Object readElement(InputNode inputNode) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        if (instance.isReference()) {
            return instance.getInstance();
        }
        return readElement(inputNode, instance);
    }

    private Object readElement(InputNode inputNode, Instance instance) throws Exception {
        inputNode = read(inputNode, this.expect);
        if (instance != null) {
            instance.setInstance(inputNode);
        }
        return inputNode;
    }

    private Object readTemplate(String str, Class cls) throws Exception {
        str = this.context.getProperty(str);
        return str != null ? this.factory.getInstance(str, cls) : null;
    }

    public boolean validate(InputNode inputNode) throws Exception {
        if (inputNode.isElement()) {
            validateElement(inputNode);
        } else {
            inputNode.getValue();
        }
        return true;
    }

    private boolean validateElement(InputNode inputNode) throws Exception {
        inputNode = this.factory.getInstance(inputNode);
        if (!inputNode.isReference()) {
            inputNode.setInstance(null);
        }
        return true;
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        obj = this.factory.getText(obj);
        if (obj != null) {
            outputNode.setValue(obj);
        }
    }
}
