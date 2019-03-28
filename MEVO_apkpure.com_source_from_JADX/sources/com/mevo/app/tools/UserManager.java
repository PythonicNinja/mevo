package com.mevo.app.tools;

import android.support.annotation.Nullable;
import com.mevo.app.Cfg;
import com.mevo.app.data.model.User;
import com.mevo.app.data.model.UserDetails;
import com.mevo.app.data.model.response.ResponseUserDetails;
import com.mevo.app.data.network.Rest;
import com.mevo.app.tools.NetTools.CallsToken;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManager {

    public interface UserDetailsListener {
        void onReceive(boolean z, Call<ResponseUserDetails> call, UserDetails userDetails);
    }

    public static void logged(User user) {
        SQLite.delete().from(User.class).execute();
        user.save();
    }

    public static void refreshUserData(User user) {
        SQLite.delete().from(User.class).execute();
        user.save();
    }

    public static void logout() {
        SQLite.delete().from(User.class).execute();
        SQLite.delete().from(UserDetails.class).execute();
    }

    public static User getUser() {
        return (User) SQLite.select(new IProperty[0]).from(User.class).querySingle();
    }

    public static String getLoginKey() {
        User user = getUser();
        return user != null ? user.getLoginkey() : "";
    }

    @Nullable
    public static String getCurrentUserMobileNumber() {
        User user = getUser();
        return user != null ? user.getPhoneNumber() : null;
    }

    public static void saveUserDetails(UserDetails userDetails) {
        SQLite.delete().from(UserDetails.class).execute();
        if (userDetails != null) {
            userDetails.save();
        }
    }

    public static void saveUserCredits(int i, int i2) {
        UserDetails userDetails = (UserDetails) SQLite.select(new IProperty[0]).from(UserDetails.class).querySingle();
        if (userDetails != null) {
            userDetails.setCredits(i);
            userDetails.setIsActive(i2);
            userDetails.save();
        }
        User user = (User) SQLite.select(new IProperty[0]).from(User.class).querySingle();
        if (user != null) {
            user.setCredits(i);
            user.setIsActive(i2);
            user.save();
        }
    }

    public static void saveUserState(int i) {
        UserDetails userDetails = (UserDetails) SQLite.select(new IProperty[0]).from(UserDetails.class).querySingle();
        if (userDetails != null) {
            userDetails.setIsActive(i);
            userDetails.save();
        }
        User user = (User) SQLite.select(new IProperty[0]).from(User.class).querySingle();
        if (user != null) {
            user.setIsActive(i);
            user.save();
        }
    }

    @Nullable
    public static UserDetails getUserDetails() {
        return (UserDetails) SQLite.select(new IProperty[0]).from(UserDetails.class).querySingle();
    }

    public static void clearUserDetails() {
        SQLite.delete().from(UserDetails.class).execute();
    }

    public static Call<ResponseUserDetails> fetchUserDetailsFromServer(CallsToken callsToken, final UserDetailsListener userDetailsListener) {
        User user = getUser();
        if (user == null) {
            user = new User();
        }
        if (callsToken == null) {
            callsToken = new CallsToken();
        }
        callsToken = NetTools.saveCall(callsToken, Rest.getApiNextbike().getUserDetails(Cfg.get().apikeyNextbike(), user.getLoginkey()));
        callsToken.enqueue(new Callback<ResponseUserDetails>() {
            public void onResponse(Call<ResponseUserDetails> call, Response<ResponseUserDetails> response) {
                if (response.body() == null || ((ResponseUserDetails) response.body()).userDetails == null) {
                    UserManager.clearUserDetails();
                    userDetailsListener.onReceive(false, call, null);
                    return;
                }
                UserManager.saveUserDetails(((ResponseUserDetails) response.body()).userDetails);
                userDetailsListener.onReceive(true, call, ((ResponseUserDetails) response.body()).userDetails);
            }

            public void onFailure(Call<ResponseUserDetails> call, Throwable th) {
                if (call.isCanceled() == null) {
                    UserManager.clearUserDetails();
                }
                userDetailsListener.onReceive(false, call, null);
            }
        });
        return callsToken;
    }
}
