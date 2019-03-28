package com.mevo.app.presentation.splash;

import android.content.Intent;
import android.os.Bundle;
import com.inverce.mod.core.IM;
import com.mevo.app.Cfg;
import com.mevo.app.data.model.User;
import com.mevo.app.data.model.response.ResponseUserDetails;
import com.mevo.app.data.network.Rest;
import com.mevo.app.presentation.base.BaseActivity;
import com.mevo.app.presentation.entry.EntryActivity;
import com.mevo.app.presentation.main.MainActivity;
import com.mevo.app.tools.NetTools;
import com.mevo.app.tools.NetTools.CallsToken;
import com.mevo.app.tools.UserManager;
import java.util.concurrent.TimeUnit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {
    private CallsToken callsToken;

    /* renamed from: com.mevo.app.presentation.splash.SplashActivity$1 */
    class C08371 implements Callback<ResponseUserDetails> {
        C08371() {
        }

        public void onResponse(Call<ResponseUserDetails> call, Response<ResponseUserDetails> response) {
            if (response.body() == null || ((ResponseUserDetails) response.body()).userDetails == null) {
                SplashActivity.this.autologinFail();
                return;
            }
            UserManager.saveUserDetails(((ResponseUserDetails) response.body()).userDetails);
            SplashActivity.this.autologinSuccess();
        }

        public void onFailure(Call<ResponseUserDetails> call, Throwable th) {
            SplashActivity.this.autologinFail();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.callsToken = new CallsToken();
        tryAutologin();
    }

    private void tryAutologin() {
        User user = UserManager.getUser();
        if (user == null) {
            autologinFail();
        } else {
            NetTools.saveCall(this.callsToken, Rest.getApiNextbikeShortTimeout().getUserDetails(Cfg.get().apikeyNextbike(), user.getLoginkey())).enqueue(new C08371());
        }
    }

    private void autologinSuccess() {
        IM.onUi().schedule(new SplashActivity$$Lambda$0(this), 3, TimeUnit.SECONDS);
    }

    final /* synthetic */ void lambda$autologinSuccess$329$SplashActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void autologinFail() {
        IM.onUi().schedule(new SplashActivity$$Lambda$1(this), 3, TimeUnit.SECONDS);
    }

    final /* synthetic */ void lambda$autologinFail$330$SplashActivity() {
        startActivity(new Intent(this, EntryActivity.class));
        finish();
    }
}
