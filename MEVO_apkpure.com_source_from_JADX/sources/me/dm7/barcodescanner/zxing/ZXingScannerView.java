package me.dm7.barcodescanner.zxing;

import android.content.Context;
import android.util.AttributeSet;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import me.dm7.barcodescanner.core.BarcodeScannerView;

public class ZXingScannerView extends BarcodeScannerView {
    public static final List<BarcodeFormat> ALL_FORMATS = new ArrayList();
    private static final String TAG = "ZXingScannerView";
    private List<BarcodeFormat> mFormats;
    private MultiFormatReader mMultiFormatReader;
    private ResultHandler mResultHandler;

    public interface ResultHandler {
        void handleResult(Result result);
    }

    static {
        ALL_FORMATS.add(BarcodeFormat.UPC_A);
        ALL_FORMATS.add(BarcodeFormat.UPC_E);
        ALL_FORMATS.add(BarcodeFormat.EAN_13);
        ALL_FORMATS.add(BarcodeFormat.EAN_8);
        ALL_FORMATS.add(BarcodeFormat.RSS_14);
        ALL_FORMATS.add(BarcodeFormat.CODE_39);
        ALL_FORMATS.add(BarcodeFormat.CODE_93);
        ALL_FORMATS.add(BarcodeFormat.CODE_128);
        ALL_FORMATS.add(BarcodeFormat.ITF);
        ALL_FORMATS.add(BarcodeFormat.CODABAR);
        ALL_FORMATS.add(BarcodeFormat.QR_CODE);
        ALL_FORMATS.add(BarcodeFormat.DATA_MATRIX);
        ALL_FORMATS.add(BarcodeFormat.PDF_417);
    }

    public ZXingScannerView(Context context) {
        super(context);
        initMultiFormatReader();
    }

