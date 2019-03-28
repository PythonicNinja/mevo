package io.fabric.sdk.android.services.persistence;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Environment;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.io.File;

public class FileStoreImpl implements FileStore {
    private final String contentPath;
    private final Context context;
    private final String legacySupport;

    public FileStoreImpl(Kit kit) {
        if (kit.getContext() == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.context = kit.getContext();
        this.contentPath = kit.getPath();
        kit = new StringBuilder();
        kit.append("Android/");
        kit.append(this.context.getPackageName());
        this.legacySupport = kit.toString();
    }

    public File getCacheDir() {
        return prepare(this.context.getCacheDir());
    }

    public File getExternalCacheDir() {
        File file;
        if (!isExternalStorageAvailable()) {
            file = null;
        } else if (VERSION.SDK_INT >= 8) {
            file = this.context.getExternalCacheDir();
        } else {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.legacySupport);
            stringBuilder.append("/cache/");
            stringBuilder.append(this.contentPath);
            file = new File(externalStorageDirectory, stringBuilder.toString());
        }
        return prepare(file);
    }

    public File getFilesDir() {
        return prepare(this.context.getFilesDir());
    }

    @TargetApi(8)
    public File getExternalFilesDir() {
        File file = null;
        if (isExternalStorageAvailable()) {
            if (VERSION.SDK_INT >= 8) {
                file = this.context.getExternalFilesDir(null);
            } else {
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.legacySupport);
                stringBuilder.append("/files/");
                stringBuilder.append(this.contentPath);
                file = new File(externalStorageDirectory, stringBuilder.toString());
            }
        }
        return prepare(file);
    }

    File prepare(File file) {
        if (file != null) {
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    Fabric.getLogger().mo2507w(Fabric.TAG, "Couldn't create file");
                }
            }
            return file;
        }
        Fabric.getLogger().mo2494d(Fabric.TAG, "Null File");
        return null;
    }

    boolean isExternalStorageAvailable() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return true;
        }
        Fabric.getLogger().mo2507w(Fabric.TAG, "External Storage is not mounted and/or writable\nHave you declared android.permission.WRITE_EXTERNAL_STORAGE in the manifest?");
        return false;
    }
}
