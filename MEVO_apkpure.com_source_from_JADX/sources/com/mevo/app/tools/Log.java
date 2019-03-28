package com.mevo.app.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public class Log {
    public static final int ASSERT = 8;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int EXCEPTION = 7;
    private static final int FULL = 1;
    public static final int INFO = 4;
    private static int LOGGING_LEVEL = 2;
    private static final String LOG_LEVEL = "log_level";
    private static final String LOG_LEVEL_BROADCAST_ACTION = "log_level_broadcast_action";
    public static final int NONE = 1000;
    private static final String SAVED_LOG_LEVEL = "saved_log_level";
    private static final String SHARED_PREFS_NAME = "secret.log_shared_prefs";
    private static final int SIMPLEST = 3;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static String baseTag = "";
    private static boolean debugMode = true;
    private static LogListener listener;

    public interface LogListener {
        boolean handleExc(int i, String str, String str2, Throwable th);

        boolean handleMsg(int i, String str, String str2);
    }

    public static void init(String str, boolean z) {
        baseTag = str;
        debugMode = z;
    }

    public static void setListener(LogListener logListener) {
        listener = logListener;
    }

    public static void setLogLevel(int i) {
        LOGGING_LEVEL = i;
    }

    public static void setDebugMode(boolean z) {
        debugMode = z;
    }

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static void handleMsg(int i, String str, String str2, Object... objArr) {
        if (debugMode && LOGGING_LEVEL <= i) {
            if (str2 != null) {
                if (str != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(baseTag);
                    stringBuilder.append(".");
                    stringBuilder.append(str);
                    str = stringBuilder.toString();
                } else {
                    str = baseTag;
                }
                if (objArr.length > 0) {
                    str2 = String.format(str2, objArr);
                }
                if (listener == null || i >= 7 || listener.handleMsg(i, str, str2) == null) {
                    switch (i) {
                        case 2:
                            android.util.Log.v(str, str2);
                            break;
                        case 3:
                            android.util.Log.d(str, str2);
                            break;
                        case 4:
                            android.util.Log.i(str, str2);
                            break;
                        case 5:
                            android.util.Log.w(str, str2);
                            break;
                        case 6:
                            android.util.Log.e(str, str2);
                            break;
                        case 8:
                            handleExc(8, str, str2, new AssertionError(str2));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public static void handleExc(int i, String str, String str2, Throwable th) {
        if (debugMode) {
            if (LOGGING_LEVEL <= 7) {
                StringBuilder stringBuilder;
                if (str != null) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(baseTag);
                    stringBuilder.append(".");
                    stringBuilder.append(str);
                    str = stringBuilder.toString();
                } else {
                    str = baseTag;
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
                if (listener != null) {
                    listener.handleExc(i, str, str2, th);
                }
                android.util.Log.e(str, str2);
                if (i == 1) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: v */
    public static void m99v(String str, String str2, Object... objArr) {
        handleMsg(2, str, str2, objArr);
    }

    /* renamed from: v */
    public static void m98v(String str, String str2) {
        handleMsg(2, str, str2, new Object[0]);
    }

    /* renamed from: v */
    public static void m97v(String str) {
        handleMsg(2, null, str, new Object[0]);
    }

    /* renamed from: v */
    public static void m100v(String str, Object... objArr) {
        handleMsg(2, null, str, objArr);
    }

    /* renamed from: d */
    public static void m87d(String str, String str2, Object... objArr) {
        handleMsg(3, str, str2, objArr);
    }

    /* renamed from: d */
    public static void m86d(String str, String str2) {
        handleMsg(3, str, str2, new Object[0]);
    }

    /* renamed from: d */
    public static void m85d(String str) {
        handleMsg(3, null, str, new Object[0]);
    }

    /* renamed from: d */
    public static void m88d(String str, Object... objArr) {
        handleMsg(3, null, str, objArr);
    }

    /* renamed from: i */
    public static void m95i(String str, String str2, Object... objArr) {
        handleMsg(4, str, str2, objArr);
    }

    /* renamed from: i */
    public static void m94i(String str, String str2) {
        handleMsg(4, str, str2, new Object[0]);
    }

    /* renamed from: i */
    public static void m93i(String str) {
        handleMsg(4, null, str, new Object[0]);
    }

    /* renamed from: i */
    public static void m96i(String str, Object... objArr) {
        handleMsg(4, null, str, objArr);
    }

    /* renamed from: w */
    public static void m103w(String str, String str2, Object... objArr) {
        handleMsg(5, str, str2, objArr);
    }

    /* renamed from: w */
    public static void m102w(String str, String str2) {
        handleMsg(5, str, str2, new Object[0]);
    }

    /* renamed from: w */
    public static void m101w(String str) {
        handleMsg(5, null, str, new Object[0]);
    }

    /* renamed from: w */
    public static void m104w(String str, Object... objArr) {
        handleMsg(5, null, str, objArr);
    }

    /* renamed from: e */
    public static void m91e(String str, String str2, Object... objArr) {
        handleMsg(6, str, str2, objArr);
    }

    /* renamed from: e */
    public static void m90e(String str, String str2) {
        handleMsg(6, str, str2, new Object[0]);
    }

    /* renamed from: e */
    public static void m89e(String str) {
        handleMsg(6, null, str, new Object[0]);
    }

    /* renamed from: e */
    public static void m92e(String str, Object... objArr) {
        handleMsg(6, null, str, objArr);
    }

    /* renamed from: a */
    public static void m83a(String str, String str2, Object... objArr) {
        handleMsg(8, str, str2, objArr);
    }

    /* renamed from: a */
    public static void m82a(String str, String str2) {
        handleMsg(8, str, str2, new Object[0]);
    }

    /* renamed from: a */
    public static void m81a(String str) {
        handleMsg(8, null, str, new Object[0]);
    }

    /* renamed from: a */
    public static void m84a(String str, Object... objArr) {
        handleMsg(8, null, str, objArr);
    }

    public static void ex(String str, String str2, Throwable th) {
        handleExc(1, str, str2, th);
    }

    public static void ex(String str, Throwable th) {
        handleExc(1, null, str, th);
    }

    public static void ex(Throwable th) {
        handleExc(1, null, null, th);
    }

    public static void exm(String str, String str2, Throwable th) {
        handleExc(3, str, str2, th);
    }

    public static void exm(String str, Throwable th) {
        handleExc(3, null, str, th);
    }

    public static void exm(Throwable th) {
        handleExc(3, null, null, th);
    }

    public static void registerHiddenActivationBroadcastReceiver(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, 0);
        int i = sharedPreferences.getInt(SAVED_LOG_LEVEL, -1);
        if (i != -1) {
            setDebugMode(true);
            setLogLevel(i);
        }
        context.getApplicationContext().registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                context = intent.getIntExtra(Log.LOG_LEVEL, 2);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Received set debug level intent, log level: ");
                stringBuilder.append(context);
                android.util.Log.i("Log", stringBuilder.toString());
                Log.setDebugMode(true);
                Log.setLogLevel(context);
                intent = sharedPreferences.edit();
                intent.putInt(Log.SAVED_LOG_LEVEL, context);
                intent.apply();
            }
        }, new IntentFilter(LOG_LEVEL_BROADCAST_ACTION));
    }
}
