package com.mevo.app.tools;

import android.support.design.widget.BottomSheetBehavior;

public class BotomSheetHelper {
    public static void toggleSheet(BottomSheetBehavior bottomSheetBehavior) {
        if (bottomSheetBehavior.getState() == 3) {
            bottomSheetBehavior.setState(4);
        } else if (bottomSheetBehavior.getState() == 4) {
            bottomSheetBehavior.setState(3);
        }
    }
}
