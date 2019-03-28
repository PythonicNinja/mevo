package com.google.maps.android.clustering;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.MarkerManager.Collection;
import com.google.maps.android.clustering.algo.Algorithm;
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm;
import com.google.maps.android.clustering.algo.PreCachingAlgorithmDecorator;
import com.google.maps.android.clustering.view.ClusterRenderer;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ClusterManager<T extends ClusterItem> implements OnCameraIdleListener, OnMarkerClickListener, OnInfoWindowClickListener {
    private Algorithm<T> mAlgorithm;
    private final ReadWriteLock mAlgorithmLock;
    private final Collection mClusterMarkers;
    private ClusterTask mClusterTask;
    private final ReadWriteLock mClusterTaskLock;
    private GoogleMap mMap;
    private final MarkerManager mMarkerManager;
    private final Collection mMarkers;
    private OnClusterClickListener<T> mOnClusterClickListener;
    private OnClusterInfoWindowClickListener<T> mOnClusterInfoWindowClickListener;
    private OnClusterItemClickListener<T> mOnClusterItemClickListener;
    private OnClusterItemInfoWindowClickListener<T> mOnClusterItemInfoWindowClickListener;
    private CameraPosition mPreviousCameraPosition;
    private ClusterRenderer<T> mRenderer;

    private class ClusterTask extends AsyncTask<Float, Void, Set<? extends Cluster<T>>> {
        private ClusterTask() {
        }

        protected Set<? extends Cluster<T>> doInBackground(Float... fArr) {
            ClusterManager.this.mAlgorithmLock.readLock().lock();
            try {
                fArr = ClusterManager.this.mAlgorithm.getClusters((double) fArr[0].floatValue());
                return fArr;
            } finally {
                ClusterManager.this.mAlgorithmLock.readLock().unlock();
            }
        }

        protected void onPostExecute(Set<? extends Cluster<T>> set) {
            ClusterManager.this.mRenderer.onClustersChanged(set);
        }
    }

    public interface OnClusterClickListener<T extends ClusterItem> {
        boolean onClusterClick(Cluster<T> cluster);
    }

    public interface OnClusterInfoWindowClickListener<T extends ClusterItem> {
        void onClusterInfoWindowClick(Cluster<T> cluster);
    }

    public interface OnClusterItemClickListener<T extends ClusterItem> {
        boolean onClusterItemClick(T t);
    }

    public interface OnClusterItemInfoWindowClickListener<T extends ClusterItem> {
        void onClusterItemInfoWindowClick(T t);
    }

    public ClusterManager(Context context, GoogleMap googleMap) {
        this(context, googleMap, new MarkerManager(googleMap));
    }

    public ClusterManager(Context context, GoogleMap googleMap, MarkerManager markerManager) {
        this.mAlgorithmLock = new ReentrantReadWriteLock();
        this.mClusterTaskLock = new ReentrantReadWriteLock();
        this.mMap = googleMap;
        this.mMarkerManager = markerManager;
        this.mClusterMarkers = markerManager.newCollection();
        this.mMarkers = markerManager.newCollection();
        this.mRenderer = new DefaultClusterRenderer(context, googleMap, this);
        this.mAlgorithm = new PreCachingAlgorithmDecorator(new NonHierarchicalDistanceBasedAlgorithm());
        this.mClusterTask = new ClusterTask();
        this.mRenderer.onAdd();
    }

    public Collection getMarkerCollection() {
        return this.mMarkers;
    }

    public Collection getClusterMarkerCollection() {
        return this.mClusterMarkers;
    }

    public MarkerManager getMarkerManager() {
        return this.mMarkerManager;
    }

    public void setRenderer(ClusterRenderer<T> clusterRenderer) {
        this.mRenderer.setOnClusterClickListener(null);
        this.mRenderer.setOnClusterItemClickListener(null);
        this.mClusterMarkers.clear();
        this.mMarkers.clear();
        this.mRenderer.onRemove();
        this.mRenderer = clusterRenderer;
        this.mRenderer.onAdd();
        this.mRenderer.setOnClusterClickListener(this.mOnClusterClickListener);
        this.mRenderer.setOnClusterInfoWindowClickListener(this.mOnClusterInfoWindowClickListener);
        this.mRenderer.setOnClusterItemClickListener(this.mOnClusterItemClickListener);
        this.mRenderer.setOnClusterItemInfoWindowClickListener(this.mOnClusterItemInfoWindowClickListener);
        cluster();
    }

    public void setAlgorithm(Algorithm<T> algorithm) {
        this.mAlgorithmLock.writeLock().lock();
        try {
            if (this.mAlgorithm != null) {
                algorithm.addItems(this.mAlgorithm.getItems());
            }
            this.mAlgorithm = new PreCachingAlgorithmDecorator(algorithm);
            cluster();
        } finally {
            this.mAlgorithmLock.writeLock().unlock();
        }
    }

    public void setAnimation(boolean z) {
        this.mRenderer.setAnimation(z);
    }

    public ClusterRenderer<T> getRenderer() {
        return this.mRenderer;
    }

    public Algorithm<T> getAlgorithm() {
        return this.mAlgorithm;
    }

    public void clearItems() {
        this.mAlgorithmLock.writeLock().lock();
        try {
            this.mAlgorithm.clearItems();
        } finally {
            this.mAlgorithmLock.writeLock().unlock();
        }
    }

    public void addItems(java.util.Collection<T> collection) {
        this.mAlgorithmLock.writeLock().lock();
        try {
            this.mAlgorithm.addItems(collection);
        } finally {
            this.mAlgorithmLock.writeLock().unlock();
        }
    }

    public void addItem(T t) {
        this.mAlgorithmLock.writeLock().lock();
        try {
            this.mAlgorithm.addItem(t);
        } finally {
            this.mAlgorithmLock.writeLock().unlock();
        }
    }

    public void removeItem(T t) {
        this.mAlgorithmLock.writeLock().lock();
        try {
            this.mAlgorithm.removeItem(t);
        } finally {
            this.mAlgorithmLock.writeLock().unlock();
        }
    }

    public void cluster() {
        this.mClusterTaskLock.writeLock().lock();
        try {
            this.mClusterTask.cancel(true);
            this.mClusterTask = new ClusterTask();
            if (VERSION.SDK_INT < 11) {
                this.mClusterTask.execute(new Float[]{Float.valueOf(this.mMap.getCameraPosition().zoom)});
            } else {
                this.mClusterTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Float[]{Float.valueOf(this.mMap.getCameraPosition().zoom)});
            }
            this.mClusterTaskLock.writeLock().unlock();
        } catch (Throwable th) {
            this.mClusterTaskLock.writeLock().unlock();
        }
    }

    public void onCameraIdle() {
        if (this.mRenderer instanceof OnCameraIdleListener) {
            ((OnCameraIdleListener) this.mRenderer).onCameraIdle();
        }
        CameraPosition cameraPosition = this.mMap.getCameraPosition();
        if (this.mPreviousCameraPosition == null || this.mPreviousCameraPosition.zoom != cameraPosition.zoom) {
            this.mPreviousCameraPosition = this.mMap.getCameraPosition();
            cluster();
        }
    }

    public boolean onMarkerClick(Marker marker) {
        return getMarkerManager().onMarkerClick(marker);
    }

    public void onInfoWindowClick(Marker marker) {
        getMarkerManager().onInfoWindowClick(marker);
    }

    public void setOnClusterClickListener(OnClusterClickListener<T> onClusterClickListener) {
        this.mOnClusterClickListener = onClusterClickListener;
        this.mRenderer.setOnClusterClickListener(onClusterClickListener);
    }

    public void setOnClusterInfoWindowClickListener(OnClusterInfoWindowClickListener<T> onClusterInfoWindowClickListener) {
        this.mOnClusterInfoWindowClickListener = onClusterInfoWindowClickListener;
        this.mRenderer.setOnClusterInfoWindowClickListener(onClusterInfoWindowClickListener);
    }

    public void setOnClusterItemClickListener(OnClusterItemClickListener<T> onClusterItemClickListener) {
        this.mOnClusterItemClickListener = onClusterItemClickListener;
        this.mRenderer.setOnClusterItemClickListener(onClusterItemClickListener);
    }

    public void setOnClusterItemInfoWindowClickListener(OnClusterItemInfoWindowClickListener<T> onClusterItemInfoWindowClickListener) {
        this.mOnClusterItemInfoWindowClickListener = onClusterItemInfoWindowClickListener;
        this.mRenderer.setOnClusterItemInfoWindowClickListener(onClusterItemInfoWindowClickListener);
    }
}
