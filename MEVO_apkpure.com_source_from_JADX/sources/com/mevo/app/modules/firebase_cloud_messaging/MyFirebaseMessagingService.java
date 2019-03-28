package com.mevo.app.modules.firebase_cloud_messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.text.TextUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.annotations.SerializedName;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Lifecycle;
import com.inverce.mod.core.interfaces.LifecycleState;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.User;
import com.mevo.app.data.model.event.EventDataBikeReturn;
import com.mevo.app.data.network.Rest;
import com.mevo.app.data.shared_prefs.SharedPrefs;
import com.mevo.app.presentation.main.MainActivity;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.UserManager;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String KEY_DATA = "data";
    public static final int REQUEST_CODE_BIKE_RETURN = 5200;
    public static final int REQUEST_CODE_ERROR_REPORT = 5201;
    private static final String TAG = "MyFirebaseMessagingService";

    public static class PushData {
        public static final String ACTION_PROBLEM_REPORT = "problem_report";
        public static final String ACTION_RENT = "rent";
        public static final String ACTION_RETURN = "return";
        public static final String OS_ANDROID = "android";
        public static final String PARAM_ACTION = "action";
        public static final String PARAM_BIKES_SYSTEM_ID = "system_id";
        public static final String PARAM_BIKE_NUMBER = "bike_number";
        public static final String PARAM_LANGUAGE = "lang";
        public static final String PARAM_MOBILE_NUMBER = "mobile_number";
        public static final String PARAM_OS = "os";
        public static final String PARAM_PROBLEM_ID = "problem_id";
        public static final String PARAM_TOKEN = "regid";
        @SerializedName("action")
        public String action;
        @SerializedName("bike_number")
        public String bikeNumber;
        @SerializedName("lang")
        public String language;
        @SerializedName("message_body")
        public String message;
        @SerializedName("mobile_number")
        public String mobileNumber;
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.m94i(TAG, "Received push");
        if (SharedPrefs.getReturnBikePushSettings()) {
            PushData pushData = (PushData) Rest.getJsonSerializer().fromJson((String) remoteMessage.getData().get("data"), PushData.class);
            if (isDataValid(pushData) && isUserLogged(pushData.mobileNumber)) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Push data:\nmessage: ");
                stringBuilder.append(pushData.message);
                Log.m94i(str, stringBuilder.toString());
                str = pushData.action;
                Object obj = -1;
                int hashCode = str.hashCode();
                if (hashCode != -1886638380) {
                    if (hashCode != -934396624) {
                        if (hashCode == 3496761) {
                            if (str.equals(PushData.ACTION_RENT)) {
                                obj = 1;
                            }
                        }
                    } else if (str.equals("return")) {
                        obj = null;
                    }
                } else if (str.equals(PushData.ACTION_PROBLEM_REPORT)) {
                    obj = 2;
                }
                switch (obj) {
                    case null:
                        onPushReturn(pushData);
                        break;
                    case 1:
                        break;
                    case 2:
                        onPushErrorReport(pushData);
                        break;
                    default:
                        break;
                }
            } else if (isDataValid(pushData) == null) {
                Log.m90e(TAG, "Push is invalid");
            } else {
                Log.m90e(TAG, "User is not logged in");
            }
            return;
        }
        Log.m102w(TAG, "Push messages are off");
    }

    private void onPushReturn(PushData pushData) {
        if (IM.activity() == null || Lifecycle.getActivityState() != LifecycleState.Resumed) {
            NotificationsHelper.sendNotification(App.getAppContext().getString(C0434R.string.app_name), pushData.message, App.getAppContext(), createReturnPendingIntent(pushData));
        } else {
            IM.onUi().execute(new MyFirebaseMessagingService$$Lambda$0(pushData));
        }
    }

    private void onPushErrorReport(PushData pushData) {
        NotificationsHelper.sendNotification(App.getAppContext().getString(C0434R.string.app_name), pushData.message, App.getAppContext(), createErrorReportPendingIntent());
    }

    private boolean isUserLogged(String str) {
        User user = UserManager.getUser();
        return (user == null || user.getPhoneNumber().equalsIgnoreCase(str) == null) ? null : true;
    }

    private boolean isDataValid(PushData pushData) {
        return (pushData == null || TextUtils.isEmpty(pushData.message) || TextUtils.isEmpty(pushData.mobileNumber) != null) ? null : true;
    }

    private PendingIntent createReturnPendingIntent(PushData pushData) {
        if (TextUtils.isEmpty(pushData.bikeNumber)) {
            return PendingIntent.getActivity(App.getAppContext(), REQUEST_CODE_BIKE_RETURN, new Intent(App.getAppContext(), MainActivity.class), 134217728);
        }
        Intent intent = new Intent(App.getAppContext(), MainActivity.class);
        intent.setAction(EventDataBikeReturn.ACTION_BIKE_RETURN);
        intent.putExtra(EventDataBikeReturn.EXTRA_BIKE_NUMBER, pushData.bikeNumber);
        return PendingIntent.getActivity(App.getAppContext(), REQUEST_CODE_BIKE_RETURN, intent, 134217728);
    }

    private PendingIntent createErrorReportPendingIntent() {
        return PendingIntent.getActivity(App.getAppContext(), REQUEST_CODE_ERROR_REPORT, new Intent(App.getAppContext(), MainActivity.class), 134217728);
    }
}
