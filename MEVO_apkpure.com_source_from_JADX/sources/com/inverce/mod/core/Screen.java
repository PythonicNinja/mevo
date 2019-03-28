package com.inverce.mod.core;

import android.app.Activity;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Screen {
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;

    public static boolean isXLargeTablet() {
        return (IM.resources().getConfiguration().screenLayout & 15) >= 4;
    }

    @NonNull
    public static Point getScreenSize() {
        Display defaultDisplay;
        Point point = new Point();
        if (IM.context() instanceof Activity) {
            defaultDisplay = ((Activity) IM.context()).getWindowManager().getDefaultDisplay();
        } else {
            defaultDisplay = ((WindowManager) IM.context().getSystemService("window")).getDefaultDisplay();
        }
        defaultDisplay.getSize(point);
        return point;
    }

    @NonNull
    public static Point getActivitySize() {
        Point screenSize = getScreenSize();
        screenSize.y -= getStatusBarHeight();
        Activity activity = IM.activity();
        if (activity != null) {
            try {
                int measuredHeight = activity.findViewById(16908290).getMeasuredHeight();
                if (measuredHeight > 0 && screenSize.y != measuredHeight) {
                    screenSize.y = measuredHeight;
                }
            } catch (Throwable e) {
                Log.exs("Tools", "getActivitySize", e);
            }
        }
        return screenSize;
    }

    public static int pxToDp(float f) {
        return (int) (f / IM.resources().getDisplayMetrics().density);
    }

    public static int dpToPx(float f) {
        return (int) (f * IM.resources().getDisplayMetrics().density);
    }

    @NonNull
    public static Point getLocationOnScreen(@Nullable View view) {
        int[] iArr = new int[2];
        if (view == null) {
            return new Point(-1, -1);
        }
        view.getLocationOnScreen(iArr);
        return new Point(iArr[0], iArr[1]);
    }

    @NonNull
    public static Point getViewSize(@Nullable View view) {
        if (view == null) {
            return new Point(-1, -1);
        }
        return new Point(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public static int getStatusBarHeight() {
        int identifier = IM.resources().getIdentifier("status_bar_height", "dimen", "android");
        return identifier > 0 ? IM.resources().getDimensionPixelSize(identifier) : 0;
    }
}
