package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    static final String BUILD_VERSION_PARAM = "build_version";
    static final String DISPLAY_VERSION_PARAM = "display_version";
    static final String HEADER_ADVERTISING_TOKEN = "X-CRASHLYTICS-ADVERTISING-TOKEN";
    static final String HEADER_ANDROID_ID = "X-CRASHLYTICS-ANDROID-ID";
    static final String HEADER_DEVICE_MODEL = "X-CRASHLYTICS-DEVICE-MODEL";
    static final String HEADER_INSTALLATION_ID = "X-CRASHLYTICS-INSTALLATION-ID";
    static final String HEADER_OS_BUILD_VERSION = "X-CRASHLYTICS-OS-BUILD-VERSION";
    static final String HEADER_OS_DISPLAY_VERSION = "X-CRASHLYTICS-OS-DISPLAY-VERSION";
    static final String ICON_HASH = "icon_hash";
    static final String INSTANCE_PARAM = "instance";
    static final String SOURCE_PARAM = "source";

    boolean requestWasSuccessful(int i) {
        if (!(i == 200 || i == 201 || i == 202)) {
            if (i != 203) {
                return false;
            }
        }
        return true;
    }

    public DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        this(kit, str, str2, httpRequestFactory, HttpMethod.GET);
    }

    DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    public JSONObject invoke(SettingsRequest settingsRequest) {
        String str;
        StringBuilder stringBuilder;
        Throwable e;
        Logger logger;
        Throwable th;
        try {
            Map queryParamsFor = getQueryParamsFor(settingsRequest);
            HttpRequest httpRequest = getHttpRequest(queryParamsFor);
            try {
                settingsRequest = applyHeadersTo(httpRequest, settingsRequest);
                try {
                    Logger logger2 = Fabric.getLogger();
                    String str2 = Fabric.TAG;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Requesting settings from ");
                    stringBuilder2.append(getUrl());
                    logger2.mo2494d(str2, stringBuilder2.toString());
                    logger2 = Fabric.getLogger();
                    str2 = Fabric.TAG;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Settings query params were: ");
                    stringBuilder2.append(queryParamsFor);
                    logger2.mo2494d(str2, stringBuilder2.toString());
                    JSONObject handleResponse = handleResponse(settingsRequest);
                    if (settingsRequest != null) {
                        Logger logger3 = Fabric.getLogger();
                        str = Fabric.TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Settings request ID: ");
                        stringBuilder.append(settingsRequest.header(AbstractSpiCall.HEADER_REQUEST_ID));
                        logger3.mo2494d(str, stringBuilder.toString());
                    }
                    return handleResponse;
                } catch (HttpRequestException e2) {
                    e = e2;
                    try {
                        Fabric.getLogger().mo2497e(Fabric.TAG, "Settings request failed.", e);
                        if (settingsRequest != null) {
                            return null;
                        }
                        logger = Fabric.getLogger();
                        str = Fabric.TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Settings request ID: ");
                        stringBuilder.append(settingsRequest.header(AbstractSpiCall.HEADER_REQUEST_ID));
                        logger.mo2494d(str, stringBuilder.toString());
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (settingsRequest != null) {
                            logger = Fabric.getLogger();
                            str = Fabric.TAG;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Settings request ID: ");
                            stringBuilder.append(settingsRequest.header(AbstractSpiCall.HEADER_REQUEST_ID));
                            logger.mo2494d(str, stringBuilder.toString());
                        }
                        throw th;
                    }
                }
            } catch (HttpRequestException e3) {
                e = e3;
                settingsRequest = httpRequest;
                Fabric.getLogger().mo2497e(Fabric.TAG, "Settings request failed.", e);
                if (settingsRequest != null) {
                    return null;
                }
                logger = Fabric.getLogger();
                str = Fabric.TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Settings request ID: ");
                stringBuilder.append(settingsRequest.header(AbstractSpiCall.HEADER_REQUEST_ID));
                logger.mo2494d(str, stringBuilder.toString());
                return null;
            } catch (Throwable th3) {
                th = th3;
                settingsRequest = httpRequest;
                if (settingsRequest != null) {
                    logger = Fabric.getLogger();
                    str = Fabric.TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Settings request ID: ");
                    stringBuilder.append(settingsRequest.header(AbstractSpiCall.HEADER_REQUEST_ID));
                    logger.mo2494d(str, stringBuilder.toString());
                }
                throw th;
            }
        } catch (HttpRequestException e4) {
            e = e4;
            settingsRequest = null;
            Fabric.getLogger().mo2497e(Fabric.TAG, "Settings request failed.", e);
            if (settingsRequest != null) {
                return null;
            }
            logger = Fabric.getLogger();
            str = Fabric.TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Settings request ID: ");
            stringBuilder.append(settingsRequest.header(AbstractSpiCall.HEADER_REQUEST_ID));
            logger.mo2494d(str, stringBuilder.toString());
            return null;
        } catch (SettingsRequest settingsRequest2) {
            th = settingsRequest2;
            settingsRequest2 = null;
            if (settingsRequest2 != null) {
                logger = Fabric.getLogger();
                str = Fabric.TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Settings request ID: ");
                stringBuilder.append(settingsRequest2.header(AbstractSpiCall.HEADER_REQUEST_ID));
                logger.mo2494d(str, stringBuilder.toString());
            }
            throw th;
        }
    }

    JSONObject handleResponse(HttpRequest httpRequest) {
        int code = httpRequest.code();
        Logger logger = Fabric.getLogger();
        String str = Fabric.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Settings result was: ");
        stringBuilder.append(code);
        logger.mo2494d(str, stringBuilder.toString());
        if (requestWasSuccessful(code)) {
            return getJsonObjectFrom(httpRequest.body());
        }
        httpRequest = Fabric.getLogger();
        String str2 = Fabric.TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Failed to retrieve settings from ");
        stringBuilder2.append(getUrl());
        httpRequest.mo2496e(str2, stringBuilder2.toString());
        return null;
    }

    private JSONObject getJsonObjectFrom(String str) {
        try {
            return new JSONObject(str);
        } catch (Throwable e) {
            Logger logger = Fabric.getLogger();
            String str2 = Fabric.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to parse settings JSON from ");
            stringBuilder.append(getUrl());
            logger.mo2495d(str2, stringBuilder.toString(), e);
            Logger logger2 = Fabric.getLogger();
            String str3 = Fabric.TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Settings response ");
            stringBuilder2.append(str);
            logger2.mo2494d(str3, stringBuilder2.toString());
            return null;
        }
    }

    private Map<String, String> getQueryParamsFor(SettingsRequest settingsRequest) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(BUILD_VERSION_PARAM, settingsRequest.buildVersion);
        hashMap.put(DISPLAY_VERSION_PARAM, settingsRequest.displayVersion);
        hashMap.put("source", Integer.toString(settingsRequest.source));
        if (settingsRequest.iconHash != null) {
            hashMap.put(ICON_HASH, settingsRequest.iconHash);
        }
        settingsRequest = settingsRequest.instanceId;
        if (!CommonUtils.isNullOrEmpty(settingsRequest)) {
            hashMap.put(INSTANCE_PARAM, settingsRequest);
        }
        return hashMap;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, SettingsRequest settingsRequest) {
        applyNonNullHeader(httpRequest, AbstractSpiCall.HEADER_API_KEY, settingsRequest.apiKey);
        applyNonNullHeader(httpRequest, AbstractSpiCall.HEADER_CLIENT_TYPE, "android");
        applyNonNullHeader(httpRequest, AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion());
        applyNonNullHeader(httpRequest, "Accept", "application/json");
        applyNonNullHeader(httpRequest, HEADER_DEVICE_MODEL, settingsRequest.deviceModel);
        applyNonNullHeader(httpRequest, HEADER_OS_BUILD_VERSION, settingsRequest.osBuildVersion);
        applyNonNullHeader(httpRequest, HEADER_OS_DISPLAY_VERSION, settingsRequest.osDisplayVersion);
        applyNonNullHeader(httpRequest, HEADER_ADVERTISING_TOKEN, settingsRequest.advertisingId);
        applyNonNullHeader(httpRequest, HEADER_INSTALLATION_ID, settingsRequest.installationId);
        applyNonNullHeader(httpRequest, HEADER_ANDROID_ID, settingsRequest.androidId);
        return httpRequest;
    }

    private void applyNonNullHeader(HttpRequest httpRequest, String str, String str2) {
        if (str2 != null) {
            httpRequest.header(str, str2);
        }
    }
}
