package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.EventListener;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteSelector.Selection;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

public final class StreamAllocation {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public final Address address;
    public final Call call;
    private final Object callStackTrace;
    private boolean canceled;
    private HttpCodec codec;
    private RealConnection connection;
    private final ConnectionPool connectionPool;
    public final EventListener eventListener;
    private int refusedStreamCount;
    private boolean released;
    private boolean reportedAcquired;
    private Route route;
    private Selection routeSelection;
    private final RouteSelector routeSelector;

    public static final class StreamAllocationReference extends WeakReference<StreamAllocation> {
        public final Object callStackTrace;

        StreamAllocationReference(StreamAllocation streamAllocation, Object obj) {
            super(streamAllocation);
            this.callStackTrace = obj;
        }
    }

    public StreamAllocation(ConnectionPool connectionPool, Address address, Call call, EventListener eventListener, Object obj) {
        this.connectionPool = connectionPool;
        this.address = address;
        this.call = call;
        this.eventListener = eventListener;
        this.routeSelector = new RouteSelector(address, routeDatabase(), call, eventListener);
        this.callStackTrace = obj;
    }

    public HttpCodec newStream(OkHttpClient okHttpClient, Chain chain, boolean z) {
        try {
            okHttpClient = findHealthyConnection(chain.connectTimeoutMillis(), chain.readTimeoutMillis(), chain.writeTimeoutMillis(), okHttpClient.pingIntervalMillis(), okHttpClient.retryOnConnectionFailure(), z).newCodec(okHttpClient, chain, this);
            synchronized (this.connectionPool) {
                this.codec = okHttpClient;
            }
            return okHttpClient;
        } catch (OkHttpClient okHttpClient2) {
            throw new RouteException(okHttpClient2);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.internal.connection.RealConnection findHealthyConnection(int r4, int r5, int r6, int r7, boolean r8, boolean r9) throws java.io.IOException {
        /*
        r3 = this;
    L_0x0000:
        r0 = r3.findConnection(r4, r5, r6, r7, r8);
        r1 = r3.connectionPool;
        monitor-enter(r1);
        r2 = r0.successCount;	 Catch:{ all -> 0x0019 }
        if (r2 != 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x0019 }
        return r0;
    L_0x000d:
        monitor-exit(r1);	 Catch:{ all -> 0x0019 }
        r1 = r0.isHealthy(r9);
        if (r1 != 0) goto L_0x0018;
    L_0x0014:
        r3.noNewStreams();
        goto L_0x0000;
    L_0x0018:
        return r0;
    L_0x0019:
        r4 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0019 }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.StreamAllocation.findHealthyConnection(int, int, int, int, boolean, boolean):okhttp3.internal.connection.RealConnection");
    }

    private RealConnection findConnection(int i, int i2, int i3, int i4, boolean z) throws IOException {
        Socket releaseIfNoNewStreams;
        Connection connection;
        RealConnection realConnection;
        Route route;
        Object obj;
        synchronized (this.connectionPool) {
            Socket socket;
            RealConnection realConnection2;
            try {
                if (r1.released) {
                    throw new IllegalStateException("released");
                } else if (r1.codec != null) {
                    throw new IllegalStateException("codec != null");
                } else if (r1.canceled) {
                    throw new IOException("Canceled");
                } else {
                    realConnection2 = r1.connection;
                    releaseIfNoNewStreams = releaseIfNoNewStreams();
                    connection = r1.connection;
                    socket = null;
                    if (connection != null) {
                        realConnection2 = r1.connection;
                    } else {
                        connection = realConnection2;
                        realConnection2 = null;
                    }
                    if (!r1.reportedAcquired) {
                        connection = socket;
                    }
                    if (realConnection2 == null) {
                        Internal.instance.get(r1.connectionPool, r1.address, r1, socket);
                        if (r1.connection != null) {
                            realConnection = r1.connection;
                            route = socket;
                            obj = 1;
                        } else {
                            route = r1.route;
                            realConnection = realConnection2;
                        }
                    } else {
                        realConnection = realConnection2;
                        route = socket;
                    }
                    obj = null;
                }
            } finally {
                RealConnection realConnection3 = 
/*
Method generation error in method: okhttp3.internal.connection.StreamAllocation.findConnection(int, int, int, int, boolean):okhttp3.internal.connection.RealConnection, dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: ?: MERGE  (r0_7 'realConnection3' okhttp3.internal.connection.RealConnection) = (r0_6 'realConnection3' okhttp3.internal.connection.RealConnection), (r6_5 okhttp3.internal.connection.RealConnection) in method: okhttp3.internal.connection.StreamAllocation.findConnection(int, int, int, int, boolean):okhttp3.internal.connection.RealConnection, dex: classes.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:203)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:100)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:50)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:299)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:229)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:65)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:187)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
Caused by: jadx.core.utils.exceptions.CodegenException: MERGE can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 29 more

*/

                private Socket releaseIfNoNewStreams() {
                    RealConnection realConnection = this.connection;
                    return (realConnection == null || !realConnection.noNewStreams) ? null : deallocate(false, false, true);
                }

                public void streamFinished(boolean z, HttpCodec httpCodec, long j, IOException iOException) {
                    Socket deallocate;
                    this.eventListener.responseBodyEnd(this.call, j);
                    synchronized (this.connectionPool) {
                        if (httpCodec != null) {
                            if (httpCodec == this.codec) {
                                if (!z) {
                                    RealConnection realConnection = this.connection;
                                    realConnection.successCount++;
                                }
                                Connection connection = this.connection;
                                deallocate = deallocate(z, false, true);
                                if (this.connection != null) {
                                    connection = null;
                                }
                                httpCodec = this.released;
                            }
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("expected ");
                        stringBuilder.append(this.codec);
                        stringBuilder.append(" but was ");
                        stringBuilder.append(httpCodec);
                        throw new IllegalStateException(stringBuilder.toString());
                    }
                    Util.closeQuietly(deallocate);
                    if (connection != null) {
                        this.eventListener.connectionReleased(this.call, connection);
                    }
                    if (iOException != null) {
                        this.eventListener.callFailed(this.call, iOException);
                    } else if (httpCodec != null) {
                        this.eventListener.callEnd(this.call);
                    }
                }

                public HttpCodec codec() {
                    HttpCodec httpCodec;
                    synchronized (this.connectionPool) {
                        httpCodec = this.codec;
                    }
                    return httpCodec;
                }

                private RouteDatabase routeDatabase() {
                    return Internal.instance.routeDatabase(this.connectionPool);
                }

                public Route route() {
                    return this.route;
                }

                public synchronized RealConnection connection() {
                    return this.connection;
                }

                public void release() {
                    Socket deallocate;
                    synchronized (this.connectionPool) {
                        Connection connection = this.connection;
                        deallocate = deallocate(false, true, false);
                        if (this.connection != null) {
                            connection = null;
                        }
                    }
                    Util.closeQuietly(deallocate);
                    if (connection != null) {
                        this.eventListener.connectionReleased(this.call, connection);
                    }
                }

                public void noNewStreams() {
                    Socket deallocate;
                    synchronized (this.connectionPool) {
                        Connection connection = this.connection;
                        deallocate = deallocate(true, false, false);
                        if (this.connection != null) {
                            connection = null;
                        }
                    }
                    Util.closeQuietly(deallocate);
                    if (connection != null) {
                        this.eventListener.connectionReleased(this.call, connection);
                    }
                }

                private Socket deallocate(boolean z, boolean z2, boolean z3) {
                    if (z3) {
                        this.codec = null;
                    }
                    if (z2) {
                        this.released = true;
                    }
                    if (this.connection) {
                        if (z) {
                            this.connection.noNewStreams = true;
                        }
                        if (!this.codec && (this.released || this.connection.noNewStreams)) {
                            release(this.connection);
                            if (this.connection.allocations.isEmpty()) {
                                this.connection.idleAtNanos = System.nanoTime();
                                if (Internal.instance.connectionBecameIdle(this.connectionPool, this.connection)) {
                                    z = this.connection.socket();
                                    this.connection = null;
                                    return z;
                                }
                            }
                            z = false;
                            this.connection = null;
                            return z;
                        }
                    }
                    return false;
                }

                public void cancel() {
                    synchronized (this.connectionPool) {
                        this.canceled = true;
                        HttpCodec httpCodec = this.codec;
                        RealConnection realConnection = this.connection;
                    }
                    if (httpCodec != null) {
                        httpCodec.cancel();
                    } else if (realConnection != null) {
                        realConnection.cancel();
                    }
                }

                public void streamFailed(IOException iOException) {
                    Connection connection;
                    Socket deallocate;
                    synchronized (this.connectionPool) {
                        if (iOException instanceof StreamResetException) {
                            StreamResetException streamResetException = (StreamResetException) iOException;
                            if (streamResetException.errorCode == ErrorCode.REFUSED_STREAM) {
                                this.refusedStreamCount++;
                            }
                            if (streamResetException.errorCode != ErrorCode.REFUSED_STREAM || this.refusedStreamCount > 1) {
                                this.route = null;
                            }
                            iOException = null;
                            connection = this.connection;
                            deallocate = deallocate(iOException, false, true);
                            if (!(this.connection == null && this.reportedAcquired)) {
                                connection = null;
                            }
                        } else {
                            if (this.connection != null && (!this.connection.isMultiplexed() || (iOException instanceof ConnectionShutdownException))) {
                                if (this.connection.successCount == 0) {
                                    if (!(this.route == null || iOException == null)) {
                                        this.routeSelector.connectFailed(this.route, iOException);
                                    }
                                    this.route = null;
                                }
                            }
                            iOException = null;
                            connection = this.connection;
                            deallocate = deallocate(iOException, false, true);
                            connection = null;
                        }
                        iOException = true;
                        connection = this.connection;
                        deallocate = deallocate(iOException, false, true);
                        connection = null;
                    }
                    Util.closeQuietly(deallocate);
                    if (connection != null) {
                        this.eventListener.connectionReleased(this.call, connection);
                    }
                }

                public void acquire(RealConnection realConnection, boolean z) {
                    if (this.connection != null) {
                        throw new IllegalStateException();
                    }
                    this.connection = realConnection;
                    this.reportedAcquired = z;
                    realConnection.allocations.add(new StreamAllocationReference(this, this.callStackTrace));
                }

                private void release(RealConnection realConnection) {
                    int size = realConnection.allocations.size();
                    for (int i = 0; i < size; i++) {
                        if (((Reference) realConnection.allocations.get(i)).get() == this) {
                            realConnection.allocations.remove(i);
                            return;
                        }
                    }
                    throw new IllegalStateException();
                }

                public Socket releaseAndAcquire(RealConnection realConnection) {
                    if (this.codec == null) {
                        if (this.connection.allocations.size() == 1) {
                            Reference reference = (Reference) this.connection.allocations.get(0);
                            Socket deallocate = deallocate(true, false, false);
                            this.connection = realConnection;
                            realConnection.allocations.add(reference);
                            return deallocate;
                        }
                    }
                    throw new IllegalStateException();
                }

                public boolean hasMoreRoutes() {
                    if (this.route == null && (this.routeSelection == null || !this.routeSelection.hasNext())) {
                        if (!this.routeSelector.hasNext()) {
                            return false;
                        }
                    }
                    return true;
                }

                public String toString() {
                    RealConnection connection = connection();
                    return connection != null ? connection.toString() : this.address.toString();
                }
            }
