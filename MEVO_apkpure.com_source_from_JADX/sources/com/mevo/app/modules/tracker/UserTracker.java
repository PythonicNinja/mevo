package com.mevo.app.modules.tracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.mevo.app.constants.BikeTypes;
import com.mevo.app.constants.Servers;
import com.mevo.app.data.model.UserDetails;
import com.mevo.app.data.model.api_extended.response.ResponseTrackUser;
import com.mevo.app.data.network.Rest;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserTracker {
    private static final String KEY_LAST_USER_DETAILS_UPDATE_PREFIX = "KEY_LAST_USER_DETAILS_UPDATE_PREFIX_";
    private static final String SHARED_PREFS_NAME = "com.mevo.app.modules.tracker.UserTracker";
    private static final int STATUS_ERROR = 98;
    private static final int STATUS_OK = 10;
    private static final String TAG = "CitibikeTracker";
    private static final long USER_DETAILS_UPDATE_INTERVAL = 64800000;
    private static final int USER_STATUS_ACTIVE = 10;
    private static final int USER_STATUS_INACTIVE = 98;
    private static UserTracker instance;

    /* renamed from: com.mevo.app.modules.tracker.UserTracker$1 */
    class C08191 implements Callback<ResponseTrackUser> {
        C08191() {
        }

        public void onResponse(Call<ResponseTrackUser> call, Response<ResponseTrackUser> response) {
            if (response.isSuccessful() == null || response.body() == null || ((ResponseTrackUser) response.body()).getStatus() != 10) {
                Log.m90e(UserTracker.TAG, "User details sending failed, but server didnt respond properly.");
                return;
            }
            String str = UserTracker.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("User details sent successfully, status: ");
            stringBuilder.append(((ResponseTrackUser) response.body()).getStatus());
            Log.m94i(str, stringBuilder.toString());
        }

        public void onFailure(Call<ResponseTrackUser> call, Throwable th) {
            Log.m90e(UserTracker.TAG, "User details sending failed.");
        }
    }

    /* renamed from: com.mevo.app.modules.tracker.UserTracker$2 */
    class C08202 implements Callback<ResponseTrackUser> {
        C08202() {
        }

        public void onResponse(Call<ResponseTrackUser> call, Response<ResponseTrackUser> response) {
            if (response.isSuccessful() == null || response.body() == null || ((ResponseTrackUser) response.body()).getStatus() != 10) {
                Log.m90e(UserTracker.TAG, "User details sending failed, but server didnt respond properly.");
                return;
            }
            String str = UserTracker.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("User details sent successfully, status: ");
            stringBuilder.append(((ResponseTrackUser) response.body()).getStatus());
            Log.m94i(str, stringBuilder.toString());
        }

        public void onFailure(Call<ResponseTrackUser> call, Throwable th) {
            Log.m90e(UserTracker.TAG, "User details sending failed.");
        }
    }

    /* renamed from: com.mevo.app.modules.tracker.UserTracker$3 */
    class C08213 implements Callback<ResponseTrackUser> {
        C08213() {
        }

        public void onResponse(Call<ResponseTrackUser> call, Response<ResponseTrackUser> response) {
            if (response.isSuccessful() == null || response.body() == null || ((ResponseTrackUser) response.body()).getStatus() != 10) {
                Log.m90e(UserTracker.TAG, "User confirmation sending failed, but server didnt respond properly.");
                return;
            }
            String str = UserTracker.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("User confirmation sent successfully, status: ");
            stringBuilder.append(((ResponseTrackUser) response.body()).getStatus());
            Log.m94i(str, stringBuilder.toString());
        }

        public void onFailure(Call<ResponseTrackUser> call, Throwable th) {
            Log.m90e(UserTracker.TAG, "User confirmation sending failed, repeating");
        }
    }

    private int getUserStatus(boolean z) {
        return z ? 10 : 98;
    }

    public static UserTracker get() {
        if (instance == null) {
            instance = new UserTracker();
        }
        return instance;
    }

    private SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(SHARED_PREFS_NAME, 0);
    }

    public void updateUserOncePerDay(UserDetails userDetails, boolean z, Context context) {
        if (userDetails == null) {
            Log.m90e(TAG, "Can't track user if we dont have his details");
            return;
        }
        SharedPreferences sharedPrefs = getSharedPrefs(context);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(KEY_LAST_USER_DETAILS_UPDATE_PREFIX);
        stringBuilder.append(userDetails.getPhoneNumber());
        if (System.currentTimeMillis() - sharedPrefs.getLong(stringBuilder.toString(), 0) > USER_DETAILS_UPDATE_INTERVAL) {
            updateUser(userDetails, z);
            z = getSharedPrefs(context).edit();
            context = new StringBuilder();
            context.append(KEY_LAST_USER_DETAILS_UPDATE_PREFIX);
            context.append(userDetails.getPhoneNumber());
            z.putLong(context.toString(), System.currentTimeMillis()).apply();
        }
    }

    public void updateUser(UserDetails userDetails, boolean z) {
        UserTracker userTracker = this;
        if (userDetails == null) {
            Log.m90e(TAG, "Can't track user if we dont have his details");
        } else {
            Rest.getApiExtended().updateUser(Servers.EXTENDED_API_SYSTEM_ID, formatPhoneNumber(userDetails.getPhoneNumber()), userDetails.getFirstname(), userDetails.getLastname(), userDetails.getEmail(), userDetails.getAddress(), userDetails.getZipCode(), Utils.intToBool(userDetails.getHasNewsletter()), userDetails.getPesel(), getUserStatus(z), null, null, null, null, null, null).enqueue(new C08191());
        }
    }

    public void updateUserWithAgreements(UserDetails userDetails, boolean z, Boolean[] boolArr) {
        UserTracker userTracker = this;
        Boolean[] boolArr2 = boolArr;
        if (!(userDetails == null || boolArr2 == null)) {
            if (boolArr2.length == 6) {
                Rest.getApiExtended().updateUser(Servers.EXTENDED_API_SYSTEM_ID, formatPhoneNumber(userDetails.getPhoneNumber()), userDetails.getFirstname(), userDetails.getLastname(), userDetails.getEmail(), userDetails.getAddress(), userDetails.getZipCode(), Utils.intToBool(userDetails.getHasNewsletter()), userDetails.getPesel(), getUserStatus(z), boolArr2[0], boolArr2[1], boolArr2[2], boolArr2[3], boolArr2[4], boolArr2[5]).enqueue(new C08202());
                return;
            }
        }
        Log.m90e(TAG, "Invalid data");
    }

    public void confirmUser(String str) {
        Rest.getApiExtended().confirmUser(Servers.EXTENDED_API_SYSTEM_ID, formatPhoneNumber(str)).enqueue(new C08213());
    }

    public static String formatPhoneNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() > 9 && (str.substring(0, 2).equals("48") || str.substring(0, 3).equals("+48"))) {
            str = str.substring(str.indexOf(BikeTypes.KIDS_BIKE_24) + 1, str.length() - 1);
        }
        return str;
    }
}
