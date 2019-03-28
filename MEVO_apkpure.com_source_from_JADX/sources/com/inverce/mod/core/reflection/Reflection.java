package com.inverce.mod.core.reflection;

import android.support.annotation.NonNull;
import com.inverce.mod.core.collections.CacheFunctionMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Reflection {
    private static CacheFunctionMap<Class<?>, Set<Class<?>>> superInterfaces = new CacheFunctionMap(Reflection$$Lambda$0.$instance);

    public static Set<Class<?>> getImplementedInterfaces(Class<?> cls) {
        return (Set) superInterfaces.get(cls);
    }

    private static Set<Class<?>> getImplementedInterfacesInternal(@NonNull Class<?> cls) {
        if (cls == Object.class) {
            return new HashSet();
        }
        Object implementedInterfacesInternal = getImplementedInterfacesInternal(cls.getSuperclass());
        cls = cls.getInterfaces();
        for (Class interfaces : cls) {
            Class[] interfaces2 = interfaces.getInterfaces();
            if (interfaces2 != null) {
                Collections.addAll(implementedInterfacesInternal, interfaces2);
            }
        }
        Collections.addAll(implementedInterfacesInternal, cls);
        return implementedInterfacesInternal;
    }
}
