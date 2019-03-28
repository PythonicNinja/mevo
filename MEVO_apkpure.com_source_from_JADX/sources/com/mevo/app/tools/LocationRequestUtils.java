package com.mevo.app.tools;

import com.inverce.mod.core.IM;
import com.mevo.app.presentation.dialogs.PermissionsDialogs;
import com.mevo.app.tools.location.LocationTool;

public class LocationRequestUtils {
    public static boolean checkPermissionsAndLocationEnabled() {
        if (!Permissions.hasPermissions(Permissions.PERMISSIONS_LOCATION, IM.activity())) {
            PermissionsDialogs.showNoPermissionLocalization();
        } else if (LocationTool.areLocationServicesEnabled()) {
            return true;
        } else {
            PermissionsDialogs.showEnableLocalization();
        }
        return false;
    }
}
