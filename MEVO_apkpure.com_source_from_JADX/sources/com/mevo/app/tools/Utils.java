package com.mevo.app.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.annotation.RawRes;
import android.text.SpannableString;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.tools.Tools.Stream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Utils {
    private static final String TAG = "Utils";

    public static class SpanUtil {
        public static SpannableString setSpan(String str, String str2, Object obj) {
            SpannableString spannableString = new SpannableString(str);
            str = str.indexOf(str2);
            spannableString.setSpan(obj, str, str2.length() + str, 34);
            return spannableString;
        }

        public static SpannableString setSpan(SpannableString spannableString, String str, String str2, Object obj) {
            str = str.indexOf(str2);
            spannableString.setSpan(obj, str, str2.length() + str, 34);
            return spannableString;
        }
    }

    public static int boolToInt(boolean z) {
        return z ? 1 : 0;
    }

    public static String emptyIfNull(String str) {
        return str == null ? "" : str;
    }

    public static boolean intToBool(int i) {
        return i == 1;
    }

    public static boolean isFLashlightAvailable() {
        return App.getAppContext().getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    public static String getRawRes(@RawRes int i, Context context) {
        Closeable closeable = null;
        Closeable byteArrayOutputStream;
        try {
            i = context.getResources().openRawResource(i);
            try {
                context = new byte[null];
                context = new byte[i.available()];
                i.read(context);
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byteArrayOutputStream.write(context);
                    byteArrayOutputStream.close();
                    i.close();
                    context = byteArrayOutputStream.toString();
                    Stream.close(i);
                    Stream.close(byteArrayOutputStream);
                    return context;
                } catch (IOException e) {
                    context = e;
                    closeable = i;
                    try {
                        Log.ex(TAG, context);
                        i = "";
                        Stream.close(closeable);
                        Stream.close(byteArrayOutputStream);
                        return i;
                    } catch (Throwable th) {
                        context = th;
                        i = closeable;
                        closeable = byteArrayOutputStream;
                        Stream.close(i);
                        Stream.close(closeable);
                        throw context;
                    }
                } catch (Throwable th2) {
                    context = th2;
                    closeable = byteArrayOutputStream;
                    Stream.close(i);
                    Stream.close(closeable);
                    throw context;
                }
            } catch (IOException e2) {
                context = e2;
                byteArrayOutputStream = null;
                closeable = i;
                Log.ex(TAG, context);
                i = "";
                Stream.close(closeable);
                Stream.close(byteArrayOutputStream);
                return i;
            } catch (Throwable th3) {
                context = th3;
                Stream.close(i);
                Stream.close(closeable);
                throw context;
            }
        } catch (IOException e3) {
            context = e3;
            byteArrayOutputStream = null;
            Log.ex(TAG, context);
            i = "";
            Stream.close(closeable);
            Stream.close(byteArrayOutputStream);
            return i;
        } catch (Throwable th4) {
            context = th4;
            i = 0;
            Stream.close(i);
            Stream.close(closeable);
            throw context;
        }
    }

    public static boolean hasTimePassed(long j, long j2) {
        return j + j2 < System.currentTimeMillis() ? 1 : 0;
    }

    public static boolean isSameDay(long j, long j2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(new Date(j)).equals(simpleDateFormat.format(new Date(j2)));
    }

    public static void launchBrowser(Context context, String str) {
        if (URLUtil.isValidUrl(str)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            if (context.getPackageManager().queryIntentActivities(intent, 0).size() > null) {
                context.startActivity(intent);
                return;
            } else {
                TopToast.show(C0434R.string.error_cant_find_appropiate_browser, 0, TopToast.DURATION_SHORT);
                return;
            }
        }
        TopToast.show(C0434R.string.error_cant_open_in_browser, 0, TopToast.DURATION_SHORT);
    }

    public static String replaceIfNullOrEmpty(String str, String str2) {
        if (str != null) {
            if (!str.isEmpty()) {
                return str;
            }
        }
        return str2;
    }

    public static <T> boolean areListEqual(List<T> list, List<T> list2) {
        if (list == null && list2 == null) {
            return true;
        }
        if (list != null) {
            if (list2 != null) {
                if (list != list2) {
                    if (!list.isEmpty() || !list2.isEmpty()) {
                        if (list.size() != list2.size()) {
                            return false;
                        }
                        return new ArrayList(list).retainAll(list2) ^ 1;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean isLocationEnabled(Context context) {
        boolean z = false;
        if (context == null) {
            return false;
        }
        if (VERSION.SDK_INT < 19) {
            return TextUtils.isEmpty(Secure.getString(context.getContentResolver(), "location_providers_allowed")) ^ 1;
        }
        try {
            if (Secure.getInt(context.getContentResolver(), "location_mode") != null) {
                z = true;
            }
            return z;
        } catch (Context context2) {
            Log.ex(context2);
            return false;
        }
    }

    public static long getStartOfDayTimestampSeconds() {
        return getStartOfDayTimestampSecondsForTimestampUTC(System.currentTimeMillis() / 1000);
    }

    public static long getStartOfDayTimestampSecondsForTimestampUTC(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getTimeZone("UTC"));
        instance.setTimeInMillis(j * 1000);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis() / 1000;
    }
}
