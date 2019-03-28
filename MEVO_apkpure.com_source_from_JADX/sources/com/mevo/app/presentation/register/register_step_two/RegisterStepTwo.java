package com.mevo.app.presentation.register.register_step_two;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.annimon.stream.Stream;
import com.mevo.app.C0434R;
import com.mevo.app.constants.RegisterAgreements;
import com.mevo.app.data.model.AgreementWithConsent;
import com.mevo.app.data.model.RegistrationData;
import com.mevo.app.data.model.response.ErrorResponse;
import com.mevo.app.data.repository.RegisterRepository;
import com.mevo.app.presentation.custom_views.AppToolbar.AppToolbarListener;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.main.contact_us.ContactUsFragment;
import com.mevo.app.presentation.register.ClausulesFragment;
import com.mevo.app.presentation.register.RegisterFragment;
import com.mevo.app.presentation.register.register_step_three.RegisterStepThree;
import com.mevo.app.tools.NetTools;
import com.mevo.app.tools.NetTools.CallsToken;
import com.mevo.app.tools.TermsRepository;
import com.mevo.app.tools.UiTools;
import com.mevo.app.tools.Utils;

public class RegisterStepTwo extends RegisterFragment implements AppToolbarListener, OnClickListener {
    private RegisterAgreementsAdapter adapter;
    private CallsToken callsToken;
    private Button clause;
    private Button downloadTerms;
    private Button proceedButton;
    private RecyclerView recyclerView;
    private RegistrationData registrationData;

    public void onBackClick() {
    }

    public void onSettingsClick() {
    }

    public static RegisterStepTwo newInstance() {
        return new RegisterStepTwo();
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_register_step_2, viewGroup, false);
        findViews(layoutInflater);
        prepareAdapter();
        setListeners();
        this.callsToken = new CallsToken();
        return layoutInflater;
    }

    private void findViews(View view) {
        this.proceedButton = (Button) view.findViewById(C0434R.id.fragment_register_step_two_keep_going_button);
        this.downloadTerms = (Button) view.findViewById(C0434R.id.fragment_register_step_two_download_button);
        this.recyclerView = (RecyclerView) view.findViewById(C0434R.id.fragment_register_step_2_recycler);
        this.clause = (Button) view.findViewById(C0434R.id.fragment_register_step_clausules);
    }

    private void prepareAdapter() {
        this.adapter = new RegisterAgreementsAdapter(Stream.of(RegisterAgreements.AGREEMENTS_PROD).map(RegisterStepTwo$$Lambda$0.$instance).toList());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setAdapter(this.adapter);
    }

    private void setListeners() {
        this.proceedButton.setOnClickListener(this);
        this.downloadTerms.setOnClickListener(this);
        this.clause.setOnClickListener(new RegisterStepTwo$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$setListeners$326$RegisterStepTwo(View view) {
        getActivityInterface().changeFragment(ClausulesFragment.newInstance(), true);
    }

    public void onClick(View view) {
        if (getActivityInterface() != null) {
            switch (view.getId()) {
                case C0434R.id.fragment_register_step_two_download_button:
                    Utils.launchBrowser(getContext(), TermsRepository.getTermsOfUseUrl());
                    break;
                case C0434R.id.fragment_register_step_two_keep_going_button:
                    UiTools.hideSoftInput(view);
                    setDataAndRegister();
                    break;
                default:
                    break;
            }
        }
    }

    private void setDataAndRegister() {
        if (getActivityInterface() != null) {
            if (Stream.of(this.adapter.getData()).filter(RegisterStepTwo$$Lambda$2.$instance).toList().isEmpty()) {
                this.registrationData = getActivityInterface().getRegistrationData();
                this.registrationData.agreements = this.adapter.getData();
                register();
                return;
            }
            TopToast.show(C0434R.string.check_all_terms, 0, TopToast.DURATION_SHORT);
        }
    }

    static final /* synthetic */ boolean lambda$setDataAndRegister$327$RegisterStepTwo(AgreementWithConsent agreementWithConsent) {
        return (agreementWithConsent.getRegisterAgreement().required && agreementWithConsent.isConsent() == null) ? true : null;
    }

    private void register() {
        if (getActivityInterface() != null) {
            getActivityInterface().setProgressViewVisibility(true);
            new RegisterRepository().register(this.registrationData, new RegisterStepTwo$$Lambda$3(this));
        }
    }

    final /* synthetic */ void lambda$register$328$RegisterStepTwo(boolean z, ErrorResponse errorResponse) {
        getActivityInterface().setProgressViewVisibility(false);
        if (z) {
            onRegisterUser();
        } else {
            onRegisterFail(errorResponse);
        }
    }

    private void onRegisterUser() {
        if (getActivityInterface() != null) {
            getActivityInterface().changeFragment(RegisterStepThree.newInstance());
        }
    }

    private void onRegisterFail(ErrorResponse errorResponse) {
        if (getActivityInterface() != null) {
            getActivityInterface().setProgressViewVisibility(false);
        }
        errorResponse = errorResponse != null ? errorResponse.errorResponse.code : null;
        if (ErrorResponse.MOBILE_ERROR_CODE.equals(errorResponse)) {
            TopToast.show(C0434R.string.mobile_already_registered, 0, TopToast.DURATION_SHORT);
        } else if ("26".equals(errorResponse) != null) {
            TopToast.show(C0434R.string.pesel_number_not_valid, 0, TopToast.DURATION_SHORT);
        } else {
            TopToast.show(C0434R.string.register_failed, 0, TopToast.DURATION_SHORT);
        }
    }

    public void onContactClick() {
        if (getActivityInterface() != null) {
            getActivityInterface().changeFragment(ContactUsFragment.newInstance(), true);
        }
    }

    public void onStop() {
        super.onStop();
        NetTools.cancelSavedOngoingCalls(this.callsToken);
        if (getActivityInterface() != null) {
            getActivityInterface().setProgressViewVisibility(false);
        }
    }
}
