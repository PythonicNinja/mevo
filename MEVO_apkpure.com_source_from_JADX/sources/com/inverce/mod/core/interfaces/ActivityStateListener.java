package com.inverce.mod.core.interfaces;

import android.app.Activity;
import android.os.Bundle;

public interface ActivityStateListener {
    void activityStateChanged(LifecycleState lifecycleState, Activity activity, Bundle bundle);
}
