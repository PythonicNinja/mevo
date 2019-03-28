package com.google.firebase.components;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class zzc {
    private final Context zza;
    private final zzb zzb;

    @VisibleForTesting
    interface zzb {
        List<String> zza(Context context);
    }

    static class zza implements zzb {
        private zza() {
        }

        public final List<String> zza(Context context) {
            context = zzb(context);
            if (context == null) {
                Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
                return Collections.emptyList();
            }
            List<String> arrayList = new ArrayList();
            for (String str : context.keySet()) {
                if ("com.google.firebase.components.ComponentRegistrar".equals(context.get(str)) && str.startsWith("com.google.firebase.components:")) {
                    arrayList.add(str.substring(31));
                }
            }
            return arrayList;
        }

        private static android.os.Bundle zzb(android.content.Context r4) {
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
            r0 = 0;
            r1 = r4.getPackageManager();	 Catch:{ NameNotFoundException -> 0x0029 }
            if (r1 != 0) goto L_0x000f;	 Catch:{ NameNotFoundException -> 0x0029 }
        L_0x0007:
            r4 = "ComponentDiscovery";	 Catch:{ NameNotFoundException -> 0x0029 }
            r1 = "Context has no PackageManager.";	 Catch:{ NameNotFoundException -> 0x0029 }
            android.util.Log.w(r4, r1);	 Catch:{ NameNotFoundException -> 0x0029 }
            return r0;	 Catch:{ NameNotFoundException -> 0x0029 }
        L_0x000f:
            r2 = new android.content.ComponentName;	 Catch:{ NameNotFoundException -> 0x0029 }
            r3 = com.google.firebase.components.ComponentDiscoveryService.class;	 Catch:{ NameNotFoundException -> 0x0029 }
            r2.<init>(r4, r3);	 Catch:{ NameNotFoundException -> 0x0029 }
            r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;	 Catch:{ NameNotFoundException -> 0x0029 }
            r4 = r1.getServiceInfo(r2, r4);	 Catch:{ NameNotFoundException -> 0x0029 }
            if (r4 != 0) goto L_0x0026;	 Catch:{ NameNotFoundException -> 0x0029 }
        L_0x001e:
            r4 = "ComponentDiscovery";	 Catch:{ NameNotFoundException -> 0x0029 }
            r1 = "ComponentDiscoveryService has no service info.";	 Catch:{ NameNotFoundException -> 0x0029 }
            android.util.Log.w(r4, r1);	 Catch:{ NameNotFoundException -> 0x0029 }
            return r0;	 Catch:{ NameNotFoundException -> 0x0029 }
        L_0x0026:
            r4 = r4.metaData;	 Catch:{ NameNotFoundException -> 0x0029 }
            return r4;
        L_0x0029:
            r4 = "ComponentDiscovery";
            r1 = "Application info not found.";
            android.util.Log.w(r4, r1);
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.components.zzc.zza.zzb(android.content.Context):android.os.Bundle");
        }
    }

    public zzc(Context context) {
        this(context, new zza());
    }

    @VisibleForTesting
    private zzc(Context context, zzb zzb) {
        this.zza = context;
        this.zzb = zzb;
    }

    public final List<ComponentRegistrar> zza() {
        return zza(this.zzb.zza(this.zza));
    }

    private static List<ComponentRegistrar> zza(List<String> list) {
        List<ComponentRegistrar> arrayList = new ArrayList();
        for (String cls : list) {
            try {
                Class cls2 = Class.forName(cls);
                if (ComponentRegistrar.class.isAssignableFrom(cls2)) {
                    arrayList.add((ComponentRegistrar) cls2.newInstance());
                } else {
                    Log.w("ComponentDiscovery", String.format("Class %s is not an instance of %s", new Object[]{cls, "com.google.firebase.components.ComponentRegistrar"}));
                }
            } catch (Throwable e) {
                Log.w("ComponentDiscovery", String.format("Class %s is not an found.", new Object[]{cls}), e);
            } catch (Throwable e2) {
                Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", new Object[]{cls}), e2);
            } catch (Throwable e22) {
                Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", new Object[]{cls}), e22);
            }
        }
        return arrayList;
    }
}
