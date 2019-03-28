package com.mevo.app.tools.location;

import android.location.Location;
import android.support.annotation.Nullable;

public interface LocationCallback {
    void onReceive(@Nullable Location location);
}
