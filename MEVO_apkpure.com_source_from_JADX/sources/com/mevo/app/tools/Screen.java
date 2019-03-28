package com.mevo.app.tools;

import android.content.Context;
import com.mevo.app.App;

public class Screen {
    public static int pxToDp(int i) {
        return (int) (((float) i) / App.getAppContext().getApplicationContext().getResources().getDisplayMetrics().density);
    }

    public static int dpToPx(float f) {
        return (int) (f * App.getAppContext().getApplicationContext().getResources().getDisplayMetrics().density);
    }

    public static int dpToPx(float f, Context context) {
        return (int) (f * context.getApplicationContext().getResources().getDisplayMetrics().density);
    }
}
