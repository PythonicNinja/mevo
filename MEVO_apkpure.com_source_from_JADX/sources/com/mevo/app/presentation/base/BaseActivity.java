package com.mevo.app.presentation.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import com.mevo.app.tools.LocaleHelper;

public abstract class BaseActivity extends AppCompatActivity {
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LocaleHelper.onAttach(context));
    }
}
