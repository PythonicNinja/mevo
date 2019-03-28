package me.dm7.barcodescanner.core;

import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class CameraHandlerThread extends HandlerThread {
    private static final String LOG_TAG = "CameraHandlerThread";
    private BarcodeScannerView mScannerView;

    public CameraHandlerThread(BarcodeScannerView barcodeScannerView) {
        super(LOG_TAG);
        this.mScannerView = barcodeScannerView;
        start();
    }

    public void startCamera(final int i) {
        new Handler(getLooper()).post(new Runnable() {
            public void run() {
                final Camera cameraInstance = CameraUtils.getCameraInstance(i);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        CameraHandlerThread.this.mScannerView.setupCameraPreview(CameraWrapper.getWrapper(cameraInstance, i));
                    }
                });
            }
        });
    }
}
