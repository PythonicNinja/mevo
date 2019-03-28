package io.fabric.sdk.android.services.settings;

import android.content.res.Resources.NotFoundException;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.KitInfo;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Locale;

abstract class AbstractAppSpiCall extends AbstractSpiCall implements AppSpiCall {
    public static final String APP_BUILD_VERSION_PARAM = "app[build_version]";
    public static final String APP_BUILT_SDK_VERSION_PARAM = "app[built_sdk_version]";
    public static final String APP_DISPLAY_VERSION_PARAM = "app[display_version]";
    public static final String APP_ICON_DATA_PARAM = "app[icon][data]";
    public static final String APP_ICON_HASH_PARAM = "app[icon][hash]";
    public static final String APP_ICON_HEIGHT_PARAM = "app[icon][height]";
    public static final String APP_ICON_PRERENDERED_PARAM = "app[icon][prerendered]";
    public static final String APP_ICON_WIDTH_PARAM = "app[icon][width]";
    public static final String APP_IDENTIFIER_PARAM = "app[identifier]";
    public static final String APP_INSTANCE_IDENTIFIER_PARAM = "app[instance_identifier]";
    public static final String APP_MIN_SDK_VERSION_PARAM = "app[minimum_sdk_version]";
    public static final String APP_NAME_PARAM = "app[name]";
    public static final String APP_SDK_MODULES_PARAM_BUILD_TYPE = "app[build][libraries][%s][type]";
    public static final String APP_SDK_MODULES_PARAM_PREFIX = "app[build][libraries][%s]";
    public static final String APP_SDK_MODULES_PARAM_VERSION = "app[build][libraries][%s][version]";
    public static final String APP_SOURCE_PARAM = "app[source]";
    static final String ICON_CONTENT_TYPE = "application/octet-stream";
    static final String ICON_FILE_NAME = "icon.png";

