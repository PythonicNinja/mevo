package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import org.json.JSONObject;

class DefaultCachedSettingsIo implements CachedSettingsIo {
    private final Kit kit;

    public DefaultCachedSettingsIo(Kit kit) {
        this.kit = kit;
    }

    public JSONObject readCachedSettings() {
        Throwable e;
        Fabric.getLogger().mo2494d(Fabric.TAG, "Reading cached settings...");
        Closeable closeable = null;
        Closeable fileInputStream;
        try {
            JSONObject jSONObject;
            File file = new File(new FileStoreImpl(this.kit).getFilesDir(), Settings.SETTINGS_CACHE_FILENAME);
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    jSONObject = new JSONObject(CommonUtils.streamToString(fileInputStream));
                    closeable = fileInputStream;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Fabric.getLogger().mo2497e(Fabric.TAG, "Failed to fetch cached settings", e);
                        CommonUtils.closeOrLog(fileInputStream, "Error while closing settings cache file.");
                        return null;
                    } catch (Throwable th) {
                        e = th;
                        closeable = fileInputStream;
                        CommonUtils.closeOrLog(closeable, "Error while closing settings cache file.");
                        throw e;
                    }
                }
            }
            Fabric.getLogger().mo2494d(Fabric.TAG, "No cached settings found.");
            jSONObject = null;
            CommonUtils.closeOrLog(closeable, "Error while closing settings cache file.");
            return jSONObject;
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            Fabric.getLogger().mo2497e(Fabric.TAG, "Failed to fetch cached settings", e);
            CommonUtils.closeOrLog(fileInputStream, "Error while closing settings cache file.");
            return null;
        } catch (Throwable th2) {
            e = th2;
            CommonUtils.closeOrLog(closeable, "Error while closing settings cache file.");
            throw e;
        }
    }

    public void writeCachedSettings(long j, JSONObject jSONObject) {
        Throwable e;
        Fabric.getLogger().mo2494d(Fabric.TAG, "Writing settings to cache file...");
        if (jSONObject != null) {
            Closeable closeable = null;
            try {
                jSONObject.put(SettingsJsonConstants.EXPIRES_AT_KEY, j);
                j = new FileWriter(new File(new FileStoreImpl(this.kit).getFilesDir(), Settings.SETTINGS_CACHE_FILENAME));
                try {
                    j.write(jSONObject.toString());
                    j.flush();
                    CommonUtils.closeOrLog(j, "Failed to close settings writer.");
                } catch (Exception e2) {
                    e = e2;
                    closeable = j;
                    try {
                        Fabric.getLogger().mo2497e(Fabric.TAG, "Failed to cache settings", e);
                        CommonUtils.closeOrLog(closeable, "Failed to close settings writer.");
                    } catch (Throwable th) {
                        e = th;
                        CommonUtils.closeOrLog(closeable, "Failed to close settings writer.");
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    closeable = j;
                    CommonUtils.closeOrLog(closeable, "Failed to close settings writer.");
                    throw e;
                }
            } catch (Exception e3) {
                e = e3;
                Fabric.getLogger().mo2497e(Fabric.TAG, "Failed to cache settings", e);
                CommonUtils.closeOrLog(closeable, "Failed to close settings writer.");
            }
        }
    }
}
