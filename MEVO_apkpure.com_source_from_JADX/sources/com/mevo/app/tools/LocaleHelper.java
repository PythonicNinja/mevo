package com.mevo.app.tools;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import com.mevo.app.constants.AppLanguageCode;
import java.util.Locale;

public class LocaleHelper {
    private static final String APP_LANGUAGE_CODE = "APP_LANGUAGE_CODE";
    private static final String SHARED_PREFS_NAME = "veturilo.tools.locale_helper";

    public static Context onAttach(Context context) {
        return setLocale(context, getLanguage(context));
    }

    public static String getLanguage(Context context) {
        context = getPrefs(context).getString(APP_LANGUAGE_CODE, Locale.getDefault().getLanguage());
        if (isAppLanguage(context)) {
            return context;
        }
        return AppLanguageCode.DEFAULT_LANGUAGE.getCode();
    }

    public static boolean isAppLanguage(String str) {
        for (AppLanguageCode code : AppLanguageCode.values()) {
            if (code.getCode().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(SHARED_PREFS_NAME, 0);
    }

    public static Context setLocale(Context context, String str) {
        persist(context, str);
        if (VERSION.SDK_INT >= 24) {
            return updateResources(context, str);
        }
        return updateResourcesLegacy(context, str);
    }

    public static boolean setLocaleIfCan(Context context, String str) {
        if (str == null || !canLanguageBeChanged(context, str)) {
            return false;
        }
        setLocale(context, str);
        return true;
    }

    public static boolean canLanguageBeChanged(Context context, String str) {
        return (isAppLanguage(str) && getLanguage(context).equalsIgnoreCase(str) == null) ? true : null;
    }

    private static void persist(Context context, String str) {
        context = getPrefs(context).edit();
        context.putString(APP_LANGUAGE_CODE, str);
        context.apply();
    }

    @TargetApi(24)
    private static Context updateResources(Context context, String str) {
        Locale locale = new Locale(str);
        Locale.setDefault(locale);
        str = context.getResources().getConfiguration();
        str.setLocale(locale);
        return context.createConfigurationContext(str);
    }

    private static Context updateResourcesLegacy(Context context, String str) {
        Locale locale = new Locale(str);
        Locale.setDefault(locale);
        str = context.getResources();
        Configuration configuration = str.getConfiguration();
        configuration.locale = locale;
        str.updateConfiguration(configuration, str.getDisplayMetrics());
        return context;
    }
}
