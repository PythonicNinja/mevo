package org.simpleframework.xml.strategy;

import java.util.Map;

class Allocate implements Value {
    private String key;
    private Map map;
    private Value value;

    public boolean isReference() {
        return false;
    }

    public Allocate(Value value, Map map, String str) {
        this.value = value;
        this.map = map;
        this.key = str;
    }

    public Object getValue() {
        return this.map.get(this.key);
    }

    public void setValue(Object obj) {
        if (this.key != null) {
            this.map.put(this.key, obj);
        }
        this.value.setValue(obj);
    }

    public Class getType() {
        return this.value.getType();
    }

    public int getLength() {
        return this.value.getLength();
    }
}
