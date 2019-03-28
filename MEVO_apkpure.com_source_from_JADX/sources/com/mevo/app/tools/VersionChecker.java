package com.mevo.app.tools;

import com.mevo.app.App;
import com.mevo.app.data.model.VersionCheck;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.shared_prefs.SharedPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VersionChecker {
    private static final String TAG = "VersionCheck";

    /* renamed from: com.mevo.app.tools.VersionChecker$1 */
    static class C08411 implements Callback<VersionCheck> {
        C08411() {
        }

        public void onResponse(Call<VersionCheck> call, Response<VersionCheck> response) {
            if (response.isSuccessful() != null && response.body() != null) {
                call = VersionChecker.getAppVersionNumber();
                if (call <= null || call >= ((VersionCheck) response.body()).getVersionCode()) {
                    Log.m94i(VersionChecker.TAG, "You have newest app version");
                }
                SharedPrefs.setCacheTimeListXml(((VersionCheck) response.body()).getCacheListTime());
            }
        }

        public void onFailure(Call<VersionCheck> call, Throwable th) {
            Log.m90e(VersionChecker.TAG, "Failed to fetch app version");
        }
    }

    public static void check() {
        Rest.getApiExtended().getCurrentAppVersion().enqueue(new C08411());
    }

    private static int getAppVersionNumber() {
        try {
            return App.getAppContext().getPackageManager().getPackageInfo(App.getAppContext().getPackageName(), 0).versionCode;
        } catch (Throwable e) {
            Log.ex(TAG, e);
            return -1;
        }
    }
}
