package com.mevo.app.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;

public class Permissions {
    public static final int CODE_CAMERA = 2;
    public static final int CODE_EXTERNAL_STORAGE = 3;
    public static final int CODE_LOCATION = 1;
    public static final String[] PERMISSIONS_CAMERA = new String[]{"android.permission.CAMERA"};
    public static final String[] PERMISSIONS_LOCATION = new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static final String SHARED_PREFS_NAME = "tools.permissions.shared_prefs";

    @SuppressLint({"NewApi"})
    public static boolean requestIfShould(String[] strArr, int i, Activity activity) {
        if (activity != null) {
            if (isSdkVersionWithRuntimePermissions()) {
                strArr = filterBlockedByUser(getMissingPermissions(strArr, activity), activity);
                if (strArr.length <= 0) {
                    return false;
                }
                ActivityCompat.requestPermissions(activity, strArr, i);
                return 1;
            }
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public static boolean requestIfShould(String[] strArr, int i, Fragment fragment) {
        if (fragment != null) {
            if (isSdkVersionWithRuntimePermissions()) {
                strArr = filterBlockedByUser(getMissingPermissions(strArr, fragment.getActivity()), fragment.getActivity());
                if (strArr.length <= 0) {
                    return false;
                }
                fragment.requestPermissions(strArr, i);
                return 1;
            }
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    public static String[] getMissingPermissions(String[] strArr, Activity activity) {
        int i = 0;
        if (activity != null) {
            if (isSdkVersionWithRuntimePermissions()) {
                ArrayList arrayList = new ArrayList();
                int length = strArr.length;
                while (i < length) {
                    String str = strArr[i];
                    if (ContextCompat.checkSelfPermission(activity, str) != 0) {
                        arrayList.add(str);
                    }
                    i++;
                }
                strArr = new String[arrayList.size()];
                arrayList.toArray(strArr);
                return strArr;
            }
        }
        return new String[0];
    }

    @SuppressLint({"NewApi"})
    public static boolean hasPermissions(String[] strArr, Activity activity) {
        if (!isSdkVersionWithRuntimePermissions()) {
            return true;
        }
        if (activity == null) {
            return false;
        }
        for (String checkSelfPermission : strArr) {
            if (ContextCompat.checkSelfPermission(activity, checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    public static String[] filterBlockedByUser(String[] strArr, Activity activity) {
        if (activity != null) {
            if (isSdkVersionWithRuntimePermissions()) {
                ArrayList arrayList = new ArrayList();
                SharedPreferences sharedPrefs = getSharedPrefs(activity);
                for (String str : strArr) {
                    if (activity.shouldShowRequestPermissionRationale(str)) {
                        arrayList.add(str);
                    } else if (sharedPrefs.getBoolean(str, true)) {
                        arrayList.add(str);
                        sharedPrefs.edit().putBoolean(str, false).apply();
                    }
                }
                strArr = new String[arrayList.size()];
                arrayList.toArray(strArr);
                return strArr;
            }
        }
        return new String[0];
    }

    @SuppressLint({"NewApi"})
    public static String[] getBlockedByUser(String[] strArr, Activity activity) {
        ArrayList arrayList = new ArrayList();
        SharedPreferences sharedPrefs = getSharedPrefs(activity);
        for (String str : getMissingPermissions(strArr, activity)) {
            boolean shouldShowRequestPermissionRationale = activity.shouldShowRequestPermissionRationale(str);
            boolean z = sharedPrefs.getBoolean(str, true);
            if (!(shouldShowRequestPermissionRationale || z)) {
                arrayList.add(str);
            }
        }
        strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }

    private static boolean isSdkVersionWithRuntimePermissions() {
        return VERSION.SDK_INT >= 23;
    }

    private static SharedPreferences getSharedPrefs(Context context) {
        return context.getApplicationContext().getSharedPreferences(SHARED_PREFS_NAME, 0);
    }

    public static boolean hasUserBlockedAny(String[] strArr, Activity activity) {
        return getBlockedByUser(strArr, activity).length > null ? 1 : null;
    }
}
