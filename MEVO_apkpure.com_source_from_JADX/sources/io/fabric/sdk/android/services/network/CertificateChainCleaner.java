package io.fabric.sdk.android.services.network;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedList;

final class CertificateChainCleaner {
    private CertificateChainCleaner() {
    }

    public static X509Certificate[] getCleanChain(X509Certificate[] x509CertificateArr, SystemKeyStore systemKeyStore) throws CertificateException {
        LinkedList linkedList = new LinkedList();
        boolean isTrustRoot = systemKeyStore.isTrustRoot(x509CertificateArr[0]);
        linkedList.add(x509CertificateArr[0]);
        int i = 1;
        boolean z = isTrustRoot;
        int i2 = 1;
        while (i2 < x509CertificateArr.length) {
            if (systemKeyStore.isTrustRoot(x509CertificateArr[i2])) {
                z = true;
            }
            if (!isValidLink(x509CertificateArr[i2], x509CertificateArr[i2 - 1])) {
                break;
            }
            linkedList.add(x509CertificateArr[i2]);
            i2++;
        }
        x509CertificateArr = systemKeyStore.getTrustRootFor(x509CertificateArr[i2 - 1]);
        if (x509CertificateArr != null) {
            linkedList.add(x509CertificateArr);
        } else {
            i = z;
        }
        if (i != 0) {
            return (X509Certificate[]) linkedList.toArray(new X509Certificate[linkedList.size()]);
        }
        throw new CertificateException("Didn't find a trust anchor in chain cleanup!");
    }

    private static boolean isValidLink(java.security.cert.X509Certificate r2, java.security.cert.X509Certificate r3) {
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
        r0 = r2.getSubjectX500Principal();
        r1 = r3.getIssuerX500Principal();
        r0 = r0.equals(r1);
        r1 = 0;
        if (r0 != 0) goto L_0x0010;
    L_0x000f:
        return r1;
    L_0x0010:
        r2 = r2.getPublicKey();	 Catch:{ GeneralSecurityException -> 0x0019 }
        r3.verify(r2);	 Catch:{ GeneralSecurityException -> 0x0019 }
        r2 = 1;
        return r2;
    L_0x0019:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.network.CertificateChainCleaner.isValidLink(java.security.cert.X509Certificate, java.security.cert.X509Certificate):boolean");
    }
}
