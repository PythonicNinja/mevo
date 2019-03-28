package com.mevo.app.presentation.register;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.custom_views.AppToolbar.AppToolbarListener;

public class ClausulesFragment extends RegisterFragment implements AppToolbarListener {
    private TextView clausules;

    public void onBackClick() {
    }

    public void onContactClick() {
    }

    public void onSettingsClick() {
    }

    public static ClausulesFragment newInstance() {
        return new ClausulesFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_clausules, viewGroup, false);
        this.clausules = (TextView) layoutInflater.findViewById(C0434R.id.closures_info_text);
        this.clausules.setMovementMethod(LinkMovementMethod.getInstance());
        return layoutInflater;
    }
}
