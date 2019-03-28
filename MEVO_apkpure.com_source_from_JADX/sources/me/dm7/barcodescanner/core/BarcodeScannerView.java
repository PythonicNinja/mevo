package me.dm7.barcodescanner.core;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public abstract class BarcodeScannerView extends FrameLayout implements PreviewCallback {
    private boolean mAutofocusState = true;
    private CameraHandlerThread mCameraHandlerThread;
    private CameraWrapper mCameraWrapper;
    private Boolean mFlashState;
    private Rect mFramingRectInPreview;
    private CameraPreview mPreview;
    private boolean mShouldScaleToFill = true;
    private IViewFinder mViewFinderView;

    public BarcodeScannerView(Context context) {
        super(context);
    }

    public BarcodeScannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        context = context.getTheme().obtainStyledAttributes(attributeSet, C0510R.styleable.BarcodeScannerView, 0, 0);
        try {
            setShouldScaleToFill(context.getBoolean(C0510R.styleable.BarcodeScannerView_shouldScaleToFill, true));
        } finally {
            context.recycle();
        }
    }

    public final void setupLayout(CameraWrapper cameraWrapper) {
        removeAllViews();
        this.mPreview = new CameraPreview(getContext(), cameraWrapper, this);
        this.mPreview.setShouldScaleToFill(this.mShouldScaleToFill);
        if (this.mShouldScaleToFill == null) {
            cameraWrapper = new RelativeLayout(getContext());
            cameraWrapper.setGravity(17);
            cameraWrapper.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            cameraWrapper.addView(this.mPreview);
            addView(cameraWrapper);
        } else {
            addView(this.mPreview);
        }
        this.mViewFinderView = createViewFinderView(getContext());
        if ((this.mViewFinderView instanceof View) != null) {
            addView((View) this.mViewFinderView);
            return;
        }
        throw new IllegalArgumentException("IViewFinder object returned by 'createViewFinderView()' should be instance of android.view.View");
    }

    protected IViewFinder createViewFinderView(Context context) {
        return new ViewFinderView(context);
    }

    public void startCamera(int i) {
        if (this.mCameraHandlerThread == null) {
            this.mCameraHandlerThread = new CameraHandlerThread(this);
        }
        this.mCameraHandlerThread.startCamera(i);
    }

    public void setupCameraPreview(CameraWrapper cameraWrapper) {
        this.mCameraWrapper = cameraWrapper;
        if (this.mCameraWrapper != null) {
            setupLayout(this.mCameraWrapper);
            this.mViewFinderView.setupViewFinder();
            if (this.mFlashState != null) {
                setFlash(this.mFlashState.booleanValue());
            }
            setAutoFocus(this.mAutofocusState);
        }
    }

    public void startCamera() {
        startCamera(CameraUtils.getDefaultCameraId());
    }

    public void stopCamera() {
        if (this.mCameraWrapper != null) {
            this.mPreview.stopCameraPreview();
            this.mPreview.setCamera(null, null);
            this.mCameraWrapper.mCamera.release();
            this.mCameraWrapper = null;
        }
        if (this.mCameraHandlerThread != null) {
            this.mCameraHandlerThread.quit();
            this.mCameraHandlerThread = null;
        }
    }

    public void stopCameraPreview() {
        if (this.mPreview != null) {
            this.mPreview.stopCameraPreview();
        }
    }

    protected void resumeCameraPreview() {
        if (this.mPreview != null) {
            this.mPreview.showCameraPreview();
        }
    }

    public synchronized Rect getFramingRectInPreview(int i, int i2) {
        if (this.mFramingRectInPreview == null) {
            Rect framingRect = this.mViewFinderView.getFramingRect();
            int width = this.mViewFinderView.getWidth();
            int height = this.mViewFinderView.getHeight();
            if (!(framingRect == null || width == 0)) {
                if (height != 0) {
                    Rect rect = new Rect(framingRect);
                    if (i < width) {
                        rect.left = (rect.left * i) / width;
                        rect.right = (rect.right * i) / width;
                    }
                    if (i2 < height) {
                        rect.top = (rect.top * i2) / height;
                        rect.bottom = (rect.bottom * i2) / height;
                    }
                    this.mFramingRectInPreview = rect;
                }
            }
            return 0;
        }
        return this.mFramingRectInPreview;
    }

    public void setFlash(boolean z) {
        this.mFlashState = Boolean.valueOf(z);
        if (this.mCameraWrapper != null && CameraUtils.isFlashSupported(this.mCameraWrapper.mCamera)) {
            Parameters parameters = this.mCameraWrapper.mCamera.getParameters();
            if (z) {
                if (!parameters.getFlashMode().equals("torch")) {
                    parameters.setFlashMode("torch");
                } else {
                    return;
                }
            } else if (!parameters.getFlashMode().equals("off")) {
                parameters.setFlashMode("off");
            } else {
                return;
            }
            this.mCameraWrapper.mCamera.setParameters(parameters);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getFlash() {
        /*
        r3 = this;
        r0 = r3.mCameraWrapper;
        r1 = 0;
        if (r0 == 0) goto L_0x0026;
    L_0x0005:
        r0 = r3.mCameraWrapper;
        r0 = r0.mCamera;
        r0 = me.dm7.barcodescanner.core.CameraUtils.isFlashSupported(r0);
        if (r0 == 0) goto L_0x0026;
    L_0x000f:
        r0 = r3.mCameraWrapper;
        r0 = r0.mCamera;
        r0 = r0.getParameters();
        r0 = r0.getFlashMode();
        r2 = "torch";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0025;
    L_0x0023:
        r0 = 1;
        return r0;
    L_0x0025:
        return r1;
    L_0x0026:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: me.dm7.barcodescanner.core.BarcodeScannerView.getFlash():boolean");
    }

    public void toggleFlash() {
        if (this.mCameraWrapper != null && CameraUtils.isFlashSupported(this.mCameraWrapper.mCamera)) {
            Parameters parameters = this.mCameraWrapper.mCamera.getParameters();
            if (parameters.getFlashMode().equals("torch")) {
                parameters.setFlashMode("off");
            } else {
                parameters.setFlashMode("torch");
            }
            this.mCameraWrapper.mCamera.setParameters(parameters);
        }
    }

    public void setAutoFocus(boolean z) {
        this.mAutofocusState = z;
        if (this.mPreview != null) {
            this.mPreview.setAutoFocus(z);
        }
    }

    public void setShouldScaleToFill(boolean z) {
        this.mShouldScaleToFill = z;
    }
}
