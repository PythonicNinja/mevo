package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class MapView extends FrameLayout {
    private final zzb zzbf;

    @VisibleForTesting
    static class zzb extends DeferredLifecycleHelper<zza> {
        private OnDelegateCreatedListener<zza> zzbc;
        private final List<OnMapReadyCallback> zzbe = new ArrayList();
        private final ViewGroup zzbi;
        private final Context zzbj;
        private final GoogleMapOptions zzbk;

        @VisibleForTesting
        zzb(ViewGroup viewGroup, Context context, GoogleMapOptions googleMapOptions) {
            this.zzbi = viewGroup;
            this.zzbj = context;
            this.zzbk = googleMapOptions;
        }

        protected final void createDelegate(com.google.android.gms.dynamic.OnDelegateCreatedListener<com.google.android.gms.maps.MapView.zza> r4) {
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
            r3 = this;
            r3.zzbc = r4;
            r4 = r3.zzbc;
            if (r4 == 0) goto L_0x005b;
        L_0x0006:
            r4 = r3.getDelegate();
            if (r4 != 0) goto L_0x005b;
        L_0x000c:
            r4 = r3.zzbj;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            com.google.android.gms.maps.MapsInitializer.initialize(r4);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r4 = r3.zzbj;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r4 = com.google.android.gms.maps.internal.zzbz.zza(r4);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r0 = r3.zzbj;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r0 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r0);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r1 = r3.zzbk;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r4 = r4.zza(r0, r1);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            if (r4 != 0) goto L_0x0026;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
        L_0x0025:
            return;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
        L_0x0026:
            r0 = r3.zzbc;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r1 = new com.google.android.gms.maps.MapView$zza;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r2 = r3.zzbi;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r1.<init>(r2, r4);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r0.onDelegateCreated(r1);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r4 = r3.zzbe;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r4 = r4.iterator();	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
        L_0x0038:
            r0 = r4.hasNext();	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            if (r0 == 0) goto L_0x004e;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
        L_0x003e:
            r0 = r4.next();	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r0 = (com.google.android.gms.maps.OnMapReadyCallback) r0;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r1 = r3.getDelegate();	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r1 = (com.google.android.gms.maps.MapView.zza) r1;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r1.getMapAsync(r0);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            goto L_0x0038;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
        L_0x004e:
            r4 = r3.zzbe;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r4.clear();	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            return;
        L_0x0054:
            r4 = move-exception;
            r0 = new com.google.android.gms.maps.model.RuntimeRemoteException;
            r0.<init>(r4);
            throw r0;
        L_0x005b:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapView.zzb.createDelegate(com.google.android.gms.dynamic.OnDelegateCreatedListener):void");
        }

        public final void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
            if (getDelegate() != null) {
                ((zza) getDelegate()).getMapAsync(onMapReadyCallback);
            } else {
                this.zzbe.add(onMapReadyCallback);
            }
        }
    }

    @VisibleForTesting
    static class zza implements MapLifecycleDelegate {
        private final ViewGroup parent;
        private final IMapViewDelegate zzbg;
        private View zzbh;

        public zza(ViewGroup viewGroup, IMapViewDelegate iMapViewDelegate) {
            this.zzbg = (IMapViewDelegate) Preconditions.checkNotNull(iMapViewDelegate);
            this.parent = (ViewGroup) Preconditions.checkNotNull(viewGroup);
        }

        public final void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
            try {
                this.zzbg.getMapAsync(new zzac(this, onMapReadyCallback));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onCreate(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzbg.onCreate(bundle2);
                zzby.zza(bundle2, bundle);
                this.zzbh = (View) ObjectWrapper.unwrap(this.zzbg.getView());
                this.parent.removeAllViews();
                this.parent.addView(this.zzbh);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
        }

        public final void onDestroy() {
            try {
                this.zzbg.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
        }

        public final void onEnterAmbient(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzbg.onEnterAmbient(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onExitAmbient() {
            try {
                this.zzbg.onExitAmbient();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
        }

        public final void onLowMemory() {
            try {
                this.zzbg.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onPause() {
            try {
                this.zzbg.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onResume() {
            try {
                this.zzbg.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onSaveInstanceState(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzbg.onSaveInstanceState(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onStart() {
            try {
                this.zzbg.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onStop() {
            try {
                this.zzbg.onStop();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public MapView(Context context) {
        super(context);
        this.zzbf = new zzb(this, context, null);
        setClickable(true);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzbf = new zzb(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        setClickable(true);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzbf = new zzb(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        setClickable(true);
    }

    public MapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context);
        this.zzbf = new zzb(this, context, googleMapOptions);
        setClickable(true);
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        Preconditions.checkMainThread("getMapAsync() must be called on the main thread");
        this.zzbf.getMapAsync(onMapReadyCallback);
    }

    public final void onCreate(Bundle bundle) {
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new Builder(threadPolicy).permitAll().build());
        try {
            this.zzbf.onCreate(bundle);
            if (this.zzbf.getDelegate() == null) {
                DeferredLifecycleHelper.showGooglePlayUnavailableMessage(this);
            }
            StrictMode.setThreadPolicy(threadPolicy);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public final void onDestroy() {
        this.zzbf.onDestroy();
    }

    public final void onEnterAmbient(Bundle bundle) {
        Preconditions.checkMainThread("onEnterAmbient() must be called on the main thread");
        zzb zzb = this.zzbf;
        if (zzb.getDelegate() != null) {
            ((zza) zzb.getDelegate()).onEnterAmbient(bundle);
        }
    }

    public final void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient() must be called on the main thread");
        zzb zzb = this.zzbf;
        if (zzb.getDelegate() != null) {
            ((zza) zzb.getDelegate()).onExitAmbient();
        }
    }

    public final void onLowMemory() {
        this.zzbf.onLowMemory();
    }

    public final void onPause() {
        this.zzbf.onPause();
    }

    public final void onResume() {
        this.zzbf.onResume();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzbf.onSaveInstanceState(bundle);
    }

    public final void onStart() {
        this.zzbf.onStart();
    }

    public final void onStop() {
        this.zzbf.onStop();
    }
}
