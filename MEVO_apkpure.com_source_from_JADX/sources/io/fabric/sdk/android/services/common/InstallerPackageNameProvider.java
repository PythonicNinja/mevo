package io.fabric.sdk.android.services.common;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.cache.MemoryValueCache;
import io.fabric.sdk.android.services.cache.ValueLoader;

public class InstallerPackageNameProvider {
    private static final String NO_INSTALLER_PACKAGE_NAME = "";
    private final MemoryValueCache<String> installerPackageNameCache = new MemoryValueCache();
    private final ValueLoader<String> installerPackageNameLoader = new C08881();

    /* renamed from: io.fabric.sdk.android.services.common.InstallerPackageNameProvider$1 */
    class C08881 implements ValueLoader<String> {
        C08881() {
        }

        public String load(Context context) throws Exception {
            context = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            return context == null ? "" : context;
        }
    }

    public String getInstallerPackageName(Context context) {
        try {
            context = (String) this.installerPackageNameCache.get(context, this.installerPackageNameLoader);
            if ("".equals(context)) {
                context = null;
            }
            return context;
        } catch (Context context2) {
            Fabric.getLogger().mo2497e(Fabric.TAG, "Failed to determine installer package name", context2);
            return null;
        }
    }
}
