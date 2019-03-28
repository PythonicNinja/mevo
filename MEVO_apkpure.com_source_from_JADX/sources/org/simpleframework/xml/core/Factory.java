package org.simpleframework.xml.core;

import java.lang.reflect.Modifier;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

abstract class Factory {
    protected Context context;
    protected Class override;
    protected Support support;
    protected Type type;

    protected Factory(Context context, Type type) {
        this(context, type, null);
    }

    protected Factory(Context context, Type type, Class cls) {
        this.support = context.getSupport();
        this.override = cls;
        this.context = context;
        this.type = type;
    }

    public Class getType() {
        if (this.override != null) {
            return this.override;
        }
        return this.type.getType();
    }

    public Object getInstance() throws Exception {
        Class type = getType();
        if (isInstantiable(type)) {
            return type.newInstance();
        }
        throw new InstantiationException("Type %s can not be instantiated", type);
    }

    protected Value getOverride(InputNode inputNode) throws Exception {
        Value conversion = getConversion(inputNode);
        if (conversion != null) {
            inputNode = inputNode.getPosition();
            if (!isCompatible(getType(), conversion.getType())) {
                throw new InstantiationException("Incompatible %s for %s at %s", conversion.getType(), this.type, inputNode);
            }
        }
        return conversion;
    }

    public boolean setOverride(Type type, Object obj, OutputNode outputNode) throws Exception {
        Class type2 = type.getType();
        if (type2.isPrimitive()) {
            type = getPrimitive(type, type2);
        }
        return this.context.setOverride(type, obj, outputNode);
    }

    private Type getPrimitive(Type type, Class cls) throws Exception {
        Support support = this.support;
        Class primitive = Support.getPrimitive(cls);
        return primitive != cls ? new OverrideType(type, primitive) : type;
    }

    public Value getConversion(InputNode inputNode) throws Exception {
        inputNode = this.context.getOverride(this.type, inputNode);
        if (!(inputNode == null || this.override == null)) {
            if (!isCompatible(this.override, inputNode.getType())) {
                return new OverrideValue(inputNode, this.override);
            }
        }
        return inputNode;
    }

    public static boolean isCompatible(Class cls, Class cls2) {
        if (cls.isArray()) {
            cls = cls.getComponentType();
        }
        return cls.isAssignableFrom(cls2);
    }

    public static boolean isInstantiable(Class cls) {
        cls = cls.getModifiers();
        if (Modifier.isAbstract(cls)) {
            return null;
        }
        return Modifier.isInterface(cls) ^ 1;
    }
}
