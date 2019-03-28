package com.raizlabs.android.dbflow.config;

import android.os.Build.VERSION;
import android.util.Log;

public class FlowLog {
    public static final String TAG = "FlowLog";
    private static Level level = Level.E;

    public enum Level {
        V {
            void call(String str, String str2, Throwable th) {
                Log.v(str, str2, th);
            }
        },
        D {
            void call(String str, String str2, Throwable th) {
                Log.d(str, str2, th);
            }
        },
        I {
            void call(String str, String str2, Throwable th) {
                Log.i(str, str2, th);
            }
        },
        W {
            void call(String str, String str2, Throwable th) {
                Log.w(str, str2, th);
            }
        },
        E {
            void call(String str, String str2, Throwable th) {
                Log.e(str, str2, th);
            }
        },
        WTF {
            void call(String str, String str2, Throwable th) {
                if (VERSION.SDK_INT >= 8) {
                    Log.wtf(str, str2, th);
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("!!!!!!!!*******");
                stringBuilder.append(str2);
                stringBuilder.append("********!!!!!!");
                Log.e(str, stringBuilder.toString(), th);
            }
        };

        abstract void call(String str, String str2, Throwable th);
    }

    public static void setMinimumLoggingLevel(Level level) {
        level = level;
    }

    public static void log(Level level, String str) {
        log(level, str, null);
    }

    public static void log(Level level, String str, Throwable th) {
        log(level, TAG, str, th);
    }

    public static void log(Level level, String str, String str2, Throwable th) {
        if (isEnabled(level)) {
            level.call(str, str2, th);
        }
    }

    public static boolean isEnabled(Level level) {
        return level.ordinal() >= level.ordinal() ? true : null;
    }

    public static void logError(Throwable th) {
        log(Level.E, th);
    }

    public static void log(Level level, Throwable th) {
        log(level, TAG, "", th);
    }
}
