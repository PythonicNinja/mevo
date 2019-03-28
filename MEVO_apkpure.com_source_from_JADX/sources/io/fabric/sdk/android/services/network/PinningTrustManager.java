package io.fabric.sdk.android.services.network;

import com.google.android.gms.common.util.AndroidUtilsLight;
import io.fabric.sdk.android.Fabric;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

class PinningTrustManager implements X509TrustManager {
    private static final X509Certificate[] NO_ISSUERS = new X509Certificate[0];
    private static final long PIN_FRESHNESS_DURATION_MILLIS = 15552000000L;
    private final Set<X509Certificate> cache = Collections.synchronizedSet(new HashSet());
    private final long pinCreationTimeMillis;
    private final List<byte[]> pins = new LinkedList();
    private final SystemKeyStore systemKeyStore;
    private final TrustManager[] systemTrustManagers;

    public PinningTrustManager(SystemKeyStore systemKeyStore, PinningInfoProvider pinningInfoProvider) {
        this.systemTrustManagers = initializeSystemTrustManagers(systemKeyStore);
        this.systemKeyStore = systemKeyStore;
        this.pinCreationTimeMillis = pinningInfoProvider.getPinCreationTimeInMillis();
        for (String hexStringToByteArray : pinningInfoProvider.getPins()) {
            this.pins.add(hexStringToByteArray(hexStringToByteArray));
        }
    }

    private TrustManager[] initializeSystemTrustManagers(SystemKeyStore systemKeyStore) {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
            instance.init(systemKeyStore.trustStore);
            return instance.getTrustManagers();
        } catch (SystemKeyStore systemKeyStore2) {
            throw new AssertionError(systemKeyStore2);
        } catch (SystemKeyStore systemKeyStore22) {
            throw new AssertionError(systemKeyStore22);
        }
    }

    private boolean isValidPin(X509Certificate x509Certificate) throws CertificateException {
        try {
            x509Certificate = MessageDigest.getInstance(AndroidUtilsLight.DIGEST_ALGORITHM_SHA1).digest(x509Certificate.getPublicKey().getEncoded());
            for (byte[] equals : this.pins) {
                if (Arrays.equals(equals, x509Certificate)) {
                    return true;
                }
            }
            return null;
        } catch (X509Certificate x509Certificate2) {
            throw new CertificateException(x509Certificate2);
        }
    }

    private void checkSystemTrust(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        for (TrustManager trustManager : this.systemTrustManagers) {
            ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
        }
    }

    private void checkPinTrust(X509Certificate[] x509CertificateArr) throws CertificateException {
        if (this.pinCreationTimeMillis == -1 || System.currentTimeMillis() - this.pinCreationTimeMillis <= PIN_FRESHNESS_DURATION_MILLIS) {
            x509CertificateArr = CertificateChainCleaner.getCleanChain(x509CertificateArr, this.systemKeyStore);
            int length = x509CertificateArr.length;
            int i = 0;
            while (i < length) {
                if (!isValidPin(x509CertificateArr[i])) {
                    i++;
                } else {
                    return;
                }
            }
            throw new CertificateException("No valid pins found in chain!");
        }
        x509CertificateArr = Fabric.getLogger();
        String str = Fabric.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Certificate pins are stale, (");
        stringBuilder.append(System.currentTimeMillis() - this.pinCreationTimeMillis);
        stringBuilder.append(" millis vs ");
        stringBuilder.append(PIN_FRESHNESS_DURATION_MILLIS);
        stringBuilder.append(" millis) falling back to system trust.");
        x509CertificateArr.mo2507w(str, stringBuilder.toString());
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (!this.cache.contains(x509CertificateArr[0])) {
            checkSystemTrust(x509CertificateArr, str);
            checkPinTrust(x509CertificateArr);
            this.cache.add(x509CertificateArr[0]);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return NO_ISSUERS;
    }

    private byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
