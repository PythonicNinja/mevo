package me.dm7.barcodescanner.core;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;

public class CameraUtils {
    public static Camera getCameraInstance() {
        return getCameraInstance(getDefaultCameraId());
    }

    public static int getDefaultCameraId() {
        int numberOfCameras = Camera.getNumberOfCameras();
        CameraInfo cameraInfo = new CameraInfo();
        int i = -1;
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == 0) {
                return i2;
            }
            i = i2;
        }
        return i;
    }

    public static android.hardware.Camera getCameraInstance(int r1) {
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
        r0 = -1;
        if (r1 != r0) goto L_0x0008;
    L_0x0003:
        r1 = android.hardware.Camera.open();	 Catch:{ Exception -> 0x000d }
        goto L_0x000e;	 Catch:{ Exception -> 0x000d }
    L_0x0008:
        r1 = android.hardware.Camera.open(r1);	 Catch:{ Exception -> 0x000d }
        goto L_0x000e;
    L_0x000d:
        r1 = 0;
    L_0x000e:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.dm7.barcodescanner.core.CameraUtils.getCameraInstance(int):android.hardware.Camera");
    }

    public static boolean isFlashSupported(Camera camera) {
        if (camera == null) {
            return false;
        }
        camera = camera.getParameters();
        if (camera.getFlashMode() == null) {
            return false;
        }
        camera = camera.getSupportedFlashModes();
        if (!(camera == null || camera.isEmpty())) {
            if (camera.size() != 1 || ((String) camera.get(0)).equals("off") == null) {
                return true;
            }
        }
        return false;
    }
}
