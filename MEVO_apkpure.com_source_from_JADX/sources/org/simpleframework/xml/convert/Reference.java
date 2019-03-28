package org.simpleframework.xml.convert;

import org.simpleframework.xml.strategy.Value;

class Reference implements Value {
    private Class actual;
    private Object data;
    private Value value;

    public int getLength() {
        return 0;
    }

    public boolean isReference() {
        return true;
    }

    public Reference(Value value, Object obj, Class cls) {
        this.actual = cls;
        this.value = value;
        this.data = obj;
    }

    public Class getType() {
        if (this.data != null) {
            return this.data.getClass();
        }
        return this.actual;
    }

    public Object getValue() {
        return this.data;
    }

    public void setValue(Object obj) {
        if (this.value != null) {
            this.value.setValue(obj);
        }
        this.data = obj;
    }
}
