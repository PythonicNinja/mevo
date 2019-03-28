package com.inverce.mod.integrations.bugfixes.okhttp;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.google.android.gms.security.ProviderInstaller;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Log;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient.Builder;

public class TLS12OkHttpSupport {

    public static class Tls12SocketFactory extends SSLSocketFactory {
        private static final String[] TLS_V12_ONLY = new String[]{"TLSv1.2"};
        final SSLSocketFactory delegate;

        public Tls12SocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.delegate = sSLSocketFactory;
        }

        public String[] getDefaultCipherSuites() {
            return this.delegate.getDefaultCipherSuites();
        }

        public String[] getSupportedCipherSuites() {
            return this.delegate.getSupportedCipherSuites();
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return patch(this.delegate.createSocket(socket, str, i, z));
        }

        public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
            return patch(this.delegate.createSocket(str, i));
        }

        public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
            return patch(this.delegate.createSocket(str, i, inetAddress, i2));
        }

        public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            return patch(this.delegate.createSocket(inetAddress, i));
        }

        public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
            return patch(this.delegate.createSocket(inetAddress, i, inetAddress2, i2));
        }

        private Socket patch(Socket socket) {
            if (socket instanceof SSLSocket) {
                ((SSLSocket) socket).setEnabledProtocols(TLS_V12_ONLY);
            }
            return socket;
        }
    }

    @NonNull
    public static Builder enableTls12OnPreLollipop(@NonNull Builder builder) {
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT < 22) {
            try {
                ProviderInstaller.installIfNeeded(IM.context());
                SSLContext instance = SSLContext.getInstance("TLSv1.2");
                instance.init(null, null, null);
                for (String e : instance.getSupportedSSLParameters().getProtocols()) {
                    Log.m45e("Context supported", e);
                }
                if (getTrustManager() != null) {
                    builder.sslSocketFactory(new Tls12SocketFactory(instance.getSocketFactory()), getTrustManager());
                }
            } catch (Exception e2) {
                Log.m46e("OkHttpTLSCompat", "Error while setting TLS 1.2", e2);
            }
        }
        return builder;
    }

    private static X509TrustManager getTrustManager() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore) null);
            TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1) {
                if (trustManagers[0] instanceof X509TrustManager) {
                    return (X509TrustManager) trustManagers[0];
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected default trust managers:");
            stringBuilder.append(Arrays.toString(trustManagers));
            Log.m45e("tls", stringBuilder.toString());
            return null;
        } catch (NoSuchAlgorithmException e) {
            Log.m45e("tls", e.toString());
            e.printStackTrace();
            return null;
        } catch (KeyStoreException e2) {
            Log.m45e("tls", e2.toString());
            e2.printStackTrace();
            return null;
        }
    }
}
