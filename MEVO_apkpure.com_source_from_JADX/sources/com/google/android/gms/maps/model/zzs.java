package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzaf;

final class zzs implements TileProvider {
    private final zzaf zzek = this.zzel.zzeh;
    private final /* synthetic */ TileOverlayOptions zzel;

    zzs(TileOverlayOptions tileOverlayOptions) {
        this.zzel = tileOverlayOptions;
    }

    public final com.google.android.gms.maps.model.Tile getTile(int r2, int r3, int r4) {
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
        r0 = r1.zzek;	 Catch:{ RemoteException -> 0x0007 }
        r2 = r0.getTile(r2, r3, r4);	 Catch:{ RemoteException -> 0x0007 }
        return r2;
    L_0x0007:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.zzs.getTile(int, int, int):com.google.android.gms.maps.model.Tile");
    }
}
