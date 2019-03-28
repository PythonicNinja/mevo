package org.simpleframework.xml.core;

import java.lang.reflect.Constructor;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class InstanceFactory {
    private final Cache<Constructor> cache = new ConcurrentCache();

    private class ClassInstance implements Instance {
        private Class type;
        private Object value;

        public boolean isReference() {
            return false;
        }

        public ClassInstance(Class cls) {
            this.type = cls;
        }

        public Object getInstance() throws Exception {
            if (this.value == null) {
                this.value = InstanceFactory.this.getObject(this.type);
            }
            return this.value;
        }

        public Object setInstance(Object obj) throws Exception {
            this.value = obj;
            return obj;
        }

        public Class getType() {
            return this.type;
        }
    }

    private class ValueInstance implements Instance {
        private final Class type;
        private final Value value;

        public ValueInstance(Value value) {
            this.type = value.getType();
            this.value = value;
        }

        public Object getInstance() throws Exception {
            if (this.value.isReference()) {
                return this.value.getValue();
            }
            Object object = InstanceFactory.this.getObject(this.type);
            if (this.value != null) {
                this.value.setValue(object);
            }
            return object;
        }

        public Object setInstance(Object obj) {
            if (this.value != null) {
                this.value.setValue(obj);
            }
            return obj;
        }

        public boolean isReference() {
            return this.value.isReference();
        }

        public Class getType() {
            return this.type;
        }
    }

    public Instance getInstance(Value value) {
        return new ValueInstance(value);
    }

    public Instance getInstance(Class cls) {
        return new ClassInstance(cls);
    }

    protected Object getObject(Class cls) throws Exception {
        Constructor constructor = (Constructor) this.cache.fetch(cls);
        if (constructor == null) {
            constructor = cls.getDeclaredConstructor(new Class[0]);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            this.cache.cache(cls, constructor);
        }
        return constructor.newInstance(new Object[0]);
    }
}
