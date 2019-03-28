package com.inverce.mod.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StringRes;
import com.inverce.mod.core.interfaces.LogListener;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public class Log {
    public static final int ASSERT = 8;
    private static final String BASE_TAG = "||";
    public static final int DEBUG = 3;
    private static boolean DEBUG_MODE = true;
    public static final int ERROR = 6;
    public static final int EXCEPTION = 7;
    private static final int FULL = 1;
    public static final int INFO = 4;
    private static int LOGGING_LEVEL = 2;
    public static final int NONE = Integer.MAX_VALUE;
    private static final int SIMPLER = 2;
    private static final int SIMPLEST = 3;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static String applicationPackage = null;
    @NonNull
    private static String libraryPackage = "com.inverce.mod";
    private static LogListener listener;

    public static void setListener(LogListener logListener) {
        listener = logListener;
    }

    public static void setLogLevel(int i) {
        LOGGING_LEVEL = i;
    }

    public static void setDebugMode(boolean z) {
        DEBUG_MODE = z;
    }

    public static boolean isLoggable(int i) {
        return DEBUG_MODE && LOGGING_LEVEL <= i;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    static boolean shouldPrint(@NonNull String str) {
        if (applicationPackage == null) {
            applicationPackage = IM.context().getPackageName();
        }
        if (!str.startsWith(libraryPackage)) {
            if (str.contains(applicationPackage) == null) {
                return null;
            }
        }
        return true;
    }

    private static String getString(@StringRes int i, Object... objArr) {
        return IM.resources().getString(i, objArr);
    }

    public static void handleMsg(int i, int i2, int i3, Object... objArr) {
        if (DEBUG_MODE) {
            if (LOGGING_LEVEL <= i) {
                if (i2 == -1) {
                    handleMsg(i, (String) 0, getString(i3, objArr), new Object[0]);
                } else {
                    handleMsg(i, getString(i2, new Object[0]), getString(i3, objArr), new Object[0]);
                }
            }
        }
    }

    public static void handleMsg(int i, String str, @Nullable String str2, @NonNull Object... objArr) {
        if (DEBUG_MODE && LOGGING_LEVEL <= i) {
            if (str2 != null) {
                if (str != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("||.");
                    stringBuilder.append(str);
                    str = stringBuilder.toString();
                } else {
                    str = "||";
                }
                if (objArr.length > 0) {
                    str2 = String.format(str2, objArr);
                }
                if (listener == null || i >= 7 || listener.handleMsg(i, str, str2) == null) {
                    dispatchMessage(i, str, str2);
                }
            }
        }
    }

    private static void dispatchMessage(int i, String str, String str2) {
        switch (i) {
            case 2:
                android.util.Log.v(str, str2);
                return;
            case 3:
                android.util.Log.d(str, str2);
                return;
            case 4:
                android.util.Log.i(str, str2);
                return;
            case 5:
                android.util.Log.w(str, str2);
                return;
            case 6:
                android.util.Log.e(str, str2);
                return;
            case 8:
                handleExc(8, str, str2, new AssertionError(str2));
                return;
            default:
                return;
        }
    }

    public static void handleExc(int i, int i2, int i3, @NonNull Throwable th) {
        if (DEBUG_MODE) {
            if (LOGGING_LEVEL <= i) {
                if (i2 == -1) {
                    handleExc(i, (String) 0, getString(i3, new Object[0]), th);
                } else {
                    handleExc(i, getString(i2, new Object[0]), getString(i3, new Object[0]), th);
                }
            }
        }
    }

    public static void handleExc(int i, String str, String str2, @NonNull Throwable th) {
        if (DEBUG_MODE) {
            if (LOGGING_LEVEL <= 7) {
                StringBuilder stringBuilder;
                if (listener != null) {
                    listener.handleExc(i, str, str2, th);
                }
                if (str != null) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("||.");
                    stringBuilder.append(str);
                    str = stringBuilder.toString();
                } else {
                    str = "||";
                }
                stringBuilder = new StringBuilder();
                if (str2 != null) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str2);
                    stringBuilder2.append(": ");
                    str2 = stringBuilder2.toString();
                } else {
                    str2 = "";
                }
                stringBuilder.append(str2);
                stringBuilder.append(Operation.LESS_THAN);
                stringBuilder.append(th.getClass().getSimpleName());
                stringBuilder.append("> ");
                stringBuilder.append(th.getMessage());
                str2 = stringBuilder.toString();
                android.util.Log.e(str, str2);
                if (i != 1) {
                    android.util.Log.w("", str2);
                    for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                        if (shouldPrint(stackTraceElement.getClassName())) {
                            android.util.Log.w(str, stackTraceElement.toString());
                        }
                    }
                    if (th.getCause() != 0) {
                        i = new StringBuilder();
                        i.append("Caused by: <");
                        i.append(th.getCause().getClass().getSimpleName());
                        i.append("> ");
                        i.append(th.getCause().getMessage());
                        android.util.Log.w(str, i.toString());
                    }
                } else {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: v */
    public static void m62v(String str, String str2, Object... objArr) {
        handleMsg(2, str, str2, objArr);
    }

    /* renamed from: v */
    public static void m61v(String str, String str2) {
        handleMsg(2, str, str2, new Object[0]);
    }

    /* renamed from: v */
    public static void m60v(String str) {
        handleMsg(2, null, str, new Object[0]);
    }

    /* renamed from: v */
    public static void m63v(String str, Object... objArr) {
        handleMsg(2, null, str, objArr);
    }

    /* renamed from: d */
    public static void m38d(String str, String str2, Object... objArr) {
        handleMsg(3, str, str2, objArr);
    }

    /* renamed from: d */
    public static void m37d(String str, String str2) {
        handleMsg(3, str, str2, new Object[0]);
    }

    /* renamed from: d */
    public static void m36d(String str) {
        handleMsg(3, null, str, new Object[0]);
    }

    /* renamed from: d */
    public static void m39d(String str, Object... objArr) {
        handleMsg(3, null, str, objArr);
    }

    /* renamed from: i */
    public static void m54i(String str, String str2, Object... objArr) {
        handleMsg(4, str, str2, objArr);
    }

    /* renamed from: i */
    public static void m53i(String str, String str2) {
        handleMsg(4, str, str2, new Object[0]);
    }

    /* renamed from: i */
    public static void m52i(String str) {
        handleMsg(4, null, str, new Object[0]);
    }

    /* renamed from: i */
    public static void m55i(String str, Object... objArr) {
        handleMsg(4, null, str, objArr);
    }

    /* renamed from: w */
    public static void m70w(String str, String str2, Object... objArr) {
        handleMsg(5, str, str2, objArr);
    }

    /* renamed from: w */
    public static void m69w(String str, String str2) {
        handleMsg(5, str, str2, new Object[0]);
    }

    /* renamed from: w */
    public static void m68w(String str) {
        handleMsg(5, null, str, new Object[0]);
    }

    /* renamed from: w */
    public static void m71w(String str, Object... objArr) {
        handleMsg(5, null, str, objArr);
    }

    /* renamed from: e */
    public static void m46e(String str, String str2, Object... objArr) {
        handleMsg(6, str, str2, objArr);
    }

    /* renamed from: e */
    public static void m45e(String str, String str2) {
        handleMsg(6, str, str2, new Object[0]);
    }

    /* renamed from: e */
    public static void m44e(String str) {
        handleMsg(6, null, str, new Object[0]);
    }

    /* renamed from: e */
    public static void m47e(String str, Object... objArr) {
        handleMsg(6, null, str, objArr);
    }

    /* renamed from: a */
    public static void m30a(String str, String str2, Object... objArr) {
        handleMsg(8, str, str2, objArr);
    }

    /* renamed from: a */
    public static void m29a(String str, String str2) {
        handleMsg(8, str, str2, new Object[0]);
    }

    /* renamed from: a */
    public static void m28a(String str) {
        handleMsg(8, null, str, new Object[0]);
    }

    /* renamed from: a */
    public static void m31a(String str, Object... objArr) {
        handleMsg(8, null, str, objArr);
    }

    /* renamed from: v */
    public static void m58v(@StringRes int i, @StringRes int i2, Object... objArr) {
        handleMsg(2, i, i2, objArr);
    }

    /* renamed from: v */
    public static void m57v(@StringRes int i, @StringRes int i2) {
        handleMsg(2, i, i2, new Object[0]);
    }

    /* renamed from: v */
    public static void m56v(@StringRes int i) {
        handleMsg(2, -1, i, new Object[0]);
    }

    /* renamed from: v */
    public static void m59v(@StringRes int i, Object... objArr) {
        handleMsg(2, -1, i, objArr);
    }

    /* renamed from: d */
    public static void m34d(@StringRes int i, @StringRes int i2, Object... objArr) {
        handleMsg(3, i, i2, objArr);
    }

    /* renamed from: d */
    public static void m33d(@StringRes int i, @StringRes int i2) {
        handleMsg(3, i, i2, new Object[0]);
    }

    /* renamed from: d */
    public static void m32d(@StringRes int i) {
        handleMsg(3, -1, i, new Object[0]);
    }

    /* renamed from: d */
    public static void m35d(@StringRes int i, Object... objArr) {
        handleMsg(3, -1, i, objArr);
    }

    /* renamed from: i */
    public static void m50i(@StringRes int i, @StringRes int i2, Object... objArr) {
        handleMsg(4, i, i2, objArr);
    }

    /* renamed from: i */
    public static void m49i(@StringRes int i, @StringRes int i2) {
        handleMsg(4, i, i2, new Object[0]);
    }

    /* renamed from: i */
    public static void m48i(@StringRes int i) {
        handleMsg(4, -1, i, new Object[0]);
    }

    /* renamed from: i */
    public static void m51i(@StringRes int i, Object... objArr) {
        handleMsg(4, -1, i, objArr);
    }

    /* renamed from: w */
    public static void m66w(@StringRes int i, @StringRes int i2, Object... objArr) {
        handleMsg(5, i, i2, objArr);
    }

    /* renamed from: w */
    public static void m65w(@StringRes int i, @StringRes int i2) {
        handleMsg(5, i, i2, new Object[0]);
    }

    /* renamed from: w */
    public static void m64w(@StringRes int i) {
        handleMsg(5, -1, i, new Object[0]);
    }

    /* renamed from: w */
    public static void m67w(@StringRes int i, Object... objArr) {
        handleMsg(5, -1, i, objArr);
    }

    /* renamed from: e */
    public static void m42e(@StringRes int i, @StringRes int i2, Object... objArr) {
        handleMsg(6, i, i2, objArr);
    }

    /* renamed from: e */
    public static void m41e(@StringRes int i, @StringRes int i2) {
        handleMsg(6, i, i2, new Object[0]);
    }

    /* renamed from: e */
    public static void m40e(@StringRes int i) {
        handleMsg(6, -1, i, new Object[0]);
    }

    /* renamed from: e */
    public static void m43e(@StringRes int i, Object... objArr) {
        handleMsg(6, -1, i, objArr);
    }

    /* renamed from: a */
    public static void m26a(@StringRes int i, @StringRes int i2, Object... objArr) {
        handleMsg(8, i, i2, objArr);
    }

    /* renamed from: a */
    public static void m25a(@StringRes int i, @StringRes int i2) {
        handleMsg(8, i, i2, new Object[0]);
    }

    /* renamed from: a */
    public static void m24a(@StringRes int i) {
        handleMsg(8, -1, i, new Object[0]);
    }

    /* renamed from: a */
    public static void m27a(@StringRes int i, Object... objArr) {
        handleMsg(8, -1, i, objArr);
    }

    public static void ex(@NonNull Throwable th) {
        handleExc(1, null, null, th);
    }

    public static void exs(@NonNull Throwable th) {
        handleExc(2, null, null, th);
    }

    public static void exm(@NonNull Throwable th) {
        handleExc(3, null, null, th);
    }

    public static void ex(String str, String str2, @NonNull Throwable th) {
        handleExc(1, str, str2, th);
    }

    public static void ex(String str, @NonNull Throwable th) {
        handleExc(1, null, str, th);
    }

    public static void exs(String str, String str2, @NonNull Throwable th) {
        handleExc(2, str, str2, th);
    }

    public static void exs(String str, @NonNull Throwable th) {
        handleExc(2, null, str, th);
    }

    public static void exm(String str, String str2, @NonNull Throwable th) {
        handleExc(3, str, str2, th);
    }

    public static void exm(String str, @NonNull Throwable th) {
        handleExc(3, null, str, th);
    }

    public static void ex(@StringRes int i, @StringRes int i2, @NonNull Throwable th) {
        handleExc(1, i, i2, th);
    }

    public static void ex(@StringRes int i, @NonNull Throwable th) {
        handleExc(1, -1, i, th);
    }

    public static void exs(@StringRes int i, @StringRes int i2, @NonNull Throwable th) {
        handleExc(2, i, i2, th);
    }

    public static void exs(@StringRes int i, @NonNull Throwable th) {
        handleExc(2, -1, i, th);
    }

    public static void exm(@StringRes int i, @StringRes int i2, @NonNull Throwable th) {
        handleExc(3, i, i2, th);
    }

    public static void exm(@StringRes int i, @NonNull Throwable th) {
        handleExc(3, -1, i, th);
    }
}
