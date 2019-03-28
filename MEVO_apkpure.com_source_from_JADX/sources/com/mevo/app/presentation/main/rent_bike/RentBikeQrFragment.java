package com.mevo.app.presentation.main.rent_bike;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.presentation.main.rent_bike.BikeQrScannerHelper.ScannerCallback;
import com.mevo.app.tools.NetTools;
import com.mevo.app.tools.NetTools.CallsToken;
import com.mevo.app.tools.Permissions;
import com.mevo.app.tools.bike_tools.RentBikeRepository;
import java.util.Collections;

public class RentBikeQrFragment extends MainFragment implements ScannerCallback {
    private static final String[] PERMISSIONS_QR_SCANNER = new String[]{"android.permission.CAMERA"};
    private static final int REQUEST_CODE_QR_SCANNER = 8001;
    private static final String TAG = "RentBikeQrFragment";
    private boolean askedForPermission;
    private BikeQrScannerHelper bikeQrScannerHelper;
    private CallsToken callsToken;
    private ImageButton flashlightButton;
    private long lastWrongCodeMessageShowTimestamp;
    private TextView noPermissionsInfo;
    private RentBikeRepository rentBikeRepository;
    private RentBikeSheetView rentBikeSheetView;
    private MyBarcodeScanner scannerView;

    public void onLightToggled(boolean z) {
    }

    public static RentBikeQrFragment newInstance() {
        return new RentBikeQrFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_rent_bike_qr, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        getActivityInterface().setAppToolbarVisibility(true);
        this.callsToken = new CallsToken();
        this.rentBikeRepository = new RentBikeRepository(this.callsToken, true);
        this.scannerView.setFormats(Collections.singletonList(BarcodeFormat.QR_CODE));
        this.bikeQrScannerHelper = new BikeQrScannerHelper(this.scannerView);
        setupSheet();
        return layoutInflater;
    }

    public void onResume() {
        super.onResume();
        this.bikeQrScannerHelper.onResume();
        this.bikeQrScannerHelper.setScannerCallback(this);
        checkPermissions();
    }

    private void checkPermissions() {
        boolean hasPermissions = Permissions.hasPermissions(PERMISSIONS_QR_SCANNER, getActivity());
        this.noPermissionsInfo.setVisibility(hasPermissions ? 8 : 0);
        if (!hasPermissions && !this.askedForPermission) {
            this.askedForPermission = true;
            Permissions.requestIfShould(PERMISSIONS_QR_SCANNER, (int) REQUEST_CODE_QR_SCANNER, (Fragment) this);
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 8001 && Permissions.hasPermissions(PERMISSIONS_QR_SCANNER, getActivity()) != 0) {
            getActivityInterface().refreshCurrentFragment();
        }
    }

    public void onPause() {
        super.onPause();
        this.bikeQrScannerHelper.onPause();
        this.bikeQrScannerHelper.setScannerCallback(null);
    }

    public void onStop() {
        super.onStop();
        NetTools.cancelSavedOngoingCalls(this.callsToken);
        getActivityInterface().setProgressViewVisibility(false);
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.bikeQrScannerHelper != null) {
            this.bikeQrScannerHelper.destroy();
            this.bikeQrScannerHelper = null;
            this.scannerView = null;
        }
        if (getActivityInterface() != null) {
            getActivityInterface().setAppToolbarVisibility(true);
        }
    }

    private void findViews(View view) {
        this.scannerView = (MyBarcodeScanner) view.findViewById(C0434R.id.fragment_scan_qr_code_scanner_view);
        this.flashlightButton = (ImageButton) view.findViewById(C0434R.id.fragment_scan_qr_code_flashlight_button);
        this.noPermissionsInfo = (TextView) view.findViewById(C0434R.id.fragment_rent_bike_qr_no_permissions_info);
        this.rentBikeSheetView = (RentBikeSheetView) view.findViewById(C0434R.id.fragment_stations_map_renting_sheet_root);
    }

    private void setListeners() {
        this.flashlightButton.setOnClickListener(new RentBikeQrFragment$$Lambda$0(this));
        this.noPermissionsInfo.setOnClickListener(RentBikeQrFragment$$Lambda$1.$instance);
    }

    final /* synthetic */ void lambda$setListeners$218$RentBikeQrFragment(View view) {
        this.bikeQrScannerHelper.toggleFlashlight();
    }

    public void onScanResult(com.google.zxing.Result r3, boolean r4, java.lang.String r5) {
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
        r3 = 0;
        if (r5 != 0) goto L_0x0018;
    L_0x0003:
        r4 = r2.canShowWrongCodeMessage();
        if (r4 == 0) goto L_0x0017;
    L_0x0009:
        r4 = 2131689631; // 0x7f0f009f float:1.9008283E38 double:1.0531946143E-314;
        r0 = 2500; // 0x9c4 float:3.503E-42 double:1.235E-320;
        com.mevo.app.presentation.custom_views.TopToast.show(r4, r3, r0);
        r3 = java.lang.System.currentTimeMillis();
        r2.lastWrongCodeMessageShowTimestamp = r3;
    L_0x0017:
        return;
    L_0x0018:
        r4 = r2.bikeQrScannerHelper;
        if (r4 == 0) goto L_0x0022;
    L_0x001c:
        r4 = r2.bikeQrScannerHelper;
        r0 = 0;
        r4.setScannerCallback(r0);
    L_0x0022:
        r4 = new com.mevo.app.presentation.main.rent_bike.RentBikeQrFragment$$Lambda$2;	 Catch:{ Exception -> 0x002f }
        r4.<init>(r2, r5);	 Catch:{ Exception -> 0x002f }
        r4 = com.mevo.app.presentation.dialogs.QrScanResultDialog.newInstance(r5, r4);	 Catch:{ Exception -> 0x002f }
        r4.show();	 Catch:{ Exception -> 0x002f }
        goto L_0x0037;
    L_0x002f:
        r4 = 2131689630; // 0x7f0f009e float:1.900828E38 double:1.053194614E-314;
        r0 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        com.mevo.app.presentation.custom_views.TopToast.show(r4, r3, r0);
    L_0x0037:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.presentation.main.rent_bike.RentBikeQrFragment.onScanResult(com.google.zxing.Result, boolean, java.lang.String):void");
    }

    final /* synthetic */ void lambda$onScanResult$221$RentBikeQrFragment(String str, boolean z) {
        if (z) {
            if (getActivityInterface()) {
                getActivityInterface().setProgressViewVisibility(true);
            }
            this.rentBikeRepository.tryRentBike(str, new RentBikeQrFragment$$Lambda$4(this));
        } else if (this.bikeQrScannerHelper != null) {
            this.bikeQrScannerHelper.setScannerCallback(this);
        }
    }

    final /* synthetic */ void lambda$null$220$RentBikeQrFragment(boolean z) {
        if (getActivityInterface() != null) {
            getActivityInterface().setProgressViewVisibility(false);
        }
        if (!z && this.bikeQrScannerHelper) {
            this.bikeQrScannerHelper.setScannerCallback(this);
        }
    }

    private void setupSheet() {
        this.rentBikeSheetView.setListener(new RentBikeQrFragment$$Lambda$3(BottomSheetBehavior.from(this.rentBikeSheetView)));
    }

    private boolean canShowWrongCodeMessage() {
        return this.lastWrongCodeMessageShowTimestamp + TopToast.DURATION_SHORT < System.currentTimeMillis();
    }
}
