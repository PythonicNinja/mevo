package com.google.maps.android.clustering.algo;

import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GridBasedAlgorithm<T extends ClusterItem> implements Algorithm<T> {
    private static final int GRID_SIZE = 100;
    private final Set<T> mItems = Collections.synchronizedSet(new HashSet());

    public java.util.Set<? extends com.google.maps.android.clustering.Cluster<T>> getClusters(double r21) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 15, insn: 0x0086: INVOKE  (r6_3 com.google.maps.android.clustering.algo.StaticCluster), (r15 com.google.maps.android.clustering.ClusterItem) com.google.maps.android.clustering.algo.StaticCluster.add(com.google.maps.android.clustering.ClusterItem):boolean type: VIRTUAL, block:B:10:0x0086, method: com.google.maps.android.clustering.algo.GridBasedAlgorithm.getClusters(double):java.util.Set<? extends com.google.maps.android.clustering.Cluster<T>>
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:168)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:132)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r20 = this;
        r1 = r20;
        r2 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r4 = r21;
        r2 = java.lang.Math.pow(r2, r4);
        r4 = 4643211215818981376; // 0x4070000000000000 float:0.0 double:256.0;
        r2 = r2 * r4;
        r4 = 4636737291354636288; // 0x4059000000000000 float:0.0 double:100.0;
        r2 = r2 / r4;
        r2 = java.lang.Math.ceil(r2);
        r2 = (long) r2;
        r10 = new com.google.maps.android.projection.SphericalMercatorProjection;
        r4 = (double) r2;
        r10.<init>(r4);
        r11 = new java.util.HashSet;
        r11.<init>();
        r12 = new android.support.v4.util.LongSparseArray;
        r12.<init>();
        r13 = r1.mItems;
        monitor-enter(r13);
        r4 = r1.mItems;	 Catch:{ all -> 0x0090 }
        r14 = r4.iterator();	 Catch:{ all -> 0x0090 }
    L_0x002f:
        r4 = r14.hasNext();	 Catch:{ all -> 0x0090 }
        if (r4 == 0) goto L_0x008e;	 Catch:{ all -> 0x0090 }
    L_0x0035:
        r4 = r14.next();	 Catch:{ all -> 0x0090 }
        r15 = r4;	 Catch:{ all -> 0x0090 }
        r15 = (com.google.maps.android.clustering.ClusterItem) r15;	 Catch:{ all -> 0x0090 }
        r4 = r15.getPosition();	 Catch:{ all -> 0x0090 }
        r8 = r10.toPoint(r4);	 Catch:{ all -> 0x0090 }
        r6 = r8.f14x;	 Catch:{ all -> 0x0090 }
        r4 = r8.f15y;	 Catch:{ all -> 0x0090 }
        r16 = r4;	 Catch:{ all -> 0x0090 }
        r4 = r2;	 Catch:{ all -> 0x0090 }
        r1 = r8;	 Catch:{ all -> 0x0090 }
        r8 = r16;	 Catch:{ all -> 0x0090 }
        r4 = getCoord(r4, r6, r8);	 Catch:{ all -> 0x0090 }
        r6 = r12.get(r4);	 Catch:{ all -> 0x0090 }
        r6 = (com.google.maps.android.clustering.algo.StaticCluster) r6;	 Catch:{ all -> 0x0090 }
        if (r6 != 0) goto L_0x0084;	 Catch:{ all -> 0x0090 }
    L_0x005a:
        r6 = new com.google.maps.android.clustering.algo.StaticCluster;	 Catch:{ all -> 0x0090 }
        r7 = new com.google.maps.android.geometry.Point;	 Catch:{ all -> 0x0090 }
        r8 = r1.f14x;	 Catch:{ all -> 0x0090 }
        r8 = java.lang.Math.floor(r8);	 Catch:{ all -> 0x0090 }
        r16 = 4602678819172646912; // 0x3fe0000000000000 float:0.0 double:0.5;	 Catch:{ all -> 0x0090 }
        r8 = r8 + r16;	 Catch:{ all -> 0x0090 }
        r18 = r2;	 Catch:{ all -> 0x0090 }
        r1 = r1.f15y;	 Catch:{ all -> 0x0090 }
        r1 = java.lang.Math.floor(r1);	 Catch:{ all -> 0x0090 }
        r3 = 0;	 Catch:{ all -> 0x0090 }
        r1 = r1 + r16;	 Catch:{ all -> 0x0090 }
        r7.<init>(r8, r1);	 Catch:{ all -> 0x0090 }
        r1 = r10.toLatLng(r7);	 Catch:{ all -> 0x0090 }
        r6.<init>(r1);	 Catch:{ all -> 0x0090 }
        r12.put(r4, r6);	 Catch:{ all -> 0x0090 }
        r11.add(r6);	 Catch:{ all -> 0x0090 }
        goto L_0x0086;	 Catch:{ all -> 0x0090 }
    L_0x0084:
        r18 = r2;	 Catch:{ all -> 0x0090 }
    L_0x0086:
        r6.add(r15);	 Catch:{ all -> 0x0090 }
        r2 = r18;	 Catch:{ all -> 0x0090 }
        r1 = r20;	 Catch:{ all -> 0x0090 }
        goto L_0x002f;	 Catch:{ all -> 0x0090 }
    L_0x008e:
        monitor-exit(r13);	 Catch:{ all -> 0x0090 }
        return r11;	 Catch:{ all -> 0x0090 }
    L_0x0090:
        r0 = move-exception;	 Catch:{ all -> 0x0090 }
        r1 = r0;	 Catch:{ all -> 0x0090 }
        monitor-exit(r13);	 Catch:{ all -> 0x0090 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.clustering.algo.GridBasedAlgorithm.getClusters(double):java.util.Set<? extends com.google.maps.android.clustering.Cluster<T>>");
    }

    public void addItem(T t) {
        this.mItems.add(t);
    }

    public void addItems(Collection<T> collection) {
        this.mItems.addAll(collection);
    }

    public void clearItems() {
        this.mItems.clear();
    }

    public void removeItem(T t) {
        this.mItems.remove(t);
    }

    public Collection<T> getItems() {
        return this.mItems;
    }

    private static long getCoord(long j, double d, double d2) {
        return (long) ((((double) j) * Math.floor(d)) + Math.floor(d2));
    }
}