    public ZXingScannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initMultiFormatReader();
    }

    public void setFormats(List<BarcodeFormat> list) {
        this.mFormats = list;
        initMultiFormatReader();
    }

    public void setResultHandler(ResultHandler resultHandler) {
        this.mResultHandler = resultHandler;
    }

    public Collection<BarcodeFormat> getFormats() {
        if (this.mFormats == null) {
            return ALL_FORMATS;
        }
        return this.mFormats;
    }

    private void initMultiFormatReader() {
        Map enumMap = new EnumMap(DecodeHintType.class);
        enumMap.put(DecodeHintType.POSSIBLE_FORMATS, getFormats());
        this.mMultiFormatReader = new MultiFormatReader();
        this.mMultiFormatReader.setHints(enumMap);
    }

    public void onPreviewFrame(byte[] r11, android.hardware.Camera r12) {
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
        r10 = this;
        r0 = r10.mResultHandler;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = r12.getParameters();	 Catch:{ RuntimeException -> 0x0086 }
        r0 = r0.getPreviewSize();	 Catch:{ RuntimeException -> 0x0086 }
        r1 = r0.width;	 Catch:{ RuntimeException -> 0x0086 }
        r0 = r0.height;	 Catch:{ RuntimeException -> 0x0086 }
        r2 = r10.getContext();	 Catch:{ RuntimeException -> 0x0086 }
        r2 = me.dm7.barcodescanner.core.DisplayUtils.getScreenOrientation(r2);	 Catch:{ RuntimeException -> 0x0086 }
        r3 = 1;	 Catch:{ RuntimeException -> 0x0086 }
        if (r2 != r3) goto L_0x003a;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x001c:
        r2 = r11.length;	 Catch:{ RuntimeException -> 0x0086 }
        r2 = new byte[r2];	 Catch:{ RuntimeException -> 0x0086 }
        r4 = 0;	 Catch:{ RuntimeException -> 0x0086 }
        r5 = 0;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x0021:
        if (r5 >= r0) goto L_0x0038;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x0023:
        r6 = 0;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x0024:
        if (r6 >= r1) goto L_0x0035;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x0026:
        r7 = r6 * r0;	 Catch:{ RuntimeException -> 0x0086 }
        r7 = r7 + r0;	 Catch:{ RuntimeException -> 0x0086 }
        r7 = r7 - r5;	 Catch:{ RuntimeException -> 0x0086 }
        r7 = r7 - r3;	 Catch:{ RuntimeException -> 0x0086 }
        r8 = r5 * r1;	 Catch:{ RuntimeException -> 0x0086 }
        r8 = r8 + r6;	 Catch:{ RuntimeException -> 0x0086 }
        r8 = r11[r8];	 Catch:{ RuntimeException -> 0x0086 }
        r2[r7] = r8;	 Catch:{ RuntimeException -> 0x0086 }
        r6 = r6 + 1;	 Catch:{ RuntimeException -> 0x0086 }
        goto L_0x0024;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x0035:
        r5 = r5 + 1;	 Catch:{ RuntimeException -> 0x0086 }
        goto L_0x0021;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x0038:
        r11 = r2;	 Catch:{ RuntimeException -> 0x0086 }
        goto L_0x003d;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x003a:
        r9 = r1;	 Catch:{ RuntimeException -> 0x0086 }
        r1 = r0;	 Catch:{ RuntimeException -> 0x0086 }
        r0 = r9;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x003d:
        r2 = 0;	 Catch:{ RuntimeException -> 0x0086 }
        r11 = r10.buildLuminanceSource(r11, r0, r1);	 Catch:{ RuntimeException -> 0x0086 }
        if (r11 == 0) goto L_0x006d;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x0044:
        r0 = new com.google.zxing.BinaryBitmap;	 Catch:{ RuntimeException -> 0x0086 }
        r1 = new com.google.zxing.common.HybridBinarizer;	 Catch:{ RuntimeException -> 0x0086 }
        r1.<init>(r11);	 Catch:{ RuntimeException -> 0x0086 }
        r0.<init>(r1);	 Catch:{ RuntimeException -> 0x0086 }
        r11 = r10.mMultiFormatReader;	 Catch:{ ReaderException -> 0x006a, NullPointerException -> 0x0067, ArrayIndexOutOfBoundsException -> 0x0061, all -> 0x005a }
        r11 = r11.decodeWithState(r0);	 Catch:{ ReaderException -> 0x006a, NullPointerException -> 0x0067, ArrayIndexOutOfBoundsException -> 0x0061, all -> 0x005a }
        r0 = r10.mMultiFormatReader;	 Catch:{ RuntimeException -> 0x0086 }
        r0.reset();	 Catch:{ RuntimeException -> 0x0086 }
        goto L_0x006e;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x005a:
        r11 = move-exception;	 Catch:{ RuntimeException -> 0x0086 }
        r12 = r10.mMultiFormatReader;	 Catch:{ RuntimeException -> 0x0086 }
        r12.reset();	 Catch:{ RuntimeException -> 0x0086 }
        throw r11;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x0061:
        r11 = r10.mMultiFormatReader;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x0063:
        r11.reset();	 Catch:{ RuntimeException -> 0x0086 }
        goto L_0x006d;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x0067:
        r11 = r10.mMultiFormatReader;	 Catch:{ RuntimeException -> 0x0086 }
        goto L_0x0063;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x006a:
        r11 = r10.mMultiFormatReader;	 Catch:{ RuntimeException -> 0x0086 }
        goto L_0x0063;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x006d:
        r11 = r2;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x006e:
        if (r11 == 0) goto L_0x0082;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x0070:
        r12 = new android.os.Handler;	 Catch:{ RuntimeException -> 0x0086 }
        r0 = android.os.Looper.getMainLooper();	 Catch:{ RuntimeException -> 0x0086 }
        r12.<init>(r0);	 Catch:{ RuntimeException -> 0x0086 }
        r0 = new me.dm7.barcodescanner.zxing.ZXingScannerView$1;	 Catch:{ RuntimeException -> 0x0086 }
        r0.<init>(r11);	 Catch:{ RuntimeException -> 0x0086 }
        r12.post(r0);	 Catch:{ RuntimeException -> 0x0086 }
        goto L_0x0090;	 Catch:{ RuntimeException -> 0x0086 }
    L_0x0082:
        r12.setOneShotPreviewCallback(r10);	 Catch:{ RuntimeException -> 0x0086 }
        goto L_0x0090;
    L_0x0086:
        r11 = move-exception;
        r12 = "ZXingScannerView";
        r0 = r11.toString();
        android.util.Log.e(r12, r0, r11);
    L_0x0090:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.dm7.barcodescanner.zxing.ZXingScannerView.onPreviewFrame(byte[], android.hardware.Camera):void");
    }

    public void resumeCameraPreview(ResultHandler resultHandler) {
        this.mResultHandler = resultHandler;
        super.resumeCameraPreview();
    }

    public com.google.zxing.PlanarYUVLuminanceSource buildLuminanceSource(byte[] r13, int r14, int r15) {
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
        r12 = this;
        r0 = r12.getFramingRectInPreview(r14, r15);
        r1 = 0;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r11 = new com.google.zxing.PlanarYUVLuminanceSource;	 Catch:{ Exception -> 0x001f }
        r6 = r0.left;	 Catch:{ Exception -> 0x001f }
        r7 = r0.top;	 Catch:{ Exception -> 0x001f }
        r8 = r0.width();	 Catch:{ Exception -> 0x001f }
        r9 = r0.height();	 Catch:{ Exception -> 0x001f }
        r10 = 0;	 Catch:{ Exception -> 0x001f }
        r2 = r11;	 Catch:{ Exception -> 0x001f }
        r3 = r13;	 Catch:{ Exception -> 0x001f }
        r4 = r14;	 Catch:{ Exception -> 0x001f }
        r5 = r15;	 Catch:{ Exception -> 0x001f }
        r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Exception -> 0x001f }
        goto L_0x0020;
    L_0x001f:
        r11 = r1;
    L_0x0020:
        return r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.dm7.barcodescanner.zxing.ZXingScannerView.buildLuminanceSource(byte[], int, int):com.google.zxing.PlanarYUVLuminanceSource");
    }
}
