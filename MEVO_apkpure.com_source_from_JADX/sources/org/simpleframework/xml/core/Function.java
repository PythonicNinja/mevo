package org.simpleframework.xml.core;

import java.lang.reflect.Method;

class Function {
    private final boolean contextual;
    private final Method method;

    public Function(Method method) {
        this(method, false);
    }

    public Function(Method method, boolean z) {
        this.contextual = z;
        this.method = method;
    }

    public Object call(Context context, Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        context = context.getSession().getMap();
        if (!this.contextual) {
            return this.method.invoke(obj, new Object[0]);
        }
        return this.method.invoke(obj, new Object[]{context});
    }
}
