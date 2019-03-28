package com.mevo.app.presentation.main;

import android.support.v4.app.Fragment;
import com.mevo.app.presentation.ToolbarActivityInterface;

public interface MainActivityInterface extends ToolbarActivityInterface {
    void changeFragment(Fragment fragment, boolean z);

    void changeFragment(MainFragment mainFragment);

    void changeFragment(MainFragment mainFragment, boolean z);

    MainFragment getVisibleFragment();

    void goToEntryActivityAndFinish();

    void goToHome();

    void popFragment();

    void refreshCurrentFragment();

    void setAppToolbarVisibility(boolean z);

    void setProgressViewVisibility(boolean z);
}
