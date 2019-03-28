package com.mevo.app.presentation.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import com.mevo.app.C0434R;
import com.mevo.app.Cfg;
import com.mevo.app.data.model.RegistrationData;
import com.mevo.app.presentation.ToolbarActivityInterface;
import com.mevo.app.presentation.base.BaseActivity;
import com.mevo.app.presentation.custom_views.AppToolbar;
import com.mevo.app.presentation.custom_views.AppToolbar.AppToolbarListener;
import com.mevo.app.presentation.custom_views.ProgressOverlayView;
import com.mevo.app.presentation.main.MainActivity;
import com.mevo.app.presentation.main.contact_us.ContactUsFragment;
import com.mevo.app.presentation.register.register_step_one.RegisterStepOne;
import com.mevo.app.presentation.register.register_step_three.RegisterStepThree;
import com.mevo.app.tools.LocaleHelper;

public class RegisterActivity extends BaseActivity implements RegisterActivityInterface, AppToolbarListener, ToolbarActivityInterface {
    private AppToolbar appToolbar;
    private ViewGroup fragmentContainer;
    private ProgressOverlayView progressOverlayView;
    private RegistrationData registrationData;

    public void onSettingsClick() {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0434R.layout.activity_register);
        initRegistrationData();
        findViews();
        setListeners();
        showFirstStep();
    }

    private void setListeners() {
        this.appToolbar.setAppToolbarListener(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.appToolbar.setAppToolbarListener(null);
    }

    private void initRegistrationData() {
        this.registrationData = new RegistrationData();
        this.registrationData.domain = Cfg.get().domain();
        this.registrationData.languageCode = LocaleHelper.getLanguage(this);
    }

    private void findViews() {
        this.appToolbar = (AppToolbar) findViewById(C0434R.id.activity_register_app_toolbar);
        this.fragmentContainer = (ViewGroup) findViewById(C0434R.id.activity_register_fragment_container);
        this.progressOverlayView = (ProgressOverlayView) findViewById(C0434R.id.activity_register_progress_overlay);
    }

    private void showFirstStep() {
        if (this.fragmentContainer.getChildCount() == 0) {
            changeFragment(RegisterStepOne.newInstance(), false);
        }
    }

    public void changeFragment(Fragment fragment) {
        changeFragment(fragment, true);
    }

    public void changeFragment(Fragment fragment, boolean z) {
        fragment = getSupportFragmentManager().beginTransaction().replace(this.fragmentContainer.getId(), fragment);
        if (z) {
            fragment.addToBackStack(false);
        }
        fragment.commit();
    }

    public void popFragment() {
        getSupportFragmentManager().popBackStack();
    }

    public void disableBack() {
        this.appToolbar.setBackVisibility(false);
    }

    public RegistrationData getRegistrationData() {
        return this.registrationData;
    }

    public void setProgressViewVisibility(boolean z) {
        if (z) {
            this.progressOverlayView.show();
        } else {
            this.progressOverlayView.hide();
        }
    }

    public void onBackClick() {
        onBackPressed();
    }

    public void onContactClick() {
        this.appToolbar.setContactVisibility(false);
        changeFragment(ContactUsFragment.newInstance(), true);
    }

    public void onBackPressed() {
        if (!(getSupportFragmentManager().findFragmentById(C0434R.id.activity_register_fragment_container) instanceof RegisterStepThree)) {
            super.onBackPressed();
        }
        setAppToolbarVisibility(true);
    }

    public void setAppToolbarVisibility(boolean z) {
        if (z) {
            this.appToolbar.setVisibility(0);
        } else {
            this.appToolbar.setVisibility(8);
        }
    }

    public void goToMainActivityAndClearStack() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(335577088);
        startActivity(intent);
    }

    public AppToolbar getToolbar() {
        return this.appToolbar;
    }
}
