package com.mevo.app.presentation.entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.ToolbarActivityInterface;
import com.mevo.app.presentation.base.BaseActivity;
import com.mevo.app.presentation.custom_views.AppToolbar;
import com.mevo.app.presentation.custom_views.AppToolbar.AppToolbarListener;
import com.mevo.app.presentation.main.MainActivity;
import com.mevo.app.presentation.main.contact_us.ContactUsFragment;

public class EntryActivity extends BaseActivity implements EntryActivityInterface, AppToolbarListener, ToolbarActivityInterface {
    private AppToolbar appToolbar;
    private ViewGroup fragmentContainer;

    public void onSettingsClick() {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0434R.layout.activity_entry);
        findViews();
        setListeners();
        showFirstFragmentIfShould();
    }

    private void setListeners() {
        this.appToolbar.setAppToolbarListener(this);
    }

    private void findViews() {
        this.appToolbar = (AppToolbar) findViewById(C0434R.id.activity_entry_app_toolbar);
        this.fragmentContainer = (ViewGroup) findViewById(C0434R.id.activity_entry_fragment_container);
    }

    private void showFirstFragmentIfShould() {
        if (this.fragmentContainer.getChildCount() == 0) {
            changeFragment(LoginFragment.newInstance(), false);
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

    public void goToMainActivityAndFinish() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void clearBackStack() {
        getSupportFragmentManager().popBackStack(null, 1);
    }

    public void onBackClick() {
        onBackPressed();
    }

    public void onContactClick() {
        changeFragment(ContactUsFragment.newInstance(), true);
    }

    public AppToolbar getToolbar() {
        return this.appToolbar;
    }
}
