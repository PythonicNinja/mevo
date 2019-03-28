package okhttp3.internal.connection;

import android.support.v4.internal.view.SupportMenu;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Route;
import okhttp3.internal.Util;

public final class RouteSelector {
    private final Address address;
    private final Call call;
    private final EventListener eventListener;
    private List<InetSocketAddress> inetSocketAddresses = Collections.emptyList();
    private int nextProxyIndex;
    private final List<Route> postponedRoutes = new ArrayList();
    private List<Proxy> proxies = Collections.emptyList();
    private final RouteDatabase routeDatabase;

    public static final class Selection {
        private int nextRouteIndex = 0;
        private final List<Route> routes;

        Selection(List<Route> list) {
            this.routes = list;
        }

        public boolean hasNext() {
            return this.nextRouteIndex < this.routes.size();
        }

        public Route next() {
            if (hasNext()) {
                List list = this.routes;
                int i = this.nextRouteIndex;
                this.nextRouteIndex = i + 1;
                return (Route) list.get(i);
            }
            throw new NoSuchElementException();
        }

        public List<Route> getAll() {
            return new ArrayList(this.routes);
        }
    }

    public RouteSelector(Address address, RouteDatabase routeDatabase, Call call, EventListener eventListener) {
        this.address = address;
        this.routeDatabase = routeDatabase;
        this.call = call;
        this.eventListener = eventListener;
        resetNextProxy(address.url(), address.proxy());
    }

    public boolean hasNext() {
        if (!hasNextProxy()) {
            if (this.postponedRoutes.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public Selection next() throws IOException {
        if (hasNext()) {
            List arrayList = new ArrayList();
            while (hasNextProxy()) {
                Proxy nextProxy = nextProxy();
                int size = this.inetSocketAddresses.size();
                for (int i = 0; i < size; i++) {
                    Route route = new Route(this.address, nextProxy, (InetSocketAddress) this.inetSocketAddresses.get(i));
                    if (this.routeDatabase.shouldPostpone(route)) {
                        this.postponedRoutes.add(route);
                    } else {
                        arrayList.add(route);
                    }
                }
                if (!arrayList.isEmpty()) {
                    break;
                }
            }
            if (arrayList.isEmpty()) {
                arrayList.addAll(this.postponedRoutes);
                this.postponedRoutes.clear();
            }
            return new Selection(arrayList);
        }
        throw new NoSuchElementException();
    }

    public void connectFailed(Route route, IOException iOException) {
        if (!(route.proxy().type() == Type.DIRECT || this.address.proxySelector() == null)) {
            this.address.proxySelector().connectFailed(this.address.url().uri(), route.proxy().address(), iOException);
        }
        this.routeDatabase.failed(route);
    }

    private void resetNextProxy(HttpUrl httpUrl, Proxy proxy) {
        if (proxy != null) {
            this.proxies = Collections.singletonList(proxy);
        } else {
            List select = this.address.proxySelector().select(httpUrl.uri());
            if (select == null || select.isEmpty() != null) {
                httpUrl = Util.immutableList(Proxy.NO_PROXY);
            } else {
                httpUrl = Util.immutableList(select);
            }
            this.proxies = httpUrl;
        }
        this.nextProxyIndex = 0;
    }

    private boolean hasNextProxy() {
        return this.nextProxyIndex < this.proxies.size();
    }

    private Proxy nextProxy() throws IOException {
        if (hasNextProxy()) {
            List list = this.proxies;
            int i = this.nextProxyIndex;
            this.nextProxyIndex = i + 1;
            Proxy proxy = (Proxy) list.get(i);
            resetNextInetSocketAddress(proxy);
            return proxy;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("No route to ");
        stringBuilder.append(this.address.url().host());
        stringBuilder.append("; exhausted proxy configurations: ");
        stringBuilder.append(this.proxies);
        throw new SocketException(stringBuilder.toString());
    }

    private void resetNextInetSocketAddress(Proxy proxy) throws IOException {
        String hostString;
        int port;
        int size;
        int i;
        StringBuilder stringBuilder;
        this.inetSocketAddresses = new ArrayList();
        if (proxy.type() != Type.DIRECT) {
            if (proxy.type() != Type.SOCKS) {
                SocketAddress address = proxy.address();
                if (address instanceof InetSocketAddress) {
                    InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
                    hostString = getHostString(inetSocketAddress);
                    port = inetSocketAddress.getPort();
                    if (port >= 1) {
                        if (port > SupportMenu.USER_MASK) {
                            if (proxy.type() != Type.SOCKS) {
                                this.inetSocketAddresses.add(InetSocketAddress.createUnresolved(hostString, port));
                                return;
                            }
                            this.eventListener.dnsStart(this.call, hostString);
                            proxy = this.address.dns().lookup(hostString);
                            if (proxy.isEmpty()) {
                                this.eventListener.dnsEnd(this.call, hostString, proxy);
                                size = proxy.size();
                                for (i = 0; i < size; i++) {
                                    this.inetSocketAddresses.add(new InetSocketAddress((InetAddress) proxy.get(i), port));
                                }
                                return;
                            }
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(this.address.dns());
                            stringBuilder2.append(" returned no addresses for ");
                            stringBuilder2.append(hostString);
                            throw new UnknownHostException(stringBuilder2.toString());
                        }
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("No route to ");
                    stringBuilder.append(hostString);
                    stringBuilder.append(":");
                    stringBuilder.append(port);
                    stringBuilder.append("; port is out of range");
                    throw new SocketException(stringBuilder.toString());
                }
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Proxy.address() is not an InetSocketAddress: ");
                stringBuilder3.append(address.getClass());
                throw new IllegalArgumentException(stringBuilder3.toString());
            }
        }
        hostString = this.address.url().host();
        port = this.address.url().port();
        if (port >= 1) {
            if (port > SupportMenu.USER_MASK) {
                if (proxy.type() != Type.SOCKS) {
                    this.eventListener.dnsStart(this.call, hostString);
                    proxy = this.address.dns().lookup(hostString);
                    if (proxy.isEmpty()) {
                        this.eventListener.dnsEnd(this.call, hostString, proxy);
                        size = proxy.size();
                        for (i = 0; i < size; i++) {
                            this.inetSocketAddresses.add(new InetSocketAddress((InetAddress) proxy.get(i), port));
                        }
                        return;
                    }
                    StringBuilder stringBuilder22 = new StringBuilder();
                    stringBuilder22.append(this.address.dns());
                    stringBuilder22.append(" returned no addresses for ");
                    stringBuilder22.append(hostString);
                    throw new UnknownHostException(stringBuilder22.toString());
                }
                this.inetSocketAddresses.add(InetSocketAddress.createUnresolved(hostString, port));
                return;
            }
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("No route to ");
        stringBuilder.append(hostString);
        stringBuilder.append(":");
        stringBuilder.append(port);
        stringBuilder.append("; port is out of range");
        throw new SocketException(stringBuilder.toString());
    }

    static String getHostString(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }
}