    public AbstractAppSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    public boolean invoke(AppRequestData appRequestData) {
        HttpRequest applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), appRequestData), appRequestData);
        Logger logger = Fabric.getLogger();
        String str = Fabric.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Sending app info to ");
        stringBuilder.append(getUrl());
        logger.mo2494d(str, stringBuilder.toString());
        if (appRequestData.icon != null) {
            logger = Fabric.getLogger();
            str = Fabric.TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("App icon hash is ");
            stringBuilder.append(appRequestData.icon.hash);
            logger.mo2494d(str, stringBuilder.toString());
            logger = Fabric.getLogger();
            str = Fabric.TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("App icon size is ");
            stringBuilder.append(appRequestData.icon.width);
            stringBuilder.append("x");
            stringBuilder.append(appRequestData.icon.height);
            logger.mo2494d(str, stringBuilder.toString());
        }
        appRequestData = applyMultipartDataTo.code();
        String str2 = HttpRequest.METHOD_POST.equals(applyMultipartDataTo.method()) ? "Create" : "Update";
        Logger logger2 = Fabric.getLogger();
        String str3 = Fabric.TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str2);
        stringBuilder2.append(" app request ID: ");
        stringBuilder2.append(applyMultipartDataTo.header(AbstractSpiCall.HEADER_REQUEST_ID));
        logger2.mo2494d(str3, stringBuilder2.toString());
        Logger logger3 = Fabric.getLogger();
        str2 = Fabric.TAG;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Result was ");
        stringBuilder3.append(appRequestData);
        logger3.mo2494d(str2, stringBuilder3.toString());
        return ResponseParser.parse(appRequestData) == null ? true : null;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, AppRequestData appRequestData) {
        return httpRequest.header(AbstractSpiCall.HEADER_API_KEY, appRequestData.apiKey).header(AbstractSpiCall.HEADER_CLIENT_TYPE, "android").header(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion());
    }

    private HttpRequest applyMultipartDataTo(HttpRequest httpRequest, AppRequestData appRequestData) {
        Closeable openRawResource;
        Throwable e;
        Logger logger;
        String str;
        StringBuilder stringBuilder;
        httpRequest = httpRequest.part(APP_IDENTIFIER_PARAM, appRequestData.appId).part(APP_NAME_PARAM, appRequestData.name).part(APP_DISPLAY_VERSION_PARAM, appRequestData.displayVersion).part(APP_BUILD_VERSION_PARAM, appRequestData.buildVersion).part(APP_SOURCE_PARAM, Integer.valueOf(appRequestData.source)).part(APP_MIN_SDK_VERSION_PARAM, appRequestData.minSdkVersion).part(APP_BUILT_SDK_VERSION_PARAM, appRequestData.builtSdkVersion);
        if (!CommonUtils.isNullOrEmpty(appRequestData.instanceIdentifier)) {
            httpRequest.part(APP_INSTANCE_IDENTIFIER_PARAM, appRequestData.instanceIdentifier);
        }
        if (appRequestData.icon != null) {
            try {
                openRawResource = this.kit.getContext().getResources().openRawResource(appRequestData.icon.iconResourceId);
                try {
                    httpRequest.part(APP_ICON_HASH_PARAM, appRequestData.icon.hash).part(APP_ICON_DATA_PARAM, ICON_FILE_NAME, ICON_CONTENT_TYPE, (InputStream) openRawResource).part(APP_ICON_WIDTH_PARAM, Integer.valueOf(appRequestData.icon.width)).part(APP_ICON_HEIGHT_PARAM, Integer.valueOf(appRequestData.icon.height));
                } catch (NotFoundException e2) {
                    e = e2;
                    try {
                        logger = Fabric.getLogger();
                        str = Fabric.TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to find app icon with resource ID: ");
                        stringBuilder.append(appRequestData.icon.iconResourceId);
                        logger.mo2497e(str, stringBuilder.toString(), e);
                        CommonUtils.closeOrLog(openRawResource, "Failed to close app icon InputStream.");
                        if (appRequestData.sdkKits != null) {
                            for (KitInfo kitInfo : appRequestData.sdkKits) {
                                httpRequest.part(getKitVersionKey(kitInfo), kitInfo.getVersion());
                                httpRequest.part(getKitBuildTypeKey(kitInfo), kitInfo.getBuildType());
                            }
                        }
                        return httpRequest;
                    } catch (Throwable th) {
                        httpRequest = th;
                        CommonUtils.closeOrLog(openRawResource, "Failed to close app icon InputStream.");
                        throw httpRequest;
                    }
                }
            } catch (Throwable e3) {
                Throwable th2 = e3;
                openRawResource = null;
                e = th2;
                logger = Fabric.getLogger();
                str = Fabric.TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to find app icon with resource ID: ");
                stringBuilder.append(appRequestData.icon.iconResourceId);
                logger.mo2497e(str, stringBuilder.toString(), e);
                CommonUtils.closeOrLog(openRawResource, "Failed to close app icon InputStream.");
                if (appRequestData.sdkKits != null) {
                    for (KitInfo kitInfo2 : appRequestData.sdkKits) {
                        httpRequest.part(getKitVersionKey(kitInfo2), kitInfo2.getVersion());
                        httpRequest.part(getKitBuildTypeKey(kitInfo2), kitInfo2.getBuildType());
                    }
                }
                return httpRequest;
            } catch (Throwable th3) {
                httpRequest = th3;
                openRawResource = null;
                CommonUtils.closeOrLog(openRawResource, "Failed to close app icon InputStream.");
                throw httpRequest;
            }
            CommonUtils.closeOrLog(openRawResource, "Failed to close app icon InputStream.");
        }
        if (appRequestData.sdkKits != null) {
            for (KitInfo kitInfo22 : appRequestData.sdkKits) {
                httpRequest.part(getKitVersionKey(kitInfo22), kitInfo22.getVersion());
                httpRequest.part(getKitBuildTypeKey(kitInfo22), kitInfo22.getBuildType());
            }
        }
        return httpRequest;
    }

    String getKitVersionKey(KitInfo kitInfo) {
        return String.format(Locale.US, APP_SDK_MODULES_PARAM_VERSION, new Object[]{kitInfo.getIdentifier()});
    }

    String getKitBuildTypeKey(KitInfo kitInfo) {
        return String.format(Locale.US, APP_SDK_MODULES_PARAM_BUILD_TYPE, new Object[]{kitInfo.getIdentifier()});
    }
}
