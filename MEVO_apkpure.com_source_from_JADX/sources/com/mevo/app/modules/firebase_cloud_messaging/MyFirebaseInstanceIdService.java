package com.mevo.app.modules.firebase_cloud_messaging;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.mevo.app.tools.Log;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseInstanceIdService";

    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Refreshed token:\n");
        stringBuilder.append(token);
        Log.m94i(str, stringBuilder.toString());
    }
}
