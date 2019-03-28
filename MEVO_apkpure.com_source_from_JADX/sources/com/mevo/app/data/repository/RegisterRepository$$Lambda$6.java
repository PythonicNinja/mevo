package com.mevo.app.data.repository;

import com.mevo.app.data.model.response.ErrorResponse;
import com.mevo.app.data.repository.RegisterRepository.RegisterListener;

final /* synthetic */ class RegisterRepository$$Lambda$6 implements Runnable {
    private final RegisterListener arg$1;
    private final ErrorResponse arg$2;

    RegisterRepository$$Lambda$6(RegisterListener registerListener, ErrorResponse errorResponse) {
        this.arg$1 = registerListener;
        this.arg$2 = errorResponse;
    }

    public void run() {
        this.arg$1.onResponse(false, this.arg$2);
    }
}
