package com.mevo.app.modules.nfc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.ReaderCallback;
import android.nfc.Tag;
import com.inverce.mod.core.IM;
import com.mevo.app.tools.Log;
import java.lang.ref.WeakReference;

public class LoyaltyCardReader implements ReaderCallback {
    public static int READER_FLAGS = 129;
    private static final String TAG = "LoyaltyCardReader";
    boolean isScannerEnabled = false;
    private WeakReference<AccountCallback> mAccountCallback;

    public interface AccountCallback {
        void onAccountReceived(String str);
    }

    public LoyaltyCardReader(AccountCallback accountCallback) {
        this.mAccountCallback = new WeakReference(accountCallback);
    }

    @SuppressLint({"MissingPermission"})
    public void onTagDiscovered(Tag tag) {
        Log.m94i(TAG, "New tag discovered");
        long[] jArr = new long[]{(long) (tag.getId()[0] & 255), (long) (tag.getId()[1] & 255), (long) (tag.getId()[2] & 255), (long) (tag.getId()[3] & 255)};
        long j = (((jArr[3] << 24) | (jArr[2] << 16)) | (jArr[1] << 8)) | jArr[0];
        if (this.mAccountCallback != null && this.mAccountCallback.get() != null) {
            ((AccountCallback) this.mAccountCallback.get()).onAccountReceived(String.valueOf(j));
        }
    }

    public synchronized void enableReaderMode() {
        if (this.isScannerEnabled) {
            disableReaderMode();
        }
        Log.m94i(TAG, "Enabling reader mode");
        Context activity = IM.activity();
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(activity);
        if (defaultAdapter != null) {
            defaultAdapter.enableReaderMode(activity, this, READER_FLAGS, null);
        }
        this.isScannerEnabled = true;
    }

    public synchronized void disableReaderMode() {
        if (this.isScannerEnabled) {
            Log.m94i(TAG, "Disabling reader mode");
            Context activity = IM.activity();
            NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(activity);
            if (defaultAdapter != null) {
                defaultAdapter.disableReaderMode(activity);
            }
            this.isScannerEnabled = false;
        }
    }

    public static boolean canReadMiFareTag() {
        return HCEManager.get().info().isNfcSupported() && HCEManager.get().info().isNfcHceSupported() && HCEManager.get().info().isMifareClassicSupported();
    }
}
