package com.mevo.app.presentation.register;

import android.support.v4.app.Fragment;
import com.mevo.app.data.model.RegistrationData;
import com.mevo.app.presentation.ToolbarActivityInterface;

public interface RegisterActivityInterface extends ToolbarActivityInterface {
    void changeFragment(Fragment fragment);

    void changeFragment(Fragment fragment, boolean z);

    RegistrationData getRegistrationData();

    void goToMainActivityAndClearStack();

    void popFragment();

    void setProgressViewVisibility(boolean z);
}
