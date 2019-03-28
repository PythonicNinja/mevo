package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;

public abstract class ErrorDialogFragmentFactory<T> {
    protected final ErrorDialogConfig config;

    @TargetApi(11)
    public static class Honeycomb extends ErrorDialogFragmentFactory<Fragment> {
        public Honeycomb(ErrorDialogConfig errorDialogConfig) {
            super(errorDialogConfig);
        }

        protected Fragment createErrorFragment(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
            throwableFailureEvent = new org.greenrobot.eventbus.util.ErrorDialogFragments.Honeycomb();
            throwableFailureEvent.setArguments(bundle);
            return throwableFailureEvent;
        }
    }

    public static class Support extends ErrorDialogFragmentFactory<android.support.v4.app.Fragment> {
        public Support(ErrorDialogConfig errorDialogConfig) {
            super(errorDialogConfig);
        }

        protected android.support.v4.app.Fragment createErrorFragment(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
            throwableFailureEvent = new org.greenrobot.eventbus.util.ErrorDialogFragments.Support();
            throwableFailureEvent.setArguments(bundle);
            return throwableFailureEvent;
        }
    }

    protected abstract T createErrorFragment(ThrowableFailureEvent throwableFailureEvent, Bundle bundle);

    protected ErrorDialogFragmentFactory(ErrorDialogConfig errorDialogConfig) {
        this.config = errorDialogConfig;
    }

    protected T prepareErrorFragment(ThrowableFailureEvent throwableFailureEvent, boolean z, Bundle bundle) {
        if (throwableFailureEvent.isSuppressErrorUi()) {
            return null;
        }
        if (bundle != null) {
            bundle = (Bundle) bundle.clone();
        } else {
            bundle = new Bundle();
        }
        if (!bundle.containsKey(ErrorDialogManager.KEY_TITLE)) {
            bundle.putString(ErrorDialogManager.KEY_TITLE, getTitleFor(throwableFailureEvent, bundle));
        }
        if (!bundle.containsKey(ErrorDialogManager.KEY_MESSAGE)) {
            bundle.putString(ErrorDialogManager.KEY_MESSAGE, getMessageFor(throwableFailureEvent, bundle));
        }
        if (!bundle.containsKey(ErrorDialogManager.KEY_FINISH_AFTER_DIALOG)) {
            bundle.putBoolean(ErrorDialogManager.KEY_FINISH_AFTER_DIALOG, z);
        }
        if (!bundle.containsKey(ErrorDialogManager.KEY_EVENT_TYPE_ON_CLOSE) && this.config.defaultEventTypeOnDialogClosed) {
            bundle.putSerializable(ErrorDialogManager.KEY_EVENT_TYPE_ON_CLOSE, this.config.defaultEventTypeOnDialogClosed);
        }
        if (!bundle.containsKey(ErrorDialogManager.KEY_ICON_ID) && this.config.defaultDialogIconId) {
            bundle.putInt(ErrorDialogManager.KEY_ICON_ID, this.config.defaultDialogIconId);
        }
        return createErrorFragment(throwableFailureEvent, bundle);
    }

    protected String getTitleFor(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
        return this.config.resources.getString(this.config.defaultTitleId);
    }

    protected String getMessageFor(ThrowableFailureEvent throwableFailureEvent, Bundle bundle) {
        return this.config.resources.getString(this.config.getMessageIdForThrowable(throwableFailureEvent.throwable));
    }
}
