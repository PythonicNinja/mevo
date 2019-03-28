package com.mevo.app.data.network.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

public class ReorderGetParametersInterceptor implements Interceptor {
    private String INTERNAL_TAG = "dont_reorder_get_parameters";
    SmartCache cache;

    public ReorderGetParametersInterceptor(SmartCache smartCache) {
        this.cache = smartCache;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.header(this.INTERNAL_TAG) == null) {
            request = request.newBuilder().url(sortQueryParameters(request.url())).build();
        } else {
            request = request.newBuilder().removeHeader(this.INTERNAL_TAG).build();
        }
        return chain.proceed(request);
    }

    HttpUrl sortQueryParameters(HttpUrl httpUrl) {
        Builder newBuilder = httpUrl.newBuilder();
        List<String> arrayList = new ArrayList();
        Map hashMap = new HashMap();
        for (String str : httpUrl.queryParameterNames()) {
            newBuilder.removeAllQueryParameters(str);
            arrayList.add(str);
            hashMap.put(str, httpUrl.queryParameterValues(str));
        }
        Collections.sort(arrayList);
        for (String str2 : arrayList) {
            List list = (List) hashMap.get(str2);
            List<String> arrayList2 = new ArrayList();
            if (list != null) {
                arrayList2.addAll(list);
                Collections.sort(arrayList2);
                for (String str3 : arrayList2) {
                    newBuilder.addQueryParameter(str2, str3);
                }
            }
        }
        return newBuilder.build();
    }
}
