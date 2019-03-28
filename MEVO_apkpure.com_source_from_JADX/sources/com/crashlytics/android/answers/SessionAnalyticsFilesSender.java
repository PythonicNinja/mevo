package com.crashlytics.android.answers;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.events.FilesSender;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.File;
import java.util.List;

class SessionAnalyticsFilesSender extends AbstractSpiCall implements FilesSender {
    static final String FILE_CONTENT_TYPE = "application/vnd.crashlytics.android.events";
    static final String FILE_PARAM_NAME = "session_analytics_file_";
    private final String apiKey;

    public SessionAnalyticsFilesSender(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, String str3) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.POST);
        this.apiKey = str3;
    }

    public boolean send(List<File> list) {
        HttpRequest header = getHttpRequest().header(AbstractSpiCall.HEADER_CLIENT_TYPE, "android").header(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion()).header(AbstractSpiCall.HEADER_API_KEY, this.apiKey);
        int i = 0;
        for (File file : list) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(FILE_PARAM_NAME);
            stringBuilder.append(i);
            header.part(stringBuilder.toString(), file.getName(), FILE_CONTENT_TYPE, file);
            i++;
        }
        Logger logger = Fabric.getLogger();
        String str = Answers.TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Sending ");
        stringBuilder2.append(list.size());
        stringBuilder2.append(" analytics files to ");
        stringBuilder2.append(getUrl());
        logger.mo2494d(str, stringBuilder2.toString());
        list = header.code();
        Logger logger2 = Fabric.getLogger();
        String str2 = Answers.TAG;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Response code for analytics file send is ");
        stringBuilder3.append(list);
        logger2.mo2494d(str2, stringBuilder3.toString());
        if (ResponseParser.parse(list) == null) {
            return true;
        }
        return false;
    }
}
