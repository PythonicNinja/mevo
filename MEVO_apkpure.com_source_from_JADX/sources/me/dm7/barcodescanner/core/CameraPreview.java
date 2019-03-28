package me.dm7.barcodescanner.core;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import java.util.List;

public class CameraPreview extends SurfaceView implements Callback {
    private static final String TAG = "CameraPreview";
    AutoFocusCallback autoFocusCB = new C05092();
    private Runnable doAutoFocus = new C05081();
    private boolean mAutoFocus = true;
    private Handler mAutoFocusHandler;
    private CameraWrapper mCameraWrapper;
    private PreviewCallback mPreviewCallback;
    private boolean mPreviewing = true;
    private boolean mShouldScaleToFill = true;
    private boolean mSurfaceCreated = null;

    /* renamed from: me.dm7.barcodescanner.core.CameraPreview$1 */
    class C05081 implements Runnable {
        C05081() {
        }

        public void run() {
            if (CameraPreview.this.mCameraWrapper != null && CameraPreview.this.mPreviewing && CameraPreview.this.mAutoFocus && CameraPreview.this.mSurfaceCreated) {
                CameraPreview.this.safeAutoFocus();
            }
        }
    }

    /* renamed from: me.dm7.barcodescanner.core.CameraPreview$2 */
    class C05092 implements AutoFocusCallback {
        C05092() {
        }

        public void onAutoFocus(boolean z, Camera camera) {
            CameraPreview.this.scheduleAutoFocus();
        }
    }

    public CameraPreview(Context context, CameraWrapper cameraWrapper, PreviewCallback previewCallback) {
        super(context);
        init(cameraWrapper, previewCallback);
    }

    public CameraPreview(Context context, AttributeSet attributeSet, CameraWrapper cameraWrapper, PreviewCallback previewCallback) {
        super(context, attributeSet);
        init(cameraWrapper, previewCallback);
    }

    public void init(CameraWrapper cameraWrapper, PreviewCallback previewCallback) {
        setCamera(cameraWrapper, previewCallback);
        this.mAutoFocusHandler = new Handler();
        getHolder().addCallback(this);
        getHolder().setType(3);
    }

    public void setCamera(CameraWrapper cameraWrapper, PreviewCallback previewCallback) {
        this.mCameraWrapper = cameraWrapper;
        this.mPreviewCallback = previewCallback;
    }

