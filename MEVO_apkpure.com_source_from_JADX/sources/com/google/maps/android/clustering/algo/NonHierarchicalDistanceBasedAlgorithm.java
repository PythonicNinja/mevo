package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
import com.google.maps.android.quadtree.PointQuadTree.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class NonHierarchicalDistanceBasedAlgorithm<T extends ClusterItem> implements Algorithm<T> {
    public static final int MAX_DISTANCE_AT_ZOOM = 100;
    private static final SphericalMercatorProjection PROJECTION = new SphericalMercatorProjection(1.0d);
    private final Collection<QuadItem<T>> mItems = new ArrayList();
    private final PointQuadTree<QuadItem<T>> mQuadTree = new PointQuadTree(0.0d, 1.0d, 0.0d, 1.0d);

    private static class QuadItem<T extends ClusterItem> implements Item, Cluster<T> {
        private final T mClusterItem;
        private final Point mPoint;
        private final LatLng mPosition;
        private Set<T> singletonSet;

        public int getSize() {
            return 1;
        }

        private QuadItem(T t) {
            this.mClusterItem = t;
            this.mPosition = t.getPosition();
            this.mPoint = NonHierarchicalDistanceBasedAlgorithm.PROJECTION.toPoint(this.mPosition);
            this.singletonSet = Collections.singleton(this.mClusterItem);
        }

        public Point getPoint() {
            return this.mPoint;
        }

        public LatLng getPosition() {
            return this.mPosition;
        }

        public Set<T> getItems() {
            return this.singletonSet;
        }

        public int hashCode() {
            return this.mClusterItem.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof QuadItem) {
                return ((QuadItem) obj).mClusterItem.equals(this.mClusterItem);
            }
            return null;
        }
    }

    public void addItem(T t) {
        Item quadItem = new QuadItem(t);
        synchronized (this.mQuadTree) {
            this.mItems.add(quadItem);
            this.mQuadTree.add(quadItem);
        }
    }

    public void addItems(Collection<T> collection) {
        for (T addItem : collection) {
            addItem(addItem);
        }
    }

    public void clearItems() {
        synchronized (this.mQuadTree) {
            this.mItems.clear();
            this.mQuadTree.clear();
        }
    }

    public void removeItem(T t) {
        Item quadItem = new QuadItem(t);
        synchronized (this.mQuadTree) {
            this.mItems.remove(quadItem);
            this.mQuadTree.remove(quadItem);
        }
    }

    public java.util.Set<? extends com.google.maps.android.clustering.Cluster<T>> getClusters(double r22) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r21 = this;
        r1 = r21;
        r2 = r22;
        r2 = (int) r2;
        r2 = (double) r2;
        r4 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r2 = java.lang.Math.pow(r4, r2);
        r4 = 4636737291354636288; // 0x4059000000000000 float:0.0 double:100.0;
        r4 = r4 / r2;
        r2 = 4643211215818981376; // 0x4070000000000000 float:0.0 double:256.0;
        r4 = r4 / r2;
        r2 = new java.util.HashSet;
        r2.<init>();
        r3 = new java.util.HashSet;
        r3.<init>();
        r6 = new java.util.HashMap;
        r6.<init>();
        r7 = new java.util.HashMap;
        r7.<init>();
        r8 = r1.mQuadTree;
        monitor-enter(r8);
        r9 = r1.mItems;	 Catch:{ all -> 0x00d8 }
        r9 = r9.iterator();	 Catch:{ all -> 0x00d8 }
    L_0x002f:
        r10 = r9.hasNext();	 Catch:{ all -> 0x00d8 }
        if (r10 == 0) goto L_0x00d6;	 Catch:{ all -> 0x00d8 }
    L_0x0035:
        r10 = r9.next();	 Catch:{ all -> 0x00d8 }
        r10 = (com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm.QuadItem) r10;	 Catch:{ all -> 0x00d8 }
        r11 = r2.contains(r10);	 Catch:{ all -> 0x00d8 }
        if (r11 == 0) goto L_0x0042;	 Catch:{ all -> 0x00d8 }
    L_0x0041:
        goto L_0x002f;	 Catch:{ all -> 0x00d8 }
    L_0x0042:
        r11 = r10.getPoint();	 Catch:{ all -> 0x00d8 }
        r11 = r1.createBoundsFromSpan(r11, r4);	 Catch:{ all -> 0x00d8 }
        r12 = r1.mQuadTree;	 Catch:{ all -> 0x00d8 }
        r11 = r12.search(r11);	 Catch:{ all -> 0x00d8 }
        r12 = r11.size();	 Catch:{ all -> 0x00d8 }
        r13 = 1;	 Catch:{ all -> 0x00d8 }
        if (r12 != r13) goto L_0x0067;	 Catch:{ all -> 0x00d8 }
    L_0x0057:
        r3.add(r10);	 Catch:{ all -> 0x00d8 }
        r2.add(r10);	 Catch:{ all -> 0x00d8 }
        r11 = 0;	 Catch:{ all -> 0x00d8 }
        r11 = java.lang.Double.valueOf(r11);	 Catch:{ all -> 0x00d8 }
        r6.put(r10, r11);	 Catch:{ all -> 0x00d8 }
        goto L_0x002f;	 Catch:{ all -> 0x00d8 }
    L_0x0067:
        r12 = new com.google.maps.android.clustering.algo.StaticCluster;	 Catch:{ all -> 0x00d8 }
        r13 = r10.mClusterItem;	 Catch:{ all -> 0x00d8 }
        r13 = r13.getPosition();	 Catch:{ all -> 0x00d8 }
        r12.<init>(r13);	 Catch:{ all -> 0x00d8 }
        r3.add(r12);	 Catch:{ all -> 0x00d8 }
        r13 = r11.iterator();	 Catch:{ all -> 0x00d8 }
    L_0x007b:
        r14 = r13.hasNext();	 Catch:{ all -> 0x00d8 }
        if (r14 == 0) goto L_0x00cb;	 Catch:{ all -> 0x00d8 }
    L_0x0081:
        r14 = r13.next();	 Catch:{ all -> 0x00d8 }
        r14 = (com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm.QuadItem) r14;	 Catch:{ all -> 0x00d8 }
        r15 = r6.get(r14);	 Catch:{ all -> 0x00d8 }
        r15 = (java.lang.Double) r15;	 Catch:{ all -> 0x00d8 }
        r16 = r4;	 Catch:{ all -> 0x00d8 }
        r4 = r14.getPoint();	 Catch:{ all -> 0x00d8 }
        r5 = r10.getPoint();	 Catch:{ all -> 0x00d8 }
        r4 = r1.distanceSquared(r4, r5);	 Catch:{ all -> 0x00d8 }
        if (r15 == 0) goto L_0x00b5;	 Catch:{ all -> 0x00d8 }
    L_0x009d:
        r18 = r15.doubleValue();	 Catch:{ all -> 0x00d8 }
        r15 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1));	 Catch:{ all -> 0x00d8 }
        if (r15 >= 0) goto L_0x00a8;	 Catch:{ all -> 0x00d8 }
    L_0x00a5:
        r4 = r16;	 Catch:{ all -> 0x00d8 }
        goto L_0x007b;	 Catch:{ all -> 0x00d8 }
    L_0x00a8:
        r15 = r7.get(r14);	 Catch:{ all -> 0x00d8 }
        r15 = (com.google.maps.android.clustering.algo.StaticCluster) r15;	 Catch:{ all -> 0x00d8 }
        r1 = r14.mClusterItem;	 Catch:{ all -> 0x00d8 }
        r15.remove(r1);	 Catch:{ all -> 0x00d8 }
    L_0x00b5:
        r1 = java.lang.Double.valueOf(r4);	 Catch:{ all -> 0x00d8 }
        r6.put(r14, r1);	 Catch:{ all -> 0x00d8 }
        r1 = r14.mClusterItem;	 Catch:{ all -> 0x00d8 }
        r12.add(r1);	 Catch:{ all -> 0x00d8 }
        r7.put(r14, r12);	 Catch:{ all -> 0x00d8 }
        r4 = r16;	 Catch:{ all -> 0x00d8 }
        r1 = r21;	 Catch:{ all -> 0x00d8 }
        goto L_0x007b;	 Catch:{ all -> 0x00d8 }
    L_0x00cb:
        r16 = r4;	 Catch:{ all -> 0x00d8 }
        r2.addAll(r11);	 Catch:{ all -> 0x00d8 }
        r4 = r16;	 Catch:{ all -> 0x00d8 }
        r1 = r21;	 Catch:{ all -> 0x00d8 }
        goto L_0x002f;	 Catch:{ all -> 0x00d8 }
    L_0x00d6:
        monitor-exit(r8);	 Catch:{ all -> 0x00d8 }
        return r3;	 Catch:{ all -> 0x00d8 }
    L_0x00d8:
        r0 = move-exception;	 Catch:{ all -> 0x00d8 }
        r1 = r0;	 Catch:{ all -> 0x00d8 }
        monitor-exit(r8);	 Catch:{ all -> 0x00d8 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm.getClusters(double):java.util.Set<? extends com.google.maps.android.clustering.Cluster<T>>");
    }

    public Collection<T> getItems() {
        Collection arrayList = new ArrayList();
        synchronized (this.mQuadTree) {
            for (QuadItem access$100 : this.mItems) {
                arrayList.add(access$100.mClusterItem);
            }
        }
        return arrayList;
    }

    private double distanceSquared(Point point, Point point2) {
        return ((point.f14x - point2.f14x) * (point.f14x - point2.f14x)) + ((point.f15y - point2.f15y) * (point.f15y - point2.f15y));
    }

    private Bounds createBoundsFromSpan(Point point, double d) {
        d /= 2.0d;
        return new Bounds(point.f14x - d, point.f14x + d, point.f15y - d, point.f15y + d);
    }
}
