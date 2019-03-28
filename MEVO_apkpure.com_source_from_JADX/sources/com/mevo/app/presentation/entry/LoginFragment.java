package com.mevo.app.presentation.entry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.constants.AppLanguageCode;
import com.mevo.app.constants.Servers;
import com.mevo.app.data.model.User;
import com.mevo.app.data.model.UserDetails;
import com.mevo.app.data.model.response.ResponseLogin;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.shared_prefs.SharedPrefs;
import com.mevo.app.presentation.custom_views.CustomInput;
import com.mevo.app.presentation.custom_views.ProgressOverlayView;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.register.RegisterActivity;
import com.mevo.app.tools.LocaleHelper;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.NetTools;
import com.mevo.app.tools.NetTools.CallsToken;
import com.mevo.app.tools.SoftKeyboardStateWatcher;
import com.mevo.app.tools.SoftKeyboardStateWatcher.SoftKeyboardStateListener;
import com.mevo.app.tools.Tools;
import com.mevo.app.tools.UiTools;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.Validator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends EntryFragment implements SoftKeyboardStateListener {
    private static final String PHONE_KEY = "phone_key";
    private static final String TAG = "LoginFragment";
    private Switch apiKeySwitch;
    private TextView appVersionText;
    private CallsToken callsToken;
    private TextView financingInfo;
    private TextView forgotPinButton;
    private boolean isValidPhone = true;
    private TextView languageDeButton;
    private TextView languageEnButton;
    private TextView languagePlButton;
    private TextView languageRuButton;
    private Button loginButton;
    private CustomInput mobileNumberContainer;
    private EditText mobileNumberEdit;
    private CustomInput pinContainer;
    private EditText pinEdit;
    private ProgressOverlayView progressView;
    private TextView registerButton;
    private TextView serverText;
    private SoftKeyboardStateWatcher softKeyboardStateWatcher;
    private TextView textPhone;
    private TextWatcher textWatcher;

    /* renamed from: com.mevo.app.presentation.entry.LoginFragment$1 */
    class C04431 implements OnGlobalLayoutListener {
        C04431() {
        }

        public void onGlobalLayout() {
            LoginFragment.this.registerButton.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            if (LoginFragment.this.registerButton.getLineCount() < 2) {
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                layoutParams.gravity = GravityCompat.END;
                LoginFragment.this.registerButton.setLayoutParams(layoutParams);
                LoginFragment.this.registerButton.setGravity(GravityCompat.END);
                return;
            }
            layoutParams = new LinearLayout.LayoutParams(-1, -1);
            layoutParams.gravity = GravityCompat.START;
            LoginFragment.this.registerButton.setLayoutParams(layoutParams);
            LoginFragment.this.registerButton.setGravity(GravityCompat.START);
        }
    }

    /* renamed from: com.mevo.app.presentation.entry.LoginFragment$2 */
    class C04442 implements TextWatcher {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        C04442() {
        }

        public void afterTextChanged(Editable editable) {
            LoginFragment.this.setTextPhoneColorWhenIsValid(Boolean.valueOf(true));
        }
    }

    /* renamed from: com.mevo.app.presentation.entry.LoginFragment$4 */
    class C04454 implements OnCheckedChangeListener {
        C04454() {
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            LoginFragment.this.setHost(z);
        }
    }

    /* renamed from: com.mevo.app.presentation.entry.LoginFragment$3 */
    class C08223 implements Callback<ResponseLogin> {
        C08223() {
        }

        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
            ResponseLogin responseLogin = (ResponseLogin) response.body();
            if (response.isSuccessful() == null || responseLogin == null || responseLogin.user == null || TextUtils.isEmpty(responseLogin.user.getLoginkey()) != null) {
                LoginFragment.this.onLoginFailed();
            } else {
                LoginFragment.this.onLoginSuccess(responseLogin.user);
            }
        }

        public void onFailure(Call<ResponseLogin> call, Throwable th) {
            LoginFragment.this.progressView.hide();
            if (call.isCanceled() == null) {
                LoginFragment.this.onLoginFailed();
            }
        }
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public static LoginFragment newInstance(String str) {
        LoginFragment loginFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PHONE_KEY, str);
        loginFragment.setArguments(bundle);
        return loginFragment;
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_login, viewGroup, false);
        findViews(layoutInflater);
        setListeners(layoutInflater);
        prefillData();
        setTextWatcher();
        setAppVersionText();
        prepareApikeySwitchIfShould(layoutInflater);
        this.callsToken = new CallsToken();
        return layoutInflater;
    }

    public void onStop() {
        super.onStop();
        NetTools.cancelSavedOngoingCalls(this.callsToken);
    }

    public void onStart() {
        super.onStart();
        onToolbarInterface(LoginFragment$$Lambda$0.$instance);
    }

    public void onDestroy() {
        super.onDestroy();
        onToolbarInterface(LoginFragment$$Lambda$1.$instance);
    }

    private void findViews(View view) {
        this.mobileNumberEdit = (EditText) view.findViewById(C0434R.id.fragment_login_mobile_number_edit);
        this.pinEdit = (EditText) view.findViewById(C0434R.id.fragment_login_pin_edit);
        this.loginButton = (Button) view.findViewById(C0434R.id.fragment_login_login_button);
        this.registerButton = (TextView) view.findViewById(C0434R.id.fragment_login_register_button);
        this.progressView = (ProgressOverlayView) view.findViewById(C0434R.id.fragment_login_progress_view);
        this.forgotPinButton = (TextView) view.findViewById(C0434R.id.fragment_login_forgot_pin);
        this.appVersionText = (TextView) view.findViewById(C0434R.id.fragment_login_app_version);
        this.languagePlButton = (TextView) view.findViewById(C0434R.id.fragment_login_language_pl);
        this.languageEnButton = (TextView) view.findViewById(C0434R.id.fragment_login_language_en);
        this.languageDeButton = (TextView) view.findViewById(C0434R.id.fragment_login_language_de);
        this.languageRuButton = (TextView) view.findViewById(C0434R.id.fragment_login_language_ru);
        this.apiKeySwitch = (Switch) view.findViewById(C0434R.id.fragment_login_api_key_switch);
        this.serverText = (TextView) view.findViewById(C0434R.id.fragment_login_api_text_view);
        this.mobileNumberContainer = (CustomInput) view.findViewById(C0434R.id.fragment_login_mobile_number_edit_container);
        this.pinContainer = (CustomInput) view.findViewById(C0434R.id.fragment_login_pin_edit_container);
        this.financingInfo = (TextView) view.findViewById(C0434R.id.fragmentLoginFinancingInfo);
        this.textPhone = (TextView) view.findViewById(C0434R.id.fragment_login_text_phone);
        this.registerButton.getViewTreeObserver().addOnGlobalLayoutListener(new C04431());
    }

    private void setListeners(View view) {
        this.loginButton.setOnClickListener(new LoginFragment$$Lambda$2(this));
        this.registerButton.setOnClickListener(new LoginFragment$$Lambda$3(this));
        this.forgotPinButton.setOnClickListener(new LoginFragment$$Lambda$4(this));
        this.languagePlButton.setOnClickListener(new LoginFragment$$Lambda$5(this));
        this.languageEnButton.setOnClickListener(new LoginFragment$$Lambda$6(this));
        this.languageDeButton.setOnClickListener(new LoginFragment$$Lambda$7(this));
        this.languageRuButton.setOnClickListener(new LoginFragment$$Lambda$8(this));
        this.pinEdit.setOnEditorActionListener(new LoginFragment$$Lambda$9(this));
        this.softKeyboardStateWatcher = new SoftKeyboardStateWatcher(view);
        this.softKeyboardStateWatcher.addSoftKeyboardStateListener(this);
        this.mobileNumberContainer.setValidator(null, new LoginFragment$$Lambda$10(this));
        this.pinContainer.setValidator(null, new LoginFragment$$Lambda$11(this));
    }

    final /* synthetic */ void lambda$setListeners$159$LoginFragment(View view) {
        UiTools.hideSoftInput(this.loginButton);
        login();
        setTextPhoneColorWhenIsValid(Boolean.valueOf(this.isValidPhone));
    }

    final /* synthetic */ void lambda$setListeners$160$LoginFragment(View view) {
        startRegistration();
    }

    final /* synthetic */ void lambda$setListeners$161$LoginFragment(View view) {
        getActivityInterface().changeFragment(RecoverPinFragment.newInstance());
    }

    final /* synthetic */ void lambda$setListeners$162$LoginFragment(View view) {
        setLanguage(AppLanguageCode.PL.getCode());
    }

    final /* synthetic */ void lambda$setListeners$163$LoginFragment(View view) {
        setLanguage(AppLanguageCode.EN.getCode());
    }

    final /* synthetic */ void lambda$setListeners$164$LoginFragment(View view) {
        setLanguage(AppLanguageCode.DE.getCode());
    }

    final /* synthetic */ void lambda$setListeners$165$LoginFragment(View view) {
        setLanguage(AppLanguageCode.RU.getCode());
    }

    final /* synthetic */ boolean lambda$setListeners$166$LoginFragment(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return null;
        }
        login();
        return true;
    }

    final /* synthetic */ Pair lambda$setListeners$167$LoginFragment(String str) {
        if (Validator.isPhoneNumberValid(str) != null) {
            this.isValidPhone = true;
            return new Pair(Boolean.valueOf(true), null);
        }
        this.isValidPhone = false;
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.login_phone_text) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$168$LoginFragment(String str) {
        if (Validator.isPinValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.login_screen_wrong_pin) : "");
    }

    private void setTextWatcher() {
        this.textWatcher = new C04442();
        this.mobileNumberEdit.addTextChangedListener(this.textWatcher);
    }

    private void prefillData() {
        CharSequence charSequence = "";
        if (getArguments() != null) {
            charSequence = getArguments().getString(PHONE_KEY, "");
        }
        if (charSequence.isEmpty()) {
            charSequence = SharedPrefs.getMobileNumber();
        }
        if (charSequence != null) {
            this.mobileNumberEdit.setText(charSequence);
        }
    }

    private void setTextPhoneColorWhenIsValid(Boolean bool) {
        int i;
        TextView textView = this.textPhone;
        if (bool.booleanValue() != null) {
            bool = IM.resources();
            i = C0434R.color.lightHintColor;
        } else {
            bool = IM.resources();
            i = C0434R.color.textColorError;
        }
        textView.setTextColor(bool.getColor(i));
    }

    private void setAppVersionText() {
        if (Tools.getAppVersion() != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Version: ");
            stringBuilder.append(Tools.getAppVersion());
            this.appVersionText.setText(stringBuilder.toString());
        }
    }

    private void setLanguage(String str) {
        LocaleHelper.setLocale(getContext(), str);
        startActivity(new Intent(getContext(), EntryActivity.class));
        getActivity().finish();
    }

    private void startRegistration() {
        startActivity(new Intent(getContext(), RegisterActivity.class));
    }

    private boolean checkAllFieldsValid() {
        this.mobileNumberContainer.checkValid();
        this.pinContainer.checkValid();
        return this.mobileNumberContainer.isValid().booleanValue() && this.pinContainer.isValid().booleanValue();
    }

    private void login() {
        if (checkAllFieldsValid()) {
            this.progressView.show();
            NetTools.saveCall(this.callsToken, Rest.getApiNextbike().login(Cfg.get().apikeyNextbike(), this.mobileNumberEdit.getText().toString(), this.pinEdit.getText().toString(), Cfg.get().domain().getDomainCode())).enqueue(new C08223());
        }
    }

    private void onLoginSuccess(User user) {
        UserManager.logged(user);
        SharedPrefs.setMobileNumber(this.mobileNumberEdit.getText().toString());
        fetchUserDetailsAndGo();
    }

    private void onLoginFailed() {
        this.progressView.hide();
        TopToast.show(C0434R.string.login_failed, 0, TopToast.DURATION_SHORT);
    }

    private void fetchUserDetailsAndGo() {
        UserManager.fetchUserDetailsFromServer(this.callsToken, new LoginFragment$$Lambda$12(this));
    }

    final /* synthetic */ void lambda$fetchUserDetailsAndGo$169$LoginFragment(boolean z, Call call, UserDetails userDetails) {
        this.progressView.hide();
        if (getActivityInterface()) {
            getActivityInterface().goToMainActivityAndFinish();
        }
    }

    private void prepareApikeySwitchIfShould(View view) {
        if (Cfg.get().serverSwitchAvailable()) {
            view.findViewById(C0434R.id.fragment_login_server_switch).setVisibility(0);
            this.apiKeySwitch.setOnCheckedChangeListener(new C04454());
            view = SharedPrefs.getApikeyIsProduction();
            this.apiKeySwitch.setChecked(view);
            setHost(view);
        }
    }

    @SuppressLint({"SetTextI18n"})
    private void setHost(boolean z) {
        if (z) {
            Cfg.get().setApiNextbikeUrl(Servers.API_NEXTBIKE_PROD);
            Cfg.get().setApiNextbikeMapUrl(Servers.API_NEXTBIKE_MAP_PROD);
            this.serverText.setText("Production ON");
            Log.m94i(TAG, "Host: https://mevo-api.nextbike.net");
        } else {
            Cfg.get().setApiNextbikeUrl("https://px-15.nextbike.net/");
            Cfg.get().setApiNextbikeMapUrl("https://px-15.nextbike.net/");
            this.serverText.setText("STG ON");
            Log.m94i(TAG, "Host: https://px-15.nextbike.net/");
        }
        Rest.init();
        SharedPrefs.setHostIsProduction(z);
    }

    public void onSoftKeyboardOpened(int i) {
        this.financingInfo.setVisibility(8);
    }

    public void onSoftKeyboardClosed() {
        this.financingInfo.setVisibility(0);
    }
}