    public void setShouldScaleToFill(boolean z) {
        this.mShouldScaleToFill = z;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mSurfaceCreated = true;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (surfaceHolder.getSurface() != null) {
            stopCameraPreview();
            showCameraPreview();
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mSurfaceCreated = null;
        stopCameraPreview();
    }

    public void showCameraPreview() {
        if (this.mCameraWrapper != null) {
            try {
                getHolder().addCallback(this);
                this.mPreviewing = true;
                setupCameraParameters();
                this.mCameraWrapper.mCamera.setPreviewDisplay(getHolder());
                this.mCameraWrapper.mCamera.setDisplayOrientation(getDisplayOrientation());
                this.mCameraWrapper.mCamera.setOneShotPreviewCallback(this.mPreviewCallback);
                this.mCameraWrapper.mCamera.startPreview();
                if (!this.mAutoFocus) {
                    return;
                }
                if (this.mSurfaceCreated) {
                    safeAutoFocus();
                } else {
                    scheduleAutoFocus();
                }
            } catch (Throwable e) {
                Log.e(TAG, e.toString(), e);
            }
        }
    }

    public void safeAutoFocus() {
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
        r2 = this;
        r0 = r2.mCameraWrapper;	 Catch:{ RuntimeException -> 0x000a }
        r0 = r0.mCamera;	 Catch:{ RuntimeException -> 0x000a }
        r1 = r2.autoFocusCB;	 Catch:{ RuntimeException -> 0x000a }
        r0.autoFocus(r1);	 Catch:{ RuntimeException -> 0x000a }
        goto L_0x000d;
    L_0x000a:
        r2.scheduleAutoFocus();
    L_0x000d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.dm7.barcodescanner.core.CameraPreview.safeAutoFocus():void");
    }

    public void stopCameraPreview() {
        if (this.mCameraWrapper != null) {
            try {
                this.mPreviewing = false;
                getHolder().removeCallback(this);
                this.mCameraWrapper.mCamera.cancelAutoFocus();
                this.mCameraWrapper.mCamera.setOneShotPreviewCallback(null);
                this.mCameraWrapper.mCamera.stopPreview();
            } catch (Throwable e) {
                Log.e(TAG, e.toString(), e);
            }
        }
    }

    public void setupCameraParameters() {
        Size optimalPreviewSize = getOptimalPreviewSize();
        Parameters parameters = this.mCameraWrapper.mCamera.getParameters();
        parameters.setPreviewSize(optimalPreviewSize.width, optimalPreviewSize.height);
        this.mCameraWrapper.mCamera.setParameters(parameters);
        adjustViewSize(optimalPreviewSize);
    }

    private void adjustViewSize(Size size) {
        Point convertSizeToLandscapeOrientation = convertSizeToLandscapeOrientation(new Point(getWidth(), getHeight()));
        float f = ((float) size.width) / ((float) size.height);
        if (((float) convertSizeToLandscapeOrientation.x) / ((float) convertSizeToLandscapeOrientation.y) > f) {
            setViewSize((int) (((float) convertSizeToLandscapeOrientation.y) * f), convertSizeToLandscapeOrientation.y);
        } else {
            setViewSize(convertSizeToLandscapeOrientation.x, (int) (((float) convertSizeToLandscapeOrientation.x) / f));
        }
    }

    private Point convertSizeToLandscapeOrientation(Point point) {
        if (getDisplayOrientation() % 180 == 0) {
            return point;
        }
        return new Point(point.y, point.x);
    }

    private void setViewSize(int i, int i2) {
        LayoutParams layoutParams = getLayoutParams();
        if (getDisplayOrientation() % 180 != 0) {
            int i3 = i2;
            i2 = i;
            i = i3;
        }
        if (this.mShouldScaleToFill) {
            i = (float) i;
            float width = ((float) ((View) getParent()).getWidth()) / i;
            i2 = (float) i2;
            float height = ((float) ((View) getParent()).getHeight()) / i2;
            if (width <= height) {
                width = height;
            }
            i = Math.round(i * width);
            i2 = Math.round(i2 * width);
        }
        layoutParams.width = i;
        layoutParams.height = i2;
        setLayoutParams(layoutParams);
    }

    public int getDisplayOrientation() {
        int i = 0;
        if (this.mCameraWrapper == null) {
            return 0;
        }
        int i2;
        CameraInfo cameraInfo = new CameraInfo();
        if (this.mCameraWrapper.mCameraId == -1) {
            Camera.getCameraInfo(0, cameraInfo);
        } else {
            Camera.getCameraInfo(this.mCameraWrapper.mCameraId, cameraInfo);
        }
        switch (((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 0:
                break;
            case 1:
                i = 90;
                break;
            case 2:
                i = 180;
                break;
            case 3:
                i = 270;
                break;
            default:
                break;
        }
        if (cameraInfo.facing == 1) {
            i2 = (360 - ((cameraInfo.orientation + i) % 360)) % 360;
        } else {
            i2 = ((cameraInfo.orientation - i) + 360) % 360;
        }
        return i2;
    }

    private Size getOptimalPreviewSize() {
        Size size = null;
        if (this.mCameraWrapper == null) {
            return null;
        }
        List<Size> supportedPreviewSizes = r0.mCameraWrapper.mCamera.getParameters().getSupportedPreviewSizes();
        int width = getWidth();
        int height = getHeight();
        if (DisplayUtils.getScreenOrientation(getContext()) == 1) {
            int i = height;
            height = width;
            width = i;
        }
        double d = ((double) width) / ((double) height);
        if (supportedPreviewSizes == null) {
            return null;
        }
        double d2 = Double.MAX_VALUE;
        double d3 = Double.MAX_VALUE;
        for (Size size2 : supportedPreviewSizes) {
            if (Math.abs((((double) size2.width) / ((double) size2.height)) - d) <= 0.1d) {
                if (((double) Math.abs(size2.height - height)) < d3) {
                    d3 = (double) Math.abs(size2.height - height);
                    size = size2;
                }
            }
        }
        if (size == null) {
            for (Size size3 : supportedPreviewSizes) {
                if (((double) Math.abs(size3.height - height)) < d2) {
                    size = size3;
                    d2 = (double) Math.abs(size3.height - height);
                }
            }
        }
        return size;
    }

    public void setAutoFocus(boolean z) {
        if (this.mCameraWrapper != null && this.mPreviewing && z != this.mAutoFocus) {
            this.mAutoFocus = z;
            if (!this.mAutoFocus) {
                Log.v(TAG, "Cancelling autofocus");
                this.mCameraWrapper.mCamera.cancelAutoFocus();
            } else if (this.mSurfaceCreated) {
                Log.v(TAG, "Starting autofocus");
                safeAutoFocus();
            } else {
                scheduleAutoFocus();
            }
        }
    }

    private void scheduleAutoFocus() {
        this.mAutoFocusHandler.postDelayed(this.doAutoFocus, 1000);
    }
}
