package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;

class ObjectFactory extends PrimitiveFactory {
    public ObjectFactory(Context context, Type type, Class cls) {
        super(context, type, cls);
    }

    public Instance getInstance(InputNode inputNode) throws Exception {
        inputNode = getOverride(inputNode);
        Class type = getType();
        if (inputNode != null) {
            return new ObjectInstance(this.context, inputNode);
        }
        if (Factory.isInstantiable(type) != null) {
            return this.context.getInstance(type);
        }
        throw new InstantiationException("Cannot instantiate %s for %s", type, this.type);
    }
}
