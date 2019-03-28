package com.mevo.app.tools;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.camera2.CameraManager;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import com.mevo.app.App;

public class FlashlightHelper {
    private static final String TAG = "FlashlightHelper";
    private Camera camera;
    private boolean isFlashlightOn = false;

    public static class FlashlightNoAvailableException extends Exception {
        public String getMessage() {
            return "Flashlight is not available on this device";
        }
    }

    public void setFlashLight(boolean z) throws FlashlightNoAvailableException {
        if (!Utils.isFLashlightAvailable()) {
            throw new FlashlightNoAvailableException();
        } else if (z == this.isFlashlightOn) {
            Log.m102w(TAG, "Flashlight is already in required state");
        } else {
            if (VERSION.SDK_INT >= 23) {
                flashlightCamera2(z);
            } else {
                flashlightCameraOld(z);
            }
        }
    }

    private void flashlightCameraOld(boolean z) {
        if (z) {
            try {
                this.camera = Camera.open();
                Parameters parameters = this.camera.getParameters();
                parameters.setFlashMode("torch");
                this.camera.setParameters(parameters);
                this.camera.startPreview();
            } catch (boolean z2) {
                Log.ex(TAG, z2);
                return;
            }
        } else if (this.camera != null) {
            this.camera.stopPreview();
            this.camera.release();
            this.camera = null;
        }
        this.isFlashlightOn = z2;
    }

    @RequiresApi(23)
    private void flashlightCamera2(boolean z) {
        CameraManager cameraManager = (CameraManager) App.getAppContext().getSystemService("camera");
        try {
            try {
                cameraManager.setTorchMode(cameraManager.getCameraIdList()[0], z);
                this.isFlashlightOn = z;
            } catch (boolean z2) {
                Log.ex(TAG, z2);
            }
        } catch (boolean z22) {
            Log.ex(TAG, z22);
        }
    }

    public boolean isFlashlightOn() {
        return this.isFlashlightOn;
    }
}
