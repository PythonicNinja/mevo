package com.mevo.app.modules.nfc;

import android.app.Activity;
import android.content.pm.FeatureInfo;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import com.annimon.stream.function.Function;
import com.inverce.mod.core.IM;
import com.mevo.app.tools.Permissions;

public class HCEManager {
    private static HCEManager manager = new HCEManager();
    private NfcInfo info = new NfcInfo();
    SharedConfigValue settings = new SharedConfigValue("nfc_preferences", new NfcConfiguration());

    public class NfcInfo {
        public boolean isNfcSupported() {
            return NfcAdapter.getDefaultAdapter(IM.context()) != null;
        }

        public boolean isNfcEnabled() {
            NfcManager nfcManager = (NfcManager) IM.context().getSystemService("nfc");
            boolean z = false;
            if (nfcManager == null) {
                return false;
            }
            NfcAdapter defaultAdapter = nfcManager.getDefaultAdapter();
            if (defaultAdapter != null && defaultAdapter.isEnabled()) {
                z = true;
            }
            return z;
        }

        public boolean isNfcHceSupported() {
            boolean z = false;
            if (VERSION.SDK_INT < 19) {
                return false;
            }
            if (isNfcSupported() && IM.context().getPackageManager().hasSystemFeature("android.hardware.nfc.hce")) {
                z = true;
            }
            return z;
        }

        public boolean isMifareClassicSupported() {
            if (IM.activity() == null) {
                return false;
            }
            for (FeatureInfo featureInfo : IM.activity().getPackageManager().getSystemAvailableFeatures()) {
                String str = featureInfo.name;
                if (str != null && str.equals("com.nxp.mifare")) {
                    return true;
                }
            }
            return false;
        }

        public boolean isNfcPermissionGranted() {
            return ContextCompat.checkSelfPermission(IM.context(), "android.permission.NFC") == 0;
        }

        public void askPermissions(Activity activity) {
            Permissions.requestIfShould(new String[]{"android.permission.NFC"}, Math.abs("android.permission.NFC".hashCode()), activity);
        }
    }

    public static HCEManager get() {
        return manager;
    }

    public void enable(boolean z) {
        changeConfiguration(new HCEManager$$Lambda$0(z));
    }

    public void changeConfiguration(Function<NfcConfiguration, NfcConfiguration> function) {
        this.settings.set((NfcConfiguration) function.apply((NfcConfiguration) this.settings.get()));
    }

    public NfcInfo info() {
        return this.info;
    }
}
