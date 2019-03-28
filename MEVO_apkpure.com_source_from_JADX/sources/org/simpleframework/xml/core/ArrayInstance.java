package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import org.simpleframework.xml.strategy.Value;

class ArrayInstance implements Instance {
    private final int length;
    private final Class type;
    private final Value value;

    public ArrayInstance(Value value) {
        this.length = value.getLength();
        this.type = value.getType();
        this.value = value;
    }

    public Object getInstance() throws Exception {
        if (this.value.isReference()) {
            return this.value.getValue();
        }
        Object newInstance = Array.newInstance(this.type, this.length);
        if (this.value != null) {
            this.value.setValue(newInstance);
        }
        return newInstance;
    }

    public Object setInstance(Object obj) {
        if (this.value != null) {
            this.value.setValue(obj);
        }
        return obj;
    }

    public Class getType() {
        return this.type;
    }

    public boolean isReference() {
        return this.value.isReference();
    }
}
