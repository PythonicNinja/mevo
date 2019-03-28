package com.inverce.mod.events;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inverce.mod.core.Log;
import com.inverce.mod.core.collections.WeakArrayList;
import com.inverce.mod.events.annotation.EventInfo;
import com.inverce.mod.events.annotation.Listener;
import com.inverce.mod.events.interfaces.EventCaller;
import com.inverce.mod.events.interfaces.MultiEvent;
import com.inverce.mod.events.interfaces.SingleEvent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Event<T extends Listener> implements SingleEvent<T>, MultiEvent<T>, EventCaller<T>, InvocationHandler {
    protected static Executor bgExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 3, TimeUnit.SECONDS, new SynchronousQueue());
    protected static Executor uiExecutor = new DefaultUiExecutor();
    @NonNull
    protected final List<T> list;
    protected boolean needCleanUp;
    @NonNull
    protected final T proxyCaller;
    protected Class<T> service;

    public static class Bus {
        static ChannelGroup channels = null;
        static Channel defaultChannel = null;
        static long hashLastRegister = 0;
        static long tsLastCount = 0;
        static long tsLastNotify = 0;
        static long tsLastRegister = 0;
        static long tsTimeNotify = 2000;

        private static void handleRegisterHint(@Nullable Object obj) {
            if (Log.isLoggable(4) && obj != null) {
                long hashCode = (long) obj.hashCode();
                if (hashLastRegister != hashCode) {
                    tsLastRegister = 0;
                    hashLastRegister = hashCode;
                    tsLastCount = 0;
                    return;
                }
                tsLastCount++;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - tsLastRegister < 15 && currentTimeMillis - tsLastNotify > tsTimeNotify && tsLastCount > 2) {
                    Log.m52i("Consider using Bus.registerAll for registering multiple event on same object.");
                    tsLastNotify = currentTimeMillis;
                }
                tsLastRegister = currentTimeMillis;
                hashLastRegister = hashCode;
            }
        }

        public static <T extends Listener> void registerAll(T t) {
            channel().registerAll(t);
        }

        public static <T extends Listener> void unregisterAll(T t) {
            channel().unregisterAll(t);
        }

        public static <T extends Listener> void register(Class<T> cls, T t) {
            handleRegisterHint(t);
            channel().event(cls).addListener((Listener) t);
        }

        public static <T extends Listener> void registerSingle(Class<T> cls, T t) {
            channel().event(cls).setListener((Listener) t);
        }

        public static <T extends Listener> void unregister(Class<T> cls, T t) {
            channel().event(cls).removeListener((Listener) t);
        }

        @NonNull
        public static <T extends Listener> T post(Class<T> cls) {
            return channel().event(cls).post();
        }

        public static synchronized <T extends Listener> Event<T> event(Class<T> cls) {
            synchronized (Bus.class) {
                cls = channel().event(cls);
            }
            return cls;
        }

        public static Channel channel(int i) {
            if (channels == null) {
                channels = new ChannelGroup(true);
            }
            return channels.on(i);
        }

        private static Channel channel() {
            if (defaultChannel == null) {
                defaultChannel = new Channel(true);
            }
            return defaultChannel;
        }
    }

    public Event(Class<T> cls) {
        this(cls, true);
    }

    public Event(Class<T> cls, boolean z) {
        this.service = cls;
        if (z) {
            this.list = new WeakArrayList();
        } else {
            this.list = new ArrayList(1);
        }
        this.proxyCaller = (Listener) Proxy.newProxyInstance(this.service.getClassLoader(), new Class[]{this.service}, this);
    }

    private void cleanUp(@Nullable T t) {
        t = t != null ? this.list.remove(t) + null : null;
        if (this.list instanceof WeakArrayList) {
            t += ((WeakArrayList) this.list).clearEmptyReferences();
        }
        if (this.needCleanUp) {
            String simpleName = this.service.getSimpleName();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cleaned up references ");
            stringBuilder.append(t);
            Log.m45e(simpleName, stringBuilder.toString());
        }
        this.needCleanUp = false;
    }

    void addListenerInternal(Object obj) {
        if (this.service.isInstance(obj)) {
            addListener((Listener) obj);
        }
    }

    void removeListenerInternal(Object obj) {
        if (this.service.isInstance(obj)) {
            removeListener((Listener) obj);
        }
    }

    public void setListener(@Nullable T t) {
        synchronized (this.list) {
            this.list.clear();
            if (t != null) {
                this.list.add(t);
            }
        }
    }

    public void addListener(@Nullable T t) {
        if (t != null) {
            synchronized (this.list) {
                if (!this.list.contains(t)) {
                    this.list.add(t);
                }
            }
        }
    }

    public void removeListener(T t) {
        synchronized (this.list) {
            cleanUp(t);
        }
    }

    public void clear() {
        synchronized (this.list) {
            this.list.clear();
        }
    }

    @NonNull
    public T post() {
        T t;
        synchronized (this.list) {
            t = this.proxyCaller;
        }
        return t;
    }

    public int getCount() {
        int size;
        synchronized (this.list) {
            size = this.list.size();
        }
        return size;
    }

    @Nullable
    public Object invoke(Object obj, @NonNull Method method, Object[] objArr) throws Throwable {
        EventInfo eventInfo = (EventInfo) method.getAnnotation(EventInfo.class);
        if (eventInfo != null) {
            switch (eventInfo.thread()) {
                case BgThread:
                    bgExecutor.execute(createInvokerRunnable(null, method, objArr));
                    return null;
                case UiThread:
                    uiExecutor.execute(createInvokerRunnable(null, method, objArr));
                    return null;
                default:
                    break;
            }
        }
        return invokeInternal(null, method, objArr);
    }

    private Runnable createInvokerRunnable(final AsyncResult<T> asyncResult, @NonNull final Method method, final Object[] objArr) {
        return new Runnable() {
            public void run() {
                try {
                    Event.this.invokeInternal(asyncResult, method, objArr);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
    }

    @Nullable
    Object invokeInternal(AsyncResult<T> asyncResult, @NonNull Method method, Object[] objArr) throws Throwable {
        synchronized (this.list) {
            Object[] objArr2 = new Object[this.list.size()];
            for (int i = 0; i < this.list.size(); i++) {
                Listener listener = (Listener) this.list.get(i);
                if (listener != null) {
                    objArr2[i] = method.invoke(listener, objArr);
                } else {
                    this.needCleanUp = true;
                }
            }
            objArr = null;
            if (this.needCleanUp != null) {
                cleanUp(null);
            }
            if (this.list.size() > null) {
                objArr = objArr2[0];
            }
        }
        return objArr;
    }
}
