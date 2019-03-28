package org.simpleframework.xml.core;

import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.strategy.Type;

class Entry {
    private static final String DEFAULT_NAME = "entry";
    private boolean attribute;
    private Contact contact;
    private String entry;
    private String key;
    private Class keyType;
    private ElementMap label;
    private String value;
    private Class valueType;

    public Entry(Contact contact, ElementMap elementMap) {
        this.attribute = elementMap.attribute();
        this.entry = elementMap.entry();
        this.value = elementMap.value();
        this.key = elementMap.key();
        this.contact = contact;
        this.label = elementMap;
    }

    public Contact getContact() {
        return this.contact;
    }

    public boolean isAttribute() {
        return this.attribute;
    }

    public boolean isInline() throws Exception {
        return isAttribute();
    }

    public Converter getKey(Context context) throws Exception {
        Type keyType = getKeyType();
        if (context.isPrimitive(keyType)) {
            return new PrimitiveKey(context, this, keyType);
        }
        return new CompositeKey(context, this, keyType);
    }

    public Converter getValue(Context context) throws Exception {
        Type valueType = getValueType();
        if (context.isPrimitive(valueType)) {
            return new PrimitiveValue(context, this, valueType);
        }
        return new CompositeValue(context, this, valueType);
    }

    protected Type getKeyType() throws Exception {
        if (this.keyType == null) {
            this.keyType = this.label.keyType();
            if (this.keyType == Void.TYPE) {
                this.keyType = getDependent(0);
            }
        }
        return new ClassType(this.keyType);
    }

    protected Type getValueType() throws Exception {
        if (this.valueType == null) {
            this.valueType = this.label.valueType();
            if (this.valueType == Void.TYPE) {
                this.valueType = getDependent(1);
            }
        }
        return new ClassType(this.valueType);
    }

    private Class getDependent(int i) throws Exception {
        Class[] dependents = this.contact.getDependents();
        if (dependents.length < i) {
            return Object.class;
        }
        if (dependents.length == 0) {
            return Object.class;
        }
        return dependents[i];
    }

    public String getKey() throws Exception {
        if (this.key == null) {
            return this.key;
        }
        if (isEmpty(this.key)) {
            this.key = null;
        }
        return this.key;
    }

    public String getValue() throws Exception {
        if (this.value == null) {
            return this.value;
        }
        if (isEmpty(this.value)) {
            this.value = null;
        }
        return this.value;
    }

    public String getEntry() throws Exception {
        if (this.entry == null) {
            return this.entry;
        }
        if (isEmpty(this.entry)) {
            this.entry = DEFAULT_NAME;
        }
        return this.entry;
    }

    private boolean isEmpty(String str) {
        return str.length() == null ? true : null;
    }

    public String toString() {
        return String.format("%s on %s", new Object[]{this.label, this.contact});
    }
}
