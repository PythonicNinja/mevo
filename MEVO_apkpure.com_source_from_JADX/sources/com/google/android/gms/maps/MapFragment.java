package com.google.android.gms.maps;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

@TargetApi(11)
public class MapFragment extends Fragment {
    private final zzb zzay = new zzb(this);

    @VisibleForTesting
    static class zzb extends DeferredLifecycleHelper<zza> {
        private final Fragment zzaz;
        private OnDelegateCreatedListener<zza> zzbc;
        private Activity zzbd;
        private final List<OnMapReadyCallback> zzbe = new ArrayList();

        @VisibleForTesting
        zzb(Fragment fragment) {
            this.zzaz = fragment;
        }

        private final void setActivity(Activity activity) {
            this.zzbd = activity;
            zzc();
        }

        private final void zzc() {
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
            r0 = r4.zzbd;
            if (r0 == 0) goto L_0x005b;
        L_0x0004:
            r0 = r4.zzbc;
            if (r0 == 0) goto L_0x005b;
        L_0x0008:
            r0 = r4.getDelegate();
            if (r0 != 0) goto L_0x005b;
        L_0x000e:
            r0 = r4.zzbd;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            com.google.android.gms.maps.MapsInitializer.initialize(r0);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r0 = r4.zzbd;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r0 = com.google.android.gms.maps.internal.zzbz.zza(r0);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r1 = r4.zzbd;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r1 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r1);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r0 = r0.zzc(r1);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            if (r0 != 0) goto L_0x0026;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
        L_0x0025:
            return;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
        L_0x0026:
            r1 = r4.zzbc;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r2 = new com.google.android.gms.maps.MapFragment$zza;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r3 = r4.zzaz;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r2.<init>(r3, r0);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r1.onDelegateCreated(r2);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r0 = r4.zzbe;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r0 = r0.iterator();	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
        L_0x0038:
            r1 = r0.hasNext();	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            if (r1 == 0) goto L_0x004e;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
        L_0x003e:
            r1 = r0.next();	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r1 = (com.google.android.gms.maps.OnMapReadyCallback) r1;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r2 = r4.getDelegate();	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r2 = (com.google.android.gms.maps.MapFragment.zza) r2;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r2.getMapAsync(r1);	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            goto L_0x0038;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
        L_0x004e:
            r0 = r4.zzbe;	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            r0.clear();	 Catch:{ RemoteException -> 0x0054, GooglePlayServicesNotAvailableException -> 0x005b }
            return;
        L_0x0054:
            r0 = move-exception;
            r1 = new com.google.android.gms.maps.model.RuntimeRemoteException;
            r1.<init>(r0);
            throw r1;
        L_0x005b:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapFragment.zzb.zzc():void");
        }

        protected final void createDelegate(OnDelegateCreatedListener<zza> onDelegateCreatedListener) {
            this.zzbc = onDelegateCreatedListener;
            zzc();
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
        private final Fragment zzaz;
        private final IMapFragmentDelegate zzba;

        public zza(Fragment fragment, IMapFragmentDelegate iMapFragmentDelegate) {
            this.zzba = (IMapFragmentDelegate) Preconditions.checkNotNull(iMapFragmentDelegate);
            this.zzaz = (Fragment) Preconditions.checkNotNull(fragment);
        }

        public final void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
            try {
                this.zzba.getMapAsync(new zzab(this, onMapReadyCallback));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onCreate(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                Bundle arguments = this.zzaz.getArguments();
                if (arguments != null && arguments.containsKey("MapOptions")) {
                    zzby.zza(bundle2, "MapOptions", arguments.getParcelable("MapOptions"));
                }
                this.zzba.onCreate(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                IObjectWrapper onCreateView = this.zzba.onCreateView(ObjectWrapper.wrap(layoutInflater), ObjectWrapper.wrap(viewGroup), bundle2);
                zzby.zza(bundle2, bundle);
                return (View) ObjectWrapper.unwrap(onCreateView);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onDestroy() {
            try {
                this.zzba.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onDestroyView() {
            try {
                this.zzba.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onEnterAmbient(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzba.onEnterAmbient(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onExitAmbient() {
            try {
                this.zzba.onExitAmbient();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            GoogleMapOptions googleMapOptions = (GoogleMapOptions) bundle.getParcelable("MapOptions");
            try {
                Bundle bundle3 = new Bundle();
                zzby.zza(bundle2, bundle3);
                this.zzba.onInflate(ObjectWrapper.wrap(activity), googleMapOptions, bundle3);
                zzby.zza(bundle3, bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onLowMemory() {
            try {
                this.zzba.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onPause() {
            try {
                this.zzba.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onResume() {
            try {
                this.zzba.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onSaveInstanceState(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzba.onSaveInstanceState(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onStart() {
            try {
                this.zzba.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onStop() {
            try {
                this.zzba.onStop();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public static MapFragment newInstance(GoogleMapOptions googleMapOptions) {
        MapFragment mapFragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googleMapOptions);
        mapFragment.setArguments(bundle);
        return mapFragment;
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        Preconditions.checkMainThread("getMapAsync must be called on the main thread.");
        this.zzay.getMapAsync(onMapReadyCallback);
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.zzay.setActivity(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzay.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = this.zzay.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setClickable(true);
        return onCreateView;
    }

    public void onDestroy() {
        this.zzay.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzay.onDestroyView();
        super.onDestroyView();
    }

    public final void onEnterAmbient(Bundle bundle) {
        Preconditions.checkMainThread("onEnterAmbient must be called on the main thread.");
        zzb zzb = this.zzay;
        if (zzb.getDelegate() != null) {
            ((zza) zzb.getDelegate()).onEnterAmbient(bundle);
        }
    }

    public final void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient must be called on the main thread.");
        zzb zzb = this.zzay;
        if (zzb.getDelegate() != null) {
            ((zza) zzb.getDelegate()).onExitAmbient();
        }
    }

    @SuppressLint({"NewApi"})
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new Builder(threadPolicy).permitAll().build());
        try {
            super.onInflate(activity, attributeSet, bundle);
            this.zzay.setActivity(activity);
            Parcelable createFromAttributes = GoogleMapOptions.createFromAttributes(activity, attributeSet);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("MapOptions", createFromAttributes);
            this.zzay.onInflate(activity, bundle2, bundle);
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public void onLowMemory() {
        this.zzay.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzay.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzay.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzay.onSaveInstanceState(bundle);
    }

    public void onStart() {
        super.onStart();
        this.zzay.onStart();
    }

    public void onStop() {
        this.zzay.onStop();
        super.onStop();
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }
}
