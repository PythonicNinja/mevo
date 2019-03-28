package com.inverce.mod.integrations.support.annotations;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public interface MapValue<T, Y> {

    public interface ToDrawable<T> {
        Drawable get(T t);
    }

    public interface ToDrawableRes<T> {
        @DrawableRes
        int get(T t);
    }

    public interface ToStringRes<T> {
        @StringRes
        int get(T t);
    }

    Y get(T t);
}
