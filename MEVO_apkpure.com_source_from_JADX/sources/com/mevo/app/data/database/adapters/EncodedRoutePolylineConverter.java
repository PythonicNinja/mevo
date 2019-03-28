package com.mevo.app.data.database.adapters;

import com.google.android.gms.maps.model.LatLng;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import java.util.List;

public class EncodedRoutePolylineConverter extends TypeConverter<String, List<LatLng>> {
    public java.lang.String getDBValue(java.util.List<com.google.android.gms.maps.model.LatLng> r1) {
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
        r0 = this;
        r1 = com.google.maps.android.PolyUtil.encode(r1);	 Catch:{ Exception -> 0x0005 }
        return r1;
    L_0x0005:
        r1 = "";
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.database.adapters.EncodedRoutePolylineConverter.getDBValue(java.util.List):java.lang.String");
    }

    public java.util.List<com.google.android.gms.maps.model.LatLng> getModelValue(java.lang.String r2) {
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
        r1 = this;
        r2 = com.google.maps.android.PolyUtil.decode(r2);	 Catch:{ Exception -> 0x0005 }
        return r2;
    L_0x0005:
        r2 = new java.util.ArrayList;
        r0 = 0;
        r2.<init>(r0);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.database.adapters.EncodedRoutePolylineConverter.getModelValue(java.lang.String):java.util.List<com.google.android.gms.maps.model.LatLng>");
    }
}
