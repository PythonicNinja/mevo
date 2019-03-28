package com.mevo.app.modules.nfc;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import com.mevo.app.tools.Log;
import java.util.Arrays;

@RequiresApi(api = 19)
public class EmulationCardService extends HostApduService {
    private static final String APPLICATION_ID = "F222222222";
    private static final String SELECT_APDU_HEADER = "00A40400";
    private static final byte[] SELECT_OK_SW = MathEx.HexStringToByteArray("9000");
    private static final byte[] UNKNOWN_CMD_SW = MathEx.HexStringToByteArray("0000");

    public void onDeactivated(int i) {
    }

    public static byte[] BuildSelectApdu(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SELECT_APDU_HEADER);
        stringBuilder.append(String.format("%02X", new Object[]{Integer.valueOf(str.length() / 2)}));
        stringBuilder.append(str);
        return MathEx.HexStringToByteArray(stringBuilder.toString());
    }

    public byte[] processCommandApdu(byte[] bArr, Bundle bundle) {
        bundle = new StringBuilder();
        bundle.append("Received APDU: ");
        bundle.append(MathEx.ByteArrayToHexString(bArr));
        Log.m93i(bundle.toString());
        NfcConfiguration nfcConfiguration = (NfcConfiguration) HCEManager.get().settings.get();
        if (nfcConfiguration != null) {
            if (nfcConfiguration.isEnabled()) {
                if (Arrays.equals(BuildSelectApdu(APPLICATION_ID), bArr) == null) {
                    return UNKNOWN_CMD_SW;
                }
                if (nfcConfiguration.getCardId() != null) {
                    bArr = nfcConfiguration.getCardId();
                    bundle = bArr.getBytes();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Sending account number: ");
                    stringBuilder.append(bArr);
                    Log.m93i(stringBuilder.toString());
                    return MathEx.ConcatArrays(bundle, SELECT_OK_SW);
                }
                Log.m90e("EmulationCard", "Card Id is null");
                return UNKNOWN_CMD_SW;
            }
        }
        return UNKNOWN_CMD_SW;
    }
}
