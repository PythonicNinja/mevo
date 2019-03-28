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
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class StreetViewPanoramaView extends FrameLayout {
    private final zzb zzcc;

    @VisibleForTesting
    static class zzb extends DeferredLifecycleHelper<zza> {
        private OnDelegateCreatedListener<zza> zzbc;
        private final ViewGroup zzbi;
        private final Context zzbj;
        private final List<OnStreetViewPanoramaReadyCallback> zzbv = new ArrayList();
        private final StreetViewPanoramaOptions zzcf;

        @VisibleForTesting
        zzb(ViewGroup viewGroup, Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
            this.zzbi = viewGroup;
            this.zzbj = context;
            this.zzcf = streetViewPanoramaOptions;
        }

        protected final void createDelegate(com.google.android.gms.dynamic.OnDelegateCreatedListener<com.google.android.gms.maps.StreetViewPanoramaView.zza> r4) {
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
            if (r4 == 0) goto L_0x0058;
        L_0x0006:
            r4 = r3.getDelegate();
            if (r4 != 0) goto L_0x0058;
        L_0x000c:
            r4 = r3.zzbj;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            com.google.android.gms.maps.MapsInitializer.initialize(r4);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r4 = r3.zzbj;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r4 = com.google.android.gms.maps.internal.zzbz.zza(r4);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r0 = r3.zzbj;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r0 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r0);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r1 = r3.zzcf;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r4 = r4.zza(r0, r1);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r0 = r3.zzbc;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r1 = new com.google.android.gms.maps.StreetViewPanoramaView$zza;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r2 = r3.zzbi;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r1.<init>(r2, r4);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r0.onDelegateCreated(r1);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r4 = r3.zzbv;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r4 = r4.iterator();	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
        L_0x0035:
            r0 = r4.hasNext();	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            if (r0 == 0) goto L_0x004b;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
        L_0x003b:
            r0 = r4.next();	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r0 = (com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback) r0;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r1 = r3.getDelegate();	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r1 = (com.google.android.gms.maps.StreetViewPanoramaView.zza) r1;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r1.getStreetViewPanoramaAsync(r0);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            goto L_0x0035;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
        L_0x004b:
            r4 = r3.zzbv;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r4.clear();	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            return;
        L_0x0051:
            r4 = move-exception;
            r0 = new com.google.android.gms.maps.model.RuntimeRemoteException;
            r0.<init>(r4);
            throw r0;
        L_0x0058:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.StreetViewPanoramaView.zzb.createDelegate(com.google.android.gms.dynamic.OnDelegateCreatedListener):void");
        }

        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            if (getDelegate() != null) {
                ((zza) getDelegate()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
            } else {
                this.zzbv.add(onStreetViewPanoramaReadyCallback);
            }
        }
    }

    @VisibleForTesting
    static class zza implements StreetViewLifecycleDelegate {
        private final ViewGroup parent;
        private final IStreetViewPanoramaViewDelegate zzcd;
        private View zzce;

        public zza(ViewGroup viewGroup, IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate) {
            this.zzcd = (IStreetViewPanoramaViewDelegate) Preconditions.checkNotNull(iStreetViewPanoramaViewDelegate);
            this.parent = (ViewGroup) Preconditions.checkNotNull(viewGroup);
        }

        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            try {
                this.zzcd.getStreetViewPanoramaAsync(new zzaj(this, onStreetViewPanoramaReadyCallback));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onCreate(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzcd.onCreate(bundle2);
                zzby.zza(bundle2, bundle);
                this.zzce = (View) ObjectWrapper.unwrap(this.zzcd.getView());
                this.parent.removeAllViews();
                this.parent.addView(this.zzce);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
        }

        public final void onDestroy() {
            try {
                this.zzcd.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
        }

        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
        }

        public final void onLowMemory() {
            try {
                this.zzcd.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onPause() {
            try {
                this.zzcd.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onResume() {
            try {
                this.zzcd.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onSaveInstanceState(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzcd.onSaveInstanceState(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onStart() {
            try {
                this.zzcd.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onStop() {
            try {
                this.zzcd.onStop();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public StreetViewPanoramaView(Context context) {
        super(context);
        this.zzcc = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzcc = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzcc = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
        super(context);
        this.zzcc = new zzb(this, context, streetViewPanoramaOptions);
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzcc.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }

    public final void onCreate(Bundle bundle) {
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new Builder(threadPolicy).permitAll().build());
        try {
            this.zzcc.onCreate(bundle);
            if (this.zzcc.getDelegate() == null) {
                DeferredLifecycleHelper.showGooglePlayUnavailableMessage(this);
            }
            StrictMode.setThreadPolicy(threadPolicy);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public void onDestroy() {
        this.zzcc.onDestroy();
    }

    public final void onLowMemory() {
        this.zzcc.onLowMemory();
    }

    public final void onPause() {
        this.zzcc.onPause();
    }

    public void onResume() {
        this.zzcc.onResume();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzcc.onSaveInstanceState(bundle);
    }

    public void onStart() {
        this.zzcc.onStart();
    }

    public void onStop() {
        this.zzcc.onStop();
    }
}
