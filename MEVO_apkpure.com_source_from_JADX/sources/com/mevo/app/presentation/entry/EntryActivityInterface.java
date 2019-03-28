package com.mevo.app.presentation.entry;

import android.support.v4.app.Fragment;
import com.mevo.app.presentation.ToolbarActivityInterface;

public interface EntryActivityInterface extends ToolbarActivityInterface {
    void changeFragment(Fragment fragment);

    void changeFragment(Fragment fragment, boolean z);

    void clearBackStack();

    void goToMainActivityAndFinish();

    void popFragment();
}
