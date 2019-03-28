package com.mevo.app.presentation.entry;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class LoginFragment$$Lambda$9 implements OnEditorActionListener {
    private final LoginFragment arg$1;

    LoginFragment$$Lambda$9(LoginFragment loginFragment) {
        this.arg$1 = loginFragment;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return this.arg$1.lambda$setListeners$166$LoginFragment(textView, i, keyEvent);
    }
}
