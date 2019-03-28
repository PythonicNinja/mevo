package com.google.android.gms.maps.model;

import java.net.URL;

public abstract class UrlTileProvider implements TileProvider {
    private final int height;
    private final int width;

    public UrlTileProvider(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public final com.google.android.gms.maps.model.Tile getTile(int r6, int r7, int r8) {
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
        r5 = this;
        r6 = r5.getTileUrl(r6, r7, r8);
        if (r6 != 0) goto L_0x0009;
    L_0x0006:
        r6 = NO_TILE;
        return r6;
    L_0x0009:
        r7 = new com.google.android.gms.maps.model.Tile;	 Catch:{ IOException -> 0x0030 }
        r8 = r5.width;	 Catch:{ IOException -> 0x0030 }
        r0 = r5.height;	 Catch:{ IOException -> 0x0030 }
        r6 = r6.openStream();	 Catch:{ IOException -> 0x0030 }
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x0030 }
        r1.<init>();	 Catch:{ IOException -> 0x0030 }
        r2 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;	 Catch:{ IOException -> 0x0030 }
        r2 = new byte[r2];	 Catch:{ IOException -> 0x0030 }
    L_0x001c:
        r3 = r6.read(r2);	 Catch:{ IOException -> 0x0030 }
        r4 = -1;	 Catch:{ IOException -> 0x0030 }
        if (r3 == r4) goto L_0x0028;	 Catch:{ IOException -> 0x0030 }
    L_0x0023:
        r4 = 0;	 Catch:{ IOException -> 0x0030 }
        r1.write(r2, r4, r3);	 Catch:{ IOException -> 0x0030 }
        goto L_0x001c;	 Catch:{ IOException -> 0x0030 }
    L_0x0028:
        r6 = r1.toByteArray();	 Catch:{ IOException -> 0x0030 }
        r7.<init>(r8, r0, r6);	 Catch:{ IOException -> 0x0030 }
        return r7;
    L_0x0030:
        r7 = 0;
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.UrlTileProvider.getTile(int, int, int):com.google.android.gms.maps.model.Tile");
    }

    public abstract URL getTileUrl(int i, int i2, int i3);
}
