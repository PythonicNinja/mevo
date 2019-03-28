package com.mevo.app.data.repository;

import com.mevo.app.data.repository.RegisterRepository.RegisterListener;

final /* synthetic */ class RegisterRepository$$Lambda$5 implements Runnable {
    private final RegisterListener arg$1;

    RegisterRepository$$Lambda$5(RegisterListener registerListener) {
        this.arg$1 = registerListener;
    }

    public void run() {
        this.arg$1.onResponse(true, null);
    }
}
