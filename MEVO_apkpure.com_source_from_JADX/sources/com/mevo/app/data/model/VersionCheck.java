package com.mevo.app.data.model;

import com.google.gson.annotations.SerializedName;

public class VersionCheck {
    @SerializedName("android_achievements_release_date")
    private long achievementReleaseDate;
    @SerializedName("cache_listxml")
    private long cacheListTime;
    @SerializedName("citi_version_android")
    private int versionCode;

    public long getAchievementReleaseDate() {
        return this.achievementReleaseDate;
    }

    public void setAchievementReleaseDate(long j) {
        this.achievementReleaseDate = j;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public long getCacheListTime() {
        return this.cacheListTime;
    }

    public VersionCheck setVersionCode(int i) {
        this.versionCode = i;
        return this;
    }
}
