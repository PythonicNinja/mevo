package com.google.android.gms.internal.measurement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzzv<MessageType extends zzzv<MessageType, BuilderType>, BuilderType> extends zzyz<MessageType, BuilderType> {
    private static Map<Object, zzzv<?, ?>> zzbuh = new ConcurrentHashMap();
    protected zzabp zzbuf = zzabp.zzvf();
    private int zzbug = -1;

    public enum zzb {
        private static final int zzbuj = 1;
        private static final int zzbuk = 2;
        public static final int zzbul = 3;
        private static final int zzbum = 4;
        private static final int zzbun = 5;
        public static final int zzbuo = 6;
        public static final int zzbup = 7;
        private static final /* synthetic */ int[] zzbuq = new int[]{1, 2, 3, 4, 5, 6, 7};
        public static final int zzbur = 1;
        private static final int zzbus = 2;
        private static final /* synthetic */ int[] zzbut = new int[]{1, 2};
        private static final int zzbuu = 1;
        private static final int zzbuv = 2;
        private static final /* synthetic */ int[] zzbuw = new int[]{1, 2};
    }

    public static abstract class zza<MessageType extends zza<MessageType, BuilderType>, BuilderType> extends zzzv<MessageType, BuilderType> implements zzaar {
        protected zzzr<Object> zzbui = zzzr.zztx();
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        Throwable e;
        try {
            return method.invoke(obj, objArr);
        } catch (Throwable e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            e2 = e3.getCause();
            if (e2 instanceof RuntimeException) {
                throw ((RuntimeException) e2);
            } else if (e2 instanceof Error) {
                throw ((Error) e2);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", e2);
            }
        }
    }

    static <T extends zzzv<?, ?>> T zzf(Class<T> cls) {
        T t = (zzzv) zzbuh.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzzv) zzbuh.get(cls);
            } catch (Throwable e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        String str = "Unable to get default instance for: ";
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public boolean equals(Object obj) {
        return this == obj ? true : !((zzzv) zza(6, null, null)).getClass().isInstance(obj) ? false : zzaay.zzus().zzt(this).equals(this, (zzzv) obj);
    }

    public int hashCode() {
        if (this.zzbta != 0) {
            return this.zzbta;
        }
        this.zzbta = zzaay.zzus().zzt(this).hashCode(this);
        return this.zzbta;
    }

    public String toString() {
        return zzaas.zza(this, super.toString());
    }

    protected abstract Object zza(int i, Object obj, Object obj2);
}
