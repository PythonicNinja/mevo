package me.dm7.barcodescanner.core;

import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.view.WindowManager;

public class DisplayUtils {
    public static Point getScreenResolution(Context context) {
        context = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (VERSION.SDK_INT >= 13) {
            context.getSize(point);
        } else {
            point.set(context.getWidth(), context.getHeight());
        }
        return point;
    }

    public static int getScreenOrientation(Context context) {
        context = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (context.getWidth() == context.getHeight()) {
            return 3;
        }
        return context.getWidth() < context.getHeight() ? 1 : 2;
    }
}
