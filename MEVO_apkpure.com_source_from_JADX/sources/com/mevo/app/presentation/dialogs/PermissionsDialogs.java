package com.mevo.app.presentation.dialogs;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.inverce.mod.core.IM;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.dialogs.InfoDialog.Builder;

public class PermissionsDialogs {
    static final /* synthetic */ void lambda$showEnableLocalization$140$PermissionsDialogs(View view) {
    }

    static final /* synthetic */ void lambda$showNoPermissionLocalization$138$PermissionsDialogs(View view) {
    }

    public static void showNoPermissionLocalization() {
        new Builder().setText((int) C0434R.string.missing_permissions_dialog).setNeutralButton((int) C0434R.string.close, PermissionsDialogs$$Lambda$0.$instance).setPositiveButton((int) C0434R.string.settings, PermissionsDialogs$$Lambda$1.$instance).build().show();
    }

    public static void showEnableLocalization() {
        new Builder().setText((int) C0434R.string.missing_localization_dialog).setNeutralButton((int) C0434R.string.close, PermissionsDialogs$$Lambda$2.$instance).setPositiveButton((int) C0434R.string.settings, PermissionsDialogs$$Lambda$3.$instance).build().show();
    }

    private static void goToAppSettings() {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package:");
        stringBuilder.append(App.getAppContext().getPackageName());
        intent.setData(Uri.parse(stringBuilder.toString()));
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        App.getAppContext().startActivity(intent);
    }

    private static void goToLocalizationSettings() {
        IM.context().startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }
}
