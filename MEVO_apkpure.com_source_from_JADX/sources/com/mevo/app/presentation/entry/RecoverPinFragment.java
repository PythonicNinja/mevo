package com.mevo.app.presentation.entry;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.data.model.response.ResponseRecoverPin;
import com.mevo.app.data.network.Rest;
import com.mevo.app.presentation.custom_views.AppToolbar.AppToolbarListener;
import com.mevo.app.presentation.custom_views.CustomInput;
import com.mevo.app.presentation.custom_views.ProgressOverlayView;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.custom_views.ValidationEditText;
import com.mevo.app.presentation.custom_views.ValidationEditText.ValidChangeListener;
import com.mevo.app.presentation.main.contact_us.ContactUsFragment;
import com.mevo.app.tools.NetTools;
import com.mevo.app.tools.NetTools.CallsToken;
import com.mevo.app.tools.UiTools;
import com.mevo.app.tools.Validator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecoverPinFragment extends EntryFragment implements AppToolbarListener, ValidChangeListener {
    private CallsToken callsToken;
    private AppCompatButton confirmButton;
    private CustomInput phoneNumberContainer;
    private TextInputEditText phoneNumberEdit;
    private ProgressOverlayView progressOverlayView;

    /* renamed from: com.mevo.app.presentation.entry.RecoverPinFragment$1 */
    class C08231 implements Callback<ResponseRecoverPin> {
        C08231() {
        }

        public void onResponse(Call<ResponseRecoverPin> call, Response<ResponseRecoverPin> response) {
            RecoverPinFragment.this.onRecoverPin();
            RecoverPinFragment.this.progressOverlayView.hide();
        }

        public void onFailure(Call<ResponseRecoverPin> call, Throwable th) {
            if (call.isCanceled() == null) {
                RecoverPinFragment.this.onRecoverPin();
            }
            RecoverPinFragment.this.progressOverlayView.hide();
        }
    }

    public void onSettingsClick() {
    }

    public void onValidChanged(ValidationEditText validationEditText, boolean z) {
    }

    public static RecoverPinFragment newInstance() {
        return new RecoverPinFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_recover_pin, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        this.callsToken = new CallsToken();
        setValidator();
        return layoutInflater;
    }

    public void onStop() {
        super.onStop();
        NetTools.cancelSavedOngoingCalls(this.callsToken);
    }

    public void onStart() {
        super.onStart();
        onToolbarInterface(RecoverPinFragment$$Lambda$0.$instance);
    }

    public void onDestroy() {
        super.onDestroy();
        onToolbarInterface(RecoverPinFragment$$Lambda$1.$instance);
    }

    private void setListeners() {
        this.confirmButton.setOnClickListener(new RecoverPinFragment$$Lambda$2(this));
    }

    final /* synthetic */ void lambda$setListeners$172$RecoverPinFragment(View view) {
        this.phoneNumberContainer.checkValid();
        if (this.phoneNumberContainer.isValid().booleanValue() != null) {
            remindPin();
            UiTools.hideSoftInput(this.phoneNumberEdit);
            return;
        }
        onRecoverFailed();
    }

    private void findViews(View view) {
        this.phoneNumberEdit = (TextInputEditText) view.findViewById(C0434R.id.fragment_recover_pin_edit_text_pin);
        this.confirmButton = (AppCompatButton) view.findViewById(C0434R.id.fragment_recover_pin_generate_pin_button);
        this.progressOverlayView = (ProgressOverlayView) view.findViewById(C0434R.id.fragment_recover_pin_progress_view);
        this.phoneNumberContainer = (CustomInput) view.findViewById(C0434R.id.fragment_login_mobile_number_edit_container);
    }

    private void setValidator() {
        this.phoneNumberContainer.setValidator(null, RecoverPinFragment$$Lambda$3.$instance);
    }

    static final /* synthetic */ Pair lambda$setValidator$173$RecoverPinFragment(String str) {
        if (Validator.isPhoneNumberValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), "");
    }

    private void remindPin() {
        this.progressOverlayView.show();
        NetTools.saveCall(this.callsToken, Rest.getApiNextbike().recoverPin(Cfg.get().apikeyNextbike(), this.phoneNumberEdit.getText().toString(), Cfg.get().domain().getDomainCode())).enqueue(new C08231());
    }

    private void onRecoverPin() {
        TopToast.show(C0434R.string.recover_pin_success, 1, TopToast.DURATION_LONG);
        if (getActivityInterface() != null) {
            getActivityInterface().clearBackStack();
            getActivityInterface().changeFragment(LoginFragment.newInstance(this.phoneNumberEdit.getText().toString()));
        }
    }

    private void onRecoverFailed() {
        TopToast.show(C0434R.string.recover_pin_failed, 0, TopToast.DURATION_LONG);
    }

    public void onBackClick() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    public void onContactClick() {
        if (getActivityInterface() != null) {
            getActivityInterface().changeFragment(ContactUsFragment.newInstance(), true);
        }
    }
}
