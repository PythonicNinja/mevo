package com.mevo.app.data.network.interceptors;

import android.text.TextUtils;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.mevo.app.Cfg;
import com.mevo.app.constants.FabricEvents;
import com.mevo.app.constants.Servers;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;

public class NextbikeApiFabricLogger implements Interceptor {
    private static final Pattern EVENT_NAME_PATTERN = Pattern.compile("/[a-zA-Z0-9_-]+\\.xml");
    private static final String EVENT_NAME_PATTER_PREFIX = "/";
    private static final String TAG = "NextbikeApiFabricLogger";

    public Response intercept(Chain chain) throws IOException {
        CharSequence httpUrl = chain.request().url().toString();
        if (httpUrl.contains(Servers.API_NEXTBIKE_PROD)) {
            Matcher matcher = EVENT_NAME_PATTERN.matcher(httpUrl);
            Object obj = "";
            if (matcher.find()) {
                obj = matcher.group().replaceAll("/", "");
            }
            if (!TextUtils.isEmpty(obj) && Cfg.get().fabricEnabled()) {
                Answers.getInstance().logCustom((CustomEvent) new CustomEvent(FabricEvents.API_NEXTBIKE_EVENT_NAME).putCustomAttribute(FabricEvents.API_NEXTBIKE_ATTRIBUTE_NAME, (String) obj));
            }
        }
        return chain.proceed(chain.request());
    }
}
