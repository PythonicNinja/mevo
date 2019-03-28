package com.google.firebase.iid;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.Task;

@KeepForSdk
public interface MessagingChannel {
    @KeepForSdk
    Task<Void> ackMessage(String str);

    @KeepForSdk
    Task<Void> buildChannel(String str, String str2);

    @KeepForSdk
    Task<Void> deleteInstanceId(String str);

    @KeepForSdk
    Task<Void> deleteToken(String str, String str2, String str3);

    @KeepForSdk
    Task<String> getToken(String str, String str2, String str3);

    @KeepForSdk
    boolean isAvailable();

    @KeepForSdk
    boolean isChannelBuilt();

    @KeepForSdk
    Task<Void> subscribeToTopic(String str, String str2, String str3);

    @KeepForSdk
    Task<Void> unsubscribeFromTopic(String str, String str2, String str3);
}
