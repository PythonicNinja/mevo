package com.google.maps.android.clustering.algo;

import android.support.v4.util.LruCache;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PreCachingAlgorithmDecorator<T extends ClusterItem> implements Algorithm<T> {
    private final Algorithm<T> mAlgorithm;
    private final LruCache<Integer, Set<? extends Cluster<T>>> mCache = new LruCache(5);
    private final ReadWriteLock mCacheLock = new ReentrantReadWriteLock();

    private class PrecacheRunnable implements Runnable {
        private final int mZoom;

        public PrecacheRunnable(int i) {
            this.mZoom = i;
        }

        public void run() {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
            /*
            r4 = this;
            r0 = java.lang.Math.random();	 Catch:{ InterruptedException -> 0x0010 }
            r2 = 4647503709213818880; // 0x407f400000000000 float:0.0 double:500.0;	 Catch:{ InterruptedException -> 0x0010 }
            r0 = r0 * r2;	 Catch:{ InterruptedException -> 0x0010 }
            r0 = r0 + r2;	 Catch:{ InterruptedException -> 0x0010 }
            r0 = (long) r0;	 Catch:{ InterruptedException -> 0x0010 }
            java.lang.Thread.sleep(r0);	 Catch:{ InterruptedException -> 0x0010 }
        L_0x0010:
            r0 = com.google.maps.android.clustering.algo.PreCachingAlgorithmDecorator.this;
            r1 = r4.mZoom;
            r0.getClustersInternal(r1);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.clustering.algo.PreCachingAlgorithmDecorator.PrecacheRunnable.run():void");
        }
    }

    public PreCachingAlgorithmDecorator(Algorithm<T> algorithm) {
        this.mAlgorithm = algorithm;
    }

    public void addItem(T t) {
        this.mAlgorithm.addItem(t);
        clearCache();
    }

    public void addItems(Collection<T> collection) {
        this.mAlgorithm.addItems(collection);
        clearCache();
    }

    public void clearItems() {
        this.mAlgorithm.clearItems();
        clearCache();
    }

    public void removeItem(T t) {
        this.mAlgorithm.removeItem(t);
        clearCache();
    }

    private void clearCache() {
        this.mCache.evictAll();
    }

    public Set<? extends Cluster<T>> getClusters(double d) {
        d = (int) d;
        Set<? extends Cluster<T>> clustersInternal = getClustersInternal(d);
        int i = d + 1;
        if (this.mCache.get(Integer.valueOf(i)) == null) {
            new Thread(new PrecacheRunnable(i)).start();
        }
        d--;
        if (this.mCache.get(Integer.valueOf(d)) == null) {
            new Thread(new PrecacheRunnable(d)).start();
        }
        return clustersInternal;
    }

    public Collection<T> getItems() {
        return this.mAlgorithm.getItems();
    }

    private Set<? extends Cluster<T>> getClustersInternal(int i) {
        this.mCacheLock.readLock().lock();
        Set<? extends Cluster<T>> set = (Set) this.mCache.get(Integer.valueOf(i));
        this.mCacheLock.readLock().unlock();
        if (set == null) {
            this.mCacheLock.writeLock().lock();
            set = (Set) this.mCache.get(Integer.valueOf(i));
            if (set == null) {
                set = this.mAlgorithm.getClusters((double) i);
                this.mCache.put(Integer.valueOf(i), set);
            }
            this.mCacheLock.writeLock().unlock();
        }
        return set;
    }
}
