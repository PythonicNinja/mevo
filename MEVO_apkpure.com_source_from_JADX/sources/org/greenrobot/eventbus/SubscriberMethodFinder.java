package org.greenrobot.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.greenrobot.eventbus.meta.SubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

class SubscriberMethodFinder {
    private static final int BRIDGE = 64;
    private static final FindState[] FIND_STATE_POOL = new FindState[4];
    private static final Map<Class<?>, List<SubscriberMethod>> METHOD_CACHE = new ConcurrentHashMap();
    private static final int MODIFIERS_IGNORE = 5192;
    private static final int POOL_SIZE = 4;
    private static final int SYNTHETIC = 4096;
    private final boolean ignoreGeneratedIndex;
    private final boolean strictMethodVerification;
    private List<SubscriberInfoIndex> subscriberInfoIndexes;

    static class FindState {
        final Map<Class, Object> anyMethodByEventType = new HashMap();
        Class<?> clazz;
        final StringBuilder methodKeyBuilder = new StringBuilder(128);
        boolean skipSuperClasses;
        Class<?> subscriberClass;
        final Map<String, Class> subscriberClassByMethodKey = new HashMap();
        SubscriberInfo subscriberInfo;
        final List<SubscriberMethod> subscriberMethods = new ArrayList();

        FindState() {
        }

        void initForSubscriber(Class<?> cls) {
            this.clazz = cls;
            this.subscriberClass = cls;
            this.skipSuperClasses = null;
            this.subscriberInfo = null;
        }

        void recycle() {
            this.subscriberMethods.clear();
            this.anyMethodByEventType.clear();
            this.subscriberClassByMethodKey.clear();
            this.methodKeyBuilder.setLength(0);
            this.subscriberClass = null;
            this.clazz = null;
            this.skipSuperClasses = false;
            this.subscriberInfo = null;
        }

        boolean checkAdd(Method method, Class<?> cls) {
            Object put = this.anyMethodByEventType.put(cls, method);
            if (put == null) {
                return true;
            }
            if (put instanceof Method) {
                if (checkAddWithMethodSignature((Method) put, cls)) {
                    this.anyMethodByEventType.put(cls, this);
                } else {
                    throw new IllegalStateException();
                }
            }
            return checkAddWithMethodSignature(method, cls);
        }

        private boolean checkAddWithMethodSignature(Method method, Class<?> cls) {
            this.methodKeyBuilder.setLength(0);
            this.methodKeyBuilder.append(method.getName());
            StringBuilder stringBuilder = this.methodKeyBuilder;
            stringBuilder.append('>');
            stringBuilder.append(cls.getName());
            cls = this.methodKeyBuilder.toString();
            method = method.getDeclaringClass();
            Class cls2 = (Class) this.subscriberClassByMethodKey.put(cls, method);
            if (cls2 != null) {
                if (cls2.isAssignableFrom(method) == null) {
                    this.subscriberClassByMethodKey.put(cls, cls2);
                    return false;
                }
            }
            return true;
        }

