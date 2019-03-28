package io.fabric.sdk.android.services.settings;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.CommonUtils;

public class IconRequest {
    public final String hash;
    public final int height;
    public final int iconResourceId;
    public final int width;

    public IconRequest(String str, int i, int i2, int i3) {
        this.hash = str;
        this.iconResourceId = i;
        this.width = i2;
        this.height = i3;
    }

    public static IconRequest build(Context context, String str) {
        if (str != null) {
            try {
                int appIconResourceId = CommonUtils.getAppIconResourceId(context);
                Logger logger = Fabric.getLogger();
                String str2 = Fabric.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("App icon resource ID is ");
                stringBuilder.append(appIconResourceId);
                logger.mo2494d(str2, stringBuilder.toString());
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(context.getResources(), appIconResourceId, options);
                return new IconRequest(str, appIconResourceId, options.outWidth, options.outHeight);
            } catch (Context context2) {
                Fabric.getLogger().mo2497e(Fabric.TAG, "Failed to load icon", context2);
            }
        }
        return null;
    }
}
