package io.fabric.sdk.android;

import android.util.Log;

public class DefaultLogger implements Logger {
    private int logLevel;

    public DefaultLogger(int i) {
        this.logLevel = i;
    }

    public DefaultLogger() {
        this.logLevel = 4;
    }

    public boolean isLoggable(String str, int i) {
        return this.logLevel <= i ? true : null;
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(int i) {
        this.logLevel = i;
    }

    /* renamed from: d */
    public void mo2495d(String str, String str2, Throwable th) {
        if (isLoggable(str, 3)) {
            Log.d(str, str2, th);
        }
    }

    /* renamed from: v */
    public void mo2506v(String str, String str2, Throwable th) {
        if (isLoggable(str, 2)) {
            Log.v(str, str2, th);
        }
    }

    /* renamed from: i */
    public void mo2500i(String str, String str2, Throwable th) {
        if (isLoggable(str, 4)) {
            Log.i(str, str2, th);
        }
    }

    /* renamed from: w */
    public void mo2508w(String str, String str2, Throwable th) {
        if (isLoggable(str, 5)) {
            Log.w(str, str2, th);
        }
    }

    /* renamed from: e */
    public void mo2497e(String str, String str2, Throwable th) {
        if (isLoggable(str, 6)) {
            Log.e(str, str2, th);
        }
    }

    /* renamed from: d */
    public void mo2494d(String str, String str2) {
        mo2495d(str, str2, null);
    }

    /* renamed from: v */
    public void mo2505v(String str, String str2) {
        mo2506v(str, str2, null);
    }

    /* renamed from: i */
    public void mo2499i(String str, String str2) {
        mo2500i(str, str2, null);
    }

    /* renamed from: w */
    public void mo2507w(String str, String str2) {
        mo2508w(str, str2, null);
    }

    /* renamed from: e */
    public void mo2496e(String str, String str2) {
        mo2497e(str, str2, null);
    }

    public void log(int i, String str, String str2) {
        log(i, str, str2, false);
    }

    public void log(int i, String str, String str2, boolean z) {
        if (z || isLoggable(str, i)) {
            Log.println(i, str, str2);
        }
    }
}