        void moveToSuperclass() {
            if (this.skipSuperClasses) {
                this.clazz = null;
                return;
            }
            this.clazz = this.clazz.getSuperclass();
            String name = this.clazz.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                this.clazz = null;
            }
        }
    }

    SubscriberMethodFinder(List<SubscriberInfoIndex> list, boolean z, boolean z2) {
        this.subscriberInfoIndexes = list;
        this.strictMethodVerification = z;
        this.ignoreGeneratedIndex = z2;
    }

    List<SubscriberMethod> findSubscriberMethods(Class<?> cls) {
        List<SubscriberMethod> list = (List) METHOD_CACHE.get(cls);
        if (list != null) {
            return list;
        }
        if (this.ignoreGeneratedIndex) {
            list = findUsingReflection(cls);
        } else {
            list = findUsingInfo(cls);
        }
        if (list.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Subscriber ");
            stringBuilder.append(cls);
            stringBuilder.append(" and its super classes have no public methods with the @Subscribe annotation");
            throw new EventBusException(stringBuilder.toString());
        }
        METHOD_CACHE.put(cls, list);
        return list;
    }

    private List<SubscriberMethod> findUsingInfo(Class<?> cls) {
        FindState prepareFindState = prepareFindState();
        prepareFindState.initForSubscriber(cls);
        while (prepareFindState.clazz != null) {
            prepareFindState.subscriberInfo = getSubscriberInfo(prepareFindState);
            if (prepareFindState.subscriberInfo != null) {
                for (SubscriberMethod subscriberMethod : prepareFindState.subscriberInfo.getSubscriberMethods()) {
                    if (prepareFindState.checkAdd(subscriberMethod.method, subscriberMethod.eventType)) {
                        prepareFindState.subscriberMethods.add(subscriberMethod);
                    }
                }
            } else {
                findUsingReflectionInSingleClass(prepareFindState);
            }
            prepareFindState.moveToSuperclass();
        }
        return getMethodsAndRelease(prepareFindState);
    }

    private List<SubscriberMethod> getMethodsAndRelease(FindState findState) {
        List arrayList = new ArrayList(findState.subscriberMethods);
        findState.recycle();
        synchronized (FIND_STATE_POOL) {
            for (int i = 0; i < 4; i++) {
                if (FIND_STATE_POOL[i] == null) {
                    FIND_STATE_POOL[i] = findState;
                    break;
                }
            }
        }
        return arrayList;
    }

    private FindState prepareFindState() {
        synchronized (FIND_STATE_POOL) {
            for (int i = 0; i < 4; i++) {
                FindState findState = FIND_STATE_POOL[i];
                if (findState != null) {
                    FIND_STATE_POOL[i] = null;
                    return findState;
                }
            }
            return new FindState();
        }
    }

    private SubscriberInfo getSubscriberInfo(FindState findState) {
        if (!(findState.subscriberInfo == null || findState.subscriberInfo.getSuperSubscriberInfo() == null)) {
            SubscriberInfo superSubscriberInfo = findState.subscriberInfo.getSuperSubscriberInfo();
            if (findState.clazz == superSubscriberInfo.getSubscriberClass()) {
                return superSubscriberInfo;
            }
        }
        if (this.subscriberInfoIndexes != null) {
            for (SubscriberInfoIndex subscriberInfo : this.subscriberInfoIndexes) {
                SubscriberInfo subscriberInfo2 = subscriberInfo.getSubscriberInfo(findState.clazz);
                if (subscriberInfo2 != null) {
                    return subscriberInfo2;
                }
            }
        }
        return null;
    }

    private List<SubscriberMethod> findUsingReflection(Class<?> cls) {
        FindState prepareFindState = prepareFindState();
        prepareFindState.initForSubscriber(cls);
        while (prepareFindState.clazz != null) {
            findUsingReflectionInSingleClass(prepareFindState);
            prepareFindState.moveToSuperclass();
        }
        return getMethodsAndRelease(prepareFindState);
    }

    private void findUsingReflectionInSingleClass(org.greenrobot.eventbus.SubscriberMethodFinder.FindState r15) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r14 = this;
        r0 = 1;
        r1 = r15.clazz;	 Catch:{ Throwable -> 0x0008 }
        r1 = r1.getDeclaredMethods();	 Catch:{ Throwable -> 0x0008 }
        goto L_0x0010;
    L_0x0008:
        r1 = r15.clazz;
        r1 = r1.getMethods();
        r15.skipSuperClasses = r0;
    L_0x0010:
        r2 = r1.length;
        r3 = 0;
        r4 = 0;
    L_0x0013:
        if (r4 >= r2) goto L_0x00ec;
    L_0x0015:
        r6 = r1[r4];
        r5 = r6.getModifiers();
        r7 = r5 & 1;
        if (r7 == 0) goto L_0x00a5;
    L_0x001f:
        r5 = r5 & 5192;
        if (r5 != 0) goto L_0x00a5;
    L_0x0023:
        r5 = r6.getParameterTypes();
        r7 = r5.length;
        if (r7 != r0) goto L_0x0059;
    L_0x002a:
        r7 = org.greenrobot.eventbus.Subscribe.class;
        r7 = r6.getAnnotation(r7);
        r7 = (org.greenrobot.eventbus.Subscribe) r7;
        if (r7 == 0) goto L_0x00e8;
    L_0x0034:
        r8 = r5[r3];
        r5 = r15.checkAdd(r6, r8);
        if (r5 == 0) goto L_0x00e8;
    L_0x003c:
        r9 = r7.threadMode();
        r11 = r15.subscriberMethods;
        r12 = new org.greenrobot.eventbus.SubscriberMethod;
        r10 = r7.priority();
        r13 = r7.sticky();
        r5 = r12;
        r7 = r8;
        r8 = r9;
        r9 = r10;
        r10 = r13;
        r5.<init>(r6, r7, r8, r9, r10);
        r11.add(r12);
        goto L_0x00e8;
    L_0x0059:
        r7 = r14.strictMethodVerification;
        if (r7 == 0) goto L_0x00e8;
    L_0x005d:
        r7 = org.greenrobot.eventbus.Subscribe.class;
        r7 = r6.isAnnotationPresent(r7);
        if (r7 == 0) goto L_0x00e8;
    L_0x0065:
        r15 = new java.lang.StringBuilder;
        r15.<init>();
        r0 = r6.getDeclaringClass();
        r0 = r0.getName();
        r15.append(r0);
        r0 = ".";
        r15.append(r0);
        r0 = r6.getName();
        r15.append(r0);
        r15 = r15.toString();
        r0 = new org.greenrobot.eventbus.EventBusException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "@Subscribe method ";
        r1.append(r2);
        r1.append(r15);
        r15 = "must have exactly 1 parameter but has ";
        r1.append(r15);
        r15 = r5.length;
        r1.append(r15);
        r15 = r1.toString();
        r0.<init>(r15);
        throw r0;
    L_0x00a5:
        r5 = r14.strictMethodVerification;
        if (r5 == 0) goto L_0x00e8;
    L_0x00a9:
        r5 = org.greenrobot.eventbus.Subscribe.class;
        r5 = r6.isAnnotationPresent(r5);
        if (r5 == 0) goto L_0x00e8;
    L_0x00b1:
        r15 = new java.lang.StringBuilder;
        r15.<init>();
        r0 = r6.getDeclaringClass();
        r0 = r0.getName();
        r15.append(r0);
        r0 = ".";
        r15.append(r0);
        r0 = r6.getName();
        r15.append(r0);
        r15 = r15.toString();
        r0 = new org.greenrobot.eventbus.EventBusException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r1.append(r15);
        r15 = " is a illegal @Subscribe method: must be public, non-static, and non-abstract";
        r1.append(r15);
        r15 = r1.toString();
        r0.<init>(r15);
        throw r0;
    L_0x00e8:
        r4 = r4 + 1;
        goto L_0x0013;
    L_0x00ec:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.eventbus.SubscriberMethodFinder.findUsingReflectionInSingleClass(org.greenrobot.eventbus.SubscriberMethodFinder$FindState):void");
    }

    static void clearCaches() {
        METHOD_CACHE.clear();
    }
}
