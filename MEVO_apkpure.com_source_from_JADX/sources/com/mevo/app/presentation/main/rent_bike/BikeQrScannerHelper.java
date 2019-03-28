package com.mevo.app.presentation.main.rent_bike;

import android.support.annotation.NonNull;
import com.google.zxing.Result;
import com.mevo.app.tools.Validator;
import me.dm7.barcodescanner.zxing.ZXingScannerView.ResultHandler;

public class BikeQrScannerHelper implements ResultHandler {
    private ScannerCallback callback;
    private MyBarcodeScanner scannerView;

    public interface ScannerCallback {
        void onLightToggled(boolean z);

        void onScanResult(Result result, boolean z, String str);
    }

    public BikeQrScannerHelper(@NonNull MyBarcodeScanner myBarcodeScanner) {
        this.scannerView = myBarcodeScanner;
        this.scannerView.setResultHandler(this);
    }

    public void setScannerCallback(ScannerCallback scannerCallback) {
        this.callback = scannerCallback;
    }

    public void onResume() {
        setCameraState(true);
        this.scannerView.setResultHandler(this);
    }

    public void onPause() {
        this.scannerView.setResultHandler(null);
        setCameraState(false);
        if (this.scannerView.getFlash()) {
            this.scannerView.toggleFlash();
            if (this.callback != null) {
                this.callback.onLightToggled(false);
            }
        }
    }

    public void setCameraState(boolean z) {
        if (z) {
            this.scannerView.startCamera();
        } else {
            this.scannerView.stopCamera();
        }
    }

    public void destroy() {
        this.callback = null;
        this.scannerView.setResultHandler(null);
        this.scannerView = null;
    }

    public boolean toggleFlashlight() {
        this.scannerView.toggleFlash();
        boolean flash = this.scannerView.getFlash();
        if (this.callback != null) {
            this.callback.onLightToggled(flash);
        }
        return flash;
    }

    public void handleResult(Result result) {
        this.scannerView.blockAnalysisForAMoment();
        this.scannerView.resumeCameraPreview(this);
        String text = result.getText();
        String str = null;
        if (text.matches("http://nxtb.it/(b/){0,1}\\d{5}")) {
            text = text.replaceFirst("http://nxtb.it/(b/){0,1}", "");
            if (Validator.isBikeNumberValid(text)) {
                str = text;
            }
        }
        if (this.callback != null) {
            this.callback.onScanResult(result, str != null, str);
        }
    }
}
