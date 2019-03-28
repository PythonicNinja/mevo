package io.fabric.sdk.android;

public interface Logger {
    /* renamed from: d */
    void mo2494d(String str, String str2);

    /* renamed from: d */
    void mo2495d(String str, String str2, Throwable th);

    /* renamed from: e */
    void mo2496e(String str, String str2);

    /* renamed from: e */
    void mo2497e(String str, String str2, Throwable th);

    int getLogLevel();

    /* renamed from: i */
    void mo2499i(String str, String str2);

    /* renamed from: i */
    void mo2500i(String str, String str2, Throwable th);

    boolean isLoggable(String str, int i);

    void log(int i, String str, String str2);

    void log(int i, String str, String str2, boolean z);

    void setLogLevel(int i);

    /* renamed from: v */
    void mo2505v(String str, String str2);

    /* renamed from: v */
    void mo2506v(String str, String str2, Throwable th);

    /* renamed from: w */
    void mo2507w(String str, String str2);

    /* renamed from: w */
    void mo2508w(String str, String str2, Throwable th);
}
