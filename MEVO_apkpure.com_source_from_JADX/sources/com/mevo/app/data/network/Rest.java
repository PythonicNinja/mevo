package com.mevo.app.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inverce.mod.integrations.bugfixes.okhttp.TLS12OkHttpSupport;
import com.mevo.app.Cfg;
import com.mevo.app.data.network.cache.SmartCache;
import com.mevo.app.data.network.interceptors.NextbikeApiFabricLogger;
import com.mevo.app.data.network.interceptors.NextbikeUserAgentInterceptor;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Rest {
    private static ApiExtended apiExtended;
    private static ApiNextbike apiNextbike;
    private static ApiNextbikeJson apiNextbikeJson;
    private static ApiNextbikeMap apiNextbikeMap;
    private static ApiNextbike apiNextbikeShortTimeout;
    private static Gson jsonSerializer;
    private static Serializer xmlSerializer;

    public static void init() {
        xmlSerializer = new Persister(new RegistryStrategy(new Registry()));
        jsonSerializer = new GsonBuilder().create();
        Builder addInterceptor = new Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).addInterceptor(new NextbikeApiFabricLogger()).addInterceptor(new NextbikeUserAgentInterceptor());
        Builder addInterceptor2 = new Builder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.SECONDS).addInterceptor(new NextbikeApiFabricLogger()).addInterceptor(new NextbikeUserAgentInterceptor());
        if (Cfg.get().isCacheEnabled()) {
            new SmartCache(25).applyTo(addInterceptor);
        }
        Retrofit.Builder addConverterFactory = new Retrofit.Builder().baseUrl(Cfg.get().apiNextbikeUrl()).addConverterFactory(SimpleXmlConverterFactory.createNonStrict(xmlSerializer));
        Retrofit.Builder addConverterFactory2 = new Retrofit.Builder().baseUrl(Cfg.get().apiNextbikeMapUrl()).addConverterFactory(SimpleXmlConverterFactory.createNonStrict(xmlSerializer));
        Retrofit.Builder addConverterFactory3 = new Retrofit.Builder().baseUrl(Cfg.get().apiExtendedUrl()).addConverterFactory(GsonConverterFactory.create(jsonSerializer));
        Retrofit.Builder addConverterFactory4 = new Retrofit.Builder().baseUrl(Cfg.get().apiNextbikeUrl()).addConverterFactory(GsonConverterFactory.create(jsonSerializer));
        OkHttpClient build = TLS12OkHttpSupport.enableTls12OnPreLollipop(addInterceptor).build();
        OkHttpClient build2 = TLS12OkHttpSupport.enableTls12OnPreLollipop(addInterceptor2).build();
        apiNextbike = (ApiNextbike) addConverterFactory.client(build).build().create(ApiNextbike.class);
        apiNextbikeMap = (ApiNextbikeMap) addConverterFactory2.client(build).build().create(ApiNextbikeMap.class);
        apiNextbikeShortTimeout = (ApiNextbike) addConverterFactory.client(build2).build().create(ApiNextbike.class);
        apiNextbikeJson = (ApiNextbikeJson) addConverterFactory4.client(build).build().create(ApiNextbikeJson.class);
        apiExtended = (ApiExtended) addConverterFactory3.client(build).build().create(ApiExtended.class);
    }

    public static ApiNextbike getApiNextbike() {
        return apiNextbike;
    }

    public static ApiNextbikeMap getApiNextbikeMap() {
        return apiNextbikeMap;
    }

    public static ApiNextbikeJson getApiNextbikeJson() {
        return apiNextbikeJson;
    }

    public static ApiNextbike getApiNextbikeShortTimeout() {
        return apiNextbikeShortTimeout;
    }

    public static ApiExtended getApiExtended() {
        return apiExtended;
    }

    public static Gson getJsonSerializer() {
        return jsonSerializer;
    }

    public static Serializer getXmlSerializer() {
        return xmlSerializer;
    }
}
