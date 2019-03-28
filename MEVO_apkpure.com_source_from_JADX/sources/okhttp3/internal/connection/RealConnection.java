package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownServiceException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http1.Http1Codec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Codec;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Connection.Builder;
import okhttp3.internal.http2.Http2Connection.Listener;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket.Streams;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public final class RealConnection extends Listener implements Connection {
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    public int allocationLimit = 1;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    private final ConnectionPool connectionPool;
    private Handshake handshake;
    private Http2Connection http2Connection;
    public long idleAtNanos = Long.MAX_VALUE;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    public int successCount;

    public RealConnection(ConnectionPool connectionPool, Route route) {
        this.connectionPool = connectionPool;
        this.route = route;
    }

    public static RealConnection testConnection(ConnectionPool connectionPool, Route route, Socket socket, long j) {
        RealConnection realConnection = new RealConnection(connectionPool, route);
        realConnection.socket = socket;
        realConnection.idleAtNanos = j;
        return realConnection;
    }

    public void connect(int i, int i2, int i3, int i4, boolean z, Call call, EventListener eventListener) {
        IOException iOException;
        int i5;
        int i6;
        IOException e;
        int i7;
        IOException iOException2;
        IOException iOException3;
        Call call2 = call;
        EventListener eventListener2 = eventListener;
        if (this.protocol != null) {
            throw new IllegalStateException("already connected");
        }
        List connectionSpecs = r7.route.address().connectionSpecs();
        ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(connectionSpecs);
        if (r7.route.address().sslSocketFactory() == null) {
            if (connectionSpecs.contains(ConnectionSpec.CLEARTEXT)) {
                String host = r7.route.address().url().host();
                if (!Platform.get().isCleartextTrafficPermitted(host)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("CLEARTEXT communication to ");
                    stringBuilder.append(host);
                    stringBuilder.append(" not permitted by network security policy");
                    throw new RouteException(new UnknownServiceException(stringBuilder.toString()));
                }
            }
            throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
        }
        RouteException routeException = null;
        do {
            try {
                if (r7.route.requiresTunnel()) {
                    connectTunnel(i, i2, i3, call2, eventListener2);
                    if (r7.rawSocket != null) {
                        i5 = i;
                        i6 = i2;
                    } else if (!r7.route.requiresTunnel() && r7.rawSocket == null) {
                        throw new RouteException(new ProtocolException("Too many tunnel connections attempted: 21"));
                    } else if (r7.http2Connection != null) {
                        synchronized (r7.connectionPool) {
                            try {
                                r7.allocationLimit = r7.http2Connection.maxConcurrentStreams();
                            } catch (Throwable th) {
                                Throwable th2 = th;
                            }
                        }
                        return;
                    } else {
                        return;
                    }
                }
                try {
                    connectSocket(i, i2, call2, eventListener2);
                } catch (IOException e2) {
                    e = e2;
                    i7 = i4;
                    iOException2 = e;
                    Util.closeQuietly(r7.socket);
                    Util.closeQuietly(r7.rawSocket);
                    r7.socket = null;
                    r7.rawSocket = null;
                    r7.source = null;
                    r7.sink = null;
                    r7.handshake = null;
                    r7.protocol = null;
                    r7.http2Connection = null;
                    iOException3 = iOException2;
                    eventListener2.connectFailed(call2, r7.route.socketAddress(), r7.route.proxy(), null, iOException2);
                    if (routeException != null) {
                        iOException = iOException3;
                        routeException.addConnectException(iOException);
                    } else {
                        iOException = iOException3;
                        routeException = new RouteException(iOException);
                    }
                    if (z) {
                        break;
                    } else if (!connectionSpecSelector.connectionFailed(iOException)) {
                    }
                    throw routeException;
                }
                try {
                    establishProtocol(connectionSpecSelector, i4, call2, eventListener2);
                    eventListener2.connectEnd(call2, r7.route.socketAddress(), r7.route.proxy(), r7.protocol);
                    if (!r7.route.requiresTunnel()) {
                    }
                    if (r7.http2Connection != null) {
                        synchronized (r7.connectionPool) {
                            r7.allocationLimit = r7.http2Connection.maxConcurrentStreams();
                        }
                        return;
                    }
                    return;
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                e = e4;
                i5 = i;
                i6 = i2;
                i7 = i4;
                iOException2 = e;
                Util.closeQuietly(r7.socket);
                Util.closeQuietly(r7.rawSocket);
                r7.socket = null;
                r7.rawSocket = null;
                r7.source = null;
                r7.sink = null;
                r7.handshake = null;
                r7.protocol = null;
                r7.http2Connection = null;
                iOException3 = iOException2;
                eventListener2.connectFailed(call2, r7.route.socketAddress(), r7.route.proxy(), null, iOException2);
                if (routeException != null) {
                    iOException = iOException3;
                    routeException = new RouteException(iOException);
                } else {
                    iOException = iOException3;
                    routeException.addConnectException(iOException);
                }
                if (z) {
                    break;
                } else if (connectionSpecSelector.connectionFailed(iOException)) {
                }
                throw routeException;
            }
        } while (connectionSpecSelector.connectionFailed(iOException));
        throw routeException;
    }

    private void connectTunnel(int i, int i2, int i3, Call call, EventListener eventListener) throws IOException {
        Request createTunnelRequest = createTunnelRequest();
        HttpUrl url = createTunnelRequest.url();
        int i4 = 0;
        while (i4 < 21) {
            connectSocket(i, i2, call, eventListener);
            createTunnelRequest = createTunnel(i2, i3, createTunnelRequest, url);
            if (createTunnelRequest != null) {
                Util.closeQuietly(this.rawSocket);
                this.rawSocket = null;
                this.sink = null;
                this.source = null;
                eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), null);
                i4++;
            } else {
                return;
            }
        }
    }

    private void connectSocket(int i, int i2, Call call, EventListener eventListener) throws IOException {
        Socket socket;
        Proxy proxy = this.route.proxy();
        Address address = this.route.address();
        if (proxy.type() != Type.DIRECT) {
            if (proxy.type() != Type.HTTP) {
                socket = new Socket(proxy);
                this.rawSocket = socket;
                eventListener.connectStart(call, this.route.socketAddress(), proxy);
                this.rawSocket.setSoTimeout(i2);
                Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), i);
                this.source = Okio.buffer(Okio.source(this.rawSocket));
                this.sink = Okio.buffer(Okio.sink(this.rawSocket));
            }
        }
        socket = address.socketFactory().createSocket();
        this.rawSocket = socket;
        eventListener.connectStart(call, this.route.socketAddress(), proxy);
        this.rawSocket.setSoTimeout(i2);
        try {
            Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), i);
            try {
                this.source = Okio.buffer(Okio.source(this.rawSocket));
                this.sink = Okio.buffer(Okio.sink(this.rawSocket));
            } catch (int i3) {
                if (NPE_THROW_WITH_NULL.equals(i3.getMessage()) != 0) {
                    throw new IOException(i3);
                }
            }
        } catch (int i32) {
            call = new StringBuilder();
            call.append("Failed to connect to ");
            call.append(this.route.socketAddress());
            i2 = new ConnectException(call.toString());
            i2.initCause(i32);
            throw i2;
        }
    }

    private void establishProtocol(ConnectionSpecSelector connectionSpecSelector, int i, Call call, EventListener eventListener) throws IOException {
        if (this.route.address().sslSocketFactory() == null) {
            this.protocol = Protocol.HTTP_1_1;
            this.socket = this.rawSocket;
            return;
        }
        eventListener.secureConnectStart(call);
        connectTls(connectionSpecSelector);
        eventListener.secureConnectEnd(call, this.handshake);
        if (this.protocol == Protocol.HTTP_2) {
            this.socket.setSoTimeout(null);
            this.http2Connection = new Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).listener(this).pingIntervalMillis(i).build();
            this.http2Connection.start();
        }
    }

    private void connectTls(ConnectionSpecSelector connectionSpecSelector) throws IOException {
        Address address = this.route.address();
        String str = null;
        try {
            Socket socket = (SSLSocket) address.sslSocketFactory().createSocket(this.rawSocket, address.url().host(), address.url().port(), true);
            try {
                connectionSpecSelector = connectionSpecSelector.configureSecureSocket(socket);
                if (connectionSpecSelector.supportsTlsExtensions()) {
                    Platform.get().configureTlsExtensions(socket, address.url().host(), address.protocols());
                }
                socket.startHandshake();
                SSLSession session = socket.getSession();
                if (isValid(session)) {
                    Handshake handshake = Handshake.get(session);
                    if (address.hostnameVerifier().verify(address.url().host(), session)) {
                        address.certificatePinner().check(address.url().host(), handshake.peerCertificates());
                        if (connectionSpecSelector.supportsTlsExtensions() != null) {
                            str = Platform.get().getSelectedProtocol(socket);
                        }
                        this.socket = socket;
                        this.source = Okio.buffer(Okio.source(this.socket));
                        this.sink = Okio.buffer(Okio.sink(this.socket));
                        this.handshake = handshake;
                        if (str != null) {
                            connectionSpecSelector = Protocol.get(str);
                        } else {
                            connectionSpecSelector = Protocol.HTTP_1_1;
                        }
                        this.protocol = connectionSpecSelector;
                        if (socket != null) {
                            Platform.get().afterHandshake(socket);
                            return;
                        }
                        return;
                    }
                    X509Certificate x509Certificate = (X509Certificate) handshake.peerCertificates().get(0);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Hostname ");
                    stringBuilder.append(address.url().host());
                    stringBuilder.append(" not verified:\n    certificate: ");
                    stringBuilder.append(CertificatePinner.pin(x509Certificate));
                    stringBuilder.append("\n    DN: ");
                    stringBuilder.append(x509Certificate.getSubjectDN().getName());
                    stringBuilder.append("\n    subjectAltNames: ");
                    stringBuilder.append(OkHostnameVerifier.allSubjectAltNames(x509Certificate));
                    throw new SSLPeerUnverifiedException(stringBuilder.toString());
                }
                throw new IOException("a valid ssl session was not established");
            } catch (AssertionError e) {
                connectionSpecSelector = e;
                str = socket;
                try {
                    if (Util.isAndroidGetsocknameError(connectionSpecSelector)) {
                        throw connectionSpecSelector;
                    }
                    throw new IOException(connectionSpecSelector);
                } catch (Throwable th) {
                    connectionSpecSelector = th;
                    socket = str;
                    if (socket != null) {
                        Platform.get().afterHandshake(socket);
                    }
                    Util.closeQuietly(socket);
                    throw connectionSpecSelector;
                }
            } catch (Throwable th2) {
                connectionSpecSelector = th2;
                if (socket != null) {
                    Platform.get().afterHandshake(socket);
                }
                Util.closeQuietly(socket);
                throw connectionSpecSelector;
            }
        } catch (AssertionError e2) {
            connectionSpecSelector = e2;
            if (Util.isAndroidGetsocknameError(connectionSpecSelector)) {
                throw connectionSpecSelector;
            }
            throw new IOException(connectionSpecSelector);
        }
    }

    private boolean isValid(SSLSession sSLSession) {
        return ("NONE".equals(sSLSession.getProtocol()) || "SSL_NULL_WITH_NULL_NULL".equals(sSLSession.getCipherSuite()) != null) ? null : true;
    }

    private Request createTunnel(int i, int i2, Request request, HttpUrl httpUrl) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CONNECT ");
        stringBuilder.append(Util.hostHeader(httpUrl, true));
        stringBuilder.append(" HTTP/1.1");
        httpUrl = stringBuilder.toString();
        while (true) {
            Http1Codec http1Codec = new Http1Codec(null, null, this.source, this.sink);
            this.source.timeout().timeout((long) i, TimeUnit.MILLISECONDS);
            this.sink.timeout().timeout((long) i2, TimeUnit.MILLISECONDS);
            http1Codec.writeRequest(request.headers(), httpUrl);
            http1Codec.finishRequest();
            Response build = http1Codec.readResponseHeaders(false).request(request).build();
            long contentLength = HttpHeaders.contentLength(build);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source newFixedLengthSource = http1Codec.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            newFixedLengthSource.close();
            int code = build.code();
            if (code == 200) {
                break;
            } else if (code != 407) {
                i2 = new StringBuilder();
                i2.append("Unexpected response code for CONNECT: ");
                i2.append(build.code());
                throw new IOException(i2.toString());
            } else {
                Request authenticate = this.route.address().proxyAuthenticator().authenticate(this.route, build);
                if (authenticate == null) {
                    throw new IOException("Failed to authenticate with proxy");
                } else if ("close".equalsIgnoreCase(build.header("Connection")) != null) {
                    return authenticate;
                } else {
                    request = authenticate;
                }
            }
        }
        if (this.source.buffer().exhausted() != 0) {
            if (this.sink.buffer().exhausted() != 0) {
                return null;
            }
        }
        throw new IOException("TLS tunnel buffered too many bytes!");
    }

    private Request createTunnelRequest() {
        return new Request.Builder().url(this.route.address().url()).header("Host", Util.hostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
    }

    public boolean isEligible(okhttp3.Address r5, @javax.annotation.Nullable okhttp3.Route r6) {
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
        r4 = this;
        r0 = r4.allocations;
        r0 = r0.size();
        r1 = r4.allocationLimit;
        r2 = 0;
        if (r0 >= r1) goto L_0x00a6;
    L_0x000b:
        r0 = r4.noNewStreams;
        if (r0 == 0) goto L_0x0011;
    L_0x000f:
        goto L_0x00a6;
    L_0x0011:
        r0 = okhttp3.internal.Internal.instance;
        r1 = r4.route;
        r1 = r1.address();
        r0 = r0.equalsNonHost(r1, r5);
        if (r0 != 0) goto L_0x0020;
    L_0x001f:
        return r2;
    L_0x0020:
        r0 = r5.url();
        r0 = r0.host();
        r1 = r4.route();
        r1 = r1.address();
        r1 = r1.url();
        r1 = r1.host();
        r0 = r0.equals(r1);
        r1 = 1;
        if (r0 == 0) goto L_0x0040;
    L_0x003f:
        return r1;
    L_0x0040:
        r0 = r4.http2Connection;
        if (r0 != 0) goto L_0x0045;
    L_0x0044:
        return r2;
    L_0x0045:
        if (r6 != 0) goto L_0x0048;
    L_0x0047:
        return r2;
    L_0x0048:
        r0 = r6.proxy();
        r0 = r0.type();
        r3 = java.net.Proxy.Type.DIRECT;
        if (r0 == r3) goto L_0x0055;
    L_0x0054:
        return r2;
    L_0x0055:
        r0 = r4.route;
        r0 = r0.proxy();
        r0 = r0.type();
        r3 = java.net.Proxy.Type.DIRECT;
        if (r0 == r3) goto L_0x0064;
    L_0x0063:
        return r2;
    L_0x0064:
        r0 = r4.route;
        r0 = r0.socketAddress();
        r3 = r6.socketAddress();
        r0 = r0.equals(r3);
        if (r0 != 0) goto L_0x0075;
    L_0x0074:
        return r2;
    L_0x0075:
        r6 = r6.address();
        r6 = r6.hostnameVerifier();
        r0 = okhttp3.internal.tls.OkHostnameVerifier.INSTANCE;
        if (r6 == r0) goto L_0x0082;
    L_0x0081:
        return r2;
    L_0x0082:
        r6 = r5.url();
        r6 = r4.supportsUrl(r6);
        if (r6 != 0) goto L_0x008d;
    L_0x008c:
        return r2;
    L_0x008d:
        r6 = r5.certificatePinner();	 Catch:{ SSLPeerUnverifiedException -> 0x00a5 }
        r5 = r5.url();	 Catch:{ SSLPeerUnverifiedException -> 0x00a5 }
        r5 = r5.host();	 Catch:{ SSLPeerUnverifiedException -> 0x00a5 }
        r0 = r4.handshake();	 Catch:{ SSLPeerUnverifiedException -> 0x00a5 }
        r0 = r0.peerCertificates();	 Catch:{ SSLPeerUnverifiedException -> 0x00a5 }
        r6.check(r5, r0);	 Catch:{ SSLPeerUnverifiedException -> 0x00a5 }
        return r1;
    L_0x00a5:
        return r2;
    L_0x00a6:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.isEligible(okhttp3.Address, okhttp3.Route):boolean");
    }

    public boolean supportsUrl(HttpUrl httpUrl) {
        if (httpUrl.port() != this.route.address().url().port()) {
            return false;
        }
        boolean z = true;
        if (httpUrl.host().equals(this.route.address().url().host())) {
            return true;
        }
        if (this.handshake == null || OkHostnameVerifier.INSTANCE.verify(httpUrl.host(), (X509Certificate) this.handshake.peerCertificates().get(0)) == null) {
            z = false;
        }
        return z;
    }

    public HttpCodec newCodec(OkHttpClient okHttpClient, Chain chain, StreamAllocation streamAllocation) throws SocketException {
        if (this.http2Connection != null) {
            return new Http2Codec(okHttpClient, chain, streamAllocation, this.http2Connection);
        }
        this.socket.setSoTimeout(chain.readTimeoutMillis());
        this.source.timeout().timeout((long) chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.sink.timeout().timeout((long) chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        return new Http1Codec(okHttpClient, streamAllocation, this.source, this.sink);
    }

    public Streams newWebSocketStreams(StreamAllocation streamAllocation) {
        final StreamAllocation streamAllocation2 = streamAllocation;
        return new Streams(true, this.source, this.sink) {
            public void close() throws IOException {
                streamAllocation2.streamFinished(true, streamAllocation2.codec(), -1, null);
            }
        };
    }

    public Route route() {
        return this.route;
    }

    public void cancel() {
        Util.closeQuietly(this.rawSocket);
    }

    public Socket socket() {
        return this.socket;
    }

    public boolean isHealthy(boolean r5) {
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
        r4 = this;
        r0 = r4.socket;
        r0 = r0.isClosed();
        r1 = 0;
        if (r0 != 0) goto L_0x0051;
    L_0x0009:
        r0 = r4.socket;
        r0 = r0.isInputShutdown();
        if (r0 != 0) goto L_0x0051;
    L_0x0011:
        r0 = r4.socket;
        r0 = r0.isOutputShutdown();
        if (r0 == 0) goto L_0x001a;
    L_0x0019:
        goto L_0x0051;
    L_0x001a:
        r0 = r4.http2Connection;
        r2 = 1;
        if (r0 == 0) goto L_0x0027;
    L_0x001f:
        r5 = r4.http2Connection;
        r5 = r5.isShutdown();
        r5 = r5 ^ r2;
        return r5;
    L_0x0027:
        if (r5 == 0) goto L_0x0050;
    L_0x0029:
        r5 = r4.socket;	 Catch:{ SocketTimeoutException -> 0x0050, IOException -> 0x004f }
        r5 = r5.getSoTimeout();	 Catch:{ SocketTimeoutException -> 0x0050, IOException -> 0x004f }
        r0 = r4.socket;	 Catch:{ all -> 0x0048 }
        r0.setSoTimeout(r2);	 Catch:{ all -> 0x0048 }
        r0 = r4.source;	 Catch:{ all -> 0x0048 }
        r0 = r0.exhausted();	 Catch:{ all -> 0x0048 }
        if (r0 == 0) goto L_0x0042;
    L_0x003c:
        r0 = r4.socket;	 Catch:{ SocketTimeoutException -> 0x0050, IOException -> 0x004f }
        r0.setSoTimeout(r5);	 Catch:{ SocketTimeoutException -> 0x0050, IOException -> 0x004f }
        return r1;	 Catch:{ SocketTimeoutException -> 0x0050, IOException -> 0x004f }
    L_0x0042:
        r0 = r4.socket;	 Catch:{ SocketTimeoutException -> 0x0050, IOException -> 0x004f }
        r0.setSoTimeout(r5);	 Catch:{ SocketTimeoutException -> 0x0050, IOException -> 0x004f }
        return r2;	 Catch:{ SocketTimeoutException -> 0x0050, IOException -> 0x004f }
    L_0x0048:
        r0 = move-exception;	 Catch:{ SocketTimeoutException -> 0x0050, IOException -> 0x004f }
        r3 = r4.socket;	 Catch:{ SocketTimeoutException -> 0x0050, IOException -> 0x004f }
        r3.setSoTimeout(r5);	 Catch:{ SocketTimeoutException -> 0x0050, IOException -> 0x004f }
        throw r0;	 Catch:{ SocketTimeoutException -> 0x0050, IOException -> 0x004f }
    L_0x004f:
        return r1;
    L_0x0050:
        return r2;
    L_0x0051:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.isHealthy(boolean):boolean");
    }

    public void onStream(Http2Stream http2Stream) throws IOException {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }

    public void onSettings(Http2Connection http2Connection) {
        synchronized (this.connectionPool) {
            this.allocationLimit = http2Connection.maxConcurrentStreams();
        }
    }

    public Handshake handshake() {
        return this.handshake;
    }

    public boolean isMultiplexed() {
        return this.http2Connection != null;
    }

    public Protocol protocol() {
        return this.protocol;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Connection{");
        stringBuilder.append(this.route.address().url().host());
        stringBuilder.append(":");
        stringBuilder.append(this.route.address().url().port());
        stringBuilder.append(", proxy=");
        stringBuilder.append(this.route.proxy());
        stringBuilder.append(" hostAddress=");
        stringBuilder.append(this.route.socketAddress());
        stringBuilder.append(" cipherSuite=");
        stringBuilder.append(this.handshake != null ? this.handshake.cipherSuite() : "none");
        stringBuilder.append(" protocol=");
        stringBuilder.append(this.protocol);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
