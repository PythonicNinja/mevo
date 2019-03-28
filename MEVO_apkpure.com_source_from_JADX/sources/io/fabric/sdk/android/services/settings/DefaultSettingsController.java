package io.fabric.sdk.android.services.settings;

import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import org.json.JSONException;
import org.json.JSONObject;

class DefaultSettingsController implements SettingsController {
    private static final String LOAD_ERROR_MESSAGE = "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.";
    private static final String PREFS_BUILD_INSTANCE_IDENTIFIER = "existing_instance_identifier";
    private final CachedSettingsIo cachedSettingsIo;
    private final CurrentTimeProvider currentTimeProvider;
    private final Kit kit;
    private final PreferenceStore preferenceStore = new PreferenceStoreImpl(this.kit);
    private final SettingsJsonTransform settingsJsonTransform;
    private final SettingsRequest settingsRequest;
    private final SettingsSpiCall settingsSpiCall;

    public DefaultSettingsController(Kit kit, SettingsRequest settingsRequest, CurrentTimeProvider currentTimeProvider, SettingsJsonTransform settingsJsonTransform, CachedSettingsIo cachedSettingsIo, SettingsSpiCall settingsSpiCall) {
        this.kit = kit;
        this.settingsRequest = settingsRequest;
        this.currentTimeProvider = currentTimeProvider;
        this.settingsJsonTransform = settingsJsonTransform;
        this.cachedSettingsIo = cachedSettingsIo;
        this.settingsSpiCall = settingsSpiCall;
    }

    public SettingsData loadSettingsData() {
        return loadSettingsData(SettingsCacheBehavior.USE_CACHE);
    }

    public SettingsData loadSettingsData(SettingsCacheBehavior settingsCacheBehavior) {
        SettingsCacheBehavior settingsCacheBehavior2 = null;
        try {
            if (!(Fabric.isDebuggable() || buildInstanceIdentifierChanged())) {
                settingsCacheBehavior2 = getCachedSettingsData(settingsCacheBehavior);
            }
            if (settingsCacheBehavior2 == null) {
                settingsCacheBehavior = this.settingsSpiCall.invoke(this.settingsRequest);
                if (settingsCacheBehavior != null) {
                    SettingsData buildFromJson = this.settingsJsonTransform.buildFromJson(this.currentTimeProvider, settingsCacheBehavior);
                    try {
                        this.cachedSettingsIo.writeCachedSettings(buildFromJson.expiresAtMillis, settingsCacheBehavior);
                        logSettings(settingsCacheBehavior, "Loaded settings: ");
                        setStoredBuildInstanceIdentifier(getBuildInstanceIdentifierFromContext());
                        settingsCacheBehavior2 = buildFromJson;
                    } catch (Exception e) {
                        settingsCacheBehavior = e;
                        settingsCacheBehavior2 = buildFromJson;
                        Fabric.getLogger().mo2497e(Fabric.TAG, LOAD_ERROR_MESSAGE, settingsCacheBehavior);
                        return settingsCacheBehavior2;
                    }
                }
            }
            if (settingsCacheBehavior2 == null) {
                return getCachedSettingsData(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
            }
        } catch (Exception e2) {
            settingsCacheBehavior = e2;
            Fabric.getLogger().mo2497e(Fabric.TAG, LOAD_ERROR_MESSAGE, settingsCacheBehavior);
            return settingsCacheBehavior2;
        }
        return settingsCacheBehavior2;
    }

    private SettingsData getCachedSettingsData(SettingsCacheBehavior settingsCacheBehavior) {
        SettingsData settingsData = null;
        try {
            if (SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(settingsCacheBehavior)) {
                return null;
            }
            JSONObject readCachedSettings = this.cachedSettingsIo.readCachedSettings();
            if (readCachedSettings != null) {
                SettingsData buildFromJson = this.settingsJsonTransform.buildFromJson(this.currentTimeProvider, readCachedSettings);
                if (buildFromJson != null) {
                    logSettings(readCachedSettings, "Loaded cached settings: ");
                    long currentTimeMillis = this.currentTimeProvider.getCurrentTimeMillis();
                    if (SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(settingsCacheBehavior) == null) {
                        if (buildFromJson.isExpired(currentTimeMillis) != null) {
                            Fabric.getLogger().mo2494d(Fabric.TAG, "Cached settings have expired.");
                            return null;
                        }
                    }
                    try {
                        Fabric.getLogger().mo2494d(Fabric.TAG, "Returning cached settings.");
                        return buildFromJson;
                    } catch (Exception e) {
                        settingsCacheBehavior = e;
                        settingsData = buildFromJson;
                        Fabric.getLogger().mo2497e(Fabric.TAG, "Failed to get cached settings", settingsCacheBehavior);
                        return settingsData;
                    }
                }
                Fabric.getLogger().mo2497e(Fabric.TAG, "Failed to transform cached settings data.", null);
                return null;
            }
            Fabric.getLogger().mo2494d(Fabric.TAG, "No cached settings data found.");
            return null;
        } catch (Exception e2) {
            settingsCacheBehavior = e2;
            Fabric.getLogger().mo2497e(Fabric.TAG, "Failed to get cached settings", settingsCacheBehavior);
            return settingsData;
        }
    }

    private void logSettings(JSONObject jSONObject, String str) throws JSONException {
        Logger logger = Fabric.getLogger();
        String str2 = Fabric.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(jSONObject.toString());
        logger.mo2494d(str2, stringBuilder.toString());
    }

    String getBuildInstanceIdentifierFromContext() {
        return CommonUtils.createInstanceIdFrom(CommonUtils.resolveBuildId(this.kit.getContext()));
    }

    String getStoredBuildInstanceIdentifier() {
        return this.preferenceStore.get().getString(PREFS_BUILD_INSTANCE_IDENTIFIER, "");
    }

    @SuppressLint({"CommitPrefEdits"})
    boolean setStoredBuildInstanceIdentifier(String str) {
        Editor edit = this.preferenceStore.edit();
        edit.putString(PREFS_BUILD_INSTANCE_IDENTIFIER, str);
        return this.preferenceStore.save(edit);
    }

    boolean buildInstanceIdentifierChanged() {
        return getStoredBuildInstanceIdentifier().equals(getBuildInstanceIdentifierFromContext()) ^ 1;
    }
}
