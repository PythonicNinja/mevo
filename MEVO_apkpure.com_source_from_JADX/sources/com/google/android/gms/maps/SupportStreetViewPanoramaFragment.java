package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.support.v4.app.Fragment;
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
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class SupportStreetViewPanoramaFragment extends Fragment {
    private final zzb zzch = new zzb(this);

    @VisibleForTesting
    static class zzb extends DeferredLifecycleHelper<zza> {
        private final Fragment fragment;
        private OnDelegateCreatedListener<zza> zzbc;
        private Activity zzbd;
        private final List<OnStreetViewPanoramaReadyCallback> zzbv = new ArrayList();

        @VisibleForTesting
        zzb(Fragment fragment) {
            this.fragment = fragment;
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
            if (r0 == 0) goto L_0x0058;
        L_0x0004:
            r0 = r4.zzbc;
            if (r0 == 0) goto L_0x0058;
        L_0x0008:
            r0 = r4.getDelegate();
            if (r0 != 0) goto L_0x0058;
        L_0x000e:
            r0 = r4.zzbd;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            com.google.android.gms.maps.MapsInitializer.initialize(r0);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r0 = r4.zzbd;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r0 = com.google.android.gms.maps.internal.zzbz.zza(r0);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r1 = r4.zzbd;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r1 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r1);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r0 = r0.zzd(r1);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r1 = r4.zzbc;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r2 = new com.google.android.gms.maps.SupportStreetViewPanoramaFragment$zza;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r3 = r4.fragment;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r2.<init>(r3, r0);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r1.onDelegateCreated(r2);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r0 = r4.zzbv;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r0 = r0.iterator();	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
        L_0x0035:
            r1 = r0.hasNext();	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            if (r1 == 0) goto L_0x004b;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
        L_0x003b:
            r1 = r0.next();	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r1 = (com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback) r1;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r2 = r4.getDelegate();	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r2 = (com.google.android.gms.maps.SupportStreetViewPanoramaFragment.zza) r2;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r2.getStreetViewPanoramaAsync(r1);	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            goto L_0x0035;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
        L_0x004b:
            r0 = r4.zzbv;	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            r0.clear();	 Catch:{ RemoteException -> 0x0051, GooglePlayServicesNotAvailableException -> 0x0058 }
            return;
        L_0x0051:
            r0 = move-exception;
            r1 = new com.google.android.gms.maps.model.RuntimeRemoteException;
            r1.<init>(r0);
            throw r1;
        L_0x0058:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.SupportStreetViewPanoramaFragment.zzb.zzc():void");
        }

        protected final void createDelegate(OnDelegateCreatedListener<zza> onDelegateCreatedListener) {
            this.zzbc = onDelegateCreatedListener;
            zzc();
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
        private final Fragment fragment;
        private final IStreetViewPanoramaFragmentDelegate zzbt;

        public zza(Fragment fragment, IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate) {
            this.zzbt = (IStreetViewPanoramaFragmentDelegate) Preconditions.checkNotNull(iStreetViewPanoramaFragmentDelegate);
            this.fragment = (Fragment) Preconditions.checkNotNull(fragment);
        }

        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            try {
                this.zzbt.getStreetViewPanoramaAsync(new zzal(this, onStreetViewPanoramaReadyCallback));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onCreate(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                Bundle arguments = this.fragment.getArguments();
                if (arguments != null && arguments.containsKey("StreetViewPanoramaOptions")) {
                    zzby.zza(bundle2, "StreetViewPanoramaOptions", arguments.getParcelable("StreetViewPanoramaOptions"));
                }
                this.zzbt.onCreate(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                IObjectWrapper onCreateView = this.zzbt.onCreateView(ObjectWrapper.wrap(layoutInflater), ObjectWrapper.wrap(viewGroup), bundle2);
                zzby.zza(bundle2, bundle);
                return (View) ObjectWrapper.unwrap(onCreateView);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onDestroy() {
            try {
                this.zzbt.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onDestroyView() {
            try {
                this.zzbt.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                bundle = new Bundle();
                zzby.zza(bundle2, bundle);
                this.zzbt.onInflate(ObjectWrapper.wrap(activity), null, bundle);
                zzby.zza(bundle, bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onLowMemory() {
            try {
                this.zzbt.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onPause() {
            try {
                this.zzbt.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onResume() {
            try {
                this.zzbt.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onSaveInstanceState(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                zzby.zza(bundle, bundle2);
                this.zzbt.onSaveInstanceState(bundle2);
                zzby.zza(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onStart() {
            try {
                this.zzbt.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void onStop() {
            try {
                this.zzbt.onStop();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public static SupportStreetViewPanoramaFragment newInstance() {
        return new SupportStreetViewPanoramaFragment();
    }

    public static SupportStreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions streetViewPanoramaOptions) {
        SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment = new SupportStreetViewPanoramaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("StreetViewPanoramaOptions", streetViewPanoramaOptions);
        supportStreetViewPanoramaFragment.setArguments(bundle);
        return supportStreetViewPanoramaFragment;
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzch.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.zzch.setActivity(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzch.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzch.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        this.zzch.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzch.onDestroyView();
        super.onDestroyView();
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new Builder(threadPolicy).permitAll().build());
        try {
            super.onInflate(activity, attributeSet, bundle);
            this.zzch.setActivity(activity);
            this.zzch.onInflate(activity, new Bundle(), bundle);
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public void onLowMemory() {
        this.zzch.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzch.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzch.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzch.onSaveInstanceState(bundle);
    }

    public void onStart() {
        super.onStart();
        this.zzch.onStart();
    }

    public void onStop() {
        this.zzch.onStop();
        super.onStop();
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }
}
