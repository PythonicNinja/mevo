package com.mevo.app.data.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponseShouldRegisterForPushMessages {
    @SerializedName("should_send_push")
    private boolean shouldRegisterForPushMessages;

    public boolean getShouldRegisterForPushMessages() {
        return this.shouldRegisterForPushMessages;
    }

    public void setShouldRegisterForPushMessages(boolean z) {
        this.shouldRegisterForPushMessages = z;
    }
}
