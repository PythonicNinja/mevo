package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;

public class ApiKey {
    static final String CRASHLYTICS_API_KEY = "com.crashlytics.ApiKey";
    static final String FABRIC_API_KEY = "io.fabric.ApiKey";

    protected String buildApiKeyInstructions() {
        return "Fabric could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"io.fabric.ApiKey\" android:value=\"YOUR_API_KEY\"/>";
    }

    @Deprecated
    public static String getApiKey(Context context) {
        Fabric.getLogger().mo2507w(Fabric.TAG, "getApiKey(context) is deprecated, please upgrade kit(s) to the latest version.");
        return new ApiKey().getValue(context);
    }

    @Deprecated
    public static String getApiKey(Context context, boolean z) {
        Fabric.getLogger().mo2507w(Fabric.TAG, "getApiKey(context, debug) is deprecated, please upgrade kit(s) to the latest version.");
        return new ApiKey().getValue(context);
    }

    public String getValue(Context context) {
        Object apiKeyFromManifest = getApiKeyFromManifest(context);
        if (TextUtils.isEmpty(apiKeyFromManifest)) {
            apiKeyFromManifest = getApiKeyFromStrings(context);
        }
        if (TextUtils.isEmpty(apiKeyFromManifest)) {
            logErrorOrThrowException(context);
        }
        return apiKeyFromManifest;
    }

    protected String getApiKeyFromManifest(Context context) {
        Logger logger;
        String str;
        StringBuilder stringBuilder;
        String str2 = null;
        try {
            context = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (context == null) {
                return null;
            }
            String string = context.getString(FABRIC_API_KEY);
            if (string != null) {
                return string;
            }
            try {
                Fabric.getLogger().mo2494d(Fabric.TAG, "Falling back to Crashlytics key lookup from Manifest");
                return context.getString(CRASHLYTICS_API_KEY);
            } catch (Exception e) {
                context = e;
                str2 = string;
                logger = Fabric.getLogger();
                str = Fabric.TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Caught non-fatal exception while retrieving apiKey: ");
                stringBuilder.append(context);
                logger.mo2494d(str, stringBuilder.toString());
                return str2;
            }
        } catch (Exception e2) {
            context = e2;
            logger = Fabric.getLogger();
            str = Fabric.TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Caught non-fatal exception while retrieving apiKey: ");
            stringBuilder.append(context);
            logger.mo2494d(str, stringBuilder.toString());
            return str2;
        }
    }

    protected String getApiKeyFromStrings(Context context) {
        int resourcesIdentifier = CommonUtils.getResourcesIdentifier(context, FABRIC_API_KEY, "string");
        if (resourcesIdentifier == 0) {
            Fabric.getLogger().mo2494d(Fabric.TAG, "Falling back to Crashlytics key lookup from Strings");
            resourcesIdentifier = CommonUtils.getResourcesIdentifier(context, CRASHLYTICS_API_KEY, "string");
        }
        return resourcesIdentifier != 0 ? context.getResources().getString(resourcesIdentifier) : null;
    }

    protected void logErrorOrThrowException(Context context) {
        if (!Fabric.isDebuggable()) {
            if (CommonUtils.isAppDebuggable(context) == null) {
                Fabric.getLogger().mo2496e(Fabric.TAG, buildApiKeyInstructions());
                return;
            }
        }
        throw new IllegalArgumentException(buildApiKeyInstructions());
    }
}
