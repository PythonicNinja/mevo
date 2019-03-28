package com.inverce.mod.events;

import android.support.annotation.NonNull;
import com.inverce.mod.core.collections.CacheFunctionMap;
import com.inverce.mod.core.reflection.Reflection;
import com.inverce.mod.events.annotation.Listener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Channel extends HashMap<Class<? extends Listener>, Event<? extends Listener>> {
    protected static CacheFunctionMap<Class<?>, Set<Class<?>>> listenersInClass = new CacheFunctionMap(Channel$$Lambda$0.$instance);
    private final boolean useWeekEvents;

    @NonNull
    public static Set<Class<?>> getListenersInClassImpl(Class<?> cls) {
        cls = Reflection.getImplementedInterfaces(cls);
        Set<Class<?>> hashSet = new HashSet();
        cls = cls.iterator();
        while (cls.hasNext()) {
            Class cls2 = (Class) cls.next();
            if (Listener.class.isAssignableFrom(cls2) && cls2 != Listener.class) {
                hashSet.add(cls2);
            }
        }
        return hashSet;
    }

    public Channel() {
        this(true);
    }

    public Channel(boolean z) {
        this.useWeekEvents = z;
    }

    public <T extends Listener> void registerAll(@NonNull T t) {
        for (Class eventInternal : (Set) listenersInClass.get(t.getClass())) {
            eventInternal(eventInternal).addListenerInternal(t);
        }
    }

    public <T extends Listener> void unregisterAll(@NonNull T t) {
        for (Class eventInternal : (Set) listenersInClass.get(t.getClass())) {
            eventInternal(eventInternal).removeListenerInternal(t);
        }
    }

    public <T extends Listener> void register(Class<T> cls, T t) {
        event(cls).addListener((Listener) t);
    }

    public <T extends Listener> void registerSingle(Class<T> cls, T t) {
        event(cls).setListener((Listener) t);
    }

    public <T extends Listener> void unregister(Class<T> cls, T t) {
        event(cls).removeListener((Listener) t);
    }

    @NonNull
    public <T extends Listener> T post(Class<T> cls) {
        return event(cls).post();
    }

    @NonNull
    public <T extends Listener> Event<T> event(Class<T> cls) {
        Event<T> event = (Event) super.get(cls);
        if (event != null) {
            return event;
        }
        event = new Event(cls, this.useWeekEvents);
        put(cls, event);
        return event;
    }

    @NonNull
    private Event<?> eventInternal(Class<?> cls) {
        Event<?> event = new Event(cls, this.useWeekEvents);
        put(cls, event);
        return event;
    }
}
