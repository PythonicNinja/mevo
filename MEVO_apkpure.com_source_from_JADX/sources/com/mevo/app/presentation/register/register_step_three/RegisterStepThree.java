package com.mevo.app.presentation.register.register_step_three;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.data.model.RegistrationData;
import com.mevo.app.data.model.User;
import com.mevo.app.data.model.UserDetails;
import com.mevo.app.data.model.response.ResponseLogin;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.shared_prefs.SharedPrefs;
import com.mevo.app.modules.tracker.UserTracker;
import com.mevo.app.presentation.custom_views.AppToolbar.AppToolbarListener;
import com.mevo.app.presentation.custom_views.CustomInput;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.main.contact_us.ContactUsFragment;
import com.mevo.app.presentation.register.RegisterActivity;
import com.mevo.app.presentation.register.RegisterFragment;
import com.mevo.app.tools.NetTools;
import com.mevo.app.tools.NetTools.CallsToken;
import com.mevo.app.tools.UiTools;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.UserManager.UserDetailsListener;
import com.mevo.app.tools.Validator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterStepThree extends RegisterFragment implements AppToolbarListener, OnClickListener {
    private CallsToken callsToken;
    private Button createAccount;
    private CustomInput inputPin;
    private EditText pinEdit;
    private RegistrationData registrationData;
    private TextView textError;

    /* renamed from: com.mevo.app.presentation.register.register_step_three.RegisterStepThree$1 */
    class C08351 implements Callback<ResponseLogin> {
        C08351() {
        }

        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
            if (response.isSuccessful() == null || response.body() == null || ((ResponseLogin) response.body()).user == null) {
                RegisterStepThree.this.onLoginFailed();
            } else {
                RegisterStepThree.this.onLoginSuccess(((ResponseLogin) response.body()).user);
            }
            if (RegisterStepThree.this.getActivityInterface() != null) {
                RegisterStepThree.this.getActivityInterface().setProgressViewVisibility(null);
            }
        }

        public void onFailure(Call<ResponseLogin> call, Throwable th) {
            if (call.isCanceled() == null) {
                RegisterStepThree.this.onLoginFailed();
            }
            if (RegisterStepThree.this.getActivityInterface() != null) {
                RegisterStepThree.this.getActivityInterface().setProgressViewVisibility(null);
            }
        }
    }

    /* renamed from: com.mevo.app.presentation.register.register_step_three.RegisterStepThree$2 */
    class C08362 implements UserDetailsListener {
        C08362() {
        }

        public void onReceive(boolean z, Call call, UserDetails userDetails) {
            if (RegisterStepThree.this.getActivityInterface()) {
                RegisterStepThree.this.getActivityInterface().goToMainActivityAndClearStack();
            }
        }
    }

    public void onBackClick() {
    }

    public void onSettingsClick() {
    }

    public static RegisterStepThree newInstance() {
        return new RegisterStepThree();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_register_step_3, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        this.registrationData = getActivityInterface().getRegistrationData();
        this.callsToken = new CallsToken();
        disableBackArrow();
        return layoutInflater;
    }

    private void findViews(View view) {
        this.createAccount = (Button) view.findViewById(C0434R.id.fragment_register_step_third_create_account_button);
        this.pinEdit = (EditText) view.findViewById(C0434R.id.fragment_register_step_third_edit_text_pin);
        this.inputPin = (CustomInput) view.findViewById(C0434R.id.fragment_register_step_third_custom);
        this.textError = (TextView) view.findViewById(C0434R.id.fragment_register_step_third_text);
    }

    private void setListeners() {
        this.createAccount.setOnClickListener(this);
        this.inputPin.setValidator(null, new RegisterStepThree$$Lambda$0(this));
    }

    final /* synthetic */ Pair lambda$setListeners$324$RegisterStepThree(String str) {
        if (Validator.isPinValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.login_screen_wrong_pin) : "");
    }

    public void onClick(View view) {
        if (view.getId() == C0434R.id.fragment_register_step_third_create_account_button) {
            UiTools.hideSoftInput(view);
            login();
        }
    }

    private void login() {
        if (getActivityInterface() != null && checkValid()) {
            getActivityInterface().setProgressViewVisibility(true);
            NetTools.saveCall(this.callsToken, Rest.getApiNextbike().login(Cfg.get().apikeyNextbike(), this.registrationData.phoneNumber, this.pinEdit.getText().toString(), Cfg.get().domain().getDomainCode())).enqueue(new C08351());
        }
    }

    private boolean checkValid() {
        this.inputPin.checkValid();
        if (this.inputPin.isValid().booleanValue()) {
            this.textError.setTextColor(IM.resources().getColor(C0434R.color.hintColor));
        } else {
            this.textError.setTextColor(IM.resources().getColor(C0434R.color.textColorError));
        }
        return this.inputPin.isValid().booleanValue();
    }

    private void onLoginSuccess(User user) {
        UserManager.logged(user);
        SharedPrefs.setMobileNumber(this.registrationData.phoneNumber);
        fetchUserDetailsAndGo(user.getLoginkey());
        UserTracker.get().confirmUser(user.getPhoneNumber());
    }

    private void onLoginFailed() {
        TopToast.show(C0434R.string.login_failed, 0, TopToast.DURATION_SHORT);
    }

    private void fetchUserDetailsAndGo(String str) {
        UserManager.fetchUserDetailsFromServer(this.callsToken, new C08362());
    }

    private void disableBackArrow() {
        if (getActivity() != null) {
            ((RegisterActivity) getActivity()).disableBack();
        }
    }

    public void onContactClick() {
        if (getActivityInterface() != null) {
            getActivityInterface().changeFragment(ContactUsFragment.newInstance(), true);
        }
    }
}
