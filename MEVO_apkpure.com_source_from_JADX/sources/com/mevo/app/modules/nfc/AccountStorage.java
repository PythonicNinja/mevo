package com.mevo.app.modules.nfc;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

public class AccountStorage {
    private static final String PREF_ACCOUNT_NUMBER = "account_number";
    private static final String TAG = "AccountStorage";
    private static String sAccount;
    private static final Object sAccountLock = new Object();

    public static void SetAccount(Context context, String str) {
        synchronized (sAccountLock) {
            String str2 = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Setting account number: ");
            stringBuilder.append(str);
            Log.i(str2, stringBuilder.toString());
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PREF_ACCOUNT_NUMBER, str).commit();
            sAccount = str;
        }
    }

    public static String GetAccount(Context context) {
        synchronized (sAccountLock) {
            if (sAccount == null) {
                sAccount = PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_ACCOUNT_NUMBER, null);
            }
            context = sAccount;
        }
        return context;
    }
}
