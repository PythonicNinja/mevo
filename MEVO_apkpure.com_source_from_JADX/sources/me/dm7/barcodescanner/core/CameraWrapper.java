package me.dm7.barcodescanner.core;

import android.hardware.Camera;
import android.support.annotation.NonNull;

public class CameraWrapper {
    public final Camera mCamera;
    public final int mCameraId;

    private CameraWrapper(@NonNull Camera camera, int i) {
        if (camera == null) {
            throw new NullPointerException("Camera cannot be null");
        }
        this.mCamera = camera;
        this.mCameraId = i;
    }

    public static CameraWrapper getWrapper(Camera camera, int i) {
        return camera == null ? null : new CameraWrapper(camera, i);
    }
}
