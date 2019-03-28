package com.mevo.app.presentation.main.rent_bike;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MyBarcodeScanner extends ZXingScannerView {
    private static final long ANALYSIS_BLOCK_TIME = 3000;
    private boolean isAnalysisBlocked = null;

    private class ClearViewFinder extends ViewFinderView {
        public void drawLaser(Canvas canvas) {
        }

        public void onDraw(Canvas canvas) {
        }

        public ClearViewFinder(Context context) {
            super(context);
        }

        public ClearViewFinder(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public MyBarcodeScanner(Context context) {
        super(context);
    }

    public MyBarcodeScanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onPreviewFrame(final byte[] r3, final android.hardware.Camera r4) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.isAnalysisBlocked;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = new android.os.Handler;
        r0.<init>();
        r1 = new com.mevo.app.presentation.main.rent_bike.MyBarcodeScanner$1;
        r1.<init>(r3, r4);
        r3 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r0.postDelayed(r1, r3);
        return;
    L_0x0014:
        super.onPreviewFrame(r3, r4);	 Catch:{ Exception -> 0x0017 }
    L_0x0017:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.presentation.main.rent_bike.MyBarcodeScanner.onPreviewFrame(byte[], android.hardware.Camera):void");
    }

    public void blockAnalysisForAMoment() {
        this.isAnalysisBlocked = true;
    }

    protected IViewFinder createViewFinderView(Context context) {
        return new ClearViewFinder(context);
    }
}
