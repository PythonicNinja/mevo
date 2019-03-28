package com.mevo.app.data.network.cache;

import android.support.v4.media.session.PlaybackStateCompat;
import com.inverce.mod.core.IM;
import okhttp3.Cache;
import okhttp3.OkHttpClient.Builder;

public class SmartCache {
    Cache cacheImpl;
    String defaultControl = "no-cache";
    boolean reorderGetParameters = true;
    long sizeMb;

    public SmartCache(long j) {
        this.sizeMb = j;
        this.cacheImpl = new Cache(IM.context().getCacheDir(), (j * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
    }

    public void applyTo(Builder builder) {
        builder.cache(this.cacheImpl);
        if (this.reorderGetParameters) {
            builder.addInterceptor(new ReorderGetParametersInterceptor(this));
        }
        builder.addNetworkInterceptor(new ApplyCacheConfigInterceptor(this));
        builder.addInterceptor(new ApplyCacheConfigInterceptor(this));
    }

    public String getDefaultControl() {
        return this.defaultControl;
    }
}
