package com.mevo.app.tools;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.mevo.app.App;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;

public class NetTools {
    public static final String TAG = "NetTools";
    private static SparseArray<List<WeakReference<Call>>> savedCalls = new SparseArray();

    public static class CallsToken {
        private static int currentToken;
        private int value = currentToken;

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj != null) {
                if (getClass() == obj.getClass()) {
                    if (this.value != ((CallsToken) obj).value) {
                        z = false;
                    }
                    return z;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.value;
        }

        public CallsToken() {
            currentToken++;
        }

        public int get() {
            return this.value;
        }
    }

    public static void cancelCallIfOngoing(@Nullable Call call) {
        if (call != null && call.isExecuted() && !call.isCanceled()) {
            call.cancel();
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cancelling call if not finished: ");
            stringBuilder.append(call.request().url());
            Log.m102w(str, stringBuilder.toString());
        }
    }

    public static void cancelSavedOngoingCalls(@NonNull CallsToken callsToken) {
        List list = (List) savedCalls.get(callsToken.get());
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (((WeakReference) list.get(i)).get() != null) {
                    cancelCallIfOngoing((Call) ((WeakReference) list.get(i)).get());
                }
            }
        }
        savedCalls.remove(callsToken.get());
    }

    public static <T extends Call> T saveCall(@NonNull CallsToken callsToken, T t) {
        List list = (List) savedCalls.get(callsToken.get());
        if (list == null) {
            list = new ArrayList();
            savedCalls.put(callsToken.get(), list);
        }
        list.add(new WeakReference(t));
        return t;
    }

    public static boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) App.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
